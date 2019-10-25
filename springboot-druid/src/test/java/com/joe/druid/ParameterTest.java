package com.joe.druid;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ParameterTest<T> {

    public static void main(String[] args) {
        Method method = null;
        try {
            //这里的第二个参数，和getRawType()意义类似
            method = new ParameterTest().getClass().getMethod("test", HashMap.class);
            Class<?> clsTemp = new ParameterTest().getClass();
            Type type = clsTemp.getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                Class<?> cls = (Class<?>) types[0];
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Type[] types = method.getGenericParameterTypes();
        ParameterizedType ptype = (ParameterizedType) types[0];
        Type rawType = ptype.getRawType();
        System.out.println("最外层<>前面那个类型 rawType："+rawType);
        Type type = ptype.getActualTypeArguments()[0];
        Type type1 = ptype.getActualTypeArguments()[1];
        System.out.println("泛型 type："+type);
        System.out.println("泛型 type1："+type1);
        Type ownerType = ptype.getOwnerType();
        System.out.println("ownerType："+ownerType);
        //type是Type类型，但直接输出的不是具体Type的五种子类型，
        //而是这五种子类型以及WildcardType具体表现形式
        System.out.println("泛型 type name："+type.getClass().getName());
    }
    public void test(HashMap<String,Integer> a){
    }
}
