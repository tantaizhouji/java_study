package com.tinygame.herostory.cmdHandler;

import com.tinygame.herostory.Broadcaster;
import com.tinygame.herostory.model.MoveState;
import com.tinygame.herostory.model.User;
import com.tinygame.herostory.model.UserManager;
import com.tinygame.herostory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class UserMoveToCmdHandler implements CmdHandler<GameMsgProtocol.UserMoveToCmd> {

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserMoveToCmd msg) {
        if (null == ctx || null == msg) {
            return;
        }

        //获取用户Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (null == userId) {
            return;
        }

        //获取移动的用户
        User moveUser = UserManager.getUserById(userId);
        if (null == moveUser) {
            return;
        }

        //获取移动状态
        MoveState mvState = moveUser.moveState;
        //设置位置和时间
        mvState.fromPosX = msg.getMoveFromPosX();
        mvState.fromPosY = msg.getMoveFromPosY();
        mvState.toPosX = msg.getMoveToPosX();
        mvState.toPosY = msg.getMoveToPosY();
        mvState.startTime = System.currentTimeMillis();

        GameMsgProtocol.UserMoveToResult.Builder resultBuilder = GameMsgProtocol.UserMoveToResult.newBuilder();
        resultBuilder.setMoveUserId(userId);
        resultBuilder.setMoveFromPosX(mvState.fromPosX);
        resultBuilder.setMoveFromPosY(mvState.fromPosY);
        resultBuilder.setMoveToPosX(mvState.toPosX);
        resultBuilder.setMoveToPosY(mvState.toPosY);
        resultBuilder.setMoveStartTime(mvState.startTime);

        GameMsgProtocol.UserMoveToResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);
    }
}
