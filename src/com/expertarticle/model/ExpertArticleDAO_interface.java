package com.expertarticle.model;

import java.util.List;



public interface ExpertArticleDAO_interface {

	public void insert(ExpertArticleVO expertArticleVO);
	public void update(ExpertArticleVO expertArticleVO);
	public void delete(Integer articleNo); //��PK
	public ExpertArticleVO findByPrimaryKey(Integer articleNo); //��PK, �|�^�Ǥ@���K�媫��
	public List<ExpertArticleVO> getAll();
	
	
}
