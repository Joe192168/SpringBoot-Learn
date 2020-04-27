package com.joe.oauth.algorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: springboot-learn
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-04-27 14:46
 **/
public class ArrayListTest {

    public static void main(String[] args) {
        List<Map<String, String>> list = new ArrayList<>(16) ;
        Map<String,String> map1 = new HashMap<>(16);
        map1.put("key1", "val1");
        Map<String,String> map2 = new HashMap<>(16);
        map2.put("key1", "val1");
        list.add(map1);
        list.add(map2);
        //打印list里面的数据
        list.forEach(System.out::println);

        ArrayList<Map<String, String>> data = list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(m -> m.get("key1")))), ArrayList::new));
        System.out.println("=======去重前后分界线=======");
        //打印过滤后的数据
        data.forEach(System.out::println);
    }

}
