<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<%@ include file="/back_end/header.jsp"%>
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
<body>
<%@ include file="/back_end/sliderbar.jsp"%>
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
				<c:if test="${not empty errorMsgs}">
					<div class="card mb-4">
						<div class="card-body">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
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
										<td><img src="${adVO.adPic1}"></td>
										<td><img src="${adVO.adPic2}"></td>
										<td><img src="${adVO.adPic3}"></td>
										<td ${adVO.adState==0?"style='color:red;'":"style='color:blue;'" }>${adVO.adState==0?"下架":"上架"}</td>
										<td>${adVO.postTime}</td>
										<td>${adVO.deadline}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/ad/ad.do"
												style="margin-bottom: 0px;">
												<input type="hidden" name="adNo" value="${adVO.adNo}">
												<input type="hidden" name="action" value="getOne_For_Update">
												<input
													type="hidden" name="requestURL"
													value="<%=request.getServletPath()%>">
													<input
													type="hidden" name="whichPage"
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
												<input
													type="hidden" name="requestURL"
													value="<%=request.getServletPath()%>">
													<input
													type="hidden" name="whichPage"
													value="<%=whichPage%>">
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
			</div>
		</main>
	</div>
	<%@ include file="/back_end/pages/script.file"%>
	<%@ include file="/back_end/footer.jsp"%>
	<script>
	$(document).ready(()=>{
		if($(".a-<%=whichPage%>")){
			
			$(".a-<%=whichPage%>").css({"color":"red","backgroundColor":"#1cc88a"});
		}
	})
	</script>
</body>
</html>