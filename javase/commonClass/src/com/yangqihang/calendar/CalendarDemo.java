package com.yangqihang.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {

    private String str;

    private int year;
    private int month;
    private int day;

    //创建该类需要接收个显示日期的字符串
    public CalendarDemo(String str) throws ParseException {
        this.str = str;

        //创建日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //将字符串转换成Date类
        Date date = dateFormat.parse(this.str);
        Calendar calendar = Calendar.getInstance();
        //设置时间
        calendar.setTime(date);

        //获取年份
        this.year = calendar.get(Calendar.YEAR);
        //获取月份(月份从0开始故要+1)
        this.month = calendar.get(Calendar.MONTH) + 1;
        //获取日期
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void display() {
        display1();
        display2();
    }

    public void display1() {
        System.out.println(year + "年" + month + "月");
    }

    public void display2() {
        Week.show();
    }
}
