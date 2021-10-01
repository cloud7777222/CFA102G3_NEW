<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dateappoorder.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	DateappoorderService dateappoorderSvc = new DateappoorderService();
// 	Integer userNo =((MemberVO) session.getAttribute("memberVO")).getMemberNo();
// dateappoorderSvc.getOneDateappoorder(dateOrderNo)
// 	boolean isNull = request.getAttribute("keyword") == null;
	List<DateappoorderVO> list = dateappoorderSvc.getAll();
	pageContext.setAttribute("list", list);
	request.setAttribute("date",new Date());
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

	
<html>

<head>
<meta charset="UTF-8">
<title>所有訂單資料</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free Website Template" name="keywords">
<meta content="Free Website Template" name="description">

<%@ include file="/front_end/pages/link.file"%>
<style>
*{
	text-align: center;
}
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

img {
	width: 100px;
	height: 100px;
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
.card-header{
	text-align:left;
}

table td {
	width: 120px;
	overflow-wrap: anywhere;
}

#close{
	width: 200px;
    height: 150px;
    position:fixed;
    top: 50%;
    left: 50%;
    margin-left: -100px;
    margin-top: -75px;
    border: 2px solid black;
    background-color: gray;
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
		

		<div id="layoutSidenav_content">
						<main>
							<div class="container-fluid px-4">
								<h1 class="mt-4">所有訂單資料</h1>
								<ol class="breadcrumb mb-4">
									<li class="breadcrumb-item"><a
										href="<%=request.getContextPath()%>/front_end/index/index.jsp">回首頁</a></li>
									<li class="breadcrumb-item active">Tables</li>
								</ol>
								<%-- 錯誤表列 --%>
								<div class="text-center">
									<c:if test="${not empty errorMsgs}">

										<div class="alert alert-warning" role="alert">請修正以下錯誤:</div>
										<c:forEach var="message" items="${errorMsgs}">
											<div class="alert alert-danger" role="alert">
												${message}
											</div>
										</c:forEach>

									</c:if>									
								</div>
							</div>
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> DataTable 約會
								</div>
								<div class="card-body">
									<div class="table-header">
										<div class="section-left">
											<%@ include file="pages/page1.file"%>
										</div>
										<div class="section-right">

<!-- 											<form -->
<!-- 												class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"> -->
<!-- 												<div class="input-group"> -->
<!-- 													<input type="text" name="keyword" id="keyword" -->
<!-- 														class="form-control bg-light border-0 small" -->
<!-- 														placeholder="Search for..." aria-label="Search" -->
<!-- 														aria-describedby="basic-addon2"> -->
<!-- 													<div class="input-group-append"> -->
<!-- 														<button class="btn btn-primary" type="button" -->
<%-- 															onclick="keywordSearch('<%=request.getContextPath()%>/dateappoorder/dateappoorder.do')"> --%>
<!-- 															<i class="fas fa-search fa-sm"></i> -->
<!-- 														</button> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</form> -->

											<%
												if (pageNumber > 1) {
											%>
											<FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">
												<select size="1" name="whichPage">
													<%
														for (int i = 1; i <= pageNumber; i++) {
													%>
													<option value="<%=i%>">跳至第<%=i%>頁
														<%
														}
													%>
													
												</select>
												<button type="submit" class="btn btn-primary">確定</button>
											</FORM>
											<%
												}
											%>
										</div>
									</div>
									<table <%=(list.size() == 0) ? "style='display: none;'" : ""%>>
										<thead>
											<tr>
												<th>約會訂單編號</th>
												<th>邀請人</th>
												<th>被邀請人</th>
												<th>訂單成立日期</th>
												<th>約會日期</th>
												<th>約會狀態</th>
												<th>留下評價</th>
												<th>約會評價</th>
												<th>修改</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>約會訂單編號</th>
												<th>邀請人</th>
												<th>被邀請人</th>
												<th>訂單成立日期</th>
												<th>約會日期</th>
												<th>約會狀態</th>
												<th>留下評價</th>
												<th>約會評價</th>
												<th width="25%">修改</th>
											</tr>
										</tfoot>
										<tbody>

											<c:forEach var="dateappoorderVO" items="${list}" begin="<%=pageIndex%>"
												end="<%=pageIndex+rowsPerPage-1%>">

												<tr ${(param.dateOrderNo == dateappoorderVO.dateOrderNo) ? "style='background-color: #fff3cd;'" : "" }>
													<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/dateappoorder/dateappoorder.do"
														style="margin-bottom: 0px;">
														<input type="hidden" name="dateOrderNo" value="${dateappoorderVO.dateOrderNo}">
														<input type="hidden" name="action" value="getOne_For_Display">
														
														<button type="submit" class="btn btn-info">${dateappoorderVO.dateOrderNo}</button>
													</FORM>
													</td>
													<td>${memberSvc.getOneMember(dateappoorderVO.memberNoA).memberName }</td>
													<td>${memberSvc.getOneMember(dateappoorderVO.memberNoB).memberName }</td>
													<td>${dateappoorderVO.dateOrderDate}</td>
													<td>${dateappoorderVO.dateAppoDate}</td>
													<td ${dateappoorderVO.dateOrderState==0?"style='color:red;'":"style='color:blue;'"}>${dateappoorderVO.dateOrderState==1?"預約成立":"預約取消"}</td>
<!-- 												    ((MemberVO)session.getAttribute("memberVO")).getMemberNo() -->
													<td>${sessionScope.memberVO.memberNo==dateappoorderVO.memberNoA?dateappoorderVO.dateStarRateA:dateappoorderVO.dateStarRateB}</td>
													<td>${dateappoorderVO.dateCE}</td>
													<td>

													<div class="container">
												        <div class="row">
									        				<c:if test="${dateappoorderVO.dateOrderState==1 && dateappoorderVO.dateAppoDate>date}">
													            <div class="col">
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/dateappoorder/dateappoorder.do"
																		style="margin-bottom: 0px;">
																		<input type="hidden" name="dateOrderNo" value="${dateappoorderVO.dateOrderNo}">
																		<input type="hidden" name="memberNoB" value="${dateappoorderVO.memberNoB}">
																		<input type="hidden" name="action"
																			value="getOne_For_Update"> <input type="hidden"
																			name="requestURL" value="<%=request.getServletPath()%>">
																		<input type="hidden" name="whichPage"
																			value="<%=whichPage%>">
																			<button type="submit" class="btn btn-success">修改日期</button>
																	</FORM>
													            </div>
															</c:if>
															<c:if test="${dateappoorderVO.dateOrderState==1 && dateappoorderVO.dateAppoDate>date}">
													            <div class="col">
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/dateappoorder/dateappoorder.do"
																		style="margin-bottom: 0px;">
																		<input type="hidden" name="dateOrderNo" value="${dateappoorderVO.dateOrderNo}">
																		<input type="hidden" name="memberNoB" value="${dateappoorderVO.memberNoB}">
																		<input type="hidden" name="action"
																			value="getOne_For_Update"> <input type="hidden"
																			name="requestURL" value="<%=request.getServletPath()%>">
																		<input type="hidden" name="whichPage"
																			value="<%=whichPage%>">
																			<button type="submit" class="btn btn-danger">取消預約</button>
																	</FORM>
													            </div>
															</c:if>
													        
												            
															<c:if test="${dateappoorderVO.dateOrderState==1 && dateappoorderVO.dateAppoDate<date}">
												            	<div class="col">
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/dateappoorder/dateappoorder.do"
																		style="margin-bottom: 0px;">
																		<input type="hidden" name="dateOrderNo" value="${dateappoorderVO.dateOrderNo}">
																		<input type="hidden" name="memberNoB" value="${dateappoorderVO.memberNoB}">
																		<input type="hidden" name="action"
																			value="getOne_For_Update"> <input type="hidden"
																			name="requestURL" value="<%=request.getServletPath()%>">
																		<input type="hidden" name="whichPage"
																			value="<%=whichPage%>">
																			<button type="submit" class="btn btn-info">留下評價</button>
																	</FORM>
												            	</div>
															</c:if>
															  
												        </div>
												    </div>
									

														
														
													</td>
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="table-footer">

										<%
											if (rowsPerPage < rowNumber) {
										%>
										<%
											if (pageIndex >= rowsPerPage) {
										%>
										<A href="<%=request.getRequestURI()%>?whichPage=1"><i
											class="fas fa-angle-double-left"></i></A>&nbsp; <A
											href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>"><i
											class="fas fa-chevron-left"></i></A>&nbsp;
										<%
											}
										%>
										<c:forEach items="${list}" varStatus="tableCount" step="10">
											<a ${tableCount.count==param.whichPage?"style='color:red;'":""}
												href="<%=request.getRequestURI()%>?whichPage=${tableCount.count}">${tableCount.count}</a>
										</c:forEach>

										<%
											if (pageIndex < pageIndexArray[pageNumber - 1]) {
										%>
										<A
											href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>"><i
											class="fas fa-chevron-right"> </i></A>&nbsp; <A
											href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>"><i
											class="fas fa-angle-double-right"> </i></A>&nbsp;
										<%
											}
										%>
										<%
											}
										%>
									</div>
								</div>
							</div>
						</main>
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