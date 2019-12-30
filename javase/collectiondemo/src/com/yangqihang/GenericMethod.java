package com.yangqihang;

public class GenericMethod<E> {
    private E e;

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public <Q> void show(Q q) {
        System.out.println(q);
        System.out.println(e);
    }
}
