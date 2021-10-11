<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>

<%
	MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) 存入req的mppVO物件 (包括幫忙取出的mppVO, 也包括輸入資料錯誤時的mppVO物件)
%>

<%-- <%=mppVO == null%>--${mppVO.postNo}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>會員個人頁面貼文修改 - update_MemPerPage_input.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/memPersonalPage/css/update_MemPerPage_input.css">
<%@ include file="/back_end/header.jsp"%>
</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>
        <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">		    		    
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/memPersonalPage/select_page.jsp">個人貼文管理首頁</a></li>		    		    
		    <li class="breadcrumb-item active" aria-current="page">個人貼文修改 </li>
		  </ol>
		</nav>
	


	<%-- 錯誤表列 --%>
	<div class="errorMsgs">
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	</div>
	
	
	<div class="card w-50">
	  <div class="card-body">
	    <h5 class="card-title"><i class="far fa-edit"></i> 個人貼文修改</h5>
	  	<div>
	  		<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet"
				name="form1" enctype="multipart/form-data">
				<table class="table">
					<tr>
						<td>貼文編號  :</td>
						<td><%=mppVO.getPostNo()%></td>
					</tr>
					<tr>
						<td>會員編號  :</td>
						<td><%=mppVO.getMemberNo()%></td>
					</tr>
					<tr>
						<td>貼文圖片  :</td>						
						<td><img src="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet?postNo=${mppVO.postNo}"/><input type="file" name="postPhoto" size="45"
							value="<%=(mppVO == null) ? "" : mppVO.getPostPhoto()%>" /></td>
		
					</tr>
					<tr>
						<td>貼文內容  :</td>
						<td><input type="TEXT" name="postContent" size="50"
							value="<%=mppVO.getPostContent()%>" /></td>
					</tr>
					<tr>
						<td>發表時間  :</td>
		
						<td><%=mppVO.getPostTime()%></td>
					</tr>
					<tr>
						<td>按讚數  :</td>
						<td><%=mppVO.getNumOfLike()%></td>											
					</tr>
					<tr>
						<td>貼文狀態  :</td>
						<td><input type="radio" name="postState" id="hidden" value="0"><label
							for="hidden">隱藏</label> <input type="radio" name="postState"
							id="show" value="1" checked><label for="show">顯示</label></td>
					</tr>
		
		
				</table>
				<br> <input type="hidden" name="action" value="update"> 
					 <input type="hidden" name="postNo" value="<%=mppVO.getPostNo()%>">
					 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
					 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllMemPerPage.jsp-->			  
					<button type="submit" class="btn btn-primary">修改</button> 
			</FORM>
	  	</div>
	  </div>
	</div>
	

<%@ include file="/back_end/footer.jsp"%> 
</body>

</html>