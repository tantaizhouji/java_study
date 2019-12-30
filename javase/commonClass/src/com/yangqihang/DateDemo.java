package com.yangqihang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDemo {

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //将Date类按照规范转换成字符串格式
        String str = dateFormat.format(date);
        System.out.println(str);
        //将字符串转换成对应的日期类
        Date d1 = dateFormat.parse("2019-11-24");
        System.out.println(d1);

        //获取的是当前系统的时间
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        //设定指定时间的日历类
        calendar.setTime(d1);
        System.out.println(calendar);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    }
}
