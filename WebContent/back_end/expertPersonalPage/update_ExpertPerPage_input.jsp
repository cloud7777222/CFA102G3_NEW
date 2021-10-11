<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertpersonalpage.model.*"%>

<%
	ExpertPersonalPageVO eppVO = (ExpertPersonalPageVO) request.getAttribute("eppVO");//ExpertPersonalPageServlet.java (Concroller) �s�Jreq��eppVO���� (�]�A�������X��eppVO, �]�]�A��J��ƿ��~�ɪ�eppVO����)
%>

<%=eppVO == null%>--${eppVO.postNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�M�a�ӤH�����K��ק� - update_ExpertPerPage_input.jsp</title>

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
				<h3>�M�a�ӤH�����K��ק� - update_ExpertPerPage_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/expertPersonalPage/select_page.jsp"><img
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
		ACTION="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>�K��s��:<font color=red><b>*</b></font></td>
				<td><%=eppVO.getPostNo()%></td>
			</tr>
			<tr>
				<td>�M�a�s��:<font color=red><b>*</b></font></td>
				<td><%=eppVO.getExpertNo()%></td>
			</tr>
			<tr>
				<td>�K��Ϥ�:</td>
				<td><input type="file" name="postPhoto" size="45"
					value="<%=(eppVO == null) ? "" : eppVO.getPostPhoto()%>" /></td>

			</tr>
			<tr>
				<td>�K�夺�e:</td>
				<td><input type="TEXT" name="postContent" size="50"
					value="<%=eppVO.getPostContent()%>" /></td>
			</tr>
			<tr>
				<td>�o��ɶ�:</td>

				<td><%=eppVO.getPostTime()%></td>
			</tr>
			<tr>
				<td>���g��:</td>
				<td><%=eppVO.getNumOfLike()%></td>
			</tr>
			<tr>
				<td>�K�媬�A:</td>
				<td><input type="radio" name="postState" id="hidden" value="0"><label
					for="hidden">����</label> <input type="radio" name="postState"
					id="show" value="1" checked><label for="show">���</label></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="postNo" value="<%=eppVO.getPostNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllExpertPerPage.jsp-->
			  
			 <input type="submit" value="�e�X�ק�">
	</FORM>
</body>

</html>