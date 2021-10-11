<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.messagereport.model.*"%>
<%@ page import="java.util.*"%>

<%
	MessageReportService messageReportSvc = new MessageReportService();
    List<MessageReportVO> list = messageReportSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<%= messageReportSvc==null %>---- ${param.mesNo}--   

 
      

<html>
<head>
<title>�Ҧ��׾¤峹�d�����| - listAllMessageReport.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  img{
  max-width: 100%;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��׾¤峹�d�����| - listAllMessageReport.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/messageReport/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>���|�H�|���s��</th>
		<th>�峹�d���s��</th>		
		<th>���|�ɶ�</th>
		<th>���|��]</th>		
		<th>�d�����|�B�z���A</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="messageReportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(messageReportVO.memberNo==param.memberNo && messageReportVO.mesNo==param.mesNo) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
		
		<td>${messageReportVO.memberNo}</td>
		<td>${messageReportVO.mesNo}</td>		
		<td>${messageReportVO.mesRepTime}</td>
		<td>${messageReportVO.mesRepFor}</td>
		<td>${messageReportVO.mesRevState}</td>	
			
			
			<td>						
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/messageReport/MessageReportServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="memberNo" value="${messageReportVO.memberNo}">
			     <input type="hidden" name="mesNo"  value="${messageReportVO.mesNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->			     
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/messageReport/MessageReportServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">		     
			     <input type="hidden" name="memberNo" value="${messageReportVO.memberNo}">
			     <input type="hidden" name="mesNo"  value="${messageReportVO.mesNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->			     
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<font color=blue>�峹���|�B�z���A: (1:�ݼf�� / 2:�f�ֳq�L / 3:�f�֥��q�L)</font><br>
<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>