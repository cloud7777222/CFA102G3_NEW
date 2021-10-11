<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>


<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");//PostServlet.java (Concroller) �s�Jreq��postVO���� (�]�A�������X��postVO, �]�]�A��J��ƿ��~�ɪ�postVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic" rel="stylesheet">

<title>�׾¤峹�s�W</title>
</head>
<style>
	body {
	  font-family: "Open Sans", sans-serif;
	  color: #5a656e;
	}
</style>


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


	<FORM action="<%=request.getContextPath()%>/post/PostServlet" method="post">

		<br>


		<table>
			<tr>
				<td>�峹���O�s��:</td>
				<td><input type="TEXT" name="postTypeNo" size="45" /></td>
			</tr>				
			<tr>
				<td>�|���s��:</td>
				<td><input type="TEXT" name="memberNo" size="45" /></td>
			</tr>
			<tr>
				<td>�峹���e:</td>
				<td><textarea class="form-control" name="postContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"></textarea></td>
			</tr>
			<tr>
				<td>�峹���A:</td>
				<td>
				<input type="radio" name="postState" id="hidden" value="0"><label for="hidden">����</label>
				<input type="radio" name="postState" id="show" value="1" checked><label for="show">���</label>
				</td>
			</tr>

		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�W��">
	</FORM>


</body>
</html>


