<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.ad.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller AdServlet.java�w�s�Jrequest��AdVO����--%>
<%AdVO adVO = (AdVO) request.getAttribute("adVO");%>

<html>
<head>
<title>���u��� - listOneAd.jsp</title>

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
  img {
	width: 100px;
	height: 100px;
	object-fit: cover;
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
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - listOneAd.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/ad/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
				<th>�s�i�s��</th>
				<th>�s�i���D</th>
				<th>�s�i�ԭz</th>
				<th>�Ϥ�1</th>
				<th>�Ϥ�2</th>
				<th>�Ϥ�3</th>
				<th>�s�i���A</th>
				<th>�o�G��</th>
				<th>�I���</th>
			</tr>
	<tr>
		<td><%=adVO.getAdNo()%></td>
		<td><%=adVO.getAdTitle()%></td>
		<td><%=adVO.getAd()%></td>
		<td><img src="<%=adVO.getAdPic1()%>"></td>
		<td><img src="<%=adVO.getAdPic2()%>"></td>
		<td><img src="<%=adVO.getAdPic3()%>"></td>
		<td><%=adVO.getAdState()%></td>
		<td><%=adVO.getPostTime()%></td>
		<td><%=adVO.getDeadline()%></td>
	</tr>
</table>

</body>
</html>