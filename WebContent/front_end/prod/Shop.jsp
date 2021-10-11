<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.prod.model.*"%>
<%
 	ProdService prodSvc = new ProdService();
    Object prodVO = request.getAttribute("listBySort")==null? prodSvc.getAllV():request.getAttribute("listBySort");
    session.setAttribute("listBySort", prodVO);
%>
<jsp:useBean id="prodsortSvc" scope="page" class="com.prodsort.model.ProdsortService" />


<html lang="en">

<head>
<meta charset="UTF-8">
<title>BELOVED 首頁</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free Website Template" name="keywords">
<meta content="Free Website Template" name="description">

<%@ include file="/front_end/pages/link.file"%>
<style>

.text p {
	width: 100%;
}

.carousel {
	background: #940a3f;
	min-height: 130px;
}

@media ( max-width : 991.98px) {
	.carousel {
		min-height: 0;
	}
}
</style>
<%@ include file="/front_end/pages/link.file"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/topbar.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/prod/css/Mall.css">
<style>

.text p {
	width: 100%;
}

.carousel {
	background: #940a3f;
	min-height: 130px;
}

@media ( max-width : 991.98px) {
	.carousel {
		min-height: 0;
	}
}
</style>


</head>

<body>
	<!-- Top Bar Start -->
	<jsp:include page="/front_end/topbar.jsp" flush="true" />
	<!-- Nav Bar End -->

	<div class="carousel">
		<div class="container-fluid">
			<div class="owl-carousel"></div>
		</div>
	</div>
	<!-- Carousel End -->

	<div class="container-fluid">
		<!-- Page Heading 修改以下●●●-->
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
							</c:forEach><br>
							<div><a href="<%=request.getContextPath()%>/front_end/prod/Shop.jsp"class="cool-link align-middle">全部商品</a></div>
						</div>
					</section>
					<h4 class="align-middle" style="color:black;">價格範圍</h4><br>
					<div class="price-range-block">
             <div id="slider-range" class="price-filter-range" name="rangeInput"></div><br>
  <form method="post" action="<%=request.getContextPath()%>/prod/prod.do">
  <input type="hidden" name="action" value="ListByPriceRange"/>
    <div style="margin-bottom:10px">
    
    <span>NT</span><input type="number" min=0 max="87000" oninput="validity.valid||(value='100');" id="min_price" class="price-range-field" name="minprice" required/>-
    <span>NT</span><input type="number" min=0 max="88000" oninput="validity.valid||(value='88000');" id="max_price" class="price-range-field" name="maxprice" required/>
  </div><br>
  
  <input type="submit" class="css_button" id="pricebtn" value="搜尋" style="padding:10px 93px; border-radius: 16px; background:#FFDCB9;"/>

  <div id="searchResults" class="search-results-block" style="color:blue;"></div>
