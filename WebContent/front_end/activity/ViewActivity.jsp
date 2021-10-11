<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%
ActivityService activitySvc = new ActivityService();
Object activityVO = request.getAttribute("list")==null? activitySvc.getAll():request.getAttribute("list");	
 	pageContext.setAttribute("list", activitySvc.getAll());
%>
<jsp:useBean id="activitytypeSvc" scope="page" class="com.activitytype.model.ActivitytypeService" />
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, shrink-to-fit=9">
		<meta name="description" content="Gambolthemes">
		<meta name="author" content="Gambolthemes">
		<title>Beloved Activity</title>
		
		<!-- Favicon Icon -->
		<link rel="icon" type="image/jpg" href="images/logo2.jpg">
		
		<!-- Stylesheets -->
		<link href="css/responsive.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.min.css" rel="stylesheet">
		<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
		<link href="vendor/OwlCarousel/assets/owl.carousel.css" rel="stylesheet">
		<link href="vendor/OwlCarousel/assets/owl.theme.default.min.css" rel="stylesheet">		
	
	</head>

	<body>
	 
		<!-- Header Start -->
		<header>
		


			<div class="container">				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<nav class="navbar navbar-expand-lg navbar-light bg-dark1 justify-content-sm-start">
							<a class="order-1 order-lg-0 ml-lg-0 ml-3 mr-auto" href="<%=request.getContextPath()%>/front_end/index/index.jsp"><img src="images/logo2.jpg" alt=""></a>
							<button class="navbar-toggler align-self-start" type="button">
								<i class="fas fa-bars"></i>
							</button>
							<div class="collapse navbar-collapse d-flex flex-column flex-lg-row flex-xl-row justify-content-lg-end bg-dark1 p-3 p-lg-0 mt1-5 mt-lg-0 mobileMenu" id="navbarSupportedContent">
								<ul class="navbar-nav align-self-stretch">
								
									<li class="nav-item active">
										<a class="nav-link" href="<%=request.getContextPath()%>/front_end/activity/ViewActivity.jsp">Home <span class="sr-only">(current)</span></a>
									</li>
									
							
							</ul>
						<div class="account order-1 dropdown">
								<a href="#" class="account-link dropdown-toggle-no-caret" role="button" data-toggle="dropdown"> 
									<div class="user-dp"><img src="images/logo.jpg" alt=""></div>
									<span></span>
									<i class="fas fa-angle-down"></i>
								</a>
								<div class="dropdown-menu account-dropdown dropdown-menu-right">
									<a class="link-item" href="<%=request.getContextPath()%>/front_end/activity/Activityorder.jsp">Activityorder</a>
									<a class="link-item" href="<%=request.getContextPath()%>/front_end/member/logInMember.jsp">重新登入</a>																	
								</div>
						
								
																	
								</div>
							</div>							
						</nav>
						<div class="overlay"></div>
					</div>					
				</div>					
			</div>
		</header>
		<!-- Header End -->
		<!-- Body Start -->	
		<main>	
			<div class="main-section">
				<div class="container">
					<div class="row">
						<div class="col-lg-3 col-md-5">
							<div class="main-left-sidebar">
								<div class="user-data full-width">
									<div class="user-profile">
										<div class="username-dt dpbg-1">
											<div class="usr-pic">
												<img src="images/d2.jpg" alt="">
											</div>
										</div>
										<div class="user-main-details">
											 <font color="#FF0000"><marquee direction=up behavior=alternate width=40 height=100>報</marquee></font>
    <font color="#009933"><marquee direction=up behavior=alternate width=40 height=60>名</marquee></font>
    <font color="#000080"><marquee direction=up behavior=alternate width=40 height=40>活</marquee></font>
    <font color="#FF00FF"><marquee direction=up behavior=alternate width=40 height=60>動</marquee></font>
    <font color="#3366CC"><marquee direction=up behavior=alternate width=40 height=70>吧</marquee></font>
										</div>
										<ul class="followers-dts">
											<li>
											
									</div>							
								</div>	
								<div class="user-data full-width">
									<div class="categories-left-heading">
										<h3>Categories</h3>							
									</div>
									<div class="categories-items">
										<a class="category-item" href="#"><i class="fas fa-music"></i>INTEREST</a>
										<a class="category-item" href="#"><i class="fas fa-flag"></i>AGE</a>
										<a class="category-item" href="#"><i class="fas fa-pen-nib"></i>CONDITION</a>
										<a class="category-item" href="#"><i class="fas fa-microphone-alt"></i>ATTENDANCE</a>						
									</div>
								</div>
								<div class="user-data full-width">
									<div class="categories-left-heading">
										<h4 style="color:#0088A8" >熱門活動</h4>	
																
									</div>
									<div class="sugguest-user">
										<div class="sugguest-user-dt">
											<img src="images/logo.jpg" alt=""></a>
										<h5 style="color:#66CDAA">戀在放映室</h5></a>
										</div>
									
									</div>
									<div class="sugguest-user">
										<div class="sugguest-user-dt">
										<img src="images/logo2.jpg" alt=""></a>
											<h5 style="color:#66CDAA">料理小廚神</h5></a>
										</div>
										
									</div>
									<div class="sugguest-user">
										<div class="sugguest-user-dt">
											<img src="images/logo.jpg" alt=""></a>
												<h5 style="color:#66CDAA">愛情密碼</h5></a>
										</div>
										
									</div>
									<div class="sugguest-user">
										<div class="sugguest-user-dt">
											<img src="images/logo2.jpg" alt=""></a>
												<h5 style="color:#66CDAA">醫起守護</h5></a>
										</div>
										
									</div>
									<div class="sugguest-user">
										<div class="sugguest-user-dt">
											<a href="user_dashboard_activity.html"><img src="images/logo.jpg" alt=""></a>
											<h5 style="color:#66CDAA">滾滾保齡球</h5></a>
										</div>
										
										
									</div>
								</div>								
							</div>
						</div>
						<div class="col-lg-6 col-md-7">						
							<div class="center-section">						
								<div class="main-search-bar">						
									<h2>Activity happening what your like</h2>
									<form>
										<div class="main-search-inputs">
											<div class="row no-gutters">
												<div class="col-lg-5 col-md-12 col-sm-12">
													<input class="search-form-input" type="text" placeholder="Search events by categories">													
												</div>
												<div class="col-lg-3 col-md-12 col-sm-12 border-lr">
													<select class="wide" style="display: none;">
														<option>Select </option>	
														<option>INTEREST</option>
														<option>AGE</option>
														<option>CONDITION</option>
														<option>ATTENDANCE</option>
																					
													</select>
												</div>
												<div class="col-lg-3 col-md-12 col-sm-12">
													<input class="search-form-input datepicker-here" data-language='en' type="text" placeholder="Select Date">
												</div>
												
											</div>
										</div>
									</form>
								</div>
							
								<div class="main-tabs">
									<ul class="nav nav-tabs" id="myTab" role="tablist">
										<li class="nav-item">
											<a href="#tab-upcoming" class="nav-link active" data-toggle="tab">Upcoming</a>
										</li>
										<li class="nav-item">
											<a href="#tab-trending" class="nav-link" data-toggle="tab">Trending</a>
										</li>
										<li class="nav-item">
											<a href="#tab-this-week" class="nav-link" data-toggle="tab">This Week</a>
										</li>										
									</ul>
										<c:forEach var="activityVO" items="${list}" varStatus="varStatusName">
											<div class="card mb-2  pro-item _box-border">
					
									<div class="tab-content">
										<div class="tab-pane active" id="tab-upcoming">
											<div class="main-posts">
												<div class="event-main-post">
													<div class="event-top">
														<div class="event-top-left">
															<a href="<%=request.getContextPath()%>/front_end/activity/Activity_Detail.jsp?actNo=${activityVO.actNo}"aria-hidden="true"></a><h4>${activityVO.actName}</h4></a>
														</div>
														<div class="event-top-right">
															
															<div class="post-dt-dropdown dropdown">
																<span class="dropdown-toggle-no-caret"  role="button" data-toggle="dropdown"><i class="fas fa-ellipsis-v"></i></span>
																<div class="dropdown-menu post-rt-dropdown dropdown-menu-right">
																	<a class="post-link-item" href="#">Hide</a>
																	<a class="post-link-item" href="#">Details</a>											
																	
																	<a class="post-link-item" href="#">Report</a>																									
																</div>
															</div>
														</div>
													</div>
													<div class="event-main-image">
														<div class="main-photo">
															<div class="photo-overlay"></div>
															<img src="images/d4.jpg" alt="">
															<div class="post-buttons">
																<div class="left-buttons">
																	
																	
																			
												
																</div>
							<div class="pro-wrap hover _com-icon _down-up _img-dark">
							<a href="<%=request.getContextPath()%>/front_end/activity/Activity_Detail.jsp?actNo=${activityVO.actNo}"class="icon badge badge-light p-2 rounded-0" no="1"><i class="main-btn-link" >VIEW</i></a> 

							<input type="hidden" name="actNo" value="${activityVO.actNo}">
 						
					</div>
																	
																</div>
															</div>
														</div>														
													</div>
													<div class="event-city-dt">
														<ul class="city-dt-list">
															<li>
																<div class="it-items">
																	<i class="fas fa-map-marker-alt"></i>
																	<div class="list-text-dt">
																		<span>活動地點</span>
																		<p>${activityVO.actLocation}</p>
																	</div>
																</div>
															</li>
															<li>
																<div class="it-items">
																	<i class="fas fa-calendar-alt"></i>
																	<div class="list-text-dt">
																		<span>活動日期</span>
																		<p>${activityVO.actDate}</p>
																	</div>
																</div>
															</li>
															<li>
																<div class="it-items">
																	<i class="fas fa-clock"></i>
																	<div class="list-text-dt">
																		<span>Time</span>
																		<ins>6 PM to 9 PM</ins>
																	</div>
																</div>
															</li>
															<li>
																<div class="it-items">
																	<i class="fas fa-cloud-sun"></i>
																	<div class="list-text-dt">
																		<span>Weather</span>
																		<ins>Clear</ins>
																	</div>
																</div>
															</li>
														</ul>
													</div>
													<div class="event-go-dt">
														<ul class="go-dt-list">
															<li>
																<div class="it-items">
																	<i class="fas fa-check" style="color:#a7a8aa;"></i>
																	<div class="list-text-dt">
																		<span>Going</span>
																		<ins>45</ins>
																	</div>
																</div>
															</li>
															<li>
																<div class="it-items">
																	<i class="fas fa-question-circle" style="color:#a7a8aa;"></i>
																	<div class="list-text-dt">
																		<span>MayBe</span>
																		<ins>120</ins>
																	</div>
																</div>
															</li>
															<li>
																<div class="it-items">
																	<i class="fas fa-times" style="color:#a7a8aa;"></i>
																	<div class="list-text-dt">
																		<span>Can't Go</span>
																		<ins>70</ins>
																	</div>
																</div>
															</li>															
														</ul>
													</div>
													<div class="like-comments">
														<div class="left-comments">
															<a href="#" class="like-item" title="Like">
																<i class="fas fa-heart"></i>
																<span><ins>Like</ins> 251</span>
															</a>
															<a href="#" class="like-item lc-left" title="Comment">
																<i class="fas fa-comment-alt"></i>
																<span><ins>Comment</ins> 10</span>
															
																
															</a>
														</div>
														<div class="right-comments">
															<a href="#" class="like-item" title="Share">
																<i class="fas fa-share-alt"></i>
																<span><ins>Share</ins> 21</span>
															</a>
														</div>
													</div>
													</c:forEach>
												</div>
												
												
												</div>
