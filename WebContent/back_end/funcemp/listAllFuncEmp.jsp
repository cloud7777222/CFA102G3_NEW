<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.empfunction.model.*"%>
<%@ page import="com.functionemp.model.*"%>


<%
	FunctionEmpService funcEmpSvc = new FunctionEmpService();
	List<FunctionEmpVO> list = funcEmpSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有功能編號與其功能內容 - listAllFunctionEmp.jsp</title>

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
		<tr>
			<td>
				<h3>所有功能編號與其功能內容 - listAllFuncEmp.jsp</h3>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<c:forEach var="functionempVO" items="${list}">
			<tr>
				<th>權限編號</th>
				<th>權限內容說明</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<tr>
				<td>${functionempVO.funcno}</td>
				<td>${functionempVO.funccontent}</td>


				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/funcemp/funcemp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="funcno" value="${functionempVO.funcno}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>


				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/funcemp/funcemp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="funcno" value="${functionempVO.funcno}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

	<h3>權限內容新增</h3>
	<ul>
		<li><a href='addFunctionEmp.jsp'>Add</a> NEW Emp---Function</li>
	</ul>

	
</body>
</html>