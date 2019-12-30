package com.yangqihang.calendar;

import java.text.ParseException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws ParseException {

//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
        String str = "2017-10-3";
        CalendarDemo calendarDemo = new CalendarDemo(str);

        calendarDemo.display();
    }
}
