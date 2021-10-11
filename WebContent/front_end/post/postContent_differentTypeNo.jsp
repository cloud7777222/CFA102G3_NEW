<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.postmessage.model.*"%>
<%@ page import="com.posttype.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>


<%
	Integer memberNo = ((MemberVO) session.getAttribute("memberVO")).getMemberNo();
	request.setAttribute("memberNo", memberNo); //來參觀的人	
%>


<%-- <jsp:useBean id="listPostsBy_PostTypeNo" scope="request" type="java.util.Set<PostVO>" /><!-- 於EL此行可省略 -->	 --%>
<jsp:useBean id="postTypeSvc" scope="page"
	class="com.posttype.model.PostTypeService" />


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>文章類別</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link
	href="<%=request.getContextPath()%>/vendor/post/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/vendor/post/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/vendor/post/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/vendor/post/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/vendor/post/swiper/swiper-bundle.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">


<!-- Template Main CSS File -->
<link href="<%=request.getContextPath()%>/front_end/css/post.css" rel="stylesheet" />

<!-- 開合卡 -->
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/bulma/0.1.0/css/bulma.min.css'>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'>

<!-- =======================================================
  * Template Name: Lonely - v4.3.0
  * Template URL: https://bootstrapmade.com/free-html-bootstrap-template-lonely/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<style>
	body {
		font-family: 'Nunito', sans-serif;
		padding: 50px;
	}
	
	div.container {
		border: 2px, solid, black;
	}
	
	div#table{
		display: block;
		margin-left: 5px;
	}
	button{
		border: 0px solid white;
		background-color: white;
	}
	i.far:hover{
		color: gold;
	}
	i.fa-angle-double-down:hover{
		color: gold;
	}
	nav{
		margin-top: 10px;
	}
	nav:hover{
		color: gold;
	}
	table#messageIcon{
	 	margin-top: 6px;
	}
	footer{
	 	margin-top: 30px;
	}



/*開合卡*/
	.card+.card {
		margin-top: 20px;
	}
	header.card-header{
		background-color: white;
	}
	div.is-fullwidth{
	 
	}


</style>

<body>



	<!-- ======= Header ======= -->
	<header id="header" class="d-flex align-items-center">
		<div
			class="container d-flex align-items-center justify-content-between">

			<div class="logo">
				<h1>
					<a href="<%=request.getContextPath()%>/front_end/index/index.jsp">Beloved</a>
				</h1>
			</div>

			<nav id="navbar" class="navbar">
				<ul>
					<!-- 					Button trigger modal -->
					<li><button type="button" class="btn btn-warning"
							data-bs-toggle="modal" data-bs-target="#exampleModal">
							<i class="fas fa-edit"></i> Post
						</button></li>
				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav>
			<!-- .navbar -->

		</div>
	</header>
	<!-- End Header -->

	<main id="main">

		<div class="container">
			<nav
				style="-bs-breadcrumb-divider: url(&amp; amp; amp; amp; amp; amp; amp; #34; data: image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='currentColor'/%3E%3C/svg%3E&amp;amp;"
				aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/front_end/post/post_main.jsp">論壇首頁</a></li>
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/postType/PostTypeServlet?postTypeNo=1&action=listPostsBy_PostTypeNo_C">旅遊</a></li>						
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/postType/PostTypeServlet?postTypeNo=2&action=listPostsBy_PostTypeNo_C">吃吃喝喝</a></li>
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/postType/PostTypeServlet?postTypeNo=3&action=listPostsBy_PostTypeNo_C">兩性關係</a></li>
					<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/postType/PostTypeServlet?postTypeNo=4&action=listPostsBy_PostTypeNo_C">其他</a></li>
				</ol>
			</nav>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-lg-11">
					<c:forEach var="postVO" items="${listPostsBy_PostTypeNo}" varStatus="tableCount">
						<!-- ======= Start of accordion ======= -->											
						<section class="section">
							<div class="container">						
								<div class="card is-fullwidth" id="post" postState="${postVO.postState}" >							
									<header class="card-header">
										<div class="card-header-title">
										<div id="table" ><table>
											<tr>
												<td><i class="fas fa-star-of-david"></i> 文章編號 : </td>
												<td> ${postVO.postNo}</td>
											</tr>
