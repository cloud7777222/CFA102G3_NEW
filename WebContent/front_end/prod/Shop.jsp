<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.prod.model.*"%>
<%
 	ProdService prodSvc = new ProdService();
    Object prodVO = request.getAttribute("listBySort")==null? prodSvc.getAllV():request.getAttribute("listBySort");
	pageContext.setAttribute("listBySort", prodVO);
%>
<jsp:useBean id="prodsortSvc" scope="page" class="com.prodsort.model.ProdsortService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Beloved shop</title>
<%@include file="/includes/head.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/prod/css/Mall.css">
</head>


<nav class="navbar navbar-expand-lg navbar-light" >
 	<div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="<%=request.getContextPath()%>/back_end/prod/images/heart1.png" width="30" height="24" class="d-inline-block align-text-top">
      Beloved Shop
    </a>
     <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link " href="#"><i class="fa fa-home" aria-hidden="true"></i>Home</a></li>
        <li class="nav-item"><a class="nav-link " href="<%=request.getContextPath()%>/front_end/prod/Cart.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Cart</a></li>
    </ul>
</div>
 </div> 
  
</nav>

	<div class="container">
		<div class="row">
			<div class="col-3 mt-3">
				<div class="slid">
					<section class="ms-3 mt-2">
						<h4 class="align-middle" style="color:black;">商品類別</h4>
						<div class="text-muted text-uppercase mb-5 ">
							
							<c:forEach var="prodsortVO" items="${prodsortSvc.all}">
								<div ><br>
								<a href="<%=request.getContextPath()%>/prod/prod.do?action=getBySort_V&prodsortno=${prodsortVO.prodsortno}" class="cool-link align-middle">${prodsortVO.prodsortname}</a>
								</div>
							</c:forEach>
						</div>
					</section>
			</div>
			</div>
		
		 <div class="col-lg-9 mt-5">
            <div class="product-show-option">
              <div class="row">
                <div class="col-lg-7 col-md-7">
                  <div class="select-option">
                  <h3> 商品列表</h3>
                  	<form action="prod.do" method="post">
                  	<input type="hidden" name="action" >
 					<select class="p-show"  id="p-show">
                      <option >預設排序</option>
                      <option value="1" >價格：低到高</option>
                      <option value="2">價格：高到低</option>
                      </select>
                      
                  </form>
                  </div>
                </div>
             
     <div class="row">
		   <c:forEach var="prodVO" items="${listBySort}">
		    <div class="col-4 mt-4">
		  		<div class="card mb-2 pro-item _box-border" style="width: 16rem; margin-right:200px;" >
					<div class="pro-wrap hover _com-icon _down-up _img-dark">
						    <a href="#" class="pro-photo"><img
								src="<%=request.getContextPath()%>/back_end/prod/images/${prodVO.prodpic1}" alt="Card image cap" height=200
								class="card-img-top"></a>
							<a href="<%=request.getContextPath()%>/front_end/prod/Prod_Detail.jsp?prodno=${prodVO.prodno}"class="icon badge badge-light p-2 rounded-0" no="1"><i class="fa fa-search" aria-hidden="true"></i></a> 
							<form method ="post" action ="<%=request.getContextPath()%>/prod/cart.do">
								<input type="hidden" name="prodno" value="${prodVO.prodno}">
							 	<a href="<%=request.getContextPath()%>/prod/cart.do?action=AddToCart&prodno=${prodVO.prodno}&quantity=1"
								class="icon badge badge-light p-2 rounded-0" no="2" onclick="addcart()" ><i class="fas fa-shopping-cart"><br></i></a>
							</form>
					</div>
				
				<div class="card-body">
					<div class="pro-sort">
						<a href="#" class="text-secondary fz-2">商品類別:
							<c:forEach var="prodsortVO" items="${prodsortSvc.all}">
									<c:if test="${prodVO.prodsortno == prodsortVO.prodsortno}">
	                    	       ${prodsortVO.prodsortname}
                    	 			</c:if>
							</c:forEach>
						</a>
					</div>
					<h3 class="pro-title h6">
						<span class="text-basic">商品名稱:${prodVO.prodname}</span>
					</h3>
					<div class="pro-price fz-2">
						<span class="price-new" style="color: red;font-size:20px">${prodVO.price}</span>點數
					</div>
				</div>
			</div>
		 </div>
		</c:forEach>
    </div>

	</div>
	</div>
	</div>
	</div>
	</div>
	<%@include file="/includes/footer.jsp"%>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
function addcart(){
	Swal.fire('加入購物車');
}
</script>
</body>
</html>