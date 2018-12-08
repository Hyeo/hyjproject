package com.hyj.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyj.vo.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDao {

    @Autowired
    private SqlSession sqlSession;

    public UserDto select(String username) {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================userDao=================");
        log.info("=========================================");
        log.info("=========================================");
        return sqlSession.selectOne("userMapper.select", username);
    }

}
