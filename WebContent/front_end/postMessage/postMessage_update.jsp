<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postmessage.model.*"%>

<%
	PostMessageVO postMessageVO = (PostMessageVO) request.getAttribute("postMessageVO");//PostMessageServlet.java (Concroller) �s�Jreq��postMessageVO���� (�]�A�������X��postMessageVO, �]�]�A��J��ƿ��~�ɪ�postMessageVO����)
%>

<%=postMessageVO == null%>--${postMessageVO.mesNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�׾¤峹�d���ק� - update_PostMessage_input.jsp</title>



</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�׾¤峹�d���ק� - update_PostMessage_input.jsp</h3>
				
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" name="form1">
		<table>
			<tr>
				<td>�d���s��:<font color=red><b>*</b></font></td>
				<td><%=postMessageVO.getMesNo()%></td>
			</tr>
			<tr>
				<td>�|���s��:<font color=red><b>*</b></font></td>
				<td><%=postMessageVO.getMemberNo()%></td>
			</tr>
			<tr>
				<td>�峹�s��:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="postNo" size="50"
					value="<%=postMessageVO.getPostNo()%>" /></td>
			</tr>						
			<tr>
				<td>�d�����e:</td>
				<td><input type="TEXT" name="mesContent" size="50"
					value="<%=postMessageVO.getMesContent()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>�d���ɶ�:</td> -->
<%-- 				<td><%=postMessageVO.getMesTime()%></td> --%>
<!-- 			</tr>			 -->
			<tr>
				<td>�峹�d�����A:</td>
				<td>
				<input type="radio" name="mesState" id="hidden" value="0"><label for="hidden">����</label> 
				<input type="radio" name="mesState" id="show" value="1" checked><label for="show">���</label></td>
			</tr>			


		</table>
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="mesNo" value="<%=postMessageVO.getMesNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllMemPerPage.jsp-->		  
			 <input type="submit" value="�e�X�ק�">
	</FORM>
</body>

</html>