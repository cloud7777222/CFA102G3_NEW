<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Beloved PostReport (�峹���|) : Home</title>

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
   <tr><td><h3>Beloved PostReport (�峹���|) : Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Beloved PostReport</p>

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
  <li><a href='<%=request.getContextPath()%>/back_end/postReport/listAllPostReport.jsp'>List</a> all PostReport <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postReport/PostReportServlet" >
        <b>��J�峹�s�� :</b>
        <input type="text" name="postNo">
        <b>��J���|�H�|���s�� :</b>
        <input type="text" name="memberNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="postReportSvc" scope="page" class="com.postreport.model.PostReportService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postReport/PostReportServlet" >
       <b>��ܤ峹�s�� :</b>
       <select size="1" name="postNo">
         <c:forEach var="postReportVO" items="${postReportSvc.all}" > 
          <option value="${postReportVO.postNo}">${postReportVO.postNo}
         </c:forEach>   
       </select>
        <b>������|�H�|���s�� :</b>
       <select size="1" name="memberNo">
         <c:forEach var="postReportVO" items="${postReportSvc.all}" > 
          <option value="${postReportVO.memberNo}">${postReportVO.memberNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  
</ul>


<h3>�峹�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/postReport/addPostReport.jsp'>Add</a> a new PostReport</li>
</ul>

</body>
</html>