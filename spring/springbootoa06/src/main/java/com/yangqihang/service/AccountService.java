package com.yangqihang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Account;
import com.yangqihang.entity.AccountExample;
import com.yangqihang.entity.Permission;
import com.yangqihang.entity.Role;
import com.yangqihang.mapper.AccountMapper;
import com.yangqihang.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户名和密码查找数据库中是否存在相同的数据
     *
     * @param loginName
     * @param password
     * @return 有，返回对象。没有，返回NULL
     */
    public Account findByLoginNameAndPassword(String loginName, String password) {

        AccountExample example = new AccountExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
        List<Account> accList = accMapper.selectByExample(example);

        if (accList.isEmpty()) {
            return null;
        }

        //得到account和account的id
        Account account = accList.get(0);
        Integer id = account.getId();

        //获取该账号的角色列表和角色的权限列表
        List<Role> roleList = accMapper.findRoleById(id);
        for (Role role : roleList) {
            List<Permission> role_permissionList = roleMapper.findPermissionsById(role.getId());
            role.setPermissionList(role_permissionList);
        }

        //获取该账号的权限列表
        List<Permission> permissionList = accMapper.findPermissionById(id);

        //更新账号的角色列表和权限列表
        account.setRoleList(roleList);
        account.setPerList(permissionList);

        return account;
    }

    /**
     * 查找数据库中所有用户信息，并分页
     *
     * @param pageNum  第几页
     * @param pageSize 每页显示多少个
     * @return 分页好的结果
     */
    public PageInfo<Account> findAll(int pageNum, int pageSize) {

        //分页操作
        PageHelper.startPage(pageNum, pageSize);

        //查询用户列表
        AccountExample example = new AccountExample();
        List<Account> accList = accMapper.selectByExample(example);
        for (Account account : accList) {
            //设置用户的角色列表
            List<Role> account_roleList = accMapper.findRoleById(account.getId());
            for (Role role : account_roleList) {
                //设置用户角色的权限列表
                List<Permission> role_permissionList = roleMapper.findPermissionsById(role.getId());

                role.setPermissionList(role_permissionList);
            }
            //查找用户的权限列表
            List<Permission> account_permissionList = accMapper.findPermissionById(account.getId());

            account.setRoleList(account_roleList);
            account.setPerList(account_permissionList);
        }

        PageInfo<Account> accPageInfo = new PageInfo<>(accList, 5);

        return accPageInfo;
    }

    public RespStat add(Account account) {
        AccountExample example = new AccountExample();
        example.createCriteria().andLoginNameEqualTo(account.getLoginName());
        List<Account> accountList = accMapper.selectByExample(example);
        if (!accountList.isEmpty()) {
            return RespStat.build(400, "账号已被注册");
        }

        //没填图片默认给个地址
        String location = account.getLocation();
        if (null == location || "" == location) {
            account.setLocation("default.jpg");
        }

        accMapper.insertSelective(account);

        return RespStat.build(200, "注册成功,3秒后跳转登录页面");
    }

    public RespStat deleteById(int id) {
        //删除用户，返回数据库影响的行数,通过主键，返回是0/1
        int row = accMapper.deleteByPrimaryKey(id);

        if (0 == row) {
            return RespStat.build(500, "删除失败");
        }

        return RespStat.build(200, "删除成功");
    }

    public RespStat update(int id, String password) {
        if ("".equals(password)) {
            return RespStat.build(502, "密码不能为空");
        }

        //根据ID获得数据库里的数据
        Account account = accMapper.selectByPrimaryKey(id);
        //修改数据库里的数据
        account.setPassword(password);
        int row = accMapper.updateByPrimaryKey(account);

        if (0 == row) {
            return RespStat.build(501, "修改失败");
        }

        return RespStat.build(200, "修改成功,3秒后退出");
    }

    /**
     * 修改头像
     *
     * @param originalFilename
     * @param account
     */
    public void updateProfile(String originalFilename, Account account) {
        if (null == account) {
            throw new RuntimeException("未检测到登录账号");
        }

        account.setLocation(originalFilename);

        accMapper.updateByPrimaryKeySelective(account);
    }

    /**
     * 修改头像
     *
     * @param fileName
     * @param account
     */
    public void updateProfile(MultipartFile fileName, Account account) {
        //上传的头像文件是否为空
        if (null == fileName || fileName.isEmpty()) {
            throw new RuntimeException("没有传头像");
        }
        //账号是否为空
        if (null == account) {
            throw new RuntimeException("未检测到登录账号");
        }

        try {
            //获取classpath路径名称
            String classpath = ResourceUtils.getURL("classpath:").getPath();
            System.out.println("classpath: " + classpath);

            //文件上传是否成功标志
            Boolean success = false;

            //判断路径名称
            if (classpath.startsWith("/D:/")) {
                //路径名称是Windows下的
                System.out.println("Windows");

                //往指定路径上传文件,返回是否成功
                success = uploadFile("d:/github/java_study/uploads/", fileName);

            } else if (classpath.startsWith("file:/")) {
                //路径名称是Linux系统下的
                System.out.println("Linux");

                //往指定路径上传文件,返回是否成功
                success = uploadFile("/var/data/static/uploads/",fileName);
            }

            if(!success){
                throw new RuntimeException("上传文件失败");
            }

            //更新用户头像地址
            account.setLocation(fileName.getOriginalFilename());

            //更新数据库
            accMapper.updateByPrimaryKeySelective(account);

            System.out.println("保存头像成功");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Account findById(int id) {
        if (1 > id) {
            throw new RuntimeException("id非法");
        }

        Account account = accMapper.selectByPrimaryKey(id);

        if (null == account) {
            throw new RuntimeException("系统出错,没有找到该账号");
        }

        List<Role> account_roleList = accMapper.findRoleById(id);
        account.setRoleList(account_roleList);

        return account;
    }

    public RespStat modifyRoles(int[] roles, int id) {
        if (1 > id) {
            return RespStat.build(600, "id非法", id);
        }

        if (null == roles) {
            accMapper.deleteRolesById(id);
        } else {
            accMapper.updateRolesById(roles, id);
        }

        return RespStat.build(200, "修改角色成功");
    }

    /**
     * 上传文件方法:往指定路径上传文件
     * @param path
     * @param fileName
     * @return true，上传成功，false上传失败
     */
    public static Boolean uploadFile(String path, MultipartFile fileName) {
        //存放目录路径
        String uploadDirs = path;
        //获取存放目录实例,并判断是否存在，不存在就创建
        File dirs = new File(uploadDirs);
        if (!dirs.exists()) {
            System.out.println("目录存放不存在");
            dirs.mkdirs();
            System.out.println("创建目录完成");
        }

        //获取存放位置实例
        File uploadPath = new File(uploadDirs);
        System.out.println("uploadPath: " + uploadPath);

        //获取存放位置中上传文件实例
        File upload = new File(uploadPath.getAbsolutePath(), fileName.getOriginalFilename());

        //文件转存
        try {
            fileName.transferTo(upload);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
