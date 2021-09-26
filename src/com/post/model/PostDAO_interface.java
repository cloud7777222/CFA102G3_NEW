package com.post.model;

import java.util.List;
import java.util.Map;

public interface PostDAO_interface {
	
	public void insert(PostVO postVO);
	public void update(PostVO postVO);
	public void delete(Integer postNo);
	public PostVO findByPrimaryKey(Integer postNo);
	public List<PostVO> getAll();
	public List<PostVO> getAll(Map<String, String[]> map); //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	
}
