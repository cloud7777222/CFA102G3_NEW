<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>


<%
	MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) 存入req的mppVO物件 (包括幫忙取出的mppVO, 也包括輸入資料錯誤時的mppVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員個人貼文新增</title>
</head>



<body bgcolor="lightpink">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM
		action="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet"
		method="post" enctype="multipart/form-data">

		<br>


		<table>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memberNo" size="45"
					value="<%=(mppVO == null) ? "" : ""%>" /></td>
			</tr>
			<tr>
				<td>貼文圖片:</td>
				<td><input type="file" name="postPhoto" size="45"
					value="<%=(mppVO == null) ? "" : mppVO.getPostPhoto()%>" /></td>
					<td><img src="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet?postNo=${mppVO.postNo}"/></td>

			</tr>

			<tr>
				<td>貼文內容:</td>
				<td><textarea class="form-control" name="postContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"><%=(mppVO == null) ? "" : mppVO.getPostContent()%></textarea></td>
			</tr>
			<tr>
				<td>貼文狀態:</td>
				<td>
				<input type="radio" name="postState" id="hidden" value="0"><label for="hidden">隱藏</label>
				<input type="radio" name="postState" id="show" value="1" checked><label for="show">顯示</label>
				</td>
			</tr>



		</table>


		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="上傳">
	</FORM>


</body>
</html>


