package com.news.model;
import java.sql.Date;

public class NewsVO implements java.io.Serializable{
	private Integer newsNo;
	private String newsTitle;
	private String news;
	private byte[] newsPic;
	private Integer newsState;
	private Date postTime;
	
	public NewsVO() {
		
	}

	public Integer getNewsNo() {
		return newsNo;
	}

	public void setNewsNo(Integer newsNo) {
		this.newsNo = newsNo;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public byte[] getNewsPic() {
		return newsPic;
	}

	public void setNewsPic(byte[] newsPic) {
		this.newsPic = newsPic;
	}

	public Integer getNewsState() {
		return newsState;
	}

	public void setNewsState(Integer newsState) {
		this.newsState = newsState;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	

}
