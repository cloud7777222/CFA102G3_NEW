package com.expertpersonalpage.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.expertpersonalpage.model.ExpertPersonalPageService;
import com.expertpersonalpage.model.ExpertPersonalPageVO;
import com.mempersonalpage.model.MemPersonalPageService;
import com.mempersonalpage.model.MemPersonalPageVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ExpertPersonalPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");
		Integer postNo = new Integer(req.getParameter("postNo")); // ��<img src=" ?pk=yyy"> pk���Ѽƭ�
		ExpertPersonalPageService eppSvc = new ExpertPersonalPageService();
		ServletOutputStream sout = res.getOutputStream(); // ��X��Ƭy
		byte[] buf = eppSvc.getOneExpertPerPage(postNo).getPostPhoto();
		sout.write(buf); // ��X�G�줸���
		sout.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ �Ӧ�select_page.jsp��"�ܤ@postNO �ݶK��"���ШD ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("postNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�K��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/expertPersonalPage/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer postNo = null;
				try {
					postNo = new Integer(str); // ���W�w�K��s���u���Ʀr
				} catch (Exception e) {
					errorMsgs.add("�K��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/expertPersonalPage/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ExpertPersonalPageService eppSvc = new ExpertPersonalPageService();
				ExpertPersonalPageVO eppVO = eppSvc.getOneExpertPerPage(postNo);
				if (eppVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/expertPersonalPage/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("eppVO", eppVO); // ��Ʈw���X��eppVO����,�s�Jreq
				String url = "/back_end/expertPersonalPage/listOneExpertPerPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\�ɵe��forward��浹
																				// listOneExpertPerPage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertPersonalPage/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�addExpertPerPage.jsp��"�s�W"�ШD ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

			/* �M�a�s�� */
			String str = req.getParameter("expertNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J�M�a�s��");
			}
			Integer expertNo = null;
			try {
				expertNo = new Integer(req.getParameter("expertNo").trim()); // �p��۰ʨ��o�n�J�̱b���۰ʱa�J�o��? session
				// ��setAttribute��?
			} catch (Exception e) {
				errorMsgs.add("�M�a�s���榡���~");
			}

//			try {
			/* �K��Ϥ� */
			res.setContentType("text/html; charset=UTF-8");
			Part part = req.getPart("postPhoto");
			String filename = getFileNameFromPart(part);

			// �Nimage�e�iDB�x�s
			InputStream in = part.getInputStream();
			byte[] postPhoto = new byte[in.available()]; // �Q��setBytes �n�e�iDB
			in.read(postPhoto);
			in.close();
			req.setAttribute("postPhoto", postPhoto);
			if (filename == null || (filename.trim()).length() == 0) {
				errorMsgs.add("�п�ܤW�ǹϤ�");
			}

			/* �K�夺�e */
			String postContent = req.getParameter("postContent");
			if (postContent == null || (postContent.trim()).length() == 0) {
				errorMsgs.add("�п�J�K�夺�e");
			}
			/* �K��ɶ� */
			java.sql.Date postTime = null;
			postTime = new java.sql.Date(System.currentTimeMillis()); // ���o��Upo��t�ήɶ�

			/* ���g�� */
			Integer numOfLike = 0;

			/* �K�媬�A */
			Integer postState = 1;

			/* ==================�غc===================== */
			ExpertPersonalPageVO eppVO = new ExpertPersonalPageVO();
			eppVO.setExpertNo(expertNo);
			eppVO.setPostPhoto(postPhoto);
			eppVO.setPostContent(postContent);
			eppVO.setPostTime(postTime);
			eppVO.setNumOfLike(numOfLike);
			eppVO.setPostState(postState);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("eppVO", eppVO); // �t����J�榡���~��eppVO����,�]�s�Jreq, ���ϥΪ̤�������@�Ǹ�T
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertPersonalPage/addExpertPerPage.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�s�W��� ***************************************/
			ExpertPersonalPageService eppSvc = new ExpertPersonalPageService();
			eppVO = eppSvc.addExpertPerPage(expertNo, postPhoto, postContent, postTime, numOfLike, postState);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			req.setAttribute("eppVO", eppVO);
			String url = "/back_end/expertPersonalPage/listAllExpertPerPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllExpertPerPage.jsp
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�K��s�W����:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertPersonalPage/addExpertPerPage.jsp");
//				failureView.forward(req, res);
//				return;// �{�����_
//			}
		}

		// [ �Ӧ�listAllExpertPerPage.jsp.jsp ��"�R��"�ШD]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer postNo = new Integer(req.getParameter("postNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				ExpertPersonalPageService eppSvc = new ExpertPersonalPageService();
				eppSvc.deleteExpertPerPage(postNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL;
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^listAllExpertPerPage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertPersonalPage/listAllExpertPerPage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�listAllExpertPerPage.jsp "�浧�K���˵�"���ШD ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			/*************************** 1.�����ШD�Ѽ� ****************************************/
			Integer postNo = new Integer(req.getParameter("postNo").trim());

			/*************************** 2.�}�l�d�߸�� ****************************************/
			ExpertPersonalPageService eppSvc = new ExpertPersonalPageService();
			ExpertPersonalPageVO eppVO = eppSvc.getOneExpertPerPage(postNo);

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
			req.setAttribute("eppVO", eppVO); // ��Ʈw���X��mppVO����,�s�Jreq
			String url = "/back_end/expertPersonalPage/update_ExpertPerPage_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_ExpertPerPage_input.jsp
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/memPersonalPage/listAllMemPerPage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�update_ExpertPerPage_input.jsp��"�ץ�"�ШD ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ�����
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �K��s�� */
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.�w�Ƴs���d��DB��� ****************************************/
				ExpertPersonalPageService eppSvc = new ExpertPersonalPageService();
				
				/**************************** 3.Ū��client�ݰe�L�Ӫ���� ******************************/
				/* �M�a�s�� */
				Integer expertNo = eppSvc.getOneExpertPerPage(postNo).getExpertNo(); // �|���s�������, �ҥH�u���¸��
				/* �K��Ϥ� */
				Part part = req.getPart("postPhoto");
				InputStream in = part.getInputStream(); // �Q��part�������Ƭy�s��byte[]
				byte[] postPhoto = null;
				if (in.available() > 0) { // ����s�Ϥ�,�ӥB��Ū�����(>0)
					postPhoto = new byte[in.available()];
					in.read(postPhoto);
					in.close();
				} else {
					postPhoto = eppSvc.getOneExpertPerPage(postNo).getPostPhoto(); // �S��s�Ϥ��N�έ쥻���Ϥ�
				}
				req.setAttribute("postPhoto", postPhoto);
				/* �K�夺�e */
				String postContent = req.getParameter("postContent");
				if (postContent == null) {
					errorMsgs.add("�п�J�K�夺�e");
				}
				/* �K��ɶ� */
				java.sql.Date postTime = eppSvc.getOneExpertPerPage(postNo).getPostTime();
				
				/* ���g�� */
				Integer numOfLike = eppSvc.getOneExpertPerPage(postNo).getNumOfLike();

				/* �K�媬�A */
				String str = req.getParameter("postState");
				Integer postState = new Integer(str);
				if (str.equals(null)) {
					postState = eppSvc.getOneExpertPerPage(postNo).getPostState();
				}

				/* ==================�غc===================== */
				ExpertPersonalPageVO eppVO = new ExpertPersonalPageVO();
				eppVO.setPostNo(postNo);
				eppVO.setExpertNo(expertNo);
				eppVO.setPostPhoto(postPhoto);
				eppVO.setPostContent(postContent);
				eppVO.setPostTime(postTime);
				eppVO.setNumOfLike(numOfLike);
				eppVO.setPostState(postState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eppVO", eppVO); // �t����J�榡���~��eppVO1����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/expertPersonalPage/update_ExpertPerPage_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.�}�l�s�W��� ***************************************/
				ExpertPersonalPageService eppSVC = new ExpertPersonalPageService();
				eppVO = eppSVC.updateExpertPerPage(postNo, expertNo, postPhoto, postContent, postTime, numOfLike,
						postState);

				/*************************** 5.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("eppVO", eppVO); // ��Ʈwupdate���\��,���T����eppVO����,�s�Jreq				
				String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllExpertPerPage.jsp)�M�ק諸�O���@��
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����ܰe�X�ק諸�ӷ�����+���������
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("��ƭק異��:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertPersonalPage/update_ExpertPerPage_input.jsp");
				failureView.forward(req, res);
			}
		}

	}

	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
