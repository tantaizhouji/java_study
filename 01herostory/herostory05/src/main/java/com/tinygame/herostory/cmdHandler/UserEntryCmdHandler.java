package com.tinygame.herostory.cmdHandler;

import com.tinygame.herostory.Broadcaster;
import com.tinygame.herostory.model.User;
import com.tinygame.herostory.model.UserManager;
import com.tinygame.herostory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class UserEntryCmdHandler implements CmdHandler<GameMsgProtocol.UserEntryCmd> {

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserEntryCmd msg) {
        //从指令对象中获取用户ID和英雄形象
        GameMsgProtocol.UserEntryCmd cmd = msg;
        int userId = cmd.getUserId();
        String heroAvatar = cmd.getHeroAvatar();

        GameMsgProtocol.UserEntryResult.Builder resultBuilder = GameMsgProtocol.UserEntryResult.newBuilder();
        resultBuilder.setUserId(userId);
        resultBuilder.setHeroAvatar(heroAvatar);

        //新建用户
        User newUser = new User();
        newUser.userId = userId;
        newUser.heroAvatar = heroAvatar;
        newUser.currHp = 100;
        //并将用户加入管理器
        UserManager.addUser(newUser);

        //将用户ID附着到Channel
        ctx.channel().attr(AttributeKey.valueOf("userId")).set(userId);

        //构建结果并发送
        GameMsgProtocol.UserEntryResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);
    }
}
