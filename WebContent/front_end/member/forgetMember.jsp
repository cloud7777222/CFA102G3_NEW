<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


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
				<input type="text" id="MemberAccount" name="memberAccount" placeholder="帳號" minlength="6" maxlength="10"><br>
                <input type="password" id="MemberPassword" name="memberPassword" placeholder="密碼" minlength="6" maxlength="10"><br>
                <input type="text" id="MemberEmail" name="memberEmail" placeholder="E-Mail"  maxlength="40" required><br>
                <input type="hidden" name="action" value="insert_Member">
                <input type="submit" value="送出"></FORM>
    </form>
</body>
</html>