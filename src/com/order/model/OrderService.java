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
	public OrderVO updateOrder(String orderer,String address,String tel,Integer deliverymeth,Integer orderno) {
		OrderVO orderVO = new OrderVO();
		
		
		orderVO.setOrderer(orderer);
		orderVO.setAddress(address);
		orderVO.setTel(tel);
		orderVO.setDeliverymeth(deliverymeth);
		orderVO.setOrderno(orderno);
		
		dao.updateOrder(orderer,address,tel,deliverymeth,orderno);
		
		return orderVO;		
	}
	public void deleteOrder(Integer orderno) {
		dao.delete(orderno);
	}
	public void cancalOrder(Integer orderno,Integer orderstate) {
		dao.cancel(orderno,orderstate);
	}
	public void updateCancelReason(Integer orderno,String cancelreason) {
		dao.cancelreason(orderno, cancelreason);		
	}
	public OrderVO getOneOrder(Integer orderno) {
		return dao.findByPrimaryKey(orderno);
	}
	public OrderVO getOneOrderByMember(Integer orderno ,Integer memberno){
		return dao.findOrderByMemberno(orderno, memberno);
	}
	public List<OrderVO> getAll(){
		return dao.getAll();
	}
	public List<OrderVO> getAllByMno(Integer memberno){
		return dao.getByMno(memberno);
	}
	public List<OrderlistVO> getListByOrdno(Integer orderno){
		return dao.getListbyono(orderno);
	}
	public void insertWithOrderlist(OrderVO orderVO,List<OrderlistVO> list) {
		dao.insertWithOrderlist(orderVO, list);
		
	}
	public void getNewPrice(Integer total,Integer orderno){
		dao.updatePrice(total, orderno);
	}
	public List<OrderVO> getOrderState(Integer orderstate) {
		return dao.getOrderState(orderstate);
	}
	public List<OrderVO> getOrderStateV(Integer orderstate,Integer memberno){
		return dao.getOrderStateV(orderstate, memberno);
	}
	
}
