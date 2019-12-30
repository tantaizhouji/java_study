package com.yangqihang.why3;

import com.yangqihang.why.Student;
import com.yangqihang.why2.StudentFilter;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();
        list.add(new Student("zhangsan", 14, 67));
        list.add(new Student("lisi", 17, 88));
        list.add(new Student("wangwu", 10, 43));
        list.add(new Student("zhaoliu", 12, 55));

        getByFilter(list, new StudentFilter() {
            @Override
            public boolean compare(Student student) {
                return student.getAge() > 14;
            }
        });
        System.out.println("---------------------------");

        getByFilter(list, new StudentFilter() {
            @Override
            public boolean compare(Student student) {
                return student.getScore() > 60;
            }
        });
        System.out.println("---------------------------");

        getByFilter(list, new StudentFilter() {
            @Override
            public boolean compare(Student student) {
                return student.getName().length() > 5;
            }
        });
    }

    public static void getByFilter(ArrayList<Student> students, StudentFilter filter) {
        ArrayList<Student> list = new ArrayList<Student>();
        for (Student student : students) {
            if (filter.compare(student)) {
                list.add(student);
            }
        }
        printStudent(list);
    }

    public static void printStudent(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
