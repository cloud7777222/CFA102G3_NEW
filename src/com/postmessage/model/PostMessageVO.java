package com.postmessage.model;

import java.io.Serializable;
import java.sql.Date;

public class PostMessageVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer mesNo;
	private Integer memberNo;
	private Integer postNo;
	private String mesContent;
	private Date mesTime;
	private Integer mesState;

	// ぃ盿把计篶
	public PostMessageVO() {
		super();
	}

	// 篶, 盿把计ノ
	public PostMessageVO(Integer mesNo, Integer memberNo, Integer postNo, String mesContent, Date mesTime, Integer mesState) {

		this.mesNo = mesNo;
		this.memberNo = memberNo;
		this.postNo = postNo;
		this.mesContent = mesContent;
		this.mesTime = mesTime;
		this.mesState = mesState;
	}

	
	//砞ミ┮Τ篶把计getter/setter
	public Integer getMesNo() {
		return mesNo;
	}

	public void setMesNo(Integer mesNo) {
		this.mesNo = mesNo;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public Integer getPostNo() {
		return postNo;
	}

	public void setPostNo(Integer postNo) {
		this.postNo = postNo;
	}

	public String getMesContent() {
		return mesContent;
	}

	public void setMesContent(String mesContent) {
		this.mesContent = mesContent;
	}

	public Date getMesTime() {
		return mesTime;
	}

	public void setMesTime(Date mesTime) {
		this.mesTime = mesTime;
	}

	public Integer getMesState() {
		return mesState;
	}

	public void setMesState(Integer mesState) {
		this.mesState = mesState;
	}

	
	
}