<!-- 												<div class="main-loader">													 -->
<!-- 													<div class="spinner"> -->
<!-- 														<div class="bounce1"></div> -->
<!-- 														<div class="bounce2"></div> -->
<!-- 														<div class="bounce3"></div> -->
<!-- 													</div>																										 -->
<!-- 												</div>												 -->
<!-- 											</div> -->
								
						
								
								
								
								
		</main>
		<!-- Body End -->			
		<!-- Footer Start -->
		<footer>
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<div class="footer-left">
							<ul>
							
								<li><a href="<%=request.getContextPath()%>/front_end/index/index.jsp#about">About</a></li>
								<li><a href="<%=request.getContextPath()%>/front_end/index/index.jsp#contact">Contact Us</a></li>								
							</ul>
						</div>
					</div>					
					<div class="col-lg-6 col-md-12">
						<div class="footer-right">
							<ul class="copyright-text">
								<li><a href="index.html"><img src="" alt=""></a></li>
								<li><div class="ftr-1"><i class="far fa-copyright"></i> Beloved <a href="https://themeforest.net/user/gambolthemes"></a></div></li>
							</ul>
						</div>
					</div>
				</div>
				
			</div>
		</footer>
		<!-- Footer End -->			
		<!-- Scripts js -->	
		

 		
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.nice-select.js"></script>
		<script src="js/datepicker.min.js"></script>
		<script src="js/i18n/datepicker.en.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script src="vendor/OwlCarousel/owl.carousel.js"></script>		
		<script src="js/custom1.js"></script>					

<!-- 	</body> -->
	
</html>