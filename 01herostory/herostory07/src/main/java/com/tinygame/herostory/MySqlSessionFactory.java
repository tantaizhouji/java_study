package com.tinygame.herostory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MySql会话工厂
 */
public final class MySqlSessionFactory {

    /**
     * MyBatis Sql会话工厂
     */
    private static SqlSessionFactory _sqlSessionFactory;

    /**
     * 私有化默认构造器
     */
    private MySqlSessionFactory() {
    }

    /**
     * 初始化
     */
    public static void init() {

        try {
            _sqlSessionFactory = (new SqlSessionFactoryBuilder()).build(Resources.getResourceAsStream("MyBatisConfig.xml"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 开启MySql会话
     *
     * @return MySql会话
     */
    public static SqlSession openSession() {
        if (null == _sqlSessionFactory) {
            throw new RuntimeException("_sqlSessionFactory尚未初始化");
        }

        return _sqlSessionFactory.openSession(true);
    }
}
