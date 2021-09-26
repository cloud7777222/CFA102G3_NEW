package com.emp.controller;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.emp.model.EmpService;
import com.emp.model.EmpVO;



@WebServlet("/emp/emp.do")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String empname = req.getParameter("empname");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工名稱請勿空白");
				}else if(!empname.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String empaccount = req.getParameter("empaccount");
				String empaccReg = "^[a-zA-Z0-9]";
				if (empaccount == null || empaccount.trim().length() == 0) {
					errorMsgs.add("員工帳號請勿空白");
				}else if(empaccount.trim().matches(empaccReg)) { 
					errorMsgs.add("員工帳號: 內有非合法字元");
	            }
				EmpVO empVO = new EmpVO();
				EmpService empSvc = new EmpService();
				for(EmpVO emp:empSvc.getAll()) {
					if(empaccount.equals(emp.getEmpaccount())) {
						errorMsgs.add("員工帳號重複");
					}
				}
					
				
				String emppassword = req.getParameter("emppassword");
				String emppwReg = "^[a-zA-Z0-9]";
				if (emppassword == null || emppassword.trim().length() == 0) {
					errorMsgs.add("員工密碼請勿空白");
				}else if(emppassword.trim().matches(emppwReg)) { 
					errorMsgs.add("員工密碼: 內有非合法字元");
	            }
				Integer empstate = new Integer(req.getParameter("empstate"));
				
				empVO.setEmpname(empname);
				empVO.setEmpaccount(empaccount);
				empVO.setEmppassword(emppassword);
				empVO.setEmpstate(empstate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				empVO = empSvc.addEmp(empaccount, emppassword, empname, empstate);
				
				String url = "/back_end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);        
				String url = "/back_end/emp/updateEmp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer empno = new Integer(req.getParameter("empno"));
				
				String empname = req.getParameter("empname");
				
				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工名稱請勿空白");
				}
				String empaccount = req.getParameter("empaccount");
				String empaccReg = "^[a-zA-Z0-9]";
				if (empaccount == null || empaccount.trim().length() == 0) {
					errorMsgs.add("員工帳號請勿空白");
				}else if(empaccount.trim().matches(empaccReg)) { 
					errorMsgs.add("員工帳號: 內有非合法字元");
	            }
				
				
				String emppassword = req.getParameter("emppassword");
				String emppwReg = "^[a-zA-Z0-9]";
				if (emppassword == null || emppassword.trim().length() == 0) {
					errorMsgs.add("員工密碼請勿空白");
				}else if(emppassword.trim().matches(emppwReg)) { 
					errorMsgs.add("員工密碼: 內有非合法字元");
	            }
				Integer empstate = new Integer(req.getParameter("empstate"));
				EmpVO empVO = new EmpVO();
				empVO.setEmpname(empname);
				empVO.setEmpaccount(empaccount);
				empVO.setEmppassword(emppassword);
				empVO.setEmpstate(empstate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/emp/updateEmp_input.jsp");
					failureView.forward(req, res);
					return;
				}
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(empaccount, emppassword, empname, empstate,empno);
				String url = "/back_end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/updateEmp_input.jsp");
				failureView.forward(req, res);
			}
		}
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.開始刪除資料***************************************/
				EmpService empSvc = new EmpService();
				empSvc.delEmp(empno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer empno = new Integer(req.getParameter("empno"));
				String Path = req.getContextPath();
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(Path+"/back_end/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); 
				String url = "/back_end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(req.getContextPath()+"/back_end/index.jsp");
				failureView.forward(req, res);
			}
		}
	
	
	
	}
	
}
