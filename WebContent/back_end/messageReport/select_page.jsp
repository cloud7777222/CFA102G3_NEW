<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Beloved MessageReport (�峹�d�����|) : Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: lightyellow;
	margin-top: 5px;
	margin-bottom: 10px;
/*     border: 3px ridge Gray; */
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: lightyellow;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Beloved MessageReport (�峹�d�����|) : Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Beloved MessageReport</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/messageReport/listAllMessageReport.jsp'>List</a> all MessageReport <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/messageReport/MessageReportServlet" >       
        <b>��J���|�H�|���s�� :</b>
        <input type="text" name="memberNo">
        <b>��J�峹�d���s�� :</b>
        <input type="text" name="mesNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="messageReportSvc" scope="page" class="com.messagereport.model.MessageReportService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/messageReport/MessageReportServlet" >    
        <b>������|�H�|���s�� :</b>
       <select size="1" name="memberNo">
         <c:forEach var="messageReportVO" items="${messageReportSvc.all}" > 
          <option value="${messageReportVO.memberNo}">${messageReportVO.memberNo}
         </c:forEach>   
       </select>
       <b>��ܤ峹�d���s�� :</b>
       <select size="1" name="mesNo">
         <c:forEach var="messageReportVO" items="${messageReportSvc.all}" > 
          <option value="${messageReportVO.mesNo}">${messageReportVO.mesNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  
</ul>


<h3>�峹�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/messageReport/addMessageReport.jsp'>Add</a> a new MessageReport</li>
</ul>

</body>
</html>