<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員查詢</title>
<%@ include file="/back_end/header.jsp"%>
</head>
<body>
<%@ include file="/back_end/sliderbar.jsp"%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
    
 
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member" name="form1">
       <b><font color=blue>萬用複合查詢:</font></b> <br>
       
       <b>會員編號</b>
       <input type="text" name="memberNo"><br>
           
       <b>會員帳號</b>
       <input type="text" name="memberAccount"><br>
       
       <b>會員姓名</b>
       <input type="text" name="memberName"><br>	
       
       <b>會員信箱</b>
       <input type="text" name="memberEmail"><br>
       
       <b>會員電話</b>
       <input type="text" name="memberPhone"><br>
    
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listMember_ByCompositeQuery">
     </FORM>
  <%@ include file="/back_end/footer.jsp"%>
</body>
</html>


