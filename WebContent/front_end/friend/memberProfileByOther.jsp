<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
  MemberVO memberVOA = (MemberVO) request.getAttribute("memberVOA");
  MemberVO memberVOB = (MemberVO) request.getAttribute("memberVOB");
  FriendService friendService=new FriendService();
 
  Integer memberNoA=memberVOA.getMemberNo();
  Integer memberNoB=memberVOB.getMemberNo();
  boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
</style>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>



</head>
<body bgcolor='white'>

--------------------------------------<br>
<table>
	<tr>
		<th>相片</th>
		<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVOB.memberAccount}"></td>
	</tr>
	<tr>
		<th>姓名</th>
		<td><%=memberVOB.getMemberName()%></td>
	</tr>
	<tr>
		<th>性別</th>
		<td><%=memberVOB.getMemberGender()%></td>
	</tr>
	<tr>
		<th>生日</th>
		<td><%=memberVOB.getMemberBirthday()%></td>
	</tr>
	<tr>
		<th>職業</th>
		<td><%=memberVOB.getMemberJob()%></td>
	</tr>
	<tr>
		<th>居住地</th>
		<td><%=memberVOB.getMemberCountry()%></td>
	</tr>
	
	<tr>
		<th>自我介紹</th>
		<td><%=memberVOB.getMemberIntroduce()%></td>
		<form action="<%=request.getContextPath()%>/friend/friend" method="POST">
		<input type="hidden" name="action" value="add_Friend">
		<input type="hidden" name="memberAccountA" value="${memberVOA.memberAccount}">
		<input type="hidden" name="memberAccountB" value="${memberVOB.memberAccount}">
		<td><input type="submit" value="加為好友"></td>
		</form>
		
		<form id="Refresh" action="<%=request.getContextPath()%>/friend/friend" method="POST">
		<input type="hidden" name="action" value="member_Profile_By_Other">
		<input type="hidden" name="memberAccountA" value="${memberVOA.memberAccount}">
		<input type="hidden" name="memberAccountB" value="${memberVOB.memberAccount}">
		</form>
	</tr>
</table>
<script>
function refresh(){
if(<%=check%>){
	let Refresh=document.getElementById("Refresh");
	Refresh.submit();
}
}
refresh();
</script>
</body>

</html>