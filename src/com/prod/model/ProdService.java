package com.prod.model;

import java.util.List;



public class ProdService {
	
	private ProdDAO_interface dao;

	public ProdService() {
		dao = new ProdDAO();
	}
	
	public List<ProdVO> getAll(){
		return dao.getAll();
	}
	
	public ProdVO getProdDetail(Integer prodno) {
		return dao.findByPrimaryKey(prodno);
	}
	
	public ProdVO addProd(Integer prodsortno,String prodname,Integer price,String indroce,String prodpic1,String prodpic2,String prodpic3,Integer prodstate)
	{
	ProdVO prodVO = new ProdVO();
	
	prodVO.setProdsortno(prodsortno);
	prodVO.setProdname(prodname);
	prodVO.setPrice(price);
	prodVO.setIndroce(indroce);
	prodVO.setProdpic1(prodpic1);
	prodVO.setProdpic2(prodpic2);
	prodVO.setProdpic3(prodpic3);
	prodVO.setProdstate(prodstate);
	
	dao.insert(prodVO);
	
	return prodVO;
	}
	
	public ProdVO updateProduct(Integer prodno,Integer prodsortno,String prodname,Integer price,String indroce,String prodpic1,String prodpic2,String prodpic3) {
		
		ProdVO prodVO = new ProdVO();
		prodVO.setProdno(prodno);
		prodVO.setProdsortno(prodsortno);
		prodVO.setProdname(prodname);
		prodVO.setPrice(price);
		prodVO.setIndroce(indroce);
		prodVO.setProdpic1(prodpic1);
		prodVO.setProdpic2(prodpic2);
		prodVO.setProdpic3(prodpic3);
		
		dao.update(prodVO);
		
		return prodVO;
	}
	
	public ProdVO updatestate(Integer prodno,Integer prodstate) {
		ProdVO prodVO = new ProdVO();
		
		prodVO.setProdno(prodno);
		prodVO.setProdstate(prodstate);
		
		dao.updatestate(prodVO);
		
		return prodVO;
		
	}
	public void delProd(Integer prodno) {
        dao.delete(prodno);
		
	}
	public  List<ProdVO> getProdBySortV(Integer prodsortno) {
        return dao.searchBySortV(prodsortno);
	}
	public  List<ProdVO> getProdBySortAll(Integer prodsortno) {
        return dao.searchBySortAll(prodsortno);
	}
	public List<ProdVO> getProdByPriceRange(Integer minprice,Integer maxprice){
		return dao.searchByPricerag(minprice,maxprice);
	}
	public List<ProdVO> getAllV(){
		return dao.getAllForFront();
	}
	
}
