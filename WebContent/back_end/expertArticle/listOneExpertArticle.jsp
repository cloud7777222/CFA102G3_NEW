<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertarticle.model.*"%>

<%
	ExpertArticleVO eaVO = (ExpertArticleVO) request.getAttribute("eaVO");//ExpertAticleServlet.java (Concroller) 存入req的eaVO物件 (包括幫忙取出的eaVO, 也包括輸入資料錯誤時的eaVO物件)
%>


<%= eaVO==null %>--${eaVO.articleNo}---- ${param.articleNo}--
<html>
<head>
<title>專家專欄 - listOneExpertArticle.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  img{
  	max-width: 100%
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>專家專欄 - listOneExpertArticle.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/expertArticle/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>專家專欄編號</th>
		<th>專家編號</th>	
		<th>專家專欄內容</th>
		<th>專家專欄圖片</th>
		<th>專家專欄發表時間</th>		
		<th>專家專欄發表狀態</th>
		<th>按讚數</th>
	</tr>
	<tr>
		<td>${eaVO.articleNo}</td>
		<td>${eaVO.expertNo}</td>
		<td>${eaVO.articleContent}</td>
		<td><img src="<%=request.getContextPath()%>/expertArticle/ExpertArticleServlet?articleNo=${eaVO.articleNo}"></td>	
		<td>${eaVO.articleTime}</td>	
		<td>${eaVO.articleState}</td>
		<td>${eaVO.numOfLike}</td>
	</tr>
</table>

</body>
</html>