package com.yangqihang.pool.HikariCP;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class HikariCPTest {
    public static void main(String[] args) throws Exception {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/demo?serverTimezone=UTC");
//        config.setUsername("root");
//        config.setPassword("123456");
//
//        HikariDataSource ds = new HikariDataSource(config);
//        Connection connection = ds.getConnection();
//        System.out.println(connection);
//        connection.close();

//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/demo?serverTimezone=UTC");
//        hikariDataSource.setUsername("root");
//        hikariDataSource.setPassword("123456");

        HikariConfig config = new HikariConfig("src/com/yangqihang/pool/HikariCP/hikariCP.properties");
        HikariDataSource hikariDataSource = new HikariDataSource(config);

        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
