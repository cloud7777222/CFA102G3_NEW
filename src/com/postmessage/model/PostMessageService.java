package com.postmessage.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.post.model.PostVO;


public class PostMessageService {
	
	private PostMessageDAO_interface dao;

	public PostMessageService() {
		dao = new PostMessageDAO();
	}
	
	public PostMessageVO addPostMessage(Integer memberNo, Integer postNo, String mesContent, Date mesTime, Integer mesState) {

		PostMessageVO postMessageVO = new PostMessageVO();		
		postMessageVO.setMemberNo(memberNo);
		postMessageVO.setPostNo(postNo);
		postMessageVO.setMesContent(mesContent);
		postMessageVO.setMesTime(mesTime);
		postMessageVO.setMesState(mesState);
		dao.insert(postMessageVO);

		return postMessageVO; // 傳去servlet
	}

	public PostMessageVO updatePostMessage(Integer mesNo, Integer memberNo, Integer postNo, String mesContent, Date mesTime, Integer mesState) {

		PostMessageVO postMessageVO = new PostMessageVO();
		postMessageVO.setMesNo(mesNo);;
		postMessageVO.setMemberNo(memberNo);
		postMessageVO.setPostNo(postNo);
		postMessageVO.setMesContent(mesContent);
		postMessageVO.setMesTime(mesTime);
		postMessageVO.setMesState(mesState);
		dao.update(postMessageVO);

		return postMessageVO; // 傳去servlet
	}

	public void deletePostMessage(Integer mesNo) {
		dao.delete(mesNo);
	}

	public PostMessageVO getOnePostMessage(Integer mesNo) {
		return dao.findByPrimaryKey(mesNo);
	}

	public List<PostMessageVO> getAll() {
		return dao.getAll();
	}
	
	
}
