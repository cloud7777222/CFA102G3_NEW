<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
		 <h3>員工資料 - ListOneMember.jsp</h3>
		 <h4><a href="/CFA102G3/front_end/member/logInMember.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>相片</th>
		<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}"></td>
	</tr>
	<tr>
		<th>姓名</th>
		<td><%=memberVO.getMemberName()%></td>
	</tr>
	<tr>
		<th>性別</th>
		<td><%=memberVO.getMemberGender()%></td>
	</tr>
	<tr>
		<th>生日</th>
		<td><%=memberVO.getMemberBirthday()%></td>
	</tr>
	<tr>
		<th>職業</th>
		<td><%=memberVO.getMemberJob()%></td>
	</tr>
	<tr>
		<th>居住地</th>
		<td><%=memberVO.getMemberCountry()%></td>
	</tr>
	<tr>
		<th>手機</th>
		<td><%=memberVO.getMemberPhone()%></td>
	</tr>
	<tr>
		<th>自我介紹</th>
		<td><%=memberVO.getMemberIntroduce()%></td>
	</tr>
</table>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member" style="margin-bottom: 0px;">
			     <input type="submit" value="修改個人資料">
			     <input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
			     <input type="hidden" name="action"	value="update_Member_Profile">
			</FORM>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member" style="margin-bottom: 0px;">
			     <input type="submit" value="修改密碼">
			     <input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
			     <input type="hidden" name="action"	value="update_Password_Member_Profile">
			</FORM>
</body>
</html>