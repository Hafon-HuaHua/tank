package com.tank.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置属性加载
 */
public class PropertiesMgr {

    private PropertiesMgr(){}
    /**
     * 静态内部类使用单例实现LazyLoad
     */
    private static class PropInnerClass{
        private static final Properties properties = new Properties();
        static {
            try {
                properties.load(PropInnerClass.class.getClassLoader().getResourceAsStream("config/config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static int getIntVal(String key){
        String val = checkVal(key);
        if(val == null){
            return 0;
        }
        return Integer.valueOf(val);
    }
    public static String getStringVal(String key){
        return checkVal(key);
    }
    private static String checkVal(String key){
        Properties prop = PropInnerClass.properties;
        if(prop.isEmpty()){
            return null;
        }
        return prop.getProperty(key);
    }
}
