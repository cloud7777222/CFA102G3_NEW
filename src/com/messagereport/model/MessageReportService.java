package com.messagereport.model;

import java.sql.Date;
import java.util.List;


public class MessageReportService {
	
	private MessageReportDAO_interface dao;
	
	public MessageReportService() {
		dao = new MessageReportDAO();
	}
	
	public MessageReportVO addMessageReport(Integer memberNo, Integer mesNo, Date mesRepTime, String mesRepFor, Integer mesRevState) {

		MessageReportVO messageReportVO = new MessageReportVO();		
		messageReportVO.setMemberNo(memberNo);
		messageReportVO.setMesNo(mesNo);
		messageReportVO.setMesRepTime(mesRepTime);
		messageReportVO.setMesRepFor(mesRepFor);
		messageReportVO.setMesRevState(mesRevState);
		dao.insert(messageReportVO);

		return messageReportVO; // 傳去servlet
	}

	public MessageReportVO updateMessageReport(Integer memberNo, Integer mesNo, Date mesRepTime, String mesRepFor, Integer mesRevState) {

		MessageReportVO messageReportVO = new MessageReportVO();
		messageReportVO.setMemberNo(memberNo);
		messageReportVO.setMesNo(mesNo);
		messageReportVO.setMesRepTime(mesRepTime);
		messageReportVO.setMesRepFor(mesRepFor);
		messageReportVO.setMesRevState(mesRevState);
		dao.update(messageReportVO);

		return messageReportVO; // 傳去servlet
	}

	public void deleteMessageReport(Integer memberNo, Integer mesNo) {
		dao.delete(memberNo, mesNo);
	}

	public MessageReportVO getOneMessageReport(Integer memberNo, Integer mesNo) {
		return dao.findByPrimaryKey(memberNo, mesNo);
	}

	public List<MessageReportVO> getAll() {
		return dao.getAll();
	}


}
