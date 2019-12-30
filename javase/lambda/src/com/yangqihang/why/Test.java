package com.yangqihang.why;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();
        list.add(new Student("zhangsan", 14, 67));
        list.add(new Student("lisi", 17, 88));
        list.add(new Student("wangwu", 10, 43));
        list.add(new Student("zhaoliu", 12, 55));
        //查找年龄大于14的学生
        findByAge(list);
        System.out.println("------------------");
        //查找分数大于60的学生
        findByScore(list);
    }

    public static void findByAge(ArrayList<Student> students) {
        ArrayList<Student> list = new ArrayList<Student>();
        for (Student stu : students) {
            if (stu.getAge() > 14) {
                list.add(stu);
            }
        }
        for (Student student : list) {
            System.out.println(student);
        }
    }

    public static void findByScore(ArrayList<Student> students) {
        ArrayList<Student> list = new ArrayList<Student>();
        for (Student stu : students) {
            if (stu.getScore() > 60) {
                list.add(stu);
            }
        }
        for (Student student : list) {
            System.out.println(student);
        }
    }
}
