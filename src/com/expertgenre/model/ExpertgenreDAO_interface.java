package com.expertgenre.model;

import java.util.List;


public interface ExpertgenreDAO_interface {
	public void insert(ExpertgenreVO expertgenreVO);
    public void update(ExpertgenreVO expertgenreVO);
    public void delete(Integer expertGenreNo);
    public ExpertgenreVO findByPrimaryKey(Integer expertGenreNo);
    public List<ExpertgenreVO> getAll();
}
