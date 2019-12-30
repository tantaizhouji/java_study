package com.tinygame.herostory.login;

import com.alibaba.fastjson.JSONObject;
import com.tinygame.herostory.MySqlSessionFactory;
import com.tinygame.herostory.async.AsyncOperation;
import com.tinygame.herostory.async.AsyncOperationProcessor;
import com.tinygame.herostory.login.db.UserDao;
import com.tinygame.herostory.login.db.UserEntity;
import com.tinygame.herostory.util.RedisUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.function.Function;

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
     * @param callback 回调函数
     * @return 用户实体
     */
    public void userLogin(String userName, String password, Function<UserEntity, Void> callback) {
        if (null == userName || null == password) {
            return;
        }

        AsyncOperation asyncOp = new AsyncGetUserByName(userName, password) {

            @Override
            public void doFinish() {
                if (null != callback) {
                    callback.apply(this.getUserEntity());
                }
            }
        };

        AsyncOperationProcessor.getInstance().process(asyncOp);
    }

    /**
     * 更新Redis中的用户基本信息
     *
     * @param userEntity 用户实体
     */
    private void updateUserBasicInfoInRedis(UserEntity userEntity) {
        if (null == userEntity) {
            return;
        }

        try (Jedis redis = RedisUtil.getRedis()) {
            //获取用户Id
            int userId = userEntity.userId;

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("userId", userId);
            jsonObj.put("userName", userEntity.userName);
            jsonObj.put("heroAvatar", userEntity.heroAvatar);

            //更新Redis数据
            redis.hset("User_" + userId, "BasicInfo", jsonObj.toJSONString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 异步方式获取用户
     */
    private class AsyncGetUserByName implements AsyncOperation {

        /**
         * 用户名称
         */
        private final String userName;

        /**
         * 密码
         */
        private final String password;

        /**
         * 用户实体
         */
        private UserEntity userEntity = null;

        /**
         * 类参数构造器
         *
         * @param userName
         * @param password
         */
        AsyncGetUserByName(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        /**
         * 获取用户实体
         *
         * @return 用户实体
         */
        public UserEntity getUserEntity() {
            return userEntity;
        }

        @Override
        public int bindId() {
            return userName.charAt(userName.length() - 1);
        }

        @Override
        public void doAsync() {
            try (SqlSession mySqlSessiion = MySqlSessionFactory.openSession()) {

                //获取DAO
                UserDao dao = mySqlSessiion.getMapper(UserDao.class);

                //看看当前线程
                LOGGER.info("当前线程 = {}", Thread.currentThread().getName());

                //根据用户名称获取用户实体
                UserEntity userEntity = dao.getUserByName(userName);

                if (null != userEntity) {
                    //判断用户密码
                    if (!password.equals(userEntity.password)) {
                        //用户密码错误
                        LOGGER.error("用户密码错误, userName = {}", userName);
                    }
                } else {
                    //如果用户实体为空,则新建用户
                    userEntity = new UserEntity();
                    userEntity.userName = userName;
                    userEntity.password = password;
                    userEntity.heroAvatar = "Hero_Shaman";

                    //将用户实体插入到数据库
                    dao.insertInto(userEntity);
                }

                this.userEntity = userEntity;

                LoginService.getInstance().updateUserBasicInfoInRedis(userEntity);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
