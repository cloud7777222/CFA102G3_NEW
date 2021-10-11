<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
  boolean beMember =(boolean) request.getAttribute("beMember");
%>
<script>
if(<%=beMember%>){
	alert("已經成為會員開始填寫基本資料")
}
</script>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>修改會員資料</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
 img{
      border-radius: 100px;
    }
</style>







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
<form class="form-horizontal" action="<%=request.getContextPath()%>/member/member" method="POST" enctype="multipart/form-data">
<fieldset>

<!-- Form Name -->
<legend>修改會員資料</legend>
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- File Button --> 
<div class="form-group">
  <label class="col-md-4 control-label" for="MemberPhoto"></label>
  <div class="col-md-4">
    <label class="col-md-4 control-label" for="MemberPhoto"><img id="Preview" src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}"></label>
    <input type="file" class="input-file" id="MemberPhoto" name="memberPhoto" style="display: none;">
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="MemberName">姓名</label>  
  <div class="col-md-4">
 <div class="input-group">
       <input  id="MemberName" name="memberName" type="text"  class="form-control" value="<%= (memberVO.getMemberName()==null)? "" : memberVO.getMemberName()%>">
      </div>
  </div>
</div>


<!-- Multiple Radios (inline) -->
<div class="form-group">
  <label class="col-md-4 control-label" for="Gender">性別</label>
  <div class="col-md-4"> 
    <label class="radio-inline" for="male">
      <input id="male" type="radio" name="memberGender"  value="1" checked="checked">
      男生
    </label> 
    <label class="radio-inline" for="female">
      <input id="female" type="radio" name="memberGender"  value="2" ${(memberVO.memberGender==2)?'checked':'' }>
      女生
    </label> 
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="MemberBirthday">生日</label>  
  <div class="col-md-4">
  <div class="input-group">
       <input  id="MemberBirthday" name="memberBirthday" type="text"  class="form-control input-md">
      </div>
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="MemberJob">職業</label>  
  <div class="col-md-4">
  <div class="input-group">
    <input  id="MemberJob" name="memberJob" type="text"  class="form-control input-md" value="<%= (memberVO.getMemberJob()==null)? "" : memberVO.getMemberJob()%>">
      </div>
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="MemberPhone">手機</label>  
  <div class="col-md-4">
  <div class="input-group">
    <input  id="MemberPhone" name="memberPhone" type="text"  class="form-control input-md" value="<%= (memberVO.getMemberPhone()==null)? "" : memberVO.getMemberPhone()%>">
      </div>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="MemberCountry">居住地</label>  
  <div class="col-md-4">
  <div class="input-group">
    <select id="MemberCountry" name="memberCountry" class="form-control input-md">
      <option value="1" ${(memberVO.memberCountry==1)?'selected':'' }>臺北市</option>
      <option value="2" ${(memberVO.memberCountry==2)?'selected':'' }>新北市</option>
      <option value="3" ${(memberVO.memberCountry==3)?'selected':'' }>桃園市</option>
      <option value="4" ${(memberVO.memberCountry==4)?'selected':'' }>臺中市</option>
      <option value="5" ${(memberVO.memberCountry==5)?'selected':'' }>臺南市</option>
      <option value="6" ${(memberVO.memberCountry==6)?'selected':'' }>高雄市</option>
      <option value="7" ${(memberVO.memberCountry==7)?'selected':'' }>基隆市</option>
      <option value="8" ${(memberVO.memberCountry==8)?'selected':'' }>新竹市</option>
      <option value="9" ${(memberVO.memberCountry==9)?'selected':'' }>嘉義市</option>
      <option value="10" ${(memberVO.memberCountry==10)?'selected':'' }>新竹縣</option>
      <option value="11" ${(memberVO.memberCountry==11)?'selected':'' }>苗栗縣</option>
      <option value="12" ${(memberVO.memberCountry==12)?'selected':'' }>彰化縣</option>
      <option value="13" ${(memberVO.memberCountry==13)?'selected':'' }>南投縣</option>
      <option value="14" ${(memberVO.memberCountry==14)?'selected':'' }>雲林縣</option>
      <option value="15" ${(memberVO.memberCountry==15)?'selected':'' }>嘉義縣</option>
      <option value="16" ${(memberVO.memberCountry==16)?'selected':'' }>屏東縣</option>
      <option value="17" ${(memberVO.memberCountry==17)?'selected':'' }>宜蘭縣</option>
      <option value="18" ${(memberVO.memberCountry==18)?'selected':'' }>花蓮縣</option>
      <option value="19" ${(memberVO.memberCountry==19)?'selected':'' }>臺東縣</option>
      <option value="20" ${(memberVO.memberCountry==20)?'selected':'' }>澎湖縣</option>
  </select>
      </div>
  </div>
