<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>
<%@ page import="java.util.*"%>



<%
	MemPersonalPageService mppSvc = new MemPersonalPageService();
    List<MemPersonalPageVO> list = mppSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<%-- <%= mppSvc==null %>---- ${param.postNo}--    --%>

 
      

<html>
<head>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">

<title>所有會員個人頁面貼文 - listAllMemPerPage.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/memPersonalPage/css/listAllMemPerPage.css">
<%@ include file="/back_end/header.jsp"%>

</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<div class="container">
   <body bgcolor='white'>

		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">		    		    
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/memPersonalPage/select_page.jsp">個人貼文管理首頁</a></li>
		    <li class="breadcrumb-item active" aria-current="page">所有個人貼文檢視</li>
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
		<td>貼文編號</td>
		<td>會員編號</td>
		<td>圖片</td>
		<td>內容</td>
		<td>發表時間</td>
		<td>按讚數</td>
		<td>貼文狀態</td>
		<td>修改</td>
<!-- 		<td>刪除</td> -->
	</tr>
	</thead>
	 <tbody>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="mppVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr ${(mppVO.postNo==param.postNo) ? 'bgcolor=#ffd500':''}><!--將修改的那一筆加入對比色而已-->
			<td>${mppVO.postNo}</td>
			<td>${mppVO.memberNo}</td>
			<td><img src="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet?postNo=${mppVO.postNo}"/></td>
			<td>${mppVO.postContent}</td>
			<td>${mppVO.postTime}</td>
			<td>${mppVO.numOfLike}</td>
			<td>${mppVO.postState}</td>
			
			
			<td>						
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" style="margin-bottom: 0px;">
			     <button type="submit" class="btn btn-primary">修改</button>
			     <input type="hidden" name="postNo"  value="${mppVO.postNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			     
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="postNo"  value="${mppVO.postNo}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			      --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="pages/page2.file" %>

<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>
  <%@ include file="/back_end/footer.jsp"%> 
  </div>  
</body>
</html>