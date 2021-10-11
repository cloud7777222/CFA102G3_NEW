<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postmessage.model.*"%>

<%
	PostMessageVO postMessageVO = (PostMessageVO) request.getAttribute("postMessageVO");//PostMessageServlet.java (Concroller) �s�Jreq��postMessageVO���� (�]�A�������X��postMessageVO, �]�]�A��J��ƿ��~�ɪ�postMessageVO����)
%>

<%-- <%=postMessageVO == null%>--${postMessageVO.mesNo}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>�׾¤峹�d���ק� - update_PostMessage_input.jsp</title>
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
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postMessage/select_page.jsp">�峹�d���޲z����</a></li>
		    <li class="breadcrumb-item active" aria-current="page">�峹�d���ק�</li>
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
	    <h5 class="card-title">�峹�d���ק�</h5>
	   		<div>
	   			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" name="form1">
					<table class="table">
						<tr>
							<td>�峹�d���s��  :</td>
							<td><%=postMessageVO.getMesNo()%></td>
						</tr>
						<tr>
							<td>�|���s��  :</td>
							<td><%=postMessageVO.getMemberNo()%></td>
						</tr>
						<tr>
							<td>�峹�s��  :</td>
							<td><%=postMessageVO.getPostNo()%></td>
						</tr>						
						<tr>
							<td>�峹�d�����e:</td>
							<td><textarea class="form-control" name="mesContent" id="message"
							placeholder="Message" required="required"
							data-validation-required-message="Please enter your message"
							aria-invalid="false" rows="10"  cols="40"><%=(postMessageVO == null) ? "" : postMessageVO.getMesContent()%></textarea></td>
							
						</tr>
						<tr>
							<td>�d���ɶ�:</td>
							<td><%=postMessageVO.getMesTime()%></td>
						</tr>			
						<tr>
							<td>�峹�d�����A:</td>
							<td>
							<input type="radio" name="mesState" id="hidden" value="0"><label for="hidden">����</label> 
							<input type="radio" name="mesState" id="show" value="1" checked><label for="show">���</label></td>
						</tr>			
			
			
					</table>
					<br> <input type="hidden" name="action" value="update"> 
						 <input type="hidden" name="mesNo" value="<%=postMessageVO.getMesNo()%>">
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