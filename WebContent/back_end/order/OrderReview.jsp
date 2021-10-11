<%@page import="com.order.model.*"%>
<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單審核</title>
<%@ include file="/back_end/header.jsp"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<style type="text/css">
body{
    background:#eee;    
}
.main-box.no-header {
    padding-top: 20px;
}
.main-box {
    background: #FFFFFF;
    -webkit-box-shadow: 1px 1px 2px 0 #CCCCCC;
    -moz-box-shadow: 1px 1px 2px 0 #CCCCCC;
    -o-box-shadow: 1px 1px 2px 0 #CCCCCC;
    -ms-box-shadow: 1px 1px 2px 0 #CCCCCC;
    box-shadow: 1px 1px 2px 0 #CCCCCC;
    margin-bottom: 16px;
    -webikt-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
}
.table a.table-link.danger {
    color: #e74c3c;
}
.label {
    border-radius: 3px;
    font-size: 0.875em;
    font-weight: 600;
}
.user-list tbody td .user-subhead {
    font-size: 0.875em;
    font-style: italic;
}
.user-list tbody td .user-link {
    display: block;
    font-size: 1.25em;
    padding-top: 3px;
    margin-left: 60px;
}
a {
    color: #3498db;
    outline: none!important;
}
.user-list tbody td>img {
    position: relative;
    max-width: 50px;
    float: left;
    margin-right: 15px;
}

.table thead tr th {
    text-transform: uppercase;
    font-size: 0.875em;
}
.table thead tr th {
    border-bottom: 2px solid #e7ebee;
}
.table tbody tr td:first-child {
    font-size: 1.125em;
    font-weight: 300;
}
.table tbody tr td {
    font-size: 0.875em;
    vertical-align: middle;
    border-top: 1px solid #e7ebee;
    padding: 12px 8px;
}
a:hover{
text-decoration:none;
}
</style>
</head>
<body>
<%
	OrderService orderSvc = new OrderService();
	int orderstate = 3;
	List<OrderVO> list = (List<OrderVO>)orderSvc.getOrderState(orderstate);
%>

<hr>
<%@ include file="/back_end/sliderbar.jsp"%>
<div class="container bootstrap snippets bootdey">
    <div class="row">
        <div class="col-lg-12">
            <div class="main-box no-header clearfix">
            <a href="<%=request.getContextPath()%>/back_end/order/listAllOrder.jsp">回訂單管理</a>
                <div class="main-box-body clearfix">
                    <div class="table-responsive">
                        <table class="table user-list">
                            <thead>
                                <tr>
                                <th><span>會員編號</span></th>
                                <th><span>訂單編號</span></th>
                                <th><span>訂單狀態</span></th>
                                <th><span>訂購人</span></th>
                                <th><span>電話</span></th>
                                <th><span>退貨原因</span></th>
                                <th>是否同意退貨</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            	for(OrderVO orderVO:list){
                            	MemberService memSvc = new MemberService();
                            	MemberVO memberVO = memSvc.getOneMember(orderVO.getOrderno());
                            %>
                                <tr>
                                    <td>
                                        <img src="https://bootdey.com/img/Content/user_1.jpg" alt="">
                                        <a href="#" class="user-link"><%=orderVO.getMemberno()%></a>
                                        <span class="user-subhead">Member</span>
                                    </td>
                                    <td><a href="<%=request.getContextPath()%>/back_end/order/Order_Detail.jsp?orderno=<%=orderVO.getOrderno()%>" class="orderlist-link"><%=orderVO.getOrderno()%></a></td>
                                    <td >
                                         <span style="color:orange;font-weight:bold;">審核中</span>
                                    </td>
                                    <td>
                                        <%=orderVO.getOrderer()%>
                                    </td>
                                    <td>
                                        <%=orderVO.getTel()%>
                                    </td>
                                    <td>
                                        <%=orderVO.getCancelreason()%>
                                    </td>
                                    <td style="width: 20%;">
                                        <a href="<%=request.getContextPath()%>/order/order.do?action=cancelorder&orderstate=2&orderno=<%=orderVO.getOrderno()%>" class="table-link text-info">
                                            <span class="fa-stack">
                                                <i class="fa fa-check fa-stack-2x" aria-hidden="true"></i>
                                            </span>
                                        </a>
                                        <a href="<%=request.getContextPath()%>/order/order.do?action=nocancelorder&orderstate=4&orderno=<%=orderVO.getOrderno()%>" class="table-link danger">
                                            <span class="fa-stack">
                                                <i class="fa fa-times fa-stack-2x" aria-hidden="true"></i>
                                            </span>
                                        </a>
                                    </td>
                                </tr>
                              <%}%>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/back_end/footer.jsp"%>
</body>
</html>