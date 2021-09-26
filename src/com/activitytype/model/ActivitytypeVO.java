package com.activitytype.model;


public class ActivitytypeVO {

	private Integer actType;
	private String actTypeName;
     public ActivitytypeVO() {
		
	}
	public ActivitytypeVO(Integer actType, String actTypeName) {
		super();
		this.actType = actType;
		this.actTypeName = actTypeName;
	}
	public Integer getActType() {
		return actType;
	}
	public void setActType(Integer actType) {
		this.actType = actType;
	}
	public String getActTypeName() {
		return actTypeName;
	}
	public void setActTypeName(String actTypeName) {
		this.actTypeName = actTypeName;
	}
	
	
}
