package com.hyj.utill;

public enum HyjCode {

	apple("D1001","사과","01000"),
	banana("D1002","바나나","02000"),
	melon("D1003","메론","03000");
	
	private String code;
	private String codeNm;
	private String upperCode;
	
	HyjCode(String code, String codeNm, String upperCode) {
		this.code = code;
		this.codeNm = codeNm;
		this.upperCode = upperCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getUpperCode() {
		return upperCode;
	}

	public void setUpperCode(String upperCode) {
		this.upperCode = upperCode;
	}
}
