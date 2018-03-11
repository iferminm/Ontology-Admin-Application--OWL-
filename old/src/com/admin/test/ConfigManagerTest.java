package com.admin.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.admin.config.ConfigManager;



public class ConfigManagerTest {
	@Test
	public void getPropertyTest() {
		String prop = ConfigManager.getInstance().getProperty("virtuosoConnectionString");
		assertTrue("Wrong Parameters or configuration", "jdbc:virtuoso://localhost:1111".equals(prop));
	}	
}