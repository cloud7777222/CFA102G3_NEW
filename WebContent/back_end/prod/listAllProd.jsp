<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="java.util.*"%>

<%
    ProdService prodSvc = new ProdService();
    List<ProdVO> list = prodSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="prodsortSrv" scope="page" class="com.prodsort.model.ProdsortService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全部商品</title>
<%@ include file="/back_end/header.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="cssandjs/sidebars.css">
</head>
<body>
<%@ include file="/back_end/sliderbar.jsp"%>
<div class="container">

</div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="container">
<form method="post" action="<%=request.getContextPath()%>/prod/prod.do">
<div style="color:red;">用商品類別搜尋</div>
<jsp:useBean id="prodsortSvc" scope="page" class="com.prodsort.model.ProdsortService" />
		<select size="1" name="prodsortno">
			<c:forEach var="prodsortVO" items="${prodsortSvc.all}">
				<option value="${prodsortVO.prodsortno}">${prodsortVO.prodsortname}
			</c:forEach>
	    </select>
<input type="hidden" name="action" value="getBySort">
<input type="submit" value="查詢產品">
</form>
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">商品類別</th>
      <th scope="col">商品名稱</th>
      <th scope="col">商品價格</th>
      <th scope="col">商品介紹</th>
      <th scope="col">商品圖片一</th>
      <th scope="col">商品圖片二</th>
      <th scope="col">商品圖片三</th>
      <th scope="col">修改</th>
      <th scope="col">刪除</th>
      <th scope="col">上下架狀態</th>
      <th scope="col">上下架</th>
    </tr>
    </thead>
  <%@ include file="/back_end/prod/page1.file" %> 
	<c:forEach var="prodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
  <tbody>
    <tr>
      <th scope="row">
      	<c:forEach var="prodsortVO" items="${prodsortSrv.all}">
			<c:if test="${prodVO.prodsortno==prodsortVO.prodsortno}">
	                    	${prodsortVO.prodsortname}
             </c:if>
		</c:forEach>
	  </th>
      <td>${prodVO.prodname}</td>
      <td>${prodVO.price}</td>
      <td>${prodVO.indroce}</td>
      <td><img src ="<%=request.getContextPath()%>/back_end/prod/images/${prodVO.prodpic1}" height="50px" height="50px"></td>
	  <td><img src ="<%=request.getContextPath()%>/back_end/prod/images/${prodVO.prodpic2}" height="50px" height="50px"></td>
	  <td><img src ="<%=request.getContextPath()%>/back_end/prod/images/${prodVO.prodpic3}" height="50px" height="50px"></td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/prod/prod.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="prodno"  value="${prodVO.prodno}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/prod/prod.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="prodno"  value="${prodVO.prodno}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
			<td>
			<c:if test="${prodVO.prodstate == 1}">上架</c:if>
			<c:if test="${prodVO.prodstate == 0}">下架</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/prod/prod.do" style="margin-bottom: 0px;">
			    
			     <button type="submit" class="btn"id ="btnstate">
			     <c:if test="${prodVO.prodstate == 1}"><i class="fa fa-arrow-circle-down" aria-hidden="true"></i></c:if>  
			     <c:if test="${prodVO.prodstate == 0}"><i class="fa fa-chevron-circle-up" aria-hidden="true"></i></c:if>
			     </button>
			      
				 <button type="submit" class="btn"></button>
			     <input type="hidden" name="prodno"  value="${prodVO.prodno}">
			      <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action" value="updatestate">
			  </FORM>
			  
			</td>
    </tr>
  </tbody>
  </c:forEach>
</table>

<%@ include file="/back_end/prod/page2.file" %>
<%@ include file="/back_end/footer.jsp"%>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>