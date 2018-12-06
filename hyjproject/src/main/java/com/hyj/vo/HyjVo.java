package com.hyj.vo;

import lombok.Data;

@Data
public class HyjVo {
	private String name;
	private int num;

	@Override
	public String toString() {
		return "HyjVo [name=" + name + ", num=" + num + "]";
	}
	
	
	
	
}
