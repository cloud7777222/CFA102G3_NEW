<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.expert.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ExpertService expertSvc = new ExpertService();
List<ExpertVO> list = expertSvc.getAll();
pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有專家資料 - listAllExpert.jsp</title>

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
		 <h3>所有專家資料 - listAllExpert.jsp</h3>
		 <h4><a href="/expert/select_page.jsp"><img src="/images/original.gif" width="100" height="32" border="0">回首頁</a></h4>
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
	<%@ include file="page1.file" %> 
	<c:forEach var="expertVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${expertVO.expertNo}</td>
			<td>${expertVO.expertGenreNo}</td>
			<td>${expertVO.exAcount}</td>
			<td>${expertVO.exPassword}</td>
			<td>${expertVO.exPhoto}</td>
			<td>${expertVO.exName}</td>
			<td>${expertVO.exGender}</td>
			<td>${expertVO.exPhone}</td>
			<td>${expertVO.exEmail}</td>
			<td>${expertVO.exAdress}</td>
			<td>${expertVO.exIntroduce}</td>
			<td>${expertVO.checkData}</td>
			<td>${expertVO.checkState}</td>
			<td>${expertVO.exPoint}</td>
			<td>${expertVO.exBlackList}</td>
			<td>${expertVO.exPopSum}</td>
			<td>${expertVO.exPointSum}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expert.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="expertNo"  value="${expertVO.expertNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expert.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="expertNo"  value="${expertVO.expertNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>