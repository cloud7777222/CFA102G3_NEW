<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>


<%
	MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) �s�Jreq��mppVO���� (�]�A�������X��mppVO, �]�]�A��J��ƿ��~�ɪ�mppVO����)
%>
<%= mppVO==null %>--${mppVO.postNo}--
<html>
<head>
<title>�ӤH�����K�� - listOneMemPerPage.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>�ӤH�����K�� - listOneMemPerPage.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�K��s��</th>
		<th>�|���s��</th>
		<th>�K��Ϥ�</th>
		<th>�K�夺�e</th>
		<th>�o���ɶ�</th>
		<th>���g��</th>
		<th>�K�媬�A</th>
	</tr>
	<tr>
		<td>${mppVO.postNo}</td>
		<td>${mppVO.memberNo}</td>
		<td><img src="${mppVO.postPhoto}"></td>
		<td>${mppVO.postContent}</td>
		<td>${mppVO.postTime}</td>
		<td>${mppVO.numOfLike}</td>
		<td>${mppVO.postState}</td>
	</tr>
</table>

</body>
</html>