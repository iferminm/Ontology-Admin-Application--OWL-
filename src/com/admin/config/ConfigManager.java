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

	private String fileName = "ServerConfig.properties";
	private Properties properties;
	private String defaultValue = "not set";

	
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
			File file = new File(name);
			file.createNewFile();
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
	
	public ConfigManager() {
		this("ServerConfig.properties");
	}
	
	private void loadProperties(String fileName) {
		try {
			FileInputStream in = new FileInputStream(fileName);
			this.properties.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// TODO: rewrite
	public ConfigManager(String fileName) {
		if (fileName.endsWith(".properties")) {
			File file = new File(fileName);
			if (!file.exists()) {
				this.generateFile(fileName);
			}
			this.loadProperties(fileName);
		} else {
			System.out.println("Invalid Name");
		}
	}
}