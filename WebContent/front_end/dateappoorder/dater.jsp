<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%-- <%@ page import="com.dateappoorder.model.*"%> --%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemberService memberService = new MemberService();
	boolean isNull = request.getAttribute("keyword")==null;
	List<MemberVO> list =null;
	if(isNull){
		
		list = memberService.getAllMember();
	}else{
		list = memberService.getAllMember().stream()
				.filter(i->i.toString().toLowerCase().indexOf(request.getAttribute("keyword").toString().toLowerCase())!=-1)
				.collect(Collectors.toList());
	}
	String[] country = {"國外","臺北市","新北市","桃園市","臺中市","臺南市","高雄市","基隆市","新竹市","嘉義市","新竹縣","苗栗縣","彰化縣","南投縣","雲林縣","嘉義縣","屏東縣","宜蘭縣","花蓮縣","臺東縣","澎湖縣"};
// 	List<MemberVO> list = isNull
// 			?(List<MemberVO>)request.getAttribute("keywordOfList")
// 					:memberService.getAllMember();
// 	try{
// 		list = (List<MemberVO>)request.getAttribute("keywordOfList");
// 	}catch(Exception e){
// 		list = memberService.getAllMember();
// 	}
		
// 	List<DateappoorderVO> list = dateappoorderSvc.getAll();

// 	DateappoorderService dateappoorderSvc = new DateappoorderService();
// 	boolean isNull = request.getAttribute("keyword") == null;
// 	List<DateappoorderVO> list = dateappoorderSvc.getAll();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("country", country);
%>
<%-- <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" /> --%>
	
<html>

