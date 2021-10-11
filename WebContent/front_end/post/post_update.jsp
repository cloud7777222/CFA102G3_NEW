<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>

<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");//PostServlet.java (Concroller) 存入req的postVO物件 (包括幫忙取出的postVO, 也包括輸入資料錯誤時的postVO物件)
%>

<%-- <%=postVO == null%>--${postVO.postNo}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">

<title>論壇文章修改 - post_update.jsp</title>


<style>
	body {
	  font-family: "Open Sans", sans-serif;
	  color: #5a656e;
	}
	section{
		margin-left: 100px;
	}
	div #title{
	margin-left: 30px;
	}
	table{
		cellspacing: 30px;
		cellpadding: 10px;
		bgcolor: white;
		border: 1px solid gray;
		margin-top: 20px;
		padding: 30px;
		bgcolor: white;
		box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.3);				
	}
</style>



</head>
<body bgcolor='white'>

<div id="container">
	
		<br>
	<div id="title"><h2>文章施工修改... <i class="fas fa-pencil-alt"></i></h2></div>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<section>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" name="form1">
		<table class="table">
<!-- 			<tr> -->
<!-- 				<td>文章編號:<font color=red><b>*</b></font></td> -->
<%-- 				<td><%=postVO.getPostNo()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>文章類別編號:<font color=red><b>*</b></font></td> -->
<!-- 				<td><input type="TEXT" name="postTypeNo" size="50" -->
<%-- 					value="<%=postVO.getPostTypeNo()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>會員編號:<font color=red><b>*</b></font></td> -->
<%-- 				<td><%=postVO.getMemberNo()%></td> --%>
<!-- 			</tr>						 -->
			<tr>
				<td>文章內容  :  </td>
				<td><textarea class="form-control" name="postContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"><%=(postVO == null) ? "" : postVO.getPostContent()%></textarea></td>
			</tr>					
			<tr>
<!-- 				<td>發表時間:</td> -->
<%-- 				<td><%=postVO.getPostTime()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>文章留言數:</td> -->
<%-- 				<td><%=postVO.getMesCount()%></td> --%>
<!-- 			</tr>			 -->
			<tr>
				<td>文章狀態  :  </td>
				<td>
				<input type="radio" name="postState" id="hidden" value="0"><label for="hidden">隱藏</label> 
				<input type="radio" name="postState" id="show" value="1" checked><label for="show">顯示</label></td>
			</tr>			
<!-- 			<tr> -->
<!-- 				<td>按讚數:</td> -->
<%-- 				<td><%=postVO.getNumOfLike()%></td> --%>
<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update">
			 <input type="hidden" name="postTypeNo" value="<%=request.getParameter("postTypeNo")%>">  
			 <input type="hidden" name="postNo" value="<%=postVO.getPostNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllMemPerPage.jsp-->		  
			 <input type="submit" value="送出修改">
	</FORM>
	</section>
	</div>
	
</body>

</html>