<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
    MemberService memberService = new MemberService();
    List<MemberVO> list = memberService.getAllMember();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>會員編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>相片</th>
		<th>姓名</th>
		<th>性別</th>
		<th>生日</th>
		<th>職業</th>
		<th>居住地</th>
		<th>手機</th>
		<th>信箱</th>
		<th>自我介紹</th>
		<th>點數</th>
		<th>黑名單</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
		<td>${memberVO.memberNo}</td>
		<td>${memberVO.memberAccount}</td>
		<td>${memberVO.memberPassword}</td>
		<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}"></td>
		<td>${memberVO.memberName}</td>
		<td>${memberVO.memberGender}</td>
		<td><fmt:formatDate value="${memberVO.memberBirthday}" pattern="yyyy-MM-dd HH:mm:ss.SSSZ"/></td>
		<td>${memberVO.memberJob}</td>
		<td>${memberVO.memberCountry}</td>
		<td>${memberVO.memberPhone}</td>
		<td>${memberVO.memberEmail}</td>
		<td>${memberVO.memberIntroduce}</td>
		<td>${memberVO.memberPoint}</td>
		<td>${memberVO.memberBlackList}</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>