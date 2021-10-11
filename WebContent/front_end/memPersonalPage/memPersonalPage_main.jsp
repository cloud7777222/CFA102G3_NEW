<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>
<%@ page import="redis.clients.jedis.Jedis"%>


<%-- <%=request.getParameter("memberNo")==null%> --%>
<%
	MemPersonalPageService mppSvc = new MemPersonalPageService();
	List<MemPersonalPageVO> list = null;
	String memberNo = request.getParameter("memberNo");//被參觀的人
	pageContext.setAttribute("memberNo", memberNo); 
	try{		
		list = mppSvc.getAll().stream()
				.filter(i-> i.getMemberNo().toString().equals(memberNo))
				.collect(Collectors.toList());
		pageContext.setAttribute("list", list);						
	}catch(Exception e){
		list = mppSvc.getAll();
		pageContext.setAttribute("list", list);	
	}
	try{
		MemberService memberService = new MemberService();
		MemberVO memberVO = memberService.getOneMember(Integer.parseInt(memberNo));
		String account = memberVO.getMemberAccount(); //被參觀的人的帳號
		pageContext.setAttribute("account", account);
		String memberName = memberVO.getMemberName();
		pageContext.setAttribute("memberName", memberName);
	}catch (Exception e){
		
	}
	
	//惠君
		Jedis jedis = new Jedis("localhost", 6379);
	 	String who = "men:"+ request.getParameter("memberNo")+":page.view";
		String field = "points";
// 		List<String> count = new ArrayList<String>();
// 		String allCount = null;
		
		if(jedis.hexists(who, field)){
			jedis.hincrByFloat(who, field, 1);
		}else{
			
		jedis.hset(who, field, "1");
		}
		String all ="men:all:page.view";
		if(jedis.hexists(all, field)){
			jedis.hincrByFloat(all, field, 1);
		}else{
			
		jedis.hset(all, field, "1");
		}
// 		pageContext.setAttribute("count", count);
		jedis.close();
	
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Blog Home</title>      
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<!-- 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"> -->
		 <!-- Core theme CSS (includes Bootstrap)-->
		<link href="<%=request.getContextPath()%>/front_end/css/memPersonalPage.css" rel="stylesheet" />
    </head>
    
    <style>
    	*{    		
		    font-family: var(--bs-body-font-family);
		    font-size: var(--bs-body-font-size);
		    font-weight: var(--bs-body-font-weight);
		    line-height: var(--bs-body-line-height);
		    text-align: var(--bs-body-text-align);
		    -webkit-text-size-adjust: 100%;
		    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);		
    	}
    	body{   		
    		margin: 0;
    	}
    	.pagination li{
    		color: gold;
    	}
    	i.fa-bookmark{
    		color: gold;   		
    	}
    	i.fa-thumbs-up{
    		color: red;
    	}	
    	i.fa-paper-plane{
    		 color: #5E5A54;
    		 padding-left: 10px;
    	}
    	i.fa-book-reader{
    		display: block;
    		color: #5E5A54;
    		padding-left: 6px;
    	}
    	button.like{
    		display: block;
    		background-color: white;
    		border: 2px solid white;
    	}
    	button.edit{
    		display: block;
   			width: 100%;
    		background-color: white;
    		border: 2px solid white;
    		color: #B8AA95;		
    	}
    	div.btn{
    		display: inline-block;  		
    	}
    	div.btn btn-warning{
    	display: inline-flex;
    	justify-content: flex-end;    	
    	}
		div.card{
  			margin-top: auto;
  			box-sizing: border-box;
  			opacity: 0.75;	
  		}
  		div.card:hover{  			
  			box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.3);
  			opacity: 1;
/*   			transform: scale(1.2); */
/* 			-ms-transform: scale(1.05); */
/* 			-webkit-transform: scale(1.05);	 */
  		}
  		
  		li{
  			color: black;
  		}
  		ul.pagination{
  			display:center;
  			text-color: black;
  		}
  		img{
  		max-width: 100%;
  		overflow: hidden;
  		}
  		img#head_shot{
	  		border-radius: 50%;
	  		max-width: 300px;
			margin: 10px 12px;
			text-align: center;	
			opacity: 0.8;
			border: 2px solid #ddd;
			padding: 15px;
		}
  		p.card-text{
  			height: 25px;
  			overflow: hidden;
  			margin-botton: 5px;
  		}
  		h6.card-title{
  			 background-image: linear-gradient(white, gold);
  			 border-radius: 40px 10px;
  			 color: #5E5A54;
  			 display: inline-block;
  			 padding-right: 10px;
  			 padding-left: 5px;
  		}
  		div.card-body{
  			display: inline-block;
    		justify-content: block-end; 
  		}
  		img #profileImage {  			
  			border-radius: 50%;						
		}
		div.col-lg-4{
			padding-left: 50px;			
		}
		button{
			margin-left: 10px;
			margin-right: 10px;
		}
		div.add{
			padding: 5px;					
		}
		nav.navbar{
			background: #940a3f;
		}
		footer{
			background: #940a3f;
		}
		
		
		
					
    </style>
    <body>
        <!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/index/index.jsp">BELOVED</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
