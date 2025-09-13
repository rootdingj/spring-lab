package com.dj.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
 * @ClassName PropertiesUtil  
 * @Description properties文件加载类 
 * @author dingj 
 * @date 2018年7月20日  
 *   
 */
public class PropertiesUtil {
	private static final String PROP_FILE = "config.properties";
	private static Properties properties;
	static {
		properties = new Properties();
		try {
			InputStream is = 
					Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_FILE);
			assert (is != null);
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("读取" + PROP_FILE + "配置文件异常", e);
		}
	}
	
	public static String getValue(String key) {
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getValue("readFilePath"));
	}
	
}
