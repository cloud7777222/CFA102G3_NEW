<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.ad.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdService adService = new AdService();
	List<AdVO> list = adService.getAll().stream()
			.filter(i->i.getAdState()!=0)
			.filter(i->java.sql.Timestamp.valueOf(i.getDeadline().toString()+" 00:00:00").getTime()>=System.currentTimeMillis())
			.collect(Collectors.toList());
	pageContext.setAttribute("list", list);
	AdVO adVO = (AdVO) request.getAttribute("adVO");
	request.setAttribute("date",new Date());
%>
<jsp:useBean id="adSvc" scope="page" class="com.ad.model.AdService" />

	
<html>

<head>
<meta charset="UTF-8">
<title>廣告頁面</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free Website Template" name="keywords">
<meta content="Free Website Template" name="description">

<%@ include file="/front_end/pages/link.file"%>
<style>

/* .text p { */
/* 	width: 100%; */
/* } */

/* .carousel { */
/* 	background: #940a3f; */
/* 	min-height: 130px; */
/* } */

/* @media ( max-width : 991.98px) { */
/* 	.carousel { */
/* 		min-height: 0; */
/* 	} */
/* } */

/* sub{ */
/* 	color: #FDBE33; */
/* 	font-size: 10px; */
/* } */

/* pre{ */
/* 	white-space: pre-wrap;   */
/* } */

/* a{ */
/* 	cursor: pointer; */
/* } */

</style>
</head>

