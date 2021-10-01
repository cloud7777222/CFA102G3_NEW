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
<title>�Ҧ��\��s���P��\�ऺ�e - listAllFunctionEmp.jsp</title>

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
				<h3>�Ҧ��\��s���P��\�ऺ�e - listAllFuncEmp.jsp</h3>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<c:forEach var="functionempVO" items="${list}">
			<tr>
				<th>�v���s��</th>
				<th>�v�����e����</th>
				<th>�ק�</th>
				<th>�R��</th>
			</tr>
			<tr>
				<td>${functionempVO.funcno}</td>
				<td>${functionempVO.funccontent}</td>


				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/funcemp/funcemp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="funcno" value="${functionempVO.funcno}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>


				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/funcemp/funcemp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="funcno" value="${functionempVO.funcno}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

	<h3>�v�����e�s�W</h3>
	<ul>
		<li><a href='addFunctionEmp.jsp'>Add</a> NEW Emp---Function</li>
	</ul>

	
</body>
</html>