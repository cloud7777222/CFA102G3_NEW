<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<h2>kkkk<%=request.getParameter("memberNoA") %> <%=request.getAttribute("jsonDataA") %></h2>
<script>
function aa(){
	let data=<%=request.getAttribute("jsonDataA") %>;
	return data;
}
	let data=<%=request.getAttribute("jsonDataA") %>;
	console.log(data);
	data=<%=request.getAttribute("jsonDataB") %>;
	console.log(data);
	console.log("jjjjjjjj");
	console.log(aa());

</script>
</body>
</html>