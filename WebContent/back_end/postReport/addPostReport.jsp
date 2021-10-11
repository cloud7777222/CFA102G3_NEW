<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postreport.model.*"%>


<%
	PostReportVO postReportVO = (PostReportVO) request.getAttribute("postReportVO");//PostReportServlet.java (Concroller) 存入req的postReportVO物件 (包括幫忙取出的postReportVO, 也包括輸入資料錯誤時的postReportVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>論壇文章檢舉新增</title>
</head>


<body bgcolor="lightgreen">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM action="<%=request.getContextPath()%>/postReport/PostReportServlet" method="post">

		<br>


		<table>
			<tr>
				<td>文章編號:</td>
				<td><input type="TEXT" name="postNo" size="45" /></td>
			</tr>				
			<tr>
				<td>檢舉人會員編號:</td>
				<td><input type="TEXT" name="memberNo" size="45" /></td>
			</tr>
			<tr>
				<td>文章檢舉原因:</td>
				<td><textarea class="form-control" name="postRepFor" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"></textarea></td>
			</tr>
			<tr>
				<td>文章檢舉處理狀態:</td>
				<td>
				<input type="radio" name="postRevState" id="unverified" value="1" checked><label for="unverified">待審核</label> 
				<input type="radio" name="postRevState" id="verified" value="2" ><label for="verified">審核通過</label>
				<input type="radio" name="postRevState" id="reject" value="3" ><label for="reject">審核未通過</label>
				</td>
			</tr>			
		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="上傳">
	</FORM>


</body>
</html>


