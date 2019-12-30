package com.yangqihang.why2;

import com.yangqihang.why.Student;

public class AgeFilter implements StudentFilter {
    @Override
    public boolean compare(Student student) {
        return student.getAge() > 14;
    }
}
