package com.activitymessagereport.model;

import java.util.List;

public interface ActivitymessagereportDAO_interface {
	 public void insert(ActivitymessagereportVO activitymessagereportVO);
     public void update(ActivitymessagereportVO activitymessagereportVO);
     public void delete(Integer actMessageNo);
     public ActivitymessagereportVO findByPrimaryKey(Integer actMessageNo);
     public List<ActivitymessagereportVO> getAll();
}
