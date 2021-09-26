package com.post.model;

import java.io.Serializable;
import java.sql.Date;

public class PostVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer postNo;
	private Integer postTypeNo;
	private Integer memberNo;
	private String postContent;
	private Date postTime;
	private Integer postState;
	private Integer mesCount;
	private Integer numOfLike;

	// ぃ盿把计篶
	public PostVO() {
		super();
	}

	
	// 篶, 盿把计ノ
	public PostVO(Integer postNo, Integer postTypeNo, Integer memberNo, String postContent, Date postTime,
			Integer postState, Integer mesCount, Integer numOfLike) {
		
		this.postNo = postNo;
		this.postTypeNo = postTypeNo;
		this.memberNo = memberNo;
		this.postContent = postContent;
		this.postTime = postTime;
		this.postState = postState;
		this.mesCount = mesCount;
		this.numOfLike = numOfLike;				
	}


	
	//砞ミ┮Τ篶把计getter/setter
	public Integer getPostNo() {
		return postNo;
	}


	public void setPostNo(Integer postNo) {
		this.postNo = postNo;
	}


	public Integer getPostTypeNo() {
		return postTypeNo;
	}


	public void setPostTypeNo(Integer postTypeNo) {
		this.postTypeNo = postTypeNo;
	}


	public Integer getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
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


	public Integer getPostState() {
		return postState;
	}


	public void setPostState(Integer postState) {
		this.postState = postState;
	}


	public Integer getMesCount() {
		return mesCount;
	}


	public void setMesCount(Integer mesCount) {
		this.mesCount = mesCount;
	}


	public Integer getNumOfLike() {
		return numOfLike;
	}


	public void setNumOfLike(Integer numOfLike) {
		this.numOfLike = numOfLike;
	}

	

}
