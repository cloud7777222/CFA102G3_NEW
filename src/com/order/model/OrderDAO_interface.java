package com.order.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.orderlist.model.OrderlistVO;

public interface OrderDAO_interface {
	
	
	public void insert(OrderVO orderVO);
	public void delete(Integer orderno);
	public void update(OrderVO orderVO);
    public void cancel(Integer orderno);
   
   
    public OrderVO findByPrimaryKey(Integer orderno);
    
    public List<OrderVO> getAll();
    
    public List<OrderVO> getByMno(Integer memberno);
    public Set<OrderlistVO> getListbyono(Integer orderno);
    
    public void insertWithOrderlist(OrderVO orderVO,List<OrderlistVO> list);
   
}
