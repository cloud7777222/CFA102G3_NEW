package com.mempersonalpage.model;

import java.io.Serializable;
import java.sql.Date;


public class MemPersonalPageVO implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Integer postNo;
	private Integer memberNo;
	private byte[] postPhoto;
	private String postContent;
	private Date postTime;
	private Integer numOfLike;
	private Integer postState;
	
	//ぃ盿把计篶
	public MemPersonalPageVO() {
		super();
	}
	
	
	//篶, 盿把计ノ
	public MemPersonalPageVO(Integer postNo, Integer memberNo, byte[] postPhoto, String postContent, Date postTime, Integer numOfLike, Integer postState) {
		this.postNo = postNo;
		this.memberNo = memberNo;
		this.postPhoto = postPhoto;
		this.postContent = postContent;
		this.postTime = postTime;
		this.numOfLike = numOfLike;
		this.postState = postState;			
	}


	
	//砞ミ┮Τ篶把计getter/setter
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


	public byte[] getPostPhoto() {
		return postPhoto;
	}


	public void setPostPhoto(byte[] postPhoto) {
		this.postPhoto = postPhoto;
	}


	public String getPostContent() {
		return postContent;
	}


	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}


	public Date getPostTime() {
		return postTime;
	}


	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}


	public Integer getNumOfLike() {
		return numOfLike;
	}


	public void setNumOfLike(Integer numOfLike) {
		this.numOfLike = numOfLike;
	}


	public Integer getPostState() {
		return postState;
	}


	public void setPostState(Integer postState) {
		this.postState = postState;
	}



	
	
	
	
}