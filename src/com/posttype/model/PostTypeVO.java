package com.posttype.model;

import java.io.Serializable;

public class PostTypeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer postTypeNo;
	private String postType;

	// ���a�Ѽƪ��غc�l
	public PostTypeVO() {
		super();
	}

	// �غc�l, �a�J�Ѽƥ�
	public PostTypeVO(Integer postTypeNo, String postType) {
		this.postTypeNo = postTypeNo;
		this.postType = postType;

	}

	//�]�ߩҦ��غc�l�Ѽƪ�getter/setter
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
