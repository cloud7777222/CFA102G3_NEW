<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.posttype.model.*"%>

<%
	PostTypeVO postTypeVO = (PostTypeVO) request.getAttribute("postTypeVO");//PostTypeServlet.java (Concroller) �s�Jreq��postTypeVO���� (�]�A�������X��postTypeVO, �]�]�A��J��ƿ��~�ɪ�postTypeVO����)
%>

<%-- <%=postTypeVO == null%>--${postTypeVO.postTypeNo}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>�׾¤峹���O�ק� - update_PostType_input.jsp</title>
<%@ include file="/back_end/header.jsp"%>
<style>
	body{
		width: 100%; 
		height: 100%;
		font-family: 'Noto Sans TC', sans-serif !important;
		font-size: 15px !important;
		
	}
	div.w-50{
		margin-top: 10px;
		margin-left: 50px;
		box-sizing: border-box;
		box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.3);			
	}
	table.table{
		width: 100%; 
		height: auto;
		font-size: 14px !important;
		cellpadding: 10px !important;				
		margin-top: 30px;
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
<div class="container">
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp">�峹���O�޲z����</a></li>
	    <li class="breadcrumb-item active" aria-current="page">�峹���O�ק� </li>
	  </ol>
	</nav>	

	<%-- ���~��C --%>
	<div class="errorMsgs">
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	</div>
	
	
	<div class="card w-50">
	  <div class="card-body">
	    <h6 class="card-title"><i class="far fa-edit"></i> �峹���O�ק�</h6>
	   	<div>
	   		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postType/PostTypeServlet" name="form1">
			<table class="table">
				<tr>
					<td>�峹���O�s��  :<font color=red><b> *</b></font></td>
					<td><%=postTypeVO.getPostTypeNo()%></td>
				</tr>
				<tr>
					<td>�峹���O�W��  :<font color=red><b> *</b></font></td>
					<td><input type="TEXT" name="postType" size="30"
						value="<%=postTypeVO.getPostType()%>" /></td>	
			</table>		
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="postTypeNo" value="<%=postTypeVO.getPostTypeNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllMemPerPage.jsp-->		  
			 <button type="submit" class="btn btn-primary">�ק�</button> 
	</FORM>
	   	</div>
	  </div>
	</div>
	

</div>	
  <%@ include file="/back_end/footer.jsp"%> 
</body>

</html>