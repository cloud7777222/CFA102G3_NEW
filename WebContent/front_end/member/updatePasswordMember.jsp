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

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
				
	<form action="<%=request.getContextPath()%>/member/member" method="post">
					<input type="text" id="MemberPassword" name="memberPassword" placeholder="舊密碼" minlength="6" maxlength="10">
					<input type="text" id="MemberPasswordNew" name="memberPasswordNew" placeholder="新密碼" minlength="6" maxlength="10">
					<input type="text" id="MemberPasswordNewCheck" name="memberPasswordNewCheck" placeholder="確認新密碼" minlength="6" maxlength="10"><br>
				<input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
                <input type="hidden" name="action" value="update_Password_Member">
                <input type="submit" value="送出">
    </form>
</body>
</html>