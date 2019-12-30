package com.yangqihang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Account;
import com.yangqihang.entity.AccountExample;
import com.yangqihang.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accMapper;

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

        return accList.get(0);
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

        AccountExample example = new AccountExample();
        List<Account> accList = accMapper.selectByExample(example);
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

        accMapper.insert(account);

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
        if("".equals(password)) {
            return RespStat.build(502,"密码不能为空");
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
}
