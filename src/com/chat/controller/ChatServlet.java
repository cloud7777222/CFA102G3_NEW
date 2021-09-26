package com.chat.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		
		if("chat_Room".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String memberAccount=req.getParameter("memberAccount");
			
			MemberService memberService=new MemberService();
			MemberVO memberVO=new MemberVO();
			memberVO=memberService.getOneMember(memberAccount);
			/***************************2.準備轉交(Send the Success view)*************/
			req.setAttribute("memberVO", memberVO);
			String url = "/front_end/chat/chatRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			}
			/***************************其他可能的錯誤處理*************************************/
			catch(Exception e) {
				errorMsgs.add("資料傳送失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front_end/member/logInMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}//我是post
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
