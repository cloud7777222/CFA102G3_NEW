<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工更新</title>
<%@ include file="/back_end/header.jsp"%>
</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<h3>員工資料更新</h3>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do">
<table>
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td>${empVO.empno}</td>
	</tr>
	<tr>	
		<td>員工姓名:</td>
		<td><input name="empname"  type="TEXT" value="${empVO.empname}"/></td>
	</tr>
	<tr>
		<td>員工帳號:</td>
		<td><input name="empaccount" type="text" value="${empVO.empaccount}"></td>
	</tr>
	<tr>
		<td>員工密碼:</td>
		<td><input name="emppassword" type="text" value="${empVO.emppassword}"></td>
	</tr>
	<tr>
		<td>員工狀態:</td>
		<td><input name="empstate" type="radio" value="1" id="state_1"checked>
		<label for="state_1">在職</label>
		<input name="empstate" type="radio" value="0" id="state_0">
		<label for="state_0">離職</label></td>
	</tr>

</table>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="${empVO.empno}">
<input type="submit" value="送出修改">
</FORM>
<%@ include file="/back_end/footer.jsp"%> 
</body>
</html>