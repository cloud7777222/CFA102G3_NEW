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
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;


public class LoginoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				String empaccount = req.getParameter("empaccount");
				
				if (empaccount == null || empaccount.isEmpty()) {
					errorMsgs.add("請輸入帳號");
				}
				String emppassword = req.getParameter("emppassword");
				if (emppassword == null || emppassword.isEmpty()) {
					errorMsgs.add("請輸入密碼");
				}
				if (!errorMsgs.isEmpty()) {
					String url = "/back_end/Elogin.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}
				
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.Elogin(empaccount, emppassword);
				
				
				if (empVO == null) {
					errorMsgs.add("請確認帳號密碼");
					RequestDispatcher forwarPage = req.getRequestDispatcher("/back_end/Elogin.jsp");
					forwarPage.forward(req, res);
				} else {
					HttpSession session = req.getSession();
					session.setAttribute("empVO", empVO);
					res.sendRedirect(req.getContextPath()+"/back_end/index.jsp");
				}
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/Elogin.jsp");
				failureView.forward(req, res);
			}
		
	}//else if
	}
}
