<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.posttype.model.*"%>


<%
	PostTypeVO postTypeVO = (PostTypeVO) request.getAttribute("postTypeVO");//PostTypeServlet.java (Concroller) �s�Jreq��postTypeVO���� (�]�A�������X��postTypeVO, �]�]�A��J��ƿ��~�ɪ�postTypeVO����)
%>

<%= postTypeVO==null %>--${postTypeVO.postTypeNo}--
<html>
<head>
<title>��@�峹���O�˵� - listOnePostType.jsp</title>
<%@ include file="/back_end/header.jsp"%>
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
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>��@�峹���O�˵� - listOnePostType.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
		<th>�峹���O�s��</th>
		<th>�峹���O</th>
	</tr>
	<tr>
		<td>${postTypeVO.postTypeNo}</td>
		<td>${postTypeVO.postType}</td>
	</tr>
</table>
  <%@ include file="/back_end/footer.jsp"%> 
</body>
</html>