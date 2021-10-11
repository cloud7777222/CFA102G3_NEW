<!DOCTYPE HTML>
<html lang=”en-US”>
<head>
<meta charset=UTF-8″>
<title>javascript星級評分</title>
<style type=”text/css”>
*{margin:0;padding:0;}
.wrapper{height:20px;padding:5px;width:130px;margin:100px auto 10px;}
a{float:left;width:26px;height:20px;background:url(star.png) 0 -20px no-repeat;}
p{font:24px SimSun;width:130px;margin-left:auto;margin-right:auto;}
</style>
</head>
<body>
<div class=”wrapper”>
<a href=”javascript:;”></a>
<a href=”javascript:;”></a>
<a href=”javascript:;”></a>
<a href=”javascript:;”></a>
<a href=”javascript:;”></a>
</div>
<p></p>
</body>
</html>
<script type=”text/javascript”>
window.onload = function(){
var star = document.getElementsByTagName(‘a’);
var oDiv = document.getElementsByTagName(‘div’)[0];
var temp = 0;
for(var i = 0, len = star.length; i < len; i ){
star[i].index = i;
star[i].onmouseover = function(){
clear();
for(var j = 0; j < this.index 1; j ){
star[j].style.backgroundPosition = ‘0 0’;
}
}
star[i].onmouseout = function(){
for(var j = 0; j < this.index 1; j ){
star[j].style.backgroundPosition = ‘0 -20px’;
}
current(temp);
}
star[i].onclick = function(){
temp = this.index 1;
document.getElementsByTagName(‘p’)[0].innerHTML = temp ‘ 顆星’;
current(temp);
}
}
//清除所有
function clear(){
for(var i = 0, len = star.length; i < len; i ){
star[i].style.backgroundPosition = ‘0 -20px’;
}
}
//顯示當前第幾顆星
function current(temp){
for(var i = 0; i < temp; i ){
star[i].style.backgroundPosition = ‘0 0’;
}
}
};
</script>