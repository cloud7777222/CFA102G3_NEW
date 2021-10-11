package com.activity.model;

import java.util.*;

public interface ActivityDAO_interface {
	
              public void insert(ActivityVO activityVO);
	          public void update(ActivityVO activityVO);
	          public void delete(Integer actNo);
	          public ActivityVO findByPrimaryKey(Integer actNo);
	          public List<ActivityVO> getAll();
			public byte[] getPhoto(String actName);
			public void updatePhoto(String actName, byte[] actPicture);
			  public List<ActivityVO> searchByTypeAll(Integer actType);
			public List<ActivityVO> searchByTypeA(Integer actType);
			public List<ActivityVO> findAllByKeyword(String keyword);
			
	}
