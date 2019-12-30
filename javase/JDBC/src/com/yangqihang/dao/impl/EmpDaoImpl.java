package com.yangqihang.dao.impl;

import com.yangqihang.dao.EmpDao;
import com.yangqihang.entity.Emp;
import com.yangqihang.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class EmpDaoImpl implements EmpDao {

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
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            //拼接sql语句
            String sql = "insert into emp values(" + emp.getEmpno() + ",'" + emp.getEname() + "','" + emp.getJob() + "',"
                    + emp.getMgr() + ",to_date('" + emp.getHiredate() + "','YYYY-MM-DD')," + emp.getSal() + ","
                    + emp.getComm() + "," + emp.getDeptno() + ")";
            System.out.println(sql);
            //返回值表示受影响的行数
            int i = statement.executeUpdate(sql);
            System.out.println("受影响的行数是" + i);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public void delete(Emp emp) {
        Connection connection = null;
        //设置事务是否自动提交,true表示自动提交,false表示不是自动提交
//        connection.setAutoCommit(true);
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            //拼接sql语句
            String sql = "delete from emp where empno=" + emp.getEmpno();
            System.out.println(sql);
            //返回值表示受影响的行数
            int i = statement.executeUpdate(sql);
            System.out.println("受影响的行数是" + i);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public void update(Emp emp) {
        Connection connection = null;
        //设置事务是否自动提交,true表示自动提交,false表示不是自动提交
//        connection.setAutoCommit(true);
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            //拼接sql语句
            String sql = "update emp set job = 'SALES' where empno=" + emp.getEmpno();
            System.out.println(sql);
            //返回值表示受影响的行数
            int i = statement.executeUpdate(sql);
            System.out.println("受影响的行数是" + i);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public Emp getEmpByEmpno(Integer empno) {
        Connection connection = null;
        //设置事务是否自动提交,true表示自动提交,false表示不是自动提交
//        connection.setAutoCommit(true);
        Statement statement = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            //拼接sql语句
            String sql = "select * from emp where empno=" + empno;
            System.out.println(sql);
            //返回值表示受影响的行数
            resultSet = statement.executeQuery(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"), resultSet.getString("ename"),
                        resultSet.getString("job"), resultSet.getInt("mgr"), sdf.format(resultSet.getDate("hiredate")),
                        resultSet.getDouble("sal"), resultSet.getDouble("comm"), resultSet.getInt("deptno"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, statement, resultSet);
        }
        return emp;
    }

    @Override
    public Emp getEmpByEname(String ename) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            String sql = "select * from emp where ename=" + ename;
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"), resultSet.getString("ename"),
                        resultSet.getString("job"), resultSet.getInt("mgr"), sdf.format(resultSet.getDate("hiredate")),
                        resultSet.getDouble("sal"), resultSet.getDouble("comm"), resultSet.getInt("deptno"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, statement, resultSet);
        }
        return emp;
    }

    public static void main(String[] args) {
        EmpDao empDao = new EmpDaoImpl();
        Emp emp = new Emp(7777, "TOM", "SALESMAN", 7698, "2019-12-5", 1500.0, 500.0, 10);
//        empDao.insert(emp);
//        empDao.delete(emp);
//        empDao.update(emp);
//        Emp emp1 = empDao.getEmpByEmpno(7369);
        //sql注入
        Emp emp1 = empDao.getEmpByEname("'BLAKE' or 1=1");
        System.out.println(emp1);
    }
}
