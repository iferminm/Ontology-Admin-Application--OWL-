package com.admin.config;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * This class manages the properties file
 * 
 * @author israelord
 */
public class ConfigManager {

	private static ConfigManager instance = new ConfigManager();
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	private ResourceBundle getResource() {
		ResourceBundle resource = ResourceBundle.getBundle("com.admin.config.ServerConfig.properties");
		return resource;
	}
	
	public String getProperty(String key) {
		return this.getResource().getString(key);
	}
	
	public HashMap<String, String> getAllProperties() {
		ResourceBundle resource = this.getResource();
		Enumeration<String> keys = resource.getKeys();
		HashMap<String, String> result = new HashMap<String, String>();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = resource.getString(key);
			result.put(key, value);
		}
		return result;
	}
}