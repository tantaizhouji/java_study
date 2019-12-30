package com.yangqihang;

public class Point {

    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void length(Point p) {
        double length;
        length = Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
        System.out.println(length);
    }

    public static void main(String[] args) {
        Point p1 = new Point(3,3);
        Point p2 = new Point(0,0);

        p1.length(p2);
    }
}
