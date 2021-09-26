<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.expert.model.*"%>  
 
 <%
  ExpertVO expertVO = (ExpertVO) request.getAttribute("expertVO"); //ActivityServlet.java(Concroller), 存入req的empVO物件
%> 
<!DOCTYPE html>
<html>
<head>
<title>專家資料 - listOneExpertVO.jsp</title>

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
		 <h3>專家資料 - listOneExpert.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/expert/select_page.jsp"><img src="<%=request.getContextPath()%>/images/original.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
		<tr>
		<th>專家編號</th>
		<th>專家種類編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>相片</th>
		<th>姓名</th>
		<th>性別</th>
		<th>電話</th>
		<th>Email</th>
		<th>地址</th>
		<th>自我介紹</th>
		<th>審核資料</th>
		<th>審核狀態</th>
		<th>點數</th>
		<th>黑名單</th>
		<th>評價總人數</th>
		<th>總分數</th>
	</tr>
	<tr>
	        <td><%=expertVO.getExpertNo()%></td>
	        <td><%=expertVO.getExpertGenreNo()%></td>
		    <td><%=expertVO.getExAcount()%></td>
			<td><%=expertVO.getExPassword()%></td>
			<td><%=expertVO.getExPhoto()%></td>
			<td><%=expertVO.getExName()%></td>
			<td><%=expertVO.getExGender()%></td>
		    <td><%=expertVO.getExPhone()%></td>
			<td><%=expertVO.getExEmail()%></td>
			<td><%=expertVO.getExAdress()%></td>
			<td><%=expertVO.getExIntroduce()%></td>
			<td><%=expertVO.getCheckData()%></td>
			<td><%=expertVO.getCheckState()%></td>
			<td><%=expertVO.getExPoint()%></td>
			<td><%=expertVO.getExBlackList()%></td>
			<td><%=expertVO.getExPopSum()%></td>
			<td><%=expertVO.getExPointSum()%></td>
		
	</tr>
</table>

</body>
</html>
