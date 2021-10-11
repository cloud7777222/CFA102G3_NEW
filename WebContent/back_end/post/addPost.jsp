<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>


<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");//PostServlet.java (Concroller) 存入req的postVO物件 (包括幫忙取出的postVO, 也包括輸入資料錯誤時的postVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic" rel="stylesheet">

<title>論壇文章新增</title>
</head>
<style>
	body {
	  font-family: "Open Sans", sans-serif;
	  color: #5a656e;
	}
</style>


<body bgcolor="lightblue">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM action="<%=request.getContextPath()%>/post/PostServlet" method="post">

		<br>


		<table>
			<tr>
				<td>文章類別編號:</td>
				<td><input type="TEXT" name="postTypeNo" size="45" /></td>
			</tr>				
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberNo" size="45" /></td>
			</tr>
			<tr>
				<td>文章內容:</td>
				<td><textarea class="form-control" name="postContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"></textarea></td>
			</tr>
			<tr>
				<td>文章狀態:</td>
				<td>
				<input type="radio" name="postState" id="hidden" value="0"><label for="hidden">隱藏</label>
				<input type="radio" name="postState" id="show" value="1" checked><label for="show">顯示</label>
				</td>
			</tr>

		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="上傳">
	</FORM>


</body>
</html>


