<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<head>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Beloved Post: Home</title>
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
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp">文章類別管理首頁</a></li>
		    <li class="breadcrumb-item active" aria-current="page">文章管理首頁</li>
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postMessage/select_page.jsp">文章留言管理首頁</a></li>
		  </ol>
		</nav>
	
<%-- 錯誤表列 --%>
	<div class="errorMsgs">
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
			    <c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>

	<div class="card w-50">
	  <div class="card-body">
	    <h6 class="card-title"><i class="fas fa-search"></i> 文章查詢</h6>
		<div>			
			<ul>
			  <a href='<%=request.getContextPath()%>/back_end/post/listAllPost.jsp'>
				<button type="button" class="btn btn-outline-info">列出所有文章 </button></a>
				<p></p>			  			  
<!-- 			  <li> -->
<%-- 			    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" > --%>
<!-- 			        <b>輸入文章編號 (如01):</b> -->
<!-- 			        <input type="text" name="postNo"> -->
<!-- 			        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			        <input type="submit" value="送出"> -->
<!-- 			    </FORM> -->
<!-- 			  </li> -->
			
<%-- 			  <jsp:useBean id="postSvc" scope="page" class="com.post.model.PostService" />  --%>
<!-- 			  <li> -->
<%-- 			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" > --%>
<!-- 			       <b>選擇文章編號 :</b> -->
<!-- 			       <select size="1" name="postNo"> -->
<%-- 			         <c:forEach var="postVO" items="${postSvc.all}" >  --%>
<%-- 			          <option value="${postVO.postNo}">${postVO.postNo} --%>
<%-- 			         </c:forEach>    --%>
<!-- 			       </select>      -->
<!-- 			       <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			       <input type="submit" value="送出">       -->
<!-- 			    </FORM> -->
<!-- 			  </li> -->
			  
			  <jsp:useBean id="postSvc" scope="page" class="com.post.model.PostService" />
			   <div>
			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/PostServlet" >
			      <i class="far fa-comment-dots"></i>
			       <font color=orange>(查詢文章看其所有留言) 選擇文章編號:</font>
			       <select size="1" name="postNo">
			         <c:forEach var="postVO" items="${postSvc.all}" > 
			          <option value="${postVO.postNo}">${postVO.postNo}
			         </c:forEach>   
			       </select>       
			       <input type="hidden" name="action" value="listMessagesBy_PostNo">
			       <button type="submit" class="btn btn-outline-info"> 送出  </button> 
			     </FORM>
			  <div>
			  
			  			  
<%-- 			  <jsp:useBean id="postTypeSvc" scope="page" class="com.posttype.model.PostTypeService" />  --%>
<!-- 			    <li> -->
<%-- 			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postType/PostTypeServlet" >       --%>
<!-- 			        <b>選擇文章類別 :</b> -->
<!-- 			       <select size="1" name="postTypeNo"> -->
<%-- 			         <c:forEach var="postTypeVO" items="${postTypeSvc.all}" >  --%>
<%-- 			          <option value="${postTypeVO.postTypeNo}">${postTypeVO.postType} --%>
<%-- 			         </c:forEach>    --%>
<!-- 			       </select>			        -->
<!-- 			       <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			       <input type="submit" value="送出">			           -->
<!-- 			    </FORM> -->
<!-- 			  </li>			 			   -->
			</ul>
			
			
<!-- 			<h6><i class="far fa-plus-square"></i> 文章管理</h6>			 -->
<!-- 			<ul> -->
<%-- 			  <li><a href='<%=request.getContextPath()%>/back_end/post/addPost.jsp'>Add</a> a new Post</li> --%>
<!-- 			</ul> -->
		</div>
	  </div>
	</div>

  <%@ include file="/back_end/footer.jsp"%> 
</body>
</html>