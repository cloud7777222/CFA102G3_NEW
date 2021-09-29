package com.dateappoorder.model;

import java.util.List;

public class DateappoorderService {

	private DateappoorderDAO_interface dao;

	public DateappoorderService() {
		dao = new DateappoorderDAO();
	}

	public DateappoorderVO addDateappoorder(Integer memberNoA, Integer memberNoB, java.sql.Timestamp dateOrderDate, java.sql.Timestamp dateAppoDate,
			Integer dateOrderState,Integer dateStarRateA,Integer dateStarRateB,Integer dateCE) {

		DateappoorderVO dateappoorderVO = new DateappoorderVO();

		dateappoorderVO.setMemberNoA(memberNoA);
		dateappoorderVO.setMemberNoB(memberNoB);
		dateappoorderVO.setDateOrderDate(dateOrderDate);
		dateappoorderVO.setDateAppoDate(dateAppoDate);
		dateappoorderVO.setDateOrderState(dateOrderState);
		dateappoorderVO.setDateStarRateA(dateStarRateA);
		dateappoorderVO.setDateStarRateB(dateStarRateB);
		dateappoorderVO.setDateCE(dateCE);
		dao.insert(dateappoorderVO);

		return dateappoorderVO;
	}

	public DateappoorderVO updateDateappoorder(Integer dateOrderNo,Integer memberNoA, Integer memberNoB, java.sql.Timestamp dateOrderDate, java.sql.Timestamp dateAppoDate,
			Integer dateOrderState,Integer dateStarRateA,Integer dateStarRateB,Integer dateCE) {

		DateappoorderVO dateappoorderVO = new DateappoorderVO();

		dateappoorderVO.setDateOrderNo(dateOrderNo);
		dateappoorderVO.setMemberNoA(memberNoA);
		dateappoorderVO.setMemberNoB(memberNoB);
		dateappoorderVO.setDateOrderDate(dateOrderDate);
		dateappoorderVO.setDateAppoDate(dateAppoDate);
		dateappoorderVO.setDateOrderState(dateOrderState);
		dateappoorderVO.setDateStarRateA(dateStarRateA);
		dateappoorderVO.setDateStarRateB(dateStarRateB);
		dateappoorderVO.setDateCE(dateCE);
		dao.update(dateappoorderVO);

		return dateappoorderVO;
	}

//	public void deleteDateappoorder(Integer dateOrderNo) {
//		dao.delete(dateOrderNo);
//	}

	public DateappoorderVO getOneDateappoorder(Integer dateOrderNo) {
		return dao.findByPrimaryKey(dateOrderNo);
	}

	public List<DateappoorderVO> getAll() {
		return dao.getAll();
	}
}
