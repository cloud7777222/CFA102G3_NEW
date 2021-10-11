<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<head>
 <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Beloved PostMessage (�峹�d��) : Home</title>

<style>
	body{
		width:70%; 
		height:70%;
		font-family: 'Noto Sans TC', sans-serif;
		font-size: 15px !important;
	}
	div.w-50{
		margin-top: 10px;
		margin-left: 250px;
		margin-left: 250px;
		box-sizing: border-box;
		box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.3);			
	}
	button{
		font-size: 14px !important;
	}
	nav{
		margin-top: 100px;
		margin-left: 250px;
		ont-size: 18px !important;	
	}				
  
</style>

</head>
<body bgcolor='white'>


	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	   	<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp">�峹���O�޲z����</a></li>
	  	<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/post/select_page.jsp">�峹�޲z����</a></li>
	  	<li class="breadcrumb-item active" aria-current="page">�峹�d���޲z����</li>	    
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


	<div class="card w-50">
	  <div class="card-body">
	    <h5 class="card-title"><i class="fas fa-search"></i> �峹�d���d��</h5>
	    <div>
	    	<ul>
			  <a href='<%=request.getContextPath()%>/back_end/postMessage/listAllPostMessage.jsp'>
			  <button type="button" class="btn btn-outline-info">�C�X�Ҧ��d�� </button></a>
			  <p>
			  			  
<!-- 			  <li> -->
<%-- 			    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" > --%>
<!-- 			        <b>��J�峹�d���s�� :</b> -->
<!-- 			        <input type="text" name="mesNo"> -->
<!-- 			        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			        <input type="submit" value="�e�X"> -->
<!-- 			    </FORM> -->
<!-- 			  </li> -->
			
<%-- 			  <jsp:useBean id="postMessageSvc" scope="page" class="com.postmessage.model.PostMessageService" /> --%>
			   
<!-- 			  <div> -->
<%-- 			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" > --%>
<!-- 			     <i class="far fa-comment-dots"></i> -->
<!-- 			       <font color=orange> ��ܤ峹�d���s��:</font> -->
<!-- 			       <select size="1" name="mesNo"> -->
<%-- 			         <c:forEach var="postMessageVO" items="${postMessageSvc.all}" >  --%>
<%-- 			          <option value="${postMessageVO.mesNo}">${postMessageVO.mesNo} --%>
<%-- 			         </c:forEach>    --%>
<!-- 			       </select> -->
<!-- 			       <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			       <button type="submit" class="btn btn-outline-info"> �e�X  </button> 	 -->
<!-- 			    </FORM> -->
<!-- 			  </div>			  			   -->
			</ul>
						
<!-- 			<h3>�峹�޲z</h3>			 -->
<!-- 			<ul> -->
<%-- 			  <li><a href='<%=request.getContextPath()%>/back_end/postMessage/addPostMessage.jsp'>Add</a> a new PostMessage</li> --%>
<!-- 			</ul> -->
				    	
	    </div>
	  </div>
	</div>



</body>
</html>