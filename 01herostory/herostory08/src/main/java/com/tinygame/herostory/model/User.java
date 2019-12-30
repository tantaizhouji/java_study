package com.tinygame.herostory.model;

public class User {

    /**
     * 用户Id
     */
    public int userId;

    /**
     * 用户名称
     */
    public String userName;

    /**
     * 影响形象
     */
    public String heroAvatar;

    /**
     * 当前血量
     */
    public int currHp;

    /**
     * 是否已经死亡
     */
    public boolean died;

    /**
     * 移动状态
     */
    public final MoveState moveState = new MoveState(); //创建的实例不能被改变,里面的值可以改,相当于人不变,但是身高体重会变化
}
