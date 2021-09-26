<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.expert.model.*"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>專家資料修改 - update_expert_input.jsp</title>
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
		 <h3>專家資料修改 - update_expert_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/expert/select_page.jsp"><img src="/images/original.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expert.do" name="form1">
<table>
<tr>
	<td>專家編號:</td>
		<td><input type="TEXT" name="expertNo" size="45" value="=${expertVO.expertNo}" /></td>
	</tr>
	 <jsp:useBean id="expertgenreSvc" scope="page" class="com.expertgenre.model.ExpertgenreService"/>
	<tr>	
		<td>專家種類邊號:</td>
	<td>
		<select size="1" name="expertGenreNo">
			<c:forEach var="expertgenreVO" items="${expertgenreSvc.all}">
				<option value="${expertgenreVO.expertGenreNo}" ${(expertVO.expertGenreNo==expertgenreVO.expertGenreNo)}>${expertgenreVO.exGenreName}
			</c:forEach>
	    </select>
	</tr>
	 <tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="exAcount" size="45" value="${expertVO.exAcount}" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="exPassword" size="45" value="${expertVO.exPassword}" /></td>
	</tr>
	<tr>
		<td>相片:</td>
		<td><input type="TEXT" name="exPhoto" size="45"	value="${expertVO.exPhoto}>" /></td>
	</tr>
	<tr>
	<td>姓名:</td>
		<td><input type="TEXT" name="exName" size="45"	value="${expertVO.exName}" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="exGender" size="45"	value="${expertVO.exGender}" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="exPhone" size="45"	value="${expertVO.exPhone}" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="TEXT" name="exEmail" size="45"	value="${expertVO.exEmail}" /></td>
	</tr>
		<tr>
		<td>地址:</td>
		<td><input type="TEXT" name=" exAdress" size="45"	value="${expertVO.exAdress}" /></td>
	</tr>
	<tr>
		<td>自我介紹:</td>
		<td><input type="TEXT" name="exIntroduce" size="45"	value="${expertVO.exIntroduce}" /></td>
	</tr>
	<tr>
		<td>點數:</td>
		<td><input type="TEXT" name="exPoint" size="45"	value="${expertVO.exPoint}" /></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="expertNo" value="${expertVO.expertNo}">
<input type="submit" value="送出修改"></FORM>
</body>
</html>