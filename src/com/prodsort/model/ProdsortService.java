package com.prodsort.model;

import java.util.List;

import com.order.model.OrderVO;
import com.orderlist.model.OrderlistVO;
import com.prod.model.ProdVO;
import com.prodsort.model.ProdsortVO;

public class ProdsortService {
	
	private ProdsortDAO_interface dao;

	public ProdsortService() {
		dao = new ProdsortDAO();
	}
	public ProdsortVO addProdsort(String prodsortname) {

		ProdsortVO prodsortVO = new ProdsortVO();

		prodsortVO.setProdsortname(prodsortname);
		dao.insert(prodsortVO);

		return prodsortVO;
	}
	public ProdsortVO updateProdsort(String prodsortname,Integer prodsortno) {

		ProdsortVO prodsortVO = new ProdsortVO();

		prodsortVO.setProdsortname(prodsortname);
		prodsortVO.setProdsortno(prodsortno);
		
		dao.update(prodsortVO);

		return prodsortVO;
	}
	public void deleteProdsort(Integer prodsortno) {
		dao.delete(prodsortno);
	}
	public ProdsortVO getOneEmp(Integer prodsortno) {
		return dao.findByPrimaryKey(prodsortno);
	}
	public List<ProdsortVO> getAll(){
		return dao.getAll();
	}
	public void insertWithProd(ProdsortVO prodsortVO,ProdVO prodVO) {
		dao.insertWithProd(prodsortVO, prodVO);
		
	}

}
