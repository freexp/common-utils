package com.commonutis.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class BeanMapUtils {

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T pojo = mapper.convertValue(map, clazz);
        return pojo;
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> pojo = mapper.convertValue(bean, Map.class);
        return pojo;
    }

}
