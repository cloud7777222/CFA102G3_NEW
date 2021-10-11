package com.member.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, String> authCodeMap = new HashMap<String, String>();
	private static Map<String, MemberVO> addMemberMap = new HashMap<String, MemberVO>();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert_Member".equals(action)) {// addMember.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memberAccount = req.getParameter("memberAccount").trim();
				String memberAccountReg = "^[a-zA-Z0-9]{6,10}$";

				MemberService memberService = new MemberService();
				boolean checkAccount;
				checkAccount = memberService.checkAccount(memberAccount);// 判斷這個是否是true

				if (memberAccount == null || memberAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if (!memberAccount.trim().matches(memberAccountReg)) {
					errorMsgs.add("帳號請輸入6到10英數混合字元不含特殊符號");
				} else if (checkAccount) {
					errorMsgs.add("帳號已有人申請");
				}

				String memberPassword = req.getParameter("memberPassword").trim();
				String memberPasswordReg = "^[a-zA-Z0-9]{6,10}$";
				if (memberPassword == null || memberPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if (!memberPassword.trim().matches(memberPasswordReg)) {
					errorMsgs.add("密碼請輸入6到10英數混合字元不含特殊符號");
				}

				String memberPasswordCheck = req.getParameter("memberPasswordCheck").trim();
				if (!memberPassword.equals(memberPasswordCheck)) {
					errorMsgs.add("兩次密碼輸入不相同");
				}

				String memberEmail = req.getParameter("memberEmail").trim();
				String memberEmailReg = "^[a-zA-Z0-9_.-]{1,18}@[a-z0-9]{1,10}.[a-z]{1,10}$";

				boolean checkEmail;
				checkEmail = memberService.checkEmail(memberEmail);

				if (memberEmail == null || memberEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				} else if (!memberEmail.trim().matches(memberEmailReg)) {
					errorMsgs.add("請輸入正確信箱格式");
				} else if (checkEmail) {
					errorMsgs.add("信箱已有人申請");
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setMemberAccount(memberAccount);
				memberVO.setMemberPassword(memberPassword);
				memberVO.setMemberEmail(memberEmail);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);// 把錯誤訊息存起來回傳回去
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/addMember.jsp");// 轉到新增頁面
					failureView.forward(req, res);
					return;// 要return不然會往下跑
				}
				String auth = authcode.genAuthCode();
				String memberauth = "會員驗證碼:" + auth;
				System.out.println(auth);
				MailService.sendMail(memberEmail, "會員驗證", memberauth);
				/*************************** 2.開始新增資料 ***************************************/
				authCodeMap.put(memberAccount, auth);
				addMemberMap.put(memberAccount, memberVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				boolean resend = false;
				req.setAttribute("memberVO", memberVO);
				req.setAttribute("resend", resend);
				String url = "/front_end/member/authMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("send_Auth_Code".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memberAccount = req.getParameter("memberAccount").trim();
				String auth = authCodeMap.get(memberAccount);
				String memberAuthCode = req.getParameter("memberAuthCode").trim();
				if (memberAuthCode == null || memberAuthCode.trim().length() == 0) {
					errorMsgs.add("驗證碼請勿空白");
				} else if (!memberAuthCode.trim().equals(auth)) {
					errorMsgs.add("驗證碼錯誤");
				}
				MemberVO memberVO = addMemberMap.get(memberAccount);
				if (!errorMsgs.isEmpty()) {
					boolean resend = false;
					req.setAttribute("resend", resend);
					req.setAttribute("memberVO", memberVO);// 把錯誤訊息存起來回傳回去
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/authMember.jsp");// 轉到新增頁面
					failureView.forward(req, res);
					return;// 要return不然會往下跑
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memberService = new MemberService();
				memberService.addMember(memberVO);// Service在前面宣告過了

				FileInputStream fis = new FileInputStream("C:/test/dog.jpg");
				BufferedInputStream bis = new BufferedInputStream(fis);
				byte[] memberPhotoBytes = new byte[fis.available()];
				bis.read(memberPhotoBytes);
				bis.close();
				fis.close();

				memberService.updatePhoto(memberAccount, memberPhotoBytes);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				boolean beMember = true;
				req.setAttribute("beMember", beMember);
				req.setAttribute("memberVO", memberVO);
				String url = "/front_end/member/updateMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/authMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("resend_Auth_Code".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memberAccount = req.getParameter("memberAccount").trim();
				MemberVO memberVO = addMemberMap.get(memberAccount);// 拿註冊帳號使用者的memberVO
				/*************************** 2.重新寄驗證碼 ***************************************/
				String auth = authcode.genAuthCode();
				String memberauth = "會員驗證碼: " + auth;

				MailService.sendMail(memberVO.getMemberEmail(), "會員驗證", memberauth);
				authCodeMap.put(memberAccount, auth);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				boolean resend = true;
				req.setAttribute("memberVO", memberVO);
				req.setAttribute("resend", resend);
				String url = "/front_end/member/authMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 可能要做已重新寄送Email
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/authMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_Member".equals(action)) {// updateMember的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			try {

				String memberAccount = req.getParameter("memberAccount");// 從jsp抓帳號
				Part memberPhoto = req.getPart("memberPhoto");
				MemberService memberService = new MemberService();

				InputStream memberPhotoPart = memberPhoto.getInputStream();
				byte[] memberPhotoBytes = new byte[memberPhotoPart.available()];
				if (memberPhotoBytes.length == 0) {
					memberPhotoBytes = memberService.getPhoto(memberAccount);
				}
				memberPhotoPart.read(memberPhotoBytes);
				memberPhotoPart.close();

				String memberName = req.getParameter("memberName").trim();
				String memberNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
				if (memberName == null || memberName.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				} else if (!memberName.trim().matches(memberNameReg)) {
					errorMsgs.add("請輸入2到20個中英字元");
				}

				Integer memberGender = new Integer(req.getParameter("memberGender").trim());

				java.sql.Date memberBirthday = null;
				try {
					memberBirthday = java.sql.Date.valueOf(req.getParameter("memberBirthday").trim());
				} catch (IllegalArgumentException e) {
					memberBirthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}

				String memberJob = req.getParameter("memberJob").trim();
				String memberJobReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
				if (memberJob == null || memberJob.trim().length() == 0) {
					errorMsgs.add("職業請勿空白");
				} else if (!memberJob.trim().matches(memberJobReg)) {
					errorMsgs.add("請輸入2到20個中英字元");
				}

				String memberPhone = req.getParameter("memberPhone");
				String memberPhoneReg = "^09[0-9]{8}$";
				if (memberPhone == null || memberPhone.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				} else if (!memberPhone.trim().matches(memberPhoneReg)) {
					errorMsgs.add("請輸入正確手機號碼");
				}

				Integer memberCountry = new Integer(req.getParameter("memberCountry").trim());

				String memberIntroduce = req.getParameter("memberIntroduce".trim());
				String memberIntroduceReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,300}$";
				if (memberIntroduce == null || memberIntroduce.trim().length() == 0) {
					errorMsgs.add("自我介紹請勿空白");
				} else if (!memberIntroduce.trim().matches(memberIntroduceReg)) {
					errorMsgs.add("請輸入2到300個中英數字元");
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setMemberAccount(memberAccount);
				memberVO.setMemberPhoto(memberPhotoBytes);
				memberVO.setMemberName(memberName);
				memberVO.setMemberGender(memberGender);
				memberVO.setMemberBirthday(memberBirthday);
				memberVO.setMemberJob(memberJob);
				memberVO.setMemberPhone(memberPhone);
				memberVO.setMemberCountry(memberCountry);
				memberVO.setMemberIntroduce(memberIntroduce);
				if (!errorMsgs.isEmpty()) {
					boolean beMember = false;
					req.setAttribute("beMember", beMember);
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/updateMember.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/

				memberService.updateMember(memberVO);
				memberVO=memberService.getOneMember(memberAccount);
				memberService.updatePhoto(memberAccount, memberPhotoBytes);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO);
				req.getSession().setAttribute("memberVO", memberVO);
				String url = "/front_end/member/memberProfileByMe.jsp";// 路徑要換!!!!!!!!!!!!!!!!!!!!!!!
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/updateMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("member_Profile_By_Me".equals(action)) {// 來自memberProfileByMe.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberAccount = req.getParameter("memberAccount");
				/*************************** 2.準備轉交(Send the Success view) *************/
				MemberService memberService = new MemberService();
				MemberVO memberVO = new MemberVO();
				memberVO = memberService.getOneMember(memberAccount);

				req.setAttribute("memberVO", memberVO);
				String url = "/front_end/member/memberProfileByMe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交memberProfileByMe.jsp
				successView.forward(req, res);
			}
			/*************************** 其他可能的錯誤處理 *************************************/
			catch (Exception e) {
				errorMsgs.add("資料傳送失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/lohInMember.jsp");// 發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}

		if ("update_Member_Profile".equals(action)) {// 來自memberProfileByMe.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberAccount = req.getParameter("memberAccount");
				/*************************** 2.準備轉交(Send the Success view) *************/
				MemberService memberService = new MemberService();
				MemberVO memberVO = new MemberVO();
				memberVO = memberService.getOneMember(memberAccount);

				boolean beMember = false;
				req.setAttribute("beMember", beMember);
				req.setAttribute("memberVO", memberVO);
				String url = "/front_end/member/updateMember.jsp";// 再寫一隻送是因為要把她導到修改頁面
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交updatePointMember.jsp
				successView.forward(req, res);
			}
			/*************************** 其他可能的錯誤處理 *************************************/
			catch (Exception e) {
				errorMsgs.add("資料傳送失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/memberProfileByMe.jsp");// 發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}

		if ("update_Password_Member_Profile".equals(action)) {// 來自memberProfileByMe.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberAccount = req.getParameter("memberAccount");
				/*************************** 2.準備轉交(Send the Success view) *************/
				MemberService memberService = new MemberService();
				MemberVO memberVO = new MemberVO();
				memberVO = memberService.getOneMember(memberAccount);
				req.setAttribute("memberVO", memberVO);
				String url = "/front_end/member/updatePasswordMember.jsp";// 再寫一隻送是因為要把她導到修改頁面
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交updatePointMember.jsp
				successView.forward(req, res);
			}
			/*************************** 其他可能的錯誤處理 *************************************/
			catch (Exception e) {
				errorMsgs.add("資料傳送失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/memberProfileByMe.jsp");// 發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}

		if ("update_Password_Member".equals(action)) {// 來自updatePasswordMember.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberAccount = req.getParameter("memberAccount").trim();
				String memberPassword = req.getParameter("memberPassword").trim();

				MemberService memberService = new MemberService();
				boolean checkAccountPassword;
				checkAccountPassword = memberService.checkAccountPassword(memberAccount, memberPassword);
				if (!checkAccountPassword) {
					errorMsgs.add("舊密碼錯誤");// 密碼不正確
				}

				String memberPasswordNew = req.getParameter("memberPasswordNew").trim();
				String memberPasswordNewReg = "^[a-zA-Z0-9]{6,10}$";
				if (memberPasswordNew == null || memberPasswordNew.trim().length() == 0) {
					errorMsgs.add("新密碼請勿空白");
				} else if (!memberPasswordNew.trim().matches(memberPasswordNewReg)) {
					errorMsgs.add("新密碼請輸入6到10英數混合字元不含特殊符號");
				}

				String memberPasswordNewCheck = req.getParameter("memberPasswordNewCheck").trim();
				if (!memberPasswordNew.equals(memberPasswordNewCheck)) {
					errorMsgs.add("兩次密碼輸入不相同");
				}

				if (!errorMsgs.isEmpty()) {
					MemberVO memberVO = new MemberVO();
					memberVO = memberService.getOneMember(memberAccount);
					req.setAttribute("memberVO", memberVO);// 僅限點數有問題
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member/updatePasswordMember.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改密碼 *****************************************/
				memberService.updatePassword(memberAccount, memberPasswordNew);
				req.getSession().removeAttribute("memberVO");// 重新登入把Session移除
//			 MemberVO memberVO=new MemberVO();
//			 memberVO=memberService.getOneMember(memberAccount);
//			 req.setAttribute("memberVO", memberVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				String url="/front_end/member/logInMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
//				successView.forward(req, res);
				res.sendRedirect(req.getContextPath() + "/front_end/member/logInMember.jsp");
			}
			/*************************** 其他可能的錯誤處理 *************************************/
			catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/updatePasswordMember.jsp");// 發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}

		if ("forgot_Password".equals(action)) {// 來自updatePasswordMember.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String memberAccount = req.getParameter("memberAccount").trim();
				String memberAccountReg = "^[a-zA-Z0-9]{6,10}$";

				MemberService memberService = new MemberService();
				boolean checkAccount;
				checkAccount = memberService.checkAccount(memberAccount);// 判斷這個是否是true

				if (memberAccount == null || memberAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if (!memberAccount.trim().matches(memberAccountReg)) {
					errorMsgs.add("帳號請輸入6到10英數混合字元不含特殊符號");
				}

				String memberEmail = req.getParameter("memberEmail").trim();
				String memberEmailReg = "^[a-zA-Z0-9_.-]{1,18}@[a-z0-9]{1,10}.[a-z]{1,10}$";

				boolean checkEmail;
				checkEmail = memberService.checkEmail(memberEmail);

				if (memberEmail == null || memberEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				} else if (!memberEmail.trim().matches(memberEmailReg)) {
					errorMsgs.add("請輸入正確信箱格式");
				}

				boolean checkAccountEmail = memberService.checkAccountEmail(memberAccount, memberEmail);
				if (!checkAccountEmail) {
					errorMsgs.add("帳號和信箱不相符");
				}

				if (!errorMsgs.isEmpty()) {
					MemberVO memberVO = new MemberVO();
					memberVO = memberService.getOneMember(memberAccount);
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/forgetMember.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改密碼 *****************************************/
				String auth = authcode.genAuthCode();
				String memberauth = "新密碼為:" + auth;

				memberService.updatePassword(memberAccount, auth);
				MailService.sendMail(memberEmail, "新密碼", memberauth);
				MemberVO memberVO = memberService.getOneMember(memberAccount);
				req.setAttribute("memberVO", memberVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/front_end/member/updatePasswordMember.jsp";
				if (memberVO.getMemberName() == null) {
					url = "/front_end/member/logInMember.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);
			}
			/*************************** 其他可能的錯誤處理 *************************************/
			catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/forgetMember.jsp");// 發生無法控制的錯誤送回select_page.jsp
				failureView.forward(req, res);
			}
		}

//		if("getOne_For_Update_Point".equals(action)) {//來自listOneMember.jsp的請求
//			List<String> errorMsgs=new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			String memberAccount=req.getParameter("memberAccount");
//			/***************************2.準備轉交(Send the Success view)*************/
//			MemberService memberService=new MemberService();
//			MemberVO memberVO=new MemberVO();
//			memberVO=memberService.getOneMember(memberAccount);
//			req.setAttribute("memberVO", memberVO);
//			String url = "/back_end/member/updatePointMember.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); //轉交updatePointMember.jsp
//			successView.forward(req, res);
//			}
//			/***************************其他可能的錯誤處理*************************************/
//			catch(Exception e) {
//				errorMsgs.add("資料傳送失敗:"+e.getMessage());
//				RequestDispatcher failureView=req.getRequestDispatcher("/back_end/member/select_page.jsp");//發生無法控制的錯誤送回select_page.jsp
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		
//		
//		if("update_Point_Member".equals(action)) {//來自updatePointMember.jsp的請求
//			List<String> errorMsgs=new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			MemberService memberService=new MemberService();
//			String memberAccount=req.getParameter("memberAccount");
//			
//			
//			Integer memberPoint=null;
//			try {
//			memberPoint=new Integer (req.getParameter("memberPoint").trim());
//			
//			if(memberPoint<0) {
//				errorMsgs.add("無法修改至0以下");
//			}
//			} catch (NumberFormatException e) {
//				errorMsgs.add("點數請填數字");
//			}
//			
//			
//			if(!errorMsgs.isEmpty()) {
//				MemberVO memberVO=new MemberVO();
//				memberVO=memberService.getOneMember(memberAccount);
//				req.setAttribute("memberVO", memberVO);//僅限點數有問題
//				RequestDispatcher failureView=req.getRequestDispatcher("/back_end/member/updatePointMember.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//			
//			/***************************2.開始修改點數*****************************************/
//			memberService.updatePoint(memberAccount, memberPoint);
//			MemberVO memberVO=new MemberVO();
//			memberVO=memberService.getOneMember(memberAccount);
//			req.setAttribute("memberVO", memberVO);
//			/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			String url="/back_end/member/listOneMember.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
//			successView.forward(req, res);
//			}
//			/***************************其他可能的錯誤處理*************************************/
//			catch(Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView=req.getRequestDispatcher("/back_end/member/select_page.jsp");//發生無法控制的錯誤送回select_page.jsp
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		
//		if("getOne_For_Update_BlackList".equals(action)) {//來自listOneMember.jsp的請求
//			List<String> errorMsgs=new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			String memberAccount=req.getParameter("memberAccount");
//			/***************************2.準備轉交(Send the Success view)*************/
//			MemberService memberService=new MemberService();
//			MemberVO memberVO=new MemberVO();
//			memberVO=memberService.getOneMember(memberAccount);
//			req.setAttribute("memberVO", memberVO);
//			String url = "/back_end/member/updateBlackListMember.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); //轉交updateBlackListMember.jsp
//			successView.forward(req, res);
//			}
//			/***************************其他可能的錯誤處理*************************************/
//			catch(Exception e) {
//				errorMsgs.add("資料傳送失敗:"+e.getMessage());
//				RequestDispatcher failureView=req.getRequestDispatcher("/back_end/member/select_page.jsp");//發生無法控制的錯誤送回select_page.jsp
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		
//		if("update_BlackList_Member".equals(action)) {//來自updateBlackListMember.jsp的請求
//			List<String> errorMsgs=new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			String memberAccount=req.getParameter("memberAccount");
//			
//			Integer memberBlackList=new Integer (req.getParameter("memberBlackList").trim());
//			
//			if(!errorMsgs.isEmpty()) {
//				MemberService memberService=new MemberService();
//				MemberVO memberVO=new MemberVO();
//				memberVO=memberService.getOneMember(memberAccount);
//				req.setAttribute("memberVO", memberVO);//僅限點數有問題
//				RequestDispatcher failureView=req.getRequestDispatcher("/back_end/member/updateBlackListMember.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//			
//			/***************************2.開始修改點數*****************************************/
//			MemberService memberService=new MemberService();
//			memberService.updateBlackList(memberAccount, memberBlackList);
//			MemberVO memberVO=new MemberVO();
//			memberVO=memberService.getOneMember(memberAccount);
//			req.setAttribute("memberVO", memberVO);
//			/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("memberVO", memberVO);
//			String url="/back_end/member/listOneMember.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
//			successView.forward(req, res);
//			}
//			/***************************其他可能的錯誤處理*************************************/
//			catch(Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView=req.getRequestDispatcher("/back_end/member/select_page.jsp");//發生無法控制的錯誤送回select_page.jsp
//				failureView.forward(req, res);
//			}
//		}

//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//			List<String> errorMsgs=new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			String memberAccount=req.getParameter("memberAccount");
//			String memberAccountReg="^[a-zA-Z0-9]{6,10}$";
//			if(memberAccount==null||memberAccount.trim().length()==0) {
//				errorMsgs.add("帳號請勿空白");
//			}
//			else if(!memberAccount.trim().matches(memberAccountReg)) {
//				errorMsgs.add("帳號請輸入6到10英數混合字元不含特殊符號");
//			}
//			if(!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView=req.getRequestDispatcher("/back_end/member/select_page.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//			
//			
//			/***************************2.開始查詢資料*****************************************/
//			MemberService memberService=new MemberService();
//			MemberVO memberVO=memberService.getOneMember(memberAccount);
//			if(memberVO==null) {
//				errorMsgs.add("查無此資料");
//			}
//			if(!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/select_page.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//			
//			
//			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("memberVO", memberVO);
//			String url="/back_end/member/listOneMember.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
//			successView.forward(req, res);
//			
//			/***************************其他可能的錯誤處理*************************************/
//			}catch(Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("listMember_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null) {
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map", map1);
					map = map1;
				}

				/*************************** 2.開始複合查詢 ***************************************/
				MemberService memberService = new MemberService();
				List<MemberVO> list = memberService.compositeQuery(map);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listMember_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req
						.getRequestDispatcher("/back_end/member/listMember_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("login_Member".equals(action)) {// updateMember的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberAccount = req.getParameter("memberAccount");

				String memberAccountReg = "^[a-zA-Z0-9]{6,10}$";

				MemberService memberService = new MemberService();
				boolean checkAccount;
				checkAccount = memberService.checkAccount(memberAccount);

				if (memberAccount == null || memberAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if (!memberAccount.trim().matches(memberAccountReg)) {
					errorMsgs.add("帳號請輸入6到10英數混合字元不含特殊符號");
				} else if (!checkAccount) {
					errorMsgs.add("帳號不存在");
				}

				String memberPassword = req.getParameter("memberPassword");
				String memberPasswordReg = "^[a-zA-Z0-9]{6,10}$";
				if (memberPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if (!memberPassword.trim().matches(memberPasswordReg)) {
					errorMsgs.add("密碼請輸入6到10英數混合字元不含特殊符號");
				}
				boolean checkAccountPassword;
				checkAccountPassword = memberService.checkAccountPassword(memberAccount, memberPassword);
				if (checkAccount && !checkAccountPassword) {
					errorMsgs.add("密碼錯誤");// 帳號存在且密碼不正確
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/logInMember.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************
				 * 2.檢查location及會員資料是否完整
				 *****************************************/
				MemberVO memberVO = new MemberVO();
				memberVO = memberService.getOneMember(memberAccount);
				if (memberVO.getMemberName() == null) {
					boolean beMember = true;
					req.setAttribute("beMember", beMember);
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher notFillProfile = req.getRequestDispatcher("/front_end/member/updateMember.jsp");
					notFillProfile.forward(req, res);
					return;
				}

				req.getSession().setAttribute("memberVO", memberVO);
				try {
					String location = (String) req.getSession().getAttribute("location");
					if (location != null) {
						req.getSession().removeAttribute(location);
						req.setAttribute("memberVO", memberVO);
						boolean beMember = true;
						req.setAttribute("beMember", beMember);
						String url = location.substring(9);
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
				} catch (Exception ignore) {
				}
				/*************************** 3.登入完成,準備轉交(Send the Success view) *************/
				req.getSession().setAttribute("memberVO", memberVO);
				req.setAttribute("memberVO", memberVO);
				boolean beMember = true;
				req.setAttribute("beMember", beMember);
//			res.sendRedirect(req.getContextPath()+"/front_end/friend/browseMember.jsp");
				String url = "/front_end/index/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("登入失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/logInMember.jsp");
				failureView.forward(req, res);
			}
		}

		if ("logout".equals(action)) {// updateMember的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String url = "/front_end/index/index.jsp";
			try {
				req.getSession().removeAttribute("memberVO");
				/*************************** 3.登入完成,準備轉交(Send the Success view) *************/
//			res.sendRedirect(req.getContextPath()+"/member/select_page.jsp");//路徑要改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("登出失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}

	}// 我是Post請求
}
