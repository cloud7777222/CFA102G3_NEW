package com.activitymessage.model;

import java.util.List;

public interface ActivitymessageDAO_interface {
	
	 public void insert(ActivitymessageVO activitymessageVO);
     public void update(ActivitymessageVO activitymessageVO);
     public void delete(Integer actMessageNo);
     public ActivitymessageVO findByPrimaryKey(Integer actMessageNo);
     public List<ActivitymessageVO> getAll();
}
