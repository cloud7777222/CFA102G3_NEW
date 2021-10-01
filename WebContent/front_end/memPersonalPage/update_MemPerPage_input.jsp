<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>

<%
MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) �s�Jreq��mppVO���� (�]�A�������X��mppVO, �]�]�A��J��ƿ��~�ɪ�mppVO����)
%>

<%= mppVO==null %>--${mppVO.postNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�|���ӤH�����K��ק� - update_MemPerPage_input.jsp</title>

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
	<tr><td>
		 <h3>�|���ӤH�����K��ק� - update_MemPerPage_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="MemPersonalPageServlet" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�K��s��:<font color=red><b>*</b></font></td>
		<td><%=mppVO.getPostNo()%></td>
	</tr>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><%=mppVO.getMemberNo()%></td>
	</tr>
	<tr>
		<td>�K��Ϥ�:</td>
		<td><input type="file" name="postPhoto" size="45"
			 value="<%= (mppVO==null)? "" : mppVO.getPostPhoto()%>" /></td>
	</tr>
	<tr>
		<td>�K�夺�e:</td>
		<td><input type="TEXT" name="postContent" size="50"
			 value="<%=mppVO.getPostContent()%>" /></td>
	</tr>
	<tr>
		<td>�o���ɶ�:</td>
		
		<td><%=mppVO.getPostTime()%></td>
	</tr>
	<tr>
		<td>���g��:</td>
		<td><%=mppVO.getNumOfLike()%></td>
	</tr>
	<tr>
		<td>�K�媬�A:</td>
		<td><input type="TEXT" name="postState" size="50"
			 value="<%=mppVO.getPostState()%>" /></td>
	</tr>
	



</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="postNo" value="<%=mppVO.getPostNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>