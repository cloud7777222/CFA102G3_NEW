package com.orderlist.model;

import java.util.List;



public class OrderlistService {
	
	private Orderlist_interface dao;
	
	public OrderlistService() {
		
		dao = new OrderlistDAO(); 
	}
	public List<OrderlistVO> getAll(){
		return dao.getAll();
	}
	public OrderlistVO addList(Integer orderno,Integer prodno,Integer quantity,Integer price) {
		OrderlistVO orderlistVO = new OrderlistVO();
		
		orderlistVO.setOrderno(orderno);
		orderlistVO.setProdno(prodno);
		orderlistVO.setQuantity(quantity);
		orderlistVO.setPrice(price);
		
		dao.insert(orderlistVO);
		
		return orderlistVO;
	}
	public OrderlistVO updateList(Integer orderno,Integer prodno,Integer quantity,Integer price) {
		OrderlistVO orderlistVO = new OrderlistVO();
		
		orderlistVO.setOrderno(orderno);
		orderlistVO.setProdno(prodno);
		orderlistVO.setQuantity(quantity);
		orderlistVO.setPrice(price);
		
		dao.update(orderlistVO);
		
		return orderlistVO;
	}
	
	public void deleteList(Integer orderno,Integer prodno) {
		dao.delete(orderno,prodno);
	}
	public OrderlistVO getOneList(Integer orderno,Integer prodno) {
		return dao.findByPrimaryKey(orderno, prodno);
	}
	public List<OrderlistVO> getOrderDetail(Integer orderno){
		return dao.getOrderList(orderno);
	}

}
