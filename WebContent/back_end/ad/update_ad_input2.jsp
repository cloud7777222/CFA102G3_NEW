<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*"%>

<%
  AdVO adVO = (AdVO) request.getAttribute("adVO"); //AdServlet.java (Concroller) 存入req的adVO物件 (包括幫忙取出的adVO, 也包括輸入資料錯誤時的adVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>廣告資料修改 - update_ad_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_ad_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/ad/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ad/ad.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>廣告編號:<font color=red><b>*</b></font></td>
		<td><%=adVO.getAdNo()%></td>
	</tr>
	<tr>
				<td>廣告標題:</td>
				<td><input type="TEXT" name="adTitle" size="20"
					value="<%=(adVO == null) ? "請輸入廣告標題" : adVO.getAdTitle()%>" /></td>
			</tr>
			<tr>
				<td>廣告內容:</td>
				<td><textarea name="ad" cols="30" rows="10"><%=(adVO == null) ? "請輸入廣告內容" : adVO.getAd()%></textarea></td>
			</tr>
			<tr>
				<td>廣告圖1:</td>
				<td><input type="file" name="upfile1" multiple>上傳圖片<input
					type="TEXT" name="adPic1"
					value="<%=(adVO == null) ? "請添加廣告圖1" : adVO.getAdPic1()%>" /><img
					src="<%=request.getAttribute("path")%>"><%=request.getAttribute("path")%></td>
			</tr>
			<tr>
				<td>廣告圖2:</td>
				<td><input type="TEXT" name="adPic2"
					value="<%=(adVO == null) ? "請添加廣告圖2" : adVO.getAdPic2()%>" /></td>
			</tr>
			<tr>
				<td>廣告圖3:</td>
				<td><input type="TEXT" name="adPic3"
					value="<%=(adVO == null) ? "請添加廣告圖3" : adVO.getAdPic3()%>" /></td>
			</tr>
			<tr>
				<td>上下架:</td>
				<td><input type="number" name="adState" size="1"
					value="<%=(adVO == null) ? "0" : adVO.getAdState()%>" /></td>
			</tr>
			<tr>
				<td>發佈日期:</td>
				<td><input name="postTime" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>截止日期:</td>
				<td><input name="deadline" id="f_date2" type="text"></td>
			</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="adNo" value="<%=adVO.getAdNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date postTime = null;
	java.sql.Date deadline = null;
	try {
		postTime = adVO.getPostTime();
	} catch (Exception e) {
		postTime = new java.sql.Date(System.currentTimeMillis());
	}
	try {
		deadline = adVO.getDeadline();
	} catch (Exception e) {
		deadline = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}
.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=postTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       value: '<%=deadline%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------
        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>