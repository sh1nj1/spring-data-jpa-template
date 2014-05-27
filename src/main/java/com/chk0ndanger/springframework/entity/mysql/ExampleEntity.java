package com.chk0ndanger.springframework.entity.mysql;

import javax.persistence.Entity;

import com.chk0ndanger.springframework.entity.BaseEntity;

/**
 * Created by soonoh on 1/2/14.
 */
@Entity
public class ExampleEntity extends BaseEntity {

    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
