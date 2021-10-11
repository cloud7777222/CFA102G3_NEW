<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertpersonalpage.model.*"%>




<%
	ExpertPersonalPageVO eppVO = (ExpertPersonalPageVO) request.getAttribute("eppVO");//ExpertPersonalPageServlet.java (Concroller) 存入req的eppVO物件 (包括幫忙取出的eppVO, 也包括輸入資料錯誤時的eppVO物件)	

%>


<%= eppVO==null %>--${eppVO.postNo}---- ${param.postNo}--
<html>
<head>
<title>專家個人頁面貼文 - listOneExpertPerPage.jsp</title>

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
		 <h3>專家個人頁面貼文 - listOneExpertPerPage.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/expertPersonalPage/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>貼文編號</th>
		<th>專家編號</th>
		<th>貼文圖片</th>
		<th>貼文內容</th>
		<th>發表時間</th>
		<th>按讚數</th>
		<th>貼文狀態</th>
	</tr>
	<tr>
		<td>${eppVO.postNo}</td>
		<td>${eppVO.expertNo}</td>
		<td><img src="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet?postNo=${eppVO.postNo}"></td>
		<td>${eppVO.postContent}</td>
		<td>${eppVO.postTime}</td>
		<td>${eppVO.numOfLike}</td>
		<td>${eppVO.postState}</td>
	</tr>
</table>

</body>
</html>