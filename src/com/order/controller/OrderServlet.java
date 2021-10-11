package com.order.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import com.emp.model.EmpService;
import com.order.model.MailService;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderlist.model.OrderlistVO;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;

@WebServlet("/order/order.do")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insertWithOrderlist".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String orderer = req.getParameter("orderer");
				if (orderer == null || orderer.trim().length() == 0) {
					errorMsgs.add("訂購人請勿空白");
				}
				String city = req.getParameter("city");
				String dist = req.getParameter("dist");
				String state = req.getParameter("state");
				String address = city+","+dist+","+state;
				
				if(state == null) {
					errorMsgs.add("請輸入地址");
				}
				String tel = req.getParameter("tel");
				String telReg = "^[09]{2}[0-9]{8}$";
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				}else if(!tel.trim().matches(telReg)) {
					 errorMsgs.add("請輸入正確手機格式");
				}
				String email_address = req.getParameter("email_address");
				if (email_address == null || email_address.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}
				Integer memberno = new Integer(req.getParameter("memberno"));
				
				System.out.println(memberno);
				
				Integer orderstate = 1;
				Integer paymentmeth = new Integer(req.getParameter("paymentmeth"));
				Integer deliverymeth = new Integer(req.getParameter("deliverymeth"));
				Integer total = new Integer(req.getParameter("total"));
				String creditcardnum = req.getParameter("creditcardnum");
				if(paymentmeth == 2) {
					if (creditcardnum == null || creditcardnum.trim().length() == 0) {
						errorMsgs.add("信用卡號請勿空白");
					}
				}
				
			    @SuppressWarnings("unchecked")
				List<ProdVO> buylist = (Vector<ProdVO>)req.getSession().getAttribute("shoppingcart");
				if(buylist.size() == 0) {
					errorMsgs.add("購物車內無商品,請加入商品");
				}
			   
			    OrderVO orderVO = new OrderVO();
			    orderVO.setOrderstate(orderstate);
			    orderVO.setTotal(total);
			    orderVO.setOrderer(orderer);
			    orderVO.setAddress(address);
			    orderVO.setTel(tel);
			    orderVO.setPaymentmeth(paymentmeth);
			    orderVO.setDeliverymeth(deliverymeth);
			    orderVO.setMemberno(memberno);
			    
			    
			    List<OrderlistVO> list = new ArrayList<OrderlistVO>();
			    
			    for(ProdVO prodVO :buylist) {
			    	OrderlistVO orderlistVO = new OrderlistVO();
			    	
			    	orderlistVO.setProdno(prodVO.getProdno());
			    	orderlistVO.setQuantity(prodVO.getQuantity());
			    	orderlistVO.setPrice(prodVO.getQuantity()*prodVO.getPrice());
			    
			    	list.add(orderlistVO);
			    }
			    if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderVO", orderVO);
					req.setAttribute("list", list);
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/order/order.jsp");
					failureView.forward(req, res);
					return;
				}
			    OrderService orderSvc = new OrderService();
			    orderSvc.insertWithOrderlist(orderVO, list);
			    
			    req.setAttribute("orderVO", orderVO);
			    req.setAttribute("list", list);
			    req.setAttribute("email_address",email_address);
			    
			    buylist.clear();
			    OrderlistVO getorderno = list.get(0);
				int orderno = getorderno.getOrderno();
				OrderVO orderVo = orderSvc.getOneOrder(orderno);
				
				String product = "";
				String num = "";
				String price = "";
				String productandnum ="";
				for(int index = 0;index <list.size();index++){
    	 		 	OrderlistVO orderlistVO = list.get(index);
    	 		 	int prodno = orderlistVO.getProdno();
    	 		 	ProdService prodSvc = new ProdService();
    	 			ProdVO prodVO = prodSvc.getProdDetail(prodno);
    	 			product = prodVO.getProdname();
    	 			num = orderlistVO.getQuantity().toString();
    	 			price = orderlistVO.getPrice().toString();
    	 			productandnum = productandnum + product +" 數量:"+num+" 小計:"+price+"\r\n";
				}
			    
				String to = email_address;
			    String subject = "Beloved訂單通知";
			    String paymentmethtext = "因為您選擇的是匯款方式\r\n"+
                					     "以下是匯款帳戶\r\n"+
			    		                 "台北富邦銀行 代號012\r\n"+
                			             "123456789012"
                						 ;
			    String messageText = "親愛的 "+orderer+" 先生/小姐 您好:\r\n" + 
			    		             "您所訂購的商品，明細如下：\r\n" + 
			    		             "訂單編號："+orderno+"\r\n" + 
			    		             "訂單日期："+orderVo.getOrderdate()+"\r\n" +
			    		             "運送地點:"+address+"\r\n"+
			    		             "訂購商品：\r\n"+
			    		             productandnum+
			    		             "總計:"+orderVo.getTotal()+"\r\n"
			    		             ; 
			    
			    MailService mailService = new MailService();
			    if(paymentmeth == 3) {
			    	mailService.sendMail(to, subject, messageText+paymentmethtext);
			    }else {
			    	mailService.sendMail(to, subject, messageText);
			    }
			     String url = "/front_end/order/finishorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			    
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/order/order.jsp");
				failureView.forward(req, res);
				
			}	
				
		}
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer orderno = new Integer(req.getParameter("orderno"));
				
				/***************************2.開始查詢資料****************************************/
				OrderService orderSvc = new OrderService();
				OrderVO orderVO = orderSvc.getOneOrder(orderno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("orderVO", orderVO); 
				String url = "";
				if("getOne_For_Update".equals(action)) {
					url = "/back_end/order/updateOrder_input.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/order/updateOrder_input.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer orderno = new Integer(req.getParameter("orderno"));
				String orderer = req.getParameter("orderer");
				if (orderer == null || orderer.trim().length() == 0) {
					errorMsgs.add("訂購人請勿空白");
				}
				String city = req.getParameter("city");
				String dist = req.getParameter("dist");
				String state = req.getParameter("state");
				String address = city+dist+state;
				if (state == null || state.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String tel = req.getParameter("tel");
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				Integer deliverymeth = new Integer(req.getParameter("deliverymeth"));
				OrderVO orderVO = new OrderVO();
				orderVO.setOrderer(orderer);
				orderVO.setAddress(address);
				orderVO.setTel(tel);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderVO", orderVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/order/updateOrder_input.jsp");
					failureView.forward(req, res);
					return;
				}
				orderVO.setDeliverymeth(deliverymeth);
				OrderService orderSvc = new OrderService();
				orderVO = orderSvc.updateOrder(orderer, address, tel, deliverymeth,orderno);
				String url = "/back_end/order/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/order/updateOrder_input.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOrderStateV".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer orderstate = new Integer(req.getParameter("orderstate"));
				Integer memberno = new Integer(req.getParameter("memberno"));
				
				OrderService orderSvc = new OrderService();
				List<OrderVO> List = orderSvc.getOrderStateV(orderstate, memberno);
				if(orderstate == 1) {
					List<OrderVO> List4 = orderSvc.getOrderStateV(4, memberno);
					for(OrderVO orderVO:List4) {
						List.add(orderVO);
					}
				}
				req.setAttribute("listByOrderstate",List);
				String url="/front_end/order/SearchOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/order/SearchOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if("cancelbymember".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				Integer orderno = new Integer(req.getParameter("orderno"));
				Integer memberno = new Integer (req.getParameter("memberno"));
				String cancelreason = req.getParameter("cancelreason");
				
				int orderstate = 3;
				
				OrderService orderSvc = new OrderService();
				orderSvc.updateCancelReason(orderno,cancelreason);
				System.out.println(cancelreason);
				orderSvc.cancalOrder(orderno, orderstate);
				
				String url = requestURL;
				res.sendRedirect(req.getContextPath()+url);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/order/SearchOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if("cancelorder".equals(action)||"nocancelorder".equals(action)) {
			
			Integer orderstate  = new Integer(req.getParameter("orderstate"));
			Integer orderno = new Integer(req.getParameter("orderno"));
			OrderService orderSvc = new OrderService();
			orderSvc.cancalOrder(orderno, orderstate);
			
			String url = "/back_end/order/OrderReview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("searchorder".equals(action)){
			String message = null ;
			try {
				Integer orderno = new Integer(req.getParameter("orderno"));
				Integer memberno = new Integer (req.getParameter("memberno"));
				OrderService orderSvc = new OrderService();
				OrderVO orderVO = orderSvc.getOneOrderByMember(orderno, memberno);
				if(orderVO == null) {
					message = "查無資料";
				}
				List<OrderVO> List = new ArrayList<OrderVO>();
				List.add(orderVO);
				 if (message!=null) {
					 req.setAttribute("message", message);
					RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/order/SearchOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				String url="/front_end/order/SearchOrder.jsp";
				req.setAttribute("listByOrderstate",List);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				req.setAttribute("message", message);
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/order/SearchOrder.jsp");
				failureView.forward(req, res);
			}
			
		}
		if("searchorderbyorderno".equals(action)) {
			String message = null ;
			Integer orderno = new Integer(req.getParameter("orderno"));
			OrderService orderSvc = new OrderService();
			List<OrderVO> List = new ArrayList<OrderVO>();
			OrderVO orderVO  =   orderSvc.getOneOrder(orderno);
			List.add(orderVO);
			if(orderVO == null) {
				message = "查無資料";
				req.setAttribute("message", message);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/order/listAllOrder.jsp");
			    failureView.forward(req, res);
			    return;
			}else {
				String url="/back_end/order/listAllOrder.jsp";
				req.setAttribute("listByCondition",List);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		if("searchorderbymemberno".equals(action)) {
			String message = null ;
			Integer memberno = new Integer(req.getParameter("memberno"));
			OrderService orderSvc = new OrderService();
			List<OrderVO> List  = orderSvc.getAllByMno(memberno);
			if(List.size() == 0) {
				message = "查無資料";
				req.setAttribute("message", message);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/order/listAllOrder.jsp");
			    failureView.forward(req, res);
			    return;
			}else {
				String url="/back_end/order/listAllOrder.jsp";
				req.setAttribute("listByCondition",List);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
	  }
	public static String addColor(String msg, Color color) {
	    String hexColor = String.format("#%06X",  (0xFFFFFF & color.getRGB()));
	    String colorMsg = "<FONT COLOR=\"#" + hexColor + "\">" + msg + "</FONT>";
	    return colorMsg;
	}

}
