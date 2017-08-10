package com.jidian001.utils;

import java.util.HashMap;
import java.util.Map;


public class BeanMapUtilsTest {
    void mapToBean() {
        Map map = new HashMap();
        map.put("name", "name");
        map.put("age", 18);
        map.put("gender", "1");
        map.put("assest", "123456.78");

        Bean bean = BeanMapUtils.mapToBean(map, Bean.class);
        System.out.println(bean);
    }

    void beanToMap() {
        Bean bean = new Bean();
        bean.setName("name1");
        bean.setAge(21);
        bean.setGender('2');
        bean.setAssest(123456.78);

        Map<String, ?> map = BeanMapUtils.beanToMap(bean);
        System.out.println(map);
    }

    public static void main(String[] args) {
        BeanMapUtilsTest beanMapUtilsTest = new BeanMapUtilsTest();
        beanMapUtilsTest.mapToBean();
        beanMapUtilsTest.beanToMap();

        Bean bean = new Bean();
        bean.setName("name1");
        bean.setAge(21);
        bean.setGender('2');
        bean.setAssest(123456.78);

        Map map = BeanMapUtils.beanToMap(bean);
        Bean1 bean1 = BeanMapUtils.mapToBean(map, Bean1.class);
        System.out.println(bean.toString());
        System.out.println(bean1.toString());
    }

}