<body>
	<!-- Top Bar Start -->
	<jsp:include page="/front_end/topbar.jsp" flush="true" />
	<!-- Nav Bar End -->

	<!-- Carousel Start -->
    <div class="carousel">
        <div class="container-fluid">
            <div class="owl-carousel">
                <div class="carousel-item">
                    <div class="carousel-img">
                        <img src="img/carousel-1.jpg" alt="Image">
                    </div>
                    <div class="carousel-text">
                        <h1>出去玩吧!</h1>


                        <p>你有多久沒有去認識一個新朋友了?</p>
                        <p>
				                            總是一個人無聊的聽歌追劇、一個人去看電影、一個人去吃飯、
				                            好希望有人和你一起分享這些無聊而又美好的一切....
                        </p>
                        <p>加入BELOVED~ 終結孤單~</p>


                        <div class="carousel-btn">
                            <a class="btn btn-custom" href="">join Now</a>
                            <a class="btn btn-custom btn-play" data-toggle="modal"
                                data-src="https://www.youtube.com/embed/DWRcNpR6Kdc" data-target="#videoModal">Watch
                                Video</a>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="carousel-img">
                        <img src="img/carousel-2.jpg" alt="Image">
                    </div>
                    <div class="carousel-text">
                        <h1>Get Involved with helping hand</h1>
                        <p>
                            Morbi sagittis turpis id suscipit feugiat. Suspendisse eu augue urna. Morbi sagittis, orci
                            sodales varius fermentum, tortor
                        </p>
                        <div class="carousel-btn">
                            <a class="btn btn-custom" href="">Donate Now</a>
                            <a class="btn btn-custom btn-play" data-toggle="modal"
                                data-src="https://www.youtube.com/embed/DWRcNpR6Kdc" data-target="#videoModal">Watch
                                Video</a>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="carousel-img">
                        <img src="img/carousel-3.jpg" alt="Image">
                    </div>
                    <div class="carousel-text">
                        <h1>Bringing smiles to millions</h1>
                        <p>
                            Sed ultrices, est eget feugiat accumsan, dui nibh egestas tortor, ut rhoncus nibh ligula
                            euismod quam. Proin pellentesque odio
                        </p>
                        <div class="carousel-btn">
                            <a class="btn btn-custom" href="">Donate Now</a>
                            <a class="btn btn-custom btn-play" data-toggle="modal"
                                data-src="https://www.youtube.com/embed/DWRcNpR6Kdc" data-target="#videoModal">Watch
                                Video</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Carousel End -->

    <!-- Video Modal Start-->
    <div class="modal fade" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <!-- 16:9 aspect ratio -->
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="" id="video" allowscriptaccess="always"
                            allow="autoplay"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Video Modal End -->


    <!-- About Start -->
    <div class="about">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-6">
                    <div class="about-img" data-parallax="scroll" data-image-src="img/about.jpg"></div>
                </div>
                <div class="col-lg-6">
                    <div class="section-header">
                        <p>About Us</p>
                        <h2>BELOVED 發展</h2>
                    </div>
                    <div class="about-tab">
                        <ul class="nav nav-pills nav-justified">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="pill" href="#tab-content-1">About</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="pill" href="#tab-content-2">Mission</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="pill" href="#tab-content-3">Vision</a>
                            </li>
                        </ul>

                        <div class="tab-content">
                            <div id="tab-content-1" class="container tab-pane active">
                                近年來科技蓬勃發展 雖然改善了我們的生活卻疏遠了彼此之間的距離
                                漸漸地科技冷漠成了常態 想當然結交朋友也成了一大難題

                                製作一個平台提供活動及認識新朋友的機會，讓枯燥乏味的生活增添許多樂趣

                            </div>
                            <div id="tab-content-2" class="container tab-pane fade">
                                服務流程 品牌故事 幸福案例 常見問題 關於我
                            </div>
                            <div id="tab-content-3" class="container tab-pane fade">
                                Aliquam dolor odio, mollis sed feugiat sit amet, feugiat ut sapien. Nunc eu dignissim
                                lorem. Suspendisse at hendrerit enim. Interdum et malesuada fames ac ante ipsum primis
                                in faucibus. Sed condimentum semper turpis vel facilisis. Nunc vel faucibus orci. Mauris
                                ut mauris rhoncus, efficitur nisi at, venenatis quam. Praesent egestas pretium enim sit
                                amet finibus. Curabitur at erat molestie, tincidunt lorem eget, consequat ligula.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- About End -->


    <!-- Service Start -->
    <div class="service">
        <div class="container">
            <div class="section-header text-center">
                <p>What We profer?</p>
                <h2>We believe that we can enjoy more lifes with you</h2>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="far fa-heart"></i>
                        </div>
                        <div class="service-text">
                            <h3>約會</h3>
                            <p>Lorem ipsum dolor sit amet elit. Phase nec preti facils ornare velit non metus tortor</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="far fa-newspaper"></i>
                        </div>
                        <div class="service-text">
                            <h3>論壇</h3>
                            <p>Lorem ipsum dolor sit amet elit. Phase nec preti facils ornare velit non metus tortor</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="fas fa-chess"></i>
                        </div>
                        <div class="service-text">
                            <h3>活動</h3>
                            <p>Lorem ipsum dolor sit amet elit. Phase nec preti facils ornare velit non metus tortor</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="fas fa-comments"></i>
                        </div>
                        <div class="service-text">
                            <h3>線上聊天</h3>
                            <p>Lorem ipsum dolor sit amet elit. Phase nec preti facils ornare velit non metus tortor</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="flaticon-home"></i>
                        </div>
                        <div class="service-text">
                            <h3>Residence Facilities</h3>
                            <p>Lorem ipsum dolor sit amet elit. Phase nec preti facils ornare velit non metus tortor</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="flaticon-social-care"></i>
                        </div>
                        <div class="service-text">
                            <h3>Social Care</h3>
                            <p>Lorem ipsum dolor sit amet elit. Phase nec preti facils ornare velit non metus tortor</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Service End -->


    <!-- Facts Start -->
    <div class="facts" data-parallax="scroll" data-image-src="img/facts.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="facts-item">
                        <i class="fas fa-male"></i>
                        <div class="facts-text">
                            <h3 class="facts-plus" data-toggle="counter-up">999</h3>
                            <p>男性會員數</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="facts-item">
                        <i class="fas fa-female"></i>
                        <div class="facts-text">
                            <h3 class="facts-plus" data-toggle="counter-up">400</h3>
                            <p>女性會員數</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="facts-item">
                        <i class="fas fa-users"></i>
                        <div class="facts-text">
                            <h3 class="facts-plus" data-toggle="counter-up">10000</h3>
                            <p>專家人數</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="facts-item">
                        <i class="fas fa-chess"></i>
                        <div class="facts-text">
                            <h3 class="facts-plus" data-toggle="counter-up">5000</h3>
                            <p>可報名活動</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Facts End -->


    <!-- Causes Start -->
    <div class="causes">
        <div class="container">
            <div class="section-header text-center">
                <p>Popular Causes</p>
                <h2>Let's know about charity causes around the world</h2>
            </div>
            <div class="owl-carousel causes-carousel">
                <div class="causes-item">
                    <div class="causes-img">
                        <img src="img/causes-1.jpg" alt="Image">
                    </div>
                    <div class="causes-progress">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="85" aria-valuemin="0"
                                aria-valuemax="100">
                                <span>85%</span>
                            </div>
                        </div>
                        <div class="progress-text">
                            <p><strong>Raised:</strong> $100000</p>
                            <p><strong>Goal:</strong> $50000</p>
                        </div>
                    </div>
                    <div class="causes-text">
                        <h3>Lorem ipsum dolor sit</h3>
                        <p>Lorem ipsum dolor sit amet elit. Phasell nec pretium mi. Curabit facilis ornare velit non
                            vulputa</p>
                    </div>
                    <div class="causes-btn">
                        <a class="btn btn-custom">Learn More</a>
                        <a class="btn btn-custom">Donate Now</a>
                    </div>
                </div>
                <div class="causes-item">
                    <div class="causes-img">
                        <img src="img/causes-2.jpg" alt="Image">
                    </div>
                    <div class="causes-progress">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="85" aria-valuemin="0"
                                aria-valuemax="100">
                                <span>85%</span>
                            </div>
                        </div>
                        <div class="progress-text">
                            <p><strong>Raised:</strong> $100000</p>
                            <p><strong>Goal:</strong> $50000</p>
                        </div>
                    </div>
                    <div class="causes-text">
                        <h3>Lorem ipsum dolor sit</h3>
                        <p>Lorem ipsum dolor sit amet elit. Phasell nec pretium mi. Curabit facilis ornare velit non
                            vulputa</p>
                    </div>
                    <div class="causes-btn">
                        <a class="btn btn-custom">Learn More</a>
                        <a class="btn btn-custom">Donate Now</a>
                    </div>
                </div>
                <div class="causes-item">
                    <div class="causes-img">
                        <img src="img/causes-3.jpg" alt="Image">
                    </div>
                    <div class="causes-progress">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="85" aria-valuemin="0"
                                aria-valuemax="100">
                                <span>85%</span>
                            </div>
                        </div>
                        <div class="progress-text">
                            <p><strong>Raised:</strong> $100000</p>
                            <p><strong>Goal:</strong> $50000</p>
                        </div>
                    </div>
                    <div class="causes-text">
                        <h3>Lorem ipsum dolor sit</h3>
                        <p>Lorem ipsum dolor sit amet elit. Phasell nec pretium mi. Curabit facilis ornare velit non
                            vulputa</p>
                    </div>
                    <div class="causes-btn">
                        <a class="btn btn-custom">Learn More</a>
                        <a class="btn btn-custom">Donate Now</a>
                    </div>
                </div>
                <div class="causes-item">
                    <div class="causes-img">
                        <img src="img/causes-4.jpg" alt="Image">
                    </div>
                    <div class="causes-progress">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="85" aria-valuemin="0"
                                aria-valuemax="100">
                                <span>85%</span>
                            </div>
                        </div>
                        <div class="progress-text">
                            <p><strong>Raised:</strong> $100000</p>
                            <p><strong>Goal:</strong> $50000</p>
                        </div>
                    </div>
                    <div class="causes-text">
                        <h3>Lorem ipsum dolor sit</h3>
                        <p>Lorem ipsum dolor sit amet elit. Phasell nec pretium mi. Curabit facilis ornare velit non
                            vulputa</p>
                    </div>
                    <div class="causes-btn">
                        <a class="btn btn-custom">Learn More</a>
                        <a class="btn btn-custom">Donate Now</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Causes End -->


    <!-- Donate Start -->
    <div class="donate" data-parallax="scroll" data-image-src="img/donate.jpg">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-7">
                    <div class="donate-content">
                        <div class="section-header">
                            <p>Donate Now</p>
                            <h2>Let's donate to needy people for better lives</h2>
                        </div>
                        <div class="donate-text">
                            <p>
                                Lorem ipsum dolor sit amet elit. Phasellus nec pretium mi. Curabitur facilisis ornare
                                velit non. Aliquam metus tortor, auctor id gravida, viverra quis sem. Curabitur non nisl
                                nec nisi maximus. Aenean convallis porttitor. Aliquam interdum at lacus non blandit.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="donate-form">
                        <form>
                            <div class="control-group">
                                <input type="text" class="form-control" placeholder="Name" required="required" />
                            </div>
                            <div class="control-group">
                                <input type="email" class="form-control" placeholder="Email" required="required" />
                            </div>
                            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                <label class="btn btn-custom active">
                                    <input type="radio" name="options" checked> $10
                                </label>
                                <label class="btn btn-custom">
                                    <input type="radio" name="options"> $20
                                </label>
                                <label class="btn btn-custom">
                                    <input type="radio" name="options"> $30
                                </label>
                            </div>
                            <div>
                                <button class="btn btn-custom" type="submit">Donate Now</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Donate End -->


    <!-- Event Start -->
    <div class="event">
        <div class="container">
            <div class="section-header text-center">
                <p>Upcoming Events</p>
                <h2>令人興奮的活動</h2>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="event-item">
                        <img src="img/event-1.jpg" alt="Image">
                        <div class="event-content">
                            <div class="event-meta">
                                <p><i class="fa fa-calendar-alt"></i>01-Jan-45</p>
                                <p><i class="far fa-clock"></i>8:00 - 10:00</p>
                                <p><i class="fa fa-map-marker-alt"></i>New York</p>
                            </div>
                            <div class="event-text">
                                <h3>Lorem ipsum dolor sit</h3>
                                <p>
                                    Lorem ipsum dolor sit amet elit. Neca pretim miura bitur facili ornare velit non
                                    vulpte liqum metus tortor
                                </p>
                                <a class="btn btn-custom" href="">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="event-item">
                        <img src="img/event-2.jpg" alt="Image">
                        <div class="event-content">
                            <div class="event-meta">
                                <p><i class="fa fa-calendar-alt"></i>01-Jan-45</p>
                                <p><i class="far fa-clock"></i>8:00 - 10:00</p>
                                <p><i class="fa fa-map-marker-alt"></i>New York</p>
                            </div>
                            <div class="event-text">
                                <h3>Lorem ipsum dolor sit</h3>
                                <p>
                                    Lorem ipsum dolor sit amet elit. Neca pretim miura bitur facili ornare velit non
                                    vulpte liqum metus tortor
                                </p>
                                <a class="btn btn-custom" href="">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Event End -->


    <!-- Team Start -->
    <div class="team">
        <div class="container">
            <div class="section-header text-center">
                <p>Meet Our Team</p>
                <h2>Awesome guys behind our charity activities</h2>
            </div>
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="team-item">
                        <div class="team-img">
                            <img src="img/team-1.jpg" alt="Team Image">
                        </div>
                        <div class="team-text">
                            <h2>Donald John</h2>
                            <p>Founder & CEO</p>
                            <div class="team-social">
                                <a href=""><i class="fab fa-twitter"></i></a>
                                <a href=""><i class="fab fa-facebook-f"></i></a>
                                <a href=""><i class="fab fa-linkedin-in"></i></a>
                                <a href=""><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="team-item">
                        <div class="team-img">
                            <img src="img/team-2.jpg" alt="Team Image">
                        </div>
                        <div class="team-text">
                            <h2>Adam Phillips</h2>
                            <p>Chef Executive</p>
                            <div class="team-social">
                                <a href=""><i class="fab fa-twitter"></i></a>
                                <a href=""><i class="fab fa-facebook-f"></i></a>
                                <a href=""><i class="fab fa-linkedin-in"></i></a>
                                <a href=""><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="team-item">
                        <div class="team-img">
                            <img src="img/team-3.jpg" alt="Team Image">
                        </div>
                        <div class="team-text">
                            <h2>Thomas Olsen</h2>
                            <p>Chef Advisor</p>
                            <div class="team-social">
                                <a href=""><i class="fab fa-twitter"></i></a>
                                <a href=""><i class="fab fa-facebook-f"></i></a>
                                <a href=""><i class="fab fa-linkedin-in"></i></a>
                                <a href=""><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="team-item">
                        <div class="team-img">
                            <img src="img/team-4.jpg" alt="Team Image">
                        </div>
                        <div class="team-text">
                            <h2>James Alien</h2>
                            <p>Advisor</p>
                            <div class="team-social">
                                <a href=""><i class="fab fa-twitter"></i></a>
                                <a href=""><i class="fab fa-facebook-f"></i></a>
                                <a href=""><i class="fab fa-linkedin-in"></i></a>
                                <a href=""><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Team End -->


    <!-- dater Start -->
    <div class="dater" data-parallax="scroll" data-image-src="img/dater.jpg">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-5">
                    <div class="dater-form">
                        <form>
                            <div class="control-group">
                                <input type="text" class="form-control" placeholder="Name" required="required" />
                            </div>
                            <div class="control-group">
                                <input type="email" class="form-control" placeholder="Email" required="required" />
                            </div>
                            <div class="control-group">
                                <textarea class="form-control" placeholder="Why you want to become a dater?"
                                    required="required"></textarea>
                            </div>
                            <div>
                                <button class="btn btn-custom" type="submit">Become a dater</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-lg-7">
                    <div class="dater-content">
                        <div class="section-header">
                            <p>Become A dater</p>
                            <h2>Let’s make a difference in the lives of others</h2>
                        </div>
                        <div class="dater-text">
                            <p>
                                Lorem ipsum dolor sit amet elit. Phasellus nec pretium mi. Curabitur facilisis ornare
                                velit non. Aliquam metus tortor, auctor id gravida, viverra quis sem. Curabitur non nisl
                                nec nisi maximus. Aenean convallis porttitor. Aliquam interdum at lacus non blandit.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- dater End -->


    <!-- Testimonial Start -->
    <div class="testimonial">
        <div class="container">
            <div class="section-header text-center">
                <p>Testimonial</p>
                <h2>What people are talking about our charity activities</h2>
            </div>
            <div class="owl-carousel testimonials-carousel">
                <div class="testimonial-item">
                    <div class="testimonial-profile">
                        <img src="img/testimonial-1.jpg" alt="Image">
                        <div class="testimonial-name">
                            <h3>Person Name</h3>
                            <p>Profession</p>
                        </div>
                    </div>
                    <div class="testimonial-text">
                        <p>
                            Lorem ipsum dolor sit amet elit. Phasel preti mi facilis ornare velit non vulputa. Aliqu
                            metus tortor, auctor id gravid vivera quis
                        </p>
                    </div>
                </div>
                <div class="testimonial-item">
                    <div class="testimonial-profile">
                        <img src="img/testimonial-2.jpg" alt="Image">
                        <div class="testimonial-name">
                            <h3>Person Name</h3>
                            <p>Profession</p>
                        </div>
                    </div>
                    <div class="testimonial-text">
                        <p>
                            Lorem ipsum dolor sit amet elit. Phasel preti mi facilis ornare velit non vulputa. Aliqu
                            metus tortor, auctor id gravid vivera quis
                        </p>
                    </div>
                </div>
                <div class="testimonial-item">
                    <div class="testimonial-profile">
                        <img src="img/testimonial-3.jpg" alt="Image">
                        <div class="testimonial-name">
                            <h3>Person Name</h3>
                            <p>Profession</p>
                        </div>
                    </div>
                    <div class="testimonial-text">
                        <p>
                            Lorem ipsum dolor sit amet elit. Phasel preti mi facilis ornare velit non vulputa. Aliqu
                            metus tortor, auctor id gravid vivera quis
                        </p>
                    </div>
                </div>
                <div class="testimonial-item">
                    <div class="testimonial-profile">
                        <img src="img/testimonial-4.jpg" alt="Image">
                        <div class="testimonial-name">
                            <h3>Person Name</h3>
                            <p>Profession</p>
                        </div>
                    </div>
                    <div class="testimonial-text">
                        <p>
                            Lorem ipsum dolor sit amet elit. Phasel preti mi facilis ornare velit non vulputa. Aliqu
                            metus tortor, auctor id gravid vivera quis
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Testimonial End -->


    <!-- Contact Start -->
    <div class="contact">
        <div class="container">
            <div class="section-header text-center">
                <p>Get In Touch</p>
                <h2>Contact for any query</h2>
            </div>
            <div class="contact-img">
                <img src="img/contact.jpg" alt="Image">
            </div>
            <div class="contact-form">
                <div id="success"></div>
                <form name="sentMessage" id="contactForm" novalidate="novalidate">
                    <div class="control-group">
                        <input type="text" class="form-control" id="name" placeholder="Your Name" required="required"
                            data-validation-required-message="Please enter your name" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <input type="email" class="form-control" id="email" placeholder="Your Email" required="required"
                            data-validation-required-message="Please enter your email" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <input type="text" class="form-control" id="subject" placeholder="Subject" required="required"
                            data-validation-required-message="Please enter a subject" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <textarea class="form-control" id="message" placeholder="Message" required="required"
                            data-validation-required-message="Please enter your message"></textarea>
                        <p class="help-block text-danger"></p>
                    </div>
                    <div>
                        <button class="btn btn-custom" type="submit" id="sendMessageButton">Send Message</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Contact End -->


    <!-- Blog Start -->
    <div class="blog">
        <div class="container">
            <div class="section-header text-center">
                <p>Our Blog</p>
                <h2>Latest news & articles directly from our blog</h2>
            </div>
            <div class="row">
                <div class="col-lg-4">
                    <div class="blog-item">
                        <div class="blog-img">
                            <img src="img/blog-1.jpg" alt="Image">
                        </div>
                        <div class="blog-text">
                            <h3><a href="#">Lorem ipsum dolor sit</a></h3>
                            <p>
                                Lorem ipsum dolor sit amet elit. Neca pretim miura bitur facili ornare velit non vulpte
                                liqum metus tortor
                            </p>
                        </div>
                        <div class="blog-meta">
                            <p><i class="fa fa-user"></i><a href="">Admin</a></p>
                            <p><i class="fa fa-comments"></i><a href="">15 Comments</a></p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="blog-item">
                        <div class="blog-img">
                            <img src="img/blog-2.jpg" alt="Image">
                        </div>
                        <div class="blog-text">
                            <h3><a href="#">Lorem ipsum dolor sit</a></h3>
                            <p>
                                Lorem ipsum dolor sit amet elit. Neca pretim miura bitur facili ornare velit non vulpte
                                liqum metus tortor
                            </p>
                        </div>
                        <div class="blog-meta">
                            <p><i class="fa fa-user"></i><a href="">Admin</a></p>
                            <p><i class="fa fa-comments"></i><a href="">15 Comments</a></p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="blog-item">
                        <div class="blog-img">
                            <img src="img/blog-3.jpg" alt="Image">
                        </div>
                        <div class="blog-text">
                            <h3><a href="#">Lorem ipsum dolor sit</a></h3>
                            <p>
                                Lorem ipsum dolor sit amet elit. Neca pretim miura bitur facili ornare velit non vulpte
                                liqum metus tortor
                            </p>
                        </div>
                        <div class="blog-meta">
                            <p><i class="fa fa-user"></i><a href="">Admin</a></p>
                            <p><i class="fa fa-comments"></i><a href="">15 Comments</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Blog End -->

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
    <script>
    function willCacel(dateOrderNo){
    	let no = dateOrderNo.toString();
    	console.log(no)
	    swal({
	        title: "確定要取消嗎?",
	        text: "約會編號["+dateOrderNo+"]取消後不可再做任何修改!",
	        icon: "warning",
	        buttons: true,
	        dangerMode: true,
	      }).then((willCancel) => {
	          if (willCancel) {
	            // 刪除
// 	            swal(no);
	            $.ajax({
				    url: "<%=request.getContextPath()%>/ad/ad.do",
				    type: "post",
				    data: { 
				    	action: "update",
				    	dateOrderNo: no,
				    	dateOrderState: 0,
				    	requestURL:"<%=request.getServletPath()%>",
				    	whichPage:1
				    	},
				    error:function () {
		        	  		swal("失敗了");
					    },
				    success: function (data) {

	        	  		swal("已取消請確認!");
				    }
				  });
	            
				
	          }
	        });
    	
    }
    </script>

</body>

</html>