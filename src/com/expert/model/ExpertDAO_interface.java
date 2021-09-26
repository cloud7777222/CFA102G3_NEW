package com.expert.model;

import java.util.List;

public interface ExpertDAO_interface {
	public void insert(ExpertVO expertVO);
    public void update(ExpertVO expertVO);
    public void delete(Integer expertNo);
    public ExpertVO findByPrimaryKey(Integer expertNo);
    public List<ExpertVO> getAll();
}
