<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單更新</title>
<%@ include file="/back_end/header.jsp"%>
</head>
<body>
<%@ include file="/back_end/sliderbar.jsp"%>
<a href="<%=request.getContextPath()%>/back_end/order/listAllOrder.jsp">回訂單管理</a><br>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order/order.do">
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td>${orderVO.orderno}</td>
	</tr>
	<tr>	
		<td>訂單者姓名:</td>
		<td><input name="orderer"  type="TEXT" value="${orderVO.orderer}"/></td>
	</tr>
	<tr>
		<td>訂單地址:</td>
		<td>
		  <input class="js-demeter-tw-zipcode-selector" data-city="#city" data-dist="#dist" placeholder="請輸入郵遞區號"/>
         <select id="city" placeholder="請選擇縣市"  name="city"></select>
          <select id="dist" placeholder="請選擇鄉鎮區" name="dist"></select><br>
          <input type="text" name="state"  value="" />
         </td> 
	</tr>
	<tr>
		<td>訂單電話:</td>
		<td><input name="tel" type="text" value="${orderVO.tel}"></td>
	</tr>
	<tr>
		<td>運送方法:</td>
		<td>
		<input type="radio" name="deliverymeth" value="1" id="deliv1"checked/>
        <label for="deliv1">宅急便</label>
       <input type="radio" name="deliverymeth" value="2" id="deliv2"/>
        <label for="deliv2">郵寄</label>
		</td>
	</tr>

</table>
<input type="hidden" name="action" value="update">
<input type="hidden" name="orderno" value="${orderVO.orderno}">
<input type="submit" value="送出修改">
</FORM>
<%@ include file="/back_end/footer.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>    
<script type="text/javascript">
function demtChange(){
	var objS = document.getElementById('paymentmeth');
    var value = objS.options[objS.selectedIndex].value;
    if (parseInt(value, 10) === 2){
    	document.getElementById("credit").style.visibility="visible";
    }else{
    	document.getElementById("credit").style.visibility="hidden";
    }
}
</script>
</body>
</html>