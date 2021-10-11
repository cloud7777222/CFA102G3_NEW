package com.posttype.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.post.model.PostVO;


public class PostTypeService {
	
	private PostTypeDAO_interface dao;

	public PostTypeService() {
		dao = new PostTypeDAO();
	}

	public PostTypeVO addPostType(String postType) {

		PostTypeVO postTypeVO = new PostTypeVO();
		postTypeVO.setPostType(postType);
		dao.insert(postTypeVO);

		return postTypeVO; // 傳去servlet
	}

	public PostTypeVO updatePostType(Integer postTypeNo, String postType) {

		PostTypeVO postTypeVO = new PostTypeVO();
		postTypeVO.setPostTypeNo(postTypeNo);;
		postTypeVO.setPostType(postType);
		dao.update(postTypeVO);

		return postTypeVO; // 傳去servlet
	}

	public void deletePostType(Integer postTypeNo) {
		dao.delete(postTypeNo);
	}

	public PostTypeVO getOnePostType(Integer postTypeNo) {
		return dao.findByPrimaryKey(postTypeNo);
	}

	public List<PostTypeVO> getAll() {
		return dao.getAll();
	}

	public List<PostTypeVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}


	public Set<PostVO> getPostsByPostTypeNo(Integer postTypeNo) {
		return dao.getPostsByPostTypeNo(postTypeNo);
	}
}
