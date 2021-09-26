package com.dateappoorder.model;

import java.sql.Timestamp;

public class DateappoorderVO implements java.io.Serializable{
	private Integer dateOrderNo;
	private Integer memberNoA;
	private Integer memberNoB;
	private Timestamp dateOrderDate;
	private Timestamp dateAppoDate;
	private Integer dateOrderState;
	private Integer dateStarRateA;
	private Integer dateStarRateB;
	private Integer dateCE;
	public Integer getDateOrderNo() {
		return dateOrderNo;
	}
	public void setDateOrderNo(Integer dateOrderNo) {
		this.dateOrderNo = dateOrderNo;
	}
	public Integer getMemberNoA() {
		return memberNoA;
	}
	public void setMemberNoA(Integer memberNoA) {
		this.memberNoA = memberNoA;
	}
	public Integer getMemberNoB() {
		return memberNoB;
	}
	public void setMemberNoB(Integer memberNoB) {
		this.memberNoB = memberNoB;
	}
	public Timestamp getDateOrderDate() {
		return dateOrderDate;
	}
	public void setDateOrderDate(Timestamp dateOrderDate) {
		this.dateOrderDate = dateOrderDate;
	}
	public Timestamp getDateAppoDate() {
		return dateAppoDate;
	}
	public void setDateAppoDate(Timestamp dateAppoDate) {
		this.dateAppoDate = dateAppoDate;
	}
	public Integer getDateOrderState() {
		return dateOrderState;
	}
	public void setDateOrderState(Integer dateOrderState) {
		this.dateOrderState = dateOrderState;
	}
	public Integer getDateStarRateA() {
		return dateStarRateA;
	}
	public void setDateStarRateA(Integer dateStarRateA) {
		this.dateStarRateA = dateStarRateA;
	}
	public Integer getDateStarRateB() {
		return dateStarRateB;
	}
	public void setDateStarRateB(Integer dateStarRateB) {
		this.dateStarRateB = dateStarRateB;
	}
	public Integer getDateCE() {
		return dateCE;
	}
	public void setDateCE(Integer dateCE) {
		this.dateCE = dateCE;
	}
	
	
	
}
