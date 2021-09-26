package com.activitymessagereport.model;
import java.util.List;

public class ActivitymessagereportService {
	private ActivitymessagereportDAO dao;

	public ActivitymessagereportService() {
		dao = new ActivitymessagereportDAO();
	}
	public ActivitymessagereportVO addActivitymessagereport(Integer actMessageNo, Integer memberNo, String actMessageReportReason,Integer actMessageReportState) {
		
		ActivitymessagereportVO activitymessagereportVO = new ActivitymessagereportVO();
		activitymessagereportVO.setActMessageNo(actMessageNo);
		activitymessagereportVO.setMemberNo(memberNo);
		activitymessagereportVO.setActMessageReportReason(actMessageReportReason);
		activitymessagereportVO.setActMessageReportState(actMessageReportState);
		dao.insert(activitymessagereportVO);
		return activitymessagereportVO;
	}

	public ActivitymessagereportVO updateActivitymessagereport(Integer actMessageNo, Integer memberNo, String actMessageReportReason,Integer actMessageReportState) {
		ActivitymessagereportVO activitymessagereportVO = new ActivitymessagereportVO();
		activitymessagereportVO.setActMessageNo(actMessageNo);
		activitymessagereportVO.setMemberNo(memberNo);
		activitymessagereportVO.setActMessageReportReason(actMessageReportReason);
		activitymessagereportVO.setActMessageReportState(actMessageReportState);
		dao.update(activitymessagereportVO);
		return activitymessagereportVO;
	}

	

	public void deleteActivitymessagereport(Integer actMessageNo) {
		
		dao.delete(actMessageNo);
	}

	public ActivitymessagereportVO getOneActivitymessagereport(Integer actMessageNo) {
		return dao.findByPrimaryKey(actMessageNo);
	}


	public List<ActivitymessagereportVO> getAll() {
		return dao.getAll();
	}
}
