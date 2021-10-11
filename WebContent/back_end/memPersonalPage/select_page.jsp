<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<head>
<!-- Google Fonts -->

<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Beloved MemberPersonalPage: Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/memPersonalPage/css/MemPerPage.css">
<%@ include file="/back_end/header.jsp"%>
</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>

<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">		    		    
		    <li class="breadcrumb-item active" aria-current="page">個人貼文管理首頁</li>
<%-- 		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/memPersonalPage/listAllMemPerPage.jsp">所有個人貼文檢視</a></li> --%>
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
	    <h5 class="card-title"><i class="fas fa-search"></i> 貼文查詢</h5>
	    <div>	    	
			<ul>
			  <a href='<%=request.getContextPath()%>/back_end/memPersonalPage/listAllMemPerPage.jsp'>
			  <button type="button" class="btn btn-outline-info">列出所有個人貼文</button></a>		  			  
<!-- 			  <li> -->
<%-- 			    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" > --%>
<!-- 			        <b>輸入貼文編號 (如01):</b> -->
<!-- 			        <input type="text" name="postNo"> -->
<!-- 			        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			        <input type="submit" value="送出"> -->
<!-- 			    </FORM> -->
<!-- 			  </li>			 -->
<%-- 			  <jsp:useBean id="mppSvc" scope="page" class="com.mempersonalpage.model.MemPersonalPageService" />			    --%>
<!-- 			  <li> -->
<%-- 			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" > --%>
<!-- 			       <b>選擇貼文編號 :</b> -->
<!-- 			       <select size="1" name="postNo"> -->
<%-- 			         <c:forEach var="mppVO" items="${mppSvc.all}" >  --%>
<%-- 			          <option value="${mppVO.postNo}">${mppVO.postNo} --%>
<%-- 			         </c:forEach>    --%>
<!-- 			       </select> -->
<!-- 			       <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			       <input type="submit" value="送出"> -->
<!-- 			    </FORM> -->
<!-- 			  </li>			  			   -->
			</ul>			
<!-- 			<h3>貼文管理</h3>			 -->
<!-- 			<ul> -->
<%-- 			  <li><a href='<%=request.getContextPath()%>/back_end/memPersonalPage/addMemPerPage.jsp'>Add</a> a new MemberPersonalPage</li> --%>
<!-- 			</ul> -->
	    </div>	   
	  </div>
	</div>


  <%@ include file="/back_end/footer.jsp"%>   
 
</body>
</html>