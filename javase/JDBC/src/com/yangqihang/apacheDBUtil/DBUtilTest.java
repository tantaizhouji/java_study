package com.yangqihang.apacheDBUtil;

import com.yangqihang.entity.Emp;
import com.yangqihang.util.MySQLDBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtilTest {

    public static Connection connection;

    public static void testQuery() throws SQLException {

        connection = MySQLDBUtil.getConnection();
        String sql = "select * from emp where empno = ?";
        QueryRunner queryRunner = new QueryRunner();
        Emp query = queryRunner.query(connection, sql, new BeanHandler<Emp>(Emp.class), 7369);
        System.out.println(query);
        connection.close();
    }

    public static void testList() throws SQLException {

        connection = MySQLDBUtil.getConnection();
        String sql = "select * from emp";
        QueryRunner queryRunner = new QueryRunner();
        List<Emp> query = queryRunner.query(connection, sql, new BeanListHandler<Emp>(Emp.class));
        for (Emp emp : query) {
            System.out.println(emp);
        }
        connection.close();
    }

    public static void main(String[] args) throws SQLException {
//        testQuery();
        testList();
    }
}
