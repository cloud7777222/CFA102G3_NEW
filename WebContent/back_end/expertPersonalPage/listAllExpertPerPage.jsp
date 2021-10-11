<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertpersonalpage.model.*"%>
<%@ page import="java.util.*"%>



<%
	ExpertPersonalPageService eppSvc = new ExpertPersonalPageService();
    List<ExpertPersonalPageVO> list = eppSvc.getAll();
    pageContext.setAttribute("list", list);    
%>
<%= eppSvc==null %>-- ${param.postNo}--

 
      

<html>
<head>
<title>所有專家個人頁面貼文 - listAllExpertPerPage.jsp</title>

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
  img{
  max-width: 100%;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>所有專家個人頁面貼文 - listAllExpertPerPage.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/expertPersonalPage/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>專家編號</th>
		<th>貼文圖片</th>
		<th>貼文內容</th>
		<th>貼文發表時間</th>
		<th>按讚數</th>
		<th>貼文狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="eppVO" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(eppVO.postNo==param.postNo) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
	
			<td>${eppVO.postNo}</td>
			<td>${eppVO.expertNo}</td>
			<td><img src="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet?postNo=${eppVO.postNo}"/></td>
			<td>${eppVO.postContent}</td>
			<td>${eppVO.postTime}</td>
			<td>${eppVO.numOfLike}</td>
			<td>${eppVO.postState}</td>
			
			
			<td>						
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="postNo"  value="${eppVO.postNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="postNo"  value="${eppVO.postNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>
   
</body>
</html>