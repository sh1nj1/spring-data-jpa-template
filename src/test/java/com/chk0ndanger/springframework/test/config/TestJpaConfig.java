package com.chk0ndanger.springframework.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.chk0ndanger.springframework.config.JpaConfig;

@Configuration
@Profile("test")
@EnableJpaRepositories(basePackages = {"com.chk0ndanger.springframework.test.repository"})
public class TestJpaConfig extends JpaConfig {

}
