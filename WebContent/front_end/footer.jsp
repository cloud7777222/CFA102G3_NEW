<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>

</head>

<body>

	<div class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="footer-contact">
                        <h2>哪裡可以找到我們?</h2>
                        <p><i class="fa fa-map-marker-alt"></i>台灣，桃園JAVA班</p>
<!--                         <p><i class="fa fa-phone-alt"></i>09+012 345 67890</p> -->
                        <p><i class="fa fa-envelope"></i>cfa102g3@gmail.com</p>
                        <div class="footer-social">
                            <a class="btn btn-custom" href="https://github.com/cloud7777222/CFA102G3_NEW"><i class="fab fa-github"></i></a>
                            <a class="btn btn-custom" href="<%=request.getContextPath()%>/front_end/index/index.jsp#contact"><i class="far fa-envelope"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="footer-link">
                        <h2>Popular Links</h2>
                        <a href="<%=request.getContextPath()%>/front_end/index/index.jsp#about">About Us</a>
                        <a href="<%=request.getContextPath()%>/front_end/index/index.jsp#contact">Contact Us</a>
                        <a href="<%=request.getContextPath()%>/front_end/prod/Shop.jsp">Shop</a>
<!--                         <a href="">Upcoming Events</a> -->
<!--                         <a href="">Latest Blog</a> -->
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="footer-link">
                        <h2>喜愛社交的您</h2>
                        <a href="<%=request.getContextPath()%>/front_end/dateappoorder/dater.jsp">交友約會</a>
                        <a href="<%=request.getContextPath()%>/front_end/activity/ViewActivity.jsp">活動</a>
                        <a href="<%=request.getContextPath()%>/front_end/post/post_main.jsp">論壇</a>
<!--                         <a href="">Help</a> -->
<!--                         <a href="">FQAs</a> -->
                    </div>
                </div>
<!--                 <div class="col-lg-3 col-md-6"> -->
<!--                     <div class="footer-newsletter"> -->
<!--                         <h2>Newsletter</h2> -->
<!--                         <form> -->
<!--                             <input class="form-control" placeholder="Email goes here"> -->
<!--                             <button class="btn btn-custom">Submit</button> -->
<!--                             <label>Don't worry, we don't spam!</label> -->
<!--                         </form> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
        </div>
        <div class="container copyright">
            <div class="row">
                <div class="col-md-6">
                    <p>&copy; <a href="#">BELOVED</a>, 交友網站.</p>
                </div>
                <div class="col-md-6">
                    <p>Designed By <a href="https://htmlcodex.com">CFA102G3</a></p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>