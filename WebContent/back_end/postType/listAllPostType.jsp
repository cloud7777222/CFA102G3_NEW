<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.posttype.model.*"%>
<%@ page import="java.util.*"%>



<%
	PostTypeService postTypeSvc = new PostTypeService();
    List<PostTypeVO> list = postTypeSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<%-- <%= postTypeSvc==null %>---- ${param.postTypeNo}--    --%>
     
<html>
<head>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>論壇所有文章類別 - listAllPostType.jsp</title>
<%@ include file="/back_end/header.jsp"%>
<style>
	body{
		width: 100%; 
		height: 100%;
		font-family: 'Noto Sans TC', sans-serif;
		font-size: 14px !important;
		
	}
	table.table-hover{
		width: 100%; 
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
<div class="container">
<body bgcolor='white'>

	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp">文章類別管理首頁</a></li>
	    <li class="breadcrumb-item active" aria-current="page">所有文章類別</li>
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
		<td>類別編號</td>
		<td>類別名稱</td>
		<td>修改</td>
		<td>查詢類別文章</td>
	</tr>
	</thead>
	 <tbody>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="postTypeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(postTypeVO.postTypeNo==param.postTypeNo) ? 'bgcolor=#ffd500':''}><!--將修改的那一筆加入對比色而已-->
		
			<td>${postTypeVO.postTypeNo}</td>
			<td>${postTypeVO.postType}</td>
			
			<td>						
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postType/PostTypeServlet" style="margin-bottom: 0px;">
			  	 <button type="submit" class="btn btn-primary">修改</button> 			    
			     <input type="hidden" name="postTypeNo"  value="${postTypeVO.postTypeNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			     
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postType/PostTypeServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <button type="submit" class="btn btn-primary">刪除</button> 	 -->
<%-- 			     <input type="hidden" name="postTypeNo"  value="${postTypeVO.postTypeNo}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			      --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postType/PostTypeServlet" style="margin-bottom: 0px;">
			    <button type="submit" class="btn btn-primary">查詢</button> 
			    <input type="hidden" name="postTypeNo" value="${postTypeVO.postTypeNo}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->		
			    <input type="hidden" name="action" value="listPostsBy_PostTypeNo_B"></FORM>
			</td>
			
		</tr>
	</c:forEach>
 </tbody>
</table>
<%@ include file="pages/page2.file" %>

<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>


<%if (request.getAttribute("listPostsBy_PostTypeNo")!=null){%>
       <jsp:include page="listPostsBy_PostTypeNo.jsp" />
<%} %>
  <%@ include file="/back_end/footer.jsp"%> 
</div>
</body>
</html>