<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ActivityService activitySvc = new ActivityService();
List<ActivityVO> list = activitySvc.getAll();
pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有活動資料 - listAllActivity.jsp</title>

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
	width: 800px;
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
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有活動資料 - listAllActivity.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/activity/select_page.jsp"><img src="<%=request.getContextPath()%>/back_end/activity/images/original.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>活動編號</th>
		<th>活動類別編號</th>
		<th>活動名稱</th>
		<th>活動日期</th>
		<th>活動地點</th>
		<th>活動說明</th>
		
		<th>活動上下架狀態</th>
		<th>活動舉辦狀態</th>
		<th>活動報名開始日期</th>
		<th>活動報名截止日期</th>
		<th>活動圖片</th>
	
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="activityVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${activityVO.actNo}</td>
			<td>${activityVO.actType}</td>
			<td>${activityVO.actName}</td>
			<td>${activityVO.actDate}</td>
			<td>${activityVO.actLocation}</td>
			<td>${activityVO.actDirection}</td>
			
			<td>${activityVO.actState}</td>
			<td>${activityVO.actHoldState}</td>
			<td>${activityVO.actRegisterStartDate}</td>
			<td>${activityVO.actRegisterDeadLine}</td>
			<td>${activityVO.actPicture}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="actNo"  value="${activityVO.actNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="actNo"  value="${activityVO.actNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/back_end/activity/page2.file" %>

</body>
</html>