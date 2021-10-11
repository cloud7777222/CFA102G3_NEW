package com.activityorder.model;

import java.util.List;

public interface ActivityorderDAO_interface {
	   public void insert(ActivityorderVO activityorderVO);
       public void update(ActivityorderVO activityorderVO);
       public void delete(Integer actNo);
       public ActivityorderVO findByPrimaryKey(Integer actNo,Integer memeberno);
       public List<ActivityorderVO> getAll();
	public void insertWithactivityorder(ActivityorderVO activityorderVO, List<ActivityorderVO> list);

}
