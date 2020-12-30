package com.tank.util;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置属性加载
 */
public class PropertiesMgr {
    static Properties properties = new Properties();
    static {
        try {
            properties.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getIntValue(String key){
        if(properties == null){
            return 0;
        }
        String val = properties.getProperty(key);
        return StringUtils.isEmpty(val) ? 0 : Integer.valueOf(val);
    }
    public static String getStringValue(String key){
        if(properties == null){
            return null;
        }
        String val = properties.getProperty(key);
        return StringUtils.isEmpty(val) ? "" : val;
    }
}
