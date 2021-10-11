<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.posttype.model.*"%>


<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");//PostServlet.java (Concroller) �s�Jreq��postVO���� (�]�A�������X��postVO, �]�]�A��J��ƿ��~�ɪ�postVO����)
%>

<%-- �ޤJPostTypeService, �����X������postType--%>
<jsp:useBean id="postTypeSvc" scope="page" class="com.posttype.model.PostTypeService" />

<%= postVO==null %>--${postVO.postNo}--${postTypeVO.postType}
<html>
<head>
<title>��@�峹�˵� - listOnePost.jsp</title>

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
		 <h3>��@�峹�˵� - listOnePost.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/post/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
		<th>�峹�s��</th>
		<th>�峹���O�s��</th>
		<th>�|���s��</th>		
		<th>�峹���e</th>
		<th>�o��ɶ�</th>		
		<th>�峹���A</th>
		<th>�峹�d����</th>
		<th>���g��</th>
	</tr>
	<tr>
		<td>${postVO.postNo}</td>
		<td>(${postVO.postTypeNo}) ${postTypeSvc.getOnePostType(postVO.postTypeNo).postType} </td>
		<td>${postVO.memberNo}</td>		
		<td>${postVO.postContent}</td>
		<td>${postVO.postTime}</td>
		<td>${postVO.postState}</td>
		<td>${postVO.mesCount}</td>
		<td>${postVO.numOfLike}</td>
		
	</tr>
</table>

</body>
</html>