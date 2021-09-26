package com.order.model;

import java.sql.Date;

public class OrderVO implements java.io.Serializable{
	
	 private Integer orderno;
	 private Integer memberno;
	 private Integer orderstate;
	 private Integer total;
	 private String orderer;
	 private String address;
	 private String tel;
	 private Date orderdate;
	 private String creditcardnum;
	 private Integer paymentmeth;
	 private Integer deliverymeth;
	 
	 
	public Integer getOrderno() {
		return orderno;
	}
	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}
	
	public Integer getMemberno() {
		return memberno;
	}
	public void setMemberno(Integer memberno) {
		this.memberno = memberno;
	}
	public Integer getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(Integer orderstate) {
		this.orderstate = orderstate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getCreditcardnum() {
		return creditcardnum;
	}
	public void setCreditcardnum(String creditcardnum) {
		this.creditcardnum = creditcardnum;
	}
	public Integer getPaymentmeth() {
		return paymentmeth;
	}
	public void setPaymentmeth(Integer paymentmeth) {
		this.paymentmeth = paymentmeth;
	}
	public Integer getDeliverymeth() {
		return deliverymeth;
	}
	public void setDeliverymeth(Integer deliverymeth) {
		this.deliverymeth = deliverymeth;
	}
	 
	 

}
