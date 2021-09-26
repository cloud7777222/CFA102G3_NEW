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

		// [ 來自select_page.jsp的"擇一mesNO 看貼文"的請求 ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // 蒐集錯誤給jsp呈現
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("mesNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer mesNo = null;
				try {
					mesNo = new Integer(str); // 給規定編號只有數字
				} catch (Exception e) {
					errorMsgs.add("文章留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PostMessageService postMessageSvc = new PostMessageService();
				PostMessageVO postMessageVO = postMessageSvc.getOnePostMessage(mesNo);
				if (postMessageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("postMessageVO", postMessageVO); // 資料庫取出的postVO物件,存入req
				String url = "/back_end/postMessage/listOnePostMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功時畫面forward轉交給 listOnePostMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自addPostMessage.jsp的"新增"請求 ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			/* 文章編號 */
			String ptn = req.getParameter("postNo");
			if (ptn == null || (ptn.trim()).length() == 0) {
				errorMsgs.add("請輸入文章編號");
			}
			Integer postNo = null;
			try {
				postNo = new Integer(req.getParameter("postNo").trim());
			} catch (Exception e) {
				errorMsgs.add("文章編號格式錯誤, 請填數字!");
			}

			/* 會員編號 */
			String str = req.getParameter("memberNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			Integer memberNo = null;
			try {
				memberNo = new Integer(req.getParameter("memberNo").trim()); // 如何自動取得登入者帳號自動帶入這裡? session
				// 有setAttribute嗎?
			} catch (Exception e) {
				errorMsgs.add("會員編號格式錯誤");
			}
			try {
				/* 文章留言內容 */
				String mesContent = req.getParameter("mesContent");
				if (mesContent == null || (mesContent.trim()).length() == 0) {
					errorMsgs.add("請輸入文章內容");
				}
				/* 文章發表時間 */
				java.sql.Date mesTime = null;
				mesTime = new java.sql.Date(System.currentTimeMillis()); // 取得當下po文系統時間
				/* 文章狀態 */
				Integer mesState = 1; // 預設
				
				/* ==================建構===================== */
				PostMessageVO postMessageVO = new PostMessageVO();
				postMessageVO.setPostNo(postNo);
				postMessageVO.setMemberNo(memberNo);
				postMessageVO.setMesContent(mesContent);
				postMessageVO.setMesTime(mesTime);
				postMessageVO.setMesState(mesState);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postMessageVO", postMessageVO); // 含有輸入格式錯誤的postVO物件,也存入req, 讓使用者不必重填一些資訊
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/addPost.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				PostMessageService postMessageSvc = new PostMessageService();
				postMessageVO = postMessageSvc.addPostMessage(postNo, memberNo, mesContent, mesTime, mesState);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("postMessageVO", postMessageVO);
				String url = "/back_end/postMessage/listAllPostMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listAllPostMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("文章新增失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/addPostMessage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		// [ 來自listAllPostMessage.jsp 的"刪除"請求]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer mesNo = new Integer(req.getParameter("mesNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				PostMessageService postMessageSvc = new PostMessageService();
				postMessageSvc.deletePostMessage(mesNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回listAllPostMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/listAllPostMessage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自listAllPostMessage.jsp "單筆文章檢視"的請求 ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				PostMessageService postMessageSvc = new PostMessageService();
				PostMessageVO postMessageVO = postMessageSvc.getOnePostMessage(mesNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("postMessageVO", postMessageVO); // 資料庫取出的postMessageVO物件,存入req
				String url = "/back_end/postMessage/update_PostMessage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PostMessage_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/listAllPostMessage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自update_PostMessage_input.jsp的"修正"請求 ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁

			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 文章編號 */
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				PostMessageService postMessageSvc = new PostMessageService();

				/**************************** 3.讀取client端送過來的資料 ******************************/

				/* 文章編號 */
				String ptn = req.getParameter("postNo"); // 文章編號理應不會修改
				Integer postNo = new Integer(ptn);
				if (ptn == null) {
					postNo = postMessageSvc.getOnePostMessage(mesNo).getPostNo();
				}
				/* 會員編號 */
				Integer memberNo = postMessageSvc.getOnePostMessage(mesNo).getMemberNo(); // 會員編號不能改變, 所以沿用舊資料

				/* 文章內容 */
				String mesContent = req.getParameter("mesContent");
				if (mesContent == null) {
					mesContent = postMessageSvc.getOnePostMessage(mesNo).getMesContent(); // 文章內容若沒修改就照舊
				}
				/* 文章發表時間 */
				java.sql.Date mesTime = postMessageSvc.getOnePostMessage(mesNo).getMesTime();
				
				/* 文章狀態 */
				String str = req.getParameter("mesState"); // 文章狀態若沒修改就照舊
				Integer mesState = new Integer(str);
				if (str == null) {
					mesState = postMessageSvc.getOnePostMessage(mesNo).getMesState();
				}			

				/* ==================建構===================== */
				PostMessageVO postMessageVO = new PostMessageVO();
				postMessageVO.setPostNo(mesNo);
				postMessageVO.setPostNo(postNo);
				postMessageVO.setMemberNo(memberNo);
				postMessageVO.setMesContent(mesContent);
				postMessageVO.setMesTime(mesTime);
				postMessageVO.setMesState(mesState);
		
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postMessageVO", postMessageVO); // 含有輸入格式錯誤的postMessageVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/update_PostMessage_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.開始新增資料 ***************************************/
				PostMessageService postMessageSVC = new PostMessageService();
				postMessageVO = postMessageSVC.updatePostMessage(mesNo, postNo, memberNo, mesContent, mesTime, mesState);

				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("postMessageVO", postMessageVO); // 資料庫update成功後,正確的的postMessageVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage + "&mesNo=" + mesNo; // 送出修改的來源網頁的第幾頁(只用於:listAllMemPerPage.jsp)和修改的是哪一筆
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listOneMemPerPage.jsp 顯示
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postMessage/update_PostMessage_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
