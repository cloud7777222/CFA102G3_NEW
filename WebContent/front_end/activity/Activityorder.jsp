<%@page import="java.util.List"%>
<%@page import="com.activityorder.model.*"%>
<%@page import="com.activity.model.*"%>
<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, shrink-to-fit=9">
		<meta name="description" content="Gambolthemes">
		<meta name="author" content="Gambolthemes">
		<title>Beloved - My Activity History</title>
		
		<!-- Favicon Icon -->
		<link rel="icon" type="image/png" href="images/fav.png">
		
		<!-- Stylesheets -->
		<link href="css/responsive.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.min.css" rel="stylesheet">
		<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
		<link href="vendor/OwlCarousel/assets/owl.carousel.css" rel="stylesheet">
		<link href="vendor/OwlCarousel/assets/owl.theme.default.min.css" rel="stylesheet">			
	
	</head>
<%
	ActivityorderService activityorderSvc = new ActivityorderService();
    int memberno = ((MemberVO)session.getAttribute("memberVO")).getMemberNo();
    List<ActivityorderVO> list = request.getAttribute("listByActNo") == null? activityorderSvc.getAll():(List<ActivityorderVO>)request.getAttribute("listByActNo");
    ActivityService activitySvc = new ActivityService();
%>

	<body>		
		<!-- Header Start -->
		<header>
			<div class="container">				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<nav class="navbar navbar-expand-lg navbar-light bg-dark1 justify-content-sm-start">
							<a class="order-1 order-lg-0 ml-lg-0 ml-3 mr-auto" href="ViewActivity.jsp"><img src="" alt=""></a>
							<button class="navbar-toggler align-self-start" type="button">
								<i class="fas fa-bars"></i>
							</button>
							<div class="collapse navbar-collapse d-flex flex-column flex-lg-row flex-xl-row justify-content-lg-end bg-dark1 p-3 p-lg-0 mt1-5 mt-lg-0 mobileMenu" id="navbarSupportedContent">
								
									<li class="nav-item active">
										<a class="nav-link" href="ViewActivity.jsp">Home <span class="sr-only">(current)</span></a>
									</li>
									
												
						</nav>
						<div class="overlay"></div>
					</div>					
				</div>					
			</div>
		</header>
		<!-- Header End -->
		<!-- Body Start -->	
		<main class="dashboard-mp">	
			<div class="dash-todo-thumbnail-area1">
				<div class="todo-thumb1 dash-bg-image1 dash-bg-overlay" style="background-image:url(images/date.jpg);"></div>
				<div class="dash-todo-header1">
					<div class="container">
						<div class="row">
							<div class="col-lg-12 col-md-12">							
								<div class="my-profile-dash">
									<div class="my-dp-dash">
										<img src="images/man.jpg" alt="">
									</div>									
								</div>														
							</div>																		
						</div>
					</div>
				</div>
			</div>
			<div class="dash-dts">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div class="event-title">
								<div class="my-dash-dt">
									<h3>abe</h3>
									<span>abe@gmail.com</span>									
								</div>								
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12">
							<ul class="right-details">
								<li>
									<div class="my-all-evnts">
										<a href="ViewActivity.jsp">View Activity</a>
									</div>
								</li>
								<li>
									<div class="all-dis-evnt">
										<div class="dscun-txt">Activity</div>
										<div class="dscun-numbr">16</div>																	
									</div>
								</li>
								
								</li>								
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="dash-tab-links">
				<div class="container">
					<div class="row">
						
							</ul>
						</div>																		
					</div>
					<div class="booked-events mb-20">
						<div class="row">
							<div class="col-lg-12 col-md-12">
								<div class="checkout-heading">
									<h2 style="color:#66CDAA":>活動歷史訂單</h2>								
								</div>
								<%
                        	for(ActivityorderVO activityorderVO:list){
                        %>
								<div class="table-responsive table-brder">
									<table class="table">
										<thead>
											<tr>
												<th scope="col" style="font-size:16px; font-weight:500;">Activity</th>
										
											
												
											</tr>
										</thead>
										<tbody>
											<tr>
												<th scope="row">
													<div class="checkout-dt">
														<div class="check-img">
															<img src="images/d4.jpg" alt="">
														</div>
														<div class="evnt-dt-ckot">
														 <div>活動編號:<%=activityorderVO.getActNo()%>
														    <%
                             	
                             	int actno = activityorderVO.getActNo();
                                int Memberno =activityorderVO.getMemberNo();
                             
                             	ActivityVO activityVO = activitySvc.getActivityDetail(actno);
                             %>
													<br>	 <div style="float:left">	
                                     	<a href="<%=request.getContextPath()%>/front_end/activity/Activity_Detail.jsp?actno=<%=activityVO.getActNo()%>"><%=activityVO.getActName()%></a><br>
                                       
                                	</div><br>
																 <div style="float:left">	
                                     	<a href="<%=request.getContextPath()%>/front_end/activity/Activity_Detail.jsp?actno=<%=activityVO.getActNo()%>"><%=activityVO.getActLocation()%></a><br>
                                       
                                	</div><br>
															 <div style="float:left">	
                                     	<a href="<%=request.getContextPath()%>/front_end/activity/Activity_Detail.jsp?actno=<%=activityVO.getActNo()%>"><%=activityVO.getActDate()%></a><br>
                                       
                                	</div><br>
															<div class="lctn-dt"><i class="fas fa-map-marker-alt"></i> 中壢</div>
														</div>
													</div>
												</th>
												
												
											</tr>
											
										</tbody>
									</table>
								</div>						
							</div>											
						</div>
					</div>
				</div>
			</div>
		</main>
		  <%} %>
		<!-- Body End -->			
		<!-- Footer Start -->
		<footer>
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<div class="footer-left">
							<ul>
															
							</ul>
						</div>
					</div>					
					<div class="col-lg-6 col-md-12">
						<div class="footer-right">
							<ul class="copyright-text">
								<li><a href="ViewActivity.jsp"></a></li>
								<li><div class="ftr-1"><i class="far fa-copyright"></i> Beloved </div></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</footer>
		<!-- Footer End -->		
		<!-- Scripts js -->	
		<script src="js/jquery.min.js"></script>
		<script src="js/skills-search.js"></script>
		<script src="js/jquery.nice-select.js"></script>
		<script src="js/datepicker.min.js"></script>
		<script src="js/i18n/datepicker.en.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script src="vendor/OwlCarousel/owl.carousel.js"></script>		
		<script src="js/custom1.js"></script>
		
	</body>
	
</html>