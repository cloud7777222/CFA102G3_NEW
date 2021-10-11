<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertarticle.model.*"%>

<%
	ExpertArticleVO eaVO = (ExpertArticleVO) request.getAttribute("eaVO");//ExpertAticleServlet.java (Concroller) �s�Jreq��eaVO���� (�]�A�������X��eaVO, �]�]�A��J��ƿ��~�ɪ�eaVO����)
%>

<%=eaVO == null%>--${eaVO.articleNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�M�a�M��ק� - update_ExpertArticle_input.jsp</title>

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
				<h3>�M�a�M��ק� - update_ExpertArticle_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/expertArticle/select_page.jsp"><img
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

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/expertArticle/ExpertArticleServlet"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>�M�a�M��s��:<font color=red><b>*</b></font></td>
				<td><%=eaVO.getArticleNo()%></td>
			</tr>
			<tr>
				<td>�M�a�s��:<font color=red><b>*</b></font></td>
				<td><%=eaVO.getExpertNo()%></td>
			</tr>
			<tr>
				<td>�M�a�M��Ϥ�:</td>
				<td><input type="file" name="articlePhoto" size="45"
					value="<%=(eaVO == null) ? "" : eaVO.getArticlePhoto()%>" /></td>

			</tr>
			<tr>
				<td>�K�夺�e:</td>
				<td><input type="TEXT" name="articleContent" size="50"
					value="<%=eaVO.getArticleContent()%>" /></td>
			</tr>
			<tr>
				<td>�o��ɶ�:</td>

				<td><%=eaVO.getArticleTime()%></td>
			</tr>
			<tr>
				<td>���g��:</td>
				<td><%=eaVO.getNumOfLike()%></td>
			</tr>
			<tr>
				<td>�K�媬�A:</td>
				<td>
					<input type="radio" name="articleState" id="hidden" value="0"><label for="hidden">����</label> 
					<input type="radio" name="articleState" id="show" value="1" checked><label for="show">���</label></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="articleNo" value="<%=eaVO.getArticleNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllExpertPerPage.jsp-->
			  
			 <input type="submit" value="�e�X�ק�">
	</FORM>
</body>

</html>