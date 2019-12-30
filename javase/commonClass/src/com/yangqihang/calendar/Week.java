package com.yangqihang.calendar;

public enum Week {
    日, 一, 二, 三, 四, 五, 六;

    public static void show() {
        Week[] week = Week.values();
        for (int i = 0; i < week.length; i++) {
            System.out.print(week[i]);
            if (week[i] == Week.六) {
                System.out.println();
            } else {
                System.out.print("\t");
            }
        }
    }
}
