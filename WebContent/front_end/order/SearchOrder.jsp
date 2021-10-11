<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.order.model.*"%>
<%@page import="com.prod.model.*"%>
<%@page import="com.member.model.*"%>

<%
	OrderService orderSvc = new OrderService();
    OrderlistService orderlistSvc = new OrderlistService();
    MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    int memberno = memberVO.getMemberNo();

    List<OrderVO> list = request.getAttribute("listByOrderstate") == null? orderSvc.getAllByMno(memberno):(List<OrderVO>)request.getAttribute("listByOrderstate");
    ProdService prodSvc = new ProdService();
    
    %>   

<html lang="en">

<head>
<meta charset="UTF-8">
<title>查詢訂單</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free Website Template" name="keywords">
<meta content="Free Website Template" name="description">

<%@ include file="/front_end/pages/link.file"%>

<style type="text/css">
.top-bar{
    background: #940a3f;
    margin: 0;
    border: 0;
}
</style>
<%@ include file="/front_end/pages/link.file"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/topbar.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/order/css/SearchOrder.css">
<link rel="stylesheet" href="bower_components/sweetalert2/dist/sweetalert2.min.css">

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

.footer {
    width: 100%;
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
		
    <div class="row">
        <div class="col-md-3">
            <div class="osahan-account-page-left shadow-sm bg-white h-100">
                <div class="border-bottom p-4">
                    <div class="osahan-user text-center">
                        <div class="osahan-user-media">
                            <img class="mb-3 rounded-pill shadow-sm mt-1" src="<%=request.getContextPath()%>/GetPhoto?memberAccount=<%=memberVO.getMemberAccount()%>" alt="gurdeep singh osahan">
                            <div class="osahan-user-media-body">
                                <h6 class="mb-2"><%=memberVO.getMemberName()%></h6>
                                <p class="mb-1"><%=memberVO.getMemberPhone()%></p>
                                <p><%=memberVO.getMemberEmail()%></p>
                                <div class="align-items-center input-group rounded">
                                <form method ="POST" action="<%=request.getContextPath()%>/order/order.do" class="row">
                                <input type="number" class="col-9 form-control rounded" placeholder="訂單搜尋" aria-label="Search"
                                 name="orderno" required min="1" />
                                 <button type="submit" class="col-3 btn btn-outline-primary"><i class="fas fa-search"></i></button>
                             	<input type="hidden" name="action" value="searchorder"/>
                                <input type="hidden" name="memberno" value="<%=memberVO.getMemberNo()%>"/>
                               </form>
                              </div>
							</div>
                        </div>
                    </div>
                </div>
                <ul class="nav nav-tabs flex-column border-0 pt-4 pl-4 pb-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link" id="orders-tab"  href="<%=request.getContextPath()%>/front_end/order/SearchOrder.jsp" role="tab" aria-controls="orders" aria-selected="false">全部</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="orders-tab1"  href="<%=request.getContextPath()%>/order/order.do?action=getOrderStateV&orderstate=1&memberno=<%=memberVO.getMemberNo()%>" role="tab" aria-controls="orders" aria-selected="false">已付款</a>
                    </li>
                     <li class="nav-item">
                        <a class="nav-link" id="orders-tab2"  href="<%=request.getContextPath()%>/order/order.do?action=getOrderStateV&orderstate=3&memberno=<%=memberVO.getMemberNo()%>" role="tab" aria-controls="orders" aria-selected="false">審核中</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="orders-tab3"  href="<%=request.getContextPath()%>/order/order.do?action=getOrderStateV&orderstate=2&memberno=<%=memberVO.getMemberNo()%>" role="tab" aria-controls="orders" aria-selected="false">已取消</a>
                    </li>
                </ul>
            </div>
        </div>	
        <div class="col-md-9">
            <div class="osahan-account-page-right shadow-sm bg-white p-4 h-100">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane  fade  active show" id="orders" role="tabpanel" aria-labelledby="orders-tab">
                        <h4 class="font-weight-bold mt-0 mb-4">歷史訂單</h4>
                        <%
                        	for(OrderVO orderVO:list){
                        %>
                        <div class="bg-white card mb-4 order-list shadow-sm">
                               <div class="gold-members p-4" name="orderstate">
                                <div>訂單編號:<%=orderVO.getOrderno()%>
                                <span class="float-right text-info">訂單成立時間:<%=orderVO.getOrderdate() %></span>
                                </div>
                                   <div class="gold-members p-4">
                                   <%if(orderVO.getOrderstate() == 1){ %>
                                   <span class="float-right"style="color:darkgreen;font-weight:bold;">●已付款</span></div>
                                 <%}else if(orderVO.getOrderstate() == 2){ %> 
                                  <span class="float-right"style="color:red;font-weight:bold;">●已取消</span></div>
                                  <%}else if(orderVO.getOrderstate() == 3){%>
                                   <span class="float-right"style="color:orange;font-weight:bold;">●審核中</span></div>
                                 <%}else if (orderVO.getOrderstate() == 4){%>
                                 	<span class="float-right"style="color:darkgreen;font-weight:bold;">●已付款(不可取消)</span></div>
                                 <%}%>
                                 <div class="media">
                   
                                <div class="media-body">
                             <%
                             	int orderno = orderVO.getOrderno();
                             	List<OrderlistVO> orderlist = orderlistSvc.getOrderDetail(orderno); 
                             	for(OrderlistVO orderlistVO:orderlist){
                             	int prodno = orderlistVO.getProdno();
                             	ProdVO prodVO = prodSvc.getProdDetail(prodno);
                             %>   
                                <div style="display:inline-block">
                                	 <div style="float:left">
                                        <a href="<%=request.getContextPath()%>/front_end/prod/Prod_Detail.jsp?prodno=<%=prodVO.getProdno()%>"><img class="mr-4" style=""src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic1() %>" alt="Generic placeholder image"></a>
                                     </div>
                                     
                                     <div style="float:right">	
                                     	<a href="<%=request.getContextPath()%>/front_end/prod/Prod_Detail.jsp?prodno=<%=prodVO.getProdno()%>"><%=prodVO.getProdname()%></a><br>
                                       	單價:<%=prodVO.getPrice()%> <br>
                                       	x<%=orderlistVO.getQuantity()%>
                                	</div>
                                	
                               </div>	 	
                                	<br>   
                                  <%} %>  	       
                                      	<hr>
                                  		<div class="float-right">
                                  		
                                         <a class="btn btn-sm btn-outline-primary" href="<%=request.getContextPath()%>/prod/cart.do?action=AddToCartList&orderno=<%=orderVO.getOrderno()%>">再買一次</a>
                                        
             							<%if(orderVO.getOrderstate() == 1){%>
                                           <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="<%=orderVO.getOrderno()%>">取消訂單</button>
                                        <%}else if(orderVO.getOrderstate() >= 2){ %>
                                        <a class="btn btn-sm btn-primary disabled" >取消訂單</a>  
                                        <%}%>
                                        </div>
                                        <p class="mb-0 text-black text-primary pt-2"><span class="text-black font-weight-bold"> 訂單金額:</span>NT<%=orderVO.getTotal() %>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                           <%} %>  
                    </div>
        
    </div>
</div>
 <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">取消訂單</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
        <div class="modal-body">
         <form METHOD="post" ACTION="<%=request.getContextPath()%>/order/order.do">
        		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
        	 	<input type="hidden" id="orderno" name="orderno" >
				<input type="hidden" id="memberno" name="memberno" value="<%=memberVO.getMemberNo()%>">
			    <input type="hidden" name="action" value="cancelbymember">
			  <div class="mb-3">
			    <label for="recipient-name" class="col-form-label"><span></span></label>
			  </div>
			  <div class="mb-3">
			       會員編號:<%=memberno%>
			  </div>
              <div class="form-group">
              <label for="message-text" class="col-form-label">取消原因(上限150個字):</label>
              <textarea class="form-control" id="message-text" name="cancelreason"></textarea>
          </div>
		  <div class="modal-footer">
               <button type="button" class="btn btn-secondary" data-dismiss="modal">返回</button>
               <button type="submit" class="btn btn-primary">確認</button>
          </div>
         </form>       
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
    
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
 <%
String mess=(String)request.getAttribute("message");
if(mess==null){
}else{%>
<script type="text/javascript">
Swal.fire({text:"<%=mess%>",icon:"error"});
</script>
<%}%>
<script type="text/javascript">
$('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) 
    var ordernomodel = button.data('whatever') 
    var modal = $(this)
    modal.find('.modal-body label span').text('訂單編號:' + ordernomodel)
    var ordernomodel1 = document.getElementById("orderno");
    ordernomodel1.value = ordernomodel;
})

</script>
    
</body>

</html>