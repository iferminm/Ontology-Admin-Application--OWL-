package com.admin.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Test;

import com.admin.config.ConfigManager;



public class ConfigManagerTest {

	private final String fileName = "ServerConfigTest.properties";
	
	@Test
	public void test() {
		new ConfigManager(this.fileName);
		assertTrue("There were errors during this test", new File(this.fileName).exists());
	}
	
	@After
	public void cleanUp() {
		new File(this.fileName).delete();
	}
}