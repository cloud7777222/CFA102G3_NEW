package com.activitymessage.model;
import java.sql.Date;
import java.util.List;


public class ActivitymessageService {
	private ActivitymessageDAO dao;

	public ActivitymessageService() {
		dao = new ActivitymessageDAO();
	}
	public ActivitymessageVO addActivitymessage(Integer actMessageNo,Integer actNo, Integer memberNo,String actMessageContent,
			Date actMessageTime,Integer actMessageState) {
		
		ActivitymessageVO activitymessageVO = new ActivitymessageVO();
		activitymessageVO.setActMessageNo(actMessageNo);
		activitymessageVO.setActNo(actNo);
		activitymessageVO.setMemberNo(memberNo);
		activitymessageVO.setActMessageContent(actMessageContent);
		activitymessageVO.setActMessageTime(actMessageTime);
		activitymessageVO.setActMessageState(actMessageState);
		dao.insert(activitymessageVO);
		return activitymessageVO;
	}

	public ActivitymessageVO updateActivitymessage(Integer actMessageNo,Integer actNo, Integer memberNo,String actMessageContent,
			Date actMessageTime,Integer actMessageState) {

		ActivitymessageVO activitymessageVO = new ActivitymessageVO();
		activitymessageVO.setActMessageNo(actMessageNo);
		activitymessageVO.setActNo(actNo);
		activitymessageVO.setMemberNo(memberNo);
		activitymessageVO.setActMessageContent(actMessageContent);
		activitymessageVO.setActMessageTime(actMessageTime);
		activitymessageVO.setActMessageState(actMessageState);
		dao.update(activitymessageVO);
		return activitymessageVO;
	}

	

	public void deleteActivitymessage(Integer actMessageNo) {
		
		dao.delete(actMessageNo);
	}

	public ActivitymessageVO getOneActivitymessage(Integer actMessageNo) {
		return dao.findByPrimaryKey(actMessageNo);
	}


	public List<ActivitymessageVO> getAll() {
		return dao.getAll();
	}
}
