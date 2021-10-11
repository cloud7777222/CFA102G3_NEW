<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.order.model.*"%>
<%@page import="com.prod.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單商品詳情</title>
<%@ include file="/back_end/header.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
</head>
<%@ include file="/back_end/sliderbar.jsp"%>
<body>
<% 
	int orderno = Integer.parseInt(request.getParameter("orderno"));
	OrderlistService orderlistSvc = new OrderlistService();
	List<OrderlistVO> orderlist = orderlistSvc.getOrderDetail(orderno);
	ProdService prodSvc = new ProdService();
%>
<%
    OrderService orderSvc = new OrderService();
    OrderVO orderVO = orderSvc.getOneOrder(orderno); 
%>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

 <table class="table">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">商品名稱</th>
      <th scope="col">價格</th>
      <th scope="col">數量</th>
      <th scope="col">小計</th>
      <th scope="col">刪除</th>
      <th scope="col">更改數量</th>
    </tr>
  </thead>
  <tbody>
  <%
  	int i = 0;
	for(OrderlistVO orderlistVO:orderlist){
	int prodno = orderlistVO.getProdno();
	ProdVO prodVO = prodSvc.getProdDetail(prodno);
	i++;
 %>
    <tr>
      <th scope="row"><%=i%></th>
      <td><%=prodVO.getProdname()%></td>
      <td><%=prodVO.getPrice()%></td>
      <td><%=orderlistVO.getQuantity()%></td>
      <td><%=orderlistVO.getPrice()%></td>
      <td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orderlist/orderlist.do" style="margin-bottom: 0px;">
			  <input type="submit" value="刪除">
			  <input type="hidden" name="orderno"  value="<%=orderlistVO.getOrderno()%>">
			  <input type="hidden" name="prodno"  value="<%=prodVO.getProdno()%>">
			  <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			  <input type="hidden" name="action" value="delete">
		</FORM>
	  </td>
	  <td>
	  	 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="<%=prodVO.getProdno()%>">更改數量
    	</button>
    	 <input type="hidden" id="orderno<%=i%>"  value="<%=orderlistVO.getOrderno()%>">
		
	  </td>

    </tr>
<%} %>
  </tbody>
	   <tfoot>
   	              <div>訂單金額:<span style="color:red;"><%=orderVO.getTotal()%></span></div>
   	</tfoot>
</table>
<div class="pt-3 border-top text-left"><a href="<%=request.getContextPath()%>/back_end/order/listAllOrder.jsp" class="text-primary">返回訂單管理</a></div>
<div class="container my-3">
   
   

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">更改數量</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
        <div class="modal-body">
        <form METHOD="post" ACTION="<%=request.getContextPath()%>/orderlist/orderlist.do">
        		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
        	 	<input type="hidden" id="orderno" name="orderno" >
				<input type="hidden" id="prodno" name="prodno">
			  <input type="hidden" name="action" value="changeQuan">
			  <div class="mb-3">
			    <label for="recipient-name" class="col-form-label"><span></span></label>
			   </div>
          	<div class="mb-3">
            <label for="recipient-name" class="col-form-label">數量(1~9):</label>
             <input type="number" name="quantity" min="1" max="9">
            </div>
          		<div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">返回</button>
                    <button type="submit" class="btn btn-primary">確認</button>
                </div>
         </form>       
         </div>
         </div>
        </div>

        <div class="pt-3 border-top text-right"><a href="<%=request.getContextPath()%>/back_end/order/listAllOrder.jsp" class="text-primary">返回訂單管理 </a></div>
    	
    </div>
</div>
  <%@ include file="/back_end/footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
var orderno = document.getElementById("orderno<%=i%>").value;
var ordernomodel = document.getElementById("orderno");
ordernomodel.value = orderno;

$('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) 
    var prodnomodel = button.data('whatever') 
    var modal = $(this)
    modal.find('.modal-body label span').text('商品編號:' + prodnomodel)
    var prodnomodel1 = document.getElementById("prodno");
    prodnomodel1.value = prodnomodel;
})

</script>
</body>
</html>