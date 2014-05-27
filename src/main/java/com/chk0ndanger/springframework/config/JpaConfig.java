package com.chk0ndanger.springframework.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages = { "com.chk0ndanger.springframework.repository" })
@EnableTransactionManagement
public class JpaConfig {

    @Autowired
    protected Environment env;

    private static final String[] HIBERNATE_PROPERTIES = { 
        "hibernate.dialect", 
        "hibernate.hbm2ddl.auto", 
        "hibernate.cache.provider_class", 
        "hibernate.cache.factory_class" 
    };

    @Bean
    public EntityManagerFactory entityManagerFactory() throws IOException {

        // Hibernate Configuration
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(Boolean.valueOf(env.getProperty("hibernate.showSql")));
        vendorAdapter.setGenerateDdl(Boolean.valueOf(env.getProperty("hibernate.generateDdl")));
        Properties jpaProperties = new Properties();
        for (String name : HIBERNATE_PROPERTIES) {
            jpaProperties.setProperty(name, env.getProperty(name));
        }
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceProvider(vendorAdapter.getPersistenceProvider());
        entityManagerFactory.setJpaDialect(jpaDialect());
        entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan").split(","));
        entityManagerFactory.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        entityManagerFactory.setPersistenceUnitManager(null);
        entityManagerFactory.setPersistenceUnitName("default");
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setDataSource(dataSource());

        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getObject();
    }

    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {

        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
        transactionManager.setDataSource(dataSource());
        transactionManager.setJpaDialect(jpaDialect());
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {

    	int maxPoolSize = Integer.valueOf(env.getProperty("c3p0.maxPoolSize"));
    	final DataSource ds;
    	if (maxPoolSize == 0) {
    		ds = new DriverManagerDataSource();
    		DriverManagerDataSource dataSource = (DriverManagerDataSource) ds;
    		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
    		dataSource.setUrl(env.getProperty("jdbc.url"));
    		dataSource.setUsername(env.getProperty("jdbc.user"));
    		dataSource.setPassword(env.getProperty("jdbc.password"));
    	} else {
    		ds = new ComboPooledDataSource();
    		ComboPooledDataSource dataSource = (ComboPooledDataSource) ds;
            try {
                dataSource.setDriverClass(env.getProperty("jdbc.driver"));
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
            dataSource.setUser(env.getProperty("jdbc.user"));
            dataSource.setPassword(env.getProperty("jdbc.password"));

            // connection pool
            dataSource.setAutoCommitOnClose(false);
            dataSource.setMaxPoolSize(maxPoolSize);
            dataSource.setInitialPoolSize(Integer.valueOf(env.getProperty("c3p0.initialPoolSize")));
    	}
        return ds;
    }

}