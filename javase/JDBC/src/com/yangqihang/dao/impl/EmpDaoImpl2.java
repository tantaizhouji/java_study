package com.yangqihang.dao.impl;

import com.yangqihang.dao.EmpDao;
import com.yangqihang.entity.Emp;
import com.yangqihang.util.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;

public class EmpDaoImpl2 implements EmpDao {

    /*
     * 当插入数据的时候,要注意属性类型的匹配
     * 1.Date
     * 2.String类型在拼接sql的时候必须要添加''
     * */
    @Override
    public void insert(Emp emp) {
        Connection connection = null;
        //设置事务是否自动提交,true表示自动提交,false表示不是自动提交
//        connection.setAutoCommit(true);
        PreparedStatement preparedsatement = null;
        try {
            connection = DBUtil.getConnection();
            //拼接sql语句
            String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
            preparedsatement = connection.prepareStatement(sql);
            //向问号中添加值
            preparedsatement.setInt(1, emp.getEmpno());
            preparedsatement.setString(2, emp.getEname());
            preparedsatement.setString(3, emp.getJob());
            preparedsatement.setInt(4, emp.getMgr());
            preparedsatement.setDate(5, new java.sql.Date(new SimpleDateFormat("yyyy-MM-DD").parse(emp.getHiredate()).getTime()));
            preparedsatement.setDouble(6, emp.getSal());
            preparedsatement.setDouble(7, emp.getComm());
            preparedsatement.setInt(8, emp.getDeptno());
            //返回值表示受影响的行数
            int i = preparedsatement.executeUpdate();
            System.out.println("受影响的行数是" + i);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedsatement);
        }
    }

    @Override
    public void delete(Emp emp) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from emp where empno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, emp.getEmpno());
            int i = preparedStatement.executeUpdate();
            System.out.println("受影响的行数是" + i);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void update(Emp emp) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update emp set job = 'SALES' where empno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, emp.getEmpno());
            int i = preparedStatement.executeUpdate();
            System.out.println("受影响的行数是" + i);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public Emp getEmpByEmpno(Integer empno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from emp where empno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,empno);
            resultSet = preparedStatement.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"), resultSet.getString("ename"),
                        resultSet.getString("job"), resultSet.getInt("mgr"), sdf.format(resultSet.getDate("hiredate")),
                        resultSet.getDouble("sal"), resultSet.getDouble("comm"), resultSet.getInt("deptno"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedStatement, resultSet);
        }
        return emp;
    }

    @Override
    public Emp getEmpByEname(String ename) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from emp where ename = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ename);
            resultSet = preparedStatement.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"), resultSet.getString("ename"),
                        resultSet.getString("job"), resultSet.getInt("mgr"), sdf.format(resultSet.getDate("hiredate")),
                        resultSet.getDouble("sal"), resultSet.getDouble("comm"), resultSet.getInt("deptno"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedStatement, resultSet);
        }
        return emp;
    }

    public static void main(String[] args) {
        EmpDao empDao = new EmpDaoImpl2();
        Emp emp = new Emp(7777, "TOM", "SALESWOMAN", 7698, "2019-12-5", 1500.0, 500.0, 10);
//        empDao.insert(emp);
//        empDao.delete(emp);
//        empDao.update(emp);
        Emp emp1 = empDao.getEmpByEmpno(7369);
        //sql注入
//        Emp emp1 = empDao.getEmpByEname("BLAKE");
        System.out.println(emp1);
    }
}
