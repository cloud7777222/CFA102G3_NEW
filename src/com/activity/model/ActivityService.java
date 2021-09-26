package com.activity.model;


import java.util.List;


public class ActivityService {
	
	private ActivityDAO_interface dao;

	public ActivityService() {
		dao = new ActivityDAO();
	}
	
	public ActivityVO addActivity(Integer actNo,Integer actType, String actName, java.sql.Date actDate,
			String actLocation,String actDirection,  
			Integer actState,Integer actHoldState, java.sql.Date actRegisterStartDate,java.sql.Date actRegisterDeadLine
			) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActNo(actNo);
		activityVO.setActType(actType);
		activityVO.setActName(actName);
		activityVO.setActDate(actDate);
		activityVO.setActLocation(actLocation);
		activityVO.setActDirection(actDirection);
		
		activityVO.setActState(actState);
		activityVO.setActHoldState(actHoldState);
		activityVO.setActRegisterStartDate(actRegisterStartDate);
		activityVO.setActRegisterDeadLine(actRegisterDeadLine);
		
		dao.insert(activityVO);

		return activityVO;
	}

	public ActivityVO updateActivity(Integer actNo, Integer actType, String actName, java.sql.Date actDate,
			String actLocation,String actDirection, 
			Integer actState,Integer actHoldState, java.sql.Date actRegisterStartDate,java.sql.Date actRegisterDeadLine, Integer totalStar,Integer totalEvaluator) {

		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActNo(actNo);
		activityVO.setActType(actType);
		activityVO.setActName(actName);
		activityVO.setActDate(actDate);
		activityVO.setActLocation(actLocation);
		activityVO.setActDirection(actDirection);
		
		activityVO.setActState(actState);
		activityVO.setActHoldState(actHoldState);
		activityVO.setActRegisterStartDate(actRegisterStartDate);
		activityVO.setActRegisterDeadLine(actRegisterDeadLine);
		activityVO.setTotalStar(totalStar);
		activityVO.setTotalEvaluator(totalEvaluator);
		dao.update(activityVO);

		return activityVO;
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

	


	

	
}


