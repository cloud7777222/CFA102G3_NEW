<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postreport.model.*"%>


<%
	PostReportVO postReportVO = (PostReportVO) request.getAttribute("postReportVO");//PostReportServlet.java (Concroller) �s�Jreq��postReportVO���� (�]�A�������X��postReportVO, �]�]�A��J��ƿ��~�ɪ�postReportVO����)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�׾¤峹���|�s�W</title>
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


	<FORM action="<%=request.getContextPath()%>/postReport/PostReportServlet" method="post">

		<br>


		<table>
			<tr>
				<td>�峹�s��:</td>
				<td><input type="TEXT" name="postNo" size="45" /></td>
			</tr>				
			<tr>
				<td>���|�H�|���s��:</td>
				<td><input type="TEXT" name="memberNo" size="45" /></td>
			</tr>
			<tr>
				<td>�峹���|��]:</td>
				<td><textarea class="form-control" name="postRepFor" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"></textarea></td>
			</tr>
			<tr>
				<td>�峹���|�B�z���A:</td>
				<td>
				<input type="radio" name="postRevState" id="unverified" value="1" checked><label for="unverified">�ݼf��</label> 
				<input type="radio" name="postRevState" id="verified" value="2" ><label for="verified">�f�ֳq�L</label>
				<input type="radio" name="postRevState" id="reject" value="3" ><label for="reject">�f�֥��q�L</label>
				</td>
			</tr>			
		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�W��">
	</FORM>


</body>
</html>


