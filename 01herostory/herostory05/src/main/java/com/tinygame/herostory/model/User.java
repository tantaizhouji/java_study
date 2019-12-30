package com.tinygame.herostory.model;

public class User {
    public int userId;
    public String heroAvatar;
    public int currHp;

    /**
     * 移动状态
     */
    public final MoveState moveState = new MoveState(); //创建的实例不能被改变,里面的值可以改,相当于人不变,但是身高体重会变化
}
