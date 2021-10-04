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
                            <a href="cfa102g3@gmail.com"><i class="far fa-envelope"></i></a>
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
            <a href="index.html" class="navbar-brand">BELOVED</a>
            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                <div class="navbar-nav ml-auto">
                    <a href="<%=request.getContextPath()%>/front_end/index/index.jsp" class="nav-item nav-link active">Home</a>
                    <a href="#about" class="nav-item nav-link">About</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Services</a>
                        <div class="dropdown-menu">
                            <a href="single.html" class="dropdown-item">Dating</a>
                            <a href="service.html" class="dropdown-item">Consulting</a>
                            <a href="team.html" class="dropdown-item">Activity</a>
                        </div>
                    </div>
                    <a href="causes.html" class="nav-item nav-link">Article</a>
                    <a href="event.html" class="nav-item nav-link">Shop</a>
                    <a href="#contact" class="nav-item nav-link">Contact</a>

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
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
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
	                                <li><a class="dropdown-item" href="#">Settings</a></li>
	                                <li><a class="dropdown-item" href="#">Activity Log</a></li>
	                                <li>
	                                    <hr class="dropdown-divider" />
	                                </li>
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