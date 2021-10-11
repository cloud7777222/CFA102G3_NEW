package com.post.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.postmessage.model.PostMessageVO;

public interface PostDAO_interface {
	
	public void insert(PostVO postVO);
	public void update(PostVO postVO);
	public void delete(Integer postNo);
	public PostVO findByPrimaryKey(Integer postNo);
	public List<PostVO> getAll();
	public List<PostVO> getAll(Map<String, String[]> map); //萬用複合查詢(傳入參數型態Map)(回傳 List)
	
	public Set<PostMessageVO> getMessagesByPostNo(Integer postNo);//查詢某文章的留言(一對多)(回傳 Set)

}
