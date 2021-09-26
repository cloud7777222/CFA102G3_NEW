package com.orderlist.model;

import java.util.List;


import com.order.model.OrderVO;

public interface Orderlist_interface {
	
	public void insert(OrderlistVO orderlistVO);
	public void delete(Integer orderno, Integer prodno);
    public void update(OrderlistVO orderlistVO);
	
	 public OrderlistVO findByPrimaryKey(Integer orderno,Integer prodno);
	 public List<OrderlistVO> getAll();
	 public  List<OrderlistVO> getOrderList(Integer orderno);
	 public void insert2(OrderlistVO orderlistVO,java.sql.Connection con);

}
