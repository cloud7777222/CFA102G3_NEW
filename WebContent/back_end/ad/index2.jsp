<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.memTime.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdService adSvc = new AdService();
	boolean isNull = request.getAttribute("keyword") == null;
	List<AdVO> list = isNull
			? adSvc.getAll()
			: adSvc.getAllByKeyword(request.getAttribute("keyword").toString());
	pageContext.setAttribute("list", list);
	
	TestJ bb= new TestJ();
%>
<%-- <jsp:useBean id="adSvc" scope="page" class="com.ad.model.AdService" /> --%>

<html>
<head>
<title>所有廣告資料</title>
<%-- <%=request.getContextPath() + request.getServletPath()%> --%>

<%@ include file="/back_end/pages/link.file"%>


<style>
.table-header {
	display: flex;
}

.table-header .section-left {
	width: 50%;
	text-align: left;
}

.table-header .section-right {
	width: 50%;
}

.table-header .section-right form {
	text-align: right;
}

.table-footer {
	text-align: center;
}

table {
	width: 100%;
}

img {
	width: 100px;
	height: 100px;
	object-fit: cover;
}

.table-footer a {
	display: inline-block;
	width: 30px;
	height: 30px;
	cursor: pointer;
	line-height: 30px;
	border-radius: 5px;
}

table td {
	width: 120px;
	overflow-wrap: anywhere;
}
</style>
</head>

<body id="page-top">

	<%=bb.getStr() %>

	<script>
<%-- console.log("<%=bb.getStr() %>"); --%>
// console.log("${bb.getStr()}");err
	
<%-- 	let data=<%=bb.getStr() %>; --%>
let aa='<%=bb.getStr() %>';
console.log(aa);
	let data=JSON.parse(aa);
	console.log(data);
	
	let jsonData='<%=bb.getJsonData() %>';
	console.log(jsonData);
		let data2=JSON.parse(jsonData);
		console.log(data2);
	
	</script>


</body>
</html>