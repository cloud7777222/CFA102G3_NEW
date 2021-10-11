package com.activity.model;


import java.util.List;










public class ActivityService {
	
	private ActivityDAO_interface dao;

	public ActivityService() {
		dao = new ActivityDAO();
	}
	
	public ActivityVO addActivity(Integer actNo,Integer actType, String actName, java.sql.Date actDate,
			String actLocation,String actDirection, Integer maxParticipant,Integer minParticipant,  
			Integer actState,Integer actHoldState, java.sql.Date actRegisterStartDate,java.sql.Date actRegisterDeadLine
			,Integer totalStar,Integer totalEvaluator) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActNo(actNo);
		activityVO.setActType(actType);
		activityVO.setActName(actName);
		activityVO.setActDate(actDate);
		activityVO.setActLocation(actLocation);
		activityVO.setActDirection(actDirection);
		activityVO.setMaxParticipant(maxParticipant);
		activityVO.setMinParticipant(minParticipant);
		activityVO.setActState(actState);
		activityVO.setActHoldState(actHoldState);
		activityVO.setActRegisterStartDate(actRegisterStartDate);
		activityVO.setActRegisterDeadLine(actRegisterDeadLine);
		
		activityVO.setTotalStar(totalStar);
		activityVO.setTotalEvaluator(totalEvaluator);
		
		dao.insert(activityVO);

		return activityVO;
	}

	
	public ActivityVO updateActivity(Integer actNo, Integer actType, String actName, java.sql.Date actDate,
			String actLocation,String actDirection, 
			Integer actState,Integer actHoldState, Integer maxParticipant, Integer minParticipant, java.sql.Date actRegisterStartDate,java.sql.Date actRegisterDeadLine, Integer totalStar,Integer totalEvaluator) {

		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActNo(actNo);
		activityVO.setActType(actType);
		activityVO.setActName(actName);
		activityVO.setActDate(actDate);
		activityVO.setActLocation(actLocation);
		activityVO.setActDirection(actDirection);
		activityVO.setMaxParticipant(maxParticipant);
		activityVO.setMinParticipant(minParticipant);
		activityVO.setActState(actState);
		activityVO.setActHoldState(actHoldState);
		activityVO.setActRegisterStartDate(actRegisterStartDate);
		activityVO.setActRegisterDeadLine(actRegisterDeadLine);
		activityVO.setTotalStar(totalStar);
		activityVO.setTotalEvaluator(totalEvaluator);
		dao.update(activityVO);

		return activityVO;
	}

	public ActivityVO getActivityDetail(Integer actNo) {
		return dao.findByPrimaryKey(actNo);
	}
	
	

	public void deleteActivity(Integer actNo) {
		
		dao.delete(actNo);
	}

	public ActivityVO getOneActivity(Integer actNo) {
		return dao.findByPrimaryKey(actNo);
	}


	public List<ActivityVO> getAll() {
		return dao.getAll();
	}

	public byte[] getPhoto(String actName) {
		
		return dao.getPhoto(actName);
	}

	public void updatePicture(String actName, byte[] actPicture) {
		dao.updatePhoto(actName, actPicture);
	}

		public List<ActivityVO> getActivityByTypeAll(Integer actType) {
		
		   return dao.searchByTypeAll(actType);
	}

	public  List<ActivityVO> getActivityByTypeA(Integer actType) {
        return dao.searchByTypeA(actType);
	}

	public List<ActivityVO> getAllByKeyword(String keyword) {
		return dao.findAllByKeyword(keyword);
	}
	}

	

	

	



