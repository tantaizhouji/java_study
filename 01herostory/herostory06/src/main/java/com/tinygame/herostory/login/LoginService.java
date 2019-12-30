package com.tinygame.herostory.login;

import com.tinygame.herostory.MySqlSessionFactory;
import com.tinygame.herostory.login.db.UserDao;
import com.tinygame.herostory.login.db.UserEntity;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoginService {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    /**
     * 单例对象
     */
    private static final LoginService _instance = new LoginService();

    /**
     * 私有化类默认构造器
     */
    private LoginService() {
    }

    /**
     * 获取单例对象
     *
     * @return 单例对象
     */
    public static LoginService getInstance() {
        return _instance;
    }

    /**
     * 用户登录
     *
     * @param userName 用户名称
     * @param password 用户密码
     * @return 用户实体
     */
    public UserEntity userLogin(String userName, String password) {
        if (null == userName || null == password) {
            return null;
        }

        LOGGER.info("当前线程 = {}", Thread.currentThread().getName());
        try (SqlSession mySqlSessiion = MySqlSessionFactory.openSession()) {

            //获取DAO
            UserDao dao = mySqlSessiion.getMapper(UserDao.class);
            //获取用户实体
            UserEntity userEntity = dao.getUserByName(userName);

            if (null != userEntity) {
                if (!password.equals(userEntity.password)) {
                    LOGGER.error("用户密码错误, userName = {}", userName);
                    throw new RuntimeException("用户密码错误");
                }
            } else {
                userEntity = new UserEntity();
                userEntity.userName = userName;
                userEntity.password = password;
                userEntity.heroAvatar = "Hero_Shaman";

                //将用户实体插入到数据库
                dao.insertInto(userEntity);

            }

            return userEntity;
        } catch (
                Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
