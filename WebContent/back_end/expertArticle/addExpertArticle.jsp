<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.expertarticle.model.*"%>

<%
	ExpertArticleVO eaVO = (ExpertArticleVO) request.getAttribute("eaVO");//ExpertAticleServlet.java (Concroller) 存入req的eaVO物件 (包括幫忙取出的eaVO, 也包括輸入資料錯誤時的eaVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>專家個人貼文新增</title>
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
		action="<%=request.getContextPath()%>/expertArticle/ExpertArticleServlet"
		method="post" enctype="multipart/form-data">

		<br>


		<table>
			<tr>
				<td>專家編號:</td>
				<td><input type="TEXT" name="expertNo" size="45"
					value="<%=(eaVO == null) ? "" : ""%>" /></td>
			</tr>
			<tr>
				<td>專家專欄圖片:</td>
				<td><input type="file" name="articlePhoto" size="45"
					value="<%=(eaVO == null) ? "" : eaVO.getArticlePhoto()%>" /></td>
					<td><img src="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet?postNo=${eppVO.postNo}"/></td>

			</tr>

			<tr>
				<td>專家專欄內容:</td>
				<td><textarea class="form-control" name="articleContent" id="message"
						placeholder="Message" required="required"
						data-validation-required-message="Please enter your message"
						aria-invalid="false" rows="20"  cols="50"><%=(eaVO == null) ? "" : eaVO.getArticleContent()%></textarea></td>
			</tr>
			<tr>
				<td>專家專欄發表狀態:</td>
				<td>
				<input type="radio" name="articleState" id="hidden" value="0"><label for="hidden">隱藏</label>
				<input type="radio" name="articleState" id="show" value="1" checked><label for="show">顯示</label>
				</td>
			</tr>



		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="上傳">
	</FORM>


</body>
</html>


