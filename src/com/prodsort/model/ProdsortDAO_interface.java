package com.prodsort.model;

import java.util.List;


import com.prod.model.ProdVO;

public interface ProdsortDAO_interface {
	
	public void insert (ProdsortVO prodsortVO);
	public void delete(Integer prodsortno);
	public void update(ProdsortVO prodsortVO);
	public ProdsortVO findByPrimaryKey(Integer prodsortno);
	public List<ProdsortVO> getAll();
	public void insertWithProd(ProdsortVO prodsortVO,ProdVO prodVO);
	
}
