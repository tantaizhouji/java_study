package com.tinygame.herostory.cmdHandler;

import com.tinygame.herostory.model.MoveState;
import com.tinygame.herostory.model.User;
import com.tinygame.herostory.model.UserManager;
import com.tinygame.herostory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;

public class WhoElseIsHereCmdHandler implements CmdHandler<GameMsgProtocol.WhoElseIsHereCmd> {

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.WhoElseIsHereCmd cmd) {
        if (null == ctx || null == cmd) {
            return;
        }

        GameMsgProtocol.WhoElseIsHereResult.Builder resultBuilder = GameMsgProtocol.WhoElseIsHereResult.newBuilder();

        for (User currUser : UserManager.listUser()) {
            if (null == currUser) {
                continue;
            }

            //在这里构建每一个用户的信息
            GameMsgProtocol.WhoElseIsHereResult.UserInfo.Builder userInfoBuilder = GameMsgProtocol.WhoElseIsHereResult.UserInfo.newBuilder();
            userInfoBuilder.setUserId(currUser.userId);
            userInfoBuilder.setHeroAvatar(currUser.heroAvatar);

            //获取移动状态
            MoveState mvState = currUser.moveState;
            GameMsgProtocol.WhoElseIsHereResult.UserInfo.MoveState.Builder
                    mvStateBuilder = GameMsgProtocol.WhoElseIsHereResult.UserInfo.MoveState.newBuilder();
            mvStateBuilder.setFromPosX(mvState.fromPosX);
            mvStateBuilder.setFromPosY(mvState.fromPosY);
            mvStateBuilder.setToPosX(mvState.toPosX);
            mvStateBuilder.setToPosY(mvState.toPosY);
            mvStateBuilder.setStartTime(mvState.startTime);
            //将移动状态设置到用户信息
            userInfoBuilder.setMoveState(mvStateBuilder);

            //将用户信息添加到结果消息中
            resultBuilder.addUserInfo(userInfoBuilder);
        }

        GameMsgProtocol.WhoElseIsHereResult newResult = resultBuilder.build();
        ctx.writeAndFlush(newResult);
    }
}
