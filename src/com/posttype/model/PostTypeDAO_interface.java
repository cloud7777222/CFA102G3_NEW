package com.posttype.model;

import java.util.List;
import java.util.Map;

public interface PostTypeDAO_interface {

	public void insert(PostTypeVO postTypeVO);
	public void update(PostTypeVO postTypeVO);
	public void delete(Integer postTypeNo);
	public PostTypeVO findByPrimaryKey(Integer postTypeNo);
	public List<PostTypeVO> getAll();
	public List<PostTypeVO> getAll(Map<String, String[]> map); //萬用複合查詢(傳入參數型態Map)(回傳 List)
	
}
