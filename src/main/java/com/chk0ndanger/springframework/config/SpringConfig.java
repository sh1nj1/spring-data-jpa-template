package com.chk0ndanger.springframework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Default SpringFramework Annotation based configuration class,
 * To enable springframework in your module, do as below.
 * 
 * Create YourAppConfig class extends {@link SpringConfig}
 * 
 * <pre class="code">
 * &#064;Configuration
 * &#064;Profile("test")
 * &#064;ComponentScan({"com.chk0dnager.yourmodule.component"})
 * public class TestAppConfig extends AppConfig {
 *     // you can override default configuration.
 * }
 * </pre>
 * 
 * It is necessary to create your own {@link SpringConfig} class because your
 * module's component base package may differ with default configuration.
 * 
 * Also you need to create your own {@link JpaConfig} if you need Jpa repositories.
 * 
 * @see JpaConfig
 * @author soonoh
 */
@Configuration
@ComponentScan({"com.chk0dnager.springframework.component"})
@PropertySources(value= {@PropertySource("classpath:xml/spring-context_${config.env:local}.properties")})
public class SpringConfig extends AnnotationConfigApplicationContext {

    public static final String ENVIRONMENT_DEFAULT_KEY = "config.env";

    @Autowired
    protected Environment env;

    // need to use PropertySource
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {

        return new PropertySourcesPlaceholderConfigurer();
    }

}