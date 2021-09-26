package com.postreport.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.postreport.model.PostReportService;
import com.postreport.model.PostReportVO;

public class PostReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ �Ӧ�select_page.jsp��"�ܤ@postNO �ݤ峹���|"���ШD ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // �`�����~��jsp�e�{
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				//�峹�s��
				String str = req.getParameter("postNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer postNo = null;
				try {
					postNo = new Integer(str); // ���W�w�峹�s���u���Ʀr
				} catch (Exception e) {
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				//���|�H�|���s��
				String mNo = req.getParameter("memberNo");
				if (mNo == null || (mNo.trim()).length() == 0) {
					errorMsgs.add("�п�J���|�H�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				PostReportService postReportSvc = new PostReportService();
				PostReportVO postReportVO = postReportSvc.getOnePostReport(postNo, memberNo);
				if (postReportVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("postReportVO", postReportVO); // ��Ʈw���X��postReportVO����,�s�Jreq
				String url = "/back_end/postReport/listOnePostReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\�ɵe��forward��浹 listOnePostReport.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

		// [ �Ӧ�addPostReport.jsp��"�s�W"�ШD ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			/* �峹�s�� */
			String ptn = req.getParameter("postNo");
			if (ptn == null || (ptn.trim()).length() == 0) {
				errorMsgs.add("�п�J�峹�s��");
			}
			Integer postNo = null;
			try {
				postNo = new Integer(req.getParameter("postNo").trim());
			} catch (Exception e) {
				errorMsgs.add("�峹�s���榡���~, �ж�Ʀr!");
			}

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
			/* �峹���|�ɶ� */
			java.sql.Date postRepTime = null;
			postRepTime = new java.sql.Date(System.currentTimeMillis()); // ���o��Upo��t�ήɶ�
			try {
				/* �峹���|��] */
				String postRepFor = req.getParameter("postRepFor");
				if (postRepFor == null || (postRepFor.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹���|��]");
				}				
			/* �峹���|���A */
			Integer postRevState = 1; // (1:�ݼf�� / 2:�f�ֳq�L / 3:�f�֥��q�L / �w�]:1)

				/* ==================�غc===================== */
				PostReportVO postReportVO = new PostReportVO();
				postReportVO.setPostNo(postNo);
				postReportVO.setMemberNo(memberNo);
				postReportVO.setPostRepFor(postRepFor);
				postReportVO.setPostRepTime(postRepTime);
				postReportVO.setPostRevState(postRevState);
		;

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postReportVO", postReportVO); // �t����J�榡���~��postReportVO����,�]�s�Jreq, ���ϥΪ̤�������@�Ǹ�T
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/addPostReport.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				PostReportService postReportSvc = new PostReportService();
				postReportVO = postReportSvc.addPostReport(postNo, memberNo, postRepTime, postRepFor, postRevState);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("postReportVO", postReportVO);
				String url = "/back_end/postReport/listAllPostReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllPostReport.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�峹�s�W����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/addPostReport.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
		}

		// [ �Ӧ�listAllPostReport.jsp ��"�R��"�ШD]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				/* �ƦXPK:�峹�s��+���|�H�|���s�� */
				Integer postNo = new Integer(req.getParameter("postNo").trim());
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());

				/*************************** 2.�}�l�R����� ***************************************/
				PostReportService postReportSvc = new PostReportService();
				postReportSvc.deletePostReport(postNo, memberNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^listAllPostReport.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/listAllPostReport.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�listAllPostReport.jsp "�浧�峹�˵�"���ШD ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �ƦXPK:�峹�s��+���|�H�|���s�� */
				Integer postNo = new Integer(req.getParameter("postNo").trim());
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PostReportService postReportSvc = new PostReportService();
				PostReportVO postReportVO = postReportSvc.getOnePostReport(postNo, memberNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("postReportVO", postReportVO); // ��Ʈw���X��postReportVO����,�s�Jreq
				String url = "/back_end/postReport/update_PostReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_PostReport_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/listAllPostReport.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�update_PostReport_input.jsp��"�ץ�"�ШD ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ�����
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �ƦXPK:�峹�s��+���|�H�|���s�� */
				Integer postNo = new Integer(req.getParameter("postNo").trim());
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PostReportService postReportSvc = new PostReportService();

				/**************************** 3.Ū��client�ݰe�L�Ӫ���� ******************************/				
				/* �峹���|��] */
				String postRepFor = req.getParameter("postRepFor");
				if (postRepFor == null) {
					postRepFor = postReportSvc.getOnePostReport(postNo, memberNo).getPostRepFor(); // �峹���e�Y�S�ק�N����
				}
				/* �峹���|�ɶ� */
				java.sql.Date postRepTime = postReportSvc.getOnePostReport(postNo, memberNo).getPostRepTime();
				
				/* �峹���|���A */
				String str = req.getParameter("postRevState"); // �峹���A�Y�S�ק�N����
				Integer postRevState = new Integer(str);
				if (str == null) {
					postRevState = postReportSvc.getOnePostReport(postNo, memberNo).getPostRevState();
				}

				/* ==================�غc===================== */
				PostReportVO postReportVO = new PostReportVO();
				postReportVO.setPostNo(postNo);
				postReportVO.setMemberNo(memberNo);
				postReportVO.setPostRepFor(postRepFor);
				postReportVO.setPostRepTime(postRepTime);
				postReportVO.setPostRevState(postRevState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postReportVO", postReportVO); // �t����J�榡���~��postReportVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/update_PostReport_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.�}�l�s�W��� ***************************************/
				PostReportService postReportSVC = new PostReportService();
				postReportVO = postReportSVC.updatePostReport(postNo, memberNo, postRepTime, postRepFor, postRevState);

				/*************************** 5.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("postReportVO", postReportVO); // ��Ʈwupdate���\��,���T����postReportVO����,�s�Jreq
				String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo+ "&memberNo=" + memberNo; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllMemPerPage.jsp)�M�ק諸�O���@��
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listOnePostReport.jsp ���
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("��ƭק異��:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/update_PostReport_input.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�select_page.jsp��"�ƦX�d��"�ШD ]
		if ("listEmps_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.�N��J����ରMap **********************************/
				// �ĥ�Map<String,String[]> getParameterMap()����k
				// �`�N:an immutable java.util.Map ! �ӥB����s�isession scope!
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

				// �H�U�� if �϶��u��Ĥ@������ɦ���
				if (req.getParameter("whichPage") == null) {
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					// map�Oimmutable! �ҥH�o��new �@��HashMap �~���o�ӯS��!
					// ���M�U����session.setAttribute("map",map1)�|����, �ܦ��d����!
					session.setAttribute("map", map1);
					map = map1;
				}

				/*************************** 2.�}�l�ƦX�d�� ***************************************/
				PostReportService postReportSvc = new PostReportService();
				List<PostReportVO> list = postReportSvc.getAll(map);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/emp/listEmps_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

	}

}
