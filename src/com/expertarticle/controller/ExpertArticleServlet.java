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
		Integer articleNo = new Integer(req.getParameter("articleNo")); // 抓<img src=" ?pk=yyy"> pk的參數值
		ExpertArticleService eaSvc = new ExpertArticleService();
		ServletOutputStream sout = res.getOutputStream(); // 輸出資料流
		byte[] buf = eaSvc.getOneExpertArticle(articleNo).getArticlePhoto();
		sout.write(buf); // 輸出二位元資料
		sout.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ 來自select_page.jsp的"擇一articleNo 看專家專欄"的請求 ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("articleNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入專家專欄編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertArticle/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer articleNo = null;
				try {
					articleNo = new Integer(str); // 給規定專家專欄編號只有數字
				} catch (Exception e) {
					errorMsgs.add("專家專欄編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertArticle/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ExpertArticleService eppSvc = new ExpertArticleService();
				ExpertArticleVO eaVO = eppSvc.getOneExpertArticle(articleNo);
				if (eaVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertArticle/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("eaVO", eaVO); // 資料庫取出的eppVO物件,存入req
				String url = "/back_end/expertArticle/listOneExpertArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功時畫面forward轉交給listOneExpertArticle.jsp																			
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/expertArticle/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自addExpertArticle.jsp的"新增"請求 ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			/* 專家編號 */
			String str = req.getParameter("expertNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入專家編號");
			}
			Integer expertNo = null;
			try {
				expertNo = new Integer(req.getParameter("expertNo").trim()); // 如何自動取得登入者帳號自動帶入這裡? session
				// 有setAttribute嗎?
			} catch (Exception e) {
				errorMsgs.add("專家編號格式錯誤");
			}

			try {
				/* 專家專欄內容 */
				String articleContent = req.getParameter("articleContent");
				if (articleContent == null || (articleContent.trim()).length() == 0) {
					errorMsgs.add("請輸入專家專欄內容");
				}
				/* 專家專欄圖片 */
				res.setContentType("text/html; charset=UTF-8");
				Part part = req.getPart("articlePhoto");
				String filename = getFileNameFromPart(part);

				// 將image送進DB儲存
				InputStream in = part.getInputStream();
				byte[] articlePhoto = new byte[in.available()]; // 利用setBytes 要送進DB
				in.read(articlePhoto);
				in.close();
				req.setAttribute("articlePhoto", articlePhoto);
				if (filename == null || (filename.trim()).length() == 0) {
					errorMsgs.add("請選擇上傳圖片");
				}
				/* 專家專欄發表時間 */
				java.sql.Date articleTime = null;
				articleTime = new java.sql.Date(System.currentTimeMillis()); // 取得當下po文系統時間
				/* 專家專欄發表狀態 */
				Integer articleState = 1;				
				/* 按讚數 */
				Integer numOfLike = 0;

				/* ==================建構===================== */
				ExpertArticleVO eaVO = new ExpertArticleVO();
				eaVO.setExpertNo(expertNo);				
				eaVO.setArticleContent(articleContent);
				eaVO.setArticlePhoto(articlePhoto);
				eaVO.setArticleTime(articleTime);				
				eaVO.setArticleState(articleState);
				eaVO.setNumOfLike(numOfLike);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eaVO", eaVO); // 含有輸入格式錯誤的eaVO物件,也存入req, 讓使用者不必重填一些資訊
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/expertArticle/addExpertArticle.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ExpertArticleService eaSvc = new ExpertArticleService();
				eaVO = eaSvc.addExpertArticle(expertNo, articleContent, articlePhoto, articleTime, articleState, numOfLike);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("eaVO", eaVO);
				String url = "/back_end/expertArticle/listAllExpertArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listAllExpertArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("貼文新增失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertArticle/addExpertArticle.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		// [ 來自listAllExpertArticle.jsp 的"刪除"請求]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer articleNo = new Integer(req.getParameter("articleNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				ExpertArticleService eaSvc = new ExpertArticleService();
				eaSvc.deleteExpertArticle(articleNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回listAllExpertArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertArticle/listAllExpertArticle.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自listAllExpertArticle.jsp "單筆貼文檢視"的請求 ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer articleNo = new Integer(req.getParameter("articleNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				ExpertArticleService eaSvc = new ExpertArticleService();
				ExpertArticleVO eaVO = eaSvc.getOneExpertArticle(articleNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("eaVO", eaVO); // 資料庫取出的eaVO物件,存入req
				String url = "/back_end/expertArticle/update_ExpertArticle_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ExpertArticle_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertArticle/listAllExpertArticle.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自update_ExpertArticle_input.jsp的"修正"請求 ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁

			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 專家專欄編號 */
				Integer articleNo = new Integer(req.getParameter("articleNo").trim());

				/**************************** 2.預備連結查詢DB資料 ***************************************/
				ExpertArticleService eaSvc = new ExpertArticleService();

				/**************************** 3.讀取client端送過來的資料 ******************************/
				/* 專家編號 */
				Integer expertNo = eaSvc.getOneExpertArticle(articleNo).getExpertNo(); // 會員編號不能改, 所以沿用舊資料
				/* 專家專欄圖片 */
				Part part = req.getPart("articlePhoto");
				InputStream in = part.getInputStream(); // 利用part物件轉資料流存成byte[]
				byte[] articlePhoto = null;
				if (in.available() > 0) { // 有更新圖片,而且有讀取到時(>0)
					articlePhoto = new byte[in.available()];
					in.read(articlePhoto);
					in.close();
				} else {
					articlePhoto = eaSvc.getOneExpertArticle(articleNo).getArticlePhoto(); // 沒更新圖片就用原本的圖片
				}
				req.setAttribute("articlePhoto", articlePhoto);
				/* 專家專欄內容 */
				String articleContent = req.getParameter("articleContent");
				if (articleContent == null) {
					errorMsgs.add("請輸入專家專欄內容");
				}
				/* 專家專欄發表時間 */
				java.sql.Date articleTime = eaSvc.getOneExpertArticle(articleNo).getArticleTime();	//照舊			
				/* 專家專欄發表狀態 */
				String str = req.getParameter("articleState");
				Integer articleState = new Integer(str);
				if (str.equals(null)) {
					articleState = eaSvc.getOneExpertArticle(articleNo).getArticleState();
				}
				/* 按讚數 */
				Integer numOfLike = eaSvc.getOneExpertArticle(articleNo).getNumOfLike();


				/* ==================建構===================== */
				ExpertArticleVO eaVO  = new ExpertArticleVO();
				eaVO.setArticleNo(articleNo);
				eaVO.setExpertNo(expertNo);		
				eaVO.setArticleContent(articleContent);
				eaVO.setArticlePhoto(articlePhoto);
				eaVO.setArticleTime(articleTime);			
				eaVO.setArticleState(articleState);
				eaVO.setNumOfLike(numOfLike);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eaVO", eaVO); // 含有輸入格式錯誤的eaVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/expertArticle/update_ExpertArticle_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.開始新增資料 ***************************************/
				ExpertArticleService eaSVC = new ExpertArticleService();
				eaVO = eaSVC.updateExpertArticle(articleNo, expertNo, articleContent, articlePhoto, articleTime, articleState, numOfLike);

				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("eaVO", eaVO); // 資料庫update成功後,正確的的eaVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage + "&articleNo=" + articleNo; // 送出修改的來源網頁的第幾頁(只用於:listAllExpertPerPage.jsp)和修改的是哪一筆
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至送出修改的來源網頁+源頁數顯示
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/expertArticle/update_ExpertArticle_input.jsp");
				failureView.forward(req, res);
			}
		}

	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
