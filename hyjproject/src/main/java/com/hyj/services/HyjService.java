package com.hyj.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hyj.controller.HyjController;
import com.hyj.repository.HyjRepository;
import com.hyj.vo.HyjVo;

@Service
public class HyjService {

	@Autowired
	HyjRepository hyjRepository;
	
	public List<HyjVo> select() {

		return hyjRepository.select();
	}
	
}
