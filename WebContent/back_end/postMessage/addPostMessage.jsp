<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postmessage.model.*"%>


<%
	PostMessageVO postMessageVO = (PostMessageVO) request.getAttribute("postMessageVO");//PostMessageServlet.java (Concroller) 存入req的postMessageVO物件 (包括幫忙取出的postMessageVO, 也包括輸入資料錯誤時的postMessageVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>論壇文章留言新增</title>
</head>



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


	<FORM action="<%=request.getContextPath()%>/postMessage/PostMessageServlet" method="post">

		<br>


		<table>
						
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberNo" size="45" /></td>
			</tr>		
			<tr>
				<td>文章編號:</td>
				<td><input type="TEXT" name="postNo" size="45" /></td>
			</tr>	
			<tr>
				<td>文章留言內容:</td>
				<td><textarea class="form-control" name="mesContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"></textarea></td>
			</tr>
			<tr>
				<td>文章狀態:</td>
				<td>
				<input type="radio" name="mesState" id="hidden" value="0"><label for="hidden">隱藏</label>
				<input type="radio" name="mesState" id="show" value="1" checked><label for="show">顯示</label>
				</td>
			</tr>

		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="上傳">
	</FORM>


</body>
</html>


