<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postreport.model.*"%>

<%
	PostReportVO postReportVO = (PostReportVO) request.getAttribute("postReportVO");//PostReportServlet.java (Concroller) 存入req的postReportVO物件 (包括幫忙取出的postReportVO, 也包括輸入資料錯誤時的postReportVO物件)
%>

<%=postReportVO == null%>--${postReportVO.postNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>論壇文章檢舉修改 - update_PostReport_input.jsp</title>

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
				<h3>論壇文章檢舉修改 - update_PostReport_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/postReport/select_page.jsp"><img
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postReport/PostReportServlet" name="form1">
		<table>
			<tr>
				<td>文章編號:<font color=red><b>*</b></font></td>
				<td><%=postReportVO.getPostNo()%></td>
			</tr>
			<tr>
				<td>檢舉人會員編號:<font color=red><b>*</b></font></td>
				<td><%=postReportVO.getMemberNo()%></td>
			</tr>						
			<tr>
				<td>文章檢舉原因:</td>
				<td><input type="TEXT" name="postRepFor" size="50"
					value="<%=postReportVO.getPostRepFor()%>" /></td>
			</tr>
			<tr>
				<td>文章檢舉時間:</td>
				<td><%=postReportVO.getPostRepTime()%></td>
			</tr>
			<tr>
				<td>文章檢舉處理狀態:</td>
				<td>
				<input type="radio" name="postRevState" id="unverified" value="1" checked><label for="unverified">待審核</label> 
				<input type="radio" name="postRevState" id="verified" value="2" ><label for="verified">審核通過</label>
				<input type="radio" name="postRevState" id="reject" value="3" ><label for="reject">審核未通過</label>
				</td>
			</tr>			
			
		</table>
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="postNo" value="<%=postReportVO.getPostNo()%>">
			 <input type="hidden" name="memberNo" value="<%=postReportVO.getMemberNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllMemPerPage.jsp-->		  
			 <input type="submit" value="送出修改">
	</FORM>
</body>

</html>