package com.expert.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.expert.model.ExpertService;
import com.expert.model.ExpertVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ExpertServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		  if ("insert".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					Integer expertNo = new Integer(req.getParameter("expertNo").trim());
					Integer expertGenreNo = new Integer(req.getParameter("expertGenreNo").trim());
					
					String exAcount = req.getParameter("exAcount");
					String exAcountReg = "^[(a-zA-Z0-9)]{6,16}$";
					if (exAcount  == null || exAcount .trim().length() == 0) {
						errorMsgs.add("專家帳號請勿空白");
					} else if(!exAcount .trim().matches(exAcount)) { 
						errorMsgs.add("帳號請輸入英文字母 , 且長度必需在6到16之間");
		            }
					
					String  exPassword = req.getParameter(" exPassword");
					String  exPasswordReg = "^[(a-zA-Z0-9)]{6,16}$";
					if ( exPassword  == null ||  exPassword .trim().length() == 0) {
						errorMsgs.add("請輸入密碼");
					} else if(! exPassword .trim().matches( exPassword)) { 
						errorMsgs.add("密碼請輸入英文字母 , 且長度必需在6到16之間");
		            }
					Part part = req.getPart("exPhoto");
					byte[]  exPhoto = null;
					InputStream ipicture = part.getInputStream(); 
					 exPhoto = new byte[ipicture .available()];
					ipicture .read( exPhoto);
					ipicture .close();
					if (part== null || part.getSize()== 0) {
						errorMsgs.add("請重新上傳圖片");
					}	
					
					String  exName	 = req.getParameter("exName");
					String  exNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
					if ( exName== null ||  exName.trim().length() == 0) {
						errorMsgs.add("專家姓名: 請勿空白");
					} else if(!exName.trim().matches( exNameReg)) { 
						errorMsgs.add("專家姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
		            }
					
					Integer exGender = new Integer(req.getParameter(" exGender").trim());
					

					String exPhone=req.getParameter("exPhone");
					String exPhoneReg="^09[0-9]{8}$";
					if(exPhone==null||exPhone.trim().length()==0) {
						errorMsgs.add("手機號碼請勿空白");
					}
					else if(!exPhone.trim().matches(exPhone)) {
						errorMsgs.add("請輸入正確手機號碼");
					}
					
					 String  exEmail=req.getParameter(" exEmail").trim();
					 String  exEmailReg="^[a-zA-Z0-9_.-]{1,18}@[a-z0-9]{1,10}.[a-z]{1,10}$";
					 if( exEmail==null|| exEmail.trim().length()==0) {
						 errorMsgs.add("專家信箱請勿空白");
					 }
					 else if(! exEmail.trim().matches(exEmail)) {
						 errorMsgs.add("請輸入正確信箱格式");
					 }
					 
					 String exAdress=req.getParameter("exAdress".trim());
						String exAdressReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,100}$";
						if( exAdress==null|| exAdress.trim().length()==0) {
							errorMsgs.add("地址請勿空白");
						}
						else if(! exAdress.trim().matches( exAdress)) {
							errorMsgs.add("地址: 只能是中、英文字母、數字, 且長度必需在2到100之間");
						}
						
				    String  exIntroduce=req.getParameter(" exIntroduce".trim());
							String  exIntroduceReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,300}$";
							if(  exIntroduce==null||exIntroduce.trim().length()==0) {
								errorMsgs.add("自我介紹請勿空白");
							}
							else if(! exIntroduce.trim().matches(exIntroduce)) {
								errorMsgs.add("自我介紹: 只能是中、英文字母、數字, 且長度必需在2到300之間");
							}
					Integer exPoint = new Integer(req.getParameter("exPoint").trim());
					
					ExpertVO expertVO = new ExpertVO();
					expertVO.setExpertNo(expertNo);
					expertVO.setExpertGenreNo(expertGenreNo);
					expertVO.setExAcount(exAcount);
					expertVO.setExPassword(exPassword);
					expertVO.setExPhoto(exPhoto);
					expertVO.setExName(exName);
					expertVO.setExGender(exGender);
					expertVO.setExPhone(exPhone);
					expertVO.setExEmail(exEmail);
					expertVO.setExAdress(exAdress);
					expertVO.setExIntroduce(exIntroduce);
					expertVO.setExPoint(exPoint);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("expertVO", expertVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/expert/addExpert.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					ExpertService expertSvc =new ExpertService();
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/expert/listAllExpert.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/expert/addExpert.jsp");
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
				Integer expertNo = new Integer(req.getParameter("expertNo"));
				
				/***************************2.開始查詢資料****************************************/
				ExpertService expertSvc = new ExpertService();
				ExpertVO expertVO = expertSvc.getOneExpert(expertNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
	         	req.setAttribute("expertVO", expertVO);  
	            String url = "/expert/update_expert_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			 } catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/expert/listAllExpert.jsp");
				failureView.forward(req, res);
			}
		}
		
