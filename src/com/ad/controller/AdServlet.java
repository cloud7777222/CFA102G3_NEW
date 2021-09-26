package com.ad.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.ad.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常

public class AdServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自ad_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("adNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer adNo = null;
				try {
					adNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(adNo);
				if (adVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adVO", adVO); // 資料庫取出的adVO物件,存入req
				String url = "/back_end/ad/listOneAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllAd.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer adNo = new Integer(req.getParameter("adNo"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(adNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("adVO", adVO); // 資料庫取出的adVO物件,存入req
				String url = "/back_end/ad/update_ad_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ad_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ad/listAllAd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAll_For_Keyword".equals(action)) { // 來自listAllAd.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String keyword = new String(req.getParameter("keyword"));
				String keywordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (keyword == null || keyword.trim().length() == 0) {
					errorMsgs.add("搜尋條件: 請勿空白");
				} else if (!keyword.trim().matches(keywordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("搜尋條件: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				/*************************** 2.開始查詢資料 ****************************************/
//				AdService adSvc = new AdService();
//				List<AdVO> adVO = adSvc.getAllByKeyword(keyword);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("keyword", keyword); // 資料庫取出的adVO物件,存入req
				String url = "/back_end/ad/listAllAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ad_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ad/listAllAd.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_ad_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer adNo = new Integer(req.getParameter("adNo").trim());
				String adTitle = req.getParameter("adTitle");
				String adTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (adTitle == null || adTitle.trim().length() == 0) {
					errorMsgs.add("廣告標題: 請勿空白");
				} else if (!adTitle.trim().matches(adTitleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廣告標題: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				String ad = req.getParameter("ad").trim();
				if (ad == null || ad.trim().length() == 0) {
					errorMsgs.add("廣告請勿空白");
				}

				String adPic1 = req.getParameter("adPic1").trim();
				if (adPic1 == null || adPic1.trim().length() == 0) {
					errorMsgs.add("廣告圖請勿空白");
				}

				String adPic2 = req.getParameter("adPic2").trim();
				if (adPic2 == null || adPic2.trim().length() == 0) {
					errorMsgs.add("廣告圖請勿空白");
				}

				String adPic3 = req.getParameter("adPic3").trim();
				if (adPic3 == null || adPic3.trim().length() == 0) {
					errorMsgs.add("廣告圖請勿空白");
				}

				Integer adState = new Integer(req.getParameter("adState"));

				java.sql.Date postTime = null;
				try {
					postTime = java.sql.Date.valueOf(req.getParameter("postTime").trim());
				} catch (IllegalArgumentException e) {
					postTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入發佈日期!");
				}

				java.sql.Date deadline = null;
				try {
					deadline = java.sql.Date.valueOf(req.getParameter("deadline").trim());
				} catch (IllegalArgumentException e) {
					deadline = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}

				// 處理圖片
//				資料夾路徑
				String dir = getServletContext().getRealPath("/adPic_uploaded");
				File dirFile = new File(dir);
				if (!dirFile.exists())
					dirFile.mkdir();

				Collection<Part> parts = req.getParts();
				System.out.println(parts.size());
				int i = 1;
				int k = 0;
				for (Part part : parts) {
//					判斷為image才存
					if(part.getContentType() == null) continue;
					System.out.println(part.getContentType().indexOf("image") == 0);
					if(!(part.getContentType().indexOf("image") == 0)) continue;
					System.out.println(("image/jpeg").equals(part.getContentType()));
					
					// 動態產生檔案名
					java.util.Date du = new java.util.Date();
					java.sql.Date ds = new java.sql.Date(du.getTime());
					String filePath = "/" + ds + ds.hashCode() + k++ +".jpg";

//					// 利用File物件,寫入目地目錄,上傳成功
					File f = new File(dir + filePath);
					part.write(f.toString());
					String path = req.getContextPath() + "/adPic_uploaded" + filePath;

//					存值
					if (i == 1) {
						adPic1 = path;
						i++;
					} else if (i == 2) {
						adPic2 = path;
						i++;
					} else
						adPic3 = path;
				}

				AdVO adVO = new AdVO();
				adVO.setAdNo(adNo);
				adVO.setAdTitle(adTitle);
				adVO.setAd(ad);
				adVO.setAdPic1(adPic1);
				adVO.setAdPic2(adPic2);
				adVO.setAdPic3(adPic3);
				adVO.setAdState(adState);
				adVO.setPostTime(postTime);
				adVO.setDeadline(deadline);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ad/update_ad_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				

				/*************************** 2.開始修改資料 *****************************************/
				AdService adSvc = new AdService();
				adVO = adSvc.updateAd(adNo, adTitle, ad, adPic1, adPic2, adPic3, adState, postTime, deadline);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adVO", adVO); // 資料庫update成功後,正確的的adVO物件,存入req
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ad/update_ad_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addAd.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String adTitle = req.getParameter("adTitle");
				String adTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (adTitle == null || adTitle.trim().length() == 0) {
					errorMsgs.add("廣告標題: 請勿空白");
				} else if (!adTitle.trim().matches(adTitleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廣告標題: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				String ad = req.getParameter("ad").trim();
				if (ad == null || ad.trim().length() == 0) {
					errorMsgs.add("廣告請勿空白");
				}

				String adPic1 = req.getParameter("adPic1").trim();
				if (adPic1 == null || adPic1.trim().length() == 0) {
					errorMsgs.add("廣告圖請勿空白");
				}

				String adPic2 = req.getParameter("adPic2").trim();
				if (adPic2 == null || adPic2.trim().length() == 0) {
					errorMsgs.add("廣告圖請勿空白");
				}

				String adPic3 = req.getParameter("adPic3").trim();
				if (adPic3 == null || adPic3.trim().length() == 0) {
					errorMsgs.add("廣告圖請勿空白");
				}

				Integer adState = null;
				try {
					adState = new Integer(req.getParameter("adState").trim());
				} catch (Exception e) {
					errorMsgs.add("廣告上下架請選擇");
				}

				java.sql.Date postTime = null;
				try {
					postTime = java.sql.Date.valueOf(req.getParameter("postTime").trim());
				} catch (IllegalArgumentException e) {
					postTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入發佈日期!");
				}

				java.sql.Date deadline = null;
				try {
					deadline = java.sql.Date.valueOf(req.getParameter("deadline").trim());
				} catch (IllegalArgumentException e) {
					deadline = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入發佈日期!");
				}

				// 處理圖片
//				資料夾路徑
				String dir = getServletContext().getRealPath("/adPic_uploaded");
				File dirFile = new File(dir);
				if (!dirFile.exists())
					dirFile.mkdir();

				Collection<Part> parts = req.getParts();
				System.out.println(parts.size());
				int i = 1;
				int k = 0;
				for (Part part : parts) {
//					判斷為image才存
					if(part.getContentType() == null) continue;
					System.out.println(part.getContentType().indexOf("image") == 0);
					if(!(part.getContentType().indexOf("image") == 0)) continue;
					System.out.println(("image/jpeg").equals(part.getContentType()));
					
					// 動態產生檔案名
					java.util.Date du = new java.util.Date();
					java.sql.Date ds = new java.sql.Date(du.getTime());
					String filePath = "/" + ds + ds.hashCode() + k++ +".jpg";

//					// 利用File物件,寫入目地目錄,上傳成功
					File f = new File(dir + filePath);
					part.write(f.toString());
					String path = req.getContextPath() + "/adPic_uploaded" + filePath;

//					存值
					if (i == 1) {
						adPic1 = path;
						i++;
					} else if (i == 2) {
						adPic2 = path;
						i++;
					} else
						adPic3 = path;
				}

				AdVO adVO = new AdVO();
				adVO.setAdTitle(adTitle);
				adVO.setAd(ad);
				adVO.setAdPic1(adPic1);
				adVO.setAdPic2(adPic2);
				adVO.setAdPic3(adPic3);
				adVO.setAdState(adState);
				adVO.setPostTime(postTime);
				adVO.setDeadline(deadline);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ad/addAd.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AdService adSvc = new AdService();
				adVO = adSvc.addAd(adTitle, ad, adPic1, adPic2, adPic3, adState, postTime, deadline);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/ad/listAllAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ad/addAd.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllAd.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer adNo = new Integer(req.getParameter("adNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				AdService adSvc = new AdService();
				adSvc.deleteAd(adNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ad/listAllAd.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
