package com.messagereport.model;

import java.util.List;


public interface MessageReportDAO_interface {
	
	public void insert(MessageReportVO messageReportVO);
	public void update(MessageReportVO messageReportVO);
	public void delete(Integer memberNo, Integer mesNo);
	public MessageReportVO findByPrimaryKey(Integer memberNo, Integer mesNo);
	public List<MessageReportVO> getAll();

}
