<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>

 <%
    ProdService prodSvc = new ProdService();
    List<ProdVO> list = prodSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list1 = empSvc.getAll();
    pageContext.setAttribute("list1",list1);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台管理</title>
</head>
<body>
<h3>後臺商品管理</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<a href="<%=request.getContextPath()%>/back_end/prod/listAllProd.jsp">ListAllProd</a>

<a href="<%=request.getContextPath()%>/back_end/prod/addProd.jsp">addprod</a>

<h3>用商品種類搜尋</h3>
<form method="post" action="<%=request.getContextPath()%>/prod/prod.do">
<jsp:useBean id="prodsortSvc" scope="page" class="com.prodsort.model.ProdsortService" />
		<select size="1" name="prodsortno">
			<c:forEach var="prodsortVO" items="${prodsortSvc.all}">
				<option value="${prodsortVO.prodsortno}">${prodsortVO.prodsortname}
			</c:forEach>
	    </select>
<input type="hidden" name="action" value="getBySort">
<input type="submit" value="查詢產品">
</form>

<h3>用商品名稱搜尋</h3> 
	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/prod/prod.do" >
       <select size="1" name="prodno">
         <c:forEach var="prodVO" items="${list}" > 
          <option value="${prodVO.prodno}">${prodVO.prodname}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
 <h3>後臺員工管理</h3>
 <a href="<%=request.getContextPath()%>/back_end/emp/listAllEmp.jsp">ListAllEmp</a>
 <a href="<%=request.getContextPath()%>/back_end/emp/addEmp.jsp">addemp</a>
 <h3>用員工姓名搜尋</h3>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
       <select size="1" name="empno">
         <c:forEach var="empVO" items="${list1}" > 
          <option value="${empVO.empno}">${empVO.empname}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  


</body>
</html>