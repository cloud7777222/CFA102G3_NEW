<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertpersonalpage.model.*"%>


<%
	ExpertPersonalPageVO eppVO = (ExpertPersonalPageVO) request.getAttribute("eppVO");//ExpertPersonalPageServlet.java (Concroller) �s�Jreq��eppVO���� (�]�A�������X��eppVO, �]�]�A��J��ƿ��~�ɪ�eppVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�M�a�ӤH�K��s�W</title>
</head>



<body bgcolor="lightpink">

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM
		action="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet"
		method="post" enctype="multipart/form-data">

		<br>


		<table>
			<tr>
				<td>�M�a�s��:</td>
				<td><input type="TEXT" name="expertNo" size="45"
					value="<%=(eppVO == null) ? "" : ""%>" /></td>
			</tr>
			<tr>
				<td>�K��Ϥ�:</td>
				<td><input type="file" name="postPhoto" size="45"
					value="<%=(eppVO == null) ? "" : eppVO.getPostPhoto()%>" /></td>
					<td><img src="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet?postNo=${eppVO.postNo}"/></td>

			</tr>

			<tr>
				<td>�K�夺�e:</td>
				<td><textarea class="form-control" name="postContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"><%=(eppVO == null) ? "" : eppVO.getPostContent()%></textarea></td>
			</tr>
			<tr>
				<td>�K�媬�A:</td>
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


