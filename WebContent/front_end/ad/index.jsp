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
	pageContext.setAttribute("date",new Date());
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

.text p {
	width: 100%;
}

.carousel {
	background: #940a3f;
	min-height: 130px;
}

@media ( max-width : 991.98px) {
	.carousel {
		min-height: 0;
	}
}

sub{
	color: #FDBE33;
	font-size: 10px;
}

pre{
	white-space: pre-wrap;  
}

a{
	cursor: pointer;
}

</style>
</head>

<body>
	<!-- Top Bar Start -->
	<jsp:include page="/front_end/topbar.jsp" flush="true" />
	<!-- Nav Bar End -->

	<div class="carousel">
		<div class="container-fluid">
			<div class="owl-carousel"></div>
		</div>
	</div>
	<!-- Carousel End -->

	<div class="container-fluid">
		<!-- Page Heading 修改以下●●●-->
		

		
	 <!-- Single Post Start-->
        <div class="single">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="single-content">
                            <img src="${adVO.adPic1}" />
                            <h2>${adVO.adTitle}<sub>廣告編號:${adVO.adNo}</sub></h2>
                            <p><i class="far fa-clock"></i>${adVO.postTime } - ${adVO.deadline }</p>
                            <pre>${adVO.ad}</pre>
                            
                        </div>
                        
                        
                       

                        
                    </div>

                    <div class="col-lg-4">
                        <div class="sidebar">
                            

                            <div class="sidebar-widget">
                                <h2 class="widget-title">其他廣告</h2>
                                <div class="recent-post">
                                
                                	<c:forEach var="adVO_list" items="${list}" >
                                		<c:if test="${adVO_list.postTime<date}">
	                                		<FORM METHOD="post"  onclick="$(this).submit()"
												ACTION="<%=request.getContextPath()%>/ad/ad.do"
												style="margin-bottom: 0px;">
												<input type="hidden" name="adNo" value="${adVO_list.adNo}">
												<input type="hidden" name="action" value="getOne_For_Display">
												
			                                    <div class="post-item">
			                                        <div class="post-img">
			                                            <img src="${adVO_list.adPic1 }" />
			                                        </div>
			                                        <div class="post-text">
			                                            <a>${adVO_list.adTitle }</a>
			                                            <div class="post-meta">
			                                            <p><i class="far fa-clock"></i>${adVO_list.postTime } - ${adVO_list.deadline }</p>
			                                            </div>
			                                        </div>
			                                    </div>
											</FORM>
										</c:if>
                                   </c:forEach> 
                                    
                                    
                                    
                                </div>
                            </div>

                            <div class="sidebar-widget">
                                <div class="image-widget">
                                    <a href="#"><img src="${adVO.adPic2}" alt="Image"></a>
                                </div>
                            </div>

                           

                            <div class="sidebar-widget">
                                <div class="image-widget">
                                    <a href="#"><img src="${adVO.adPic3}" alt="Image"></a>
                                </div>
                            </div>

                            

                           

                            

                            <div class="sidebar-widget">
                                <h2 class="widget-title">想要獲取最新的優惠消息？</h2>
                                <div class="text-widget">
                                    <p>
                                      	  加入BELOVED成為「會員」，以便他們可以輕鬆地得到相關資訊
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Single Post End-->   





	</div>

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