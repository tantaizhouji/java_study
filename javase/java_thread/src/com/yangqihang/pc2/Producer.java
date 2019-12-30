package com.yangqihang.pc2;

//生产产品,将产品放置到共享空间中
public class Producer implements Runnable {
    private Goods goods;

    public Producer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                goods.set("娃哈哈", "矿泉水");
            } else {
                goods.set("旺仔", "小馒头");
            }
        }
    }
}
