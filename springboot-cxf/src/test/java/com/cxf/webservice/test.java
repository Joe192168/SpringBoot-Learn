package com.cxf.webservice;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) {
        String str = "1";
        String[] values = "1".split(",");
        List<String> _list = Arrays.asList(values);
        System.out.println(_list.contains("1"));


        BigDecimal a = new BigDecimal("null").setScale(2,BigDecimal.ROUND_HALF_DOWN);

        BigDecimal b = new BigDecimal(2).setScale(2,BigDecimal.ROUND_HALF_DOWN);

        System.out.println(a.equals(b));
    }

}
