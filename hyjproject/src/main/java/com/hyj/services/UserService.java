package com.hyj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyj.repository.UserDao;
import com.hyj.vo.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDao dao;
    
    public UserDto select(String userName) {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================userService=================");
        log.info("=========================================");
        log.info("=========================================");
        return dao.select(userName);
    }
	
}
