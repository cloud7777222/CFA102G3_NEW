package com.activity.model;
	
import java.sql.Date;


	public class ActivityVO {
		private Integer actNo;
		private Integer actType;
		private String actName;
		private Date actDate;
		private String actLocation;
		private String actDirection;
		private Integer maxParticipant;
		private Integer minParticipant;
		private Integer actState;
		private Integer actHoldState;
		private Date actRegisterStartDate;
		private Date actRegisterDeadLine;
		private byte[] actPicture;
		private Integer totalStar;
		private Integer totalEvaluator;
		public Integer getActNo() {
			return actNo;
		}
		public void setActNo(Integer actNo) {
			this.actNo = actNo;
		}
		public Integer getActType() {
			return actType;
		}
		public void setActType(Integer actType) {
			this.actType = actType;
		}
		public String getActName() {
			return actName;
		}
		public void setActName(String actName) {
			this.actName = actName;
		}
		public Date getActDate() {
			return actDate;
		}
		public void setActDate(Date actDate) {
			this.actDate = actDate;
		}
		public String getActLocation() {
			return actLocation;
		}
		public void setActLocation(String actLocation) {
			this.actLocation = actLocation;
		}
		public String getActDirection() {
			return actDirection;
		}
		public void setActDirection(String actDirection) {
			this.actDirection = actDirection;
		}
		public Integer getMaxParticipant() {
			return maxParticipant;
		}
		public void setMaxParticipant(Integer maxParticipant) {
			this.maxParticipant = maxParticipant;
		}
		public Integer getMinParticipant() {
			return minParticipant;
		}
		public void setMinParticipant(Integer minParticipant) {
			this.minParticipant = minParticipant;
		}
		public Integer getActState() {
			return actState;
		}
		public void setActState(Integer actState) {
			this.actState = actState;
		}
		public Integer getActHoldState() {
			return actHoldState;
		}
		public void setActHoldState(Integer actHoldState) {
			this.actHoldState = actHoldState;
		}
		public Date getActRegisterStartDate() {
			return actRegisterStartDate;
		}
		public void setActRegisterStartDate(Date actRegisterStartDate) {
			this.actRegisterStartDate = actRegisterStartDate;
		}
		public Date getActRegisterDeadLine() {
			return actRegisterDeadLine;
		}
		public void setActRegisterDeadLine(Date actRegisterDeadLine) {
			this.actRegisterDeadLine = actRegisterDeadLine;
		}
		public byte[] getActPicture() {
			return actPicture;
		}
		public void setActPicture(byte[] actPicture) {
			this.actPicture = actPicture;
		}
		public Integer getTotalStar() {
			return totalStar;
		}
		public void setTotalStar(Integer totalStar) {
			this.totalStar = totalStar;
		}
		public Integer getTotalEvaluator() {
			return totalEvaluator;
		}
		public void setTotalEvaluator(Integer totalEvaluator) {
			this.totalEvaluator = totalEvaluator;
		}
		
}
