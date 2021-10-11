package com.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.prod.model.ProdService;
import com.prod.model.ProdVO;




@WebServlet("/prod/prod.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProdServlet extends HttpServlet {
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
				Integer prodsortno = new Integer(req.getParameter("prodsortno"));
				
				String prodname = req.getParameter("prodname");
				if (prodname == null || prodname.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}
				
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填數字.");
				}
				if(price < 0) {
					errorMsgs.add("價格請填正整數");
				}
				String indroce = req.getParameter("indroce");
				if (indroce == null || indroce.trim().length() == 0) {
					errorMsgs.add("商品介紹請勿空白");
				}
				
				res.setContentType("text/html;charset=UTF-8");
				String saveDirectoy = "/back_end/prod/images";
				String realPath = getServletContext().getRealPath(saveDirectoy);
				
				File fsaveDirectoy = new File(realPath);
				if(!fsaveDirectoy.exists()) {
					fsaveDirectoy.mkdirs();
				}
				Part part1 = req.getPart("prodpic1");
				String prodpic1 = getFileNameFromPart(part1);
				req.getPart("prodpic1").write(realPath+"/"+prodpic1);
				if(part1 == null|| part1.getSize() ==0) {
					errorMsgs.add("請上傳商品圖片");
				}
				System.out.println(prodpic1);
				
				Part part2 = req.getPart("prodpic2");
				String prodpic2 = getFileNameFromPart(part2);
				req.getPart("prodpic2").write(realPath+"/"+prodpic2);
				
				
				
				Part part3 = req.getPart("prodpic3");
				String prodpic3 = getFileNameFromPart(part3);
				req.getPart("prodpic3").write(realPath+"/"+prodpic3);
				
				Integer prodstate = new Integer(req.getParameter("prodstate"));
				
				ProdVO prodVO = new ProdVO();
				prodVO.setProdsortno(prodsortno);
				prodVO.setProdname(prodname);
				prodVO.setPrice(price);
				prodVO.setIndroce(indroce);
				prodVO.setProdpic1(prodpic1);
				prodVO.setProdpic2(prodpic2);
				prodVO.setProdpic3(prodpic3);
				prodVO.setProdstate(prodstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/prod/addProd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProdService prodSvc = new ProdService();
				prodVO = prodSvc.addProd(prodsortno, prodname, price, indroce, prodpic1, prodpic2, prodpic3, prodstate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/prod/listAllProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
			    /***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/prod/addProd.jsp");
				failureView.forward(req, res);
			}
	    }
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer prodno = new Integer(req.getParameter("prodno"));
				
				/***************************2.開始刪除資料***************************************/
				ProdService prodSvc = new ProdService();
				int prodsortno = prodSvc.getProdDetail(prodno).getProdsortno();
				prodSvc.delProd(prodno);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				ProdVO prodVO = new ProdVO();
				 prodVO = prodSvc.getProdDetail(prodno);
				
				if(requestURL.equals("/back_end/prod/listAllProd.jsp") ||requestURL.equals("/back_end/prod/listBySort.jsp")) {
					req.setAttribute("listBySort", prodSvc.getProdBySortAll(prodsortno));
				}
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/prod/listAllProd.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updatestate".equals(action) || "updatestate_soft".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prodno = new Integer(req.getParameter("prodno"));
				
				ProdService prodSvc = new ProdService();
				
				int i = prodSvc.getProdDetail(prodno).getProdstate();
				int prodsortno = prodSvc.getProdDetail(prodno).getProdsortno();
				System.out.println(prodsortno);
				ProdVO prodVO = new ProdVO();
				
				if(i == 1) {
					prodVO.setProdstate(0);
				}else{
					prodVO.setProdstate(1);
				}
				prodVO.setProdno(prodno);
				
			
				Integer prodstate =new Integer(prodVO.getProdstate());
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("listBySort", prodVO);
					String url = requestURL;
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				List<ProdVO> prodsortlist = prodSvc.getProdBySortAll(prodsortno);
				prodVO = prodSvc.updatestate(prodno,prodstate);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listBySort",prodsortlist); 
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String url = "";
				if("updatestate".equals(action)){
					url="/back_end/prod/listAllProd.jsp";
				}else if("updatestate_soft".equals(action)) {
					url="/back_end/prod/listBySort.jsp";
				}
				
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action) || "getOne_For_Update_soft".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數****************************************/
				Integer prodno = new Integer(req.getParameter("prodno"));
				
				/***************************2.開始查詢資料****************************************/
				ProdService prodSvc = new ProdService();
				ProdVO prodVO = prodSvc.getProdDetail(prodno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("prodVO", prodVO); 
				String url = "";
				if("getOne_For_Update".equals(action)) {
					url = "/back_end/prod/updateProd_input.jsp";
				}else if("getOne_For_Update_soft".equals(action)){
					url = "/back_end/prod/updateProd_input.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prodno = new Integer(req.getParameter("prodno"));
				
				Integer prodsortno = new Integer(req.getParameter("prodsortno"));
				
				
				String prodname = req.getParameter("prodname");
				if (prodname == null || prodname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}	
				
				
				Integer price = null;
				if((Integer.parseInt(req.getParameter("price"))) < 0) {
					errorMsgs.add("商品價格為正整數");
				}
				try {
					price = new Integer(req.getParameter("price").trim());
				}catch(NumberFormatException e) {
					price = 0;
					errorMsgs.add("商品價格請填數字");
				}
				
				
				String indroce = req.getParameter("indroce");
				if (indroce == null || indroce.trim().length() == 0) {
					errorMsgs.add("商品介紹: 請勿空白");
				}
				
				res.setContentType("text/html;charset=UTF-8");
				
				String saveDirectoy = "/back_end/prod/images";
				String realPath = getServletContext().getRealPath(saveDirectoy);
				File fsaveDirectoy = new File(realPath);
				if(!fsaveDirectoy.exists()) {
					fsaveDirectoy.mkdirs();
				}
				ProdVO prodVO = new ProdVO();
				ProdService prodSvc = new ProdService();
				
				Part part4 = req.getPart("prodpic1");
				String prodpic1 = "";
				if(part4.getSize() != 0) {
					 prodpic1 = getFileNameFromPart(part4);
					 req.getPart("prodpic1").write(realPath+"/"+prodpic1);
				}else{
					 prodVO = prodSvc.getProdDetail(prodno);
					 prodpic1 = prodVO.getProdpic1();
				}
			
				Part part5 = req.getPart("prodpic2");
				String prodpic2 = "";
				if(part5.getSize() != 0) {
					 prodpic2 = getFileNameFromPart(part5);
					 req.getPart("prodpic2").write(realPath+"/"+prodpic2);
				}else{
					 prodVO = prodSvc.getProdDetail(prodno);
					 prodpic2 = prodVO.getProdpic2();
				}
				Part part6 = req.getPart("prodpic3");
				String prodpic3 = "";
				if(part6.getSize() != 0) {
					 prodpic3 = getFileNameFromPart(part6);
					 req.getPart("prodpic3").write(realPath+"/"+prodpic3);
				}else{
					 prodVO = prodSvc.getProdDetail(prodno);
					 prodpic3 = prodVO.getProdpic3();
				}
				
				
				prodVO.setProdno(prodno);
				prodVO.setProdsortno(prodsortno);
				prodVO.setProdname(prodname);
				prodVO.setPrice(price);
				prodVO.setIndroce(indroce);
				prodVO.setProdpic1(prodpic1);
				prodVO.setProdpic2(prodpic2);
				prodVO.setProdpic3(prodpic3);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO);
					
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				prodVO = prodSvc.updateProduct(prodno,prodsortno,prodname,price,indroce,prodpic1,prodpic2,prodpic3);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listBySort", prodSvc.getProdBySortAll(prodVO.getProdsortno()));
				System.out.println(prodVO.getProdsortno());
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/prod/updateProd_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getBySort".equals(action)||("getBySort_V".equals(action))) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer prodsortno = new Integer(req.getParameter("prodsortno"));
				
				/***************************2.開始查詢資料****************************************/
				ProdService prodSvc = new ProdService();
				List<ProdVO> List =  prodSvc.getProdBySortAll(prodsortno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listBySort",List);
				String url="";
				if("getBySort".equals(action)) {
					 url = "/back_end/prod/listBySort.jsp";
				}else if(("getBySort_V".equals(action))) {
					url ="/front_end/prod/Shop.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/index.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prodno = new Integer(req.getParameter("prodno"));
				String Path = req.getContextPath();
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(Path+"/back_end/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(Path+"/back_end/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProdService prodSvc = new ProdService();
				ProdVO prodVO = prodSvc.getProdDetail(prodno);
				if (prodVO == null) {
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
				req.setAttribute("prodVO", prodVO); 
				String url = "/back_end/prod/listOneProd.jsp";
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
		if("ListByPriceRange".equals(action)) {
			
			
			Integer minprice = new Integer(req.getParameter("minprice"));
			Integer maxprice = new Integer(req.getParameter("maxprice"));
			
			ProdService prodSvc = new ProdService();
			List<ProdVO> List = prodSvc.getProdByPriceRange(minprice, maxprice);
			
			req.setAttribute("listBySort",List);
			
			String url = "/front_end/prod/Shop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if("SortByPrice".equals(action)) {
			String requestURL = req.getParameter("requestURL");
			Integer sortbyprice = new Integer(req.getParameter("pricesort"));
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<ProdVO> list = (List<ProdVO>)session.getAttribute("listBySort");
			
			List<ProdVO> newlist = new ArrayList<>();
			if(sortbyprice == 1) {
				newlist = list.stream()
						  	  .sorted(Comparator.comparing(ProdVO::getPrice))
						      .collect(Collectors.toList());
			
				req.setAttribute("listBySort",newlist);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else if(sortbyprice == 0) {
				String url ="/front_end/prod/Shop.jsp";
				res.sendRedirect(req.getContextPath()+url);
			}else if(sortbyprice == 2) {
				newlist = list.stream()
						      .sorted(Comparator.comparing(ProdVO::getPrice).reversed())
						      .collect(Collectors.toList());
				req.setAttribute("listBySort",newlist);
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		LinkedList<ProdVO> list = (LinkedList<ProdVO>)session.getAttribute("history");
		
		if("gethistory".equals(action)) {
			Integer prodno = new Integer(req.getParameter("prodno"));
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.getProdDetail(prodno);
			System.out.println(prodno);
			if(list == null) {
				list = new LinkedList<ProdVO>();
				list.add(prodVO);
			}else if(list.contains(prodVO)) {
				list.remove(prodVO);
				list.addFirst(prodVO);
			}else if(list.size() == 5) {
				list.removeLast();
				list.addFirst(prodVO);
			}else {
				list.add(prodVO);
			}
			for(ProdVO prodVO1:list) {
				System.out.println(prodVO1.getProdname());
			}
			session.setAttribute("history", list);
		}
		if("clearhistory".equals(action)) {
			if(list != null) {
				list.clear();
			}
			String url = "/front_end/prod/Cart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
}


public String getFileNameFromPart(Part part){
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=")+2,header.length()-1)).getName();
		if(filename.length() == 0) {
			return null;
		}
		return filename;
}

}
