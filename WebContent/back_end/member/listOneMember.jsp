<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
  img{
  height 50px;
  width 20px;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneMember.jsp</h3>
		 <h4><a href="/CFA102G3/back_end/member/select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>相片</th>
		<th>姓名</th>
		<th>性別</th>
		<th>生日</th>
		<th>職業</th>
		<th>居住地</th>
		<th>手機</th>
		<th>信箱</th>
		<th>自我介紹</th>
		<th>點數</th>
		<th>黑名單</th>
		<th>修改點數</th>
		<th>修改黑名單</th>
	</tr>
	<tr>
		<td><%=memberVO.getMemberNo()%></td>
		<td><%=memberVO.getMemberAccount()%></td>
		<td><%=memberVO.getMemberPassword()%></td>
		<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=<%=memberVO.getMemberAccount()%>"></td>
		<td><%=memberVO.getMemberName()%></td>
		<td><%=memberVO.getMemberGender()%></td>
		<td><%=memberVO.getMemberBirthday()%></td>
		<td><%=memberVO.getMemberJob()%></td>
		<td><%=memberVO.getMemberCountry()%></td>
		<td><%=memberVO.getMemberPhone()%></td>
		<td><%=memberVO.getMemberEmail()%></td>
		<td><%=memberVO.getMemberIntroduce()%></td>
		<td><%=memberVO.getMemberPoint()%></td>
		<td><%=memberVO.getMemberBlackList()%></td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
			     <input type="hidden" name="action"	value="getOne_For_Update_Point">
			  </FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
			     <input type="hidden" name="action"	value="getOne_For_Update_BlackList">
			  </FORM>
		</td>
	</tr>
</table>

</body>
</html>