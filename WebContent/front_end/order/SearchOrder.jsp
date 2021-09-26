<%@page import="java.util.List"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.order.model.*"%>
<%@page import="com.prod.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢訂單</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/order/css/SearchOrder.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
</head>
<%
	OrderService orderSvc = new OrderService();
    OrderlistService orderlistSvc = new OrderlistService();
    int memberno = 3;
    List<OrderVO> list = orderSvc.getAllByMno(memberno);
    ProdService prodSvc = new ProdService();
%>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="osahan-account-page-left shadow-sm bg-white h-100">
                <div class="border-bottom p-4">
                    <div class="osahan-user text-center">
                        <div class="osahan-user-media">
                            <img class="mb-3 rounded-pill shadow-sm mt-1" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="gurdeep singh osahan">
                            <div class="osahan-user-media-body">
                                <h6 class="mb-2">會員名稱</h6>
                                <p class="mb-1">+91 85680-79956</p>
                                <p>iamosahan@gmail.com</p>
                                <p class="mb-0 text-black font-weight-bold"><a class="text-primary mr-3" data-toggle="modal" data-target="#edit-profile-modal" href="#"><i class="icofont-ui-edit"></i> EDIT</a></p>
                            </div>
                        </div>
                    </div>
                </div>
                <ul class="nav nav-tabs flex-column border-0 pt-4 pl-4 pb-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link" id="orders-tab" data-toggle="tab" href="#orders" role="tab" aria-controls="orders" aria-selected="false">全部</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="orders-tab1" data-toggle="tab" href="#orders" role="tab" aria-controls="orders" aria-selected="false">已付款</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="orders-tab2" data-toggle="tab" href="#orders" role="tab" aria-controls="orders" aria-selected="false">已取消</a>
                    </li>
                </ul>
            </div>
        </div>	
        <div class="col-md-9">
            <div class="osahan-account-page-right shadow-sm bg-white p-4 h-100">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane  fade  active show" id="orders" role="tabpanel" aria-labelledby="orders-tab">
                        <h4 class="font-weight-bold mt-0 mb-4">歷史訂單</h4>
                        <%
                        	for(OrderVO orderVO: list){
                        %>
                        <div class="bg-white card mb-4 order-list shadow-sm">
                               <div class="gold-members p-4" name="orderstate">
                                <div>訂單編號:<%=orderVO.getOrderno()%>
                                <span class="float-right text-info">訂單成立時間:<%=orderVO.getOrderdate() %></span>
                                </div>
                                   <div class="gold-members p-4">
                                   <%if(orderVO.getOrderstate() == 1){ %>
                                   <span class="float-right"style="color:darkgreen;font-weight:bold;">●已付款</span></div>
                                 <%}else if(orderVO.getOrderstate() == 2){ %> 
                                  <span class="float-right"style="color:red;font-weight:bold;">●已取消</span></div>
                                 <%} %>
                                 <div class="media">
                   
                                <div class="media-body">
                             <%
                             	int orderno = orderVO.getOrderno();
                             	List<OrderlistVO> orderlist = orderlistSvc.getOrderDetail(orderno); 
                             	for(OrderlistVO orderlistVO:orderlist){
                             	int prodno = orderlistVO.getProdno();
                             	ProdVO prodVO = prodSvc.getProdDetail(prodno);
                             %>   
                                <div style="display:inline-block">
                                	 <div style="float:left">
                                        <a href="<%=request.getContextPath()%>/front_end/prod/Prod_Detail.jsp?prodno=<%=prodVO.getProdno()%>"><img class="mr-4" style=""src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic1() %>" alt="Generic placeholder image"></a>
                                     </div>
                                     
                                     <div style="float:right">	
                                     	<a href="<%=request.getContextPath()%>/front_end/prod/Prod_Detail.jsp?prodno=<%=prodVO.getProdno()%>"><%=prodVO.getProdname()%></a><br>
                                       	單價:<%=prodVO.getPrice()%> <br>
                                       	x<%=orderlistVO.getQuantity()%>
                                	</div>
                                	
                               </div>	 	
                                	<br>   
                                  <%} %>  	       
                                      	<hr>
                                  		<div class="float-right">
                                  		
                                         <a class="btn btn-sm btn-outline-primary" href="<%=request.getContextPath()%>/prod/cart.do?action=AddToCartList&orderno=<%=orderVO.getOrderno()%>">再買一次</a>
                                        
             							<%if(orderVO.getOrderstate() == 1){%>
                                           <a class="btn btn-sm btn-primary" id="cancel" >取消訂單</a>
                                        <%}else if(orderVO.getOrderstate() == 2){ %>
                                        <a class="btn btn-sm btn-primary disabled" >取消訂單</a>  
                                        <%}%>
                                        </div>
                                        <p class="mb-0 text-black text-primary pt-2"><span class="text-black font-weight-bold"> 訂單金額:</span>NT<%=orderVO.getTotal() %>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                           <%} %>  
                    </div>
                  
             
               
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
window.onload=function(){
    var orderstate = document.getElementById("orderstate").value;
    console.log(orderstate);
}
</script>
</body>
</html>