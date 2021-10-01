<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.dateappoorder.model.*"%>
<%
MemberService memberSvc = new MemberService();
MemberVO memberVO=memberSvc.getOneMember(Integer.valueOf(request.getParameter("memberNoB")));
DateappoorderVO dateappoorderVO = (DateappoorderVO) request.getAttribute("dateappoorderVO");
%>
<%-- <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" /> --%>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>BELOVED 會員預約修改</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="Free Website Template" name="keywords" />
    <meta content="Free Website Template" name="description" />

    <%@ include file="/front_end/pages/link.file" %>

    <link
      href="<%=request.getContextPath()%>/front_end/dateappoorder/css/date-time-picker-component.min.css"
      rel="stylesheet"
    />
    <link
      href="<%=request.getContextPath()%>/front_end/dateappoorder/css/demo.css"
      rel="stylesheet"
    />
    <style>
      .text p {
        width: 100%;
      }

      .carousel {
        background: #940a3f;
        min-height: 130px;
      }

      @media (max-width: 991.98px) {
        .carousel {
          min-height: 0;
        }
      }

      div.datetime-container button.date,
      div.datetime-container button.time,
      #select_datetime {
        background-color: #fdbe33;
        border: 1px solid white;
      }

      h2 {
        color: white;
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

      <!-- dating Start -->
      
      
      <%-- <%=((MemberVO) session.getAttribute("memberVO")).getMemberNo()%> --%>

      <div class="container">
        <div
          class="dater"
          data-parallax="scroll"
          data-image-src="img/dater.jpg"
        >
          <div class="row align-items-center">
            <div class="col-lg-7">
              <div class="dater-form">
                <form
                  method="post"
                  action="<%=request.getContextPath()%>/dateappoorder/dateappoorder.do"
                  id="dating"
                >
                  <h2>To:<%=memberVO.getMemberName() %></h2>
                  <div class="control-group">
                    <input
                      type="text"
                      class="form-control"
                      name="subject"
                      placeholder="subject"
                      required="required"
                    />
                  </div>
                  <div class="control-group">
                    <div id="select_datetime">
                      <input
                        type="hidden"
                        class="date_output"
                        name="dateAppoDate"
                      />
                    </div>
                  </div>
                  <div class="control-group">
                    <textarea
                      id="message"
                      class="form-control"
                      name="message"
                      placeholder="留下您的留言"
                      required="required"
                    ></textarea>
                  </div>
                  <div>


<!--                     <input type="text" name="memberNoA" value="6" /> -->
<!--                     <input type="text" name="memberNoB" value="3" /> -->
                    <input type="hidden" name="memberNoA"
                    value="<%=((MemberVO)session.getAttribute("memberVO")).getMemberNo()%>">
                    <input type="hidden" name="memberNoB"
                    value="<%=request.getParameter("memberNoB") %>">
                     <input type="hidden" name="dateOrderNo"
                    value="<%=request.getParameter("dateOrderNo") %>">
                    
                    <input type="hidden" name="action" value="update" />
                    <input
                      type="hidden"
                      name="requestURL"
                      value='<%=request.getParameter("requestURL")%>'
                    />
                    <input
                      type="hidden"
                      name="whichPage"
                      value='<%=request.getParameter("whichPage")%>'
                    />
                    <button id="check" class="btn btn-custom" type="button">
                     	 確認送出
                    </button>
                  </div>
                </form>
              </div>
            </div>
            <div class="col-lg-5">
              <div class="dater-content">
                <div class="section-header">
                  <p>預約中心</p>
                  <div class="text-center">
                    <c:if test="${not empty errorMsgs}">
                      <div class="alert alert-warning" role="alert">
                        	預約失敗:
                      </div>
                      <c:forEach var="message" items="${errorMsgs}">
                        <div class="alert alert-danger" role="alert">
                          ${message}
                        </div>
                      </c:forEach>
                    </c:if>
                  </div>
                  <h2>
                    hi, 
                    <%=((MemberVO)session.getAttribute("memberVO")).getMemberName()%>
                  </h2>
                </div>
                <div class="dater-text">
                  <p>請修改您想預約日期與時間</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- dating End -->
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

    <%@ include file="/front_end/pages/script.file" %>
    <script src="<%=request.getContextPath()%>/front_end/dateappoorder/js/date-time-picker-component.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/dateappoorder/js/demo.js"></script>

     <script>
      let date = new Date();
      const it = {
        jan: "一月",
        feb: "二月",
        mar: "三月",
        apr: "四月",
        may: "五月",
        jun: "六月",
        jul: "七月",
        aug: "八月",
        sep: "九月",
        oct: "十月",
        nov: "十一月",
        dec: "十二月",
        jan_: "一月",
        feb_: "二月",
        mar_: "三月",
        apr_: "四月",
        may_: "五月",
        jun_: "六月",
        jul_: "七月",
        aug_: "八月",
        sep_: "九月",
        oct_: "十月",
        nov_: "十一月",
        dec_: "十二月",
        mon: "週一",
        tue: "週二",
        wed: "週三",
        thu: "週四",
        fri: "週五",
        sat: "週六",
        sun: "週日",
        mon_: "週一",
        tue_: "週二",
        wed_: "週三",
        thu_: "週四",
        fri_: "週五",
        sat_: "週六",
        sun_: "週日",
        done: "Imposta",
      };



      new DateTimePickerComponent.DateTimePicker("select_datetime", {
        first_date: new Date(),
        start_date: "<%=dateappoorderVO.getDateAppoDate().toString().substring(0,19).replace(" ", "T") %>",
        last_date: new Date(
          date.getFullYear(),
          date.getMonth() + 1,
          date.getDate() - 1
        ),
        first_day_no: 0,
//         date_output: "timestamp",
        l10n: it,
        date_output: "full_ISO",
        styles: {
          active_background: "#fec601",
          active_color: "#000",
        },
      });
<%--       let dataA=<%=request.getAttribute("jsonDataA") %>; --%>
<%--       let dataB=<%=request.getAttribute("jsonDataB") %>; --%>
      
     

      function getNewData() {
          let jsonDataA = <%=request.getAttribute("jsonDataA") %>;
          let jsonDataB = <%=request.getAttribute("jsonDataB") %>;
// 	      console.log(jsonDataA);
// 	      console.log(jsonDataB);
          
          let newData = {};
          for (let i of jsonDataA) {
            let { dateOrderState, memberNoA, memberNoB, dateOrderNo, dateAppoDate } = i;
            let day = dateAppoDate.substring(0, 10);
            let time = dateAppoDate.substring(11, 12) == "0" ? dateAppoDate.substring(12, 13) : dateAppoDate.substring(11, 13);
            if (!newData[day]) {
              newData[day] = {};
            }
            newData[day][time] = i;
          }
          if (jsonDataB.length != 0) {
            for (let i of jsonDataB) {
              let { dateAppoDate } = i;
              let day = dateAppoDate.substring(0, 10);
              let time = dateAppoDate.substring(11, 12) == "0" ? dateAppoDate.substring(12, 13) : dateAppoDate.substring(11, 13);
              if (!newData[day]) {
                newData[day] = {};
              }
              if (!newData[day][time]) {
                newData[day][time] = "不可預約";
              }
            }
          }
          return newData
        }

        let data = getNewData();
        console.log(data);
        let d;
        let h;

        let domPicker = document.getElementById("select_datetime");
        let tr = null;
        let td = null;
        let getDayDom = document.getElementsByClassName("day selectable");
        let getHourDom = document.getElementsByClassName("hour selectable");
        // =======================抓選擇的day and hours==============
        // 抓day selectable DOM
        let selectDate = document.getElementById("select_datetime").getElementsByClassName("date_output")[0];
        //抓日期時間  
        let selectDateVal = selectDate.value;
        let getDay = selectDateVal.substring(0, 10);
        let getTime = selectDateVal.substring(11, 12) == "0" ? selectDateVal.substring(12, 13) : selectDateVal.substring(11, 13);
        // 該天訂單message
        let detial = null;


        // if (i != getDay) continue;
        // console.log("oooooooo");
        // =======================抓選擇的day and hours-end=================

        domPicker.addEventListener("click", () => {
          // =======================day顯示pink========================
          // reflash DOM
          getDayDom = document.getElementsByClassName("day selectable");
          getHourDom = document.getElementsByClassName("hour selectable");
          selectDate = document.getElementById("select_datetime").getElementsByClassName("date_output")[0];
          selectDateVal = selectDate.value;
          getDay = selectDateVal.substring(0, 10);
          getTime = selectDateVal.substring(11, 12) == "0" ? selectDateVal.substring(12, 13) : selectDateVal.substring(11, 13);
          console.log(getDayDom);
          console.log(getHourDom);
          console.log("getDay" + getDay)
          console.log("getTime" + getTime)
          d = getDay;
          h = getTime;

          for (let i in data) {
            let checkDay = i.substring(8, 9)=="0"
            	?i.substring(9, 10)
            	:i.substring(8, 10);
            // console.log("checkDay=" + checkDay);

            //1.判斷該天有行程
            dayToPink();
            function dayToPink() {
              for (let day of getDayDom) {
                if (checkDay == day.innerHTML) {
                  if (data[i].length == 18) {
                    //滿行程顯示灰色
                    day.style.backgroundColor = "gray";
                    day.classList.add("disabled");
                    day.classList.remove("selectable");
                    day.addEventListener("click", () => {
                      alert("該天已無可排時間");
                    })
                    // var clone = day.cloneNode(true);
                    // day=clone;
                  } else {
                    //有行程顯示pink
                    day.style.backgroundColor = "pink";
                    console.log("pink")
                  }

                }
              }
            }
          }

          hoursToPink();
          selectDate.value = selectDate.value.replace("T", " ");
        });




        // =======================hour顯示pink=========================================
        function hoursToPink() {
          //2.判斷該天小時行程
          for (let h in data[d]) {
            for (let hour of getHourDom) {
              let hourVal = hour.innerHTML;
              hourVal = hourVal.substring(0, 1) == "0" ? hourVal.substring(1, 2) : hourVal.substring(0, 2);
              console.log("h=" + h);
              console.log("hourVal=" + hourVal);
              if (h == hourVal.substring(0, 2)) {
                //有行程顯示pink
                hour.classList.add("disabled");
                hour.classList.remove("selectable");
                hour.style.backgroundColor = "pink";

                hour.addEventListener("click", () => {
                  detial = data[d][h];
                  detial = detial == "不可預約" ? "不可預約" : "您已有訂單編號 [" + detial["dateOrderNo"] + "] 的行程，請確認！"
                  // swal("您不可選擇這天!", detial, "warning");

                  swal({
                    title: "您不可選擇這天! 需要前往查看相關訊息嗎？",
                    text: detial,
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                  })
                    .then((willDelete) => {
                      if (willDelete) {
                        // 跳到該頁面
                        window.location.href="https://www.runoob.com/jsref/jsref-link.html";
                      } else {
                        swal("請重新選擇!");
                      }
                    });


                })
                if (data[d][h] == "不可預約") {
                  //不可選顯示gray
                  hour.classList.add("disabled");
                  hour.classList.remove("selectable");
                  hour.style.backgroundColor = "gray";
                  hour.addEventListener("click", () => {
                    swal("您不可選擇這天!", "請重新選擇", "error");
                  })

                }
                break;
              }
            }
          }
        }






        //0:無 1:約會 2:專家 3:活動 4:公休
        let type = ["無", "約會", "專家", "活動", "公休"];




        // ==========================btn================================
        console.log("抓日期時間  ");
        let btn = document.getElementById("check");
        btn.addEventListener("click", () => {
//           selectDate = document.getElementById("select_datetime").getElementsByClassName("date_output")[0];
//           //抓日期時間 
//           selectDateVal = selectDate.value;
//           getDay = selectDateVal.substring(0, 10);
//           getTime = selectDateVal.substring(11, 12) == "0" ? selectDateVal.substring(12, 13) : selectDateVal.substring(11, 13);
//           console.log(getDay)
//           console.log(getTime)
          d = getDay;
          h = getTime;
          try {

            let detial = data[d][h];
            if (detial == undefined) {
              console.log("可預約1")
              $("#dating").submit();
              return;
            } else {
              detial = detial == "不可預約" ? "不可預約" : "您已有約會訂單編號[" + detial["dateOrderNo"] + "]的行程，請確認！";
              let message = detial == "不可預約" ? "error" : "warning";
              swal("您不可選擇這天!", detial, message);
              console.log("detial");
              console.log(detial);
              return;
            }

          } catch (e) {
            console.log("可預約2");
            $("#dating").submit();
            return;
          }


          selectDate.value = selectDate.value.replace("T", " ");
        })

        

      // formate time	
      document.getElementById("message").onchange = function () {
        let dom = document
          .getElementById("select_datetime")
          .getElementsByClassName("date_output")[0];
        dom.value = dom.value.replace("T", " ");
        // 		console.log("hhhhhhh")
      };
      
    
    
      let newsText = ["大受歡迎", "名額有限", "成為受歡迎對象吧"];
      let i = 0;

      let carouselNews = window.setInterval(() => {
        $("#news").html(newsText[i++ % 3]);

        if (i % 2 == 0) {
          $("#news").addClass("run");
        } else {
          $("#news").removeClass("run");
        }
      }, 4000);
    </script>
  </body>
</html>
