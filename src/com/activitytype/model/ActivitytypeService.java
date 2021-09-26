package com.activitytype.model;

import java.util.List;

public class ActivitytypeService {
	
	private ActivitytypeDAO_interface dao;

	public ActivitytypeService() {
		dao = new ActivitytypeDAO();
	}
	public ActivitytypeVO addActivitytype(Integer actType,String actTypeName) {
			
			ActivitytypeVO activitytypeVO = new ActivitytypeVO();
			activitytypeVO.setActType(actType);
			activitytypeVO.setActTypeName(actTypeName);
			
			dao.insert(activitytypeVO);

			return activitytypeVO;
	}
	public ActivitytypeVO updateActivitytype(Integer actType,String actTypeName) {

			
			ActivitytypeVO activitytypeVO = new ActivitytypeVO();
			activitytypeVO.setActType(actType);
			activitytypeVO.setActTypeName(actTypeName);
			dao.update(activitytypeVO);
			return activitytypeVO;
		}

		

	public List<ActivitytypeVO> getAll() {
		return dao.getAll();
	}

	public void deleteActivitytype(Integer actNo) {
		dao.delete(actNo);
	}

	public ActivitytypeVO getOneActivitytype(Integer actType) {
		return dao.findByPrimaryKey(actType);
	}
}



