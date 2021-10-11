<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Beloved ExpertPersonalPage: Home</title>

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
   <tr><td><h3>Beloved ExpertPersonalPage: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Beloved ExpertPersonalPage</p>

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
  <li><a href='<%=request.getContextPath()%>/back_end/expertPersonalPage/listAllExpertPerPage.jsp'>List</a> all ExpertPersonalPage <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet" >
        <b>��J�K��s�� (�p 01):</b>
        <input type="text" name="postNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="eppSvc" scope="page" class="com.expertpersonalpage.model.ExpertPersonalPageService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expertPersonalPage/ExpertPersonalPageServlet" >
       <b>��ܶK��s�� :</b>
       <select size="1" name="postNo">
         <c:forEach var="eppVO" items="${eppSvc.all}" > 
          <option value="${eppVO.postNo}">${eppVO.postNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  
</ul>


<h3>�K��޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/expertPersonalPage/addExpertPerPage.jsp'>Add</a> a new ExpertPersonalPage</li>
</ul>

</body>
</html>