<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>

<%
MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) 存入req的mppVO物件 (包括幫忙取出的mppVO, 也包括輸入資料錯誤時的mppVO物件)
%>

<%= mppVO==null %>--${mppVO.postNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員個人頁面貼文修改 - update_MemPerPage_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員個人頁面貼文修改 - update_MemPerPage_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="MemPersonalPageServlet" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>貼文編號:<font color=red><b>*</b></font></td>
		<td><%=mppVO.getPostNo()%></td>
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=mppVO.getMemberNo()%></td>
	</tr>
	<tr>
		<td>貼文圖片:</td>
		<td><input type="file" name="postPhoto" size="45"
			 value="<%= (mppVO==null)? "" : mppVO.getPostPhoto()%>" /></td>
	</tr>
	<tr>
		<td>貼文內容:</td>
		<td><input type="TEXT" name="postContent" size="50"
			 value="<%=mppVO.getPostContent()%>" /></td>
	</tr>
	<tr>
		<td>發表時間:</td>
		
		<td><%=mppVO.getPostTime()%></td>
	</tr>
	<tr>
		<td>按讚數:</td>
		<td><%=mppVO.getNumOfLike()%></td>
	</tr>
	<tr>
		<td>貼文狀態:</td>
		<td><input type="TEXT" name="postState" size="50"
			 value="<%=mppVO.getPostState()%>" /></td>
	</tr>
	



</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="postNo" value="<%=mppVO.getPostNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>

</html>