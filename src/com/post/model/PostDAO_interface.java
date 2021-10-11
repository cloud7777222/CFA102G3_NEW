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
	public List<PostVO> getAll(Map<String, String[]> map); //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	
	public Set<PostMessageVO> getMessagesByPostNo(Integer postNo);//�d�߬Y�峹���d��(�@��h)(�^�� Set)

}
