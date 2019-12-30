package com.yangqihang.map;

import java.util.*;

/*
 * Map存储的是K-V键值对映射的数据
 *       实现子类:
 *           HashMap:数据+链表(1.7) 数组+链表+红黑树(1.8)
 *           LinkedHashMap:链表
 *           TreeMap:红黑树
 *       基本API操作:
 *          增加:
 *              put(K-V)    添加元素
 *          查询:
 *              isEmpty()   判断是否为空
 *              size()      判断Map的大小
 *              containsKey()
 *              containsValue()
 *              get()
 *          删除:
 *              clear()  清空集合的所有元素
 *              remove()    删除指定元素
 *          Map.entry:表示的是K-V组合的一组映射关系,Key和Value成组出现
 * HashMap和HashTable的区别:
 *      1.HashMap线程不安全,效率高,HashTable线程安全,效率低
 *      2.HashMap中的Key和Value都可以为空,HashTable都不允许为空
 * HashMap初始值为2的n次幂原因:
 *      1.方便进行&操作,提高效率,&运算要比取模运算效率高
 *      2.在扩容之后涉及到元素的迁移过程,迁移的时候只需要判断二进制的前一位是0或者是1即可
 *        如果是0,表示新数组和旧数组的下标位置不变,如果是1,只需要将索引位置加上旧的数组的长度值即为新数组的下标
 * */

public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        System.out.println(map);
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println(map.containsKey("a"));
        System.out.println(map.containsValue(2));
        System.out.println(map.get("b"));
        map.remove("a");
        System.out.println(map);
        System.out.println("----------------------");

        //遍历操作
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + " = " + map.get(key));
        }

        //只能获取对应的Value值,不能根据Value来获取Key
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println(value);
        }

        //迭代器
        Set<String> keys2 = map.keySet();
        Iterator<String> iterator = keys2.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + "=" + map.get(key));
        }

        //Map.entry
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator1 = entries.iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, Integer> next = iterator1.next();
            System.out.println(next.getKey() + "--" + next.getValue());
        }
    }
}
