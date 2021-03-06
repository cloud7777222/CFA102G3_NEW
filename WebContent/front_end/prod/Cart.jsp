<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.prodsort.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Beloved Cart</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<style>
body {
  background: #eecda3;
  background: -webkit-linear-gradient(to right, #eecda3, #ef629f);
  background: linear-gradient(to right, #eecda3, #ef629f);
  min-height: 100vh;
}
</style>
</head>
<body>
<% @SuppressWarnings("unchecked")
Vector<ProdVO> buylist = (Vector<ProdVO>)session.getAttribute("shoppingcart");%>
<%ProdsortService prodsortSvc = new ProdsortService(); %>

<% @SuppressWarnings("unchecked")
LinkedList<ProdVO> list = (LinkedList<ProdVO>)session.getAttribute("history");
%>
<%
	MemberVO memberVO = (MemberVO)session.getAttribute("MemberVO");
%>
     
   
<div class="px-4 px-lg-0">
 
  <div class="container text-white py-5 text-center">
    <h1 class="display-4">Beloved shopping cart</h1>
    <p class="lead mb-0"> </p>
           
  </div>


  <div class="pb-5">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
			<a href="<%=request.getContextPath()%>/front_end/prod/Shop.jsp">繼續購物</a>
         <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
               	  <th scope="col" class="border-0 bg-light">
                    <div class="p-2 px-3 text-uppercase">商品</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">價格</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">數量</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">小計</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">移除</div>
                  </th>
                 
                 
                	 
                </tr>
               
              </thead>
              
              <tbody>
              <%if (buylist != null && (buylist.size() >0)){ %>
              <%
              	 int sum = 0;
                 for(int index = 0;index < buylist.size();index++){
            	 ProdVO prodVO = buylist.get(index);
            	 sum=sum+(prodVO.getPrice()*prodVO.getQuantity());	
               %>
              
                <tr>
                  <th scope="row" class="border-0">
                    <div class="p-2">
              
                      <img src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic1() %>" width="70" class="img-fluid rounded shadow-sm">
                      <div class="ml-3 d-inline-block align-middle">
                        <h5 class="mb-0"> <a href="<%=request.getContextPath()%>/front_end/prod/Prod_Detail.jsp?prodno=<%=prodVO.getProdno()%>"
                        class="text-dark d-inline-block align-middle"><%=prodVO.getProdname()%></a></h5>
                        <span class="text-muted font-weight-normal font-italic d-block">
                        Category:
                        <% for(ProdsortVO prodsortVO : prodsortSvc.getAll()){ %>
                       
						<c:if test="<%=prodVO.getProdsortno() == prodsortVO.getProdsortno()%>">
	                    		<%=prodsortVO.getProdsortname()%>
             			</c:if>
						<%} %>
					</span>
                      </div>
                    </div>
                  </th>
                  <td class="border-0 align-middle"><strong ><%=prodVO.getPrice()%></strong></td>
                  
                  <td class="border-0 align-middle">
                  
                  <a class="btn btn-sm btn-des" href="<%=request.getContextPath()%>/prod/cart.do?action=DesQuan&prodno=<%=prodVO.getProdno()%>"><i class="fa fa-minus-square" aria-hidden="true" ></i></a>
                  
                  <strong  style="font-size:20px; color:black;" id="quantity"><%=prodVO.getQuantity()%></strong>
                  
                  <a class="btn btn-sm btn-inc" href="<%=request.getContextPath()%>/prod/cart.do?action=IncQuan&prodno=<%=prodVO.getProdno()%>"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
                  
                  </td>
                
                  <td class="border-0 align-middle"><%=prodVO.getPrice()*prodVO.getQuantity()%></td>
                  <td class="border-0 align-middle">
                  <form action="<%=request.getContextPath()%>/prod/cart.do" method="post">
                  <input type="hidden" name="action" value="DeleteFromCart">
                  <input type="hidden" name="del" value="<%=index%>">
                  <button type="submit" class="btn"><i class="fa fa-trash"></i></button>
                  </form></td>
                  
                 <%} %> 
                   
                </tr>
                
              </tbody>
            </table>
            
          </div>
          
        </div>
      </div>
        
      <div class="row py-5 p-4 bg-white rounded shadow-sm">
        
        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">商品總和</div>
          <div class="p-4">
            <p class="font-italic mb-4">如買賣雙方使用本公司金流服務進行交易時發生退貨退款糾紛或其他消費爭議，得透過客服信箱向本公司要求協助處理</p>
            <ul class="list-unstyled mb-4">
           <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">商品總金額</strong>
                <h5 class="font-weight-bold">$<%=sum%></h5>
              </li>
            </ul><a href="<%=request.getContextPath()%>/front_end/order/order.jsp" class="btn btn-dark rounded-pill py-2 btn-block">去買單</a>
          
          </div>
          <%}else{%>
          <h5 style="color:red;"> 購物車內無商品</h5>
          <%}%> 
         
        </div>    
      </div>
     </div>   
  	</div>
   </div>
<div class="container text-white py-5 text-center">
<h6>瀏覽紀錄(取最近五筆) <a href="<%=request.getContextPath()%>/prod/prod.do?action=clearhistory" style="color:white;">清除</a></h6>
 <%
  if(list!=null){
  for(ProdVO prodVO : list){
 %> 
<a href="<%=request.getContextPath()%>/front_end/prod/Prod_Detail.jsp?prodno=<%=prodVO.getProdno()%>" target=_blank><img src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic1()%>" height="150" border=0></a>
<%}}%>
</div>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>