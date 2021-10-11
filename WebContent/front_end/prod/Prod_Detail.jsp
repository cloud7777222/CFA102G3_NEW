<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.prod.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<%
int prodno = Integer.parseInt(request.getParameter("prodno"));
ProdService prodSvc = new ProdService();
ProdVO prodVO = prodSvc.getProdDetail(prodno);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳情</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/prod/css/Prod_Detail.css">
</head>
<body>
<div class="container mt-5 mb-5">
    <div class="card">
        <div class="row g-0">
            <div class="col-md-6 border-end">
                <div class="d-flex flex-column justify-content-center">
                    <div class="main_image"> <img src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic1()%>" id="main_product_image" width="350"> </div>
                    <div class="thumbnail_images">
                        <ul id="thumbnail">
                            <li><img onclick="changeImage(this)" src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic1()%>" width="90"height="70"></li>
                            <li><img onclick="changeImage(this)" src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic2()%>" width="90"height="70"></li>
                            <li><img onclick="changeImage(this)" src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic3()%>" width="90"height="70"></li>
                            
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="p-3 right-side">
                	 <div>產品名稱<a href="<%=request.getContextPath()%>/front_end/prod/Shop.jsp" style="float:right;"><i class="fa fa-shopping-bag" aria-hidden="true"></i>返回商店</a></div>
                    <p class="pn"><%=prodVO.getProdname()%></p>
                    <div>產品介紹</div>
                    <p><%=prodVO.getIndroce()%></p>
                    <p>建議售價</p>	
                    <div>
                    <div class="ctpnpc">
                    <div class="pnpc"><%=prodVO.getPrice()%></div>
                    </div>
                    </div><br>
                  	
                    <p>數量<span>(最多一次加入三組到購物車)</span></p>
                  	<div class="form-group d-flex ">
                  		
                  		 <a class="btn btn-sm btn-des" onclick="des()" ><i class="fa fa-minus" aria-hidden="true"></i></a>
                  		<input type="text" id="quantity" class="form-control" value=1 readonly style="width:60px;">
                    	
                        <a class="btn btn-sm btn-incre"onclick="inc()" ><i class="fa fa-plus" aria-hidden="true"></i></a>
                     </div>   
                    </div>
                    <div class="buttons d-flex flex-row mt-5 gap-3">
                    <form action="<%=request.getContextPath()%>/prod/cart.do" method="post">
                    <input type="hidden" name="action" value="checkout">
                    <input type="hidden" name="quantity"id="quan1"> 
                    <input type="hidden" name="prodno" value="<%=prodVO.getProdno()%>">
                    <button class="btn btn-outline-dark">立即結帳</button>
                    </form>
                    
                	<form action="<%=request.getContextPath()%>/prod/cart.do" method="post">
                    <input type="hidden" name="action" value="AddToCart_Detail">
                    <input type="hidden" name="quantity"id="quan2">
                     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
                    <input type="hidden" name="prodno" value="<%=prodVO.getProdno()%>">
                    <button class="btn btn-dark" id="sub" onclick="addcart()">加入購物車</button> 
                    </form>
                     
                    </div> 
            </div>
        </div>
    </div>
</div>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<%
String mess=(String)request.getAttribute("message");
if(mess==null){
}else{%>
<script type="text/javascript">
Swal.fire("<%=mess%>");
</script>
<%}%>

<script type="text/javascript">
function changeImage(element) {
var main_prodcut_image = document.getElementById('main_product_image');
main_prodcut_image.src = element.src;

}
var quan = document.getElementById('quantity');
var quan1 = document.getElementById('quan1');
var quan2 = document.getElementById('quan2');
function inc(){
	if(quan.value < 3){
	quan.value++;}
	quan1.value = quan.value;
	quan2.value = quan.value;
}
function des(){
	if(quan.value > 1){
	quan.value = quan.value-1 ;}
	quan1.value = quan.value;
	quan2.value = quan.value;
}
quan1.value = quan.value;
quan2.value = quan.value;
</script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>