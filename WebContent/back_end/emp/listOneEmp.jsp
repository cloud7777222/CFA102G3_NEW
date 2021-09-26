<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>
<% EmpVO empVO = (EmpVO)request.getAttribute("empVO"); %>
<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list1 = empSvc.getAll();
    pageContext.setAttribute("list1",list1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工搜尋結果</title>
<%@include file="/includes/head.jsp"%>
</head>
<body>
<a href ="<%=request.getContextPath()%>/back_end/index.jsp"> 回首頁</a><br>
<table class="table table-hover">

  <thead>
    <tr>
      <th scope="col">員工編號</th>
      <th scope="col">員工姓名</th>
      <th scope="col">員工帳號</th>
      <th scope="col">員工密碼</th>
      <th scope="col">員工狀態</th>
      
     
      </tr>
    </thead>
    <tr>

      <td>${empVO.empno}</td>
      <td>${empVO.empname}</td>
      <td>${empVO.empaccount}</td>
      <td>${empVO.emppassword}</td>
      <td>
      	<c:if test="${empVO.empstate == 1}">在職</c:if>
		<c:if test="${empVO.empstate == 0}">離職</c:if>
      </td>
      
	  
</tr>

</table>


<%@include file="/includes/footer.jsp"%>
</body>
</html>