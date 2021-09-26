package com.order.controller;

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

import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderlist.model.OrderlistVO;
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
				if (tel == null || tel.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				}
				String email_address = req.getParameter("email_address");
				Integer memberno = 3;
				Integer orderstate = 1;
				Integer paymentmeth = new Integer(req.getParameter("paymentmeth"));
				Integer deliverymeth = new Integer(req.getParameter("deliverymeth"));
				Integer total = new Integer(req.getParameter("total"));
				if(paymentmeth == 2) {
					String creditcardnum = req.getParameter("creditcardnum");
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
			    
			    
			    String url = "/front_end/order/finishorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			    
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/order/order.jsp");
				failureView.forward(req, res);
				
			}	
				
		}
	  }

}
