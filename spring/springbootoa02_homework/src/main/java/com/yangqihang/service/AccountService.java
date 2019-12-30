package com.yangqihang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Account;
import com.yangqihang.entity.AccountExample;
import com.yangqihang.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accMapper;


    /**
     * 数据库查找账号
     *
     * @param loginName
     * @param password
     * @return
     */
    public RespStat findByLoginNameAndPassword(String loginName, String password) {
        //后端判断账号和密码是否为空
        if (null == loginName || "".equals(loginName) || null == password || "".equals(password)) {
            return RespStat.build(405, "账号或密码不能为空", null);
        }

        //从数据库中查询账号名称和密码相同的数据
        AccountExample example = new AccountExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
        List<Account> accList = accMapper.selectByExample(example);

        //数据库查询返回为空
        if (accList.isEmpty()) {
            return RespStat.build(404, "该账号未注册或者密码错误", accList);
        }

        return RespStat.build(200, "登录成功,3秒后跳转到主页", accList);
    }

    public RespStat add(Account account) {
        //后端判断账号密码是否为空
        String loginName = account.getLoginName();
        String password = account.getPassword();
        if (null == loginName || "".equals(loginName) || null == password || "".equals(password)) {
            return RespStat.build(406, "账号或密码不能为空", null);
        }

        //判断数据库中是否有相同账号名称的数据
        AccountExample example = new AccountExample();
        example.createCriteria().andLoginNameEqualTo(loginName);
        List<Account> accList = accMapper.selectByExample(example);

        //数据库中存在相同账号名称的数据,注册失败
        if (!accList.isEmpty()) {
            return RespStat.build(505, "账号已被注册", null);
        }

        //插入实体对象，为设置的属性在数据库中就会以默认值显示
        accMapper.insertSelective(account);
        return RespStat.build(200, "注册成功,3秒后跳转到登录界面", account);
    }

    public PageInfo<Account> findAll(int pageNum, int pageSize) {
        //分页注入
        PageHelper.startPage(pageNum, pageSize);

        //查找数据库中所有信息
        AccountExample example = new AccountExample();
        List<Account> accList = accMapper.selectByExample(example);

        //返回分页好的数据
        PageInfo<Account> accPageInfo = new PageInfo<>(accList, 5);

        return accPageInfo;
    }

    public RespStat deleteById(int id, Account account) {
        if (0 == id) {
            return RespStat.build(601, "id非法不能为0", null);
        }

        //判断account是否为空
        if (null == account) {
            return RespStat.build(404, "系统出错", null);
        }

        //登录用户不是admin权限，无法执行删除操作
        if (!"admin".equals(account.getRole())) {
            return RespStat.build(410, "你不是管理员，无法进行删除操作", null);
        }

        //根据ID删除数据，返回影响的行数
        int row = accMapper.deleteByPrimaryKey(id);
        if (row == 0) {
            return RespStat.build(602, "删除失败，没有该数据", null);
        }

        return RespStat.build(200, "删除成功", null);
    }

    public RespStat update(int id, String password) {
        //后端校验ID是否非法和密码是否为空
        if (0 > id || null == password || "".equals(password)) {
            return RespStat.build(507, "ID非法或密码为空", null);
        }

        //通过ID得到数据库中的数据
        Account account = accMapper.selectByPrimaryKey(id);

        //数据库中不存在数据
        if (null == account) {
            return RespStat.build(505, "该用户不存在", null);
        }

        //数据库中的密码是否和新密码一致
        if (account.getPassword().equals(password)) {
            return RespStat.build(506, "新密码与原密码相同", account);
        }

        //更新密码
        account.setPassword(password);
        int row = accMapper.updateByPrimaryKeySelective(account);

        //更新数据库操作失败
        if (0 == row) {
            return RespStat.build(504, "修改密码失败", account);
        }

        return RespStat.build(200, "修改密码成功,3秒后弹框消失", account);
    }

    public RespStat updateRole(int id, String role,Account accSession) {
        if (id == 0) {
            return RespStat.build(507, "系统出错，ID非法", null);
        }

        if(!accSession.getRole().equals("admin")) {
            return RespStat.build(505, "不是管理员无法修改", null);
        }

        if (!"user".equals(role) && !"admin".equals(role)) {
            return RespStat.build(505, "没有这种权限: " + role + " 无法修改", null);
        }

        //获取数据库里的数据
        Account account = accMapper.selectByPrimaryKey(id);

        if (null == account) {
            return RespStat.build(505, "系统出错，没找到该用户", null);
        }

        if (account.getRole().equals(role)) {
            return RespStat.build(506, "无需修改相同的权限", null);
        }

        //更新数据库里的权限数据
        account.setRole(role);
        int row = accMapper.updateByPrimaryKeySelective(account);

        if (0 == row) {
            return RespStat.build(507, "系统出错，修改失败", null);
        }

        return RespStat.build(200, "修改权限成功", account);
    }
}
