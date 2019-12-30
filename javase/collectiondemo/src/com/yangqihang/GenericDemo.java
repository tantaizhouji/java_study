package com.yangqihang;

import java.util.ArrayList;
import java.util.List;

/*
 * 当做一些集合的统一操作的时候,需要保证集合的类型是统一的,此时需要泛型来进行限制
 *      优点:
 *          1.数据安全
 *          2.获取数据时效率比较高
 *      给集合中的元素设置相同的类型就是泛型的基本需求
 *      使用：
 *          在定义对象的时候,通过<>中设置合理的类型来进行实现
 * 泛型的高阶应用:
 *      1.泛型类
 *          在定义类的时候在类名的后面添加<E>,起到占位的作用,类中的方法的返回值类型和参数属性的类型都可以使用
 *      2.泛型接口
 *          在定义接口的时候,在接口的名称后添加<E>
 *          1.子类在进行实现的时候,可以不填写泛型的类型,此时在创建具体的子类对象的时候才决定使用什么类型
 *          2.子类在实现泛型接口的时候,只在实现父类的接口的时候指定父类的泛型类型即可,此时,测试方法中的泛型类型必须要跟子类保持一致
 *      3.泛型方法
 *          在定义方法的时候,指定方法的返回值和参数是自定义的占位符,可以是类名中的E,也可以是自定义的Q,只不过在使用Q的时候性需要使用
 *          <>定义在返回值的前面
 *      4.泛型的上限
 *      5.泛型的下限
 * */
public class GenericDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");   //new Integer(1)
        list.add("abc");  //new String("abc")
        list.add("true");   //new Boolean(true)
        list.add(new Person("zhangsan", 12).toString());
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (String iter : list) {
            System.out.println(iter);
        }

        GenericClass<String> genericClass = new GenericClass<String>();
        genericClass.setA("abc");
        genericClass.setId(1);
        genericClass.show();

        GenericClass<Integer> genericClass1 = new GenericClass<Integer>();
        genericClass1.setA(32);
        genericClass1.setId(1);
        genericClass1.show();

        GenericClass<Person> genericClass2 = new GenericClass<Person>();
        genericClass2.setA(new Person("zhangli", 12));
        genericClass2.setId(1);
        genericClass2.show();

        GenericInterfaceSub genericInterfaceSub = new GenericInterfaceSub();
        genericInterfaceSub.test2("abc");

        GenericMethod<String> genericMethod = new GenericMethod<>();
        genericMethod.setE("aaa");
        genericMethod.show(122);
        genericMethod.show(true);
    }
}
