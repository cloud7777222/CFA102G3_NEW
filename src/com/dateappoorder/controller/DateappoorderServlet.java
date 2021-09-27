package com.dateappoorder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dateappoorder.model.*;

public class DateappoorderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String str = req.getParameter("dateOrderNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("沒有此訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/dateappoorder/select_page.jsp");
//					failureView.forward(req, res);

					return;// �{�����_
				}

				Integer dateOrderNo = null;
				try {
					dateOrderNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("沒有此訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/dateappoorder/select_page.jsp");
//					failureView.forward(req, res);
					return;// �{�����_
				}

				/***************************
				 * 2.�}�l�d�߸��
				 *****************************************/
				DateappoorderService dateappoorderSvc = new DateappoorderService();
				DateappoorderVO dateappoorderVO = dateappoorderSvc.getOneDateappoorder(dateOrderNo);
				if (dateappoorderVO == null) {
					errorMsgs.add("沒有此訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/dateappoorder/select_page.jsp");
//					failureView.forward(req, res);
					return;// �{�����_
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("dateappoorderVO", dateappoorderVO); // ��Ʈw���X��dateappoorderVO����,�s�Jreq
				String url = "/front_end/dateappoorder/listOneDateappoorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneDateappoorder.jsp
				successView.forward(req, res);

				/***************************
				 * ��L�i�઺���~�B�z
				 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/dateappoorder/select_page.jsp");
//				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllDateappoorder.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ�
				 ****************************************/
				Integer dateOrderNo = new Integer(req.getParameter("dateOrderNo"));

				/***************************
				 * 2.�}�l�d�߸��
				 ****************************************/
				DateappoorderService dateappoorderSvc = new DateappoorderService();
				DateappoorderVO dateappoorderVO = dateappoorderSvc.getOneDateappoorder(dateOrderNo);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("dateappoorderVO", dateappoorderVO); // ��Ʈw���X��dateappoorderVO����,�s�Jreq
				String url = "/front_end/dateappoorder/update_dateappoorder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_dateappoorder_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/dateappoorder/listAllDateappoorder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_dateappoorder_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				Integer dateOrderNo = new Integer(req.getParameter("dateOrderNo").trim());
				Integer memberNoA = new Integer(req.getParameter("memberNoA").trim());
				Integer memberNoB = new Integer(req.getParameter("memberNoB").trim());

				java.sql.Timestamp dateOrderDate = null;
				try {
					dateOrderDate = java.sql.Timestamp.valueOf(req.getParameter("dateOrderDate").trim());
				} catch (IllegalArgumentException e) {
					dateOrderDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("訂單日期有誤!");
				}

				java.sql.Timestamp dateAppoDate = null;
				try {
					dateAppoDate = java.sql.Timestamp.valueOf(req.getParameter("dateAppoDate").trim());
				} catch (IllegalArgumentException e) {
					dateAppoDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("訂單日期有誤!");
				}

				Integer dateOrderState = new Integer(req.getParameter("dateOrderState").trim());
				Integer dateStarRateA = new Integer(req.getParameter("dateStarRateA").trim());
				Integer dateStarRateB = new Integer(req.getParameter("dateStarRateB").trim());
				Integer dateCE = (dateStarRateA + dateStarRateB) / 2;

				DateappoorderVO dateappoorderVO = new DateappoorderVO();
				dateappoorderVO.setDateOrderNo(dateOrderNo);
				dateappoorderVO.setMemberNoA(memberNoA);
				dateappoorderVO.setMemberNoB(memberNoB);
				dateappoorderVO.setDateOrderDate(dateOrderDate);
				dateappoorderVO.setDateAppoDate(dateAppoDate);
				dateappoorderVO.setDateOrderState(dateOrderState);
				dateappoorderVO.setDateStarRateA(dateStarRateA);
				dateappoorderVO.setDateStarRateB(dateStarRateB);
				dateappoorderVO.setDateCE(dateCE);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dateappoorderVO", dateappoorderVO); // �t����J�榡���~��dateappoorderVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/dateappoorder/update_dateappoorder_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/***************************
				 * 2.�}�l�ק���
				 *****************************************/
				DateappoorderService dateappoorderSvc = new DateappoorderService();
				dateappoorderVO = dateappoorderSvc.updateDateappoorder(dateOrderNo, memberNoA, memberNoB, dateOrderDate,
						dateAppoDate, dateOrderState, dateStarRateA, dateStarRateB, dateCE);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("dateappoorderVO", dateappoorderVO); // ��Ʈwupdate���\��,���T����dateappoorderVO����,�s�Jreq
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneDateappoorder.jsp
				successView.forward(req, res);

				/***************************
				 * ��L�i�઺���~�B�z
				 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/dateappoorder/update_dateappoorder_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addDateappoorder.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				Integer memberNoA = new Integer(req.getParameter("memberNoA").trim());
				Integer memberNoB = new Integer(req.getParameter("memberNoB").trim());

				java.sql.Timestamp dateOrderDate = null;

				dateOrderDate = new java.sql.Timestamp(System.currentTimeMillis());

				java.sql.Timestamp dateAppoDate = null;
				try {
					dateAppoDate = java.sql.Timestamp.valueOf(req.getParameter("dateAppoDate").trim());
				} catch (IllegalArgumentException e) {
					dateAppoDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("約會日期有誤!");
				}

				Integer dateOrderState = 1;
				Integer dateStarRateA = 1;
				Integer dateStarRateB = 1;
				Integer dateCE = 1;

				DateappoorderVO dateappoorderVO = new DateappoorderVO();

				dateappoorderVO.setMemberNoA(memberNoA);
				dateappoorderVO.setMemberNoB(memberNoB);
				dateappoorderVO.setDateOrderDate(dateOrderDate);
				dateappoorderVO.setDateAppoDate(dateAppoDate);
				dateappoorderVO.setDateOrderState(dateOrderState);
				dateappoorderVO.setDateStarRateA(dateStarRateA);
				dateappoorderVO.setDateStarRateB(dateStarRateB);
				dateappoorderVO.setDateCE(dateCE);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dateappoorderVO", dateappoorderVO); // �t����J�榡���~��dateappoorderVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/dateappoorder/addDateappoorder.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/
				DateappoorderService dateappoorderSvc = new DateappoorderService();
				dateappoorderVO = dateappoorderSvc.addDateappoorder(memberNoA, memberNoB, dateOrderDate, dateAppoDate,
						dateOrderState, dateStarRateA, dateStarRateB, dateCE);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllDateappoorder.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/dateappoorder/addDateappoorder.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("delete".equals(action)) { // �Ӧ�listAllDateappoorder.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************
//				 * 1.�����ШD�Ѽ�
//				 ***************************************/
//				Integer dateOrderNo = new Integer(req.getParameter("dateOrderNo"));
//
//				/***************************
//				 * 2.�}�l�R�����
//				 ***************************************/
//				DateappoorderService dateappoorderSvc = new DateappoorderService();
//				dateappoorderSvc.deleteDateappoorder(dateOrderNo);
//
//				/***************************
//				 * 3.�R������,�ǳ����(Send the Success view)
//				 ***********/
//				String url = "/front_end/dateappoorder/listAllDateappoorder.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//
//				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateappoorder/listAllDateappoorder.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}