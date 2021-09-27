<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.member.model.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>廣告資料新增 - addAd.jsp</title>


<%@ include file="/back_end/pages/link.file"%>


<style>
* {
	box-sizing: border-box;
}

#myPic img {
	width: 50%;
	margin-left: 25%;
	margin-right: 25%;
}

img {
	width: 100%;
	margin: 30px;
	object-fit: cover;
	cursor: pointer;
}

#preview p {
	text-align: center;
}

input, textarea, button {
	width: 100%;
}

#bigPic {
	display: none;
	position: fixed;
	width: 40%;
	height: 40%;
	top: 50%;
	left: 50%;
	margin-top: -10%;
	/* 	margin-left: -20%; */
	border: 1px solid gray;
	z-index: 999;
}

#bigPic img {
	width: 100%;
	height: 100%;
	margin: 0;
	box-sizing: border-box;
	cursor: default;
	object-fit: cover;
	padding: 0;
}

#bigPic .close {
	position: absolute;
	top: -20px;
	right: -20px;
	color: black;
	width: 20px;
	height: 20px;
	border-radius: 50%;
	border: black;
	z-index: 999;
	cursor: pointer;
}
</style>

</head>

<body id="page-top">

	<form METHOD="post"
		ACTION="<%=request.getContextPath()%>/memTime/memTime.do" name="form1">

		<div class="form-group">
			<input type="hidden" name="memberNoA"
				value="<%=((MemberVO) session.getAttribute("memberVO")).getMemberNo()%>">
			<input type="hidden" name="memberNoB" value="3"> <input
				type="hidden" name="requestURL"
				value="<%=request.getServletPath()%>"> <input type="hidden"
				name="action" value="datingCheck">
			<button type="submit" class="btn btn-primary">送出新增</button>
		</div>

	</form>


</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
<%-- console.log("<%=bb.getStr() %>"); --%>
// console.log("${bb.getStr()}");err
	
<%-- 	let data=<%=bb.getStr() %>; --%>
<%-- let aa='<%=bb.getStr() %>'; --%>
// console.log(aa);
	let data=JSON.parse(aa);
	console.log(data);
	
<%-- 	let jsonData='<%=bb.getJsonData() %>'; --%>
// 	console.log(jsonData);
// 		let data2=JSON.parse(jsonData);
// 		console.log(data2);
	
	</script>

</html>