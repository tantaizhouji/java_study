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
}
