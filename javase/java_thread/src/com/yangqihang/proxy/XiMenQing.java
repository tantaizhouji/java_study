package com.yangqihang.proxy;

public class XiMenQing {
    public static void main(String[] args) {
        PanJinLian panJinLian = new PanJinLian();
        WangPo wangPo = new WangPo(panJinLian);
        wangPo.makeEyesWithMen();
        wangPo.playWithMen();
    }
}
