package com.posttype.model;

import java.io.Serializable;

public class PostTypeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer postTypeNo;
	private String postType;

	// ぃ盿把计篶
	public PostTypeVO() {
		super();
	}

	// 篶, 盿把计ノ
	public PostTypeVO(Integer postTypeNo, String postType) {
		this.postTypeNo = postTypeNo;
		this.postType = postType;

	}

	//砞ミ┮Τ篶把计getter/setter
	public Integer getPostTypeNo() {
		return postTypeNo;
	}

	public void setPostTypeNo(Integer postTypeNo) {
		this.postTypeNo = postTypeNo;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	
	

}
