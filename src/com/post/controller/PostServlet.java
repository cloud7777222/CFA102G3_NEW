package com.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.post.model.PostService;
import com.post.model.PostVO;
import com.postmessage.model.PostMessageVO;
import com.posttype.model.PostTypeService;
import com.member.model.MemberVO;

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		//[ 擇一查多(查詢文章看其所有留言) ]
	    // 來自前端的請求                                
		if ("listMessagesBy_PostNo_frontEnd".equals(action)) {
		
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer postNo = new Integer(req.getParameter("postNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				PostService postSvc = new PostService();
				Set<PostMessageVO> set = postSvc.getMessagesByPostNo(postNo);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/		
			//set.forEach(System.out::println);	//查出的是址			
				res.setCharacterEncoding("UTF-8");
				res.setContentType("application/json;charset=UTF-8");
				PrintWriter pw = res.getWriter();
				String jsonStr = "";
				jsonStr = new JSONArray(set).toString();
				System.out.println("List to JSON: " + jsonStr);
				pw.print(jsonStr);
				
				//gson 將java物件轉json
//				String data = " ";
//				JsonObject gsonObj = new JsonObject();
//				JsonArray array = new JsonArray();
//				
//				for (PostMessageVO message: set) {
//					JsonObject item = new JsonObject();
//					System.out.println(message.getMesNo()+": "+message.getMemberNo() + "-" + message.getMesContent());
//					item.addProperty("mesNo", message.getMesNo());
//					item.addProperty("memberNo", message.getMemberNo());
//					item.addProperty("postNo", message.getPostNo());
//					item.addProperty("mesContent", message.getMesContent());
//					//item.addProperty("mesTime", message.getMesTime());
//					item.addProperty("mesState", message.getMesState());
//					array.add(item);					
//				}
//				gsonObj.add("data", array);			
//				Gson gson = new Gson();
//				String setJSON = gson.toJson(gsonObj);
//				pw.print(setJSON);
//				System.out.println(setJSON);
							  
				//res.getWriter().print("hi! postNo= "+ postNo); //for testing if jsp could call this servlet successfully
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		

		
		//[ 擇一查多(查詢文章看其所有留言) ]
	    // 來自後端select_page.jsp的請求                                
		if ("listMessagesBy_PostNo".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer postNo = new Integer(req.getParameter("postNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				PostService postSvc = new PostService();
				Set<PostMessageVO> set = postSvc.getMessagesByPostNo(postNo);
				Integer postTypeNo = postSvc.getOnePost(postNo).getPostTypeNo();
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listMessagesBy_PostNo", set);    // 資料庫取出的list物件,存入request
				//[注意] 此為com.posttype.model來的新增請求(前端)
				PostTypeService postTypeSvc = new PostTypeService();
				req.setAttribute("listPostsBy_PostTypeNo", postTypeSvc.getPostsByPostTypeNo(postTypeNo)); // 資料庫取出的list物件,存入request
						
				String url = null;
				if ("listMessagesBy_PostNo".equals(action)) {
					url = "/back_end/post/listMessagesBy_PostNo.jsp";        // 成功轉交 /back_end/post/listMessagesBy_PostNo.jsp
				}else if("listMessagesBy_PostNo_frontEnd".equals(action)){
					url = "/postType/PostTypeServlet"+"?postTypeNo="+ postTypeNo + "&action=listPostsBy_PostTypeNo_C";
				}								
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		

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
		
		
				
				
				// [ 來自前端的"新增"請求 ]
				if ("insert".equals(action)) {
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					String requestURL = req.getParameter("requestURL"); // 送出新增的來源網頁路徑

					/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
					/* 文章類別編號 */
					String ptNo = req.getParameter("postTypeNo");
					if (ptNo == null || (ptNo.trim()).length() == 0) {
						errorMsgs.add("請輸入文章類別編號");
					}
					Integer postTypeNo = null;
					try {
						postTypeNo = new Integer(req.getParameter("postTypeNo").trim()); // 如何自動取得登入者帳號自動帶入這裡? session
						// 有setAttribute嗎?
					} catch (Exception e) {
						errorMsgs.add("文章類別編號格式錯誤! 請輸入數字: 1)旅遊; 2)吃吃喝喝; 3)兩性關係; 4)其他 ");
					}
					/* 會員編號 */				
					HttpSession session = req.getSession();
					Integer memberNo = null;
					try {
						memberNo = ((MemberVO) session.getAttribute("memberVO")).getMemberNo();
					}catch (Exception e) {
						errorMsgs.add("請先登入!");
					}
														
//					String str = req.getParameter("memberNo");
//					if (str == null || (str.trim()).length() == 0) {
//						errorMsgs.add("請輸入會員編號");
//					}
//					Integer memberNo = null;
//					try {
//						memberNo = new Integer(req.getParameter("memberNo").trim()); // 如何自動取得登入者帳號自動帶入這裡? session
//						// 有setAttribute嗎?
//					} catch (Exception e) {
//						errorMsgs.add("會員編號格式錯誤! 請輸入數字!");
//					}
					/* 文章內容 */
					String postContent = null;
					try {					
						postContent = req.getParameter("postContent");
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
							RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/post_add.jsp");
							failureView.forward(req, res);
							return;
						}
						/*************************** 2.開始新增資料 ***************************************/
						PostService postSvc = new PostService();
						postVO = postSvc.addPost(postTypeNo, memberNo, postContent, postTime, postState, mesCount, numOfLike);
						/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
						//[注意] 此為com.posttype.model來的新增請求(前端)
						PostTypeService postTypeSvc = new PostTypeService();
						req.setAttribute("listPostsBy_PostTypeNo", postTypeSvc.getPostsByPostTypeNo(postTypeNo)); // 資料庫取出的list物件,存入request
						req.setAttribute("postVO", postVO);
						String url = "/postType/PostTypeServlet"+"?postTypeNo="+ postTypeNo + "&action=listPostsBy_PostTypeNo_C";
						System.out.println("url=" + url);
						res.sendRedirect(req.getContextPath()+url); //使用重導不會帶值回去, 所以刷新不會再次送出po文	
//						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至postContent_differentTypeNo.jsp
//						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("文章新增失敗:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/post/post_add.jsp");
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
		if ("getOne_For_Update".equals(action) || "getOne_For_Update_frontEnd".equals(action)) {

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
				String url = null;
				if("getOne_For_Update".equals(action)) {
					url = "/back_end/post/update_Post_input.jsp";
				} else if ("getOne_For_Update_frontEnd".equals(action)) {
					url = "/front_end/post/post_update.jsp";
				}				
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
				String ptNo = req.getParameter("postTypeNo");
				if (ptNo == null || (ptNo.trim()).length() == 0) {
					errorMsgs.add("請輸入文章類別編號");
				}
				Integer postTypeNo = null;
				try {
					postTypeNo = new Integer(req.getParameter("postTypeNo").trim()); // 如何自動取得登入者帳號自動帶入這裡? session
					// 有setAttribute嗎?
				} catch (Exception e) {
					postTypeNo = postSvc.getOnePost(postNo).getPostTypeNo();
					errorMsgs.add("文章類別編號格式錯誤! 請輸入數字: 1)旅遊; 2)吃吃喝喝; 3)兩性關係; 4)其他 ");
					
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

				/*************************** 4.開始修改資料 ***************************************/
				PostService postSVC = new PostService();
				postVO = postSVC.updatePost(postNo, postTypeNo, memberNo, postContent, postTime, postState, mesCount,
						numOfLike);

				/*************************** 5.修改完成,準備轉交(Send the Success view) ***********/
				//[注意] 此為com.posttype.model來的修改更新請求(擇一查多)
				PostTypeService postTypeSvc = new PostTypeService();
				if(requestURL.equals("/back_end/postType/listPostsBy_PostTypeNo.jsp") || requestURL.equals("/back_end/postType/listAllPostType.jsp") || requestURL.equals("/front_end/post/postContent_differentTypeNo.jsp"))
					req.setAttribute("listPostsBy_PostTypeNo", postTypeSvc.getPostsByPostTypeNo(postTypeNo)); // 資料庫取出的list物件,存入request
				//此為com.post.model來的修改更新請求
				req.setAttribute("postVO", postVO); // 資料庫update成功後,正確的的postVO物件,存入req
				String url = requestURL + "?whichPage=" + whichPage + "&postNo=" + postNo; // 送出修改的來源網頁的第幾頁(只用於:listAllPost.jsp)和修改的是哪一筆
				System.out.println("url=" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交至來源網頁顯示
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/post/update_Post_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		// [ 來自front_end的Ajax"按讚"請求 ]
		if ("updateForLike".equals(action)) {
		
				/*************************** 1.接收請求參數 ****************************************/
				/* 文章編號 */
				Integer postNo = new Integer(req.getParameter("postNo"));
				/*************************** 2.開始查詢資料 ****************************************/
				PostService postSvc = new PostService();
				/**************************** 3.讀取client端送過來的資料 ******************************/
				/* 文章類別編號 */
				Integer postTypeNo = postSvc.getOnePost(postNo).getPostTypeNo();
				/* 會員編號 */
				Integer memberNo = postSvc.getOnePost(postNo).getMemberNo(); // 會員編號不能改, 所以沿用舊資料
				/* 文章內容 */
				String postContent = postSvc.getOnePost(postNo).getPostContent();			
				/* 文章發表時間 */
				java.sql.Date postTime = postSvc.getOnePost(postNo).getPostTime();			
				/* 文章狀態 */				
				Integer postState = postSvc.getOnePost(postNo).getPostState();				
				/* 留言數 */
				Integer mesCount = postSvc.getOnePost(postNo).getMesCount();
				/* 按讚數 */
				Integer numOfLike = postSvc.getOnePost(postNo).getNumOfLike()+1;
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
				/*************************** 4.開始修改資料 ***************************************/
				PostService postSVC = new PostService();
				postVO = postSVC.updatePost(postNo, postTypeNo, memberNo, postContent, postTime, postState, mesCount,
						numOfLike);
				/*************************** 5.更新完成,準備轉交(Send the Success result) ***********/
				req.setAttribute("postVO", postVO); // 資料庫update成功後,正確的的postVO物件,存入req
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/plain");
				PrintWriter pw = res.getWriter();
				System.out.println(numOfLike);
				pw.print(numOfLike);
						
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
