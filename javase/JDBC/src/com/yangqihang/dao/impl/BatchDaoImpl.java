package com.yangqihang.dao.impl;

import com.yangqihang.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchDaoImpl {
    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        insertBatch();
        System.out.println(System.currentTimeMillis() - start1);
        System.out.println("---------------------------------------");
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            insertBatch2(2000 + i, "msb" + i);
        }
        System.out.println(System.currentTimeMillis() - start2);
    }

    public static void insertBatch() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into emp(empno,ename) values(?,?)";
        //准备预处理对象
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {
                preparedStatement.setInt(1, i + 1000);
                preparedStatement.setString(2, "msb" + i);
                //向批处理中添加sql语句
                preparedStatement.addBatch();
            }
            int[] ints = preparedStatement.executeBatch();
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedStatement);
        }
    }

    public static void insertBatch2(Integer i, String name) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into emp(empno,ename) values(?,?)";
        //准备预处理对象
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, i);
            preparedStatement.setString(2, name + i);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedStatement);
        }
    }
}
