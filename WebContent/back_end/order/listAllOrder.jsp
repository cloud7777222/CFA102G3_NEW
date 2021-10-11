<%@page import="com.order.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全部訂單</title>
<%@ include file="/back_end/header.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<style>
body{
    background:#f5f5f5;
    margin-top:20px;
}
.card {
    border: none;
    -webkit-box-shadow: 1px 0 20px rgba(96,93,175,.05);
    box-shadow: 1px 0 20px rgba(96,93,175,.05);
    margin-bottom: 30px;
}
.table th {
    font-weight: 500;
    color: #827fc0;
}
.table thead {
    background-color: #f3f2f7;
}
.table>tbody>tr>td, .table>tfoot>tr>td, .table>thead>tr>td {
    padding: 14px 12px;
    vertical-align: middle;
}
.table tr td {
    color: #8887a9;
}
.thumb-sm {
    height: 32px;
    width: 32px;
}
.badge-soft-warning {
    background-color: rgba(248,201,85,.2);
    color: #f8c955;
}

.badge {
    font-weight: 500;
}
.badge-soft-primary {
    background-color: rgba(96,93,175,.2);
    color: #605daf;
}
</style>
</head>
<body>
<%
	OrderService orderSvc = new OrderService();
    List<OrderVO> list = null;
	if(request.getAttribute("listByCondition") != null){
	  list = (List<OrderVO>) request.getAttribute("listByCondition");
	}else{
	  list = orderSvc.getAll();
	}
	pageContext.setAttribute("list",list);
%>
<%@ include file="/back_end/sliderbar.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-xl-30">
            <div class="card">
                <div class="card-body">
                    <h5 class="header-title pb-2 mt-0">訂單管理</h5>
                   <div class="row">
                    <div class="input-group rounded col-3">
                    
   <form method="POST" action="<%=request.getContextPath()%>/order/order.do">
   <table>
   <tbody>
   <tr>
   <td>
   <input type="number" class="form-control rounded" placeholder="訂單編號搜尋" aria-label="Search"
   aria-describedby="search-addon" name="orderno" min="1" required />
   <input type="hidden" name="action" value="searchorderbyorderno" />
   </td>
   <td>
   <button class="submit">
   <i class="fas fa-search"></i>
   </button>
   </td>
   </tr>
   </tbody>
  </table>
  </form>
 </div>
<div class="input-group rounded col-3">
                    
   <form method="POST" action="<%=request.getContextPath()%>/order/order.do">
   <table>
   <tbody>
   <tr>
   <td>
   <input type="number" class="form-control rounded" placeholder="會員編號搜尋" aria-label="Search"
   aria-describedby="search-addon" name="memberno" min="1" required />
   <input type="hidden" name="action" value="searchorderbymemberno" />
   </td>
   <td>
   <button class="submit">
   <i class="fas fa-search"></i>
   </button>
   </td>
   </tr>
   </tbody>
  </table>
  </form>
  </div>
                    <div class="table-responsive">
                        <table class="table table-hover mb-0">
                            <thead>
                                <tr class="align-self-center">
                                    <th>訂單編號</th>
                                    <th>會員編號</th>
                                    <th>訂購者</th>
                                    <th>地址</th>
                                    <th>電話</th>
                                    <th>付款方式</th>
                                    <th>運送方式</th>
                                    <th>總價</th>
                                    <th>訂單狀態</th>
                                    <th>訂單商品詳情</th>
                                    <th>修改</th>
                                </tr>
                            </thead>
                            <%@ include file="/back_end/order/page1.file" %> 
							<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                            <tbody>
                                <tr>
                                    <td>${orderVO.orderno}</td>
                                    <td>${orderVO.memberno}</td>
                                    <td>${orderVO.orderer}</td>
                                    <td>${orderVO.address}</td>
                                    <td>${orderVO.tel}</td>
                                    <td>
                                    <c:if test="${orderVO.paymentmeth == 1}">貨到付款</c:if>
                                    <c:if test="${orderVO.paymentmeth == 2}">信用卡</c:if>
                                    <c:if test="${orderVO.paymentmeth == 3}">轉帳</c:if>
                                    </td>
                                    <td>
                                    <c:if test="${orderVO.deliverymeth == 1}">宅急便</c:if>
                                    <c:if test="${orderVO.deliverymeth == 2}">郵寄</c:if>
                                    </td>
                                    <td>
                                    ${orderVO.total}
                                    </td>
                                    <td>
                                    <c:if test="${orderVO.orderstate == 1}">
                                    <span class="badge badge-boxed badge-success">已付款</span>
                                    </c:if>
                                    <c:if test="${orderVO.orderstate == 2}">
                                    <span class="badge badge-boxed badge-danger">已取消</span>
                                    </c:if>
                                    <c:if test="${orderVO.orderstate == 3}">
                                    <span class="badge badge-boxed badge-warning">審核中</span>
                                    </c:if>
                                    <c:if test="${orderVO.orderstate == 4}">
                                    <span class="badge badge-boxed badge-success">已付款(無法取消)</span>
                                    </c:if>
                                    </td>
                                    <td>
                                    <a href="<%=request.getContextPath()%>/back_end/order/Order_Detail.jsp?orderno=${orderVO.orderno}"><i class="fa fa-search" aria-hidden="true"></i></a>
                                    </td>
                                    <td>
                                    	<form method="post" action="<%=request.getContextPath()%>/order/order.do">
                                    	<button type="submit" class="btn"><i class="fa fa-wrench" aria-hidden="true"></i></button>
			     						<input type="hidden" name="orderno"value="${orderVO.orderno}">
			     						<input type="hidden" name="action" value="getOne_For_Update">
                                    	</form>
                                    </td>
                                </tr>
                            
                            </tbody>
                             </c:forEach> 
                             
                        </table>
                        <%@ include file="/back_end/order/page2.file" %>  
                    </div>
                    <!--end table-responsive-->
                    
                    <a href="<%=request.getContextPath()%>/back_end/order/OrderReview.jsp" class="text-primary"> 審核訂單頁面 </a></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@ include file="/back_end/footer.jsp"%>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
 <%
String mess=(String)request.getAttribute("message");
if(mess==null){
}else{%>
<script type="text/javascript">
Swal.fire({text:"<%=mess%>",icon:"error"});
</script>
<%}%>
</body>
</html>