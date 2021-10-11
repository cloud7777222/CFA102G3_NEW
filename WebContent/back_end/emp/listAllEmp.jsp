<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>

<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>全部員工</title>
<%@ include file="/back_end/header.jsp"%>
<style>
@font-face {
    font-family: Poppins-Regular;
    src: url(https://colorlib.com/etc/tb/Table_Responsive_v2/fonts/poppins/Poppins-Regular.ttf)
}

@font-face {
    font-family: Poppins-Bold;
    src: url(https://colorlib.com/etc/tb/Table_Responsive_v2/fonts/poppins/Poppins-Bold.ttf)
}

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box
}

body,
html {
    height: 100%;
    font-family: sans-serif
}

a {
    margin: 0;
    transition: all .4s;
    -webkit-transition: all .4s;
    -o-transition: all .4s;
    -moz-transition: all .4s
}

a:focus {
    outline: none!important
}

a:hover {
    text-decoration: none
}

h1,
h2,
h3,
h4,
h5,
h6 {
    margin: 0
}

p {
    margin: 0
}

ul,
li {
    margin: 0;
    list-style-type: none
}

input {
    display: block;
    outline: none;
    border: none!important
}

textarea {
    display: block;
    outline: none
}

textarea:focus,
input:focus {
    border-color: transparent!important
}

button {
    outline: none!important;
    border: none;
    background: 0 0
}

button:hover {
    cursor: pointer
}

iframe {
    border: none!important
}

.limiter {
    width: 100%;
    margin: 0 auto
}

.container-table100 {
    width: 100%;
    min-height: 100vh;
    background: #c4d3f6;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-wrap: wrap;
    padding: 33px 30px
}

.wrap-table100 {
    width: 960px;
    border-radius: 10px;
    overflow: hidden
}

.table {
    width: 100%;
    display: table;
    margin: 0
}

@media screen and (max-width:768px) {
    .table {
        display: block
    }
}

.row {
    display: table-row;
    background: #fff
}

.row.header {
    color: #fff;
    background: #6c7ae0
}

@media screen and (max-width:768px) {
    .row {
        display: block
    }
    .row.header {
        padding: 0;
        height: 0
    }
    .row.header .cell {
        display: none
    }
    .row .cell:before {
        font-family: Poppins-Bold;
        font-size: 12px;
        color: gray;
        line-height: 1.2;
        text-transform: uppercase;
        font-weight: unset!important;
        margin-bottom: 13px;
        content: attr(data-title);
        min-width: 98px;
        display: block
    }
}

.cell {
    display: table-cell
}

@media screen and (max-width:768px) {
    .cell {
        display: block
    }
}

.row .cell {
    font-family: Poppins-Regular;
    font-size: 15px;
    color: #666;
    line-height: 1.2;
    font-weight: unset!important;
    padding-top: 20px;
    padding-bottom: 20px;
    border-bottom: 1px solid #f2f2f2
}

.row.header .cell {
    font-family: Poppins-Regular;
    font-size: 18px;
    color: #fff;
    line-height: 1.2;
    font-weight: unset!important;
    padding-top: 19px;
    padding-bottom: 19px
}

.row .cell:nth-child(1) {
    width: 360px;
    padding-left: 40px
}

.row .cell:nth-child(2) {
    width: 160px
}

.row .cell:nth-child(3) {
    width: 250px
}

.row .cell:nth-child(4) {
    width: 190px
}

.table,
.row {
    width: 100%!important
}

.row:hover {
    background-color: #ececff;
    cursor: pointer
}

@media(max-width:768px) {
    .row {
        border-bottom: 1px solid #f2f2f2;
        padding-bottom: 18px;
        padding-top: 30px;
        padding-right: 15px;
        margin: 0
    }
    .row .cell {
        border: none;
        padding-left: 30px;
        padding-top: 16px;
        padding-bottom: 16px
    }
    .row .cell:nth-child(1) {
        padding-left: 30px
    }
    .row .cell {
        font-family: Poppins-Regular;
        font-size: 18px;
        color: #555;
        line-height: 1.2;
        font-weight: unset!important
    }
    .table,
    .row,
    .cell {
        width: 100%!important
    }
}
</style>
</head>
<body>

<%@ include file="/back_end/sliderbar.jsp"%>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
 <div class="container" style="margin-top:30px">
        
            <table class="table  table-hover table-sm">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>員工姓名</th>
                        <th>員工帳號</th>
                        <th>員工密碼</th>
                        <th>員工狀態</th>
                        <th>員工資料更新</th>
                        <th>員工資料刪除</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="empVO" items="${list}">
                    <tr>
                        <td>${empVO.empno}</td>
                        <td>${empVO.empname}</td>
                        <td>${empVO.empaccount}</td>
                        <td>${empVO.emppassword}</td>
                        <td>
                        <c:if test="${empVO.empstate == 1}">在職</c:if>
						<c:if test="${empVO.empstate == 0}">離職</c:if>
                        </td>
                        <td>
                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     		<button type="submit" class="btn"><i class="fa fa-wrench" aria-hidden="true"></i></button>
			     		<input type="hidden" name="empno"  value="${empVO.empno}">
			     		<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
                        </td>
                        <td>
                       	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     		<button type="submit" class="btn"><i class="fa fa-trash" aria-hidden="true"></i></button>
			     		<input type="hidden" name="empno"  value="${empVO.empno}">
			     		<input type="hidden" name="action" value="delete">
			     		</FORM>
                        </td>
                        
                    </tr>
                  </c:forEach> 
                </tbody>
            </table>
            
        </div>

        
 <%@ include file="/back_end/footer.jsp"%>      
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>  

</body>
</html>