<!-- 											<tr> -->
<!-- 												<td><i class="fas fa-star-of-david"></i> 文章類別 :</td> -->
<%-- 												<td>${postVO.postTypeNo}</td> --%>
<!-- 											</tr> -->
											<tr>
												<td><i class="far fa-user"></i> 會員編號 : </td>
												<td> ${postVO.memberNo}</td>
											</tr>
											<tr>
												<td><i class="far fa-calendar-alt"></i> 發表時間 : </td>
												<td> ${postVO.postTime}</td>
											</tr>
											<tr>
												<td><i class="far fa-file-alt"></i> 文章內容 : </td>
												<td> ${postVO.postContent}</td>
											</tr>											
										</table>
										<table>
											<tr>
												<td>
													<!-- icon 區開始 -->
													<div>
														<div class="col-lg-10">
															<h5>
																<table id="messageIcon">
																	<tr>
																		<td>
																		<!--按讚按鈕 -->
																		<button class="like" value="${postVO.postNo}"><a href="#!"><span></span>
																				<i class="far fa-thumbs-up fa-sm">${postVO.numOfLike}</i>
																			</a></button>
																			<!--留言按鈕 -->																			
																			<button><span></span><a href="<%=request.getContextPath()%>/front_end/postMessage/postMessage_add.jsp?postNo=${postVO.postNo}&postTypeNo=${postVO.postTypeNo}">
																				<i class="far fa-comment comment"></i></a>
																			</button>																																		
																			<!--修改按鈕 -->
																			<c:if test="${sessionScope.memberVO.memberNo == postVO.memberNo}">
																			<button class="editPost">																																																			 
																			<span></span>
																				<a href="<%=request.getContextPath()%>/post/PostServlet?postNo=${postVO.postNo}&postTypeNo=${postVO.postTypeNo}&action=getOne_For_Update_frontEnd&requestURL=<%=request.getServletPath()%>"><i
																					class="far fa-edit fa-sm"></i></a>																					
																			</button>
																		</c:if>														
																	</tr>
																</table>
															</h5>
														</div>
													</div> <!-- icon 區結束 -->
												</td>
											</tr>
										</table></div>										
										</div>
										<a class="card-header-icon card-toggle"><button class="get" value="${postVO.postNo}">
										<i class="fas fa-angle-double-down fa-lg"></i></button>
										</a>
									</header>
									<div class="card-content is-hidden">
									 <!-- 留言區開始 -->
										<div class="content">										
											<table class="table table-striped table-bordered table-condensed showMessages"></table>												
										<!-- 留言區結束 -->
										</div>
									</div>
								</div>
							</div>
						</section>
						<!-- ======= End of accordion ======= -->
					</c:forEach>
				</div>
			</div>
		</div>

	</main>

	<!-- End #main -->


	<!-- Modal#1: add -->

	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">新增文章</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- =========================================以下為原post_add.jsp的內容========================================== -->
					<jsp:include page="post_add.jsp" flush="true">
						<jsp:param  name="postTypeNo" value="${postVO.postTypeNo}"/>
					</jsp:include>
					<!-- =========================================以上為原post_add.jsp的內容========================================== -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<!-- 					<button type="button" class="btn btn-warning" id="addedBtn">Save changes</button> -->
				</div>
			</div>
		</div>
	</div>