<head>
    <meta charset="UTF-8">
    <title>交友資料</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free Website Template" name="keywords">
    <meta content="Free Website Template" name="description">

    <%@ include file="/front_end/pages/link.file" %>
        <style>
            .text p {
                width: 100%;
            }

            .carousel {
                background: #940a3f;
                min-height: 130px;
            }

            @media (max-width : 991.98px) {
                .carousel {
                    min-height: 0;
                }
            }

            .table-header {
                display: flex;
            }

            .table-header .section-left {
                width: 50%;
                text-align: left;
            }

            .table-header .section-right {
                width: 50%;
            }

            .table-header .section-right form {
                text-align: right;
            }

            .table-footer {
                text-align: center;
            }

            table {
                width: 100%;
            }

            .causes-img {
                height: 250px;
            }

            img {
                width: 100%;
                object-fit: cover;
            }

            .table-footer a {
                display: inline-block;
                width: 30px;
                height: 30px;
                cursor: pointer;
                line-height: 30px;
                border-radius: 5px;
            }

            table td {
                width: 120px;
                overflow-wrap: anywhere;
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





        <!-- Causes Start -->
        <div class="causes">
            <div class="container">
                <div class="section-header text-center">
                    <p>尋找感興趣的夥伴吧</p>
                    <h2>立即開始，不要猶豫</h2>
                    <%-- 錯誤表列 --%>
                        <div class="text-center">
                            <c:if test="${not empty errorMsgs}">

                                <div class="alert alert-warning" role="alert">請修正以下錯誤:</div>
                                <c:forEach var="message" items="${errorMsgs}">
                                    <div class="alert alert-danger" role="alert">
                                        ${message}</div>

                                </c:forEach>

                            </c:if>
                        </div>
                </div>
                <div class="table-header">
                    <div class="section-left">
                        <%@ include file="pages/page1.file" %>
                    </div>
                    <div class="section-right">

<!--                         <form -->
<!--                             class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"> -->
<!--                             <div class="input-group"> -->
<!--                                 <input type="text" name="keyword" id="keyword" -->
<!--                                     class="form-control bg-light border-0 small" placeholder="Search for..." -->
<!--                                     aria-label="Search" aria-describedby="basic-addon2"> -->
<!--                                 <div class="input-group-append"> -->
<!--                                     <button class="btn btn-primary" type="button" -->
<%--                                         onclick="keywordSearch('<%=request.getContextPath()%>/dateappoorder/dateappoorder.do')"> --%>
<!--                                         <i class="fas fa-search fa-sm"></i> -->
<!--                                     </button> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </form> -->
                        
                        <form METHOD="post" ACTION="<%=request.getContextPath()%>/dateappoorder/dateappoorder.do"
                            class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group">
                                <input type="text" name="keyword" id="keyword"
                                    class="form-control bg-light border-0 small" placeholder="Search for..."
                                    aria-label="Search" aria-describedby="basic-addon2">
                                <input type="hidden" name="action" value="getAll_For_Keyword">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="submit">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>

                        <% if (pageNumber> 1) {
                            %>
                            <FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">
                                <select size="1" name="whichPage">
                                    <% for (int i=1; i <=pageNumber; i++) { %>
                                        <option value="<%=i%>">跳至第<%=i%>頁
                                                <% } %>

                                </select>
                                <button type="submit" class="btn btn-primary">確定</button>
                            </FORM>
                            <% } %>
                    </div>
                </div>
                <div class="owl-carousel causes-carousel">

                    <c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                        <div class="causes-item" ${(param.dateOrderNo==dateappoorderVO.dateOrderNo)
                            ? "style='background-color: #fff3cd;'" : "" }>
                            <div class="member-no">
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do"
                                    style="margin-bottom: 0px;">
                                    <input type="hidden" name="memberNo" value="${memberVO.memberNo}">
                                    <input type="hidden" name="action" value="getOne_For_Display">

                                    <button type="submit" class="btn btn-info">${memberVO.memberNo}</button>
                                </FORM>
                            </div>
                            <div class="causes-img">
                                <c:if test="${not empty memberVO.memberPhoto}">
                                    <img
                                        src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}">
                                </c:if>
                            </div>
                            <div class="causes-progress">
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="85" aria-valuemin="0"
                                        aria-valuemax="100">
                                        <span>85%</span>
                                    </div>
                                </div>
                                <div class="progress-text">
                                    <p><strong>點擊次數:</strong> 0</p>
                                    <p><strong>全站點擊次數:</strong> 50000</p>
                                </div>
                            </div>
                            <div class="causes-text">
                                <h3>${memberVO.memberName}</h3>
                                <p>${memberVO.memberIntroduce}</p>
                                <div class="container">
                                    <div class="row">
                                        <div class="col-5">性別:${memberVO.memberGender=="1"?"男":"女" }</div>
                                        <div class="col-7">生日:${memberVO.memberBirthday}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-5">職業:${memberVO.memberJob}</div>
                                        <div class="col-7">居住地:${country[memberVO.memberCountry] }</div>
                                    </div>
                                </div>
                            </div>
                            <div class="causes-btn">
                                <a class="btn btn-custom">查看動態</a>
                                <FORM id="datingBtn${memberVO.memberNo}" METHOD="post" ACTION="<%=request.getContextPath()%>/memTime/memTime.do"
                                    style="margin-bottom: 0px;">
                                    <input type="hidden" name="memberNoB" value="${memberVO.memberNo}">
                                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<input type="hidden" name="action" value="datingCheck">
                                </FORM>
                                <a class="btn btn-custom" onclick="$('#datingBtn${memberVO.memberNo}').submit()">立即預約</a>
                                
                            </div>
                        </div>
                    </c:forEach>
                    

          			<div class="causes-item">
                   		<div class="team">
	                  		<div class="team-item">
	                            <div class="team-img">
	                                <img src="<%=request.getContextPath()%>/front_end/img/join.png" alt="Team Image">
	                            </div>
	                            <div class="team-text">
	                                <h2>join us</h2>
	                                <p>BELOVED information</p>
	                                <div class="team-social">
	                                    <a href=""><i class="fab fa-twitter"></i></a>
	                                    <a href=""><i class="fab fa-facebook-f"></i></a>
	                                    <a href=""><i class="fab fa-linkedin-in"></i></a>
	                                    <a href=""><i class="fab fa-instagram"></i></a>
	                                </div>
	                            </div>
	                        </div>
                            
                        </div>
					</div>
                    

                </div>
                <div class="table-footer">

                    <% if (rowsPerPage < rowNumber) { %>
                        <% if (pageIndex>= rowsPerPage) {
                            %>
                            <A href="<%=request.getRequestURI()%>?whichPage=1"><i
                                    class="fas fa-angle-double-left"></i></A>&nbsp; <A
                                href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>"><i
                                    class="fas fa-chevron-left"></i></A>&nbsp;
                            <% } %>
                                <c:forEach items="${list}" varStatus="tableCount" step="10">
<%--                                 	<c:if test="${list.size()}"> --%>
                                    	<a ${tableCount.count==param.whichPage?"style='color:red;'":""}
                                        href="<%=request.getRequestURI()%>?whichPage=${tableCount.count}">${tableCount.count}</a>
<%--                                 	</c:if> --%>
                                </c:forEach>

                                <% if (pageIndex < pageIndexArray[pageNumber - 1]) { %>
                                    <A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>"><i
                                            class="fas fa-chevron-right"> </i></A>&nbsp; <A
                                        href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>"><i
                                            class="fas fa-angle-double-right"> </i></A>&nbsp;
                                    <% } %>
                                        <% } %>
                </div>
            </div>
        </div>
        <!-- Causes End -->







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

                if (i % 2 == 0) {
                    $("#news").addClass("run");
                } else {
                    $("#news").removeClass("run");
                }
            }, 4000);


        </script>
</body>

</html>