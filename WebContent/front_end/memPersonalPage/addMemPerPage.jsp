<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mempersonalpage.model.*"%>


<%
MemPersonalPageVO mppVO = (MemPersonalPageVO) request.getAttribute("mppVO");//MemPersonalPageServlet.java (Concroller) �s�Jreq��mppVO���� (�]�A�������X��mppVO, �]�]�A��J��ƿ��~�ɪ�mppVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�|���ӤH�K��s�W</title>
</head>



<body bgcolor="lightpink">

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


  <FORM action="MemPersonalPageServlet" method=post enctype="multipart/form-data">
        
        <br>  
        
        
        <table>
        <tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="memberNo" size="45" 
			 value= "<%= (mppVO==null)? "" : mppVO.getMemberNo()%>" /></td>
	</tr>
	<tr>
		<td>�K��Ϥ�:</td>
		<td><input type="file" name="postPhoto" size="45"
			 value="<%= (mppVO==null)? "" : mppVO.getPostPhoto()%>" />					 
		</td>
	</tr>
	
	<tr>
		<td>�K�夺�e:</td>
		<td><input type="TEXT" name="postContent" size="100"
			 value="<%= (mppVO==null)? "" : mppVO.getPostContent()%>" /></td>
	</tr>

	        
        </table>
        
    
	<input type="hidden" name="action" value="insert">  
    <input type="submit" value="�W��">    
  </FORM>
  

</body>
</html>

