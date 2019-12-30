package com.tinygame.herostory.cmdHandler;

import com.google.protobuf.GeneratedMessageV3;
import com.tinygame.herostory.util.PackageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 指令处理工厂
 */
public final class CmdHandlerFactory {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CmdHandlerFactory.class);

    /**
     * 处理器字典
     */
    private static final Map<Class<?>, CmdHandler<? extends GeneratedMessageV3>> _handlerMap = new HashMap<>();

    /**
     * 私有化类默认构造器
     */
    private CmdHandlerFactory() {
    }

    public static void init() {
        LOGGER.info("==== 完成 Cmd 和 Handler 的关联 ====");

        //获取包名称
        final String packageName = CmdHandlerFactory.class.getPackage().getName();
        //获取所有CmdHandler子类
        Set<Class<?>> clazzSet = PackageUtil.listSubClazz(packageName, true, CmdHandler.class);

        for (Class<?> clazz : clazzSet) {

            //判断是否是抽象类,是就跳出当前这个类的循环
            if ((clazz.getModifiers() & Modifier.ABSTRACT) != 0) {
                continue;
            }

            //获取方法数组
            Method[] methodArray = clazz.getDeclaredMethods();

            //消息类型
            Class<?> msgType = null;

            for (Method currMethod : methodArray) {

                //判断当前方法是否是handle方法,不是跳出该方法循环
                if (!currMethod.getName().equals("handle")) {
                    continue;
                }

                //获取函数参数类型
                Class<?>[] parameterTypeArray = currMethod.getParameterTypes();

                if (parameterTypeArray.length < 2 ||
                        parameterTypeArray[1] == GeneratedMessageV3.class ||
                        !GeneratedMessageV3.class.isAssignableFrom(parameterTypeArray[1])) {
                    continue;
                }

                msgType = parameterTypeArray[1];
                break;
            }

            if (null == msgType) {
                continue;
            }

            try {
                CmdHandler<?> newHandler = (CmdHandler<?>) clazz.newInstance();

                LOGGER.info("关联 {} <==> {}", msgType.getName(), clazz.getName());

                _handlerMap.put(msgType, newHandler);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public static CmdHandler<? extends GeneratedMessageV3> create(Class<?> msgClazz) {
        if (null == msgClazz) {
            return null;
        }

        return _handlerMap.get(msgClazz);
    }
}
