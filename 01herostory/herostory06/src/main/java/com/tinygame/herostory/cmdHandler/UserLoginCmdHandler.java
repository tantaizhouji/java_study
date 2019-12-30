package com.tinygame.herostory.cmdHandler;

import com.tinygame.herostory.login.LoginService;
import com.tinygame.herostory.login.db.UserEntity;
import com.tinygame.herostory.model.User;
import com.tinygame.herostory.model.UserManager;
import com.tinygame.herostory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户登录指令处理器
 */
public class UserLoginCmdHandler implements CmdHandler<GameMsgProtocol.UserLoginCmd> {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginCmdHandler.class);

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserLoginCmd cmd) {
        LOGGER.info(
                "userName = {}, password = {}",
                cmd.getUserName(),
                cmd.getPassword()
        );

        UserEntity userEntity = null;
        try {
            userEntity = LoginService.getInstance().userLogin(cmd.getUserName(), cmd.getPassword());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (null == userEntity) {
            LOGGER.error("用户登录失败, userName = {}", cmd.getUserName());
            return;
        }

        //新建用户
        User newUser = new User();
        newUser.userId = userEntity.userId;
        newUser.userName = userEntity.userName;
        newUser.heroAvatar = userEntity.heroAvatar;
        newUser.currHp = 100;
        //并将用户加入管理器
        UserManager.addUser(newUser);

        //将用户ID附着到Channel
        ctx.channel().attr(AttributeKey.valueOf("userId")).set(userEntity.userId);

        GameMsgProtocol.UserLoginResult.Builder resultBuilder = GameMsgProtocol.UserLoginResult.newBuilder();
        resultBuilder.setUserId(newUser.userId);
        resultBuilder.setUserName(newUser.userName);
        resultBuilder.setHeroAvatar(newUser.heroAvatar);

        //构建结果并发送
        GameMsgProtocol.UserLoginResult newResult = resultBuilder.build();
        ctx.writeAndFlush(newResult);
    }
}
