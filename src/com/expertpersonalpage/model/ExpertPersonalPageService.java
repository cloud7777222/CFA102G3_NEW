package com.expertpersonalpage.model;

import java.sql.Date;
import java.util.List;



public class ExpertPersonalPageService {

		private ExpertPersonalPageDAO_interface dao;
		
		public ExpertPersonalPageService() {
			dao = new ExpertPersonalPageDAO();
		}
	
	
		public ExpertPersonalPageVO addExpertPerPage(Integer expertNo, byte[] postPhoto, String postContent, Date postTime, Integer numOfLike, Integer postState) {
			
			ExpertPersonalPageVO eppVO = new ExpertPersonalPageVO();
					
			eppVO.setExpertNo(expertNo);
			eppVO.setPostPhoto(postPhoto);
			eppVO.setPostContent(postContent);
			eppVO.setPostTime(postTime);
			eppVO.setNumOfLike(numOfLike);
			eppVO.setPostState(postState);
			dao.insert(eppVO);
						
			return eppVO; //傳去servlet			
		}
		
		
		public ExpertPersonalPageVO updateExpertPerPage(Integer postNo, Integer expertNo, byte[] postPhoto, String postContent, Date postTime, Integer numOfLike, Integer postState) {
			
			ExpertPersonalPageVO eppVO = new ExpertPersonalPageVO();
			
			eppVO.setPostNo(postNo);
			eppVO.setExpertNo(expertNo);
			eppVO.setPostPhoto(postPhoto);
			eppVO.setPostContent(postContent);
			eppVO.setPostTime(postTime);
			eppVO.setNumOfLike(numOfLike);
			eppVO.setPostState(postState);
			dao.update(eppVO);
						
			return eppVO; //傳去servlet			
		}
		
		
		public void deleteExpertPerPage(Integer postNo) {
			dao.delete(postNo);
		}

		
		public ExpertPersonalPageVO getOneExpertPerPage(Integer postNo) {
			return dao.findByPrimaryKey(postNo);
		}

		
		public List<ExpertPersonalPageVO> getAll() {
			return dao.getAll();
		}
		
}
