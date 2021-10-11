<%@page import="javax.swing.text.Style"%>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
      <%@ page import="java.util.*" %>
     <%@ page import="java.text.SimpleDateFormat" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
  <head>
  <li class="nav-item active">
<a class="nav-link" href="<%=request.getContextPath()%>/front_end/activity/ViewActivity.jsp">Home </span></a>
</li>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Beloved Activity 留言板</title>
 <style>
 .wrapper{width:300px; margin:10px auto; font:14px/1.5 arial;bgcolor:lightblue;}
/*tab*/
#star{overflow:hidden;}
#star li{
float:left;
width:20px;
height:20px;
margin:2px;
display:inline;
color:#999;
font:bold 18px arial;
cursor:pointer
}
#star .act{
color:#c00
}
#star_word{
width:80px;
height:30px;
line-height:30px;
border:1px solid #ccc;
margin:10px;
text-align:center;
display:none
}
 </style>
 </head>
 <body>
 <%= "<font size='36'><b><i>Beloved Activity 留言板</i></b></font>" %>
 <%! String str= ""; %>
 <%
 String bt = new String( request.getParameter("actName").getBytes("ISO-8859-1"),"UTF-8");
 String nr = new String( request.getParameter("neirong").getBytes("ISO-8859-1"),"UTF-8");
 String xm = new String( request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
 
 Date date = new Date();
 SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 String time = da.format(date);
 if(bt.isEmpty()){
     str += "<br>活動名稱：交友" + "<br>留言內容：" + nr + "<br>發布者：" + xm + "<br>發布時間：" + time + "<hr>";
 }else{
     str += "<br>活動名稱：" + bt + "<br>留言內容：" + nr + "<br>發布者：" + xm + "<br>發布時間：" + time + "<hr>";
 }
 %><br>
<h > 評價结果</h>
<span id="result"></span>
<ul id="star">
<li>★</li>
<li>★</li>
<li>★</li>
<li>★</li>
<li>★</li>
</ul>
					
				
 <%= str %>
 
 </body>
<script>


window.onload = function(){
 var star = document.getElementById("star");
 var star_li = star.getElementsByTagName("li");
 var star_word = document.getElementById("star_word");
 var result = document.getElementById("result");
 var i=0;
 var j=0;
 var len = star_li.length;
 var word = ['一星級','二星級','三星級',"四星級","五星級"]
 for(i=0; i<len; i++){
 star_li[i].index = i;
 star_li[i].onmouseover = function(){
  star_word.style.display = "block";
  star_word.innerHTML = word[this.index];
  for(i=0; i<=this.index; i++){
   star_li[i].className = "act";
  }
 }
 star_li[i].onmouseout = function(){
  star_word.style.display = "none";
  for(i=0; i<len; i++){
   star_li[i].className = "";
  }
 }
 star_li[i].onclick = function(){
  result.innerHTML = (this.index+1)+"分";
 }
 }
}
</script> 
 </html>
 