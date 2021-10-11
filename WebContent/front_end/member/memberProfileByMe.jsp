<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
  MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
  String memberCountry=memberVO.getMemberCountry().toString();
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
    
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member">
            <button class='btn btn-info btn-xs' type="submit" style="float:right;position: relative; right: -320px; top: -10px; margin-right: 10px;"><span class="glyphicon glyphicon-edit"></span> 修改個人資料</button>
            <input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
            <input type="hidden" name="action"	value="update_Member_Profile">
       </FORM>
       <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member">
        <button class='btn btn-info btn-xs' type="submit" style="float:right; position: relative; right: -320px; top: -10px; margin-right: 10px;"><span class="glyphicon glyphicon-edit"></span> 修改密碼</button>
            <input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
            <input type="hidden" name="action"	value="update_Password_Member_Profile">
       </FORM>
    
    
        <tr>
		<th></th>
		
		<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}"></td>
	</tr>
	<tr>
		<th>姓名</th>
		<td><%=memberVO.getMemberName()%></td>
	</tr>
	<tr>
		<th>性別</th>
		<td><%= (memberVO.getMemberGender()==1)? "男生" : "女生"%></td>
	</tr>
	<tr>
		<th>生日</th>
		<td><%=memberVO.getMemberBirthday()%></td>
	</tr>
	<tr>
		<th>職業</th>
		<td><%=memberVO.getMemberJob()%></td>
	</tr>
	<tr>
		<th>居住地</th>
		<td><%=memberCountry%></td>
	</tr>
	<tr>
		<th>手機</th>
		<td><%=memberVO.getMemberPhone()%></td>
	</tr>
	<tr>
		<th>自我介紹</th>
		<td><%=memberVO.getMemberIntroduce()%></td>
	</tr>
    </table>
    
    </div>
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