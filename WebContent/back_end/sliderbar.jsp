<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Page Wrapper -->
	<div id="wrapper">
<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="<%=request.getContextPath()%>/back_end/index/index.jsp">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="far fa-heart"></i>
				</div>
				<div class="sidebar-brand-text mx-3">
					後台管理系統<p>BELOVE</p>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="<%=request.getContextPath()%>/back_end/index/index.jsp"> <i class="fas fa-home"></i> <span>回首頁</span></a>
			</li>	
				<li class="nav-item active"><a class="nav-link"
				href="<%=request.getContextPath()%>/emp/emploginout.do?action=logout"><i class="fas fa-sign-out-alt fa-sm fa-fw"></i><span>登出</span></a>
			</li>	

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">前台頁面管理</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>會員</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/member/select_page.jsp">會員複合查詢</a>
					</div>
				</div></li>
			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>活動</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/activity/addActivity.jsp">新增活動</a>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/activity/listAllActivity.jsp">顯示全部</a>
					</div>
				</div></li>
			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>商品種類</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/prodsort/addProdsort.jsp">新增商品種類</a>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/prodsort/listAllProdsort.jsp">顯示全部商品種類</a>
					</div>
				</div></li>

			<!-- Nav Item - Utilities Collapse Menu -->
				<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>商品</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/prod/addProd.jsp">新增商品</a>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/prod/listAllProd.jsp">顯示全部商品</a>
						
					</div>
				</div></li>

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>訂單</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/order/listAllOrder.jsp">顯示全部訂單</a>
					</div>
				</div></li>

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>員工</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/emp/addEmp.jsp">新增員工</a>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/emp/listAllEmp.jsp">顯示全部員工</a>
					</div>
				</div></li>

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>廣告</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/ad/addAd.jsp">新增廣告</a>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/ad/listAllAd.jsp">顯示全部廣告</a>
					</div>
				</div></li>
				<!-- Nav Item - Utilities Collapse Menu -->
				<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>會員個人頁面</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/memPersonalPage/select_page.jsp">顯示全部個人頁面</a>
					</div>
				</div></li>
			<!-- Nav Item - Utilities Collapse Menu -->
				<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>文章種類</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
					    <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/postType/select_page.jsp">文章種類複合查詢</a>
					</div>
				</div></li>
			<!-- Nav Item - Utilities Collapse Menu -->
				<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="far fa-edit"></i> <span>文章和留言</span>
			</a>
				<div class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
					    <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/post/select_page.jsp">文章查詢</a>
					    <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/postMessage/listAllPostMessage.jsp">查詢所有文章留言</a>
					</div>
				</div></li>
			
			
			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
		
			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

			
		</ul>
		<!-- End of Sidebar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">