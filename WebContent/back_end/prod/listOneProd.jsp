<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="java.util.*"%>
<% ProdVO prodVO = (ProdVO)request.getAttribute("prodVO"); %>
 <%
    ProdService prodSvc = new ProdService();
    List<ProdVO> list = prodSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品搜尋結果</title>
<%@include file="/includes/head.jsp"%>
</head>
<body>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="container">
<a href ="<%=request.getContextPath()%>/back_end/index.jsp"> 回首頁</a>
</div>
<table class="table table-hover">

  <thead>
    <tr>
      <th scope="col">商品名稱</th>
      <th scope="col">商品價格</th>
      <th scope="col">商品介紹</th>
      <th scope="col">商品圖片一</th>
      <th scope="col">商品圖片二</th>
      <th scope="col">商品圖片三</th>
      </tr>
    </thead>
    <tr>

     <td>${prodVO.prodname}</td>
      <td>${prodVO.price}</td>
      <td>${prodVO.indroce}</td>
      <td><img src ="<%=request.getContextPath()%>/back_end/prod/images/${prodVO.prodpic1}" height="50px" height="50px"></td>
      <td><img src ="<%=request.getContextPath()%>/back_end/prod/images/${prodVO.prodpic2}" height="50px" height="50px"></td>
      <td><img src ="<%=request.getContextPath()%>/back_end/prod/images/${prodVO.prodpic3}" height="50px" height="50px"></td>
	  
</tr>

</table>
<%@include file="/includes/footer.jsp"%>
</body>
</html>