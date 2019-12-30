package com.yangqihang;

/*
* 总结:
*   try语句在返回前，将其他所有的操作执行完，保留好要返回的值，而后转入执行finally中的语句，而后分为以下三种情况：
*       情况一：如果finally中有return语句，则会将try中的return语句”覆盖“掉，直接执行finally中的return语句，得到返回值，这样便无法得到try之前保留好的返回值。
*       情况二：如果finally中没有return语句，也没有改变要返回值，则执行完finally中的语句后，会接着执行try中的return语句，返回之前保留的值。
*       情况三：如果finally中没有return语句，但是改变了要返回的值，这里有点类似与引用传递和值传递的区别，分以下两种情况：
*           1）如果return的数据是基本数据类型或文本字符串，则在finally中对该基本数据的改变不起作用，try中的return语句依然会返回进入finally块之前保留的值。
*           2）如果return的数据是引用数据类型，而在finally中对该引用数据类型的属性值的改变起作用，try中的return语句返回的就是在finally中改变后的该属性的值。
* */

public class TryTest {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println(test1());
        System.out.println("----------------------------");
        System.out.println(test2());
        System.out.println("----------------------------");
        System.out.println(test3());
        System.out.println("----------------------------");
        System.out.println(test4().num);
    }

    private static int test1() {
        int num = 10;
        try {
            System.out.println("try");
            return num += 80;
        } catch (Exception e) {
            System.out.println("error");
        } finally {
            if (num > 20) {
                System.out.println("num>20 : " + num);
            }
            System.out.println("finally");
        }
        return num;
    }

    private static int test2(){
        int num = 10;
        try{
            System.out.println("try");
            return num += 80;
        }catch(Exception e){
            System.out.println("error");
        }finally{
            if (num > 20){
                System.out.println("num>20 : " + num);
            }
            System.out.println("finally");
            num = 100;
            return num;
        }
    }

    private static int test3(){
        int num = 10;
        try{
            System.out.println("try");
            //返回num=10的操作,但是要在finall语句块执行完才能操作
            return num;
        }catch(Exception e){
            System.out.println("error");
        }finally{
            if (num > 20){
                System.out.println("num>20 : " + num);
            }
            System.out.println("finally");
            //没有返回num=100的操作,执行完之后还是返回num=10
            num = 100;
        }
        return num;
    }

    private static Num test4(){
        Num number = new Num();
        try{
            System.out.println("try");
            //返回的是引用数据类型的变量
            return number;
        }catch(Exception e){
            System.out.println("error");
        }finally{
            if (number.num > 20){
                System.out.println("number.num>20 : " + number.num);
            }
            System.out.println("finally");
            //更改了引用数据类型的变量,返回时引用数据没变,里面的变量变了也会在try中返回,与基本数据类型不同
            number.num = 100;
        }
        return number;
    }
}

class Num {
    public int num = 10;
}
