package com.prodsort.model;

public class ProdsortVO {
	private Integer prodsortno;
	private String prodsortname;
	public ProdsortVO() {
		
	}
	public ProdsortVO(Integer prodsortno, String prodsortname) {
		super();
		this.prodsortno = prodsortno;
		this.prodsortname = prodsortname;
	}
	public Integer getProdsortno() {
		return prodsortno;
	}
	public void setProdsortno(Integer prodsortno) {
		this.prodsortno = prodsortno;
	}
	public String getProdsortname() {
		return prodsortname;
	}
	public void setProdsortname(String prodsortname) {
		this.prodsortname = prodsortname;
	}
	
	

}
