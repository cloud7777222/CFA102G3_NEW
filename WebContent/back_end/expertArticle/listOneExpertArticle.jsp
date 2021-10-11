<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertarticle.model.*"%>

<%
	ExpertArticleVO eaVO = (ExpertArticleVO) request.getAttribute("eaVO");//ExpertAticleServlet.java (Concroller) �s�Jreq��eaVO���� (�]�A�������X��eaVO, �]�]�A��J��ƿ��~�ɪ�eaVO����)
%>


<%= eaVO==null %>--${eaVO.articleNo}---- ${param.articleNo}--
<html>
<head>
<title>�M�a�M�� - listOneExpertArticle.jsp</title>

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
  img{
  	max-width: 100%
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>�M�a�M�� - listOneExpertArticle.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/expertArticle/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
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
		<th>�M�a�M��s��</th>
		<th>�M�a�s��</th>	
		<th>�M�a�M�椺�e</th>
		<th>�M�a�M��Ϥ�</th>
		<th>�M�a�M��o��ɶ�</th>		
		<th>�M�a�M��o���A</th>
		<th>���g��</th>
	</tr>
	<tr>
		<td>${eaVO.articleNo}</td>
		<td>${eaVO.expertNo}</td>
		<td>${eaVO.articleContent}</td>
		<td><img src="<%=request.getContextPath()%>/expertArticle/ExpertArticleServlet?articleNo=${eaVO.articleNo}"></td>	
		<td>${eaVO.articleTime}</td>	
		<td>${eaVO.articleState}</td>
		<td>${eaVO.numOfLike}</td>
	</tr>
</table>

</body>
</html>