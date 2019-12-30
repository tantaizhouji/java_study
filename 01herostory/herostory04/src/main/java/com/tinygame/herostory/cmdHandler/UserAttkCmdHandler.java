package com.tinygame.herostory.cmdHandler;

import com.tinygame.herostory.Broadcaster;
import com.tinygame.herostory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class UserAttkCmdHandler implements CmdHandler<GameMsgProtocol.UserAttkCmd> {

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserAttkCmd msg) {
        if (null == ctx || null == msg) {
            return;
        }

        //获取攻击者Id
        Integer attkUserId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (null == attkUserId) {
            return;
        }

        //获取被攻击者Id
        int targetUserId = msg.getTargetUserId();

        GameMsgProtocol.UserAttkResult.Builder resultBuilder = GameMsgProtocol.UserAttkResult.newBuilder();
        resultBuilder.setAttkUserId(attkUserId);
        resultBuilder.setTargetUserId(targetUserId);

        GameMsgProtocol.UserAttkResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);

        GameMsgProtocol.UserSubtractHpResult.Builder resultBuilder2 = GameMsgProtocol.UserSubtractHpResult.newBuilder();
        resultBuilder2.setTargetUserId(targetUserId);
        resultBuilder2.setSubtractHp(10);

        GameMsgProtocol.UserSubtractHpResult newResult2 = resultBuilder2.build();
        Broadcaster.broadcast(newResult2);
    }


}
