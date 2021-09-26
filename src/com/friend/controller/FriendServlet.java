package com.friend.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.friend.model.FriendService;
import com.friend.model.FriendVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("member_Profile_By_Other".equals(action)) {//browseMember.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String memberAccountA=req.getParameter("memberAccountA");
			String memberAccountB=req.getParameter("memberAccountB");
			/***************************2.開始瀏覽其他使用者***************************************/
			MemberService memberService=new MemberService();
			MemberVO memberVOA=new MemberVO();
			memberVOA=memberService.getOneMember(memberAccountA);
			MemberVO memberVOB=new MemberVO();
			memberVOB=memberService.getOneMember(memberAccountB);
			
			Integer memberNoA=memberVOA.getMemberNo();//檢查好友狀態
			Integer memberNoB=memberVOB.getMemberNo();
			FriendService friendService=new FriendService();
			boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
			
			/***************************3.送出完成,準備轉交(Send the Success view)*************/
			if(!check) {
				req.setAttribute("memberVOA", memberVOA);
				req.setAttribute("memberVOB", memberVOB);
				
				String url="/front_end/friend/memberProfileByOther.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
				successView.forward(req, res);
			}
			
			if(check) {
				FriendVO friendVO;
				friendVO=friendService.getOneFriend(memberNoA, memberNoB);
				if(friendVO.getFriendStatus().equals(1)) {
					req.setAttribute("memberVOA", memberVOA);
					req.setAttribute("memberVOB", memberVOB);

					String url="/front_end/friend/memberProfileByOtherAccept.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
				if(friendVO.getFriendStatus().equals(2)) {
					req.setAttribute("memberVOA", memberVOA);
					req.setAttribute("memberVOB", memberVOB);

					String url="/front_end/friend/memberProfileByOtherRequest.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
				if(friendVO.getFriendStatus().equals(3)) {
					req.setAttribute("memberVOA", memberVOA);
					req.setAttribute("memberVOB", memberVOB);

					String url="/front_end/friend/memberProfileByOtherFriend.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
			}
			}
			/***************************其他可能的錯誤處理*************************************/
			catch(Exception e) {
				errorMsgs.add("資料傳送失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front_end/member/logInMember.jsp");//發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		if ("add_Friend".equals(action)) {//memberProfileByOther.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String memberAccountA=req.getParameter("memberAccountA");
			String memberAccountB=req.getParameter("memberAccountB");
			MemberService memberService=new MemberService();
			MemberVO memberVOA=new MemberVO();
			memberVOA=memberService.getOneMember(memberAccountA);
			MemberVO memberVOB=new MemberVO();
			memberVOB=memberService.getOneMember(memberAccountB);
			
			Integer memberNoA=memberVOA.getMemberNo();//檢查好友狀態
			Integer memberNoB=memberVOB.getMemberNo();
			
			/***************************2.開始送出邀請並轉交到各自的頁面***************************************/
			FriendService friendService=new FriendService();
				boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
				if(!check) {
				friendService.addFriend(memberNoA, memberNoB, 1, 2);
				friendService.addFriend(memberNoB, memberNoA, 2, 1);
				
				req.setAttribute("memberVOA", memberVOA);
				req.setAttribute("memberVOB", memberVOB);
				String url="/front_end/friend/memberProfileByOther.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //如果存在才加
				successView.forward(req, res);
				}
				if(check) {
					FriendVO friendVO=new FriendVO();
					friendVO=friendService.getOneFriend(memberNoA, memberNoB);
					if(friendVO.getFriendStatus()==1) {
						req.setAttribute("memberVOA", memberVOA);
						req.setAttribute("memberVOB", memberVOB);
						String url="/front_end/friend/memberProfileByOtherAccept.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 不存在要轉交
						successView.forward(req, res);
					}
					if(friendVO.getFriendStatus()==2) {
						req.setAttribute("memberVOA", memberVOA);
						req.setAttribute("memberVOB", memberVOB);
						String url="/front_end/friend/memberProfileByOtherRequest.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 不存在要轉交
						successView.forward(req, res);
					}
					if(friendVO.getFriendStatus()==3) {
						req.setAttribute("memberVOA", memberVOA);
						req.setAttribute("memberVOB", memberVOB);
						String url="/front_end/friend/memberProfileByOtherFriend.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 不存在要轉交
						successView.forward(req, res);
					}
						
				}
			}
			/***************************其他可能的錯誤處理*************************************/
			catch(Exception e) {
				errorMsgs.add("資料傳送失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front_end/member/logInMember.jsp");//發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		if ("response_Friend".equals(action)) {//memberProfileByOther.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String memberAccountA=req.getParameter("memberAccountA");
			String memberAccountB=req.getParameter("memberAccountB");
			
			MemberService memberService=new MemberService();
			MemberVO memberVOA=new MemberVO();
			memberVOA=memberService.getOneMember(memberAccountA);
			MemberVO memberVOB=new MemberVO();
			memberVOB=memberService.getOneMember(memberAccountB);
			
			Integer memberNoA=memberVOA.getMemberNo();//檢查好友狀態
			Integer memberNoB=memberVOB.getMemberNo();
			
			FriendService friendService=new FriendService();
			
			 /***************************2.開始接受好友邀請***************************************/
			FriendVO friendVO=friendService.getOneFriend(memberNoA, memberNoB);
			FriendVO memberBFriendVO=friendService.getOneFriend(memberNoB, memberNoA);
			Integer getFriendRequest=new Integer(memberBFriendVO.getFriendRequest());//尋找過去的好友關係
				
			boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
			if(!check) {
				req.setAttribute("memberVOA", memberVOA);
				req.setAttribute("memberVOB", memberVOB);
				String url="/front_end/friend/memberProfileByOther.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
				successView.forward(req, res);
			}
			if(check) {
				if(friendVO.getFriendStatus().equals(1)) {
					friendService.updateFriend(memberNoA, memberNoB, getFriendRequest, 3);
					friendService.updateFriend(memberNoB, memberNoA, getFriendRequest, 3);
					req.setAttribute("memberVOA", memberVOA);
					req.setAttribute("memberVOB", memberVOB);
					String url="/front_end/friend/memberProfileByOtherAccept.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
					}
				if(friendVO.getFriendStatus().equals(2)) {
					req.setAttribute("memberVOA", memberVOA);
					req.setAttribute("memberVOB", memberVOB);
					String url="/front_end/friend/memberProfileByOtherRequest.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
					}
				if(friendVO.getFriendStatus().equals(3)) {
				req.setAttribute("memberVOA", memberVOA);
				req.setAttribute("memberVOB", memberVOB);
				String url="/front_end/friend/memberProfileByOtherFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
				successView.forward(req, res);
				}
			}
			}
			/***************************其他可能的錯誤處理*************************************/
			catch(Exception e) {
				errorMsgs.add("資料傳送失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front_end/member/logInMember.jsp");//發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		if ("delete_Friend".equals(action)) {//memberProfileByOther.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String memberAccountA=req.getParameter("memberAccountA");
			String memberAccountB=req.getParameter("memberAccountB");
			
			MemberService memberService=new MemberService();
			MemberVO memberVOA=new MemberVO();
			memberVOA=memberService.getOneMember(memberAccountA);
			MemberVO memberVOB=new MemberVO();
			memberVOB=memberService.getOneMember(memberAccountB);
			
			Integer memberNoA=memberVOA.getMemberNo();//檢查好友狀態
			Integer memberNoB=memberVOB.getMemberNo();
			FriendService friendService=new FriendService();
			
			 /***************************2.開始取消好友邀請或刪除好友***************************************/
			boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
			if(!check) {
				req.setAttribute("memberVOA", memberVOA);
				req.setAttribute("memberVOB", memberVOB);
				String url="/front_end/friend/memberProfileByOther.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
				successView.forward(req, res);
			}
			if(check) {
				FriendVO friendVO=friendService.getOneFriend(memberNoA, memberNoB);
				if(friendVO.getFriendStatus().equals(1)) {
					friendService.deleteFriend(memberNoA, memberNoB);
					friendService.deleteFriend(memberNoB, memberNoA);
					
					req.setAttribute("memberVOA", memberVOA);
					req.setAttribute("memberVOB", memberVOB);
					String url="/front_end/friend/memberProfileByOtherAccept.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
					
					// sendRedirect
				}
				if(friendVO.getFriendStatus().equals(2)) {
					
					friendService.deleteFriend(memberNoA, memberNoB);
					friendService.deleteFriend(memberNoB, memberNoA);
					
					req.setAttribute("memberVOA", memberVOA);
					req.setAttribute("memberVOB", memberVOB);
					String url="/front_end/friend/memberProfileByOtherRequest.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
				if(friendVO.getFriendStatus().equals(3)) {
					friendService.deleteFriend(memberNoA, memberNoB);//刪除會員A的好友狀態
					friendService.deleteFriend(memberNoB, memberNoA);
					req.setAttribute("memberVOA", memberVOA);
					req.setAttribute("memberVOB", memberVOB);
					
					String url="/front_end/friend/memberProfileByOtherFriend.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
			}
			}
			/***************************其他可能的錯誤處理*************************************/
			catch(Exception e) {
				errorMsgs.add("資料傳送失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front_end/member/logInMember.jsp");//發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}
		
		
		if("response_Friend_Request".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String memberAccountA=req.getParameter("memberAccountA");
			String memberAccountB=req.getParameter("memberAccountB");
			
			MemberService memberService=new MemberService();
			MemberVO memberVOA=new MemberVO();
			memberVOA=memberService.getOneMember(memberAccountA);
			MemberVO memberVOB=new MemberVO();
			memberVOB=memberService.getOneMember(memberAccountB);
			
			Integer memberNoA=memberVOA.getMemberNo();//檢查好友狀態
			Integer memberNoB=memberVOB.getMemberNo();
			
			FriendService friendService=new FriendService();
			
			 /***************************2.開始接受好友邀請***************************************/
			boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
			if(!check) {
				String url="/front_end/friend/browseMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
				successView.forward(req, res);
			}
			if(check) {
				FriendVO friendVO=friendService.getOneFriend(memberNoA, memberNoB);
				if(friendVO.getFriendStatus().equals(1)) {
					FriendVO memberBFriendVO=friendService.getOneFriend(memberNoB, memberNoA);
					Integer getFriendRequest=new Integer(memberBFriendVO.getFriendRequest());//尋找過去的好友關係
					friendService.updateFriend(memberNoA, memberNoB, getFriendRequest, 3);
					friendService.updateFriend(memberNoB, memberNoA, getFriendRequest, 3);
					
					String url="/front_end/friend/browseMember.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
				if(friendVO.getFriendStatus().equals(2)) {
					String url="/front_end/friend/browseMember.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
				if(friendVO.getFriendStatus().equals(3)) {
					String url="/front_end/friend/browseMember.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
			}
			}
			/***************************其他可能的錯誤處理*************************************/
			catch(Exception e) {
				errorMsgs.add("資料傳送失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front_end/member/logInMember.jsp");//發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}
		
		
		if("delete_Friend_Request".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String memberAccountA=req.getParameter("memberAccountA");
			String memberAccountB=req.getParameter("memberAccountB");
			
			MemberService memberService=new MemberService();
			MemberVO memberVOA=new MemberVO();
			memberVOA=memberService.getOneMember(memberAccountA);
			MemberVO memberVOB=new MemberVO();
			memberVOB=memberService.getOneMember(memberAccountB);
			
			Integer memberNoA=memberVOA.getMemberNo();//檢查好友狀態
			Integer memberNoB=memberVOB.getMemberNo();
			
			FriendService friendService=new FriendService();
			
			 /***************************2.開始接受好友邀請***************************************/
			boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
			if(!check) {
				String url="/front_end/friend/browseMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
				successView.forward(req, res);
			}
			if(check) {
				FriendVO friendVO=friendService.getOneFriend(memberNoA, memberNoB);
				if(friendVO.getFriendStatus().equals(1)) {
					friendService.deleteFriend(memberNoA, memberNoB);
					friendService.deleteFriend(memberNoB, memberNoA);
					
					String url="/front_end/friend/browseMember.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
					
					// sendRedirect
				}
				if(friendVO.getFriendStatus().equals(2)) {
					String url="/front_end/friend/browseMember.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
				if(friendVO.getFriendStatus().equals(3)) {
					friendService.deleteFriend(memberNoA, memberNoB);
					friendService.deleteFriend(memberNoB, memberNoA);
					String url="/front_end/friend/browseMember.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交/memberProfileByOther.jsp.jsp
					successView.forward(req, res);
				}
			}
			}
			/***************************其他可能的錯誤處理*************************************/
			catch(Exception e) {
				errorMsgs.add("資料傳送失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front_end/member/logInMember.jsp");//發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}
		
		
		if("delete_Friend_By_FriendList".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String memberAccountA=req.getParameter("memberAccountA");
			String memberAccountB=req.getParameter("memberAccountB");
			
			MemberService memberService=new MemberService();
			MemberVO memberVOA=new MemberVO();
			memberVOA=memberService.getOneMember(memberAccountA);
			MemberVO memberVOB=new MemberVO();
			memberVOB=memberService.getOneMember(memberAccountB);
			
			Integer memberNoA=memberVOA.getMemberNo();//檢查好友狀態
			Integer memberNoB=memberVOB.getMemberNo();
			
			FriendService friendService=new FriendService();
			
			 /***************************2.開始接受好友邀請***************************************/
			boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
			if(!check) {
				req.setAttribute("memberVO", memberVOA);
				String url = "/front_end/chat/chatRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			if(check) {
				FriendVO friendVO=friendService.getOneFriend(memberNoA, memberNoB);
				if(friendVO.getFriendStatus().equals(1)) {
					req.setAttribute("memberVO", memberVOA);
					String url = "/front_end/chat/chatRoom.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					// sendRedirect
				}
				if(friendVO.getFriendStatus().equals(2)) {
					req.setAttribute("memberVO", memberVOA);
					String url = "/front_end/chat/chatRoom.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				if(friendVO.getFriendStatus().equals(3)) {
					req.setAttribute("memberVO", memberVOA);
					friendService.deleteFriend(memberNoA, memberNoB);
					friendService.deleteFriend(memberNoB, memberNoA);
					String url = "/front_end/chat/chatRoom.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
			}
			}
			/***************************其他可能的錯誤處理*************************************/
			catch(Exception e) {
				errorMsgs.add("資料傳送失敗:"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front_end/member/logInMember.jsp");//發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}
	}//我是do get

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
