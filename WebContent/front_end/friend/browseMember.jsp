<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//用Session拿是因為重新整理memberVOA需要值(如果要的話要再去訪問一次Servlet)
	Integer memberNoA=memberVO.getMemberNo();
    MemberService memberService = new MemberService();
	Set<MemberVO> set=memberService.getAllMemberExceptMeBySet(memberNoA);
    pageContext.setAttribute("set",set);//做set用
	
	FriendService friendService=new FriendService();
    
	 List<MemberVO> requestList=new ArrayList<MemberVO>();
	 requestList=friendService.getFriendRequest(memberNoA);
	 pageContext.setAttribute("requestList",requestList);//好友邀請用
%>

<html>
<head>
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
</style>




<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>   
$(document).ready(function() {
	$("#checkRequest").click(function() {
		$("#RequestList").toggle();
	});
});
</script>
</head>
<body bgcolor='white'>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<form action="<%=request.getContextPath()%>/member/member" method="POST">
		<input type="hidden" name="action" value="member_Profile_By_Me">
		<input type="hidden" name="memberAccount" value="<%=memberVO.getMemberAccount()%>">
		<td><input type="submit" value="修改個人資料"></td>
	</form>
	
	<form action="<%=request.getContextPath()%>/chat/chat" method="POST">
		<input type="hidden" name="action" value="chat_Room">
		<input type="hidden" name="memberAccount" value="<%=memberVO.getMemberAccount()%>">
		<td><input type="submit" value="好友聊天"></td>
	</form>
		
	<input id="checkRequest" type="button" value="查看好友邀請">
		<table id="RequestList" style="display: none;">
			<c:forEach var="Request" items="${requestList}">
				<tr>
					<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${Request.memberAccount}"></td>
					<td>${Request.memberName}</td>
					<td>
						<form action="<%=request.getContextPath()%>/friend/friend" method="POST">
							<input type="submit" value="確認好友">
							<input type="hidden" name="action" value="response_Friend_Request">
							<input type="hidden" name="memberAccountA" value="${memberVO.memberAccount}">
							<input type="hidden" name="memberAccountB" value="${Request.memberAccount}">
						</form>
						<form action="<%=request.getContextPath()%>/friend/friend" method="POST">
							<input type="submit" value="刪除邀請">
							<input type="hidden" name="action" value="delete_Friend_Request">
							<input type="hidden" name="memberAccountA" value="${memberVO.memberAccount}">
							<input type="hidden" name="memberAccountB" value="${Request.memberAccount}">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
<table>
	<tr>
		<th>相片</th>
		<th>姓名</th>
		<th>性別</th>
		<th>生日</th>
		<th>職業</th>
		<th>居住地</th>
		<th>自我介紹</th>
		<th>瀏覽其他會員</th>
	</tr>
	
	<c:forEach var="memberVO" items="${set}" begin="0" end="4">
		<tr>
			<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}"></td>
			<td>${memberVO.memberName}</td>
			<td>${memberVO.memberGender}</td>
			<td><fmt:formatDate value="${memberVO.memberBirthday}" pattern="yyyy-MM-dd HH:mm:ss.SSSZ"/></td>
			<td>${memberVO.memberJob}</td>
			<td>${memberVO.memberCountry}</td>
			<td>${memberVO.memberIntroduce}</td>
			<td>
				<form action="<%=request.getContextPath()%>/friend/friend" method="POST">
					<input type="hidden" name="action" value="member_Profile_By_Other">
					<input type="hidden" name="memberAccountA" value="<%=memberVO.getMemberAccount()%>">
					<input type="hidden" name="memberAccountB" value="${memberVO.memberAccount}">
					<input type="submit" value="瀏覽個人頁面">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<a href="/CFA102G3/front_end/friend/browseMember.jsp">換一批</a>

</body>
</html>