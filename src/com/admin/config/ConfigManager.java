package com.admin.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class manages the properties file
 * 
 * @author israelord
 */
public class ConfigManager {

	private static ConfigManager instance = new ConfigManager();
	private final String fileName = "ServerConfig.properties";
	private Properties properties;
	private String defaultValue = "not set";

	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	private Properties generateProperties() {
		properties = new Properties();
		properties.setProperty("ModelsPath", defaultValue);
		properties.setProperty("BaseModelName", defaultValue);
		properties.setProperty("ReasonedModelName", defaultValue);
		properties.setProperty("TestModelName", defaultValue);
		properties.setProperty("VirtuosoConnection", defaultValue);
		properties.setProperty("VirtuosoUser", defaultValue);
		properties.setProperty("VirtuosoPassword", defaultValue);
		properties.setProperty("GrapthName", defaultValue);
		properties.setProperty("GraphLabel", defaultValue);
		properties.setProperty("TestGraphName", defaultValue);
		properties.setProperty("OntologyHistoryDirectory", defaultValue);
		
		return properties;
	}
	
	private void generateFile(String name) {
		FileOutputStream output;
		try {
			Properties properties = generateProperties();
			output = new FileOutputStream(fileName);
			properties.store(output, "Server Configuration File");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ConfigManager() { 
		try {
			FileInputStream input = new FileInputStream(fileName);
			properties.load(input);
		} catch (FileNotFoundException e) {
			new File(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			generateFile(fileName);
		}
	}
}