package com.mempersonalpage.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
import com.mempersonalpage.model.MemPersonalPageService;
import com.mempersonalpage.model.MemPersonalPageVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemPersonalPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String uploadDirectory = "/images_uploaded"; // �W���ɮת��ت��a�ؿ�;
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");
		Integer postNo = new Integer(req.getParameter("postNo")); // ��<img src=" ?pk=yyy"> pk���Ѽƭ�
		MemPersonalPageService mppSvc = new MemPersonalPageService();
		ServletOutputStream sout = res.getOutputStream(); // ��X��Ƭy
		byte[] buf = mppSvc.getOneMemPerPage(postNo).getPostPhoto();
		sout.write(buf); // ��X�G�줸���
		sout.close();

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ �Ӧ�select_page.jsp��"�ܤ@postNO �ݶK��"���ШD ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // Store this set in the request scope, in case we need
																// to send the ErrorPage view.
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
							.getRequestDispatcher("/back_end/memPersonalPage/select_page.jsp");
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
							.getRequestDispatcher("/back_end/memPersonalPage/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				MemPersonalPageVO mppVO = mppSvc.getOneMemPerPage(postNo);
				if (mppVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/memPersonalPage/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("mppVO", mppVO); // ��Ʈw���X��mppVO����,�s�Jreq
				String url = "/back_end/memPersonalPage/listOneMemPerPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\�ɵe��forward��浹 listOneMemPerPage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/memPersonalPage/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�addMemPerPage.jsp��"�s�W"�ШD ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

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
				/* �K��Ϥ� */
				res.setContentType("text/html; charset=UTF-8");
				Part part = req.getPart("postPhoto");
				String filename = getFileNameFromPart(part);

//				 //�s�i�M�ת��Ƹ�Ƨ� (getServletContext() �M�ץ���; getRealPath(uploadDirectory) ���Ƹ��|)
//				PrintWriter out = res.getWriter();
//				String realPath = getServletContext().getRealPath(uploadDirectory); 
//				File fsaveDirectory = new File(realPath);
//				if (!fsaveDirectory.exists())
//					fsaveDirectory.mkdirs(); // �� ContextPath ���U,�۰ʫإߥئa�ؿ�				
//				File f = new File(fsaveDirectory, filename);
//				out.println("filename: " + filename);
//				part.write(f.toString());// �Q��File����,�g�J�ئa�ؿ�,�W�Ǧ��\
//				 out.println("<br><img src=\"" + req.getContextPath() + uploadDirectory + "/"
//				 + filename + "\">");// �ʺA���|�B�~���ըq��

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
				MemPersonalPageVO mppVO = new MemPersonalPageVO();
				mppVO.setMemberNo(memberNo);
				mppVO.setPostPhoto(postPhoto);
				mppVO.setPostContent(postContent);
				mppVO.setPostTime(postTime);
				mppVO.setNumOfLike(numOfLike);
				mppVO.setPostState(postState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mppVO", mppVO); // �t����J�榡���~��eppVO����,�]�s�Jreq, ���ϥΪ̤�������@�Ǹ�T
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/memPersonalPage/addMemPerPage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				mppVO = mppSvc.addMemPerPage(memberNo, postPhoto, postContent, postTime, numOfLike, postState);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("mppVO", mppVO);
				String url = "/back_end/memPersonalPage/listAllMemPerPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllMemPerPage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�K��s�W����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/memPersonalPage/addMemPerPage.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
		}

		// [ �Ӧ�listAllMemPerPage.jsp ��"�R��"�ШD]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer postNo = new Integer(req.getParameter("postNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				mppSvc.deleteMemPerPage(postNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/back_end/memPersonalPage/listAllMemPerPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^listAllMemPerPage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/memPersonalPage/listAllMemPerPage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�listAllMemPerPage.jsp "�浧�K���˵�"���ШD ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				MemPersonalPageVO mppVO = mppSvc.getOneMemPerPage(postNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("mppVO", mppVO); // ��Ʈw���X��mppVO����,�s�Jreq
				String url = "/back_end/memPersonalPage/update_MemPerPage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_MemPerPage_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/memPersonalPage/listAllMemPerPage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�update_MemPerPage_input.jsp��"�ץ�"�ШD ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ�����
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �K��s�� */
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();

				/**************************** 3.Ū��client�ݰe�L�Ӫ���� ******************************/

				/* �|���s�� */
				Integer memberNo = mppSvc.getOneMemPerPage(postNo).getMemberNo(); // �|���s�������, �ҥH�u���¸��

				/* �K��Ϥ� */
				Part part = req.getPart("postPhoto");
				InputStream in = part.getInputStream(); // �Q��part�������Ƭy�s��byte[]
				byte[] postPhoto = null;
				if (in.available() > 0) { // ����s�Ϥ�,�ӥB��Ū�����(>0)
					postPhoto = new byte[in.available()];
					in.read(postPhoto);
					in.close();
				} else {
					postPhoto = mppSvc.getOneMemPerPage(postNo).getPostPhoto(); // �S��s�Ϥ��N�έ쥻���Ϥ�
				}
				req.setAttribute("postPhoto", postPhoto);

				/* �K�夺�e */
				String postContent = req.getParameter("postContent");
				if (postContent == null) {
					errorMsgs.add("�п�J�K�夺�e");
				}

				/* �K��ɶ� */
				java.sql.Date postTime = mppSvc.getOneMemPerPage(postNo).getPostTime();
			
				/* ���g�� */
				Integer numOfLike = mppSvc.getOneMemPerPage(postNo).getNumOfLike();

				/* �K�媬�A */
				String str = req.getParameter("postState");
				Integer postState = new Integer(str);
				if (str.equals(null)) {
					postState = mppSvc.getOneMemPerPage(postNo).getPostState();
				}

				/* ==================�غc===================== */
				MemPersonalPageVO mppVO = new MemPersonalPageVO();
				mppVO.setPostNo(postNo);
				mppVO.setMemberNo(memberNo);
				mppVO.setPostPhoto(postPhoto);
				mppVO.setPostContent(postContent);
				mppVO.setPostTime(postTime);
				mppVO.setNumOfLike(numOfLike);
				mppVO.setPostState(postState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mppVO", mppVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/memPersonalPage/update_MemPerPage_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.�}�l�s�W��� ***************************************/
				MemPersonalPageService mppSVC = new MemPersonalPageService();
				mppVO = mppSVC.updateMemPerPage(postNo, memberNo, postPhoto, postContent, postTime, numOfLike,
						postState);

				/*************************** 5.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("mppVO", mppVO); // ��Ʈwupdate���\��,���T����mppVO����,�s�Jreq
				String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllMemPerPage.jsp)�M�ק諸�O���@��
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listOneMemPerPage.jsp ���
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("��ƭק異��:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/memPersonalPage/update_MemPerPage_input.jsp");
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
