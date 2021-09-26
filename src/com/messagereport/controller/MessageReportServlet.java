package com.messagereport.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.messagereport.model.MessageReportService;
import com.messagereport.model.MessageReportVO;

public class MessageReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ 來自select_page.jsp的"擇一 (memberNo+mesNO) 看文章留言檢舉"的請求 ]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // 蒐集錯誤給jsp呈現
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 檢舉人會員編號
				String mNo = req.getParameter("memberNo");
				if (mNo == null || (mNo.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉人會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				// 文章留言編號
				String str = req.getParameter("mesNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MessageReportService postReportSvc = new MessageReportService();
				MessageReportVO messageReportVO = postReportSvc.getOneMessageReport(memberNo, mesNo);
				if (messageReportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("messageReportVO", messageReportVO); // 資料庫取出的messageReportVO物件,存入req
				String url = "/back_end/messageReport/listOneMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功時畫面forward轉交給 listOneMessageReport.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自addMessageReport.jsp的"新增"請求 ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/			
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
			/* 文章留言編號 */
			String ptn = req.getParameter("mesNo");
			if (ptn == null || (ptn.trim()).length() == 0) {
				errorMsgs.add("請輸入文章留言編號");
			}
			Integer mesNo = null;
			try {
				mesNo = new Integer(req.getParameter("mesNo").trim());
			} catch (Exception e) {
				errorMsgs.add("文章留言編號格式錯誤, 請填數字!");
			}
			/* 留言檢舉時間 */
			java.sql.Date mesRepTime = null;
			mesRepTime = new java.sql.Date(System.currentTimeMillis()); // 取得當下po文系統時間
			try {
				/* 留言檢舉原因 */
				String mesRepFor = req.getParameter("mesRepFor");
				if (mesRepFor == null || (mesRepFor.trim()).length() == 0) {
					errorMsgs.add("請輸入留言檢舉原因");
				}
				/* 留言檢舉處理狀態 */
				Integer mesRevState = 1; // (1:待審核 / 2:審核通過 / 3:審核未通過 / 預設:1)

				/* ==================建構===================== */
				MessageReportVO messageReportVO = new MessageReportVO();				
				messageReportVO.setMemberNo(memberNo);
				messageReportVO.setMesNo(mesNo);
				messageReportVO.setMesRepFor(mesRepFor);
				messageReportVO.setMesRepTime(mesRepTime);
				messageReportVO.setMesRevState(mesRevState);
				;

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); // 含有輸入格式錯誤的postReportVO物件,也存入req, 讓使用者不必重填一些資訊
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postReport/addPostReport.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MessageReportService messageReportSvc = new MessageReportService();
				messageReportVO = messageReportSvc.addMessageReport(memberNo, mesNo, mesRepTime, mesRepFor, mesRevState);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("messageReportVO", messageReportVO);
				String url = "/back_end/messageReport/listAllMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listAllMessageReport.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("留言檢舉新增失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/addMessageReport.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		// [ 來自listAllMessageReport.jsp 的"刪除"請求]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ***************************************/
				/* 複合PK:檢舉人會員編號+文章留言編號 */
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.開始刪除資料 ***************************************/
				MessageReportService messageReportSvc = new MessageReportService();
				messageReportSvc.deleteMessageReport(mesNo, memberNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回listAllMessageReport.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/listAllMessageReport.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自listAllMessageReport.jsp "單筆文章檢視"的請求 ]
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 複合PK:檢舉人會員編號+文章留言編號 */
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				MessageReportService messageReportSvc = new MessageReportService();
				MessageReportVO messageReportVO = messageReportSvc.getOneMessageReport(memberNo, mesNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("messageReportVO", messageReportVO); // 資料庫取出的messageReportVO物件,存入req
				String url = "/back_end/messageReport/update_MessageReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_MessageReport_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/messageReport/listAllMessageReport.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自update_MessageReport_input.jsp的"修正"請求 ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁

			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 複合PK:檢舉人會員編號+文章留言編號 */
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());
				Integer mesNo = new Integer(req.getParameter("mesNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				MessageReportService messageReportSvc = new MessageReportService();

				/**************************** 3.讀取client端送過來的資料 ******************************/
				/* 文章檢舉原因 */
				String mesRepFor = req.getParameter("mesRepFor");
				if (mesRepFor == null) {
					mesRepFor = messageReportSvc.getOneMessageReport(memberNo, mesNo).getMesRepFor(); // 文章內容若沒修改就照舊
				}
				/* 文章檢舉時間 */
				java.sql.Date mesRepTime = messageReportSvc.getOneMessageReport(memberNo, mesNo).getMesRepTime(); 
				
				/* 文章檢舉狀態 */
				String str = req.getParameter("mesRevState"); // 文章狀態若沒修改就照舊
				Integer mesRevState = new Integer(str);
				if (str == null) {
					mesRevState = messageReportSvc.getOneMessageReport(memberNo, mesNo).getMesRevState();
				}

				/* ==================建構===================== */
				MessageReportVO messageReportVO = new MessageReportVO();			
				messageReportVO.setMemberNo(memberNo);
				messageReportVO.setMesNo(mesNo);
				messageReportVO.setMesRepFor(mesRepFor);
				messageReportVO.setMesRepTime(mesRepTime);
				messageReportVO.setMesRevState(mesRevState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); // 含有輸入格式錯誤的messageReportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/messageReport/update_MessageReport_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.開始新增資料 ***************************************/
				MessageReportService messageReportSVC = new MessageReportService();
				messageReportVO = messageReportSVC.updateMessageReport(memberNo, mesNo, mesRepTime, mesRepFor, mesRevState);

				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("messageReportVO", messageReportVO); // 資料庫update成功後,正確的的messageReportVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage + "&memberNo=" + memberNo + "&mesNo=" + mesNo ; // 送出修改的來源網頁的第幾頁(只用於:listAllMemPerPage.jsp)和修改的是哪一筆
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listOneMessageReport.jsp 顯示
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/messageReport/update_MessageReport_input.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
