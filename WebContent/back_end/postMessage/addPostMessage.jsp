<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postmessage.model.*"%>


<%
	PostMessageVO postMessageVO = (PostMessageVO) request.getAttribute("postMessageVO");//PostMessageServlet.java (Concroller) �s�Jreq��postMessageVO���� (�]�A�������X��postMessageVO, �]�]�A��J��ƿ��~�ɪ�postMessageVO����)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�׾¤峹�d���s�W</title>
</head>



<body bgcolor="lightblue">

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM action="<%=request.getContextPath()%>/postMessage/PostMessageServlet" method="post">

		<br>


		<table>
						
			<tr>
				<td>�|���s��:</td>
				<td><input type="TEXT" name="memberNo" size="45" /></td>
			</tr>		
			<tr>
				<td>�峹�s��:</td>
				<td><input type="TEXT" name="postNo" size="45" /></td>
			</tr>	
			<tr>
				<td>�峹�d�����e:</td>
				<td><textarea class="form-control" name="mesContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"></textarea></td>
			</tr>
			<tr>
				<td>�峹���A:</td>
				<td>
				<input type="radio" name="mesState" id="hidden" value="0"><label for="hidden">����</label>
				<input type="radio" name="mesState" id="show" value="1" checked><label for="show">���</label>
				</td>
			</tr>

		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�W��">
	</FORM>


</body>
</html>


