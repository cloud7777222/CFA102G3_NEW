package com.expertarticle.model;

import java.io.Serializable;
import java.sql.Date;

public class ExpertArticleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer articleNo;
	private Integer expertNo;
	private String articleContent;
	private byte[] articlePhoto;
	private Date articleTime;
	private Integer articleState;
	private Integer numOfLike;

	// ぃ盿把计篶
	public ExpertArticleVO() {
		super();
	}

	// 篶, 盿把计ノ
	public ExpertArticleVO(Integer articleNo, Integer expertNo, String articleContent, byte[] articlePhoto,
			Date articleTime, Integer articleState, Integer numOfLike) {
		this.articleNo = articleNo;
		this.expertNo = expertNo;
		this.articleContent = articleContent;
		this.articlePhoto = articlePhoto;
		this.articleTime = articleTime;
		this.articleState = articleState;
		this.numOfLike = numOfLike;
	}

	// 砞ミ┮Τ篶把计getter/setter
	public Integer getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}

	public Integer getExpertNo() {
		return expertNo;
	}

	public void setExpertNo(Integer expertNo) {
		this.expertNo = expertNo;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public byte[] getArticlePhoto() {
		return articlePhoto;
	}

	public void setArticlePhoto(byte[] articlePhoto) {
		this.articlePhoto = articlePhoto;
	}

	public Date getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(Date articleTime) {
		this.articleTime = articleTime;
	}

	public Integer getArticleState() {
		return articleState;
	}

	public void setArticleState(Integer articleState) {
		this.articleState = articleState;
	}

	public Integer getNumOfLike() {
		return numOfLike;
	}

	public void setNumOfLike(Integer numOfLike) {
		this.numOfLike = numOfLike;
	}

}
