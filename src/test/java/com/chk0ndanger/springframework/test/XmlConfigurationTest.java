package com.chk0ndanger.springframework.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chk0ndanger.springframework.test.repository.TestRepository;
import com.chk0ndanger.springframework.test.util.Log4jUtil;


public class XmlConfigurationTest {

    private static final ApplicationContext context;
    static {
    	Log4jUtil.setUp("org.springframework");
    	context = new ClassPathXmlApplicationContext("xml/application-context_"+System.getProperty("profile", "test")+".xml");
    }

    @Test
    public void testXmlConfiguration() {
        assertNotNull(context.getBean(TestRepository.class));
    }
}
