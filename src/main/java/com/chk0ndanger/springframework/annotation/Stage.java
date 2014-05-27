package com.chk0ndanger.springframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Short tag for {@link Profile}("stage") can be attached to 
 * SpringFramework's {@link Component} and {@link Configuration}
 * to mark it as stage profile.
 *
 * @author soonoh
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile("stage")
public @interface Stage {
}