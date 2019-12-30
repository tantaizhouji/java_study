package com.yangqihang.innerdemo;

/*
 * 内部类(当做类中的一个普通成员变量,只不过此成员变量是class的类型):
 *       一个Java文件中可以包含多个class,但是只能有一个public class
 *       如果一个类定义在另一个类的内部，此时可以称这个类为内部类
 *   使用:
 *       创建内部类的时候,跟之前的方法不一样,需要在内部类的前面添加外部类来进行修饰
 *       InnerClassDemo.InnerClass innerClass = new InnerClassDemo().new InnerClass();
 *   特点:
 *       1.内部类可以方便的直接访问外部类的私有属性
 *       2.外部类不能直接访问内部类的私有属性,但是如果创建了内部类的对象,此时可以在外部类中访问私有属性
 *       3.内部类中不能定义静态属性
 *       4.当内部类和外部类具有相同的私有属性的时候,在内部类中访问的时候,可以直接访问内部类的属性,如果需要
 *         访问外部类的属性,那么需要添加 外部类类名.this.属性
 *   分类:
 *       1.匿名内部类:当定义一个类,实现了某个接口的时候,在使用过程中只需要使用一次,没有其它用途，考虑到代码编写
 *         的简洁,可以考虑不创建具体的类,而是采用new interface() {添加未实现的方法}就叫做匿名内部类
 *       2.静态内部类:在内部类中可以定义静态内部类,使用static关键字来修饰,使用规则
 *                  外部类.内部类 类的引用名称 = new 外部类.内部类();
 *       3.方法内部类:在外部类的方法中也可以定义类,此时叫做方法内部类(了解即可)
 *         使用的时候需要注意,只能在方法中创建对象,因为此class的作用域就是当前方法
 * */

public class TestInnerDemo {
    public static void main(String[] args) {
        InnerClassDemo innerClassDemo = new InnerClassDemo();
        innerClassDemo.show();
        System.out.println(innerClassDemo.getName());

        InnerClassDemo.InnerClass innerClass = new InnerClassDemo().new InnerClass();
        innerClass.test();
    }
}
