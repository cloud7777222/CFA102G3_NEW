<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*"%>

<%
	AdVO adVO = (AdVO) request.getAttribute("adVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>廣告資料新增 - addAd.jsp</title>


<%@ include file="/back_end/pages/link.file"%>


<style>
* {
	box-sizing: border-box;
}

#myPic img {
	width: 50%;
	margin-left: 25%;
	margin-right: 25%;
}

img {
	width: 100%;
	margin: 30px;
	object-fit: cover;
	cursor: pointer;
}

#preview p {
	text-align: center;
}

input, textarea, button {
	width: 100%;
}

#bigPic {
	display: none;
	position: fixed;
	width: 40%;
	height: 40%;
	top: 50%;
	left: 50%;
	margin-top: -10%;
	/* 	margin-left: -20%; */
	border: 1px solid gray;
	z-index: 999;
}

#bigPic img {
	width: 100%;
	height: 100%;
	margin: 0;
	box-sizing: border-box;
	cursor: default;
	object-fit: cover;
	padding: 0;
}

#bigPic .close {
	position: absolute;
	top: -20px;
	right: -20px;
	color: black;
	width: 20px;
	height: 20px;
	border-radius: 50%;
	border: black;
	z-index: 999;
	cursor: pointer;
}
</style>

</head>

<body id="page-top">
	<div id="bigPic">
		<div class="close" onclick="closePic()">
			x <i class="fas fa-times">x</i>
		</div>
	</div>

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="../sidebar.jsp" flush="true" />
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="/back_end/topbar.jsp" flush="true" />
				<!-- End of Topbar -->

				<!-- Begin Page Content -->

				<!-- ajax Page Content -->
				<div class="showDoRequestPage"></div>
				<!-- end of ajax Page Content -->
				<div class="container-fluid">


					<!-- Page Heading 修改以下●●●-->
					<div class="container-fluid px-4">
						<h1 class="mt-4">新增廣告資料</h1>
						<ol class="breadcrumb mb-4">
							<li class="breadcrumb-item"><a
								href="<%=request.getContextPath()%>/back_end/index/index.jsp">回首頁</a></li>
							<li class="breadcrumb-item active">ADD</li>
						</ol>
						<div class="card o-hidden border-0 shadow-lg my-5">
							<div class="card-body p-0">
								<!-- Nested Row within Card Body -->
								<div class="row">
									<div class="col-lg-5 d-none d-lg-block bg-register-image"
										id="preview">
										<p>圖片上傳預覽:</p>
										<div id="myPic"></div>
									</div>
									<div class="col-lg-7">
										<div class="p-5">
											<div class="text-center">
												<c:if test="${not empty errorMsgs}">

													<div class="alert alert-warning" role="alert">
														請修正以下錯誤:</div>
													<c:forEach var="message" items="${errorMsgs}">
														<div class="alert alert-danger" role="alert">
															${message}</div>

													</c:forEach>

												</c:if>
											</div>
											<form METHOD="post"
												ACTION="<%=request.getContextPath()%>/ad/ad.do" name="form1"
												enctype="multipart/form-data">
												<div class="form-group">
													<input type="TEXT" name="adTitle" size="20" class="form-control form-control-user"
														value="<%=(adVO == null) ? "請輸入廣告標題" : adVO.getAdTitle()%>"
														placeholder="廣告標題" />
												</div>
												<div class="form-group">
													<textarea class="form-control form-control-user" name="ad" cols="30" rows="10" placeholder="廣告內容"><%=(adVO == null) ? "請輸入廣告內容" : adVO.getAd()%></textarea>
												</div>
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<p>請上傳圖片檔案:3張</p>
														<label onchange="changePic(event)"> <!-- 這裡一定要有一個<input type="file">的元素，當上傳點 -->
															<input type="file" name="upfile1" multiple>
														</label> <input type="hidden" name="adPic1"
															value="<%=(adVO == null) ? "請添加廣告圖1" : adVO.getAdPic1()%>" />
														<input type="hidden" name="adPic2"
															value="<%=(adVO == null) ? "請添加廣告圖2" : adVO.getAdPic2()%>" />
														<input type="hidden" name="adPic3"
															value="<%=(adVO == null) ? "請添加廣告圖3" : adVO.getAdPic3()%>" />
													</div>
													<div class="col-sm-6">
														<p>檔案名稱：</p>
														<div id="filename"></div>
													</div>
												</div>
												<div class="form-group">
													<!-- 													<input type="number" name="adState" size="1" -->
													<%-- 														value="<%=(adVO == null) ? "1" : adVO.getAdState()%>" --%>
													<!-- 														placeholder="上下架" /> -->


													<label for="up">上架<input type="radio"
														name="adState" id="up" value="1"
														<%=((adVO == null) || ("1".equals(adVO.getAdState().toString()))) ? "checked" : ""%>></label>
													<label for="down">下架<input type="radio"
														name="adState" id="down" value="0"
														<%=((adVO != null) && ("0".equals(adVO.getAdState().toString()))) ? "checked" : ""%>></label>

												</div>
												<div class="form-group row">
													<div class="col-sm-6 ">
														<input class="form-control form-control-user" name="postTime" id="f_date1" type="text"
															placeholder="發佈日期">
													</div>
													<div class="col-sm-6">
														<input class="form-control form-control-user" name="deadline" id="f_date2" type="text"
															placeholder="截止日期">
													</div>
												</div>
												<hr>
												<div class="form-group">
													<input type="hidden" name="action" value="insert">
													<button type="submit" class="btn btn-primary">送出新增</button>
												</div>

											</form>

										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<jsp:include page="/back_end/footer.jsp" flush="true" />
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/back_end/pages/script.file"%>


