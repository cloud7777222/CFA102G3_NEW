package com.activityorder.model;

import java.sql.Date;


public class ActivityorderVO {

		private Integer actNo;
		private Integer memberNo;
		private Integer actOrderPoint;
		private Integer actTotalParticipant;
		private Date actRegisterOrderDate;
		public Integer getOrderno() {
			return actNo;
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
		public Integer getActOrderPoint() {
			return actOrderPoint;
		}
		public void setActOrderPoint(Integer actOrderPoint) {
			this.actOrderPoint = actOrderPoint;
		}
		public Integer getActTotalParticipant() {
			return actTotalParticipant;
		}
		public void setActTotalParticipant(Integer actTotalParticipant) {
			this.actTotalParticipant = actTotalParticipant;
		}
		public Date getActRegisterOrderDate() {
			return actRegisterOrderDate;
		}
		public void setActRegisterOrderDate(Date actRegisterOrderDate) {
			this.actRegisterOrderDate = actRegisterOrderDate;
		}
		
		
		
}
