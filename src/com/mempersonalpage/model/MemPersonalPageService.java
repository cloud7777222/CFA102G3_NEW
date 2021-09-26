package com.mempersonalpage.model;

import java.sql.Date;
import java.util.List;

public class MemPersonalPageService {

	private MemPersonalPageDAO_interface dao;

	public MemPersonalPageService() {
		dao = new MemPersonalPageDAO();
	}

	public MemPersonalPageVO addMemPerPage(Integer memberNo, byte[] postPhoto, String postContent, Date postTime,
			Integer numOfLike, Integer postState) {

		MemPersonalPageVO mppVO = new MemPersonalPageVO();

		mppVO.setMemberNo(memberNo);
		mppVO.setPostPhoto(postPhoto);
		mppVO.setPostContent(postContent);
		mppVO.setPostTime(postTime);
		mppVO.setNumOfLike(numOfLike);
		mppVO.setPostState(postState);
		dao.insert(mppVO);

		return mppVO; // 傳去servlet
	}

	public MemPersonalPageVO updateMemPerPage(Integer postNo, Integer memberNo, byte[] postPhoto, String postContent,
			Date postTime, Integer numOfLike, Integer postState) {

		MemPersonalPageVO mppVO = new MemPersonalPageVO();

		mppVO.setPostNo(postNo);
		mppVO.setMemberNo(memberNo);
		mppVO.setPostPhoto(postPhoto);
		mppVO.setPostContent(postContent);
		mppVO.setPostTime(postTime);
		mppVO.setNumOfLike(numOfLike);
		mppVO.setPostState(postState);
		dao.update(mppVO);

		return mppVO; // 傳去servlet
	}

	public void deleteMemPerPage(Integer postNo) {
		dao.delete(postNo);
	}

	public MemPersonalPageVO getOneMemPerPage(Integer postNo) {
		return dao.findByPrimaryKey(postNo);
	}

	public List<MemPersonalPageVO> getAll() {
		return dao.getAll();
	}

}
