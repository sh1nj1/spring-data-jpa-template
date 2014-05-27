package com.chk0ndanger.springframework.test.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chk0ndanger.springframework.test.repository.TestRepository;

@Service
public class TestService {

    @Autowired TestRepository repo;

}
