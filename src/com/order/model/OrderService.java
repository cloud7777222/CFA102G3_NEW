package com.order.model;

import java.util.List;
import java.util.Set;

import com.orderlist.model.OrderlistVO;

public class OrderService {
	private OrderDAO_interface dao;
	
	public OrderService() {
		dao = new OrderDAO();
	}
	
	public OrderVO addOrder(Integer orderstate,Integer total,String orderer,String address,String tel,
			Integer paymentmeth,Integer deliverymeth) {
		OrderVO orderVO = new OrderVO();
		orderVO.setOrderstate(orderstate);
		orderVO.setTotal(total);
		orderVO.setOrderer(orderer);
		orderVO.setAddress(address);
		orderVO.setTel(tel);
		orderVO.setPaymentmeth(paymentmeth);
		orderVO.setDeliverymeth(deliverymeth);
		
		dao.insert(orderVO);
		
		return orderVO;
	}
	public OrderVO updateOrder(Integer total,String orderer,String address,String tel,Integer paymentmeth,Integer deliverymeth) {
		OrderVO orderVO = new OrderVO();
		
		orderVO.setTotal(total);
		orderVO.setOrderer(orderer);
		orderVO.setAddress(address);
		orderVO.setTel(tel);
		orderVO.setPaymentmeth(paymentmeth);
		orderVO.setDeliverymeth(deliverymeth);
		
		dao.update(orderVO);
		
		return orderVO;		
	}
	public void deleteOrder(Integer orderno) {
		dao.delete(orderno);
	}
	public void cancalOrder(Integer orderno) {
		dao.cancel(orderno);
	}
	public OrderVO getOneOrder(Integer orderno) {
		return dao.findByPrimaryKey(orderno);
	}
	public List<OrderVO> getAll(){
		return dao.getAll();
	}
	public List<OrderVO> getAllByMno(Integer memberno){
		return dao.getByMno(memberno);
	}
	public Set<OrderlistVO> getListByOrdno(Integer orderno){
		return dao.getListbyono(orderno);
	}
	public void insertWithOrderlist(OrderVO orderVO,List<OrderlistVO> list) {
		dao.insertWithOrderlist(orderVO, list);
		
	}
	
}
