<%@page import="com.member.model.MemberService"%>
<%@page import="com.activity.model.*"%>
<%@page import="com.prod.model.*"%>
<%@page import="com.ad.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>belove後台管理系統</title>

<!-- Custom fonts for this template-->
<%@ include file="/back_end/pages/link.file"%>

</head>

<body>

	

		<!-- Sidebar -->
		<jsp:include page="/back_end/sliderbar.jsp" flush="true" />
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		
		             <div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">後臺資料</h1>
					</div>

					<!-- Content Row -->
					<div class="row">

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-primary text-uppercase mb-1">
												會員總數</div>
												<% 
													MemberService memberSvc = new MemberService();
													int membernumber = memberSvc.getAllMember().size();
												%>
											<div class="h5 mb-0 font-weight-bold text-gray-800"><%=membernumber%></div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-success text-uppercase mb-1">
												活動總數</div>
												<% 
													ActivityService activitySvc = new ActivityService();
													int activitynumber = activitySvc.getAll().size();
												%>
											<div class="h5 mb-0 font-weight-bold text-gray-800"><%=activitynumber%></div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-info shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1">商品總數
											</div>
											<%
												ProdService prodSvc = new ProdService();
												int prodnum = prodSvc.getAll().size();
											%>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><%=prodnum%></div>
												</div>
												
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Pending Requests Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-warning text-uppercase mb-1">
												廣告總數</div>
												<%
													AdService adSvc = new AdService();
													int adnum = adSvc.getAll().size();
												%>
											<div class="h5 mb-0 font-weight-bold text-gray-800"><%=adnum%></div>
										</div>
										<div class="col-auto">
											<i class="fas fa-comments fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						
						</div>
					</div>
				</div>
				<p><jsp:include page="/back_end/footer.jsp" flush="true" /></p>			

	<%@ include file="/back_end/pages/script.file"%>

</body>
</html>