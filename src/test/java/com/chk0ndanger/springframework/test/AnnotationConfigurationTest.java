package com.chk0ndanger.springframework.test;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.chk0ndanger.springframework.config.SpringConfig;
import com.chk0ndanger.springframework.context.ApplicationContextUtils;
import com.chk0ndanger.springframework.test.component.TestService;
import com.chk0ndanger.springframework.test.repository.TestRepository;
import com.chk0ndanger.springframework.test.util.Log4jUtil;

public class AnnotationConfigurationTest {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    static {
        // simulate test environment
        System.setProperty(SpringConfig.ENVIRONMENT_DEFAULT_KEY, "test");
        Log4jUtil.setUp("org.springframework");

        context.getEnvironment().setDefaultProfiles(System.getProperty(SpringConfig.ENVIRONMENT_DEFAULT_KEY));
        context.scan("com.chk0ndanger.springframework.test");
        context.refresh();
        ApplicationContextUtils.setContext(context);
    }

    @Test
    public void testAnnotationConfiguration() {
        assertNotNull(context.getBean(TestRepository.class));
        assertNotNull(context.getBean(TestService.class));
    }
}