<!--                         <li class="nav-item"><a class="nav-link" href="#">Home</a></li> -->
<!--                         <li class="nav-item"><a class="nav-link" href="#!">About</a></li> -->
<!--                         <li class="nav-item"><a class="nav-link" href="#!">Contact</a></li> -->
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Blog</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page header with logo and tagline-->
        <header class="py-5 bg-light border-bottom mb-4">
            <div class="container">
                <div class="text-center my-5">
                    <h2 class="fw-bolder">Hi, I'm ${memberName} ! </h2>
                    <h3>Welcome to My Blog Home!</h3>
                    <p class="lead mb-0"></p>
                </div>
            </div>
        </header>
        <!-- Page content-->
        <div class="container">
            <div class="row">
                <!-- Blog entries-->
                <div class="col-lg-8">
                    <!-- Featured blog post-->
<!--                     <div class="card mb-4"> -->
<!--                         <a href="#!"><img class="card-img-top" src="https://cdn0.techbang.com/system/images/107790/original/dde4742f84ef740e04a7a8c713f28835.jpg?1355644703" alt="..." /></a> -->
<!--                         <div class="card-body">                            -->
<!--                             <h2 class="card-title"><i class="fas fa-bookmark fa-sm"></i>  Featured Post Title </h2> -->
<!--                             <div class="small text-muted">January 1, 2021</div> -->
<!--                             <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus possimus, veniam magni quis!</p> -->
<!--                              <button class="btn btn-outline-warning" type="submit"><i class="fas fa-book-reader">  Read more</i></button> -->
<!--                         </div> -->
<!--                     </div> -->
            
				
                    <!-- Nested row for non-featured blog posts-->
					<!--設定每列有幾欄, >md時有2欄 -->
                    <div class="row row-cols-1 row-cols-md-2">  
<%--                       <%@ include file="pages/page1.file"%> --%>
            			<c:forEach var="mppVO" items="${list}" >          
                            <!-- Blog post-->
                            <div>
	                            <div class="col card mb-4" postState="${mppVO.postState}">                      
	                                <a href="#!"><img class="card-img-top" src="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet?postNo=${mppVO.postNo}" /></a> 	                               
	                                <div class="card-body">
	                                	<h6 class="card-title h6">Post ${mppVO.postNo} <i class="far fa-paper-plane"></i></h6>
	                                    <div class="small text-muted">${mppVO.postTime}</div>                                    
	                                    <p class="card-text">${mppVO.postContent}</p>
	                                   <table class="icon">	                                    
	                                    <tr>
	                                    <td>
	                                    <!--舊的:按讚按鈕用form送 -->	                                  	                                   
<%-- 	                                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" style="margin-bottom: 0px;"> --%>
<%-- 										     <button type="submit" class="like"><i onclick="myFunction(this)" class="far fa-thumbs-up"> ${mppVO.numOfLike}</i></button>	      --%>
<%-- 										     <input type="hidden" name="postNo"  value="${mppVO.postNo}"> --%>
<%-- 										     <input type="hidden" name="numOfLike"  value="${mppVO.numOfLike+1}">				  --%>
<%-- 										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 										     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			      --%>
<!-- 										     <input type="hidden" name="action"	value="updateLike"> -->
<!-- 										  </FORM> -->
											<!--新改版:按讚按鈕用Ajax -->
											<button class="like" value="${mppVO.postNo}"><a href="#!"><span></span>
											<i class="far fa-thumbs-up">${mppVO.numOfLike}</i>
											</a></button>
										  </td>
										  <td>	                                 														                             	
 										<!--修改按鈕 -->
 										<c:if test="${sessionScope.memberVO.memberNo == mppVO.memberNo}">
	                                	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" style="margin-bottom: 0px;">
										     <button type="submit" class="edit"><i class="far fa-edit"></i></button>		     
										     <input type="hidden" name="postNo"  value="${mppVO.postNo}">			 
										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<%-- 										     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->			      --%>
										     <input type="hidden" name="action"	value="getOne_For_Update_frontEnd">
										  </FORM>
										 </c:if> 
										  </td>
										  <td>
										 <!--擇一展開細讀按鈕 -->
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet" >
										      <input type="hidden" name="postNo"  value="${mppVO.postNo}">
										      <input type="hidden" name="action" value="getOne_For_Display_frondEnd">
										      <button class="btn btn-outline-warning" type="submit"><i class="fas fa-book-reader">  Read more</i></button>
										  </FORM>
										  </td>
										   </tr>
										</table>		    
	                                </div>
	                            </div>
                           </div>
                           
               			 </c:forEach>			
                    </div>
<%--                     <%@ include file="pages/page2.file"%> --%>
                   
                </div>
                <!-- Side widgets-->
                <div class="col-lg-4">
                 <!--使用會員大頭照-->
                 <div class="card mb-4" id="profile">
                        <div class="card-header"><b>關於我</b></div>
                        <div class="card-body">
