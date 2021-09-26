<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdService adSvc = new AdService();
	boolean isNull = request.getAttribute("keyword") == null;
	List<AdVO> list = isNull
			? adSvc.getAll()
			: adSvc.getAllByKeyword(request.getAttribute("keyword").toString());
	pageContext.setAttribute("list", list);
%>
<%-- <jsp:useBean id="adSvc" scope="page" class="com.ad.model.AdService" /> --%>

<html>
<head>
<title>所有廣告資料</title>
<%-- <%=request.getContextPath() + request.getServletPath()%> --%>

<%@ include file="/back_end/pages/link.file"%>


<style>
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

table td {
	width: 120px;
	overflow-wrap: anywhere;
}
</style>
</head>

<body id="page-top">

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

				<!-- ajax Page Content -->
				<div class="showDoRequestPage"></div>
				<!-- end of ajax Page Content -->

				<!-- Begin Page Content -->

				<div class="container-fluid">


					<!-- Page Heading 修改以下●●●-->
					<div id="layoutSidenav_content">
						<main>
							<div class="container-fluid px-4">
								<h1 class="mt-4">所有廣告資料</h1>
								<ol class="breadcrumb mb-4">
									<li class="breadcrumb-item"><a
										href="<%=request.getContextPath()%>/back_end/index/index.jsp">回首頁</a></li>
									<li class="breadcrumb-item active">Tables</li>
								</ol>
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
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-table me-1"></i> DataTable Example
								</div>
								<div class="card-body">
									<div class="table-header">
										<div class="section-left">
											<%@ include file="pages/page1.file"%>
										</div>
										<div class="section-right">

											<form
												class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
												<div class="input-group">
													<input type="text" name="keyword" id="keyword"
														class="form-control bg-light border-0 small"
														placeholder="Search for..." aria-label="Search"
														aria-describedby="basic-addon2">
													<div class="input-group-append">
														<button class="btn btn-primary" type="button"
															onclick="keywordSearch('<%=request.getContextPath()%>/ad/ad.do')">
															<i class="fas fa-search fa-sm"></i>
														</button>
													</div>
												</div>
											</form>

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
												<th>廣告編號</th>
												<th>廣告標題</th>
												<th>廣告敘述</th>
												<th>圖片1</th>
												<th>圖片2</th>
												<th>圖片3</th>
												<th>廣告狀態</th>
												<th>發佈日</th>
												<th>截止日</th>
												<th>修改</th>
												<th>刪除</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>廣告編號</th>
												<th>廣告標題</th>
												<th>廣告敘述</th>
												<th>圖片1</th>
												<th>圖片2</th>
												<th>圖片3</th>
												<th>廣告狀態</th>
												<th>發佈日</th>
												<th>截止日</th>
												<th>修改</th>
												<th>刪除</th>
											</tr>
										</tfoot>
										<tbody>

											<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>"
												end="<%=pageIndex+rowsPerPage-1%>">

												<tr ${(param.adNo == adVO.adNo) ? "style='background-color: #fff3cd;'" : "" }>
													<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/ad/ad.do"
														style="margin-bottom: 0px;">
														<input type="hidden" name="adNo" value="${adVO.adNo}">
														<input type="hidden" name="action" value="getOne_For_Display">
														
														<button type="submit" class="btn btn-info">${adVO.adNo}</button>
													</FORM>
													</td>
													<td>${adVO.adTitle}</td>
													<td>${adVO.ad}</td>
													<td><img src="${adVO.adPic1}"></td>
													<td><img src="${adVO.adPic2}"></td>
													<td><img src="${adVO.adPic3}"></td>
													<td>${adVO.adState}</td>
													<td>${adVO.postTime}</td>
													<td>${adVO.deadline}</td>
													<td>
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/ad/ad.do"
															style="margin-bottom: 0px;">
															<input type="hidden" name="adNo" value="${adVO.adNo}">
															<input type="hidden" name="action"
																value="getOne_For_Update"> <input type="hidden"
																name="requestURL" value="<%=request.getServletPath()%>">
															<input type="hidden" name="whichPage"
																value="<%=whichPage%>">
															<button type="submit" class="btn btn-success">修改</button>
														</FORM>
													</td>
													<td>
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/ad/ad.do"
															style="margin-bottom: 0px;">
															<input type="hidden" name="adNo" value="${adVO.adNo}">
															<input type="hidden" name="action" value="delete">
															<input type="hidden" name="requestURL"
																value="<%=request.getServletPath()%>"> <input
																type="hidden" name="whichPage" value="<%=whichPage%>">
															<button type="submit" class="btn btn-danger">刪除</button>
														</FORM>
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
											<a class="a-${tableCount.count}"
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

	<script>
	//post請求
	function keywordSearch(path) {
	  $.ajax({
	    url: path,
	    type: "post",
	    data: { action: "getAll_For_Keyword", keyword: $("#keyword").val() },
	    success: function (data) {
	      $(".container-fluid").hide();
	      $(".showDoRequestPage").html(data);
// 	console.log(data);
	    }
	  });
// 	console.log($("#keyword").val());
	}
	//===============================================================
	$(document).ready(()=>{
		if($(".a-<%=whichPage%>")){
			
			$(".a-<%=whichPage%>").css({"color":"red","backgroundColor":"#1cc88a"});
		}
	})
	</script>


</body>
</html>