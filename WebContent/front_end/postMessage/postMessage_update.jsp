<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postmessage.model.*"%>

<%
	PostMessageVO postMessageVO = (PostMessageVO) request.getAttribute("postMessageVO");//PostMessageServlet.java (Concroller) 存入req的postMessageVO物件 (包括幫忙取出的postMessageVO, 也包括輸入資料錯誤時的postMessageVO物件)
%>

<%=postMessageVO == null%>--${postMessageVO.mesNo}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>論壇文章留言修改 - update_PostMessage_input.jsp</title>



</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>論壇文章留言修改 - update_PostMessage_input.jsp</h3>
				
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postMessage/PostMessageServlet" name="form1">
		<table>
			<tr>
				<td>留言編號:<font color=red><b>*</b></font></td>
				<td><%=postMessageVO.getMesNo()%></td>
			</tr>
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=postMessageVO.getMemberNo()%></td>
			</tr>
			<tr>
				<td>文章編號:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="postNo" size="50"
					value="<%=postMessageVO.getPostNo()%>" /></td>
			</tr>						
			<tr>
				<td>留言內容:</td>
				<td><input type="TEXT" name="mesContent" size="50"
					value="<%=postMessageVO.getMesContent()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>留言時間:</td> -->
<%-- 				<td><%=postMessageVO.getMesTime()%></td> --%>
<!-- 			</tr>			 -->
			<tr>
				<td>文章留言狀態:</td>
				<td>
				<input type="radio" name="mesState" id="hidden" value="0"><label for="hidden">隱藏</label> 
				<input type="radio" name="mesState" id="show" value="1" checked><label for="show">顯示</label></td>
			</tr>			


		</table>
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="mesNo" value="<%=postMessageVO.getMesNo()%>">
			 <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			 <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllMemPerPage.jsp-->		  
			 <input type="submit" value="送出修改">
	</FORM>
</body>

</html>