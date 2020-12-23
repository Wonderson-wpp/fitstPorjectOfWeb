package com.peng.bookstore.utils;

import java.util.Map;

public  class BeanUtils {
    public static  <T>  T getBean(Class<T> clazz, Map map) {
        try {
            T t = clazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(t, map);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
