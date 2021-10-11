package com.prod.model;

import java.util.List;

import com.orderlist.model.OrderlistVO;
import com.prod.model.ProdVO;

public interface ProdDAO_interface {
	 public void insert (ProdVO prodVO);
	 public void delete(Integer prodno);
     public void update(ProdVO prodVO);
     public void updatestate(ProdVO prodVO);
     
     public List<ProdVO> searchBySortV(Integer prodsortno);
     public List<ProdVO> searchBySortAll(Integer prodsortno);
     public List<ProdVO> searchByPricerag(Integer minprice,Integer maxprice);
     
     public ProdVO findByPrimaryKey(Integer prodno);
     public List<ProdVO> getAll();
     public List<ProdVO> getAllForFront();
     public void insert2(ProdVO prodVO,java.sql.Connection con);
}
