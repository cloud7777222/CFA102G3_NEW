<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertarticle.model.*"%>

<%
	ExpertArticleVO eaVO = (ExpertArticleVO) request.getAttribute("eaVO");//ExpertAticleServlet.java (Concroller) 存入req的eaVO物件 (包括幫忙取出的eaVO, 也包括輸入資料錯誤時的eaVO物件)
%>

<%=eaVO == null%>--${eaVO.articleNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>專家專欄修改 - update_ExpertArticle_input.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>專家專欄修改 - update_ExpertArticle_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/expertArticle/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/expertArticle/ExpertArticleServlet"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>專家專欄編號:<font color=red><b>*</b></font></td>
				<td><%=eaVO.getArticleNo()%></td>
			</tr>
			<tr>
				<td>專家編號:<font color=red><b>*</b></font></td>
				<td><%=eaVO.getExpertNo()%></td>
			</tr>
			<tr>
				<td>專家專欄圖片:</td>
				<td><input type="file" name="articlePhoto" size="45"
					value="<%=(eaVO == null) ? "" : eaVO.getArticlePhoto()%>" /></td>

			</tr>
			<tr>
				<td>貼文內容:</td>
				<td><input type="TEXT" name="articleContent" size="50"
					value="<%=eaVO.getArticleContent()%>" /></td>
			</tr>
			<tr>
				<td>發表時間:</td>

				<td><%=eaVO.getArticleTime()%></td>
			</tr>
			<tr>
				<td>按讚數:</td>
				<td><%=eaVO.getNumOfLike()%></td>
			</tr>
			<tr>
				<td>貼文狀態:</td>
				<td>
					<input type="radio" name="articleState" id="hidden" value="0"><label for="hidden">隱藏</label> 
					<input type="radio" name="articleState" id="show" value="1" checked><label for="show">顯示</label></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="articleNo" value="<%=eaVO.getArticleNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllExpertPerPage.jsp-->
			  
			 <input type="submit" value="送出修改">
	</FORM>
</body>

</html>