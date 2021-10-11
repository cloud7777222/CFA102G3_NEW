<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.member.model.*"%>

<%-- <%=request.getParameter("postTypeNo")==null%> --%>

<%-- ${sessionScope.memberVO.memberNo} --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">

<title>論壇文章新增</title>
</head>
<style>
	body {
	  font-family: "Open Sans", sans-serif;
	  color: #5a656e;
	}
	
</style>


<body bgcolor="lightblue">
<%-- <% out.println("postTypeNo : "+request.getParameter("postTypeNo")); %> --%>
	

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
							<td>類別編號:</td>
							<td><input type="TEXT" name="postTypeNo" size="20" value="<%=request.getParameter("postTypeNo")%>"/>  1)旅遊; 2)吃吃喝喝; 3)兩性關係; 4)其他</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td>會員編號:</td> -->
<!-- 							<td><input type="TEXT" name="memberNo" size="" /></td> -->
<!-- 						</tr> -->
						<tr>
							<td>文章內容:</td>
							<td><textarea class="form-control" name="postContent"
									id="message" placeholder="Message" required="required"
									data-validation-required-message="Please enter your message"
									aria-invalid="false" rows="20" cols="50"></textarea></td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td>文章狀態:</td> -->
<!-- 							<td><input type="radio" name="postState" id="hidden" -->
<!-- 								value="0"><label for="hidden">隱藏</label> <input -->
<!-- 								type="radio" name="postState" id="show" value="1" checked><label -->
<!-- 								for="show">顯示</label></td> -->
<!-- 						</tr> -->
					</table>

					<input type="hidden" name="action" value="insert">
<%-- 					<input type="hidden" name="postTypeNo" value="<%=request.getParameter("postTypeNo")%>"> --%>
					<input type="submit" value="上傳">						
				</FORM>

	
<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>
	
</body>
</html>


