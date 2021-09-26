package com.expertarticle.model;

import java.util.List;



public interface ExpertArticleDAO_interface {

	public void insert(ExpertArticleVO expertArticleVO);
	public void update(ExpertArticleVO expertArticleVO);
	public void delete(Integer articleNo); //用PK
	public ExpertArticleVO findByPrimaryKey(Integer articleNo); //用PK, 會回傳一筆貼文物件
	public List<ExpertArticleVO> getAll();
	
	
}
