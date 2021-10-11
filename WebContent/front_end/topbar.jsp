<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>topbar</title>

<style>
    #userDropdown img{
        width: 50px;
        height: 50px;
    }
    
    .dropdown-menu a:hover{
    	background-color: #FDBE33;
    }
    
/*     .navbar-nav{ */
/*     line-height: 47px;  */
/*     } */
    
/*     .navbar-nav,.nav-item,.nav-link.dropdown-toggle{ */
/* 	line-height: 47px;  */
/* 	height: 100%; */
/* 	vertial-align: middle; */
    }
</style>

</head>

<body>

	 <div class="top-bar d-none d-md-block">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                    <div class="top-bar-left">
                        <div class="text">
                            <i class="fas fa-bullhorn"></i>
                            <p>最新消息</p>
                        </div>
                        <div class="text">
                            <i class="far fa-comment-dots"></i>
                            <p id="news">加入BELOVE終結孤單!
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="top-bar-right">
                        <div class="social">
                            <a href="https://github.com/cloud7777222/CFA102G3_NEW"><i class="fab fa-github"></i></a>
                            <a href="<%=request.getContextPath()%>/front_end/index/index.jsp#contact"><i class="far fa-envelope"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Top Bar End -->

    <!-- Nav Bar Start -->
    <div class="navbar navbar-expand-lg bg-dark navbar-dark">
        <div class="container-fluid">
            <a href="<%=request.getContextPath()%>/front_end/index/index.jsp" class="navbar-brand">BELOVED</a>
            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                <div class="navbar-nav ml-auto">
                    <a href="<%=request.getContextPath()%>/front_end/index/index.jsp" class="nav-item nav-link active">Home</a>
                    <a href="<%=request.getContextPath()%>/front_end/index/index.jsp#about" class="nav-item nav-link">About</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Services</a>
                        <div class="dropdown-menu">
                            <a href="<%=request.getContextPath()%>/front_end/dateappoorder/dater.jsp" class="dropdown-item">交友約會</a>
<!--                             <a href="service.html" class="dropdown-item">Consulting</a> -->
                            <a href="<%=request.getContextPath()%>/front_end/activity/ViewActivity.jsp" class="dropdown-item">活動</a>
                        </div>
                    </div>
                    <a href="<%=request.getContextPath()%>/front_end/post/post_main.jsp" class="nav-item nav-link">論壇</a>
                    <a href="<%=request.getContextPath()%>/front_end/prod/Shop.jsp" class="nav-item nav-link">Shop</a>
                    <a href="<%=request.getContextPath()%>/front_end/index/index.jsp#contact" class="nav-item nav-link">Contact</a>

                    <div>
                    
                    	<c:if test="${not empty sessionScope.memberVO}">
<!-- 	                        <div class="nav-item dropdown"> -->
<!-- 	                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i -->
<!-- 	                                    class="fas fa-user fa-fw"></i></a> -->
<!-- 	                            <ul class="dropdown-menu"> -->
<!-- 	                                <li><a class="dropdown-item" href="#">Settings</a></li> -->
<!-- 	                                <li><a class="dropdown-item" href="#">Activity Log</a></li> -->
<!-- 	                                <li> -->
<!-- 	                                    <hr class="dropdown-divider" /> -->
<!-- 	                                </li> -->
<%-- 	                                <li><a class="dropdown-item">登出${sessionScope.memberVO.memberName}最新消息<%=((MemberVO) session.getAttribute("memberVO")).getMemberNo()%></a></li> --%>
<!-- 	                            </ul> -->
<!-- 	                        </div> -->
	                        
	                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Hi,${sessionScope.memberVO.memberName}</span>
                                <img class="img-profile rounded-circle"
                                    src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${sessionScope.memberVO.memberAccount}">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/member/memberProfileByMe.jsp">
                                    <i class="fas fa-user fa-fw fa-fw mr-2 text-gray-400"></i>
                                    	修改個人資料
                                </a>
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/memPersonalPage/memPersonalPage_main.jsp?memberNo=${sessionScope.memberVO.memberNo}&memberName=${sessionScope.memberVO.memberName}">
                                    <i class="fas fa-address-card fa-sm fa-fw mr-2 text-gray-400"></i>
                                    	個人BLOG
                                </a>
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/friend/browseMember.jsp">
                                    <i class="fas fa-users fa-sm fa-fw mr-2 text-gray-400"></i>
                                    	瀏覽其他使用者
                                </a>
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/prod/Cart.jsp">
                                    <i class="fas fa-shopping-cart fa-sm fa-fw mr-2 text-gray-400"></i>
                                  			 購物車
                                </a>
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/dateappoorder/index.jsp">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    	約會訂單
                                </a>
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/order/SearchOrder.jsp">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    	商品訂單
                                </a>
                                <div class="dropdown-divider"></div>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member"
                                    style="margin-bottom: 0px;" id="logout">
<%--                                     <input type="hidden" name="memberNo" value="${memberVO.memberNo}"> --%>
                                    <input type="hidden" name="action" value="logout">

<%--                                     <button type="submit" class="btn btn-info">${memberVO.memberNo}</button> --%>
                                </FORM>
                                <a class="dropdown-item"  onclick="$('#logout').submit()">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    	登出
                                </a>
                            </div>
                        </li>
	                        
                        </c:if>
                        
                        <c:if test="${ empty sessionScope.memberVO}">
	                        <div class="nav-item dropdown">
	                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><i
	                                    class="fas fa-user fa-fw"></i></a>
	                            <ul class="dropdown-menu">
	                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/member/addMember.jsp">註冊</a></li>
<!-- 	                                <li><a class="dropdown-item" href="#">Activity Log</a></li> -->
<!-- 	                                <li> -->
<!-- 	                                    <hr class="dropdown-divider" /> -->
<!-- 	                                </li> -->
	                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/member/logInMember.jsp"><i class="fas fa-sign-in-alt"></i>登入</a></li>
	                            </ul>
	                        </div>
                        </c:if>
                        
                    </div>

                </div>
            </div>
        </div>
    </div>

</body>
</html>