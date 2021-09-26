package com.posttype.controller;

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
import com.posttype.model.PostTypeService;
import com.posttype.model.PostTypeVO;

public class PostTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ �Ӧ�select_page.jsp��"�ܤ@postTypeNO �ݤ峹���O"���ШD ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // �`�����~��jsp�e�{
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("postTypeNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer postTypeNo = null;
				try {
					postTypeNo = new Integer(str); // ���W�w�峹�s���u���Ʀr
				} catch (Exception e) {
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				PostTypeVO postTypeVO = postTypeSvc.getOnePostType(postTypeNo);
				if (postTypeNo == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("postTypeVO", postTypeVO); // ��Ʈw���X��postVO����,�s�Jreq
				String url = "/back_end/postType/listOnePostType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\�ɵe��forward��浹 listOnePost.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�addPostType.jsp��"�s�W"�ШD ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				/* �峹���O�s�� */
				String postType = req.getParameter("postType");
				if (postType == null || (postType.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹���O");
				}
				/* ==================�غc===================== */
				PostTypeVO postTypeVO = new PostTypeVO();
				postTypeVO.setPostType(postType);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postTypeVO", postTypeVO); // �t����J�榡���~��postTypeVO����,�]�s�Jreq, ���ϥΪ̤�������@�Ǹ�T
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/addPostType.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�s�W��� ***************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				postTypeVO = postTypeSvc.addPostType(postType);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("postTypeVO", postTypeVO);
				String url = "/back_end/postType/listAllPostType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllPostType.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�峹���O�s�W����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/addPostType.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
		}

		// [ �Ӧ�listAllPostType.jsp ��"�R��"�ШD]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer postTypeNo = new Integer(req.getParameter("postTypeNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				postTypeSvc.deletePostType(postTypeNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^listAllPostType.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/listAllPostType.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�listAllPostType.jsp "�浧�峹�˵�"���ШD ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer postTypeNo = new Integer(req.getParameter("postTypeNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				PostTypeVO postTypeVO = postTypeSvc.getOnePostType(postTypeNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("postTypeVO", postTypeVO); // ��Ʈw���X��postTypeVO����,�s�Jreq
				String url = "/back_end/postType/update_PostType_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_PostType_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/listAllPostType.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�update_PostType_input.jsp��"�ץ�"�ШD ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ�����
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �峹�s�� */
				Integer postTypeNo = new Integer(req.getParameter("postTypeNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PostTypeService postTypeSvc = new PostTypeService();

				/**************************** 3.Ū��client�ݰe�L�Ӫ���� ******************************/

				/* �峹���O */
				String postType = req.getParameter("postType"); // �峹���O�Y�S�ק�N����
				if (postType == null) {
					postType = postTypeSvc.getOnePostType(postTypeNo).getPostType();
				}
				
				/* ==================�غc===================== */
				PostTypeVO postTypeVO = new PostTypeVO();
				postTypeVO.setPostTypeNo(postTypeNo);
				postTypeVO.setPostType(postType);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postTypeVO", postTypeVO); // �t����J�榡���~��postTypeVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/update_PostType_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.�}�l�s�W��� ***************************************/
				PostTypeService postTypeSVC = new PostTypeService();
				postTypeVO = postTypeSVC.updatePostType(postTypeNo, postType);

				/*************************** 5.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("postTypeVO", postTypeVO); // ��Ʈwupdate���\��,���T����postTypeVO����,�s�Jreq
				String url = requestURL + "?whichPage=" + whichPage + "&postTypeNo=" + postTypeNo; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllPostType.jsp)�M�ק諸�O���@��
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllPostType.jsp ���
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("��ƭק異��:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/update_PostType_input.jsp");
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
				PostTypeService postTypeSvc = new PostTypeService();
				List<PostTypeVO> list = postTypeSvc.getAll(map);

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
