<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>


<jsp:useBean id="listPostsBy_PostTypeNo" scope="request" type="java.util.Set<PostVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="postTypeSvc" scope="page" class="com.posttype.model.PostTypeService" />


<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<head>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>類別文章 - listPostsBy_PostTypeNo.jsp</title>


<style>	
	body{
		width: 100%; 
		height: 100%;
		font-family: 'Noto Sans TC', sans-serif !important;
		font-size: 15px !important;
		
	}
	table.table-hover{
		width: 80%; 
		height: auto;
		font-size: 13px !important;
		cellpadding: 5px !important;
		border: 2px;
		bordercolor: black;
		margin-bottom: 100px;
	}
	button{
		font-size: 12px !important;
	}
	div.errorMsgs{
		margin-left: 250px;
		margin-top: 10px;
		font-size: 14px !important;	
	}	
</style>

</head>

<div class="container">
<body bgcolor='white'>

	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp">文章類別管理首頁</a></li>
	    <li class="breadcrumb-item active" aria-current="page">單一類別文章</li>
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
			<td>文章編號</td>
			<td>文章類別</td>
			<td>會員編號</td>		
			<td>內容</td>
			<td>發表時間</td>		
			<td>文章狀態</td>
			<td>留言數</td>
			<td>按讚數</td>
			<td>修改</td>
		</tr>
		</thead>
		<tbody>
	
		<c:forEach var="postVO" items="${listPostsBy_PostTypeNo}">
			<tr ${(postVO.postNo==param.postNo) ? 'bgcolor=#ffd500':''}><!--將修改的那一筆加入對比色而已-->
			
				<td>${postVO.postNo}</td>
				<td>(${postVO.postTypeNo})  ${postTypeSvc.getOnePostType(postVO.postTypeNo).postType}</td>
				<td>${postVO.memberNo}</td>		
				<td>${postVO.postContent}</td>
				<td>${postVO.postTime}</td>
				<td>${postVO.postState}</td>
				<td>${postVO.mesCount}</td>
				<td>${postVO.numOfLike}</td>								
				<td>						
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" style="margin-bottom: 0px;">
				     <button type="submit" class="btn btn-primary">修改</button> 
				     <input type="hidden" name="postNo"  value="${postVO.postNo}">
				     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->			              		     
				     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
<!-- 				<td> -->
<%-- 				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" style="margin-bottom: 0px;"> --%>
<!-- 				     <button type="submit" class="btn btn-primary">刪除</button>  -->
<%-- 				     <input type="hidden" name="postNo"  value="${postVO.postNo}"> --%>
<%-- 				     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->			             		      --%>
<!-- 				     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
		</tbody>
	</table>


<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>
</div>

</body>
</html>