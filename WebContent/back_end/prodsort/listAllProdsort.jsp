
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prodsort.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有商品總類</title>
<%@ include file="/back_end/header.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<%
	ProdsortService prodsortSvc = new ProdsortService();
    List<ProdsortVO> list = (List<ProdsortVO>)prodsortSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="prodsortSrv" scope="page" class="com.prodsort.model.ProdsortService" />
<%@ include file="/back_end/sliderbar.jsp"%>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="container">
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">商品類別編號</th>
      <th scope="col">商品類別</th>
      <th scope="col">修改類別名稱</th>
    </tr>
   </thead>
   <c:forEach var="prodsortVO" items="${list}">
   <tbody>
   <tr>
   <td>${prodsortVO.prodsortno}</td>
   <td>${prodsortVO.prodsortname}</td>
   <td> 
     <input type="button" value="修改" onclick="updatesortname(this)" id="${prodsortVO.prodsortno}"/>
   </td>
   </tr>
   </tbody>
   </c:forEach>
 </table>
 </div>
 <%@ include file="/back_end/footer.jsp"%>  
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
   <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
   <script type="text/javascript">
   function updatesortname(prodsortno){
	
    Swal.fire({
		   title: '輸入要更改的類別名稱',
		   html: `<input type="text" id="prodsortname" class="swal2-input" placeholder="商品種類名稱" >`,
		   focusConfirm: false,
		   showCancelButton: true,
		   confirmButtonText: '確認',
		   cancelButtonText: "取消",
		   cancelButtonColor:"red",
		   showLoaderOnConfirm: true,
		   preConfirm: () =>{
			   var prodsortname = document.getElementById('prodsortname').value;
			   if (prodsortname.length == 0) {
				      Swal.showValidationMessage("請勿空白");
				      return;
				}   
		     $.ajax({
			url:"<%=request.getContextPath()%>/prodsort/prodsort.do",
			type:"POST",
			data:{
				prodsortno:prodsortno.id,
				action:"update",
				prodsortname: document.getElementById('prodsortname').value
			},
			success: function(){
				window.location="<%=request.getContextPath()%>/back_end/prodsort/listAllProdsort.jsp"
			}
			
		});
		}		     
      })		   
	 }
	
  
   </script>
</body>
</html>