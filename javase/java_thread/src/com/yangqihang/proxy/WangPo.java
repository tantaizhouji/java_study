package com.yangqihang.proxy;

//代理人
public class WangPo implements KindWomen{

    private KindWomen kindWomen;

    public WangPo(KindWomen kindWomen) {
        this.kindWomen = kindWomen;
    }

    @Override
    public void makeEyesWithMen() {
        this.kindWomen.makeEyesWithMen();
    }

    @Override
    public void playWithMen() {
        this.kindWomen.playWithMen();
    }
}
