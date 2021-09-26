package com.memTime.model;
import java.sql.Date;

public class AdVO implements java.io.Serializable{
	private Integer adNo;
	private String adTitle;
	private String ad;
	private String adPic1;
	private String adPic2;
	private String adPic3;
	private Integer adState;
	private Date postTime;
	private Date deadline;
	
	public AdVO() {
		
	}

	public Integer getAdNo() {
		return adNo;
	}

	public void setAdNo(Integer adNo) {
		this.adNo = adNo;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getAdPic1() {
		return adPic1;
	}

	public void setAdPic1(String adPic1) {
		this.adPic1 = adPic1;
	}

	public String getAdPic2() {
		return adPic2;
	}

	public void setAdPic2(String adPic2) {
		this.adPic2 = adPic2;
	}

	public String getAdPic3() {
		return adPic3;
	}

	public void setAdPic3(String adPic3) {
		this.adPic3 = adPic3;
	}

	public Integer getAdState() {
		return adState;
	}

	public void setAdState(Integer adState) {
		this.adState = adState;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	
}
