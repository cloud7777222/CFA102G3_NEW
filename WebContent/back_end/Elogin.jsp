<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Elogin</title>
<style>
*{
	margin:0;
	padding:0;
	font-family:'標楷體字型'
}
body{
	display:flex;
	justify-content:center;
	align-items:center;
	min-height:100vh;
	background:linear-gradient(45deg,#fead46,#c00def);
}
.container{
	position:relative;
	padding:70px 40px;
	background:#fff;
	border-radius:20px;
	box-shadow:0 5px 25px rgba(0,0,0,0.2);
}
.container h2{
	color: black;
	margin-bottom:45px;
	line-height:1em;
	font-weight:600;
	padding-left:10px;
	border-left:5px solid #e91e63;
}
.container .inputBox{
	position:relative;
	width:300px;
	height:46px;
	margin-bottom:35px;
}
.container .inputBox:last-child{
	margin-bottom:0;
}
.container .inputBox input{
	position:absolute;
	top:0;
	left:0;
	width:100%;
	border:1px solid #111;
	background:transparent;
	padding:10px;
	border-radius:4px;
	box-sizing:border-box;
	outline:none;
	font-size:16px;
	color:#111;
	font-weight:300;
	
}
.container .inputBox span{
	position :absolute;
	top :1px;
	left:1px;
	padding:10px;
	display:inline-block;
	font-size:#111;
	font-weight:300;
	transition:0.5s;
	pointer-events:none;

}
.container .inputBox input:valid ~span,
.container .inputBox input:focus ~span{
	transform:translateX(-10px) translateY(-32px);
	font-size:12px;
}
.container .inputBox input[type="submit"]{
	background:#2196f3;
	color:#fff;
	border:none;
	max-width:120px;
	cursor:pointer;
	font-weight:500;
}
.container .inputBox input[type="submit"]:hover{
	background:linear-gradient(45deg,#fead46,#c00def);
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
</head>
<body>
<div class="container">
 <h2>Beloved Employee Login</h2>
 <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
 <br>
 
  <form method="post" action="<%=request.getContextPath()%>/emp/emploginout.do">
   <div class="inputBox">
	<input type="text"  name="empaccount">
	<span>Account</span>
   </div>
   <div class="inputBox">
   <input type="password"  name="emppassword">
<span>Password</span>
</div><div class="inputBox">
<input type="submit" value="login" name="action" >
</div>
</form>
</div>
</body>
</html>