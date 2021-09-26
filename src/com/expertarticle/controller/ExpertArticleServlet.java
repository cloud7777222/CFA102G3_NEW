package com.expertarticle.controller;

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
import com.expertarticle.model.ExpertArticleService;
import com.expertarticle.model.ExpertArticleVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ExpertArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");
		Integer articleNo = new Integer(req.getParameter("articleNo")); // ��<img src=" ?pk=yyy"> pk���Ѽƭ�
		ExpertArticleService eaSvc = new ExpertArticleService();
		ServletOutputStream sout = res.getOutputStream(); // ��X��Ƭy
		byte[] buf = eaSvc.getOneExpertArticle(articleNo).getArticlePhoto();
		sout.write(buf); // ��X�G�줸���
		sout.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ �Ӧ�select_page.jsp��"�ܤ@articleNo �ݱM�a�M��"���ШD ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("articleNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�M�a�M��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertArticle/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer articleNo = null;
				try {
					articleNo = new Integer(str); // ���W�w�M�a�M��s���u���Ʀr
				} catch (Exception e) {
					errorMsgs.add("�M�a�M��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertArticle/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ExpertArticleService eppSvc = new ExpertArticleService();
				ExpertArticleVO eaVO = eppSvc.getOneExpertArticle(articleNo);
				if (eaVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertArticle/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("eaVO", eaVO); // ��Ʈw���X��eppVO����,�s�Jreq
				String url = "/back_end/expertArticle/listOneExpertArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\�ɵe��forward��浹listOneExpertArticle.jsp																			
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertArticle/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�addExpertArticle.jsp��"�s�W"�ШD ]
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

			try {
				/* �M�a�M�椺�e */
				String articleContent = req.getParameter("articleContent");
				if (articleContent == null || (articleContent.trim()).length() == 0) {
					errorMsgs.add("�п�J�M�a�M�椺�e");
				}
				/* �M�a�M��Ϥ� */
				res.setContentType("text/html; charset=UTF-8");
				Part part = req.getPart("articlePhoto");
				String filename = getFileNameFromPart(part);

				// �Nimage�e�iDB�x�s
				InputStream in = part.getInputStream();
				byte[] articlePhoto = new byte[in.available()]; // �Q��setBytes �n�e�iDB
				in.read(articlePhoto);
				in.close();
				req.setAttribute("articlePhoto", articlePhoto);
				if (filename == null || (filename.trim()).length() == 0) {
					errorMsgs.add("�п�ܤW�ǹϤ�");
				}
				/* �M�a�M��o��ɶ� */
				java.sql.Date articleTime = null;
				articleTime = new java.sql.Date(System.currentTimeMillis()); // ���o��Upo��t�ήɶ�
				/* �M�a�M��o���A */
				Integer articleState = 1;				
				/* ���g�� */
				Integer numOfLike = 0;

				/* ==================�غc===================== */
				ExpertArticleVO eaVO = new ExpertArticleVO();
				eaVO.setExpertNo(expertNo);				
				eaVO.setArticleContent(articleContent);
				eaVO.setArticlePhoto(articlePhoto);
				eaVO.setArticleTime(articleTime);				
				eaVO.setArticleState(articleState);
				eaVO.setNumOfLike(numOfLike);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eaVO", eaVO); // �t����J�榡���~��eaVO����,�]�s�Jreq, ���ϥΪ̤�������@�Ǹ�T
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/expertArticle/addExpertArticle.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ExpertArticleService eaSvc = new ExpertArticleService();
				eaVO = eaSvc.addExpertArticle(expertNo, articleContent, articlePhoto, articleTime, articleState, numOfLike);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("eaVO", eaVO);
				String url = "/back_end/expertArticle/listAllExpertArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������listAllExpertArticle.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�K��s�W����:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertArticle/addExpertArticle.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
		}

		// [ �Ӧ�listAllExpertArticle.jsp ��"�R��"�ШD]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer articleNo = new Integer(req.getParameter("articleNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				ExpertArticleService eaSvc = new ExpertArticleService();
				eaSvc.deleteExpertArticle(articleNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL;
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^listAllExpertArticle.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertArticle/listAllExpertArticle.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�listAllExpertArticle.jsp "�浧�K���˵�"���ШD ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer articleNo = new Integer(req.getParameter("articleNo").trim());

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ExpertArticleService eaSvc = new ExpertArticleService();
				ExpertArticleVO eaVO = eaSvc.getOneExpertArticle(articleNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("eaVO", eaVO); // ��Ʈw���X��eaVO����,�s�Jreq
				String url = "/back_end/expertArticle/update_ExpertArticle_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_ExpertArticle_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertArticle/listAllExpertArticle.jsp");
				failureView.forward(req, res);
			}
		}

		// [ �Ӧ�update_ExpertArticle_input.jsp��"�ץ�"�ШD ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ�����
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* �M�a�M��s�� */
				Integer articleNo = new Integer(req.getParameter("articleNo").trim());

				/**************************** 2.�w�Ƴs���d��DB��� ***************************************/
				ExpertArticleService eaSvc = new ExpertArticleService();

				/**************************** 3.Ū��client�ݰe�L�Ӫ���� ******************************/
				/* �M�a�s�� */
				Integer expertNo = eaSvc.getOneExpertArticle(articleNo).getExpertNo(); // �|���s�������, �ҥH�u���¸��
				/* �M�a�M��Ϥ� */
				Part part = req.getPart("articlePhoto");
				InputStream in = part.getInputStream(); // �Q��part�������Ƭy�s��byte[]
				byte[] articlePhoto = null;
				if (in.available() > 0) { // ����s�Ϥ�,�ӥB��Ū�����(>0)
					articlePhoto = new byte[in.available()];
					in.read(articlePhoto);
					in.close();
				} else {
					articlePhoto = eaSvc.getOneExpertArticle(articleNo).getArticlePhoto(); // �S��s�Ϥ��N�έ쥻���Ϥ�
				}
				req.setAttribute("articlePhoto", articlePhoto);
				/* �M�a�M�椺�e */
				String articleContent = req.getParameter("articleContent");
				if (articleContent == null) {
					errorMsgs.add("�п�J�M�a�M�椺�e");
				}
				/* �M�a�M��o��ɶ� */
				java.sql.Date articleTime = eaSvc.getOneExpertArticle(articleNo).getArticleTime();	//����			
				/* �M�a�M��o���A */
				String str = req.getParameter("articleState");
				Integer articleState = new Integer(str);
				if (str.equals(null)) {
					articleState = eaSvc.getOneExpertArticle(articleNo).getArticleState();
				}
				/* ���g�� */
				Integer numOfLike = eaSvc.getOneExpertArticle(articleNo).getNumOfLike();


				/* ==================�غc===================== */
				ExpertArticleVO eaVO  = new ExpertArticleVO();
				eaVO.setArticleNo(articleNo);
				eaVO.setExpertNo(expertNo);		
				eaVO.setArticleContent(articleContent);
				eaVO.setArticlePhoto(articlePhoto);
				eaVO.setArticleTime(articleTime);			
				eaVO.setArticleState(articleState);
				eaVO.setNumOfLike(numOfLike);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eaVO", eaVO); // �t����J�榡���~��eaVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/expertArticle/update_ExpertArticle_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.�}�l�s�W��� ***************************************/
				ExpertArticleService eaSVC = new ExpertArticleService();
				eaVO = eaSVC.updateExpertArticle(articleNo, expertNo, articleContent, articlePhoto, articleTime, articleState, numOfLike);

				/*************************** 5.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("eaVO", eaVO); // ��Ʈwupdate���\��,���T����eaVO����,�s�Jreq
				String url = requestURL + "?whichPage=" + whichPage + "&articleNo=" + articleNo; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllExpertPerPage.jsp)�M�ק諸�O���@��
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����ܰe�X�ק諸�ӷ�����+���������
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("��ƭק異��:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertArticle/update_ExpertArticle_input.jsp");
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
