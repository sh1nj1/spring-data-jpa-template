package com.chk0ndanger.springframework.test;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chk0ndanger.springframework.entity.mysql.ExampleEntity;
import com.chk0ndanger.springframework.repository.ExampleRepository;
import com.chk0ndanger.springframework.test.util.Log4jUtil;

/**
 * @author soonoh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:xml/application-context_dev.xml")
public class MySqlTest {

    @BeforeClass
    public static void classSetUp() {

        Log4jUtil.setUp("org.springframework");
    }

    @Autowired
    ExampleRepository repo;
    
    @Before
    public void setUp() {
    	repo.deleteAll();
    }

    @Test(timeout = 5 * 1000)
    public void testWrite() {

        for (int i = 0; i < 100; ++i) {
            ExampleEntity entity = new ExampleEntity();
            entity.setName("Name " + i);
            entity.setAge(i);
            repo.save(entity);
        }
        assertEquals(100, repo.findAll().size());
    }
}
