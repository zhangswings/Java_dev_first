package com.pdsu;

import java.util.*;

/**
 * 20个最佳的Java集合框架面试题目
 */
public class Main {

    public static void main(String[] args) {
        //为什么Map接口不继承Collection 接口？
        /**
         * · Set是无序集合，并且不允许重复的元素
         · List是有序的集合，并且允许重复的元素
         · 而Map是键值对
         · 它被视为是键的set和值的set的组合
         · Map被设计为键值对的集合，所以不需要继承Collection 接口
         */
        Set<String> sets = new HashSet<>();
        sets.add("a");
        sets.add("ab");
        sets.add("ac");
        sets.add("ads");
        int len = sets.size();
        System.out.println(sets.toString());
        //输入结果：[a, ab, ads, ac]

        List lists = new ArrayList<>();
        lists.add(1);
        lists.add(1);
        lists.add("swings");
        lists.add(new Object());
        System.out.println(lists.toString());
        //输入结果：[1, 1, swings, java.lang.Object@154617c]

        Map maps=new HashMap<>();
        maps.put("key","value");
        maps.put("a",1);
        maps.put("b","b_str");
        maps.put("c",new Object());
        System.out.println("maps.size() = "+maps.size());
        System.out.println(maps.toString());
        //输入结果： {a=1, b=b_str, c=java.lang.Object@a14482, key=value}
    }
}
