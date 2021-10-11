<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postmessage.model.*"%>

<%
	PostMessageVO postMessageVO = (PostMessageVO) request.getAttribute("postMessageVO");//PostMessageServlet.java (Concroller) 存入req的postMessageVO物件 (包括幫忙取出的postMessageVO, 也包括輸入資料錯誤時的postMessageVO物件)
%>

<%-- <%=postMessageVO == null%>--${postMessageVO.mesNo}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>論壇文章留言修改 - update_PostMessage_input.jsp</title>
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
		    <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back_end/postMessage/select_page.jsp">文章留言管理首頁</a></li>
		    <li class="breadcrumb-item active" aria-current="page">文章留言修改</li>
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
	    <h5 class="card-title">文章留言修改</h5>
	   		<div>
	   			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" name="form1">
					<table class="table">
						<tr>
							<td>文章留言編號  :</td>
							<td><%=postMessageVO.getMesNo()%></td>
						</tr>
						<tr>
							<td>會員編號  :</td>
							<td><%=postMessageVO.getMemberNo()%></td>
						</tr>
						<tr>
							<td>文章編號  :</td>
							<td><%=postMessageVO.getPostNo()%></td>
						</tr>						
						<tr>
							<td>文章留言內容:</td>
							<td><textarea class="form-control" name="mesContent" id="message"
							placeholder="Message" required="required"
							data-validation-required-message="Please enter your message"
							aria-invalid="false" rows="10"  cols="40"><%=(postMessageVO == null) ? "" : postMessageVO.getMesContent()%></textarea></td>
							
						</tr>
						<tr>
							<td>留言時間:</td>
							<td><%=postMessageVO.getMesTime()%></td>
						</tr>			
						<tr>
							<td>文章留言狀態:</td>
							<td>
							<input type="radio" name="mesState" id="hidden" value="0"><label for="hidden">隱藏</label> 
							<input type="radio" name="mesState" id="show" value="1" checked><label for="show">顯示</label></td>
						</tr>			
			
			
					</table>
					<br> <input type="hidden" name="action" value="update"> 
						 <input type="hidden" name="mesNo" value="<%=postMessageVO.getMesNo()%>">
						 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
						 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllMemPerPage.jsp-->		  
						 <button type="submit" class="btn btn-primary">送出修改</button> 
				</FORM>
	   		</div>
	  </div>
	</div>
	

<%@ include file="/back_end/footer.jsp"%> 	
</body>

</html>