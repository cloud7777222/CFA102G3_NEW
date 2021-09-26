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

		// [ 來自select_page.jsp的"擇一postNO 看貼文"的請求 ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // 蒐集錯誤給jsp呈現
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("postNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer postNo = null;
				try {
					postNo = new Integer(str); // 給規定文章編號只有數字
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(postNo);
				if (postVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("postVO", postVO); // 資料庫取出的postVO物件,存入req
				String url = "/back_end/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功時畫面forward轉交給 listOnePost.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自addPost.jsp的"新增"請求 ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			/* 文章類別編號 */
			String ptn = req.getParameter("postTypeNo");
			if (ptn == null || (ptn.trim()).length() == 0) {
				errorMsgs.add("請輸入文章類別編號");
			}
			Integer postTypeNo = null;
			try {
				postTypeNo = new Integer(req.getParameter("postTypeNo").trim());
			} catch (Exception e) {
				errorMsgs.add("文章類別編號格式錯誤, 請填數字! ( 1:旅遊; 2:吃喝; 3:兩性關係 )");
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
				/* 文章內容 */
				String postContent = req.getParameter("postContent");
				if (postContent == null || (postContent.trim()).length() == 0) {
					errorMsgs.add("請輸入文章內容");
				}
				/* 文章發表時間 */
				java.sql.Date postTime = null;
				postTime = new java.sql.Date(System.currentTimeMillis()); // 取得當下po文系統時間
				/* 文章狀態 */
				Integer postState = 1; // 預設
				/* 文章留言數 */
				Integer mesCount = 0; // 預設
				/* 按讚數 */
				Integer numOfLike = 0; // 預設

				/* ==================建構===================== */
				PostVO postVO = new PostVO();
				postVO.setPostTypeNo(postTypeNo);
				postVO.setMemberNo(memberNo);
				postVO.setPostContent(postContent);
				postVO.setPostTime(postTime);
				postVO.setPostState(postState);
				postVO.setMesCount(mesCount);
				postVO.setNumOfLike(numOfLike);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的postVO物件,也存入req, 讓使用者不必重填一些資訊
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/addPost.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				PostService postSvc = new PostService();
				postVO = postSvc.addPost(postTypeNo, memberNo, postContent, postTime, postState, mesCount, numOfLike);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("postVO", postVO);
				String url = "/back_end/post/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listAllMemPerPage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("文章新增失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/addPost.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		// [ 來自listAllPost.jsp 的"刪除"請求]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer postNo = new Integer(req.getParameter("postNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				PostService postSvc = new PostService();
				postSvc.deletePost(postNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回listAllPost.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/listAllPost.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自listAllPost.jsp "單筆文章檢視"的請求 ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(postNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("postVO", postVO); // 資料庫取出的postVO物件,存入req
				String url = "/back_end/post/update_Post_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Post_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/listAllPost.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自update_Post_input.jsp的"修正"請求 ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁

			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 文章編號 */
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				PostService postSvc = new PostService();

				/**************************** 3.讀取client端送過來的資料 ******************************/

				/* 文章類別編號 */
				String ptn = req.getParameter("postTypeNo"); // 文章類別若沒修改就照舊
				Integer postTypeNo = new Integer(ptn);
				if (ptn == null) {
					postTypeNo = postSvc.getOnePost(postNo).getPostTypeNo();
				}
				/* 會員編號 */
				Integer memberNo = postSvc.getOnePost(postNo).getMemberNo(); // 會員編號不能改, 所以沿用舊資料

				/* 文章內容 */
				String postContent = req.getParameter("postContent");
				if (postContent == null) {
					postContent = postSvc.getOnePost(postNo).getPostContent(); // 文章內容若沒修改就照舊
				}
				/* 文章發表時間 */
				java.sql.Date postTime = postSvc.getOnePost(postNo).getPostTime();
				
				/* 文章狀態 */
				String str = req.getParameter("postState"); // 文章狀態若沒修改就照舊
				Integer postState = new Integer(str);
				if (str == null) {
					postState = postSvc.getOnePost(postNo).getPostState();
				}
				/* 留言數 */
				Integer mesCount = postSvc.getOnePost(postNo).getMesCount();
				/* 按讚數 */
				Integer numOfLike = postSvc.getOnePost(postNo).getNumOfLike();

				/* ==================建構===================== */
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
					req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的postVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/update_Post_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.開始新增資料 ***************************************/
				PostService postSVC = new PostService();
				postVO = postSVC.updatePost(postNo, postTypeNo, memberNo, postContent, postTime, postState, mesCount,
						numOfLike);

				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("postVO", postVO); // 資料庫update成功後,正確的的postVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo; // 送出修改的來源網頁的第幾頁(只用於:listAllMemPerPage.jsp)和修改的是哪一筆
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listOneMemPerPage.jsp 顯示
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/update_Post_input.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自select_page.jsp的"複合查詢"請求 ]
		if ("listEmps_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map ! 而且不能存進session scope!
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null) {
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					// map是immutable! 所以這裡new 一個HashMap 洗掉這個特性!
					// 不然下面的session.setAttribute("map",map1)會失效, 變成查全部!
					session.setAttribute("map", map1);
					map = map1;
				}

				/*************************** 2.開始複合查詢 ***************************************/
				PostService postSvc = new PostService();
				List<PostVO> list = postSvc.getAll(map);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/emp/listEmps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
