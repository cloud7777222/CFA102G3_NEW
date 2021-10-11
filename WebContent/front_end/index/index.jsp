<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.activity.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%	
	//抓會員人數
	MemberService memberService = new MemberService();
	int memberOfMenSize = memberService.getAllMember().stream()
			.filter(i->i.getMemberGender().toString().equals("1"))
			.collect(Collectors.toList()).size();
	int memberOfWomenSize = memberService.getAllMember().stream()
			.filter(i->i.getMemberGender().toString().equals("2"))
			.collect(Collectors.toList()).size();
	//抓總活動數
	ActivityService activityService = new ActivityService();
	int activitySize = activityService.getAll().size();
	
			
	AdService adService = new AdService();
	List<AdVO> list = adService.getAll().stream()
			.filter(i->i.getAdState()!=0)
			.filter(i->java.sql.Timestamp.valueOf(i.getDeadline().toString()+" 00:00:00").getTime()>=System.currentTimeMillis())
			.collect(Collectors.toList());
	pageContext.setAttribute("list", list);
	AdVO adVO = (AdVO) request.getAttribute("adVO");
	pageContext.setAttribute("date",new Date());
%>
<jsp:useBean id="adSvc" scope="page" class="com.ad.model.AdService" />

	
<html>

<head>
<meta charset="UTF-8">
<title>首頁</title>
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
.about-img{
height: 100%;
}

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
                        <img src="<%=request.getContextPath()%>/front_end/img/heart.jpg" alt="Image">
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
                            <a class="btn btn-custom" href="<%=request.getContextPath()%>/front_end/member/addMember.jsp">join Now</a>
                       
                        </div>
                    </div>
                </div>
                <c:forEach var="adVO_list" items="${list}" >
                <c:if test="${adVO_list.postTime<date}">
	                <div class="carousel-item">
	                    <div class="carousel-img">
	                        <img src="${adVO_list.adPic1 }" alt="Image">
	                    </div>
	                    <div class="carousel-text">
	                        <h1>${adVO_list.adTitle }</h1>
	                        <p style="color:white;width:70%;" id="ad${adVO_list.adPic1 }">${adVO_list.ad }</p>
	                        <div class="carousel-btn">
		                        <FORM METHOD="post"  onclick="$(this).submit()"
								ACTION="<%=request.getContextPath()%>/ad/ad.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="adNo" value="${adVO_list.adNo}">
								<input type="hidden" name="action" value="getOne_For_Display">
	                            	<a class="btn btn-custom" >查看廣告</a>
	                            </FORM>
	                        </div>
	                    </div>
	                </div>
	                </c:if>
                </c:forEach>
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
    <div class="about" id="about">
        <div class="container">
            <div class="row align-items-center">
                
                <div class="col-lg-12">
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
                                <a class="nav-link" data-toggle="pill" href="#tab-content-2">Together</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="pill" href="#tab-content-3">Vision</a>
                            </li>
                        </ul>

                        <div class="tab-content">
                            <div id="tab-content-1" class="container tab-pane active">
                            <pre>  近年來科技蓬勃發展 雖然改善了我們的生活卻疏遠了彼此之間的距離
                                漸漸地科技冷漠成了常態 想當然結交朋友也成了一大難題

                                製作一個平台提供活動及認識新朋友的機會，讓枯燥乏味的生活增添許多樂趣
                            </pre>
                              

                            </div>
                            <div id="tab-content-2" class="container tab-pane fade">
                                	<pre style="text-align:center;">「BELOVED和你一起開始，尋找愛。」
乾淨整潔、氣質大方的外在，
給人良好的第一印象，
但唯有彼此內在的契合，
更能融洽的長久相處。
我們不僅能給予外型上的建議，
更提供兩性相處的諮詢服務，
幫助大家建立良好的兩性觀念，
找尋合適伴侶過程中，
更瞭解自己，
進而找到相處自在的另一半。</pre>
                            </div>
                            <div id="tab-content-3" class="container tab-pane fade" style="text-align:center;">
                                2021 CFA102G3 製作
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
                <p>我們提供什麼服務?</p>
                <h2>我們相信能讓你的生活更加快樂</h2>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="far fa-heart"></i>
                        </div>
                        <div class="service-text">
                            <h3>約會</h3>
                            <p>尋找您感興趣的夥伴，進行預約約會吧!</p>
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
                            <p>分享您的生活，討論並解結問題吧!</p>
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
                            <p>交流互動，臉紅心跳，體驗令人興奮的活動吧!</p>
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
                            <p>加入好友並進行聊天，增加彼此的感情吧!</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="service-item">
                        <div class="service-icon">
                            <i class="fas fa-shopping-cart"></i>
                        </div>
                        <div class="service-text">
                            <h3>購物</h3>
                            <p>提供多樣化商品，讓你有更多選擇</p>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
    <!-- Service End -->


    <!-- Facts Start -->
    <div class="facts" data-parallax="scroll" style="background:url('<%=request.getContextPath()%>/front_end/img/offer.jpg') no-repeat 50%;object-fit: fill;">
        <div class="container">
            <div class="row">
            	<div class="col-lg-3 col-md-4">

                </div>
                <div class="col-lg-2 col-md-4">
                    <div class="facts-item">
                        <i class="fas fa-male"></i>
                        <div class="facts-text">
                            <h3 class="facts-plus" data-toggle="counter-up"><%=memberOfMenSize %></h3>
                            <p>男性會員數</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4">
                    <div class="facts-item">
                        <i class="fas fa-female"></i>
                        <div class="facts-text">
                            <h3 class="facts-plus" data-toggle="counter-up"><%=memberOfWomenSize %></h3>
                            <p>女性會員數</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4">
                    <div class="facts-item">
                        <i class="fas fa-chess"></i>
                        <div class="facts-text">
                            <h3 class="facts-plus" data-toggle="counter-up"><%=activitySize %></h3>
                            <p>舉辦過的活動</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4">

                </div>
            </div>
        </div>
    </div>
    <!-- Facts End -->


    

    


