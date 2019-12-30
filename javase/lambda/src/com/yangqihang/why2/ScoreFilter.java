package com.yangqihang.why2;

import com.yangqihang.why.Student;

public class ScoreFilter implements StudentFilter {
    @Override
    public boolean compare(Student student) {
        return student.getScore() > 60;
    }
}
