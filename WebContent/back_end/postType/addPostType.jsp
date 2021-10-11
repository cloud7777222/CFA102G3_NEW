<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.posttype.model.*"%>

<%
	PostTypeVO postTypeVO = (PostTypeVO) request.getAttribute("postTypeVO");//PostTypeServlet.java (Concroller) �s�Jreq��postTypeVO���� (�]�A�������X��postTypeVO, �]�]�A��J��ƿ��~�ɪ�postTypeVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>�׾¤峹�s�W</title>
<%@ include file="/back_end/header.jsp"%>

<style>
	body{
		width:100%; 
		height:100%;
		font-family: 'Noto Sans TC', sans-serif;
		font-size: 15px !important;
	}
	div.w-50{
		margin-top: 10px;
		margin-left: 250px;
		margin-left: 250px;
		box-sizing: border-box;
		box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.3);
		font-size: 14px !important;				
	}
	button{
		font-size: 14px !important;
	}
	nav{
		margin-top: 100px;
		margin-left: 250px;
		font-size: 16px !important;	
	}
	div.errorMsgs{
		margin-left: 250px;
		margin-top: 10px;
		font-size: 14px !important;	
	}
	.breadcrumb{
		width:70%;
	}				
</style>
</head>

<%@ include file="/back_end/sliderbar.jsp"%>
<body>
<div class="container">


	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp">�峹���O�޲z����</a></li>
	    <li class="breadcrumb-item active" aria-current="page">�s�W�峹���O</li>
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
	    <h6 class="card-title"><i class="far fa-plus-square"></i>  �s�W�峹���O</h6>
	    <div>
	    	<FORM action="<%=request.getContextPath()%>/postType/PostTypeServlet" method="post">	
			<table class="table">				
				<tr>
					<td>  �峹���O�W��  :</td>
					<td><input type="TEXT" name="postType" size="30" /></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="insert"> 
			<button type="submit" class="btn btn-primary">�W��</button>
			</FORM>
	    </div>
	  </div>
	</div>
</div>
  <%@ include file="/back_end/footer.jsp"%> 
</body>

</html>


