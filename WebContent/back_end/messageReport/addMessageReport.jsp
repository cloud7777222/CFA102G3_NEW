<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.messagereport.model.*"%>

<%
	MessageReportVO messageReportVO = (MessageReportVO) request.getAttribute("messageReportVO");//MessageReportServlet.java (Concroller) �s�Jreq��messageReportVO���� (�]�A�������X��messageReportVO, �]�]�A��J��ƿ��~�ɪ�messageReportVO����)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�׾¤峹�d�����|�s�W</title>
</head>


<body bgcolor="lightgreen">

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM action="<%=request.getContextPath()%>/messageReport/MessageReportServlet" method="post">

		<br>


		<table>					
			<tr>
				<td>���|�H�|���s��:</td>
				<td><input type="TEXT" name="memberNo" size="45" /></td>
			</tr>
			<tr>
				<td>�峹�d���s��:</td>
				<td><input type="TEXT" name="mesNo" size="45" /></td>
			</tr>	
			<tr>
				<td>�d�����|��]:</td>
				<td><textarea class="form-control" name="mesRepFor" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"></textarea></td>
			</tr>
			<tr>
				<td>�d�����|�B�z���A:</td>
				<td>
				<input type="radio" name="mesRevState" id="unverified" value="1" ><label for="unverified">�ݼf��</label> 
				<input type="radio" name="mesRevState" id="verified" value="2" ><label for="verified">�f�ֳq�L</label>
				<input type="radio" name="mesRevState" id="reject" value="3" ><label for="reject">�f�֥��q�L</label>
				</td>
			</tr>			
		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�W��">
	</FORM>


</body>
</html>


