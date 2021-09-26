package com.activitymessage.model;

import java.sql.Date;


public class ActivitymessageVO  {
		private Integer actMessageNo;
		private Integer actNo;
		private Integer memberNo;
		private String actMessageContent;
		private Date actMessageTime;
		private Integer actMessageState;
		public Integer getActMessageNo() {
			return actMessageNo;
		}
		public void setActMessageNo(Integer actMessageNo) {
			this.actMessageNo = actMessageNo;
		}
		public Integer getActNo() {
			return actNo;
		}
		public void setActNo(Integer actNo) {
			this.actNo = actNo;
		}
		public Integer getMemberNo() {
			return memberNo;
		}
		public void setMemberNo(Integer memberNo) {
			this.memberNo = memberNo;
		}
		public String getActMessageContent() {
			return actMessageContent;
		}
		public void setActMessageContent(String actMessageContent) {
			this.actMessageContent = actMessageContent;
		}
		public Date getActMessageTime() {
			return actMessageTime;
		}
		public void setActMessageTime(Date actMessageTime) {
			this.actMessageTime = actMessageTime;
		}
		public Integer getActMessageState() {
			return actMessageState;
		}
		public void setActMessageState(Integer actMessageState) {
			this.actMessageState = actMessageState;
		}
		
}
