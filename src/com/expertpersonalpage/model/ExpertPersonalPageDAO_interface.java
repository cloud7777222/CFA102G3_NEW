package com.expertpersonalpage.model;

import java.util.List;

//新增修查的抽象方法

public interface ExpertPersonalPageDAO_interface {
	
	public void insert(ExpertPersonalPageVO expertPersonalPage);
	public void update(ExpertPersonalPageVO expertPersonalPage);
	public void delete(Integer postNo); //用PK
	public ExpertPersonalPageVO findByPrimaryKey(Integer postNo); //用PK, 會回傳一筆貼文物件
	public List<ExpertPersonalPageVO> getAll();
	
	
}
