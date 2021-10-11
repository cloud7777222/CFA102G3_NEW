<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>




<%
	MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) 存入req的mppVO物件 (包括幫忙取出的mppVO, 也包括輸入資料錯誤時的mppVO物件)
%>




<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	
<title>Blog Home</title>
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">				
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
	<link href="<%=request.getContextPath()%>/css/memPersonalPage.css" rel="stylesheet" />
</head>

<style>
	*{    		
		font-family: var(--bs-body-font-family);
		font-size: var(--bs-body-font-size);
		font-weight: var(--bs-body-font-weight);
		line-height: var(--bs-body-line-height);
		text-align: var(--bs-body-text-align);
		-webkit-text-size-adjust: 100%;
		-webkit-tap-highlight-color: rgba(0, 0, 0, 0);		
    	}
	body{
		margin: 50px;
	}
	img{
		margin-top: 30px; 
		margin-bottom: 30px;
		border: 1px solid #ddd;
	    border-radius: 4px;
	    padding: 5px;
	    width: 600px;
	    max-width: 100%;
	}
	i.fa-thumbs-up{
    		color: red;
    }
    i.fa-paper-plane{
    	color: #5E5A54;
    	padding-left: 10px;
    }
    button.like{
    		display: inline-flex;
    		background-color: white;
    		border: 2px solid white;
    	}
    	
    nav{
		margin-button: 20px;
	}
	li:hover{
		color: gold;	
</style>

</head>
<body bgcolor='white'>



		

	<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<h6>
					<div><h5>Post ${mppVO.postNo}<i class="far fa-paper-plane"></h5></i><div>
				</h6>
			</div>
			<div class="col-lg-6">
				<div class="small text-muted">${mppVO.postTime} 
			<div class="btn"><button class="like">		
				<i class="far fa-thumbs-up"> ${mppVO.numOfLike}</i>
			</button></div>
			</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-8">
				<img src="<%=request.getContextPath()%>/memPersonalPage/MemPersonalPageServlet?postNo=${mppVO.postNo}" />
			</div>
		</div>	
		<div class="row">	
			<div class="col-lg-6">
				<div><p>${mppVO.postContent}</p></div>
			</div>
		</div>
	</div>



	<!-- jQuery-->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>