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

		// [ 來自select_page.jsp的"擇一postNO 看文章檢舉"的請求 ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // 蒐集錯誤給jsp呈現
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				//文章編號
				String str = req.getParameter("postNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				//檢舉人會員編號
				String mNo = req.getParameter("memberNo");
				if (mNo == null || (mNo.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉人會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer memberNo = null;
				try {
					memberNo = new Integer(mNo); // 給規定編號只有數字
				} catch (Exception e) {
					errorMsgs.add("檢舉人會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PostReportService postReportSvc = new PostReportService();
				PostReportVO postReportVO = postReportSvc.getOnePostReport(postNo, memberNo);
				if (postReportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("postReportVO", postReportVO); // 資料庫取出的postReportVO物件,存入req
				String url = "/back_end/postReport/listOnePostReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功時畫面forward轉交給 listOnePostReport.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

		// [ 來自addPostReport.jsp的"新增"請求 ]
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

			/* 檢舉人會員編號 */
			String str = req.getParameter("memberNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入檢舉人會員編號");
			}
			Integer memberNo = null;
			try {
				memberNo = new Integer(req.getParameter("memberNo").trim()); 
			} catch (Exception e) {
				errorMsgs.add("檢舉人會員編號格式錯誤, 請填數字!");
			}
			/* 文章檢舉時間 */
			java.sql.Date postRepTime = null;
			postRepTime = new java.sql.Date(System.currentTimeMillis()); // 取得當下po文系統時間
			try {
				/* 文章檢舉原因 */
				String postRepFor = req.getParameter("postRepFor");
				if (postRepFor == null || (postRepFor.trim()).length() == 0) {
					errorMsgs.add("請輸入文章檢舉原因");
				}				
			/* 文章檢舉狀態 */
			Integer postRevState = 1; // (1:待審核 / 2:審核通過 / 3:審核未通過 / 預設:1)

				/* ==================建構===================== */
				PostReportVO postReportVO = new PostReportVO();
				postReportVO.setPostNo(postNo);
				postReportVO.setMemberNo(memberNo);
				postReportVO.setPostRepFor(postRepFor);
				postReportVO.setPostRepTime(postRepTime);
				postReportVO.setPostRevState(postRevState);
		;

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postReportVO", postReportVO); // 含有輸入格式錯誤的postReportVO物件,也存入req, 讓使用者不必重填一些資訊
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/addPostReport.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				PostReportService postReportSvc = new PostReportService();
				postReportVO = postReportSvc.addPostReport(postNo, memberNo, postRepTime, postRepFor, postRevState);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("postReportVO", postReportVO);
				String url = "/back_end/postReport/listAllPostReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listAllPostReport.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("文章新增失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/addPostReport.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		// [ 來自listAllPostReport.jsp 的"刪除"請求]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ***************************************/
				/* 複合PK:文章編號+檢舉人會員編號 */
				Integer postNo = new Integer(req.getParameter("postNo").trim());
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());

				/*************************** 2.開始刪除資料 ***************************************/
				PostReportService postReportSvc = new PostReportService();
				postReportSvc.deletePostReport(postNo, memberNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回listAllPostReport.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/listAllPostReport.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自listAllPostReport.jsp "單筆文章檢視"的請求 ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 複合PK:文章編號+檢舉人會員編號 */
				Integer postNo = new Integer(req.getParameter("postNo").trim());
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				PostReportService postReportSvc = new PostReportService();
				PostReportVO postReportVO = postReportSvc.getOnePostReport(postNo, memberNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("postReportVO", postReportVO); // 資料庫取出的postReportVO物件,存入req
				String url = "/back_end/postReport/update_PostReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PostReport_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/listAllPostReport.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自update_PostReport_input.jsp的"修正"請求 ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁

			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 複合PK:文章編號+檢舉人會員編號 */
				Integer postNo = new Integer(req.getParameter("postNo").trim());
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				PostReportService postReportSvc = new PostReportService();

				/**************************** 3.讀取client端送過來的資料 ******************************/				
				/* 文章檢舉原因 */
				String postRepFor = req.getParameter("postRepFor");
				if (postRepFor == null) {
					postRepFor = postReportSvc.getOnePostReport(postNo, memberNo).getPostRepFor(); // 文章內容若沒修改就照舊
				}
				/* 文章檢舉時間 */
				java.sql.Date postRepTime = postReportSvc.getOnePostReport(postNo, memberNo).getPostRepTime();
				
				/* 文章檢舉狀態 */
				String str = req.getParameter("postRevState"); // 文章狀態若沒修改就照舊
				Integer postRevState = new Integer(str);
				if (str == null) {
					postRevState = postReportSvc.getOnePostReport(postNo, memberNo).getPostRevState();
				}

				/* ==================建構===================== */
				PostReportVO postReportVO = new PostReportVO();
				postReportVO.setPostNo(postNo);
				postReportVO.setMemberNo(memberNo);
				postReportVO.setPostRepFor(postRepFor);
				postReportVO.setPostRepTime(postRepTime);
				postReportVO.setPostRevState(postRevState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postReportVO", postReportVO); // 含有輸入格式錯誤的postReportVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/update_PostReport_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.開始新增資料 ***************************************/
				PostReportService postReportSVC = new PostReportService();
				postReportVO = postReportSVC.updatePostReport(postNo, memberNo, postRepTime, postRepFor, postRevState);

				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("postReportVO", postReportVO); // 資料庫update成功後,正確的的postReportVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo+ "&memberNo=" + memberNo; // 送出修改的來源網頁的第幾頁(只用於:listAllMemPerPage.jsp)和修改的是哪一筆
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listOnePostReport.jsp 顯示
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/update_PostReport_input.jsp");
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
				PostReportService postReportSvc = new PostReportService();
				List<PostReportVO> list = postReportSvc.getAll(map);

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
