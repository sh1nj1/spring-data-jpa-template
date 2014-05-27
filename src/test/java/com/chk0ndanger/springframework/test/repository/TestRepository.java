package com.chk0ndanger.springframework.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chk0ndanger.springframework.test.entity.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

}
