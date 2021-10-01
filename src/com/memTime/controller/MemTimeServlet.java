package com.memTime.controller;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONArray;

import com.dateappoorder.model.*;
import com.member.model.MemberVO;

public class MemTimeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String datingCheckForUpdate = null;
		try {
			datingCheckForUpdate = req.getAttribute("datingCheckForUpdate").toString();
		}catch(Exception e) {
			System.out.println("無此值");
		}

		if ("datingCheck".equals(action)) { // 來自dateappoorder_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				HttpSession session = req.getSession();
//				session.getAttribute(name)
				MemberVO memberVO= (MemberVO) session.getAttribute("memberVO");
				String memA = String.valueOf(memberVO.getMemberNo());
				System.out.println("memA="+memA);
				
				if (memA == null || (memA.trim()).length() == 0) {
					errorMsgs.add("無會員資料，請重新選擇");
				}
				String memB = req.getParameter("memberNoB");
				System.out.println("memB="+memB);
				if (memB == null || (memB.trim()).length() == 0) {
					errorMsgs.add("無會員資料，請重新選擇");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println("memA="+memA);
				System.out.println("memB="+memB);
				/*************************** 2.開始查詢資料 *****************************************/
				DateappoorderService dateappoorderSvcA = new DateappoorderService();
				List<DateappoorderVO> orderListA = dateappoorderSvcA.getAll();
				System.out.println("allOrder:" + orderListA.size());
				List<DateappoorderVO> newListA = orderListA.stream()
						.filter(i -> i.getMemberNoA().toString().equals(memA) || i.getMemberNoB().toString().equals(memA)) // 會員的訂單
						.filter(i -> i.getDateAppoDate().getTime() > new java.util.Date().getTime()) // 約會時間大於現在
						.collect(Collectors.toList());
				for (DateappoorderVO i : newListA) {
					System.out.println(i);
				}
				String jsonDataA = new JSONArray(newListA).toString();
				System.out.println("List to JSON: " + jsonDataA);
				
				
				DateappoorderService dateappoorderSvcB = new DateappoorderService();
				List<DateappoorderVO> orderListB = dateappoorderSvcB.getAll();
				System.out.println("allOrder:" + orderListB.size());
				List<DateappoorderVO> newListB = orderListB.stream()
						.filter(i -> i.getMemberNoA().toString().equals(memB) || i.getMemberNoB().toString().equals(memB)) // 會員的訂單
						.filter(i -> i.getDateAppoDate().getTime() > new java.util.Date().getTime()) // 約會時間大於現在
						.collect(Collectors.toList());
				for (DateappoorderVO i : newListB) {
					System.out.println(i);
				}
				String jsonDataB = new JSONArray(newListB).toString();
				System.out.println("List to JSON: " + jsonDataB);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateappoorder/addAd.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("jsonDataA", jsonDataA);
				req.setAttribute("jsonDataB", jsonDataB);// 資料庫取出的dateappoorderVO物件,存入req
				String url = "/front_end/dateappoorder/addDateappoorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDateappoorder.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("datingCheckForUpdate".equals(datingCheckForUpdate)) { // 來自update的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				HttpSession session = req.getSession();
//				session.getAttribute(name)
				MemberVO memberVO= (MemberVO) session.getAttribute("memberVO");
				Integer memA = memberVO.getMemberNo();
				System.out.println("memA="+memA);
				
				if (memA == null) {
					errorMsgs.add("無A會員資料，請重新選擇");
				}
				
				DateappoorderVO dateappoorderVO=(DateappoorderVO)req.getAttribute("dateappoorderVO");
				Integer memB = dateappoorderVO.getMemberNoA()==memA
						?dateappoorderVO.getMemberNoB()
								:dateappoorderVO.getMemberNoA();
				System.out.println("memB="+memB);
				if (memB == null) {
					errorMsgs.add("無B會員資料，請重新選擇");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println("memA="+memA);
				System.out.println("memB="+memB);
				/*************************** 2.開始查詢資料 *****************************************/
				DateappoorderService dateappoorderSvcA = new DateappoorderService();
				List<DateappoorderVO> orderListA = dateappoorderSvcA.getAll();
				System.out.println("allOrder:" + orderListA.size());
				List<DateappoorderVO> newListA = orderListA.stream()
						.filter(i -> i.getMemberNoA()==memA || i.getMemberNoB()==memA) // 會員的訂單
						.filter(i -> i.getDateAppoDate().getTime() > new java.util.Date().getTime()) // 約會時間大於現在
						.collect(Collectors.toList());
				for (DateappoorderVO i : newListA) {
					System.out.println(i);
				}
				String jsonDataA = new JSONArray(newListA).toString();
				System.out.println("List to JSON: " + jsonDataA);
				
				
				DateappoorderService dateappoorderSvcB = new DateappoorderService();
				List<DateappoorderVO> orderListB = dateappoorderSvcB.getAll();
				System.out.println("allOrder:" + orderListB.size());
				List<DateappoorderVO> newListB = orderListB.stream()
						.filter(i -> i.getMemberNoA()==memB || i.getMemberNoB()==memB) // 會員的訂單
						.filter(i -> i.getDateAppoDate().getTime() > new java.util.Date().getTime()) // 約會時間大於現在
						.collect(Collectors.toList());
				for (DateappoorderVO i : newListB) {
					System.out.println(i);
				}
				String jsonDataB = new JSONArray(newListB).toString();
				System.out.println("List to JSON: " + jsonDataB);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dateappoorder/addAd.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("jsonDataA", jsonDataA);
				req.setAttribute("jsonDataB", jsonDataB);// 資料庫取出的dateappoorderVO物件,存入req
				String url = "/front_end/dateappoorder/update_dateappoorder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDateappoorder.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

	}
}
