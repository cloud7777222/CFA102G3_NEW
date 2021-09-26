package com.activityevaluation.model;
import java.util.List;



public class ActivityevaluationService {

	private ActivityevaluationDAO_interface dao;

	public ActivityevaluationService() {
		dao = new ActivityevaluationDAO();
	}
	public ActivityevaluationVO addActivityevaluation(Integer actNo,Integer memberNo, Integer actStarRate
			) {
		
		ActivityevaluationVO activityevaluationVO = new ActivityevaluationVO();
		activityevaluationVO.setActNo(actNo);
		activityevaluationVO.setMemberNo(memberNo);
		activityevaluationVO.setActStarRate(actStarRate);
		dao.insert(activityevaluationVO);
		return activityevaluationVO;
	}

	public ActivityevaluationVO updateActivityevaluation(Integer actNo,Integer memberNo, Integer actStarRate
		) {

		
		
		ActivityevaluationVO activityevaluationVO = new ActivityevaluationVO();
		activityevaluationVO.setActNo(actNo);
		activityevaluationVO.setMemberNo(memberNo);
		activityevaluationVO.setActStarRate(actStarRate);
		dao.update(activityevaluationVO);
		return activityevaluationVO;
	}

	

	public void deleteActivityevaluation(Integer actNo) {
		
		dao.delete(actNo);
	}

	public ActivityevaluationVO getOneActivityevaluation(Integer actNo) {
		return dao.findByPrimaryKey(actNo);
	}


	public List<ActivityevaluationVO> getAll() {
		return dao.getAll();
	}
}
