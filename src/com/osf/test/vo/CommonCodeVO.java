package com.osf.test.vo;

public class CommonCodeVO {
	private Integer ccNum;
	private String ccGroup;
	private String ccCode;
	private String ccName;
	public Integer getCcNum() {
		return ccNum;
	}
	public void setCcNum(Integer ccNum) {
		this.ccNum = ccNum;
	}
	public String getCcGroup() {
		return ccGroup;
	}
	public void setCcGroup(String ccGroup) {
		this.ccGroup = ccGroup;
	}
	public String getCcCode() {
		return ccCode;
	}
	public void setCcCode(String ccCode) {
		this.ccCode = ccCode;
	}
	public String getCcName() {
		return ccName;
	}
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}
	@Override
	public String toString() {
		return "CommonCodeVO [ccNum=" + ccNum + ", ccGroup=" + ccGroup + ", ccCode=" + ccCode + ", ccName=" + ccName
				+ "]";
	}
}
