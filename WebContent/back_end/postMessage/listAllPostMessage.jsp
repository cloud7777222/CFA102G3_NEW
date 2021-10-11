<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postmessage.model.*"%>
<%@ page import="java.util.*"%>
<%
	PostMessageService postMessageSvc = new PostMessageService();
    List<PostMessageVO> list = postMessageSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<%-- <%= postMessageSvc==null %>---- ${param.postNo}--    --%>

 
<html>
<head>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>所有論壇文章留言 - listAllPostMessage.jsp</title>
<%@ include file="/back_end/header.jsp"%>
<style>
	body{
		width: 100%; 
		height: 100%;
		font-family: 'Noto Sans TC', sans-serif;
		font-size: 14px !important;
		
	}
	table.table-hover{
		width: 70%; 
		height: auto;
		font-size: 14px !important;
		cellpadding: 5px !important;
		border: 2px;
		bordercolor: black;
	}
	button{
		font-size: 12px !important;
	}
	nav{
		font-size: 14px !important;
	} 
</style>
</head>
<%@ include file="/back_end/sliderbar.jsp"%>

<body bgcolor='white'>
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postMessage/select_page.jsp">文章留言管理首頁</a></li>
		    <li class="breadcrumb-item active" aria-current="page">所有論壇文章留言</li>
		  </ol>
		</nav>



<%-- 錯誤表列 --%>
<div class="errorMsgs">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>

<table class="table table-hover">
	<thead>
	<tr>
		<td>文章留言編號</td>		
		<td>會員編號</td>
		<td>文章編號</td>		
		<td>文章留言內容</td>
		<td>留言時間</td>		
		<td>留言狀態</td>	
		<td>修改</td>		
	</tr>
	</thead>
	 <tbody>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="postMessageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(postMessageVO.mesNo==param.mesNo) ? 'bgcolor=#ffd500':''}><!--將修改的那一筆加入對比色而已-->
		
		<td>${postMessageVO.mesNo}</td>		
		<td>${postMessageVO.memberNo}</td>
		<td>${postMessageVO.postNo}</td>		
		<td>${postMessageVO.mesContent}</td>
		<td>${postMessageVO.mesTime}</td>
		<td>${postMessageVO.mesState}</td>		
			
			
			<td>						
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" style="margin-bottom: 0px;">
			     <button type="submit" class="btn btn-primary">修改</button>
			     <input type="hidden" name="mesNo"  value="${postMessageVO.mesNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			     
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="mesNo"  value="${postMessageVO.mesNo}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			      --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>
  <%@ include file="/back_end/footer.jsp"%> 
</body>
</html>