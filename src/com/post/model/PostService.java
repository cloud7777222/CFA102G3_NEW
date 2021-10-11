package com.post.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.postmessage.model.PostMessageVO;

public class PostService {

	private PostDAO_interface dao;

	public PostService() {
		dao = new PostDAO();
	}

	public PostVO addPost(Integer postTypeNo, Integer memberNo, String postContent, Date postTime, Integer postState,
			Integer mesCount, Integer numOfLike) {

		PostVO postVO = new PostVO();

		postVO.setPostTypeNo(postTypeNo);
		postVO.setMemberNo(memberNo);
		postVO.setPostContent(postContent);
		postVO.setPostTime(postTime);
		postVO.setPostState(postState);
		postVO.setMesCount(mesCount);
		postVO.setNumOfLike(numOfLike);
		dao.insert(postVO);

		return postVO; // 傳去servlet
	}

	public PostVO updatePost(Integer postNo, Integer postTypeNo, Integer memberNo, String postContent, Date postTime,
			Integer postState, Integer mesCount, Integer numOfLike) {

		PostVO postVO = new PostVO();
		postVO.setPostNo(postNo);;
		postVO.setPostTypeNo(postTypeNo);
		postVO.setMemberNo(memberNo);
		postVO.setPostContent(postContent);
		postVO.setPostTime(postTime);
		postVO.setPostState(postState);
		postVO.setMesCount(mesCount);
		postVO.setNumOfLike(numOfLike);
		dao.update(postVO);

		return postVO; // 傳去servlet
	}

	public void deletePost(Integer postNo) {
		dao.delete(postNo);
	}

	public PostVO getOnePost(Integer postNo) {
		return dao.findByPrimaryKey(postNo);
	}

	public List<PostVO> getAll() {
		return dao.getAll();
	}

	public List<PostVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	
	public Set<PostMessageVO> getMessagesByPostNo(Integer postNo) {
		return dao.getMessagesByPostNo(postNo);
	}


}
