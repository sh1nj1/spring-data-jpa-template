package com.chk0ndanger.springframework.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.chk0ndanger.springframework.config.SpringConfig;

@Configuration
@Profile("test")
@ComponentScan({"com.chk0ndanger.springframework.test.component"})
public class TestAppConfig extends SpringConfig {

}
