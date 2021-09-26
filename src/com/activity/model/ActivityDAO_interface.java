package com.activity.model;

import java.util.*;

public interface ActivityDAO_interface {
	
              public void insert(ActivityVO activityVO);
	          public void update(ActivityVO activityVO);
	          public void delete(Integer actNo);
	          public ActivityVO findByPrimaryKey(Integer actNo);
	          public List<ActivityVO> getAll();
	}
