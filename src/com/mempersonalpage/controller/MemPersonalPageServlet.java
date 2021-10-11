package com.mempersonalpage.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.mempersonalpage.model.MemPersonalPageService;
import com.mempersonalpage.model.MemPersonalPageVO;
import com.member.model.MemberVO;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemPersonalPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String uploadDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");
		Integer postNo = new Integer(req.getParameter("postNo")); // 抓<img src=" ?pk=yyy"> pk的參數值
		MemPersonalPageService mppSvc = new MemPersonalPageService();
		ServletOutputStream sout = res.getOutputStream(); // 輸出資料流
		byte[] buf = mppSvc.getOneMemPerPage(postNo).getPostPhoto();
		sout.write(buf); // 輸出二位元資料
		sout.close();
		
		

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// [ 來自select_page.jsp的"擇一postNO 看貼文"的請求 ]
		if ("getOne_For_Display".equals(action) || "getOne_For_Display_frondEnd".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // Store this set in the request scope, in case we need
																// to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("postNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入貼文編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/memPersonalPage/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer postNo = null;
				try {
					postNo = new Integer(str); // 給規定貼文編號只有數字
				} catch (Exception e) {
					errorMsgs.add("貼文編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/memPersonalPage/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				MemPersonalPageVO mppVO = mppSvc.getOneMemPerPage(postNo);
				if (mppVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/memPersonalPage/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("mppVO", mppVO); // 資料庫取出的mppVO物件,存入req
				String url = null;
				if ("getOne_For_Display".equals(action)){
					url = "/back_end/memPersonalPage/listOneMemPerPage.jsp";
				} else if ("getOne_For_Display_frondEnd".equals(action)) {
					url = "/front_end/memPersonalPage/listOneMemPerPage.jsp";
				}				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功時畫面forward轉交給 listOneMemPerPage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/memPersonalPage/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自addMemPerPage.jsp的"新增"請求 ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			/* 會員編號 */				
			HttpSession session = req.getSession();
			Integer memberNo = null;
			try {
				memberNo = ((MemberVO) session.getAttribute("memberVO")).getMemberNo();
			}catch (Exception e) {
				errorMsgs.add("請先登入!");
			}
			
			
//			String str = req.getParameter("memberNo");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入會員編號");
//			}
//			Integer memberNo = null;
//			try {
//				memberNo = new Integer(req.getParameter("memberNo").trim()); // 如何自動取得登入者帳號自動帶入這裡? session
//				// 有setAttribute嗎?
//			} catch (Exception e) {
//				errorMsgs.add("會員編號格式錯誤");
//			}

			try {
				/* 貼文圖片 */
				res.setContentType("text/html; charset=UTF-8");
				Part part = req.getPart("postPhoto");
				String filename = getFileNameFromPart(part);

//				 //存進專案阿飄資料夾 (getServletContext() 專案本身; getRealPath(uploadDirectory) 阿飄路徑)
//				PrintWriter out = res.getWriter();
//				String realPath = getServletContext().getRealPath(uploadDirectory); 
//				File fsaveDirectory = new File(realPath);
//				if (!fsaveDirectory.exists())
//					fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄				
//				File f = new File(fsaveDirectory, filename);
//				out.println("filename: " + filename);
//				part.write(f.toString());// 利用File物件,寫入目地目錄,上傳成功
//				 out.println("<br><img src=\"" + req.getContextPath() + uploadDirectory + "/"
//				 + filename + "\">");// 動態路徑額外測試秀圖

				// 將image送進DB儲存
				InputStream in = part.getInputStream();
				byte[] postPhoto = new byte[in.available()]; // 利用setBytes 要送進DB
				in.read(postPhoto);
				in.close();
				req.setAttribute("postPhoto", postPhoto);
				if (filename == null || (filename.trim()).length() == 0) {
					errorMsgs.add("請選擇上傳圖片");
				}

				/* 貼文內容 */
				String postContent = req.getParameter("postContent");
				if (postContent == null || (postContent.trim()).length() == 0) {
					errorMsgs.add("請輸入貼文內容");
				}
				/* 貼文時間 */
				java.sql.Date postTime = null;
				postTime = new java.sql.Date(System.currentTimeMillis()); // 取得當下po文系統時間

				/* 按讚數 */
				Integer numOfLike = 0;

				/* 貼文狀態 */
				Integer postState = 1;

				/* ==================建構===================== */
				MemPersonalPageVO mppVO = new MemPersonalPageVO();
				mppVO.setMemberNo(memberNo);
				mppVO.setPostPhoto(postPhoto);
				mppVO.setPostContent(postContent);
				mppVO.setPostTime(postTime);
				mppVO.setNumOfLike(numOfLike);
				mppVO.setPostState(postState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mppVO", mppVO); // 含有輸入格式錯誤的eppVO物件,也存入req, 讓使用者不必重填一些資訊
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/memPersonalPage/memPersonalPage_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				mppVO = mppSvc.addMemPerPage(memberNo, postPhoto, postContent, postTime, numOfLike, postState);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("mppVO", mppVO);
				String url = "/front_end/memPersonalPage/memPersonalPage_main.jsp?memberNo="+memberNo;
				res.sendRedirect(req.getContextPath()+url); //使用重導不會帶值回去, 所以刷新不會再次送出po文			
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listAllMemPerPage.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("貼文新增失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/memPersonalPage/memPersonalPage_add.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		// [ 來自listAllMemPerPage.jsp 的"刪除"請求]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer postNo = new Integer(req.getParameter("postNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				mppSvc.deleteMemPerPage(postNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/memPersonalPage/listAllMemPerPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回listAllMemPerPage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/memPersonalPage/listAllMemPerPage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自listAllMemPerPage.jsp "單筆貼文檢視"的請求 ]
		if ("getOne_For_Update".equals(action) || "getOne_For_Update_frontEnd".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				MemPersonalPageVO mppVO = mppSvc.getOneMemPerPage(postNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("mppVO", mppVO); // 資料庫取出的mppVO物件,存入req
				String url = null;
				if ("getOne_For_Update".equals(action)) {
					url = "/back_end/memPersonalPage/update_MemPerPage_input.jsp";
				} else if ("getOne_For_Update_frontEnd".equals(action)) {
					url = "/front_end/memPersonalPage/memPersonalPage_update.jsp";
				}												
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_MemPerPage_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/memPersonalPage/listAllMemPerPage.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自update_MemPerPage_input.jsp的"修正"請求 ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁

//			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 貼文編號 */
				Integer postNo = new Integer(req.getParameter("postNo").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();

				/**************************** 3.讀取client端送過來的資料 ******************************/

				/* 會員編號 */
				Integer memberNo = mppSvc.getOneMemPerPage(postNo).getMemberNo(); // 會員編號不能改, 所以沿用舊資料

				/* 貼文圖片 */
				Part part = req.getPart("postPhoto");
				InputStream in = part.getInputStream(); // 利用part物件轉資料流存成byte[]
				byte[] postPhoto = null;
				if (in.available() > 0) { // 有更新圖片,而且有讀取到時(>0)
					postPhoto = new byte[in.available()];
					in.read(postPhoto);
					in.close();
				} else {
					postPhoto = mppSvc.getOneMemPerPage(postNo).getPostPhoto(); // 沒更新圖片就用原本的圖片
				}
				req.setAttribute("postPhoto", postPhoto);

				/* 貼文內容 */
				String postContent = req.getParameter("postContent");
				if (postContent == null) {
					errorMsgs.add("請輸入貼文內容");
				}

				/* 貼文時間 */
				java.sql.Date postTime = mppSvc.getOneMemPerPage(postNo).getPostTime();
			
				/* 按讚數 */
				Integer numOfLike =  mppSvc.getOneMemPerPage(postNo).getNumOfLike();
				
				/* 貼文狀態 */
				String str = req.getParameter("postState");
				Integer postState = new Integer(str);
				if (str.equals(null)) {
					postState = mppSvc.getOneMemPerPage(postNo).getPostState();
				}

				/* ==================建構===================== */
				MemPersonalPageVO mppVO = new MemPersonalPageVO();
				mppVO.setPostNo(postNo);
				mppVO.setMemberNo(memberNo);
				mppVO.setPostPhoto(postPhoto);
				mppVO.setPostContent(postContent);
				mppVO.setPostTime(postTime);
				mppVO.setNumOfLike(numOfLike);
				mppVO.setPostState(postState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mppVO", mppVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/memPersonalPage/update_MemPerPage_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.開始新增資料 ***************************************/
				MemPersonalPageService mppSVC = new MemPersonalPageService();
				mppVO = mppSVC.updateMemPerPage(postNo, memberNo, postPhoto, postContent, postTime, numOfLike,
						postState);

				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("mppVO", mppVO); // 資料庫update成功後,正確的的mppVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo+ "&memberNo=" + memberNo; // 送出修改的來源網頁的第幾頁(只用於:listAllMemPerPage.jsp)和修改的是哪一筆
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listOneMemPerPage.jsp 顯示
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("資料修改失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/memPersonalPage/update_MemPerPage_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		
		
//		// [ 來自update_MemPerPage_input.jsp的"修正"請求 ]
//				if ("updateLike".equals(action)) {
//
//					List<String> errorMsgs = new LinkedList<String>();
//					req.setAttribute("errorMsgs", errorMsgs);
//					
//					String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
//					String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁
//
////					try {
//						/*************************** 1.接收請求參數 ****************************************/
//						/* 貼文編號 */
//						Integer postNo = new Integer(req.getParameter("postNo").trim());
//
//						/*************************** 2.開始查詢資料 ****************************************/
//						MemPersonalPageService mppSvc = new MemPersonalPageService();
//
//						/**************************** 3.讀取client端送過來的資料 ******************************/
//
//						/* 會員編號 */
//						Integer memberNo = mppSvc.getOneMemPerPage(postNo).getMemberNo(); // 會員編號不能改, 所以沿用舊資料
//
//						/* 貼文圖片 */
//						byte[] postPhoto = mppSvc.getOneMemPerPage(postNo).getPostPhoto(); // 用原本的圖片
//						
//						/* 貼文內容 */
//						String postContent = mppSvc.getOneMemPerPage(postNo).getPostContent();
//
//						/* 貼文時間 */
//						java.sql.Date postTime = mppSvc.getOneMemPerPage(postNo).getPostTime();
//					
//						/* 更新按讚數 */
//						Integer numOfLike = new Integer(req.getParameter("numOfLike").trim());
//						
//						/* 貼文狀態 */
//						Integer postState = mppSvc.getOneMemPerPage(postNo).getPostState();
//
//						/* ==================建構===================== */
//						MemPersonalPageVO mppVO = new MemPersonalPageVO();
//						mppVO.setPostNo(postNo);
//						mppVO.setMemberNo(memberNo);
//						mppVO.setPostPhoto(postPhoto);
//						mppVO.setPostContent(postContent);
//						mppVO.setPostTime(postTime);
//						mppVO.setNumOfLike(numOfLike);
//						mppVO.setPostState(postState);
//
//						if (!errorMsgs.isEmpty()) {
//							req.setAttribute("mppVO", mppVO); // 含有輸入格式錯誤的empVO物件,也存入req
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/back_end/memPersonalPage/update_MemPerPage_input.jsp");
//							failureView.forward(req, res);
//							return;
//						}
//
//						/*************************** 4.開始新增資料 ***************************************/
//						MemPersonalPageService mppSVC = new MemPersonalPageService();
//						mppVO = mppSVC.updateMemPerPage(postNo, memberNo, postPhoto, postContent, postTime, numOfLike,
//								postState);
//
//						/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
//						req.setAttribute("mppVO", mppVO); // 資料庫update成功後,正確的的mppVO物件,存入req
//						String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo; // 送出修改的來源網頁的第幾頁(只用於:listAllMemPerPage.jsp)和修改的是哪一筆
//						System.out.println("url=" + url);
//						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listOneMemPerPage.jsp 顯示
//						successView.forward(req, res);
//
//						/*************************** 其他可能的錯誤處理 *************************************/
////					} catch (Exception e) {
////						errorMsgs.add("資料修改失敗:" + e.getMessage());
////						RequestDispatcher failureView = req
////								.getRequestDispatcher("/back_end/memPersonalPage/update_MemPerPage_input.jsp");
////						failureView.forward(req, res);
////					}
//				}
		
		
		
		
		// [ 來自update_MemPerPage_input.jsp的"修正"請求 ]
		if ("updateLike".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁

//			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 貼文編號 */
				Integer postNo = new Integer(req.getParameter("postNo").trim());
				/*************************** 2.開始查詢資料 ****************************************/
				MemPersonalPageService mppSvc = new MemPersonalPageService();
				/**************************** 3.讀取client端送過來的資料 ******************************/
				/* 會員編號 */
				Integer memberNo = mppSvc.getOneMemPerPage(postNo).getMemberNo(); // 會員編號不能改, 所以沿用舊資料
				/* 貼文圖片 */
				byte[] postPhoto = mppSvc.getOneMemPerPage(postNo).getPostPhoto(); // 用原本的圖片				
				/* 貼文內容 */
				String postContent = mppSvc.getOneMemPerPage(postNo).getPostContent();
				/* 貼文時間 */
				java.sql.Date postTime = mppSvc.getOneMemPerPage(postNo).getPostTime();			
				/* 更新按讚數 */
				Integer numOfLike = mppSvc.getOneMemPerPage(postNo).getNumOfLike()+1;	
				/* 貼文狀態 */
				Integer postState = mppSvc.getOneMemPerPage(postNo).getPostState();
				/* ==================建構===================== */
				MemPersonalPageVO mppVO = new MemPersonalPageVO();
				mppVO.setPostNo(postNo);
				mppVO.setMemberNo(memberNo);
				mppVO.setPostPhoto(postPhoto);
				mppVO.setPostContent(postContent);
				mppVO.setPostTime(postTime);
				mppVO.setNumOfLike(numOfLike);
				mppVO.setPostState(postState);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mppVO", mppVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/memPersonalPage/update_MemPerPage_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 4.開始新增資料 ***************************************/
				MemPersonalPageService mppSVC = new MemPersonalPageService();
				mppVO = mppSVC.updateMemPerPage(postNo, memberNo, postPhoto, postContent, postTime, numOfLike,
						postState);
				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("mppVO", mppVO); // 資料庫update成功後,正確的的mppVO物件,存入req
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/plain");
				PrintWriter pw = res.getWriter();
				System.out.println(numOfLike);
				pw.print(numOfLike);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("資料修改失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/memPersonalPage/update_MemPerPage_input.jsp");
//				failureView.forward(req, res);
//			}
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
