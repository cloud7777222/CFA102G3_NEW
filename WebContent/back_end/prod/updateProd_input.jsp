<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品更新</title>
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
<%@ include file="/back_end/sliderbar.jsp"%>
<table id="table-1">
	<tr><td>
		 <h3>商品資料修改</h3>
		
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/prod/prod.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td>${prodVO.prodno}</td>
		
	</tr>
          <jsp:useBean id="prodsortSvc" scope="page" class="com.prodsort.model.ProdsortService"/>
	<tr>	
		<td>商品類別:</td>
		<td>
		<select size="1" name="prodsortno">
			<c:forEach var="prodsortVO" items="${prodsortSvc.all}">
				<option value="${prodsortVO.prodsortno}" ${(prodVO.prodsortno==prodsortVO.prodsortno)?'selected':''} >${prodsortVO.prodsortname}
			</c:forEach>
	    </select>
	</td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="prodname"  size="45" value="${prodVO.prodname}" /></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input name="price" type="text" value="${prodVO.price}"></td>
	</tr>
	<tr>
		<td>商品介紹:</td>
		<td><input type="TEXT" name="indroce" size="45"	value="${prodVO.indroce}" /></td>
	</tr>
	<tr>
		<td>商品圖片一:</td>
		<td><input type="file" name="prodpic1" id="file1"size="45" value="${prodVO.prodpic1}" /></td>
		<td><div class="container"> <img id="p1"/></div>
	
	</td>
	</tr><tr>
		<td>商品圖片二:</td>
		<td><input type="file" name="prodpic2" id="file2"size="45" value="${prodVO.prodpic2}" /></td>
		<td><div class="container"> <img id="p2"/></div>
	</tr><tr>
		<td>商品圖片三:</td>
		<td><input type="file" name="prodpic3" id="file3"size="45" value="${prodVO.prodpic3}" /></td>
		<td><div class="container"> <img id="p3"/></div>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prodno" value="${prodVO.prodno}">
<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
<input type="submit" value="送出修改"></FORM>
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