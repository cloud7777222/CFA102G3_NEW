<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>

<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");//PostServlet.java (Concroller) �s�Jreq��postVO���� (�]�A�������X��postVO, �]�]�A��J��ƿ��~�ɪ�postVO����)
%>

<%-- <%=postVO == null%>--${postVO.postNo}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>�׾¤峹�ק� - update_Post_input.jsp</title>
<%@ include file="/back_end/header.jsp"%>

<style>
	body{
		width: 100%; 
		height: 100%;
		font-family: 'Noto Sans TC', sans-serif !important;
		font-size: 14px !important;
		
	}
	div.w-50{
		margin-top: 10px;
		margin-left: 50px;
		box-sizing: border-box;
		box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.3);
		font-size: 14px !important;				
	}
	table.table{
		width: 80%; 
		height: auto;
		font-size: 14px !important;
		cellpadding: 10px !important;				
		margin-top: 30px;
	}
	button{
		font-size: 12px !important;
	}
	div.errorMsgs{
		margin-top: 10px;
		font-size: 14px !important;	
	}			
</style>

</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp">�峹���O�޲z����</a></li>
	    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/post/select_page.jsp">�峹�޲z����</a></li>
	    <li class="breadcrumb-item active" aria-current="page">�峹�ק� </li>
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
	    <h6 class="card-title"><i class="far fa-edit"></i> �峹�ק�</h6>
	   <div>
	   		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" name="form1">
			<table class="table">
				<tr>
					<td>�峹�s��  :<font color=red><b>*</b></font></td>
					<td><%=postVO.getPostNo()%></td>
				</tr>
				<tr>
					<td>�峹���O�s��  :<font color=red><b>*</b></font></td>
					<td><input type="TEXT" name="postTypeNo" size="50"
						value="<%=postVO.getPostTypeNo()%>" /></td>
				</tr>
				<tr>
					<td>�|���s��  :<font color=red><b>*</b></font></td>
					<td><%=postVO.getMemberNo()%></td>
				</tr>						
				<tr>
					<td>�峹���e  :<font color=red><b>*</b></font></td>
					<td><textarea class="form-control" name="postContent" id="message"
							placeholder="Message" required="required"
							data-validation-required-message="Please enter your message"
							aria-invalid="false" rows="10"  cols="40"><%=(postVO == null) ? "" : postVO.getPostContent()%></textarea></td>
				</tr>					
				<tr>
					<td>�o��ɶ�:</td>
					<td><%=postVO.getPostTime()%></td>
				</tr>
				<tr>
					<td>�峹�d����:</td>
					<td><%=postVO.getMesCount()%></td>
				</tr>			
				<tr>
					<td>�峹���A:</td>
					<td>
					<input type="radio" name="postState" id="hidden" value="0"><label for="hidden">����</label> 
					<input type="radio" name="postState" id="show" value="1" checked><label for="show">���</label></td>
				</tr>			
				<tr>
					<td>���g��:</td>
					<td><%=postVO.getNumOfLike()%></td>
				</tr>
			</table>
			<br> <input type="hidden" name="action" value="update">		
				 <input type="hidden" name="postNo" value="<%=postVO.getPostNo()%>">
				 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
				 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllMemPerPage.jsp-->		  
				 <button type="submit" class="btn btn-primary">�e�X�ק�</button> 
		</FORM>
	   </div>
	  </div>
	</div>
	

<%@ include file="/back_end/footer.jsp"%> 
</body>

</html>