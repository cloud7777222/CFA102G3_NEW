package com.messagereport.model;

import java.io.Serializable;
import java.sql.Date;

public class MessageReportVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer memberNo;
	private Integer mesNo;
	private Date mesRepTime;
	private String mesRepFor;
	private Integer mesRevState;

	// ぃ盿把计篶
	public MessageReportVO() {
		super();
	}
	
	// 篶, 盿把计ノ
	public MessageReportVO(Integer memberNo, Integer mesNo, Date mesRepTime, String mesRepFor, Integer mesRevState) {
		
		this.memberNo = memberNo;
		this.mesNo = mesNo;
		this.mesRepTime = mesRepTime;
		this.mesRepFor = mesRepFor;
		this.mesRevState = mesRevState;
	}

	
	// 砞ミ┮Τ篶把计getter/setter
	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public Integer getMesNo() {
		return mesNo;
	}

	public void setMesNo(Integer mesNo) {
		this.mesNo = mesNo;
	}

	public Date getMesRepTime() {
		return mesRepTime;
	}

	public void setMesRepTime(Date mesRepTime) {
		this.mesRepTime = mesRepTime;
	}

	public String getMesRepFor() {
		return mesRepFor;
	}

	public void setMesRepFor(String mesRepFor) {
		this.mesRepFor = mesRepFor;
	}

	public Integer getMesRevState() {
		return mesRevState;
	}

	public void setMesRevState(Integer mesRevState) {
		this.mesRevState = mesRevState;
	}
	

}
