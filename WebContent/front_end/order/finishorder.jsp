<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.orderlist.model.*"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*" %>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單完成</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/order/css/finishorder.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<%
  OrderVO orderVO = (OrderVO)request.getAttribute("orderVO"); 
  List<OrderlistVO> list =(List<OrderlistVO>)request.getAttribute("list");
  String email = (String) request.getAttribute("email_address");
%>

<body>
<div class="receipt-content">
    <div class="container bootstrap snippets bootdey">
		<div class="row">
			<div class="col-md-12">
				<div class="invoice-wrapper">
					<div class="intro">
						你好 <strong>${orderVO.orderer}</strong>, 
						<br>
						這是你總共消費<strong>${orderVO.total}</strong>的收據
					</div>

					<div class="payment-info">
						<div class="row">
							<div class="col-sm-6">
								<span>訂單編號</span>
						<% 
            	 		 	OrderlistVO getorderno = list.get(0);
							int orderno = getorderno.getOrderno();
							OrderService orderSvc = new OrderService();
							OrderVO orderVo = orderSvc.getOneOrder(orderno);
            	 		%>
								<strong><%=orderno%></strong>
								
							</div>
							<div class="col-sm-6 text-right">
								<span>訂單時間</span>
								<strong><%=orderVo.getOrderdate()%></strong>
							</div>
						</div>
					</div>
					<%
					  String str = new String(orderVO.getAddress());
					  String[] buff = str.split(",");
					%>
					<div class="payment-details">
						<div class="row">
							<div class="col-sm-6">
								<span>客戶地址</span>
								<strong>
									Taiwan
								</strong>
								<p>
								
									   <%=buff[0]%><br>
									  <%=buff[1]%><br>
									   <%=buff[2]%><br>
									
									<a href="#">
										<%=email%>
									</a>
								</p>
							</div>
							<div class="col-sm-6 text-right">
								<span>發貨地址</span>
								<strong>
									Taiwan
								</strong>
								<p>
									桃園市 <br>
									觀音區<br>
									經建二路11號-3 <br>
									Beloved科技<br>
									<a href="#">
									Beloved@gmail.com
									</a>
								</p>
							</div>
						</div>
					</div>

					<div class="line-items">
						<div class="headers clearfix">
							<div class="row">
								<div class="col-8">商品名稱</div>
								<div class="col-2">數量</div>
								<div class="col-2 text-right">小計</div>
							</div>
						</div>
						<%	
						 
              			for(int index = 0;index <list.size();index++){
            	 		 	OrderlistVO orderlistVO = list.get(index);
            	 		 	int prodno = orderlistVO.getProdno();
            	 		 	ProdService prodSvc = new ProdService();
            	 			ProdVO prodVO = prodSvc.getProdDetail(prodno);
            	 		%>
						<div class="items">
							<div class="row item">
								<div class="col-8">
									<%=prodVO.getProdname()%>
								</div>
								<div class="col-2">
									<%=orderlistVO.getQuantity()%>
								</div>
								<div class="col-2 text-right">
									<%=orderlistVO.getPrice()%>
								</div>
							</div>
							
						</div>
						<%}%>
						<div class="total text-right">
							<p class="extra-notes">
								<strong>額外說明/注意事項</strong>
								如需使用轉帳請與客服確認是否有收到款項<br>
								如需退貨,由消費者取件開始算起至第7天前寄出
							</p>
							<div class="field">
								小計 <span>$${orderVO.total}</span>
							</div>
							<div class="field">
								運費 <span>$0</span>
							</div>
							
							<div class="field grand-total">
								Total <span>$${orderVO.total}</span>
							</div>
						</div>

						<div class="print">
							<a href="<%=request.getContextPath()%>/front_end/prod/Shop.jsp">
								<i class="fa fa-print"></i>
								返回購物
							</a>
						</div>
					</div>
				</div>

				<div class="footer">
					Copyright © 2021. beloved
				</div>
			</div>
		</div>
	</div>
</div>                    

</body>
</html>