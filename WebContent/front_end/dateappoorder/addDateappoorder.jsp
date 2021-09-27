<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<html>
  <head>
    <meta charset="utf-8" />
    <title>BELOVED 會員預約</title>
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
                >
                  <h2>To:${memberSvc.getOneMemberByNo(request.getParameter("memberNoB")) }</h2>
                  <div class="control-group">
                    <input
                      type="text"
                      class="form-control"
                      placeholder="Name"
                      required="required"
                    />
                  </div>
                  <div class="control-group">
                    <div id="select_datetime">
                      <input
                        type="text"
                        class="date_output"
                        name="dateAppoDate"
                      />
                    </div>
                  </div>
                  <div class="control-group">
                    <textarea
                      id="message"
                      class="form-control"
                      placeholder="留下您的留言"
                      required="required"
                    ></textarea>
                  </div>
                  <div>
                    <%--
                    <input
                      type="hidden"
                      name="memberNoA"
                      value="${memberVO.adNo}"
                    />
                    --%> <%--
                    <input
                      type="hidden"
                      name="memberNoB"
                      value="${memberVO.adNo}"
                    />
                    --%>

<!--                     <input type="text" name="memberNoA" value="6" /> -->
<!--                     <input type="text" name="memberNoB" value="3" /> -->
                    <input type="hidden" name="memberNoA"
                    value="<%=((MemberVO)session.getAttribute("memberVO")).getMemberNo()%>">
                    <input type="hidden" name="memberNoB"
                    value="<%=request.getParameter("memberNoB") %>">
                    
                    <input type="hidden" name="action" value="insert" />
                    <input
                      type="hidden"
                      name="requestURL"
                      value="<%=request.getServletPath()%>"
                    />
                    <button class="btn btn-custom" type="submit">
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
                  <p>請選擇您想預約日期與時間</p>
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
        start_date: new Date(
          date.getFullYear(),
          date.getMonth(),
          date.getDate() + 1,
          6,
          0
        ),
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

      // new DateTimePickerComponent.DateRangePicker('start_date', 'end_date', {
      //   min_range_hours: 48,
      // });

      // new DateTimePickerComponent.DateTimeRangePicker('start_date_time', 'end_date_time', {
      //   first_date: "2030-01-02T10:30:00",
      //   start_date: "2030-01-05T16:00:00",
      //   end_date: "2030-01-06T18:00:00",
      //   last_date: new Date(2030, 0, 29, 14, 0),
      //   first_day_no: 1,
      //   round_to: 5,
      //   date_output: "timestamp",
      //   styles: {
      //     active_background: '#e34c26',
      //     active_color: '#fff',
      //     inactive_background: '#0366d9',
      //     inactive_color: '#fff'
      //   },
      //   min_range_hours: 18
      // });

      // let domPicker = document.getElementById("select_date_2");

      let memA = {
        "2021-09-22": "444444444444444444444444",
        "2021-09-25": "444444444000044444444444",
        "2021-09-30": "444444444000044444444444",
        "2021-10-04": "444444444000011111111444",
        "2021-10-10": "444444444111144444444444",
        "2021-10-11": "444444444444444444444444",
      };

      let domPicker = document.getElementById("select_datetime");
      let tr = null;
      let td = null;

      domPicker.addEventListener("click", () => {
        // 抓day selectable DOM
        let getDayDom = document.getElementsByClassName("day selectable");

        // console.log(getDayDom[0].innerHTML);

        for (let i in memA) {
          let checkDay =
            i.substring(8, 9) == "0" ? i.substring(9, 10) : i.substring(8, 10);
          console.log("checkDay=" + checkDay);

          //判斷該天有行程
          for (day of getDayDom) {
            if (checkDay == day.innerHTML) {
              if (memA[i] == "444444444444444444444444") {
                console.log(checkDay + "=" + memA[i]);
                //滿行程顯示灰色
                day.style.backgroundColor = "gray";
                day.classList.add("disabled");
                day.classList.remove("selectable");
                day.addEventListener("click", () => {
                  alert("該天已無可排時間");
                });
                // var clone = day.cloneNode(true);
                // day=clone;
              }
              //有行程顯示黃色
              else console.log((day.style.backgroundColor = "yellow"));
            }
          }
        }
        // 抓day selectable DOM
        let getHourDom = document.getElementsByClassName("hour selectable");

        //抓日期時間
        let selectDate = document
          .getElementById("select_datetime")
          .getElementsByClassName("date_output")[0];
        let selectDateVal = selectDate.value;
        let getDay = selectDateVal.substring(0, 10);
        let getTime =
          selectDateVal.substring(11, 12) == "0"
            ? selectDateVal.substring(12, 13)
            : selectDateVal.substring(11, 13);
        //0:無 1:約會 2:專家 3:活動 4:公休
        let type = ["無", "約會", "專家", "活動", "公休"];

        //新增tr

        if (!tr) {
          tr = document.createElement("tr");
          td = document.createElement("td");
          td.setAttribute("colspan", "6");
          tr.append(td);
        }

        if (memA[getDay]) {
          // 999999999
          let i = 6;
          for (time of getHourDom) {
            let checkTime =
              time.innerHTML.substring(0, 1) == "0"
                ? time.innerHTML.substring(1, 2)
                : time.innerHTML.substring(0, 2);
            // console.log(getDay);
            // console.log(getTime);
            console.log(i + "點" + type[memA[getDay][i]]);

            console.log(checkTime);
            if (i == checkTime) {
              if (memA[getDay][i] != "4" && memA[getDay][i] != "0") {
                time.style.backgroundColor = "yellow";
                // time.classList.add("disabled");
                // time.classList.remove("selectable");
                // let p = document.createElement("p");
                // p.innerHTML="999";
                // time.append(p);
                // time.addEventListener("mouseover",()=>{
                //   console.log( type[memA[getDay][i]]);
                // })
                let tbodyDom = document.getElementsByTagName("tbody")[0];
                //  console.log("99999999999");
                //  console.log(tbodyDom);

                tbodyDom.append(tr);
                time.onmouseover = function () {
                  // console.log( type[memA[getDay][i]]);
                  // console.log(checkTime + "點" + type[memA[getDay][checkTime]]);
                  // console.log(memA[getDay][checkTime]);
                  td.innerHTML =
                    "time infomation: 您" +
                    checkTime +
                    "點，有" +
                    type[memA[getDay][checkTime]];

                  // time.onmouseout=function(){
                  //   tr.remove();
                  // }
                };
              } else if (memA[getDay][i] == "4") {
                time.style.backgroundColor = "gray";
              }
              i++;
            }
          }
        }
        selectDate.value = selectDate.value.replace("T", " ");
      });


      // formate time	
      document.getElementById("message").onchange = function () {
        let dom = document
          .getElementById("select_datetime")
          .getElementsByClassName("date_output")[0];
        dom.value = dom.value.replace("T", " ");
        // 		console.log("hhhhhhh")
      };

      document.querySelector("input[name='dateOrderDate']").value = Date.now();
      // 	console.log(document.querySelector("input[name='dateOrderDate']"));

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
