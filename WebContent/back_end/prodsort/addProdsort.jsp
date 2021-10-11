<%@page import="com.prod.model.ProdVO"%>
<%@page import="com.prodsort.model.ProdsortVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%ProdsortVO prodsortVO = (ProdsortVO)request.getAttribute("prodsortVO");%>
<%ProdVO prodVO = (ProdVO)request.getAttribute("prodVO"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增商品種類</title>
<%@ include file="/back_end/header.jsp"%>
<script
  src="https://code.jquery.com/jquery-1.12.4.js"
  ></script>
  <style>
#p1,#p2,#p3{

width:150px;
height:150px;
}
</style>
</head>
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<%@ include file="/back_end/sliderbar.jsp"%>

<h3>商品種類暨商品新增</h3>

<form method="post" action="<%=request.getContextPath()%>/prodsort/prodsort.do" enctype="multipart/form-data">
<table>
<tr>
	<td style="color:red">產品種類名稱:</td>
	<td><input type="text" name="prodsortname" size="45"value="${prodsortVO.prodsortname}"/></td>
</tr>
<tr>
	<td>產品名稱:</td>
	<td><input type="text" name="prodname" size="45"value="${prodVO.prodname}"/></td>
</tr>
<tr>
	<td>產品價格:</td>
	<td><input type="text" name="price" size="45"value="${prodVO.price}"/></td>
</tr>
<tr>
	<td>產品介紹:</td>
	<td><input type="text" name="indroce" size="45"value="${prodVO.indroce}"/></td>
</tr>
<tr>
	<td>產品圖片一:</td>
	<td>
	  <input type="file" name="prodpic1" id="file1" value="${prodVO.prodpic1}"  accept="image/gif, image/jpeg, image/png">
	</td>
	<td>
	<div class="container">
  <img id="p1"/>
</div>
	</td>
</tr>

<tr>
	<td>產品圖片二:</td>
	<td>
	
	<input type="file" name="prodpic2" id="file2" value="${prodVO.prodpic2}" accept="image/gif, image/jpeg, image/png">
	</td>
	<td>
	<div class="container">
  <img id="p2"/>
</div>
	
	</td>
</tr>

<tr>
	<td>產品圖片三:</td>
	<td>
	
	<input type="file" name="prodpic3" id="file3" value="${prodVO.prodpic3}"accept="image/gif, image/jpeg, image/png"/>
	</td>
	<td>
	<div class="container">
  <img id="p3"/>
</div>
	</td>
</tr>
<tr>
	<td>產品是否上架:</td>
	<td><input type="radio" name="prodstate" value="1" checked/>上架<input type="radio" name="prodstate" value="0"/>不上架</td>
</tr>
</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="新增產品類別與產品">

</form>
<%@ include file="/back_end/footer.jsp"%>
<script>
$('#file1').on('change', function(e){      
	  const file = this.files[0];
	      
	  const fr = new FileReader();
	  fr.onload = function (e) {
	    $('#p1').attr('src', e.target.result);
	  };
	      
	  
	  fr.readAsDataURL(file);
	});
$('#file2').on('change', function(e){      
	  const file = this.files[0];
	      
	  const fr = new FileReader();
	  fr.onload = function (e) {
	    $('#p2').attr('src', e.target.result);
	  };
	      
	  
	  fr.readAsDataURL(file);
	});
$('#file3').on('change', function(e){      
	  const file = this.files[0];
	      
	  const fr = new FileReader();
	  fr.onload = function (e) {
	    $('#p3').attr('src', e.target.result);
	  };
	      
	  
	  fr.readAsDataURL(file);
	});


</script>
</body>
</html>