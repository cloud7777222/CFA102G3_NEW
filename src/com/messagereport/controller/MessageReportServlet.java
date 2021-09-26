package com.messagereport.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.messagereport.model.MessageReportService;
import com.messagereport.model.MessageReportVO;

public class MessageReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ �Ӧ�select_page.jsp��"�ܤ@ (memberNo+mesNO) �ݤ峹�d�����|"���ШD ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // �`�����~��jsp�e�{
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				// ���|�H�|���s��
				String mNo = req.getParameter("memberNo");
				if (mNo == null || (mNo.trim()).length() == 0) {
					errorMsgs.add("�п�J���|�H�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer memberNo = null;
				try {
					memberNo = new Integer(mNo); // ���W�w�s���u���Ʀr
				} catch (Exception e) {
					errorMsgs.add("���|�H�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				// �峹�d���s��
				String str = req.getParameter("mesNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�d���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer mesNo = null;
				try {
					mesNo = new Integer(str); // ���W�w�s���u���Ʀr
				} catch (Exception e) {
					errorMsgs.add("�峹�d���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				MessageReportService postReportSvc = new MessageReportService();
				MessageReportVO messageReportVO = postReportSvc.getOneMessageReport(memberNo, mesNo);
				if (messageReportVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("messageReportVO", messageReportVO); // ��Ʈw���X��messageReportVO����,�s�Jreq
				String url = "/back_end/messageReport/listOneMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\�ɵe��forward��浹 listOneMessageReport.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�addMessageReport.jsp��"�s�W"�ШD ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/			
			/* ���|�H�|���s�� */
			String str = req.getParameter("memberNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J���|�H�|���s��");
			}
			Integer memberNo = null;
			try {
				memberNo = new Integer(req.getParameter("memberNo").trim());
			} catch (Exception e) {
				errorMsgs.add("���|�H�|���s���榡���~, �ж�Ʀr!");
			}			
			/* �峹�d���s�� */
			String ptn = req.getParameter("mesNo");
			if (ptn == null || (ptn.trim()).length() == 0) {
				errorMsgs.add("�п�J�峹�d���s��");
			}
			Integer mesNo = null;
			try {
				mesNo = new Integer(req.getParameter("mesNo").trim());
			} catch (Exception e) {
				errorMsgs.add("�峹�d���s���榡���~, �ж�Ʀr!");
			}
			/* �d�����|�ɶ� */
			java.sql.Date mesRepTime = null;
			mesRepTime = new java.sql.Date(System.currentTimeMillis()); // ���o��Upo��t�ήɶ�
			try {
				/* �d�����|��] */
				String mesRepFor = req.getParameter("mesRepFor");
				if (mesRepFor == null || (mesRepFor.trim()).length() == 0) {
					errorMsgs.add("�п�J�d�����|��]");
				}
				/* �d�����|�B�z���A */
				Integer mesRevState = 1; // (1:�ݼf�� / 2:�f�ֳq�L / 3:�f�֥��q�L / �w�]:1)

				/* ==================�غc===================== */
				MessageReportVO messageReportVO = new MessageReportVO();				
				messageReportVO.setMemberNo(memberNo);
				messageReportVO.setMesNo(mesNo);
				messageReportVO.setMesRepFor(mesRepFor);
				messageReportVO.setMesRepTime(mesRepTime);
				messageReportVO.setMesRevState(mesRevState);
				;

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); // �t����J�榡���~��postReportVO����,�]�s�Jreq, ���ϥΪ̤�������@�Ǹ�T
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/addPostReport.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				MessageReportService messageReportSvc = new MessageReportService();
				messageReportVO = messageReportSvc.addMessageReport(memberNo, mesNo, mesRepTime, mesRepFor, mesRevState);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("messageReportVO", messageReportVO);
				String url = "/back_end/messageReport/listAllMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllMessageReport.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�d�����|�s�W����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/addMessageReport.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
		}

		// [ �Ӧ�listAllMessageReport.jsp ��"�R��"�ШD]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				/* �ƦXPK:���|�H�|���s��+�峹�d���s�� */
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.�}�l�R����� ***************************************/
				MessageReportService messageReportSvc = new MessageReportService();
				messageReportSvc.deleteMessageReport(mesNo, memberNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^listAllMessageReport.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/listAllMessageReport.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�listAllMessageReport.jsp "�浧�峹�˵�"���ШD ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �ƦXPK:���|�H�|���s��+�峹�d���s�� */
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				MessageReportService messageReportSvc = new MessageReportService();
				MessageReportVO messageReportVO = messageReportSvc.getOneMessageReport(memberNo, mesNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("messageReportVO", messageReportVO); // ��Ʈw���X��messageReportVO����,�s�Jreq
				String url = "/back_end/messageReport/update_MessageReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_MessageReport_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/listAllMessageReport.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�update_MessageReport_input.jsp��"�ץ�"�ШD ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ�����
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �ƦXPK:���|�H�|���s��+�峹�d���s�� */
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				MessageReportService messageReportSvc = new MessageReportService();

				/**************************** 3.Ū��client�ݰe�L�Ӫ���� ******************************/
				/* �峹���|��] */
				String mesRepFor = req.getParameter("mesRepFor");
				if (mesRepFor == null) {
					mesRepFor = messageReportSvc.getOneMessageReport(memberNo, mesNo).getMesRepFor(); // �峹���e�Y�S�ק�N����
				}
				/* �峹���|�ɶ� */
				java.sql.Date mesRepTime = messageReportSvc.getOneMessageReport(memberNo, mesNo).getMesRepTime(); 
				
				/* �峹���|���A */
				String str = req.getParameter("mesRevState"); // �峹���A�Y�S�ק�N����
				Integer mesRevState = new Integer(str);
				if (str == null) {
					mesRevState = messageReportSvc.getOneMessageReport(memberNo, mesNo).getMesRevState();
				}

				/* ==================�غc===================== */
				MessageReportVO messageReportVO = new MessageReportVO();			
				messageReportVO.setMemberNo(memberNo);
				messageReportVO.setMesNo(mesNo);
				messageReportVO.setMesRepFor(mesRepFor);
				messageReportVO.setMesRepTime(mesRepTime);
				messageReportVO.setMesRevState(mesRevState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); // �t����J�榡���~��messageReportVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/messageReport/update_MessageReport_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.�}�l�s�W��� ***************************************/
				MessageReportService messageReportSVC = new MessageReportService();
				messageReportVO = messageReportSVC.updateMessageReport(memberNo, mesNo, mesRepTime, mesRepFor, mesRevState);

				/*************************** 5.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("messageReportVO", messageReportVO); // ��Ʈwupdate���\��,���T����messageReportVO����,�s�Jreq
				String url = requestURL + "?whichPage=" + whichPage + "&memberNo=" + memberNo + "&mesNo=" + mesNo ; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllMemPerPage.jsp)�M�ק諸�O���@��
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listOneMessageReport.jsp ���
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("��ƭק異��:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/messageReport/update_MessageReport_input.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
