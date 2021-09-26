<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emp.model.*"%>

<%EmpVO empVO = (EmpVO)request.getAttribute("empVO");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>加入員工</title>
<style>

.note
{
    text-align: center;
    height: 80px;
    background: -webkit-linear-gradient(left, #0072ff, #8811c5);
    color: #fff;
    font-weight: bold;
    line-height: 80px;
}
.form-content
{
    padding: 5%;
    border: 1px solid #ced4da;
    margin-bottom: 2%;
}
.form-control{
    border-radius:1.5rem;
}
.btnSubmit
{
    border:none;
    border-radius:1.5rem;
    padding: 1%;
    width: 20%;
    cursor: pointer;
    background: #0062cc;
    color: #fff;
}
</style>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<div class="container register-form">
<form method="post" action="<%=request.getContextPath()%>/emp/emp.do"name="form1">
            <div class="form">
                <div class="note">
                    <p>加入員工</p>
                </div>

                <div class="form-content">
                    <div class="row">
                   	 <div class="col-md-6">
                   	  
                        	 <div class="form-group">
                                <input type="text" class="form-control" placeholder="員工姓名" name="empname"value="${empVO.empname}"/>
                            </div>
                            <div class="form-group">
                               <input type="radio" name="empstate" value="1" id="state_1"checked/>
                               <label for="state_1">在職</label>
                               <input type="radio" name="empstate" value="0" id="state_0"/>
                                 <label for="state_0">尚未到職</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="員工帳號" name="empaccount" value="${empVO.empaccount}"/>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="員工密碼" name="emppassword"value="${empVO.emppassword}"/>
                            </div>
                        </div>
                    </div>
                   <input type="hidden" name="action" value="insert">
                    <input type="submit" class="btnSubmit" value="新增員工">
                   
                </div>
                
            </div>
            
            </form>
        </div>
</body>
</html>