package com.activityevaluation.model;

import java.util.List;


public interface ActivityevaluationDAO_interface {
	 public void insert(ActivityevaluationVO activityevaluationVO);
     public void update(ActivityevaluationVO activityevaluationVO);
     public void delete(Integer actNo);
     public ActivityevaluationVO findByPrimaryKey(Integer actNo);
     public List<ActivityevaluationVO> getAll();
}
