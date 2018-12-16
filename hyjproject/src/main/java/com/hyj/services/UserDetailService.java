package com.hyj.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyj.repository.UserDao;
import com.hyj.utill.LoginUser;
import com.hyj.vo.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
    private UserDao dao;

	/*
	 * 초
	 */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================loadUserByUsername=================");
        log.info("=========================================");
        log.info("=========================================");
    	UserDto userDto = dao.select(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("UsernameNotFound [" + username + "]");
        }
        LoginUser user = createUser(userDto);
        return user;
    }

    private LoginUser createUser(UserDto userDto) {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================UserDto=================");
        log.info("=========================================");
        log.info("=========================================");
        LoginUser loginUser = new LoginUser(userDto);
        if (loginUser.getIsAdmin().equals("Y")) {
            loginUser.setRoles(Arrays.asList("ROLE_ADMIN"));
        } else {
            loginUser.setRoles(Arrays.asList("ROLE_USER"));
        }
        return loginUser;
    }
}