if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer expertNo = new Integer(req.getParameter("expertNo"));
			Integer expertGenreNo = new Integer(req.getParameter("expertGenreNo"));
			
			String exAcount = req.getParameter("exAcount");
			String exAcountReg = "^[(a-zA-Z0-9)]{6,16}$";
			if (exAcount  == null || exAcount .trim().length() == 0) {
				errorMsgs.add("專家帳號請勿空白");
			} else if(!exAcount .trim().matches(exAcount)) { 
				errorMsgs.add("帳號請輸入英文字母 , 且長度必需在6到16之間");
            }
			
			String  exPassword = req.getParameter("exPassword");
			String  exPasswordReg = "^[(a-zA-Z0-9)]{6,16}$";
			if ( exPassword  == null ||  exPassword .trim().length() == 0) {
				errorMsgs.add("請輸入密碼");
			} else if(! exPassword .trim().matches( exPassword)) { 
				errorMsgs.add("密碼請輸入英文字母 , 且長度必需在6到16之間");
            }
			Part part = req.getPart("exPhoto");
			byte[]  exPhoto = null;
			InputStream ipicture = part.getInputStream(); 
			 exPhoto = new byte[ipicture .available()];
			ipicture .read( exPhoto);
			ipicture .close();
			if (part== null || part.getSize()== 0) {
				errorMsgs.add("請重新上傳圖片");
			}	
			
			String  exName	 = req.getParameter("exName");
			String  exNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if ( exName== null ||  exName.trim().length() == 0) {
				errorMsgs.add("專家姓名: 請勿空白");
			} else if(!exName.trim().matches( exNameReg)) { 
				errorMsgs.add("專家姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
            }
			
			Integer exGender = new Integer(req.getParameter(" exGender"));
			

			String exPhone=req.getParameter("exPhone");
			String exPhoneReg="^09[0-9]{8}$";
			if(exPhone==null||exPhone.trim().length()==0) {
				errorMsgs.add("手機號碼請勿空白");
			}
			else if(!exPhone.trim().matches(exPhone)) {
				errorMsgs.add("請輸入正確手機號碼");
			}
			
			 String  exEmail=req.getParameter(" exEmail");
			 String  exEmailReg="^[a-zA-Z0-9_.-]{1,18}@[a-z0-9]{1,10}.[a-z]{1,10}$";
			 if( exEmail==null|| exEmail.trim().length()==0) {
				 errorMsgs.add("專家信箱請勿空白");
			 }
			 else if(! exEmail.trim().matches(exEmail)) {
				 errorMsgs.add("請輸入正確信箱格式");
			 }
			 
			 String exAdress=req.getParameter("exAdress".trim());
				String exAdressReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,100}$";
				if( exAdress==null|| exAdress.trim().length()==0) {
					errorMsgs.add("地址請勿空白");
				}
				else if(! exAdress.trim().matches( exAdress)) {
					errorMsgs.add("地址: 只能是中、英文字母、數字, 且長度必需在2到100之間");
				}
				
		    String  exIntroduce=req.getParameter(" exIntroduce".trim());
					String  exIntroduceReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,300}$";
					if(  exIntroduce==null||exIntroduce.trim().length()==0) {
						errorMsgs.add("自我介紹請勿空白");
					}
					else if(! exIntroduce.trim().matches(exIntroduce)) {
						errorMsgs.add("自我介紹: 只能是中、英文字母、數字, 且長度必需在2到300之間");
					}
			Integer exPoint = new Integer(req.getParameter("exPoint"));
				

			
			ExpertVO expertVO = new ExpertVO();
			expertVO.setExpertNo(expertNo);
			expertVO.setExpertGenreNo(expertGenreNo);
			expertVO.setExAcount(exAcount);
			expertVO.setExPassword(exPassword);
			expertVO.setExPhoto(exPhoto);
			expertVO.setExName(exName);
			expertVO.setExGender(exGender);
			expertVO.setExPhone(exPhone);
			expertVO.setExEmail(exEmail);
			expertVO.setExAdress(exAdress);
			expertVO.setExIntroduce(exIntroduce);
			expertVO.setExPoint(exPoint);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("expertVO", expertVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/expert/update_expert_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				ExpertService expertSvc = new ExpertService();
				expertVO = expertSvc.updateExpert( expertNo,expertGenreNo,exAcount,exPassword,exPhoto,exName,exGender,exPhone,exEmail,exAdress,exIntroduce);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("expertVO", expertVO); 
				String url = "/expert/listOneExpert.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/expert/update_expert_input.jsp");
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
				Integer expertNo = new Integer(req.getParameter("expertNo"));
				
				/***************************2.開始刪除資料***************************************/
				ExpertService expertSvc = new ExpertService();
				expertSvc.deleteExpert(expertNo);
              
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/													
				String url = "/expert/listAllExpert.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/expert/listAllExpert.jsp");
				failureView.forward(req, res);
			}
		}
			if ("getOne_For_Display".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String str = req.getParameter("expertNo");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入專家編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/expert/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					
					Integer expertNo = null;
					try {
						expertNo = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("專家編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/expert/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始查詢資料*****************************************/
					ExpertService expertSvc = new ExpertService();
					ExpertVO expertVO = expertSvc.getOneExpert(expertNo);
					if (expertVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/expert/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("expertVO", expertVO); 
					String url = "/expert/listOneExpert.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);


					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/expert/select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
		}
}

