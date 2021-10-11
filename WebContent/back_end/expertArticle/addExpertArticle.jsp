<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertarticle.model.*"%>

<%
	ExpertArticleVO eaVO = (ExpertArticleVO) request.getAttribute("eaVO");//ExpertAticleServlet.java (Concroller) �s�Jreq��eaVO���� (�]�A�������X��eaVO, �]�]�A��J��ƿ��~�ɪ�eaVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
		action="<%=request.getContextPath()%>/expertArticle/ExpertArticleServlet"
		method="post" enctype="multipart/form-data">

		<br>


		<table>
			<tr>
				<td>�M�a�s��:</td>
				<td><input type="TEXT" name="expertNo" size="45"
					value="<%=(eaVO == null) ? "" : ""%>" /></td>
			</tr>
			<tr>
				<td>�M�a�M��Ϥ�:</td>
				<td><input type="file" name="articlePhoto" size="45"
					value="<%=(eaVO == null) ? "" : eaVO.getArticlePhoto()%>" /></td>
					<td><img src="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet?postNo=${eppVO.postNo}"/></td>

			</tr>

			<tr>
				<td>�M�a�M�椺�e:</td>
				<td><textarea class="form-control" name="articleContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"><%=(eaVO == null) ? "" : eaVO.getArticleContent()%></textarea></td>
			</tr>
			<tr>
				<td>�M�a�M��o���A:</td>
				<td>
				<input type="radio" name="articleState" id="hidden" value="0"><label for="hidden">����</label>
				<input type="radio" name="articleState" id="show" value="1" checked><label for="show">���</label>
				</td>
			</tr>



		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�W��">
	</FORM>


</body>
</html>


