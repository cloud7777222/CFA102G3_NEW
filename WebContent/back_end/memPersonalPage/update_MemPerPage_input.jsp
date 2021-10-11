<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>

<%
	MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) �s�Jreq��mppVO���� (�]�A�������X��mppVO, �]�]�A��J��ƿ��~�ɪ�mppVO����)
%>

<%-- <%=mppVO == null%>--${mppVO.postNo}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>�|���ӤH�����K��ק� - update_MemPerPage_input.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/memPersonalPage/css/update_MemPerPage_input.css">
<%@ include file="/back_end/header.jsp"%>
</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>
        <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">		    		    
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/memPersonalPage/select_page.jsp">�ӤH�K��޲z����</a></li>		    		    
		    <li class="breadcrumb-item active" aria-current="page">�ӤH�K��ק� </li>
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
	    <h5 class="card-title"><i class="far fa-edit"></i> �ӤH�K��ק�</h5>
	  	<div>
	  		<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet"
				name="form1" enctype="multipart/form-data">
				<table class="table">
					<tr>
						<td>�K��s��  :</td>
						<td><%=mppVO.getPostNo()%></td>
					</tr>
					<tr>
						<td>�|���s��  :</td>
						<td><%=mppVO.getMemberNo()%></td>
					</tr>
					<tr>
						<td>�K��Ϥ�  :</td>						
						<td><img src="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet?postNo=${mppVO.postNo}"/><input type="file" name="postPhoto" size="45"
							value="<%=(mppVO == null) ? "" : mppVO.getPostPhoto()%>" /></td>
		
					</tr>
					<tr>
						<td>�K�夺�e  :</td>
						<td><input type="TEXT" name="postContent" size="50"
							value="<%=mppVO.getPostContent()%>" /></td>
					</tr>
					<tr>
						<td>�o��ɶ�  :</td>
		
						<td><%=mppVO.getPostTime()%></td>
					</tr>
					<tr>
						<td>���g��  :</td>
						<td><%=mppVO.getNumOfLike()%></td>											
					</tr>
					<tr>
						<td>�K�媬�A  :</td>
						<td><input type="radio" name="postState" id="hidden" value="0"><label
							for="hidden">����</label> <input type="radio" name="postState"
							id="show" value="1" checked><label for="show">���</label></td>
					</tr>
		
		
				</table>
				<br> <input type="hidden" name="action" value="update"> 
					 <input type="hidden" name="postNo" value="<%=mppVO.getPostNo()%>">
					 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
					 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:listAllMemPerPage.jsp-->			  
					<button type="submit" class="btn btn-primary">�ק�</button> 
			</FORM>
	  	</div>
	  </div>
	</div>
	

<%@ include file="/back_end/footer.jsp"%> 
</body>

</html>