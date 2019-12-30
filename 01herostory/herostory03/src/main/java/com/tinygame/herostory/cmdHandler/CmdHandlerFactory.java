package com.tinygame.herostory.cmdHandler;

import com.google.protobuf.GeneratedMessageV3;
import com.tinygame.herostory.msg.GameMsgProtocol;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令处理工厂
 */
public final class CmdHandlerFactory {

    /**
     * 处理器字典
     */
    private static Map<Class<?>, CmdHandler<? extends GeneratedMessageV3>> _handlerMap = new HashMap<>();

    /**
     * 私有化类默认构造器
     */
    private CmdHandlerFactory() {

    }

    public static void init() {
        _handlerMap.put(GameMsgProtocol.UserEntryCmd.class, new UserEntryCmdHandler());
        _handlerMap.put(GameMsgProtocol.WhoElseIsHereCmd.class, new WhoElseIsHereCmdHandler());
        _handlerMap.put(GameMsgProtocol.UserMoveToCmd.class, new UserMoveToCmdHandler());
    }

    public static CmdHandler<? extends GeneratedMessageV3> create(Class<?> msgClazz) {
        if (null == msgClazz) {
            return null;
        }

        return _handlerMap.get(msgClazz);
    }
}