<!--                         	<div class="add"><button class="btn btn-outline-warning" id="addMe"><i class="fas fa-user-friends"></i> 加好友</button></div> -->
                            <div class="input-group">
                                <img class="card-img-top" id="profileImage" src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${account}" alt="${param.memberName}"/> 
                            </div>
                        </div>
                    </div>        
                    <!-- Search widget-->
                    <div class="card mb-4">
                        <div class="card-header"><b>預約我</b></div>
                        <div class="card-body">
                            <div class="input-group">
                            	 <FORM id="datingBtn${memberVO.memberNo}" METHOD="post" ACTION="<%=request.getContextPath()%>/memTime/memTime.do"
                                    style="margin-bottom: 0px;">
                                    <input type="hidden" name="memberNoB" value="${memberNo}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<input type="hidden" name="action" value="datingCheck">
                                	<button class="btn btn-outline-warning" id="button-search" type="submit"><i class="fas fa-heart">  Go!</i></button>
                                </FORM>
                            </div>
                        </div>
                    </div>
                     <!-- Search widget-->
                    <div class="card mb-4">
                        <div class="card-header"><b>發表貼文</b></div>
                        <div class="card-body">
                            <div class="input-group">
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="fas fa-edit"></i> Post</button>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Categories widget-->
<!--                     <div class="card mb-4"> -->
<!--                         <div class="card-header">Categories</div> -->
<!--                         <div class="card-body"> -->
<!--                             <div class="row"> -->
<!--                                 <div class="col-sm-6"> -->
<!--                                     <ul class="list-unstyled mb-0"> -->
<!--                                         <li><a href="#!">Web Design</a></li> -->
<!--                                         <li><a href="#!">HTML</a></li> -->
<!--                                         <li><a href="#!">Freebies</a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="col-sm-6"> -->
<!--                                     <ul class="list-unstyled mb-0"> -->
<!--                                         <li><a href="#!">JavaScript</a></li> -->
<!--                                         <li><a href="#!">CSS</a></li> -->
<!--                                         <li><a href="#!">Tutorials</a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     Side widget -->
<!--                     <div class="card mb-4"> -->
<!--                         <div class="card-header">Side Widget</div> -->
<!--                         <div class="card-body">You can put anything you want inside of these side widgets. They are easy to use, and feature the Bootstrap 5 card component!</div> -->
<!--                     </div> -->                     						             
                    </div>
                </div>
            </div>
       
        
         <!-- Modal#1: add -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">新增貼文</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- =========================================以下為原memPersonalPage_add.jsp的內容========================================== -->
					<jsp:include page="memPersonalPage_add.jsp" />
					<!-- =========================================以上為原memPersonalPage_add.jsp的內容========================================== -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
<!-- 					<button type="button" class="btn btn-warning" id="addedBtn">Save changes</button> -->
				</div>
			</div>
		</div>
	</div>
	

        
        
        
        <!-- Footer-->
        <footer class="py-5 ">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Beloved 2021</p></div>
        </footer>
        
      
	
        <!-- jQuery-->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        
        <script type="text/javascript">     
        
 		<!-- Like Button-->
//  		function myFunction(e) {
//  			  e.classList.toggle("fas");
//  		}



 		
 		
 		
 		<!-- Like Button 按讚 -->
 			$(".fa-thumbs-up").click(function() {
 				var indexOfLike = $(this).index('.fa-thumbs-up'); //利用頁面上的index 去抓按讚顯示數字的區塊!
 				var num = $(".like").eq(indexOfLike).val();
 				//alert(indexOfLike);
 				
 				$.ajax({			
 					url: "${pageContext.request.contextPath}/memPersonalPage/MemPersonalPageServlet",
 					type: "POST",
 					data:{
 						postNo: num,
 						action: "updateLike"
 					},
 					success: function(response) {
 						//alert(response);
 						$(".fa-thumbs-up").eq(indexOfLike).html("<span>"+response+"</span>");				 				 
 					}
 					});			
 			});
 			

 		<!-- 文章狀態顯示或隱藏 -->		
 			$(document).ready(function(){						
 						var objS = $(".mb-4");
 						//alert("finish!");	 //測試functiion 是否有啟動			
 						var state = $("[postState=0]");				
 						state.html("<span> 已下架 ! </span>");
 						state.css("display", "block"); //也可以用none, 會完全看不到區塊跟.html()內的字						
 				});

 			
      	  
//         $(document).ready(function(){       	 
//         	$('.fa-heart').on('click', function(event, count) {
//         		event.preventDefault();    		  
// //         		var $this = $(this),
// //         		    count = $this.attr('value'),
// //         		    active = $this.hasClass('fas');
// //         		 $this.attr('value', active ? ++count : --count);
// //         		 $this.html(count);         			
// //         		 $this.data("value", count);
// //         		 alert(count);       		 
//         	});       	 
//         });
        	 
	</script>
    </body>
</html>
