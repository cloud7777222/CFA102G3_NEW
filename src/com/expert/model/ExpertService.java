package com.expert.model;


import java.util.List;

public class ExpertService {
	private ExpertDAO_interface dao;

	public ExpertService() {
		dao = new ExpertDAO();
	}
	public ExpertVO addExpert(Integer expertNo,Integer expertGenreNo, String exAcount,
			String exPassword,byte[] exPhoto, String exName ,Integer exGender ,
			String exPhone,String exEmail, String exAdress,String exIntroduce,
			Integer checkState,Integer exPoint) {
		
		ExpertVO expertVO = new ExpertVO();
		expertVO.setExpertNo(expertNo);
		expertVO.setExpertGenreNo(expertGenreNo);
		expertVO.setExAcount(exAcount);
		expertVO.setExPassword(exPassword);
		expertVO.setExPhoto(exPhoto);
		expertVO.setExName(exName);
		expertVO.setExGender(exGender);
		expertVO.setExPhone(exPhone);
		expertVO.setExEmail(exEmail);
		expertVO.setExAdress(exAdress);
		expertVO.setExIntroduce(exIntroduce);
		
		
		expertVO.setExPoint(exPoint);
		
		dao.insert(expertVO);

		return expertVO;
	}


	public ExpertVO updateExpert(Integer expertNo,Integer expertGenreNo, String exAcount,
			String exPassword,byte[] exPhoto, String exName ,Integer exGender ,
			String exPhone,String exEmail, String exAdress,String exIntroduce) {

		

				ExpertVO expertVO = new ExpertVO();
				expertVO.setExpertNo(expertNo);
				expertVO.setExpertGenreNo(expertGenreNo);
				expertVO.setExAcount(exAcount);
				expertVO.setExPassword(exPassword);
				expertVO.setExPhoto(exPhoto);
				expertVO.setExName(exName);
				expertVO.setExGender(exGender);
				expertVO.setExPhone(exPhone);
				expertVO.setExEmail(exEmail);
				expertVO.setExAdress(exAdress);
				expertVO.setExIntroduce(exIntroduce);
				
				
			
		        dao.update(expertVO);

		return expertVO;
}
    public void deleteExpert(Integer expertNo) {
		
		dao.delete(expertNo);
	}

	public ExpertVO getOneExpert(Integer expertNo) {
		return dao.findByPrimaryKey(expertNo);
	}


	public List< ExpertVO> getAll() {
		return dao.getAll();
	}
}