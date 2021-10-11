<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>


<%
	MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) 存入req的mppVO物件 (包括幫忙取出的mppVO, 也包括輸入資料錯誤時的mppVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員個人貼文新增</title>
</head>


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

</style>


<body bgcolor="lightpink">

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
	<FORM
		action="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet"
		method="post" enctype="multipart/form-data">

		<br>


		<table>
<!-- 			<tr> -->
<!-- 				<td>會員編號:  </td> -->
<!-- 				<td><input type="TEXT" name="memberNo" size="10" -->
<%-- 					value="<%=(mppVO == null) ? "" : ""%>" /></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>貼文圖片:  </td>
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
				<td>貼文內容:  </td>
				<td><textarea class="form-control" name="postContent"
						id="message" placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="15" cols="40"><%=(mppVO == null) ? "" : mppVO.getPostContent()%></textarea></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>貼文狀態:  </td> -->
<!-- 				<td><input type="radio" name="postState" id="hidden" value="0"><label -->
<!-- 					for="hidden">隱藏</label> <input type="radio" name="postState" -->
<!-- 					id="show" value="1" checked><label for="show">顯示</label></td> -->
<!-- 			</tr> -->
		</table>
		<p>
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="上傳">
		
	</FORM>
</section>	
	
	
	

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

</body>
</html>