</form>
</div>

			</div>
			</div>
		
		 <div class="col-lg-9 mt-5">
            <div class="product-show-option">
              <div class="row">
                <div class="col-lg-7 col-md-7">
                  <div class="select-option">
                  <h3>商品列表</h3>
                 
                  	<form action="<%=request.getContextPath()%>/prod/prod.do" method="post">
                  	<input type="hidden" name="action" value="SortByPrice">
                  	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                  	<input type="hidden" name="listBySort" value="${listBySort}">
                  	<select class="p-show"  id="p-show" name="pricesort">
                      <option value="0">預設排序</option>
                      <option value="1" >價格：低到高</option>
                      <option value="2">價格：高到低</option>
                     </select>
                     <input type="submit" value="搜尋"/> 
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
							
							<a href="<%=request.getContextPath()%>/front_end/prod/Prod_Detail.jsp?prodno=${prodVO.prodno}"class="icon badge badge-light p-2 rounded-0" no="1" onclick="history(this)" id="${prodVO.prodno}">
							<i class="fa fa-search" aria-hidden="true"></i></a> 
							<form method ="post" action ="<%=request.getContextPath()%>/prod/cart.do">
								<input type="hidden" name="prodno" value="${prodVO.prodno}">
							 	<a href="<%=request.getContextPath()%>/prod/cart.do?action=AddToCart&prodno=${prodVO.prodno}&quantity=1"
								class="icon badge badge-light p-2 rounded-0" no="2"><i class="fas fa-shopping-cart"><br></i></a>
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
						NT<span class="price-new" style="color: red;font-size:20px">${prodVO.price}</span>
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



	</div>

	<!-- Footer Start -->
	<jsp:include page="/front_end/footer.jsp" flush="true" />
	<!-- Footer End -->

	<!-- Back to top button -->
	<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

	<!-- Pre Loader -->
	<div id="loader" class="show">
		<div class="loader"></div>
	</div>

	<%@ include file="/front_end/pages/script.file"%>
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

	<script>
        // $(".nav-item.dropdown").click(()=>{
        //     console.log("ijoioi")
        // //    $(this).next().toggle();
        // // $(this).children().html("99999999999")
        // // $(".dropdown-menu.dropdown-menu-end").toggle();
        // //    console.log($(this).children(".dropdown-menu.dropdown-menu-end").toggle());
        // //    $(".dropdown-menu.dropdown-menu-end").hover();
        // //    $(".dropdown-menu.dropdown-menu-end").toggle();
        // })
        let newsText = [
            "大受歡迎",
            "名額有限",
            "成為受歡迎對象吧"
        ];
        let i = 0;

        let carouselNews = window.setInterval(() => {
            $("#news").html(newsText[i++ % 3]);

            if(i%2==0){
                $("#news").addClass("run");
            }else{
                $("#news").removeClass("run");
            }
        }, 4000);


    </script>
    
    <%
String mess=(String)request.getAttribute("message");
if(mess==null){
}else{%>
<script type="text/javascript">
Swal.fire("<%=mess%>");
</script>

<%}%>

<script>

$('#price-range-submit').hide();

$("#min_price,#max_price").on('change', function () {

  $('#price-range-submit').show();

  var min_price_range = parseInt($("#min_price").val());

  var max_price_range = parseInt($("#max_price").val());

  if (min_price_range > max_price_range) {
    $('#max_price').val(min_price_range);
  }

  $("#slider-range").slider({
    values: [min_price_range, max_price_range]
  });

});


$("#min_price,#max_price").on("paste keyup", function () {            
  $('#price-range-submit').show();

  var min_price_range = parseInt($("#min_price").val());

  var max_price_range = parseInt($("#max_price").val());

  if(min_price_range == max_price_range){

    max_price_range = min_price_range + 100;

    $("#min_price").val(min_price_range);		
    $("#max_price").val(max_price_range);
  }

  $("#slider-range").slider({
    values: [min_price_range, max_price_range]
  });

});


$(function () {
  $("#slider-range").slider({
    range: true,
    orientation: "horizontal",
    min: 100,
    max: 88000,
    values: [100, 88000],
    step: 100,

    slide: function (event, ui) {
      if (ui.values[0] == ui.values[1]) {
        return false;
      }

      $("#min_price").val(ui.values[0]);
      $("#max_price").val(ui.values[1]);
    }
  });

  $("#min_price").val($("#slider-range").slider("values", 0));
  $("#max_price").val($("#slider-range").slider("values", 1));

});

$("#slider-range,#price-range-submit").click(function () {

  var min_price = $('#min_price').val();
  var max_price = $('#max_price').val();

  $("#searchResults").text("搜尋的價格區間落在 " + min_price  +" "+ "和" + " "+ max_price);
});
function history(prodno){
	$.ajax({
		url:"<%=request.getContextPath()%>/prod/prod.do",
		type:"POST",
		data:{prodno: prodno.id,
			  action: "gethistory"	
		},
});
}

</script>
    
</body>

</html>