<!--     Event Start -->
<!--     <div class="event"> -->
<!--         <div class="container"> -->
<!--             <div class="section-header text-center"> -->
<!--                 <p>Upcoming Events</p> -->
<!--                 <h2>令人興奮的活動</h2> -->
<!--             </div> -->
<!--             <div class="row"> -->
<!--                 <div class="col-lg-6"> -->
<!--                     <div class="event-item"> -->
<!--                         <img src="img/event-1.jpg" alt="Image"> -->
<!--                         <div class="event-content"> -->
<!--                             <div class="event-meta"> -->
<!--                                 <p><i class="fa fa-calendar-alt"></i>01-Jan-45</p> -->
<!--                                 <p><i class="far fa-clock"></i>8:00 - 10:00</p> -->
<!--                                 <p><i class="fa fa-map-marker-alt"></i>New York</p> -->
<!--                             </div> -->
<!--                             <div class="event-text"> -->
<!--                                 <h3>Lorem ipsum dolor sit</h3> -->
<!--                                 <p> -->
<!--                                     Lorem ipsum dolor sit amet elit. Neca pretim miura bitur facili ornare velit non -->
<!--                                     vulpte liqum metus tortor -->
<!--                                 </p> -->
<!--                                 <a class="btn btn-custom" href="">Join Now</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-lg-6"> -->
<!--                     <div class="event-item"> -->
<!--                         <img src="img/event-2.jpg" alt="Image"> -->
<!--                         <div class="event-content"> -->
<!--                             <div class="event-meta"> -->
<!--                                 <p><i class="fa fa-calendar-alt"></i>01-Jan-45</p> -->
<!--                                 <p><i class="far fa-clock"></i>8:00 - 10:00</p> -->
<!--                                 <p><i class="fa fa-map-marker-alt"></i>New York</p> -->
<!--                             </div> -->
<!--                             <div class="event-text"> -->
<!--                                 <h3>Lorem ipsum dolor sit</h3> -->
<!--                                 <p> -->
<!--                                     Lorem ipsum dolor sit amet elit. Neca pretim miura bitur facili ornare velit non -->
<!--                                     vulpte liqum metus tortor -->
<!--                                 </p> -->
<!--                                 <a class="btn btn-custom" href="">Join Now</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!--     Event End -->


    


   

    <!-- Contact Start -->
    <div class="contact" id="contact">
        <div class="container">
            <div class="section-header text-center">
                <p>Contact us</p>
                <h2>聯絡我們</h2>
            </div>
            <div class="contact-img">
                <img style="background:url('<%=request.getContextPath()%>/front_end/img/contact.jpg') no-repeat 50% 20%;object-fit: fill;">
            </div>
            <div class="contact-form">
                <div id="success"></div>
                <form name="sentMessage" METHOD="post" ACTION="<%=request.getContextPath()%>/email/MailService">
                    <div class="control-group">
                        <input type="text" class="form-control" name="name" placeholder="Your Name" required="required"
                            data-validation-required-message="Please enter your name" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <input type="email" class="form-control" name="email" placeholder="Your Email" required="required"
                            data-validation-required-message="Please enter your email" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <input type="text" class="form-control" name="subject" placeholder="Subject" required="required"
                            data-validation-required-message="Please enter a subject" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <textarea class="form-control" name="message" placeholder="Message" required="required"
                            data-validation-required-message="Please enter your message"></textarea>
                        <p class="help-block text-danger"></p>
                    </div>
                    <div>
                        <button class="btn btn-custom" type="submit">Send Message</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Contact End -->


   
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