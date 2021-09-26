package com.activityorder.model;

import java.sql.Date;
import java.util.List;



public class ActivityorderService {
	private ActivityorderDAO_interface dao;

	public ActivityorderService() {
		dao = new ActivityorderDAO();
	}
	public ActivityorderVO addActivityorder(Integer actNo,Integer memberNo,Integer actOrderPoint,Integer actTotalParticipant, Date actRegisterOrderDate) {
		
		ActivityorderVO activityorderVO = new ActivityorderVO();
		activityorderVO.setActNo(actNo);
		activityorderVO.setMemberNo(memberNo);
		activityorderVO.setActOrderPoint(actOrderPoint);
		activityorderVO.setActTotalParticipant(actTotalParticipant);
		activityorderVO.setActRegisterOrderDate(actRegisterOrderDate);
		dao.insert(activityorderVO);

		return activityorderVO;
}
    public ActivityorderVO updateActivityorder(Integer actNo,Integer memberNo,Integer actOrderPoint,Integer actTotalParticipant, Date actRegisterOrderDate) {

	 ActivityorderVO activityorderVO = new ActivityorderVO();
	 activityorderVO.setActNo(actNo);
	 activityorderVO.setMemberNo(memberNo);
	 activityorderVO.setActOrderPoint(actOrderPoint);
	 activityorderVO.setActTotalParticipant(actTotalParticipant);
	 activityorderVO.setActRegisterOrderDate(actRegisterOrderDate);
	 dao.update(activityorderVO);
	 return activityorderVO;
	}

	

public List<ActivityorderVO> getAll() {
	return dao.getAll();
}

public void deleteActivityorder(Integer actNo) {
	dao.delete(actNo);
}

public ActivityorderVO getOneActivityorder(Integer actNo) {
	return dao.findByPrimaryKey(actNo);
}
}
