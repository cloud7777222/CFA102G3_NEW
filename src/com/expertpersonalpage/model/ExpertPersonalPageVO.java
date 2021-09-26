package com.expertpersonalpage.model;

import java.io.Serializable;
import java.sql.Date;


public class ExpertPersonalPageVO implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private Integer postNo;
	private Integer expertNo;
	private byte[] postPhoto;
	private String postContent;
	private Date postTime;
	private Integer numOfLike;
	private Integer postState;
	
	//ぃ盿把计篶
	public ExpertPersonalPageVO() {
		super();
	}
	
	
	//篶, 盿把计ノ
	public ExpertPersonalPageVO(Integer postNo, Integer expertNo, byte[] postPhoto, String postContent, Date postTime, Integer numOfLike, Integer postState) {
		this.postNo = postNo;
		this.expertNo = expertNo;
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


	public Integer getExpertNo() {
		return expertNo;
	}


	public void setExpertNo(Integer expertNo) {
		this.expertNo = expertNo;
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