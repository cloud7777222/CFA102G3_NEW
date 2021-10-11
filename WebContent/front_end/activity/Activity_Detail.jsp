
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.activity.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<%
int actNo = Integer.parseInt(request.getParameter("actNo"));
ActivityService activitySvc = new ActivityService();
ActivityVO activityVO = activitySvc.getActivityDetail(actNo);

%>
<% int memberno = 3; %>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, shrink-to-fit=9">
		<meta name="description" content="Gambolthemes">
		<meta name="author" content="Gambolthemes">
		<title>Beloved</title>
		
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
		
		<!-- Map Box -->
		<script src='https://api.tiles.mapbox.com/mapbox-gl-js/v1.3.1/mapbox-gl.js'></script>
		<link href='https://api.tiles.mapbox.com/mapbox-gl-js/v1.3.1/mapbox-gl.css' rel='stylesheet' />
	
	</head>

	<body>		
		<!-- Header Start -->
		<header>
			<div class="container">				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<nav class="navbar navbar-expand-lg navbar-light bg-dark1 justify-content-sm-start">
							<a class="order-1 order-lg-0 ml-lg-0 ml-3 mr-auto" href="ViewActivity.jsp"><img src="images/logo.svg" alt=""></a>
							<button class="navbar-toggler align-self-start" type="button">
								<i class="fas fa-bars"></i>
							</button>
							<div class="collapse navbar-collapse d-flex flex-column flex-lg-row flex-xl-row justify-content-lg-end bg-dark1 p-3 p-lg-0 mt1-5 mt-lg-0 mobileMenu" id="navbarSupportedContent">
								<ul class="navbar-nav align-self-stretch">
									<li class="nav-item active">
										<a class="nav-link" href="<%=request.getContextPath()%>/front_end/activity/ViewActivity.jsp">Home <span class="sr-only">(current)</span></a>
									</li>
									
										
									</li>
									
								</ul>
								
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
		<main class="event-mp">	
			<div class="event-todo-thumbnail-area">
				<div class="todo-thumb event-bg-image event-bg-overlay" style="background-image:url(images/d3.jpg); width:100%;height:5%;"></div>
				<div class="event-todo-header">
					<div class="container">
						<div class="row">
							<div class="col-lg-6 col-md-12">							
								<div class="my-profile-dt">
									
									</div>
								</div>														
							</div>
							<div class="col-lg-6 col-md-12">		
								<ul class="comment-likes">
									<li>
										<a href="#" class="profile-likes">
											<i class="fas fa-heart"></i>
											Like <ins>251</ins>
										</a>
									</li>
									<li>
										<a href="#" class="profile-likes">
											<i class="fas fa-comment-alt"></i>
											Comment <ins>10</ins>
										</a>
									</li>
									<li>
										<a href="#" class="profile-likes">
											<i class="fas fa-bookmark"></i>
											Bookmark
										</a>
									</li>
									<li>
										<a href="#" class="profile-likes">
											<i class="fas fa-share-alt"></i>
											Share <ins>251</ins>
										</a>
									</li>
									<li class="dropdown">
										<a href="#" class="profile-likes dropdown-toggle-no-caret"  role="button" data-toggle="dropdown">
											<i class="fas fa-ellipsis-v"></i>
										</a>
										<div class="dropdown-menu post-rt-dropdown dropdown-menu-right">
											<a class="post-link-item" href="#">Hide</a>
											<a class="post-link-item" href="#">Copy Link</a>											
											<a class="post-link-item" href="#">Report</a>																									
										</div>
									</li>
								</ul>
							</div>											
						</div>
					</div>
				</div>
			</div>
			  
    
			<div class="event-dts">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div class="event-title">
							
								<marquee behavior=scroll style="color:HotPink">生活疲乏了嗎?來報名場活動邂逅心動的他~ </marquee>
											<div class="usr-pic">
												<img src="images/d1.jpg" alt="">
												
											</div>
										</div>	
												
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12">
							<ul class="event-buttons">
								<form method="post" action="<%=request.getContextPath()%>/Activity/mail.do">
								<input type="hidden" name="action" value="SendMail" id="<%=memberno%>">
								<input type ="submit" style="background-color:lightblue;" value="報名" onclick="sign()"/>
								
								</form>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="event-sections">
			
					<div class="username-dt dpbg-1">
											
												<img src="images/dating.jpg" alt="" style="display:block;width:60%;">
											
											</div>
						<div class="col-md-6 col-xs-12">
							<div class="event-itm1 full-width">
								<div class="event-item-heading">
									<i class="fas fa-bars"></i>
									Description
								</div>
								<div class="event-item-description">
								 <p><%=activityVO.getActDirection()%></p>
								</div>
							</div>
							
											</div>
							
																																	
							
							<div class="event-itm4 full-width">
								<div class="event-go-dt border-tb">
								<ul>
										<li>
											<div class="it-items">
												<i class="fas fa-map-marker-alt"></i>
												<div class="list-text-dt">
													<span>活動地點</span>
													 <p><%=activityVO.getActLocation()%></p>
												</div>
											</div>
										</li>
										<li>
											<div class="it-items">
												<i class="fas fa-calendar-alt"></i>
												<div class="list-text-dt">
													<span>活動時間</span>
													  <p><%=activityVO.getActDate()%></p>
												</div>
											</div>
										</li>
										<li>
											<div class="it-items">
												<i class="fas fa-clock"></i>
												<div class="list-text-dt">
													<span>Time</span>
													<ins>2 PM to 9 PM</ins>
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
							</div>		
							</div>					
							<div class="event-itm4 full-width">
								<div class="event-go-dt border-tb">
								<ul>
											<li>
											<div class="it-items">
												<i class="fas fa-cloud-sun"></i>
												<div class="list-text-dt">
													<span>報名人數上限</span>
													 <p><%=activityVO.getMaxParticipant()%></p>
												</div>
											</div>
										</li>
										<li>
											<div class="it-items">
												<i class="fas fa-cloud-sun"></i>
												<div class="list-text-dt">
													<span>報名人數下限</span>
													     <p><%=activityVO.getMinParticipant()%></p>
												</div>
											</div>
										</li>
											<li>
											<div class="it-items">
												<i class="fas fa-cloud-sun"></i>
												<div class="list-text-dt">
													<span>活動報名開始時間</span>
													       <p><%=activityVO.getActRegisterStartDate()%></p>
												</div>
											</div>
										</li>
										<li>
											<div class="it-items">
												<i class="fas fa-cloud-sun"></i>
												<div class="list-text-dt">
													<span>活動報名截止時間</span>
													       <p><%=activityVO.getActRegisterDeadLine()%></p>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="event-itm1 full-width">
								<div class="event-item-heading">
									<i class="fas fa-map-marker-alt"></i>
									Location
								</div>
								<div class="event-item-description">
									<div id="map"></div>
								</div>
							</div>							
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="event-itm1 full-width">
								<div class="event-item-heading">
									<i class="fas fa-comment-alt"></i>
									活動留言
								</div>
