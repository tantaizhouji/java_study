package com.tinygame.herostory.cmdHandler;

import com.tinygame.herostory.msg.GameMsgProtocol;
import com.tinygame.herostory.rank.RankItem;
import com.tinygame.herostory.rank.RankService;
import io.netty.channel.ChannelHandlerContext;

import java.util.Collections;

/**
 * 获取排行榜指令处理器
 */
public class GetRankCmdHandler implements CmdHandler<GameMsgProtocol.GetRankCmd> {
    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.GetRankCmd cmd) {
        if (null == ctx || null == cmd) {
            return;
        }

        RankService.getInstance().getRank((rankItemList) -> {
            if (null == rankItemList) {
                rankItemList = Collections.emptyList();
            }

            GameMsgProtocol.GetRankResult.Builder resultBuilder = GameMsgProtocol.GetRankResult.newBuilder();

            for (RankItem rankItem : rankItemList) {
                GameMsgProtocol.GetRankResult.RankItem.Builder rankItemBuilder = GameMsgProtocol.GetRankResult.RankItem.newBuilder();

                rankItemBuilder.setRankId(rankItem.rankId);
                rankItemBuilder.setUserId(rankItem.userId);
                rankItemBuilder.setUserName(rankItem.userName);
                rankItemBuilder.setHeroAvatar(rankItem.heroAvatar);
                rankItemBuilder.setWin(rankItem.win);

                resultBuilder.addRankItem(rankItemBuilder);
            }

            GameMsgProtocol.GetRankResult newResult = resultBuilder.build();
            ctx.writeAndFlush(newResult);

            return  null;
        });
    }
}
