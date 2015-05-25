package com.learning.serviceTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



public class TestServiceTest {
	@Test
	public void getALLCitiesProvince(){
		ApplicationContext context = new FileSystemXmlApplicationContext("src/spring/applicationContext.xml");
		//testservice ts = (testservice) context.getBean("testservice");
		//ts.getTestUser(1);
	}
}
