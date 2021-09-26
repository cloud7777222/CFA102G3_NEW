package com.orderlist.model;

public class OrderlistVO implements java.io.Serializable{
	
	private Integer orderno;
	private Integer prodno;
	private Integer quantity;
	private Integer price;
	public Integer getOrderno() {
		return orderno;
	}
	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}
	public Integer getProdno() {
		return prodno;
	}
	public void setProdno(Integer prodno) {
		this.prodno = prodno;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	

}
