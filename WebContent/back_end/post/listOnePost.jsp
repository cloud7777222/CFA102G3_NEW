<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.posttype.model.*"%>


<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");//PostServlet.java (Concroller) 存入req的postVO物件 (包括幫忙取出的postVO, 也包括輸入資料錯誤時的postVO物件)
%>

<%-- 引入PostTypeService, 欲取出對應的postType--%>
<jsp:useBean id="postTypeSvc" scope="page" class="com.posttype.model.PostTypeService" />

<%= postVO==null %>--${postVO.postNo}--${postTypeVO.postType}
<html>
<head>
<title>單一文章檢視 - listOnePost.jsp</title>

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
		 <h3>單一文章檢視 - listOnePost.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/post/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>文章編號</th>
		<th>文章類別編號</th>
		<th>會員編號</th>		
		<th>文章內容</th>
		<th>發表時間</th>		
		<th>文章狀態</th>
		<th>文章留言數</th>
		<th>按讚數</th>
	</tr>
	<tr>
		<td>${postVO.postNo}</td>
		<td>(${postVO.postTypeNo}) ${postTypeSvc.getOnePostType(postVO.postTypeNo).postType} </td>
		<td>${postVO.memberNo}</td>		
		<td>${postVO.postContent}</td>
		<td>${postVO.postTime}</td>
		<td>${postVO.postState}</td>
		<td>${postVO.mesCount}</td>
		<td>${postVO.numOfLike}</td>
		
	</tr>
</table>

</body>
</html>