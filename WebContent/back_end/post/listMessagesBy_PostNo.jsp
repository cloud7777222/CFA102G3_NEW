<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.postmessage.model.*"%>


<%-- <jsp:useBean id="listMessagesBy_PostNo" scope="request" type="java.util.Set<PostMessageVO>" /> <!-- ��EL����i�ٲ� --> --%>
<jsp:useBean id="postSvc" scope="page" class="com.post.model.PostService" />


<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<head>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>�峹�d�� - listMessagesBy_PostNo.jsp</title>
<%@ include file="/back_end/header.jsp"%>
<style>	
	body{
		width: 100%; 
		height: 100%;
		font-family: 'Noto Sans TC', sans-serif !important;
		font-size: 15px !important;
	
	
	}
	table.table-hover{
		width: 80%; 
		height: auto;
		font-size: 13px !important;
		cellpadding: 5px !important;
		border: 2px;
		bordercolor: black;
		margin-bottom: 100px;
	}
	button{
		font-size: 12px !important;
	}
	div.errorMsgs{
		margin-left: 250px;
		margin-top: 10px;
		font-size: 14px !important;	
	}	
</style>

</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/post/select_page.jsp">�峹�޲z����</a></li>
	    <li class="breadcrumb-item active" aria-current="page">��@�峹�Ҧ��d��</li>
	  </ol>
	</nav>


<%-- ���~��C --%>
<div class="errorMsgs">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>

	<table class="table table-hover">
		<thead>
		<tr>
			<td>�d���s��</td>
			<td>�|���s��</td>
			<td>�峹�s��</td>		
			<td>�d�����e</td>
			<td>�o��ɶ�</td>		
			<td>�d�����A</td>
			<td>�ק�</td>
		</tr>
		</thead>
		<tbody>
	
	<c:forEach var="postMessageVO" items="${listMessagesBy_PostNo}">
		<tr ${(postMessageVO.mesNo==param.mesNo) ? 'bgcolor=#ffd500':''}><!--�N�ק諸���@���[�J����Ӥw-->
		
			<td>${postMessageVO.mesNo}</td>
			<td>${postMessageVO.memberNo}</td>  
			<td>${postMessageVO.postNo}</td>		
			<td>${postMessageVO.mesContent}</td>
			<td>${postMessageVO.mesTime}</td>
			<td>${postMessageVO.mesState}</td>						
			<td>						
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" style="margin-bottom: 0px;">
			     <button type="submit" class="btn btn-primary">�ק�</button>
			     <input type="hidden" name="mesNo"  value="${postMessageVO.mesNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->			              		     
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="mesNo"  value="${postMessageVO.mesNo}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->			             		      --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
	</tbody>
</table>


<!-- <br>�����������|:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>

  <%@ include file="/back_end/footer.jsp"%> 
</body>
</html>