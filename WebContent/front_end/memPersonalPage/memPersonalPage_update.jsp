<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>

<%
	MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) 存入req的mppVO物件 (包括幫忙取出的mppVO, 也包括輸入資料錯誤時的mppVO物件)
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<title>會員個人頁面貼文修改 - update_MemPerPage_input.jsp</title>

<style type="text/css">
	/* 顯圖特效開始 */
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}
	
	body {
		background: #f6f6f6;
		color: #444;
		font-family: 'Roboto', sans-serif;
 		font-family: 'Noto Sans TC', sans-serif !important; /*中文字 */
		font-size: 16px;
		line-height: 1;
	}
	
	.container {
		max-width: 1100px;
		padding: 0 20px;
		margin: 0 auto;
	}
	
	.panel {
		margin: 100px auto 40px;
		max-width: 500px;
		text-align: center;
	}
	
	.button_outer {
		background: #83ccd3;
		border-radius: 30px;
		text-align: center;
		height: 50px;
		width: 200px;
		display: inline-block;
		transition: .2s;
		position: relative;
		overflow: hidden;
	}
	
	.btn_upload {
		padding: 17px 30px 12px;
		color: #fff;
		text-align: center;
		position: relative;
		display: inline-block;
		overflow: hidden;
		z-index: 3;
		white-space: nowrap;
	}
	
	.btn_upload input {
		position: absolute;
		width: 100%;
		left: 0;
		top: 0;
		width: 100%;
		height: 105%;
		cursor: pointer;
		opacity: 0;
	}
	
	.file_uploading {
		width: 100%;
		height: 10px;
		margin-top: 20px;
		background: #ccc;
	}
	
	.file_uploading .btn_upload {
		display: none;
	}
	
	.processing_bar {
		position: absolute;
		left: 0;
		top: 0;
		width: 0;
		height: 100%;
		border-radius: 30px;
		background: #83ccd3;
		transition: 3s;
	}
	
	.file_uploading .processing_bar {
		width: 100%;
	}
	
	.success_box {
		display: none;
		width: 50px;
		height: 50px;
		position: relative;
	}
	
	.success_box:before {
		content: '';
		display: block;
		width: 9px;
		height: 18px;
		border-bottom: 6px solid #fff;
		border-right: 6px solid #fff;
		-webkit-transform: rotate(45deg);
		-moz-transform: rotate(45deg);
		-ms-transform: rotate(45deg);
		transform: rotate(45deg);
		position: absolute;
		left: 17px;
		top: 10px;
	}
	
	.file_uploaded .success_box {
		display: inline-block;
	}
	
	.file_uploaded {
		margin-top: 0;
		width: 50px;
		background: #83ccd3;
		height: 50px;
	}
	
	.uploaded_file_view {
		max-width: 300px;
		margin: 40px auto;
		text-align: center;
		position: relative;
		transition: .2s;
		opacity: 0;
		border: 2px solid #ddd;
		padding: 15px;
	}
	
	.file_remove {
		width: 30px;
		height: 30px;
		border-radius: 50%;
		display: block;
		position: absolute;
		background: #aaa;
		line-height: 30px;
		color: #fff;
		font-size: 12px;
		cursor: pointer;
		right: -15px;
		top: -15px;
	}
	
	.file_remove:hover {
		background: #222;
		transition: .2s;
	}
	
	.uploaded_file_view img {
		max-width: 100%;
	}
	
	.uploaded_file_view.show {
		opacity: 1;
	}
	
	.error_msg {
		text-align: center;
		color: #f00
	}
	/* 顯圖特效結束*/
	
	section{
		margin-left: 100px;
	}
	div#title{
		margin-left: 30px;
	}
	img{
		margin-top: 30px; 
		margin-bottom: 30px;
		border: 1px solid #ddd;
	    border-radius: 4px;
	    padding: 5px;
	    width: 300px;
	    max-width: 100%;
	}
	table{
		cellspacing: 30px;
		cellpadding: 10px;
		bgcolor: white;
		border: 1px solid gray;
		margin-top: 20px;
		padding: 30px;
		box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.3);				
	}
	
</style>



</head>
<body bgcolor='lightpink'>	

	<br>
	<div id="title"><h2>貼文施工修改... <i class="fas fa-pencil-alt"></i></h2></div>
	

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<section>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet"
		name="form1" enctype="multipart/form-data">
		<table class="table">
<!-- 			<tr> -->
<!-- 				<td>貼文編號:<font color=red><b>*</b></font></td> -->
<%-- 				<td><%=mppVO.getPostNo()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>會員編號:<font color=red><b>*</b></font></td> -->
<%-- 				<td><%=mppVO.getMemberNo()%></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>更換後的貼文圖片  :  </td>
				<td>
					<main class="main_full">
						<div class="container">
							<div class="panel">
								<div class="button_outer">
									<div class="btn_upload">
										<input type="file" id="upload_file" name="postPhoto">
										Choose Image
									</div>
									<div class="processing_bar"></div>
									<div class="success_box"></div>
								</div>
							</div>
							<div class="error_msg"></div>
							<div class="uploaded_file_view" id="uploaded_view">
								<span class="file_remove">X</span>
							</div>
						</div>
					</main>				
				</td>
			</tr>
			<tr>
			<td>原本貼文圖片 : </td>
			<td><img src="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet?postNo=${mppVO.postNo}" /></td>
			</tr>
			<tr>
				<td>貼文內容   :  </td>
				<td><textarea class="form-control" name="postContent"
						id="message" placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="15" cols="40"><%=mppVO.getPostContent()%></textarea></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>發表時間:</td> -->
<%-- 				<td><%=mppVO.getPostTime()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>按讚數:</td> -->
<%-- 				<td><%=mppVO.getNumOfLike()%></td> --%>
<!-- 			</tr>		  -->					
			<tr>
				<td>貼文狀態  :  </td>
				<td>
				<input type="radio" name="postState" id="hidden" value="0"><label for="hidden">隱藏</label> 
				<input type="radio" name="postState" id="show" value="1" checked><label for="show">顯示</label></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="postNo" value="<%=mppVO.getPostNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllMemPerPage.jsp-->			  
			 <button type="submit"><i class="fas fa-upload"> 上傳</i></button>
			 
	</FORM>
<section>
	
</body>

	
	  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

	<!-- partial -->
	<script>
		var btnUpload = $("#upload_file"), btnOuter = $(".button_outer");
		btnUpload.on("change", function(e) {
			var ext = btnUpload.val().split('.').pop().toLowerCase();
			if ($.inArray(ext, [ 'gif', 'png', 'jpg', 'jpeg' ]) == -1) {
				$(".error_msg").text("Not an Image...");
			} else {
				$(".error_msg").text("");
				btnOuter.addClass("file_uploading");
				setTimeout(function() {
					btnOuter.addClass("file_uploaded");
				}, 3000);
				var uploadedFile = URL.createObjectURL(e.target.files[0]);
				setTimeout(function() {
					$("#uploaded_view")
							.append('<img src="'+uploadedFile+'" />').addClass(
									"show");
				}, 3500);
			}
		});
		$(".file_remove").on("click", function(e) {
			$("#uploaded_view").removeClass("show");
			$("#uploaded_view").find("img").remove();
			btnOuter.removeClass("file_uploading");
			btnOuter.removeClass("file_uploaded");
		});
	</script>


</html>