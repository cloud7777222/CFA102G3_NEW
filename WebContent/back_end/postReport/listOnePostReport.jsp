<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postreport.model.*"%>


<%
	PostReportVO postReportVO = (PostReportVO) request.getAttribute("postReportVO");//PostReportServlet.java (Concroller) 存入req的postReportVO物件 (包括幫忙取出的postReportVO, 也包括輸入資料錯誤時的postReportVO物件)
%>


<%= postReportVO==null %>--${postReportVO.postNo}--
<html>
<head>
<title>單一文章檢舉檢視 - listOnePostReport.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  img{
  	max-width: 100%
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>單一文章檢舉檢視 - listOnePostReport.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/postReport/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>文章編號</th>		
		<th>檢舉人會員編號</th>		
		<th>文章檢舉時間</th>
		<th>文章檢舉原因</th>		
		<th>文章檢舉處理狀態</th>
	</tr>
	<tr>
		<td>${postReportVO.postNo}</td>
		<td>${postReportVO.memberNo}</td>		
		<td>${postReportVO.postRepTime}</td>
		<td>${postReportVO.postRepFor}</td>
		<td>${postReportVO.postRevState}</td>		
	</tr>
</table>

</body>
</html>