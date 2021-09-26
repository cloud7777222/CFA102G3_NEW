package com.post.controller;

import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;
import com.post.model.PostService;
import com.post.model.PostVO;

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ �Ӧ�select_page.jsp��"�ܤ@postNO �ݶK��"���ШD ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // �`�����~��jsp�e�{
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("postNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(postNo);
				if (postVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("postVO", postVO); // ��Ʈw���X��postVO����,�s�Jreq
				String url = "/back_end/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\�ɵe��forward��浹 listOnePost.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�addPost.jsp��"�s�W"�ШD ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			/* �峹���O�s�� */
			String ptn = req.getParameter("postTypeNo");
			if (ptn == null || (ptn.trim()).length() == 0) {
				errorMsgs.add("�п�J�峹���O�s��");
			}
			Integer postTypeNo = null;
			try {
				postTypeNo = new Integer(req.getParameter("postTypeNo").trim());
			} catch (Exception e) {
				errorMsgs.add("�峹���O�s���榡���~, �ж�Ʀr! ( 1:�ȹC; 2:�Y��; 3:������Y )");
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
				/* �峹���e */
				String postContent = req.getParameter("postContent");
				if (postContent == null || (postContent.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹���e");
				}
				/* �峹�o��ɶ� */
				java.sql.Date postTime = null;
				postTime = new java.sql.Date(System.currentTimeMillis()); // ���o��Upo��t�ήɶ�
				/* �峹���A */
				Integer postState = 1; // �w�]
				/* �峹�d���� */
				Integer mesCount = 0; // �w�]
				/* ���g�� */
				Integer numOfLike = 0; // �w�]

				/* ==================�غc===================== */
				PostVO postVO = new PostVO();
				postVO.setPostTypeNo(postTypeNo);
				postVO.setMemberNo(memberNo);
				postVO.setPostContent(postContent);
				postVO.setPostTime(postTime);
				postVO.setPostState(postState);
				postVO.setMesCount(mesCount);
				postVO.setNumOfLike(numOfLike);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO); // �t����J�榡���~��postVO����,�]�s�Jreq, ���ϥΪ̤�������@�Ǹ�T
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/addPost.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				PostService postSvc = new PostService();
				postVO = postSvc.addPost(postTypeNo, memberNo, postContent, postTime, postState, mesCount, numOfLike);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("postVO", postVO);
				String url = "/back_end/post/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllMemPerPage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�峹�s�W����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/addPost.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
		}

		// [ �Ӧ�listAllPost.jsp ��"�R��"�ШD]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer postNo = new Integer(req.getParameter("postNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				PostService postSvc = new PostService();
				postSvc.deletePost(postNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^listAllPost.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/listAllPost.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�listAllPost.jsp "�浧�峹�˵�"���ШD ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(postNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("postVO", postVO); // ��Ʈw���X��postVO����,�s�Jreq
				String url = "/back_end/post/update_Post_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_Post_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/listAllPost.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�update_Post_input.jsp��"�ץ�"�ШD ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ�����
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �峹�s�� */
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PostService postSvc = new PostService();

				/**************************** 3.Ū��client�ݰe�L�Ӫ���� ******************************/

				/* �峹���O�s�� */
				String ptn = req.getParameter("postTypeNo"); // �峹���O�Y�S�ק�N����
				Integer postTypeNo = new Integer(ptn);
				if (ptn == null) {
					postTypeNo = postSvc.getOnePost(postNo).getPostTypeNo();
				}
				/* �|���s�� */
				Integer memberNo = postSvc.getOnePost(postNo).getMemberNo(); // �|���s�������, �ҥH�u���¸��

				/* �峹���e */
				String postContent = req.getParameter("postContent");
				if (postContent == null) {
					postContent = postSvc.getOnePost(postNo).getPostContent(); // �峹���e�Y�S�ק�N����
				}
				/* �峹�o��ɶ� */
				java.sql.Date postTime = postSvc.getOnePost(postNo).getPostTime();
				
				/* �峹���A */
				String str = req.getParameter("postState"); // �峹���A�Y�S�ק�N����
				Integer postState = new Integer(str);
				if (str == null) {
					postState = postSvc.getOnePost(postNo).getPostState();
				}
				/* �d���� */
				Integer mesCount = postSvc.getOnePost(postNo).getMesCount();
				/* ���g�� */
				Integer numOfLike = postSvc.getOnePost(postNo).getNumOfLike();

				/* ==================�غc===================== */
				PostVO postVO = new PostVO();
				postVO.setPostNo(postNo);
				postVO.setPostTypeNo(postTypeNo);
				postVO.setMemberNo(memberNo);
				postVO.setPostContent(postContent);
				postVO.setPostTime(postTime);
				postVO.setPostState(postState);
				postVO.setMesCount(mesCount);
				postVO.setNumOfLike(numOfLike);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO); // �t����J�榡���~��postVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/update_Post_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.�}�l�s�W��� ***************************************/
				PostService postSVC = new PostService();
				postVO = postSVC.updatePost(postNo, postTypeNo, memberNo, postContent, postTime, postState, mesCount,
						numOfLike);

				/*************************** 5.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("postVO", postVO); // ��Ʈwupdate���\��,���T����postVO����,�s�Jreq
				String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllMemPerPage.jsp)�M�ק諸�O���@��
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listOneMemPerPage.jsp ���
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("��ƭק異��:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/update_Post_input.jsp");
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
				PostService postSvc = new PostService();
				List<PostVO> list = postSvc.getAll(map);

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
