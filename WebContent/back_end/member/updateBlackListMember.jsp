<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
</head>
<body>
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
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
				
	<form action="<%=request.getContextPath()%>/member/member" method="post">
			<table>
				<tr>
					<th>帳號</th>
					<td><%=memberVO.getMemberAccount()%></td>
				</tr>
				<tr>
					<th>黑名單</th>
					<td>
						<select id="MemberBlackList" name="memberBlackList">
        				<option value="1"${(memberVO.memberBlackList==1)?'selected':'' }>賦權</option>
        				<option value="0"${(memberVO.memberBlackList==0)?'selected':'' }>停權</option>
    					</select>
					</td>
				</tr>
			</table><br>
				<input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
                <input type="hidden" name="action" value="update_BlackList_Member">
                <input type="submit" value="送出">
    </form>
</body>
</html>