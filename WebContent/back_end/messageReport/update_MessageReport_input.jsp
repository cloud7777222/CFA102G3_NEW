<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.messagereport.model.*"%>

<%
	MessageReportVO messageReportVO = (MessageReportVO) request.getAttribute("messageReportVO");//MessageReportServlet.java (Concroller) �s�Jreq��messageReportVO���� (�]�A�������X��messageReportVO, �]�]�A��J��ƿ��~�ɪ�messageReportVO����)
%>

<%=messageReportVO == null%>--${messageReportVO.mesNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�׾¤峹�d�����|�ק� - update_MessageReport_input.jsp</title>

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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�׾¤峹�d�����|�ק� - update_MessageReport_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/messageReport/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/messageReport/MessageReportServlet" name="form1">
		<table>
			
			<tr>
				<td>���|�H�|���s��:<font color=red><b>*</b></font></td>
				<td><%=messageReportVO.getMemberNo()%></td>
			</tr>
			<tr>
				<td>�峹�d���s��:<font color=red><b>*</b></font></td>
				<td><%=messageReportVO.getMesNo()%></td>
			</tr>						
			<tr>
				<td>�峹���|�ɶ�:</td>
				<td><%=messageReportVO.getMesRepTime()%></td>
			</tr>
			<tr>
				<td>�峹�d�����|��]:</td>
				<td><input type="TEXT" name="mesRepFor" size="50"
					value="<%=messageReportVO.getMesRepFor()%>" /></td>
			</tr>
			<tr>
				<td>�峹���|�B�z���A:</td>
				<td>
				<input type="radio" name="mesRevState" id="unverified" value="1" checked ><label for="unverified">�ݼf��</label> 
				<input type="radio" name="mesRevState" id="verified" value="2" ><label for="verified">�f�ֳq�L</label>
				<input type="radio" name="mesRevState" id="reject" value="3" ><label for="reject">�f�֥��q�L</label>
				</td>
			</tr>			
			
		</table>
		<br> <input type="hidden" name="action" value="update"> 
			
			 <input type="hidden" name="memberNo" value="<%=messageReportVO.getMemberNo()%>">
			  <input type="hidden" name="mesNo" value="<%=messageReportVO.getMesNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllMemPerPage.jsp-->		  
			 <input type="submit" value="�e�X�ק�">
	</FORM>
</body>

</html>