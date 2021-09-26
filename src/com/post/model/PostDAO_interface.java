package com.post.model;

import java.util.List;
import java.util.Map;

public interface PostDAO_interface {
	
	public void insert(PostVO postVO);
	public void update(PostVO postVO);
	public void delete(Integer postNo);
	public PostVO findByPrimaryKey(Integer postNo);
	public List<PostVO> getAll();
	public List<PostVO> getAll(Map<String, String[]> map); //萬用複合查詢(傳入參數型態Map)(回傳 List)
	
}
