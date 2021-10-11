<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.activity.model.*"%>  
 
 <%
  ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); //ActivityServlet.java(Concroller), 存入req的empVO物件
%> 
<!DOCTYPE html>
<html>
<head>
<title>活動資料 - listOneActivity.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>活動資料 - listOneActivity.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/activity/select_page.jsp"><img src="<%=request.getContextPath()%>/back_end/activity/images/original.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
	    <th>活動編號</th>
		<th>活動類別編號</th>
		<th>活動名稱</th>
		<th>活動日期</th>
		<th>活動地點</th>
		<th>活動說明</th>
		<th>報名人數上限</th>
		<th>報名人數下限</th>
		<th>活動上下架狀態</th>
		<th>活動舉辦狀態</th>
		<th>活動報名開始日期</th>
		<th>活動報名截止日期</th>
		<th>總星星數</th>
		<th>評價總人數</th>
	</tr>
	<tr>
	        <td><%=activityVO.getActNo()%></td>
	        <td><%=activityVO.getActType()%></td>
		    <td><%=activityVO.getActName()%></td>
			<td><%=activityVO.getActDate()%></td>
			<td><%=activityVO.getActLocation()%></td>
			<td><%=activityVO.getActDirection()%></td>
			<td><%=activityVO.getMaxParticipant()%></td>
		    <td><%=activityVO.getMinParticipant()%></td>
			<td><%=activityVO.getActState()%></td>
			<td><%=activityVO.getActHoldState()%></td>
			<td><%=activityVO.getActRegisterStartDate()%></td>
			<td><%=activityVO.getActRegisterDeadLine()%></td>
			<td><%=activityVO.getTotalStar()%></td>
			<td><%=activityVO.getTotalEvaluator()%></td>
	
		
	</tr>
</table>

</body>
</html>
