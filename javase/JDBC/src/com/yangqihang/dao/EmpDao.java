package com.yangqihang.dao;

import com.yangqihang.entity.Emp;

public interface EmpDao {

    //插入数据
    void insert(Emp emp);

    //删除数据
    void delete(Emp emp);

    //修改数据
    void update(Emp emp);

    //查找数据
    Emp getEmpByEmpno(Integer empno);
    Emp getEmpByEname(String ename);
}
