package com.postreport.model;

import java.util.List;
import java.util.Map;

import com.post.model.PostVO;


public interface PostReportDAO_interface {
	
	public void insert(PostReportVO postReportVO);
	public void update(PostReportVO postReportVO);
	public void delete(Integer postNo, Integer memberNo);
	public PostReportVO findByPrimaryKey(Integer postNo, Integer memberNo);
	public List<PostReportVO> getAll();
	public List<PostReportVO> getAll(Map<String, String[]> map); //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)

}
