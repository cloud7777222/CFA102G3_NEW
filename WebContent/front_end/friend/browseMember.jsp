<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");//用Session拿是因為重新整理memberVOA需要值(如果要的話要再去訪問一次Servlet)
	Integer memberNoA=memberVO.getMemberNo();
    MemberService memberService = new MemberService();
	Set<MemberVO> set=memberService.getAllMemberExceptMeBySet(memberNoA);
    pageContext.setAttribute("set",set);//做set用
	
	FriendService friendService=new FriendService();
    
	 List<MemberVO> requestList=new ArrayList<MemberVO>();
	 requestList=friendService.getFriendRequest(memberNoA);
	 pageContext.setAttribute("requestList",requestList);//好友邀請用
%>

<html>
<head>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha256-eZrrJcwDc/3uDhsdt61sL2oOBY362qM3lon1gyExkL0=" crossorigin="anonymous" />


    <link rel="stylesheet" href="style.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>




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
</style>




<style>
    .box
{
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.19);
    border-radius: 10px;
}
.card
{
    margin-top: 80px;
    height: 300px;
    transition: 0.5s;
}
.card:hover
{
    border: 1px solid green;
    border-radius: 30px;
}
.card .cardImg
{
    height: 150px;
    width: 150px;
    position: relative;
    top: -15px;
    left: 20%;
    text-align:center;
    border-radius: 100px;
}
.card .cardImg img
{
 	  height:150px;
 	  width:150px;
      border-radius: 100px;
}

.card .info
{
    text-align: center;
}
.card .info h3
{
    color: rgb(70, 66, 66);
}
.card .info p
{
    color: gray;
}

.ligne
{
    display: flex;
}
.FriendRequestList{
 	  height:50px;
 	  width:50px;
      border-radius: 150px;
      margin-left:10px;
}
.btn-xs{
height:40px;
width:40px;
}



</style>



<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>   
$(document).ready(function() {
	$("#checkRequest").click(function() {
		$("#RequestList").toggle();
	});
});
</script>
</head>
<body bgcolor='white'>

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


<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	<form action="<%=request.getContextPath()%>/member/member" method="POST">
		<input type="hidden" name="action" value="member_Profile_By_Me">
		<input type="hidden" name="memberAccount" value="<%=memberVO.getMemberAccount()%>">
		<button class='btn btn-info btn-xs' type="submit" style="float:right; position: relative; right: 0px; top: 10px; margin-right: 10px;font-size:20px;"><i class="fas fa-user-circle"></i> </button>
	</form>
	
	<form action="<%=request.getContextPath()%>/chat/chat" method="POST">
		<input type="hidden" name="action" value="chat_Room">
		<input type="hidden" name="memberAccount" value="<%=memberVO.getMemberAccount()%>">
<button class='btn btn-info btn-xs' type="submit" style="float:right; position: relative; right: 0px; top: 0px; margin-right: 10px;font-size:20px;"><i class="fas fa-comment"></i> </button>
	</form>
		
		<button id="checkRequest" class='btn btn-info btn-xs' type="button" style="float:right; position: relative; right: 0px; top: 0px; margin-right: 10px;font-size:20px;"><i class="fas fa-user-friends"></i></button>
		
		<table  id="RequestList" style="display: none ;float:right; position: absolute; right: 0px; top: 235px; z-index:8;">
			<c:forEach var="Request" items="${requestList}">
				<tr style="background-color:white;border-top: 1px solid lightgray;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.19);">
					<td><img class="FriendRequestList" src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${Request.memberAccount}"></td>
					<td><div style="margin-left:10px;margin-right:10px;">${Request.memberName}</div></td>
					<td>
						<form action="<%=request.getContextPath()%>/friend/friend" method="POST">
							<input type="hidden" name="action" value="response_Friend_Request">
							<input type="hidden" name="memberAccountA" value="${memberVO.memberAccount}">
							<input type="hidden" name="memberAccountB" value="${Request.memberAccount}">
							<button class='btn btn-info btn-circle' type="submit" style="float:right;  margin-right: 10px;font-size:xx-small;margin-top: 10px;">確認邀請</button>
						</form>
						<form action="<%=request.getContextPath()%>/friend/friend" method="POST">
							<input type="hidden" name="action" value="delete_Friend_Request">
							<input type="hidden" name="memberAccountA" value="${memberVO.memberAccount}">
							<input type="hidden" name="memberAccountB" value="${Request.memberAccount}">
							<button class='btn btn-info btn-circle' type="submit" style="float:right;margin-right: 10px;font-size:xx-small;margin-top: 10px;margin-bottom: 10px">刪除邀請</button>
						</form>
					</td>
				</tr>
			</c:forEach>
			</table>
			
			
		
	
	
        <div class="row">
	<c:forEach var="memberVO" items="${set}">
            <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                <div class="box card"  style="background-color: ${(memberVO.memberGender==1)?"lightblue":"pink"};">
                    <div class="box cardImg">
                        <img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}">
                    </div>
                    <div class="info">
                        <h3>${memberVO.memberName}</h3>
                        <p>${(memberVO.memberGender==1)?"男生":"女生"}</p>
                        <form action="<%=request.getContextPath()%>/friend/friend" method="POST">
                            <input type="hidden" name="action" value="member_Profile_By_Other">
                            <input type="hidden" name="memberAccountA" value="<%=memberVO.getMemberAccount()%>">
                            <input type="hidden" name="memberAccountB" value="${memberVO.memberAccount}">
                            <input class="btn btn-primary" type="submit" value="瀏覽個人頁面">
                        </form>
                    </div>
                </div>
            </div>
	</c:forEach>
	
	        </div>


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
</body>
</html>