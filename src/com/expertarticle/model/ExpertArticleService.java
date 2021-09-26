package com.expertarticle.model;

import java.sql.Date;
import java.util.List;


public class ExpertArticleService {
	
	
	private ExpertArticleDAO_interface dao;
	
	public ExpertArticleService() {
		dao = new ExpertArticleDAO();
	}
	
	
	
	
	public ExpertArticleVO addExpertArticle(Integer expertNo, String articleContent, byte[] articlePhoto, Date articleTime, Integer articleState, Integer numOfLike) {
		
		ExpertArticleVO expertArticleVO = new ExpertArticleVO();
				
		expertArticleVO.setExpertNo(expertNo);		
		expertArticleVO.setArticleContent(articleContent);
		expertArticleVO.setArticlePhoto(articlePhoto);
		expertArticleVO.setArticleTime(articleTime);	
		expertArticleVO.setArticleState(articleState);
		expertArticleVO.setNumOfLike(numOfLike);
		dao.insert(expertArticleVO);
					
		return expertArticleVO; //傳去servlet			
	}
	
	
	public ExpertArticleVO updateExpertArticle(Integer articleNo, Integer expertNo, String articleContent, byte[] articlePhoto, Date articleTime, Integer articleState, Integer numOfLike) {
		
		ExpertArticleVO expertArticleVO = new ExpertArticleVO();
		
		expertArticleVO.setArticleNo(articleNo);
		expertArticleVO.setExpertNo(expertNo);
		expertArticleVO.setArticleContent(articleContent);
		expertArticleVO.setArticlePhoto(articlePhoto);
		expertArticleVO.setArticleTime(articleTime);	
		expertArticleVO.setArticleState(articleState);
		expertArticleVO.setNumOfLike(numOfLike);
		dao.update(expertArticleVO);
					
		return expertArticleVO; //傳去servlet			
	}
	
	
	public void deleteExpertArticle(Integer articleNo) {
		dao.delete(articleNo);
	}

	
	public ExpertArticleVO getOneExpertArticle(Integer articleNo) {
		return dao.findByPrimaryKey(articleNo);
	}

	
	public List<ExpertArticleVO> getAll() {
		return dao.getAll();
	}

}
