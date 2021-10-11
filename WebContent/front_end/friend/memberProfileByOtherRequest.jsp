<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>
<%@ page import="java.util.*"%>
<%
  MemberVO memberVOA = (MemberVO) request.getAttribute("memberVOA");
  MemberVO memberVOB = (MemberVO) request.getAttribute("memberVOB");
  
FriendService friendService=new FriendService();



  Integer memberNoA=memberVOA.getMemberNo();
  Integer memberNoB=memberVOB.getMemberNo();
  boolean check=friendService.checkIsFriend(memberNoA, memberNoB);
  
  FriendVO friendVO=new FriendVO();
  friendVO.setMemberNoA(memberNoA);
  friendVO.setMemberNoB(memberNoB);
  friendVO.setFriendStatus(0);
  if(check){
  friendVO=friendService.getOneFriend(memberNoA, memberNoB);
  }
  
  String memberCountry=memberVOB.getMemberCountry().toString();
  switch(memberCountry) {
	case "1":
		memberCountry="臺北市";
		break;
	case "2":
		memberCountry="新北市";
		break;
	case "3":
		memberCountry="桃園市";
		break;
	case "4":
		memberCountry="臺中市";
		break;
	case "5":
		memberCountry="臺南市";
		break;
	case "6":
		memberCountry="高雄市";
		break;
	case "7":
		memberCountry="基隆市";
		break;
	case "8":
		memberCountry="新竹市";
		break;
	case "9":
		memberCountry="嘉義市";
		break;
	case "10":
		memberCountry="新竹縣";
		break;
	case "11":
		memberCountry="苗栗縣";
		break;
	case "12":
		memberCountry="彰化縣";
		break;
	case "13":
		memberCountry="南投縣";
		break;
	case "14":
		memberCountry="雲林縣";
		break;
	case "15":
		memberCountry="嘉義縣";
		break;
	case "16":
		memberCountry="屏東縣";
		break;
	case "17":
		memberCountry="宜蘭縣";
		break;
	case "18":
		memberCountry="花蓮縣";
		break;
	case "19":
		memberCountry="臺東縣";
		break;
	case "20":
		memberCountry="澎湖縣";
		break;
	}
%>

<html>
<head>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>




<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<title>個人資料瀏覽</title>

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
 img{
 	  height:150px;
 	  width:150px;
      border-radius: 100px;
    }
</style>

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

	<div class="container-fluid" style="margin-left: 280px;">



<div class="container">
    <div class="row col-md-6 col-md-offset-2 custyle">
    <table class="table table-striped custab">
    
    

		<form action="<%=request.getContextPath()%>/friend/friend" method="POST">
            <input type="hidden" name="action" value="delete_Friend">
            <input type="hidden" name="memberAccountA" value="${memberVOA.memberAccount}">
            <input type="hidden" name="memberAccountB" value="${memberVOB.memberAccount}">
            <button class='btn btn-info btn-xs' type="submit" style="float:right; position: relative; right: -450px; top: -10px; margin-right: 10px;"><i class="fas fa-user-clock"></i>取消邀請</button>
            </form>
            
        <form action="<%=request.getContextPath()%>/friend/friend" method="POST">
		<input type="hidden" name="action" value="back">
		<input type="hidden" name="memberAccountA" value="${memberVOA.memberAccount}">
		<button class='btn btn-info btn-xs' type="submit" style="float:right; position: relative; right: 440px; top: -40px; margin-right: 10px;"><i class="fas fa-arrow-circle-left"></i> 回上一頁</button>
		</form>

    
        <tr>
		<th></th>
		
		<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVOB.memberAccount}"></td>
	</tr>
	<tr>
		<th>姓名</th>
		<td><%=memberVOB.getMemberName()%></td>
	</tr>
	<tr>
		<th>性別</th>
		<td><%= (memberVOB.getMemberGender()==1)? "男生" : "女生"%></td>
	</tr>
	<tr>
		<th>生日</th>
		<td><%=memberVOB.getMemberBirthday()%></td>
	</tr>
	<tr>
		<th>職業</th>
		<td><%=memberVOB.getMemberJob()%></td>
	</tr>
	<tr>
		<th>居住地</th>
		<td><%=memberCountry%></td>
	</tr>
	<tr>
		<th>手機</th>
		<td><%=memberVOB.getMemberPhone()%></td>
	</tr>
	<tr>
		<th>自我介紹</th>
		<td><%=memberVOB.getMemberIntroduce()%></td>
	</tr>
    </table>
    
    </div>
</div>
		
		<form id="Refresh" action="<%=request.getContextPath()%>/friend/friend" method="POST">
		<input type="hidden" name="action" value="member_Profile_By_Other">
		<input type="hidden" name="memberAccountA" value="${memberVOA.memberAccount}">
		<input type="hidden" name="memberAccountB" value="${memberVOB.memberAccount}">
		</form>
		
		
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
function test(){
if(<%=!check%>){
	let Refresh=document.getElementById("Refresh");
	Refresh.submit();
}



else if(<%=check%>){
	 if(<%=friendVO.getFriendStatus()!=2%>){
		let Refresh=document.getElementById("Refresh");
		Refresh.submit();
	}
	}
}
test();
</script>
</body>
</html>