<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b> --%>

	<div></div>

	<!--   <!-- ======= Footer ======= -->
	  <footer id="footer">
	    <div class="container">
	      <div class="copyright">
	        Copyright &copy;  <strong><span>Beloved</span></strong> 2021
	      </div>   
	    </div>
	<!--   </footer>End  Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>


	<!-- jQuery-->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

	<!-- 母體範本的Vendor JS Files -->
	<script
		src="<%=request.getContextPath()%>/vendor/post/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendor/post/glightbox/js/glightbox.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendor/post/isotope-layout/isotope.pkgd.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendor/post/php-email-form/validate.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendor/post/purecounter/purecounter.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendor/post/swiper/swiper-bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendor/post/waypoints/noframework.waypoints.js"></script>




	<!-- 	開合卡 -->
	<script>
		document.addEventListener('DOMContentLoaded', function () {
			  let cardToggles = document.getElementsByClassName('card-toggle');
			  for (let i = 0; i < cardToggles.length; i++) {
			    cardToggles[i].addEventListener('click', e => {
			      e.currentTarget.parentElement.parentElement.childNodes[3].classList.toggle('is-hidden');
			    });
			  }
			});
	</script>

	<!-- 顯示留言js -->
	<script type="text/javascript">
	$(".get").click(function() {
		var num = $(this).val();
		var indexOfPostOnPage = $(this).index('.get'); //利用頁面上的index 去抓下面顯示留言區塊!
// 		alert(indexOfPostOnPage);
		$.ajax({			
			url: "${pageContext.request.contextPath}/post/PostServlet?postNo="+num+"&action=listMessagesBy_PostNo_frontEnd",
			type: "GET",			
			dataType: "json",
			success: function(response) {
				//console.log(response);				
// 				$.each(response, function(index, value) {
// 					  console.log(value);
// 					});
// 				var megs = JSON.parse(response);
// 				//console.log(megs);
				 var str = "";
				 str += "<tr><th style='text-align:left;'>留言編號</th><th style='text-align:left;'>會員</th><th style='text-align:left;'>留言時間</th><th style='text-align:left;'>內容</th></tr>";
			       for(var i=0; i < response.length; i++) {			    	  			    	  			    	  
			    	   str += "<tr>";
			    	   str += "<td style='text-align:left;'>" + response[i].mesNo + "</td>";			    	   
			    	   str += "<td>" + response[i].memberNo + "</td>";
			    	   str += "<td>" + response[i].mesTime + "</td>";
			    	   str += "<td>" + response[i].mesContent + "</td>";
<%-- 			    	   str += '<td><a href="'+<%=request.getContextPath()%>+'/postMessage/PostMessageServlet?action=getOne_For_Update&mesNo='+response[i].mesNo+'"><button>修改留言</i></button></a></td>'; --%>
			    	   str += "<tr>";			    	 
			       }
			       $(".showMessages").eq(indexOfPostOnPage).html(str);
			       $(".comment").eq(indexOfPostOnPage).html(response.length);
			}
			});							
		});
	
	
<!-- Like Button 按讚 -->
	$(".fa-thumbs-up").click(function() {
		var indexOfLike = $(this).index('.fa-thumbs-up'); //利用頁面上的index 去抓按讚顯示數字的區塊!
		var num = $(".like").eq(indexOfLike).val();		
		//alert(indexOfLike);		
		$.ajax({			
			url: "${pageContext.request.contextPath}/post/PostServlet?postNo="+num+"&action=updateForLike",
			type: "GET",						
			success: function(response) {
				//alert(response);
				$(".fa-thumbs-up").eq(indexOfLike).html("<span>"+response+"</span>");				 				 
			}
			});		
	});
	
	
	<!-- 文章狀態顯示或隱藏 -->		
	$(document).ready(function(){						
				var objS = $(".is-fullwidth");
				//alert("finish!");	 //測試functiion 是否有啟動			
				var state = $("[postState=0]");				
				state.html("<span> 已下架 ! </span>");
				state.css("display", "block"); //也可以用none, 會完全看不到區塊跟.html()內的字														
		});
	
	
	
				

			
		

	</script>

</body>

</html>