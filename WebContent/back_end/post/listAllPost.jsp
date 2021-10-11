<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="java.util.*"%>



<%
	PostService postSvc = new PostService();
    List<PostVO> list = postSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<jsp:useBean id="postTypeSvc" scope="page" class="com.posttype.model.PostTypeService" />


<%-- <%= postSvc==null %>---- ${param.postNo}--    --%>

 
<html>
<head>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>�Ҧ��׾¤峹 - listAllPost.jsp</title>
<%@ include file="/back_end/header.jsp"%>
<style>
	body{
		width: 100%; 
		height: 100%;
		font-family: 'Noto Sans TC', sans-serif;
		font-size: 14px !important;
		
	}
	table.table-hover{
		width: 80%; 
		height: auto;
		font-size: 14px !important;
		cellpadding: 5px !important;
		border: 2px;
		bordercolor: black;
	}
	button{
		font-size: 12px !important;
	}
	nav{
		font-size: 14px !important;
	} 
</style>

</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>
<div class="container">

		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/post/select_page.jsp">�峹�޲z����</a></li>
		    <li class="breadcrumb-item active" aria-current="page">�Ҧ��׾¤峹</li>
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
		<td>�峹�s��</td>
		<td>�峹���O�s��</td>
		<td>�|���s��</td>		
		<td>�峹���e</td>
		<td>�o��ɶ�</td>		
		<td>�峹���A</td>
		<td>�峹�d����</td>
		<td>���g��</td>
		<td>�ק�</td>
		<td>�R��</td>
	</tr>
	</thead>
	 <tbody>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="postVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(postVO.postNo==param.postNo) ? 'bgcolor=#ffd500':''}><!--�N�ק諸���@���[�J����Ӥw-->
		
			<td>${postVO.postNo}</td>
			<td>(${postVO.postTypeNo})  ${postTypeSvc.getOnePostType(postVO.postTypeNo).postType}</td>
			<td>${postVO.memberNo}</td>		
			<td>${postVO.postContent}</td>
			<td>${postVO.postTime}</td>
			<td>${postVO.postState}</td>
			<td>${postVO.mesCount}</td>
			<td>${postVO.numOfLike}</td>
			
			
			<td>						
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" style="margin-bottom: 0px;">
			     <button type="submit" class="btn btn-primary">�ק�</button> 
			     <input type="hidden" name="postNo"  value="${postVO.postNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->			     
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" style="margin-bottom: 0px;">
			     <button type="submit" class="btn btn-primary">�R��</button>
			     <input type="hidden" name="postNo"  value="${postVO.postNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->			     
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="pages/page2.file" %>
</div>
<!-- <br>�����������|:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>
 <%@ include file="/back_end/footer.jsp"%> 
</body>
</html>