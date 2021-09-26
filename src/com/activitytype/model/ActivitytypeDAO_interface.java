package com.activitytype.model;

import java.util.List;



public interface ActivitytypeDAO_interface {

    public void insert(ActivitytypeVO activitytypeVO);
    public void update(ActivitytypeVO activitytypeVO);
    public void delete(Integer actType);
    public ActivitytypeVO findByPrimaryKey(Integer actType);
    public List<ActivitytypeVO> getAll();

}
