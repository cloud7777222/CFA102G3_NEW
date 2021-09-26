<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>
<%@ page import="java.util.*"%>



<%
	MemPersonalPageService mppSvc = new MemPersonalPageService();
    List<MemPersonalPageVO> list = mppSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<%= mppSvc==null %>--
<html>
<head>
<title>所有會員個人頁面貼文 - listAllMemPerPage.jsp</title>

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


<table id="table-1">
	<tr><td>
		 <h3>所有會員個人頁面貼文 - listAllMemPerPage.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>貼文編號</th>
		<th>會員編號</th>
		<th>貼文圖片</th>
		<th>貼文內容</th>
		<th>貼文發表時間</th>
		<th>按讚數</th>
		<th>貼文狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="mppVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${mppVO.postNo}</td>
			<td>${mppVO.memberNo}</td>
			<td><img src="listAllMemPerPage.jsp"></td>
			<td>${mppVO.postContent}</td>
			<td>${mppVO.postTime}</td>
			<td>${mppVO.numOfLike}</td>
			<td>${mppVO.postState}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="postNo"  value="${mppVO.postNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="postNo"  value="${mppVO.postNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>