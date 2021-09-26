<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.ad.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller AdServlet.java已存入request的AdVO物件--%>
<%AdVO adVO = (AdVO) request.getAttribute("adVO");%>

<html>
<head>
<title>員工資料 - listOneAd.jsp</title>

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
  img {
	width: 100px;
	height: 100px;
	object-fit: cover;
}
</style>

<style>
  table {
	width: 800px;
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
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - listOneAd.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/ad/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
				<th>廣告編號</th>
				<th>廣告標題</th>
				<th>廣告敘述</th>
				<th>圖片1</th>
				<th>圖片2</th>
				<th>圖片3</th>
				<th>廣告狀態</th>
				<th>發佈日</th>
				<th>截止日</th>
			</tr>
	<tr>
		<td><%=adVO.getAdNo()%></td>
		<td><%=adVO.getAdTitle()%></td>
		<td><%=adVO.getAd()%></td>
		<td><img src="<%=adVO.getAdPic1()%>"></td>
		<td><img src="<%=adVO.getAdPic2()%>"></td>
		<td><img src="<%=adVO.getAdPic3()%>"></td>
		<td><%=adVO.getAdState()%></td>
		<td><%=adVO.getPostTime()%></td>
		<td><%=adVO.getDeadline()%></td>
	</tr>
</table>

</body>
</html>