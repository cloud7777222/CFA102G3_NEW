package com.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order.model.OrderService;
import com.orderlist.model.OrderlistService;
import com.orderlist.model.OrderlistVO;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;


@WebServlet("/prod/cart.do")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<ProdVO> buylist = (Vector<ProdVO>)session.getAttribute("shoppingcart");
		
		
		if(action.equals("DeleteFromCart")) {
			String del = req.getParameter("del");
			int d = Integer.parseInt(del);
			buylist.remove(d);
			
			session.setAttribute("shoppingcart", buylist);
			String url ="/front_end/prod/Cart.jsp";
			res.sendRedirect(req.getContextPath()+url);
			
		}
		if(action.equals("AddToCart") || action.equals("checkout") || action.equals("AddToCart_Detail")){
			String requestURL = req.getParameter("requestURL");
			Integer prodno = new Integer(req.getParameter("prodno"));
			ProdService prodsvc = new ProdService();
			ProdVO prodVO = prodsvc.getProdDetail(prodno);
			Integer quantity = new Integer(req.getParameter("quantity"));
			prodVO.setQuantity(quantity);
			
			if(buylist == null) {
				buylist = new Vector<ProdVO>();
				buylist.add(prodVO);
			}else {
				if(buylist.contains(prodVO)) {
					ProdVO innerProdVO = buylist.get(buylist.indexOf(prodVO));
					innerProdVO.setQuantity(innerProdVO.getQuantity() + prodVO.getQuantity());
				}else {
					buylist.add(prodVO);
				}
			}
			session.setAttribute("shoppingcart", buylist);
			String url ="";
			if(action.equals("AddToCart")) {
				url ="/front_end/prod/Shop.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}else if(action.equals("checkout")) {
				url="/front_end/prod/Cart.jsp";
				res.sendRedirect(req.getContextPath()+url);
			}else if(action.equals("AddToCart_Detail")) {
				url=requestURL;
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
		}
//		 if(action.equals("Checkout")) {
//			int total = buylist.stream()
//						  .mapToInt(b -> b.getPrice() * b.getQuantity())
//						  .sum();
//				req.setAttribute("sum", total);
//				url = "/Checkout.jsp";
//				RequestDispatcher rd = req.getRequestDispatcher(url);
//				rd.forward(req, res);
//		}

		if(action.equals("DesQuan")) {
			int prodno = new Integer(req.getParameter("prodno"));
			for (ProdVO prodVO : buylist) {
				if (prodVO.getProdno() == prodno) {
					int quantity = prodVO.getQuantity();
					if(quantity > 1) {
					quantity--;
					}
					prodVO.setQuantity(quantity);
					String url ="/front_end/prod/Cart.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
				}
			}
		}
		if(action.equals("IncQuan")) {
			int prodno = new Integer(req.getParameter("prodno"));
			for (ProdVO prodVO : buylist) {
				if (prodVO.getProdno() == prodno) {
					int quantity = prodVO.getQuantity();
					quantity++;
					prodVO.setQuantity(quantity);
					String url ="/front_end/prod/Cart.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
				}
			}
		}	
		if(action.equals("AddToCartList")) {
			int orderno = new Integer(req.getParameter("orderno"));
			OrderlistService orderlistSvc = new OrderlistService();
			List<OrderlistVO> orderlistVO = orderlistSvc.getOrderDetail(orderno);
			ProdService prodSvc = new ProdService();
			for(OrderlistVO orderlist:orderlistVO) {
				int prodno = orderlist.getProdno();
             	ProdVO prodVO = prodSvc.getProdDetail(prodno);
             	int quantity = orderlist.getQuantity();
             	prodVO.setQuantity(quantity);
             	if(buylist == null) {
             		buylist = new Vector<ProdVO>();
             		buylist.add(prodVO);
             	}else {
             		if(buylist.contains(prodVO)) {
             			ProdVO innerProdVO = buylist.get(buylist.indexOf(prodVO));
             			innerProdVO.setQuantity(innerProdVO.getQuantity() + prodVO.getQuantity());
             		}else {
             			buylist.add(prodVO);
             		}
             	}
			} 	
			session.setAttribute("shoppingcart", buylist);
			String url ="/front_end/prod/Cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		
		
	}
	
	
}
