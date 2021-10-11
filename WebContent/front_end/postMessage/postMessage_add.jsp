<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postmessage.model.*"%>


<%
	PostMessageVO postMessageVO = (PostMessageVO) request.getAttribute("postMessageVO");//PostMessageServlet.java (Concroller) 存入req的postMessageVO物件 (包括幫忙取出的postMessageVO, 也包括輸入資料錯誤時的postMessageVO物件)
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>論壇文章留言新增</title>

</head>
<style>
	body {
	  font-family: "Open Sans", sans-serif;
	  color: #5a656e;
	  margin-top:100px;
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
		margin-bottom: 20px;
		padding: 100px;
		box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.3);
					
	}
	
</style>


<body bgcolor="white">
<%-- <% out.println("postTypeNo : "+request.getParameter("postTypeNo")); %> --%>
<%-- <% out.println("postNo : "+request.getParameter("postNo")); %> --%>

<div id="container">
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div id="title"><h2>我要留言.... <i class="far fa-comment"></i></h2></div>
<section>
	<FORM action="<%=request.getContextPath()%>/postMessage/PostMessageServlet" method="post">

		<br>

			<table>
							
<!-- 				<tr> -->
<!-- 					<td>會員編號  :  </td> -->
<!-- 					<td><input type="TEXT" name="memberNo" size="45" /></td> -->
<!-- 				</tr>		 -->
				<tr>
	<!-- 				<td>文章編號:</td> -->
	<%-- 				<td><%=request.getParameter("postNo")%></td> --%>								
				</tr>	
				<tr>
					<td>留言內容  :  </td>
					<td><textarea class="form-control" name="mesContent" id="message"
							placeholder="Message" required="required"
							data-validation-required-message="Please enter your message"
							aria-invalid="false" rows="10"  cols="40"></textarea></td>
				</tr>
	<!-- 			<tr> -->
	<!-- 				<td>文章狀態:</td> -->
	<!-- 				<td> -->
	<!-- 				<input type="radio" name="mesState" id="hidden" value="0"><label for="hidden">隱藏</label> -->
	<!-- 				<input type="radio" name="mesState" id="show" value="1" checked><label for="show">顯示</label> -->
	<!-- 				</td> -->
	<!-- 			</tr> -->
	
			</table>
	
	
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="postTypeNo" value="<%=request.getParameter("postTypeNo")%>">
			<input type="hidden" name="postNo" value="<%=request.getParameter("postNo")%>">
			<button type="submit" class="btn btn-info">上傳</button>  
<!-- 			<input type="submit" value="上傳"> -->
		</FORM>
	
	</section>
</div>	

	<!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>


</body>
</html>


