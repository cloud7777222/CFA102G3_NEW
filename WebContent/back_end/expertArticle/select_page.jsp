<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Beloved ExpertArticle (專家專欄): Home</title>

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
   <tr><td><h3>Beloved ExpertArticle (專家專欄) : Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Beloved ExpertArticle</p>

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
  <li><a href='<%=request.getContextPath()%>/back_end/expertArticle/listAllExpertArticle.jsp'>List</a> all ExpertArticle <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expertArticle/ExpertArticleServlet" >
        <b>專家專欄編號 (如 01):</b>
        <input type="text" name="articleNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="eaSvc" scope="page" class="com.expertarticle.model.ExpertArticleService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/expertArticle/ExpertArticleServlet" >
       <b>選擇專家專欄編號 :</b>
       <select size="1" name="articleNo">
         <c:forEach var="eaSvc" items="${eaSvc.all}" > 
          <option value="${eaSvc.articleNo}">${eaSvc.articleNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  
</ul>


<h3>貼文管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/expertArticle/addExpertArticle.jsp'>Add</a> a new ExpertArticle</li>
</ul>

</body>
</html>