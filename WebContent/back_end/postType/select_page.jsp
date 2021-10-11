<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<head>
 <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Beloved PostType: Home</title>
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
	}
	button{
		font-size: 14px !important;
	}
	nav{
		margin-top: 100px;
		margin-left: 250px;
		ont-size: 18px !important;	
	}
	.breadcrumb{
		width:70%;
	}				
</style>

</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>

	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item active" aria-current="page">�峹���O�޲z����</li>
	  	<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/post/select_page.jsp">�峹�޲z����</a></li>
	  	<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postMessage/select_page.jsp">�峹�d���޲z����</a></li>		    
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
			<h6 class="card-title"><i class="fas fa-search"></i> �峹���O�d��</h6>
			<div>
				<ul><a href='<%=request.getContextPath()%>/back_end/postType/listAllPostType.jsp'>
				<button type="button" class="btn btn-outline-info">�C�X�Ҧ����O </button></a>
				<p>				
<!-- 					<li> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/postType/PostTypeServlet"> --%>
<!-- 							<b>��J�峹���O�s�� (�p 01):</b> <input type="text" name="postTypeNo"> -->
<!-- 							<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 							<input type="submit" value="�e�X"> -->
<!-- 						</FORM> -->
<!-- 					</li> -->

					<jsp:useBean id="postTypeSvc" scope="page"
						class="com.posttype.model.PostTypeService" />

<!-- 					<li> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/postType/PostTypeServlet"> --%>
<!-- 							<b>(²����)��ܤ峹���O :</b> <select size="1" name="postTypeNo"> -->
<%-- 								<c:forEach var="postTypeVO" items="${postTypeSvc.all}"> --%>
<%-- 									<option value="${postTypeVO.postTypeNo}">${postTypeVO.postTypeNo} --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 							<input type="submit" value="�e�X"> -->
<!-- 						</FORM> -->
<!-- 					</li> -->

					
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postType/PostTypeServlet">
						<i class="fas fa-tasks"></i>							
							<font color=orange> (�ԲӪ�) �d�ݳ�@���O�Ҧ��峹   : </font><select size="1" name="postTypeNo">								
								<c:forEach var="postTypeVO" items="${postTypeSvc.all}">
									<option value="${postTypeVO.postTypeNo}">${postTypeVO.postType}
								</c:forEach>
							</select> 
							<input type="hidden" name="action" value="listPostsBy_PostTypeNo_A">
							<button type="submit" class="btn btn-outline-info"> �e�X  </button> 																					
						</FORM>					
				</ul>
				<p>
				<h6><i class="far fa-plus-square"></i> �峹���O�޲z</h6>
				<ul>
				<a href='<%=request.getContextPath()%>/back_end/postType/addPostType.jsp'>
				<button type="button" class="btn btn-outline-info"> �s�W�峹���O </button></a>
				</ul>
			</div>
		</div>
	</div>

  <%@ include file="/back_end/footer.jsp"%> 
</body>
</html>