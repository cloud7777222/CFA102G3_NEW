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
	public List<PostReportVO> getAll(Map<String, String[]> map); //萬用複合查詢(傳入參數型態Map)(回傳 List)

}
