<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
String[] country = {"國外","臺北市","新北市","桃園市","臺中市","臺南市","高雄市","基隆市","新竹市","嘉義市","新竹縣","苗栗縣","彰化縣","南投縣","雲林縣","嘉義縣","屏東縣","宜蘭縣","花蓮縣","臺東縣","澎湖縣"};
pageContext.setAttribute("country", country);
%>

<jsp:useBean id="listMember_ByCompositeQuery" scope="request" type="java.util.List<MemberVO>" /> <!-- 於EL此行可省略 -->


<html>
<head><title>複合查詢 - listMember_ByCompositeQuery.jsp</title>
<%@ include file="/back_end/header.jsp"%>
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
	width: 800px;
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
  width:120px
  }
</style>

</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body bgcolor='white'>





<table>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員相片</th>
		<th>會員名字</th>
		<th>會員性別</th>
		<th>會員生日</th>
		<th>會員職業</th>
		<th>會員居住地</th>
		<th>會員電話</th>
		<th>會員信箱</th>
		<th>會員自我介紹</th>
	</tr>
	<%@ include file="page1_ByCompositeQuery.file" %>
	<c:forEach var="memberVO" items="${listMember_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${memberVO.memberNo}</td>
			<td>${memberVO.memberAccount}</td>
			<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}"></td>
			<td>${memberVO.memberName}</td>
			<td>${(memberVO.memberGender==1)?"男生":"女生"}</td>
			<td>${memberVO.memberBirthday}</td>
			<td>${memberVO.memberJob}</td>
			<td>${country[memberVO.memberCountry]}</td>
			<td>${memberVO.memberPhone}</td>
			<td>${memberVO.memberEmail}</td>
			<td>${memberVO.memberIntroduce}</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2_ByCompositeQuery.file" %>


  <%@ include file="/back_end/footer.jsp"%>
</body>
</html>