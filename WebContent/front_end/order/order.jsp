<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.prod.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單頁面</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/order/css/order.css">
<style>
body {
  background: #eecda3;
  background: -webkit-linear-gradient(to right, #eecda3, #ef629f);
  background: linear-gradient(to right, #eecda3, #ef629f);
  min-height: 100vh;
}
#ck_button {
   padding: 10px 40px;
   border: 1px solid #C7C7C7;
   background: -webkit-gradient(linear, left top, left bottom, from(#E08020), to(#FF0000));
   background: -webkit-linear-gradient(top, #E08020, #FF0000);
   background: -moz-linear-gradient(top, #E08020, #FF0000);
   background: -ms-linear-gradient(top, #E08020, #FF0000);
   background: -o-linear-gradient(top, #E08020, #FF0000);
   background-color: #FF0000;
   box-shadow: 0px 7px 2px -5px #1A1A1A, inset 0px 0px 3px #9C0000;
   -webkit-box-shadow: 0px 7px 2px -5px #1A1A1A, inset 0px 0px 3px #9C0000;
   -moz-box-shadow: 0px 7px 2px -5px #1A1A1A, inset 0px 0px 3px #9C0000;
   -webkit-border-radius: 7px;
   -moz-border-radius: 7px;
   border-radius: 7px;
   text-shadow: #FF7A7A 1px 1px 0px;
   color: #FFFFFF;
   font-size: 27px;
   font-family: Courier New;
   text-decoration: none;
   font-weight: bold;
   -webkit-transition: 0.6s;
   -moz-transition: 0.6s;
   -o-transition: 0.6s;
   cursor: pointer;
   }
#ck_button:hover {
   background: none;
   background-color: #FF0000;
   box-shadow: 0px 0px 5px 0px #AAAAAA;
   -webkit-box-shadow: 0px 0px 5px 0px #AAAAAA;
   -moz-box-shadow: 0px 0px 5px 0px #AAAAAA;
   border: 1px solid #ffffff;
   color: #470000;
   }
#ck_button:active {
   top: 1px;
   position: relative;
   }
</style>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
<body>

<% @SuppressWarnings("unchecked")
Vector<ProdVO> buylist = (Vector<ProdVO>)session.getAttribute("shoppingcart");
MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
%>
<div class="container wrapper">
            <div class="row cart-head">
                <div class="container">
                <div class="row">
                    <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
                </div>
                <div class="row">
                    
                </div>
                </div>
            </div>    
            <div class="row cart-body">
                <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/order/order.do">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 col-md-push-6 col-sm-push-6">
                    <!--REVIEW ORDER-->
                    <div class="panel panel-info">
                        <div class="panel-heading"> 訂單預覽 <div class="pull-right"><small><a class="afix-1" href="<%=request.getContextPath()%>/front_end/prod/Cart.jsp">編輯購物車</a></small></div>
                        </div>
                        <div class="panel-body">
                        
                       	
                        <input type="hidden" name="action" value="insertWithOrderlist">
                        <input type="hidden" name="memberno" value="<%=memberVO.getMemberNo()%>">
                         
                        <%	
                    	int sum = 0;
              			for(int index = 0;index < buylist.size();index++){
            	 		 	ProdVO prodVO = buylist.get(index);
            	 		   sum=sum+(prodVO.getPrice()*prodVO.getQuantity());
            	 		  	request.setAttribute("total",sum);
            	 		%>
                            <div class="form-group">
              			
                            	<div class="col-sm-3 col-xs-3">
                                    <img class="img-responsive" src="<%=request.getContextPath()%>/back_end/prod/images/<%=prodVO.getProdpic1()%>" />
                                </div>
                                <div class="col-sm-6 col-xs-6">
                                    <div class="col-xs-12"><%=prodVO.getProdname()%></div>
                                    <div class="col-xs-12"><small>數量:<span><%=prodVO.getQuantity()%></span></small></div>
                                </div>
                                <div class="col-sm-3 col-xs-3 text-right">
                                    <h6><span>$<%=prodVO.getPrice()*prodVO.getQuantity()%></span></h6>
                                </div>
                             
                            </div>
                         
                            
                            <div class="form-group"><hr /></div>
                           <%} %>	    
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <strong>小計</strong>
                                    <div class="pull-right"><span>$</span><span><%=sum%></span></div>
                                </div>
                                <div class="col-xs-12">
                                    <small>運費</small>
                                    <div class="pull-right"><span style="color:red;">限時免運中</span></div>
                                </div>
                            </div>
                            <div class="form-group"><hr /></div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <strong>訂單總額</strong>
                                    <div class="pull-right"><span>$</span><span><%=sum%></span></div>
                                </div><br><br><input type="hidden" name="total" value="<%=sum%>"><br>
                                  <div><input type="submit"class="pull-right" value="去買單" id="ck_button"></div>
                            </div>
                        </div>
                    </div>
                    <!--REVIEW ORDER END-->
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 col-md-pull-6 col-sm-pull-6">
                    <!--SHIPPING METHOD-->
                    <div class="panel panel-info">
                        <div class="panel-heading">地址</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <h4>訂單地址</h4>
                                    <button type="button" onclick="getmeminfo()">帶入會員資訊</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>國家:台灣(目前不接受國外配送)</strong></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <strong>姓名:</strong>
                                    <input type="text" name="orderer" class="form-control" id="orderer" value="" />
                                </div>
                                
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>郵遞區號/縣市/行政區:</strong></div>
                                <div class="col-md-12">
                                   <input class="js-demeter-tw-zipcode-selector" data-city="#city" data-dist="#dist" placeholder="請輸入郵遞區號"/>
                                   <select id="city" placeholder="請選擇縣市"  name="city"></select>
                                   <select id="dist" placeholder="請選擇鄉鎮區" name="dist"></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>街道地址:</strong></div>
                                <div class="col-md-12">
                                    <input type="text" name="state" class="form-control" value="" />
                                </div>
                            </div>
                           <div class="form-group">
                                <div class="col-md-12"><strong>手機號碼:</strong></div>
                                <div class="col-md-12"><input type="text" name="tel" id="tel" class="form-control" value="" /></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>電子郵件地址:</strong></div>
                                <div class="col-md-12"><input type="email" name="email_address" id="email_address" class="form-control" value="" /></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>付款方式:</strong></div>
                                <div class="col-md-12">
                                     <select id="paymentmeth" name="paymentmeth" onchange="demtChange()">
                                     	<option value="1">貨到付款</option>
                                        <option value="2">信用卡</option>
                                        <option value="3">轉帳</option>
                                     </select>
                                </div>
                            </div>
                             <div class="form-group">
                                <div class="col-md-12"><strong>運送方式:</strong></div>
                                <div class="col-md-12">
                                    <input type="radio" name="deliverymeth" value="1" id="deliv1"checked/>
                                    <label for="deliv1">宅急便</label>
                                    <input type="radio" name="deliverymeth" value="2" id="deliv2"/>
                                    <label for="deliv2">郵寄</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--SHIPPING METHOD END-->
                    	
                    <!--CREDIT CART PAYMENT-->
                    <div class="panel panel-info">
                        <div class="panel-heading"><span><i class="fa fa-credit-card" aria-hidden="true"></i></span> 信用卡支付</div>
                        <div class="panel-body" id="credit" style="visibility:hidden; position:relative;">
                            <div class="form-group">
                                <div class="col-md-12"><strong>信用卡種類:</strong></div>
                                <div class="col-md-12">
                                    <select id="CreditCardType" name="CreditCardType" class="form-control">
                                        <option value="5">Visa</option>
                                        <option value="6">MasterCard</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>信用卡號:</strong></div>
                                <div class="col-md-12"><input type="text" class="form-control" name="creditcardnum" value="" /></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>卡片安全碼:</strong></div>
                                <div class="col-md-12"><input type="text" class="form-control" name="car_code" value="" /></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <strong>卡片到期日</strong>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <select class="form-control" name="">
                                        <option value="">Month</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                </select>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <select class="form-control" name="">
                                        <option value="">Year</option>
                                        <option value="2021">2021</option>
                                        <option value="2022">2022</option>
                                        <option value="2023">2023</option>
                                        <option value="2024">2024</option>
                                        <option value="2025">2025</option>
                                        <option value="2026">2026</option>
                                        <option value="2027">2027</option>
                                        <option value="2028">2028</option>
                                  </select>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <!--CREDIT CART PAYMENT END-->
                </div>
                
              
                </form>
            </div>
            <div class="row cart-footer">
        
            </div>
    </div>
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
function getmeminfo(){
	var orderer =  document.getElementById('orderer');
	orderer.value = "<%=memberVO.getMemberName()%>";
	var tel =  document.getElementById('tel');
	tel.value = "<%=memberVO.getMemberPhone()%>";
	var email_address = document.getElementById('email_address');
	email_address.value = "billy81318@hotmail.com.tw";
}
</script>
</body>
</html>