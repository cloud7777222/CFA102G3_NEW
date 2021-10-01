<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Beloved MemberPersonalPage: Home</title>

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
   <tr><td><h3>Beloved MemberPersonalPage: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Beloved MemberPersonalPage</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMemPerPage.jsp'>List</a> all MemberPersonalPage <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="MemPersonalPageServlet" >
        <b>��J�K��s�� (�p01):</b>
        <input type="text" name="postNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="mppSvc" scope="page" class="com.mempersonalpage.model.MemPersonalPageService" />
   
  <li>
     <FORM METHOD="post" ACTION="MemPersonalPageServlet" >
       <b>��ܶK��s�� :</b>
       <select size="1" name="postNo">
         <c:forEach var="mppVO" items="${mppSvc.all}" > 
          <option value="${mppVO.postNo}">${mppVO.postNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  
</ul>


<h3>�K��޲z</h3>

<ul>
  <li><a href='addMemPerPage.jsp'>Add</a> a new MemberPersonalPage</li>
</ul>

</body>
</html>