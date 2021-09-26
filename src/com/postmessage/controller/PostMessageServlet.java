package com.postmessage.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postmessage.model.PostMessageService;
import com.postmessage.model.PostMessageVO;

public class PostMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ �Ӧ�select_page.jsp��"�ܤ@mesNO �ݶK��"���ШD ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // �`�����~��jsp�e�{
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("mesNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�d���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				PostMessageService postMessageSvc = new PostMessageService();
				PostMessageVO postMessageVO = postMessageSvc.getOnePostMessage(mesNo);
				if (postMessageVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("postMessageVO", postMessageVO); // ��Ʈw���X��postVO����,�s�Jreq
				String url = "/back_end/postMessage/listOnePostMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\�ɵe��forward��浹 listOnePostMessage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�addPostMessage.jsp��"�s�W"�ШD ]
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

			/* �|���s�� */
			String str = req.getParameter("memberNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J�|���s��");
			}
			Integer memberNo = null;
			try {
				memberNo = new Integer(req.getParameter("memberNo").trim()); // �p��۰ʨ��o�n�J�̱b���۰ʱa�J�o��? session
				// ��setAttribute��?
			} catch (Exception e) {
				errorMsgs.add("�|���s���榡���~");
			}
			try {
				/* �峹�d�����e */
				String mesContent = req.getParameter("mesContent");
				if (mesContent == null || (mesContent.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹���e");
				}
				/* �峹�o��ɶ� */
				java.sql.Date mesTime = null;
				mesTime = new java.sql.Date(System.currentTimeMillis()); // ���o��Upo��t�ήɶ�
				/* �峹���A */
				Integer mesState = 1; // �w�]
				
				/* ==================�غc===================== */
				PostMessageVO postMessageVO = new PostMessageVO();
				postMessageVO.setPostNo(postNo);
				postMessageVO.setMemberNo(memberNo);
				postMessageVO.setMesContent(mesContent);
				postMessageVO.setMesTime(mesTime);
				postMessageVO.setMesState(mesState);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postMessageVO", postMessageVO); // �t����J�榡���~��postVO����,�]�s�Jreq, ���ϥΪ̤�������@�Ǹ�T
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/addPost.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				PostMessageService postMessageSvc = new PostMessageService();
				postMessageVO = postMessageSvc.addPostMessage(postNo, memberNo, mesContent, mesTime, mesState);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("postMessageVO", postMessageVO);
				String url = "/back_end/postMessage/listAllPostMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllPostMessage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�峹�s�W����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/addPostMessage.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
		}

		// [ �Ӧ�listAllPostMessage.jsp ��"�R��"�ШD]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer mesNo = new Integer(req.getParameter("mesNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				PostMessageService postMessageSvc = new PostMessageService();
				postMessageSvc.deletePostMessage(mesNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^listAllPostMessage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/listAllPostMessage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�listAllPostMessage.jsp "�浧�峹�˵�"���ШD ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PostMessageService postMessageSvc = new PostMessageService();
				PostMessageVO postMessageVO = postMessageSvc.getOnePostMessage(mesNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("postMessageVO", postMessageVO); // ��Ʈw���X��postMessageVO����,�s�Jreq
				String url = "/back_end/postMessage/update_PostMessage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_PostMessage_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/listAllPostMessage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�update_PostMessage_input.jsp��"�ץ�"�ШD ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ�����
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �峹�s�� */
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PostMessageService postMessageSvc = new PostMessageService();

				/**************************** 3.Ū��client�ݰe�L�Ӫ���� ******************************/

				/* �峹�s�� */
				String ptn = req.getParameter("postNo"); // �峹�s���z�����|�ק�
				Integer postNo = new Integer(ptn);
				if (ptn == null) {
					postNo = postMessageSvc.getOnePostMessage(mesNo).getPostNo();
				}
				/* �|���s�� */
				Integer memberNo = postMessageSvc.getOnePostMessage(mesNo).getMemberNo(); // �|���s���������, �ҥH�u���¸��

				/* �峹���e */
				String mesContent = req.getParameter("mesContent");
				if (mesContent == null) {
					mesContent = postMessageSvc.getOnePostMessage(mesNo).getMesContent(); // �峹���e�Y�S�ק�N����
				}
				/* �峹�o��ɶ� */
				java.sql.Date mesTime = postMessageSvc.getOnePostMessage(mesNo).getMesTime();
				
				/* �峹���A */
				String str = req.getParameter("mesState"); // �峹���A�Y�S�ק�N����
				Integer mesState = new Integer(str);
				if (str == null) {
					mesState = postMessageSvc.getOnePostMessage(mesNo).getMesState();
				}			

				/* ==================�غc===================== */
				PostMessageVO postMessageVO = new PostMessageVO();
				postMessageVO.setPostNo(mesNo);
				postMessageVO.setPostNo(postNo);
				postMessageVO.setMemberNo(memberNo);
				postMessageVO.setMesContent(mesContent);
				postMessageVO.setMesTime(mesTime);
				postMessageVO.setMesState(mesState);
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postMessageVO", postMessageVO); // �t����J�榡���~��postMessageVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/update_PostMessage_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.�}�l�s�W��� ***************************************/
				PostMessageService postMessageSVC = new PostMessageService();
				postMessageVO = postMessageSVC.updatePostMessage(mesNo, postNo, memberNo, mesContent, mesTime, mesState);

				/*************************** 5.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("postMessageVO", postMessageVO); // ��Ʈwupdate���\��,���T����postMessageVO����,�s�Jreq
				String url = requestURL + "?whichPage=" + whichPage + "&mesNo=" + mesNo; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllMemPerPage.jsp)�M�ק諸�O���@��
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listOneMemPerPage.jsp ���
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("��ƭק異��:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/update_PostMessage_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
