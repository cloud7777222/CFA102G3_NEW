<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%
  ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動資料新增 - addActivity.jsp</title>

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
		 <h3>活動資料新增 - addActivity.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back_end/activity/select_page.jsp"><img src="images/original.gif" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activity.do" name="form1">
<table>
	<tr>
		<td>活動編號:</td>
		<td><input type="TEXT" name="actNo" size="45"
			 value="${activityVO.actNo}" /></td>
	</tr>
	<jsp:useBean id="activitytypeSvc" scope="page" class="com.activitytype.model.ActivitytypeService"/>
	<tr>
	<td>活動類別:</td>
	<td>
		<select size="1" name="actType">
			<c:forEach var="activitytypeVO" items="${activitytypeSvc.all}">
				<option value="${activitytypeVO.actType}" ${(activityVO.actType==activitytypeVO.actType)}>${activitytypeVO.actTypeName}
			</c:forEach>
	    </select>
	</td>
</tr>
	<tr>
		<td>活動名稱:</td>
		<td><input type="TEXT" name="actName" size="45"
			 value="${activityVO.actName}" /></td>
	</tr>
	<tr>
		<td>活動日期:</td>
		<td><input name="actDate" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>活動說明:</td>
		<td><input type="TEXT" name="actDirection" size="45"
			 value="${activityVO.actDirection}" /></td>
	</tr>
	<tr>
		<td>活動地點:</td>
		<td><input type="TEXT" name="actLocation" size="45"
			 value="${activityVO.actLocation}" /></td>
	</tr>
   
	
	<tr>
		<td>活動上下架狀態:</td>
		<td><input type="TEXT" name="actState" size="45"
			 value="${activityVO.actState}" /></td>
	</tr>
	<tr>
		<td>活動舉辦狀態:</td>
		<td><input type="TEXT" name="actHoldState" size="45"
			 value="${activityVO.actHoldState}" /></td>
	</tr>
	<tr>
		<td>活動報名開始日期:</td>
		<td><input type="TEXT" name="actRegisterStartDate" size="45"
			 value="${activityVO.actRegisterStartDate}" /></td>
	</tr>
	<tr>
		<td>活動報名截止日期:</td>
		<td><input type="TEXT" name="actRegisterDeadLine" size="45"
			 value="${activityVO.actRegisterDeadLine}" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date actDate = null;
  try {
	  actDate = activityVO.getActDate();
   } catch (Exception e) {
	   actDate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '${activityVO.actDate}', // value:   new Date(),
         
        });
   

</script>
</html>