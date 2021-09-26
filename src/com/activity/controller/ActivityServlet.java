package com.activity.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActivityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String str = req.getParameter("actNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/activity/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer actNo = null;
				try {
					actNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/activity/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(actNo);
				if (activityVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/activity/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("activityVO", activityVO); 
				String url = "/back_end/activity/listOneActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);


				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/activity/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));
				
				/***************************2.開始查詢資料****************************************/
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.getOneActivity(actNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
	         	req.setAttribute("activityVO", activityVO);  
	            String url = "/back_end/activity/update_activity_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			 } catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actNo = new Integer(req.getParameter("actNo"));
				Integer actType = new Integer(req.getParameter("actType"));
	
				String actName = req.getParameter("actName");
				String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (actName  == null || actName .trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if(!actName .trim().matches(actNameReg)) { 
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				java.sql.Date actDate = null;
				try {
					actDate = java.sql.Date.valueOf(req.getParameter("actDate"));
				} catch (IllegalArgumentException e) {
					actDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動日期!");
				}

				
				String actLocation = req.getParameter("actLocation");
				if (actLocation == null || actLocation.trim().length() == 0) {
					errorMsgs.add("活動地點請勿空白");
				}	
				
				String actDirection = req.getParameter("actDirection");
				if (actDirection == null || actDirection.trim().length() == 0) {
					errorMsgs.add("活動說明請勿空白");
				}	
				
				Integer actState = new Integer(req.getParameter("actState"));
			    Integer actHoldState = new Integer(req.getParameter("actHoldState"));
			    
			    java.sql.Date actRegisterStartDate = null;
				try {
					actRegisterStartDate = java.sql.Date.valueOf(req.getParameter("actRegisterStartDate").trim());
				} catch (IllegalArgumentException e) {
					actDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動報名日期!");
				}
				
				 
				java.sql.Date actRegisterDeadLine = null;
				try {
					actRegisterDeadLine = java.sql.Date.valueOf(req.getParameter("actRegisterDeadLine").trim());
				} catch (IllegalArgumentException e) {
					actDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動截止日期!");
				}
		
			    ActivityVO activityVO = new ActivityVO();
			    activityVO.setActNo(actNo);
				activityVO.setActType(actType);
				activityVO.setActName(actName);
				activityVO.setActDate(actDate);
				activityVO.setActLocation(actLocation);
				activityVO.setActDirection(actDirection);
				activityVO.setActState(actState);
				activityVO.setActHoldState(actHoldState);
				activityVO.setActRegisterStartDate(actRegisterStartDate);
				activityVO.setActRegisterDeadLine(actRegisterDeadLine);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO",activityVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/activity/addActivity.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActivityService activitySvc =new ActivityService();
				
				
				activityVO = activitySvc.addActivity(actNo,actType,actName,actDate,actLocation,actDirection,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/activity/listAllActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/activity/addActivity.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));
				
				/***************************2.開始刪除資料***************************************/
				ActivityService activitySvc = new ActivityService();
				activitySvc.deleteActivity(actNo);
              
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/													
				String url = "/back_end/activity/listAllActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/activity/listAllActivity.jsp");
				failureView.forward(req, res);
			}
		}
	if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			//try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actNo = new Integer(req.getParameter("actNo"));
                
				
				
				Integer actType = new Integer(req.getParameter("actType"));
				
				String actName = req.getParameter("actName");
				String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (actName  == null || actName .trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if(!actName .trim().matches(actNameReg)) { 
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				java.sql.Date actDate = null;
				try {
					actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
				} catch (IllegalArgumentException e) {
					actDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動日期!");
				}

				
				String actLocation = req.getParameter("actLocation").trim();
				if (actLocation == null || actLocation.trim().length() == 0) {
					errorMsgs.add("活動地點請勿空白");
				}	
				
				String actDirection = req.getParameter("actDirection").trim();
				if (actDirection == null || actDirection.trim().length() == 0) {
					errorMsgs.add("活動說明請勿空白");
				}	
				
				Integer maxParticipant = new Integer(req.getParameter("maxParticipant"));
			    Integer minParticipant = new Integer(req.getParameter("minParticipant"));
				
				Integer actState = new Integer(req.getParameter("actState"));
			    Integer actHoldState = new Integer(req.getParameter("actHoldState"));
			    
			    java.sql.Date actRegisterStartDate = null;
				try {
					actRegisterStartDate = java.sql.Date.valueOf(req.getParameter("actRegisterStartDate").trim());
				} catch (IllegalArgumentException e) {
					actDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動開始日期!");
				}
				
				 
				java.sql.Date actRegisterDeadLine = null;
				try {
					actRegisterDeadLine = java.sql.Date.valueOf(req.getParameter("actRegisterDeadLine").trim());
				} catch (IllegalArgumentException e) {
					actDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入截止日期!");
				}
				
				
	
			    Integer totalStar = new Integer(req.getParameter("totalStar"));
			    Integer totalEvaluator = new Integer(req.getParameter("totalEvaluator"));

				

				ActivityVO activityVO = new ActivityVO();
				activityVO.setActNo(actNo);
				activityVO.setActType(actType);
				activityVO.setActName(actName);
				activityVO.setActDate(actDate);
				activityVO.setActLocation(actLocation);
				activityVO.setActDirection(actDirection);
				activityVO.setMaxParticipant(maxParticipant);
				activityVO.setMinParticipant(minParticipant);
				activityVO.setActState(actState);
				activityVO.setActHoldState(actHoldState);
				activityVO.setActRegisterStartDate(actRegisterStartDate);
				activityVO.setActRegisterDeadLine(actRegisterDeadLine);
				activityVO.setTotalStar(totalStar);
				activityVO.setTotalEvaluator(totalEvaluator);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/activity/update_activity_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				ActivityService activitySvc = new ActivityService();
				activityVO = activitySvc.updateActivity(actNo,actType,actName,actDate,actLocation,actDirection,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,totalStar,totalEvaluator);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("activityVO", activityVO); 
				String url = "/back_end/activity/listOneActivity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/activity/update_activity_input.jsp");
//				failureView.forward(req, res);
			}
		}
	
	
}