</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date postTime = null;
	java.sql.Date deadline = null;
	try {
		postTime = adVO.getPostTime();
	} catch (Exception e) {
		postTime = new java.sql.Date(System.currentTimeMillis());
	}
	try {
		deadline = adVO.getDeadline();
	} catch (Exception e) {
		deadline = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=postTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       value: '<%=postTime%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
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
	//      $('#f_date1').datetimepicker({
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
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
<script>
      function changePic(e) {
        let myFile = document.getElementById("myFile");
        let filename = document.getElementById("filename");
        let myPic = document.getElementById("myPic");
        let files = e.target.files;

        // console.log(myFile);
        if (files != null) {
          let showName = "";
          console.log(files);
          for (file of files) {
        	  console.log(file.name);
        	  showName += "<p>"+file.name+"</p>";
            if (file.type.indexOf("image") == -1) {
              alert(
                `${file.name}格式錯誤，請放入 image or jpg or png 格式圖片`
              );
              closePic();
              return;
            }
            if (files.length > 3) {
              alert(`檔案數為${files.length}個，請放入 3張圖片`);
              closePic();
              return;
            }
            let reader = new FileReader();
            // 在FileReader物件上註冊load事件 - 載入檔案的意思
            reader.addEventListener("load", function (e) {
              // 取得結果 提示：從e.target.result取得讀取到結果
              let result = e.target.result;
              // console.log(result) 確認讀取到結果
              // 新增img元素
              let img = document.createElement("img");
              // 賦予src屬性
              img.src = result;
              // 放到div裡面
              myPic.append(img);
              img.onclick = smalTobig;
            });
            // 使用FileReader物件上的 readAsDataURL(file) 的方法，傳入要讀取的檔案，並開始進行讀取
            reader.readAsDataURL(file); // trigger!!!!
          }
          console.log(showName);
          filename.innerHTML = showName;
        }
      }
      function smalTobig(event) {
        console.log(event.target.src);
        let bigPic = document.getElementById("bigPic");
        let img = document.createElement("img");
        bigPic.innerHTML=`<div class="close" onclick="closePic()">
        	<i class="fas fa-times"></i>
      </div>`;
        bigPic.style.display = "block";
        // 賦予src屬性
        img.src = event.target.src;
        // 放到div裡面

        bigPic.append(img);
      }
      function closePic() {
        let bigPic = document.getElementById("bigPic");
        bigPic.style.display = "none";
        bigPic.innerHTML = `<div class="close" onclick="closePic()"><i class="fas fa-times"></i></div>`;
      }
    </script>
</html>