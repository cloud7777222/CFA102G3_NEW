package com.order.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.orderlist.model.OrderlistVO;

public interface OrderDAO_interface {
	
	
	public void insert(OrderVO orderVO);
	public void delete(Integer orderno);
	public void updateOrder(String orderer,String address,String tel,Integer deliverymeth,Integer orderno);
    public void cancel(Integer orderno,Integer orderstate);
    public void updatePrice(Integer total,Integer orderno);
    public void cancelreason(Integer orderno,String cancelreason);
   
    public OrderVO findByPrimaryKey(Integer orderno);
    public OrderVO findOrderByMemberno(Integer orderno,Integer memberno);
    
    public List<OrderVO> getAll();
    public List<OrderVO> getOrderState(Integer orderstate);
    public List<OrderVO> getOrderStateV(Integer orderstate,Integer memberno);
    public List<OrderVO> getByMno(Integer memberno);
    public List<OrderlistVO> getListbyono(Integer orderno);
    
    
    public void insertWithOrderlist(OrderVO orderVO,List<OrderlistVO> list);
   
}
