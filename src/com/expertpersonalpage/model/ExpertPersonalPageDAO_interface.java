package com.expertpersonalpage.model;

import java.util.List;

//�s�W�׬d����H��k

public interface ExpertPersonalPageDAO_interface {
	
	public void insert(ExpertPersonalPageVO expertPersonalPage);
	public void update(ExpertPersonalPageVO expertPersonalPage);
	public void delete(Integer postNo); //��PK
	public ExpertPersonalPageVO findByPrimaryKey(Integer postNo); //��PK, �|�^�Ǥ@���K�媫��
	public List<ExpertPersonalPageVO> getAll();
	
	
}