</div>


<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="MemberIntroduce">自我介紹</label>
  <div class="col-md-4">                     
    <textarea class="form-control" rows="10"  id="MemberIntroduce" name="memberIntroduce"><%= (memberVO.getMemberIntroduce()==null)? "" : memberVO.getMemberIntroduce()%></textarea></textarea>
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" ></label>  
  <div class="col-md-4">
  
  <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> 送出</button>

  <button type="reset" class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign"></span> 清空</button>
    
  </div>
</div>
<input type="hidden" name="action" value="update_Member">
<input type="hidden" name="memberAccount" value="${memberVO.memberAccount}">
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
	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date memberBirthday = null;
  try {
	  memberBirthday = memberVO.getMemberBirthday();
   } catch (Exception e) {
	   memberBirthday = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
  img {
  	height: 100px;
    width: 100px;
      }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#MemberBirthday').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=memberBirthday%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#MemberBirthday').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#MemberBirthday').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
              var somedate1 = new Date('1970-01-01');
              var somedate2 = new Date();
              $('#MemberBirthday').datetimepicker({
                  beforeShowDay: function(date) {
                	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        		             ||
        		            date.getYear() >  somedate2.getYear() || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                      ) {
                           return [false, ""]
                      }
                      return [true, ""];
              }});
        
</script>
<script>
                    // 題目： 請製作可以同時上傳多張圖片到前端預覽的功能
                    // 學習重點：
                    // 1. File API – Read as Data URL
            
                    function init() {
            
                        // 1. 抓取DOM元素
                        let memberPhoto = document.getElementById("MemberPhoto");
                        let preview = document.getElementById('Preview');
            
                        // 2. 對myFile物件註冊change事件 - 改變選擇的檔案時觸發
                        memberPhoto.addEventListener('change', function(e) {
                            // 取得檔案物件的兩種方式
                            // 1. 直接從myFile物件上取得檔案物件 (因為非同步，一樣，多個classname註冊時會有問題)
                            // 2. 從event物件中取得他的soure target，也就是myFile物件，再取得檔案物件 
                            // 檔案的基本資訊，包括：檔案的名稱、大小與文件型態
                            let files = e.target.files;
                            // 判斷files物件是否存在
                            if (files !== null) {
                                let file = files[0];
                                    // 取出files物件的第i個
                                    // 判斷file.type的型別是否包含'image'
                                    if (file.type.indexOf('image') > -1) {
                                        
                                        // new a FileReader
                                        let reader = new FileReader();
                                        // 在FileReader物件上註冊load事件 - 載入檔案的意思
                                        reader.addEventListener('load', function(e) {
                                            // 取得結果 提示：從e.target.result取得讀取到結果
                                            let result = e.target.result;
                                            // console.log(result) 確認讀取到結果
                                            // 賦予src屬性
                                            preview.src=result;
                                        });
                                        // 使用FileReader物件上的 readAsDataURL(file) 的方法，傳入要讀取的檔案，並開始進行讀取
                                        reader.readAsDataURL(file); // trigger!!!!
                                    } else {
                                        // 彈出警告視窗 alert('請上傳圖片！');
                                        alert('請上傳圖片！');
                                    }
                            }
                        });
                    }
            
                    window.onload = init;
                </script>
</body>
</html>