<!-- 								<div class="event-item-description event-cmt-left"> -->
<!-- 									<div class="event-post-comment"> -->
<!-- 										<div class="event-post-bg"> -->
<!-- 											<div class="commntr-dp"> -->
<!-- 												<img src="images/event-view/user-1.jpg" alt=""> -->
<!-- 											</div> -->
<!-- 											<form> -->
<!-- 												<input class="ecomment-post" type="text" placeholder="寫活動留言吧"> -->
<!-- 												<button class="event-post-btn" type="submit">Post Comment</button> -->
												<form action="liuyan.jsp" method="post"> 
    <table width="300" height="300" border="0" align="center">
     
           
       <tr height="30">
           <td width="80" align="right">會員姓名：</td>                          
           <td><input type="text" name="name"></td></tr>
     
       <tr height="30">
           <td width="80" align="right">活動名稱：</td>
           <td><input type="text" name="actName"></td></tr>
       <tr><td width="80" align="right">留言內容：</td>
          <td><textarea name="neirong" rows="4" cols="20"></textarea></td></tr>
       <tr><td height="40" align="center" colspan="2">
          <input type="submit" value="提交"></td></tr>
    </table>
 </form>
											</form>
										</div>
									</div>									
									
							</div>	
						</div>
					
				
			<
		</main>
		<!-- Body End -->			
		<!-- Footer Start -->
		<footer>
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<div class="footer-left">
							<ul>
							
							
								<li><a href="about.html">About</a></li>
								<li><a href="contact_us.html">Contact Us</a></li>								
							</ul>
						</div>
					</div>					
					<div class="col-lg-6 col-md-12">
						<div class="footer-right">
							<ul class="copyright-text">
								<li><a href="ViewActivity.html"><img src="images/logo-2.jpg" alt=""></a></li>
								<li><div class="ftr-1"><i class="far fa-copyright"></i> Beloved <a href="https://themeforest.net/user/gambolthemes"></a></div></li>
							</ul>
						</div>
					</div>
				</div>
			
			</div>
		</footer>
		<!-- Footer End -->			
		<!-- Scripts js -->
		<!-------JavaScript線上寄信 START-------->


<!-------JavaScript線上寄信 END-------->	
		<script src="js/jquery.min.js"></script>
		<script src="js/skills-search.js"></script>
		<script src="js/jquery.nice-select.js"></script>
		<script src="js/datepicker.min.js"></script>
		<script src="js/i18n/datepicker.en.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script src="vendor/OwlCarousel/owl.carousel.js"></script>		
		<script src="js/custom1.js"></script>						
		<script src="js/timer.js"></script>						
		
		<!-- Map Box JS -->
		<script src='https://unpkg.com/es6-promise@4.2.4/dist/es6-promise.auto.min.js'></script>
		<script src="https://unpkg.com/@mapbox/mapbox-sdk/umd/mapbox-sdk.min.js"></script>
		<script>
			mapboxgl.accessToken = 'pk.eyJ1IjoiZ2FtYm9sIiwiYSI6ImNqdm03bzYydDE2cW00YWwyeHprd3FqamcifQ.HBy4R4sRcXgbgn2OteqFkQ';
			var mapboxClient = mapboxSdk({ accessToken: mapboxgl.accessToken });
			mapboxClient.geocoding.forwardGeocode({
			query: 'Zhongli, Taiwan',
			autocomplete: false,
			limit: 1
			})
			.send()
			.then(function (response) {
			if (response && response.body && response.body.features && response.body.features.length) {
			var feature = response.body.features[0];
		 
			var map = new mapboxgl.Map({
			container: 'map',
			style: 'mapbox://styles/mapbox/streets-v11',
			center: [121,25],
			zoom: 10
			});				
  
			new mapboxgl.Marker()
			.setLngLat(feature.center)
			.addTo(map);
			}
			
			// Add zoom and rotation controls to the map.
			map.addControl(new mapboxgl.NavigationControl());
			});			
			
		</script>
		<script>
		function sign(){
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/activityorder/activityorder.do",
				data:{
					memberno:<%=memberno%>,
					action:"sign",
					actNo:<%=actNo%>
				},
			});
		}
		
		</script>
	</body>
	
</html>