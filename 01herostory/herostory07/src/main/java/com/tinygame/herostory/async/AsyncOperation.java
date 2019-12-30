package com.tinygame.herostory.async;

/**
 * 异步操作接口
 */
public interface AsyncOperation {

    /**
     * 获取绑定Id
     *
     * @return 绑定Id
     */
    default int bindId() {
        return 0;
    }

    /**
     * 执行异步操作
     */
    void doAsync();

    /**
     * 执行完成逻辑
     */
    default void doFinish() {
    }

}
