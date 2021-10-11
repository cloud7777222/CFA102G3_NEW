<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>忘記密碼</title>
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

	<div class="container-fluid" style="margin-left: 100px;">







<!------ Include the above in your HEAD tag ---------->

<div class="container">
<div class="row">
<div class="col-md-10 ">
  <form action="<%=request.getContextPath()%>/member/member" method="post" class="form-horizontal" onSubmit="return CheckForm();">
<fieldset>

<!-- Form Name -->
<legend>忘記密碼</legend>
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- Text input-->
<div class="form-group">
    <label class="col-md-4 control-label" for="MemberAccount">帳號</label>  
    <div class="col-md-4">
    <div class="input-group">
      <input id="MemberAccount" name="memberAccount" type="text"  class="form-control input-md">
        </div>
    </div>
  </div>
  
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="MemberEmail">E-Mail</label>  
  <div class="col-md-4">
  <div class="input-group">
    <input id="MemberEmail" name="memberEmail" type="text"  class="form-control input-md">
      </div>
  </div>
</div>



<div class="form-group">
  <label class="col-md-4 control-label" ></label>  
  <div class="col-md-4">
  <button class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> 重製密碼</button>
  <button type="reset" class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign"></span> 清空</button>
    
  </div>
</div>
<input type="hidden" name="memberAccount"  value="${memberVO.memberAccount}">
<input type="hidden" name="action" value="forgot_Password">
</fieldset>
</form>
</div>

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
    function CheckForm()
    
    {
      if(confirm("將新密碼傳送至此信箱")==true)   
        return true;
      else  
        return false;
    }
	</script>
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