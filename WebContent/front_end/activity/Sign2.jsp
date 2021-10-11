<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Beloved</title>
<style>
m1{
color:red;
}


</style>
</head>
	<li class="nav-item active">
<a class="nav-link" href="<%=request.getContextPath()%>/front_end/activity/ViewActivity.jsp">Home </span></a>
</li>
<body bgcolor="#4682B4">
<h1 class="ml1">
  <span class="text-wrapper">
    <span class="line line1"></span>
    <span class="letters">報名成功</span>
    <span class="line line2"></span>
  </span>
</h1>
<body>
    <div class="box1">
  <img src="<%=request.getContextPath()%>/front_end/activity/images/d6.jpg" alt="" style="display:block; margin:auto;width:30%;height:30%">
 
										
    </div>
     <div class="box2">
 
  <img src="<%=request.getContextPath()%>/front_end/activity/images/d3.jpg" alt="" style="display:block; margin:auto;width:30%;height:30%">
										
    </div>
    

<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>

<script>
var textWrapper = document.querySelector('.ml1 .letters');
textWrapper.innerHTML = textWrapper.textContent.replace(/\S/g, "<span class='letter'>$&</span>");

anime.timeline({loop: true})
  .add({
    targets: '.ml1 .letter',
    scale: [0.3,1],
    opacity: [0,1],
    translateZ: 0,
    easing: "easeOutExpo",
    duration: 600,
    delay:(el, i) =>70 * (i+1)
  }).add({
    targets: '.ml1 .line',
    scaleX: [0,1],
    opacity: [0.5,1],
    easing: "easeOutExpo",
    duration: 700,
    offset: '-=875',
    delay: (el, i, l) => 80 * (l - i)
  }).add({
    targets: '.ml1',
    opacity: 0,
    duration: 1000,
    easing: "easeOutExpo",
    delay: 1000
  });

</script>
</body>
</html>