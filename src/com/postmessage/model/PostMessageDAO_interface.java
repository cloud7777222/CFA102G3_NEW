package com.postmessage.model;

import java.util.List;
import java.util.Set;

import com.post.model.PostVO;


public interface PostMessageDAO_interface {
	public void insert(PostMessageVO postMessageVO);
	public void update(PostMessageVO postMessageVO);
	public void delete(Integer mesNo);
	public PostMessageVO findByPrimaryKey(Integer mesNo);
	public List<PostMessageVO> getAll();
	
	
}
