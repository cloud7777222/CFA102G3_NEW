package com.postreport.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.post.model.PostVO;

public class PostReportService {

	private PostReportDAO_interface dao;

	public PostReportService() {
		dao = new PostReportDAO();
	}

	public PostReportVO addPostReport(Integer postNo, Integer memberNo, Date postRepTime, String postRepFor,
			Integer postRevState) {

		PostReportVO postReportVO = new PostReportVO();
		postReportVO.setPostNo(postNo);
		postReportVO.setMemberNo(memberNo);
		postReportVO.setPostRepTime(postRepTime);
		postReportVO.setPostRepFor(postRepFor);
		postReportVO.setPostRevState(postRevState);
		dao.insert(postReportVO);

		return postReportVO; // 傳去servlet
	}

	public PostReportVO updatePostReport(Integer postNo, Integer memberNo, Date postRepTime, String postRepFor,
			Integer postRevState) {

		PostReportVO postReportVO = new PostReportVO();
		postReportVO.setPostNo(postNo);
		postReportVO.setMemberNo(memberNo);
		postReportVO.setPostRepTime(postRepTime);
		postReportVO.setPostRepFor(postRepFor);
		postReportVO.setPostRevState(postRevState);
		dao.update(postReportVO);

		return postReportVO; // 傳去servlet
	}

	public void deletePostReport(Integer postNo, Integer memberNo) {
		dao.delete(postNo, memberNo);
	}

	public PostReportVO getOnePostReport(Integer postNo, Integer memberNo) {
		return dao.findByPrimaryKey(postNo, memberNo);
	}

	public List<PostReportVO> getAll() {
		return dao.getAll();
	}

	public List<PostReportVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
