package com.activityorder.model;

import java.util.List;

public interface ActivityorderDAO_interface {
	   public void insert(ActivityorderVO activityorderVO);
       public void update(ActivityorderVO activityorderVO);
       public void delete(Integer actNo);
       public ActivityorderVO findByPrimaryKey(Integer actNo);
       public List<ActivityorderVO> getAll();
}
