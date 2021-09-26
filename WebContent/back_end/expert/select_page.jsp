<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Activity</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #FFFCCF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: skyblue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Expert</h3><h4>( MVC )</h4></td></tr>
</table>

<p></p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllExpert.jsp'>List</a> all Experts.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expert.do" >
        <b>輸入專家編號 (如1):</b>
        <input type="text" name="expertNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="expertSvc" scope="page" class="com.expert.model.ExpertService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expert.do" >
       <b>選擇專家編號:</b>
       <select size="1" name="expertNo">
         <c:forEach var="expertVO" items="${expertSvc.all}" > 
          <option value="${expertVO.expertNo}">${expertyVO.expertNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
    
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expert.do" >
       <b>選擇活動名稱:</b>
       <select size="1" name="expertNo">
         <c:forEach var="expertVO" items="${expertSvc.all}" > 
          <option value="${expertVO.expertNo}">${expertVO.exName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>活動管理</h3>

<ul>
  <li><a href='addExpert.jsp'>Add</a> a new Expert.</li>
</ul>

</body>
</html>