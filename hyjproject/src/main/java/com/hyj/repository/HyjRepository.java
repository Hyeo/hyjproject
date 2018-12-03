package com.hyj.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyj.vo.HyjVo;


@Repository
public class HyjRepository {
	
	@Autowired
	SqlSessionTemplate mybatis;
	
	public List<HyjVo> select() {
		return mybatis.selectList("dictSQL.select");
	}
}