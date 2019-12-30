package com.yangqihang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("ad");
        list.add("bcssas");
        list.add("ews");
        list.add("dzzz");

        Collections.addAll(list, "e", "fdwqdsxq", "gdsdd");
        System.out.println(list);

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(list);

        //二分查找的时候需要先进行排序操作,如果没有排序的话,是找不到指定元素的
        int i = Collections.binarySearch(list, "ad");
        System.out.println(list.get(i));

        Collections.fill(list, "yangqihang");
        System.out.println(list);
    }
}
