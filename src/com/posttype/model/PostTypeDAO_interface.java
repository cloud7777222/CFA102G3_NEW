package com.posttype.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.post.model.PostVO;


public interface PostTypeDAO_interface {

	public void insert(PostTypeVO postTypeVO);
	public void update(PostTypeVO postTypeVO);
	public void delete(Integer postTypeNo);
	public PostTypeVO findByPrimaryKey(Integer postTypeNo);
	public List<PostTypeVO> getAll();
	
	public List<PostTypeVO> getAll(Map<String, String[]> map); //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List) 
    public Set<PostVO> getPostsByPostTypeNo(Integer postTypeNo);//�d�߬Y���O���峹(�@��h)(�^�� Set)
	
	
}
