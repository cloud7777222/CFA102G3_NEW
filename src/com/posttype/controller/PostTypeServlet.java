package com.posttype.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.post.model.PostVO;
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
		
		
		//[ 擇一查多(詳細版) ]
	    // 來自select_page.jsp的請求                                  // 來自 postType/listAllPostType.jsp的請求                  // 來自 front_end/post/post_main.jsp的請求
		if ("listPostsBy_PostTypeNo_A".equals(action) || "listPostsBy_PostTypeNo_B".equals(action) || "listPostsBy_PostTypeNo_C".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer postTypeNo = new Integer(req.getParameter("postTypeNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				Set<PostVO> set = postTypeSvc.getPostsByPostTypeNo(postTypeNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listPostsBy_PostTypeNo", set);    // 資料庫取出的list物件,存入request

				String url = null;
				if ("listPostsBy_PostTypeNo_A".equals(action))
					url = "/back_end/postType/listPostsBy_PostTypeNo.jsp";        // 成功轉交 back_end/postType/listPostsBy_PostTypeNo.jsp
				else if ("listPostsBy_PostTypeNo_B".equals(action))
					url = "/back_end/postType/listAllPostType.jsp";              // 成功轉交 back_end/postType/listAllPostType.jsp
				else if ("listPostsBy_PostTypeNo_C".equals(action))
					url = "/front_end/post/postContent_differentTypeNo.jsp";	 // 成功轉交 front_end/postType/postContent_differentTypeNo.jsp
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
//			} catch (Exception e) {
//				throw new ServletException(e);
//			}
		}
		
		

		// [ 來自select_page.jsp的"擇一postTypeNO 看文章類別"的請求 (簡易版)]
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // 蒐集錯誤給jsp呈現
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("postTypeNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer postTypeNo = null;
				try {
					postTypeNo = new Integer(str); // 給規定文章編號只有數字
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				PostTypeVO postTypeVO = postTypeSvc.getOnePostType(postTypeNo);
				if (postTypeNo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("postTypeVO", postTypeVO); // 資料庫取出的postVO物件,存入req
				String url = "/back_end/postType/listOnePostType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功時畫面forward轉交給 listOnePost.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自addPostType.jsp的"新增"請求 ]
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				/* 文章類別 */
				String postType = req.getParameter("postType");
				if (postType == null || (postType.trim()).length() == 0) {
					errorMsgs.add("請輸入文章類別");
				}
				/* ==================建構===================== */
				PostTypeVO postTypeVO = new PostTypeVO();
				postTypeVO.setPostType(postType);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postTypeVO", postTypeVO); // 含有輸入格式錯誤的postTypeVO物件,也存入req, 讓使用者不必重填一些資訊
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/addPostType.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				postTypeVO = postTypeSvc.addPostType(postType);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("postTypeVO", postTypeVO);
				String url = "/back_end/postType/listAllPostType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listAllPostType.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("文章類別新增失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/addPostType.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		// [ 來自listAllPostType.jsp 的"刪除"請求]
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer postTypeNo = new Integer(req.getParameter("postTypeNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				postTypeSvc.deletePostType(postTypeNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回listAllPostType.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/listAllPostType.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自listAllPostType.jsp "單筆文章檢視"的請求 ]
		if ("getOne_For_Update".equals(action) || "getOne_For_Update_front".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer postTypeNo = new Integer(req.getParameter("postTypeNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				PostTypeService postTypeSvc = new PostTypeService();
				PostTypeVO postTypeVO = postTypeSvc.getOnePostType(postTypeNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("postTypeVO", postTypeVO); // 資料庫取出的postTypeVO物件,存入req
				String url = "/back_end/postType/update_PostType_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PostType_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/listAllPostType.jsp");
				failureView.forward(req, res);
			}
		}

		// [ 來自update_PostType_input.jsp的"修正"請求 ]
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁

			try {
				/*************************** 1.接收請求參數 ****************************************/
				/* 文章編號 */
				Integer postTypeNo = new Integer(req.getParameter("postTypeNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				PostTypeService postTypeSvc = new PostTypeService();

				/**************************** 3.讀取client端送過來的資料 ******************************/

				/* 文章類別 */
				String postType = req.getParameter("postType"); // 文章類別若沒修改就照舊
				if (postType == null || (postType.trim()).length() == 0) {
					postType = postTypeSvc.getOnePostType(postTypeNo).getPostType();
				}				
				/* ==================建構===================== */
				PostTypeVO postTypeVO = new PostTypeVO();
				postTypeVO.setPostTypeNo(postTypeNo);
				postTypeVO.setPostType(postType);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postTypeVO", postTypeVO); // 含有輸入格式錯誤的postTypeVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/update_PostType_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 4.開始新增資料 ***************************************/
				PostTypeService postTypeSVC = new PostTypeService();
				postTypeVO = postTypeSVC.updatePostType(postTypeNo, postType);

				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("postTypeVO", postTypeVO); // 資料庫update成功後,正確的的postTypeVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage + "&postTypeNo=" + postTypeNo; // 送出修改的來源網頁的第幾頁(只用於:listAllPostType.jsp)和修改的是哪一筆
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至listAllPostType.jsp 顯示
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/postType/update_PostType_input.jsp");
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
				PostTypeService postTypeSvc = new PostTypeService();
				List<PostTypeVO> list = postTypeSvc.getAll(map);

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
