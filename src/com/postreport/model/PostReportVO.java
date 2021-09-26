package com.postreport.model;

import java.io.Serializable;
import java.sql.Date;

public class PostReportVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer postNo;
	private Integer memberNo;
	private Date postRepTime;
	private String postRepFor;
	private Integer postRevState;

	// ���a�Ѽƪ��غc�l
	public PostReportVO() {
		super();
	}

	// �غc�l, �a�J�Ѽƥ�
	public PostReportVO(Integer postNo, Integer memberNo, Date postRepTime, String postRepFor, Integer postRevState) {
		this.postNo = postNo;
		this.memberNo = memberNo;
		this.postRepTime = postRepTime;
		this.postRepFor = postRepFor;
		this.postRevState = postRevState;
	}

	// �]�ߩҦ��غc�l�Ѽƪ�getter/setter
	public Integer getPostNo() {
		return postNo;
	}

	public void setPostNo(Integer postNo) {
		this.postNo = postNo;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public Date getPostRepTime() {
		return postRepTime;
	}

	public void setPostRepTime(Date postRepTime) {
		this.postRepTime = postRepTime;
	}

	public String getPostRepFor() {
		return postRepFor;
	}

	public void setPostRepFor(String postRepFor) {
		this.postRepFor = postRepFor;
	}

	public Integer getPostRevState() {
		return postRevState;
	}

	public void setPostRevState(Integer postRevState) {
		this.postRevState = postRevState;
	}

}
