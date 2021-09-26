<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<%= memberVO==null%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
</head>
<body>
--------------------${memberVO.memberAccount}------------------------
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<form action="<%=request.getContextPath()%>/member/member" method="POST" enctype="multipart/form-data">
    <img id="Preview" src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${memberVO.memberAccount}"><br>
    <input type="file" id="MemberPhoto" name="memberPhoto"><br>
    姓名<input type="text" id="MemberName" name="memberName" value="<%= (memberVO.getMemberName()==null)? "" : memberVO.getMemberName()%>"><br>
    男<input type="radio" name="memberGender" value="1" checked>
    女<input type="radio" name="memberGender" value="2" ${(memberVO.memberGender==2)?'checked':'' }><br>
    生日<input id="MemberBirthday" name="memberBirthday" type="text" ><br>
    職業<input type="text" id="MemberJob" name="memberJob" value="<%= (memberVO.getMemberJob()==null)? "" : memberVO.getMemberJob()%>"><br>
    手機<input type="text" id="MemberPhone" name="memberPhone" value="<%= (memberVO.getMemberPhone()==null)? "" : memberVO.getMemberPhone()%>"><br>
   居住地<select id="MemberCountry" name="memberCountry">
        <option value="1" ${(memberVO.memberCountry==1)?'selected':'' }>臺北市</option>
        <option value="2" ${(memberVO.memberCountry==2)?'selected':'' }>新北市</option>
        <option value="3" ${(memberVO.memberCountry==3)?'selected':'' }>桃園市</option>
        <option value="4" ${(memberVO.memberCountry==4)?'selected':'' }>臺中市</option>
        <option value="5" ${(memberVO.memberCountry==5)?'selected':'' }>臺南市</option>
        <option value="6" ${(memberVO.memberCountry==6)?'selected':'' }>高雄市</option>
        <option value="7" ${(memberVO.memberCountry==7)?'selected':'' }>基隆市</option>
        <option value="8" ${(memberVO.memberCountry==8)?'selected':'' }>新竹市</option>
        <option value="9" ${(memberVO.memberCountry==9)?'selected':'' }>嘉義市</option>
        <option value="10" ${(memberVO.memberCountry==10)?'selected':'' }>新竹縣</option>
        <option value="11" ${(memberVO.memberCountry==11)?'selected':'' }>苗栗縣</option>
        <option value="12" ${(memberVO.memberCountry==12)?'selected':'' }>彰化縣</option>
        <option value="13" ${(memberVO.memberCountry==13)?'selected':'' }>南投縣</option>
        <option value="14" ${(memberVO.memberCountry==14)?'selected':'' }>雲林縣</option>
        <option value="15" ${(memberVO.memberCountry==15)?'selected':'' }>嘉義縣</option>
        <option value="16" ${(memberVO.memberCountry==16)?'selected':'' }>屏東縣</option>
        <option value="17" ${(memberVO.memberCountry==17)?'selected':'' }>宜蘭縣</option>
        <option value="18" ${(memberVO.memberCountry==18)?'selected':'' }>花蓮縣</option>
        <option value="19" ${(memberVO.memberCountry==19)?'selected':'' }>臺東縣</option>
        <option value="20" ${(memberVO.memberCountry==20)?'selected':'' }>澎湖縣</option>
    </select><br>
    <textarea id="MemberIntroduce" name="memberIntroduce"  cols="30" rows="10"><%= (memberVO.getMemberIntroduce()==null)? "" : memberVO.getMemberIntroduce()%></textarea><br>
    <input type="hidden" name="action" value="update_Member">
    <input type="hidden" name="memberAccount" value="${memberVO.memberAccount}">
    <input type="submit" value="送出"></FORM>
	</form>
	
	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date memberBirthday = null;
  try {
	  memberBirthday = memberVO.getMemberBirthday();
   } catch (Exception e) {
	   memberBirthday = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
  img {
  	height: 100px;
    width: 100px;
      }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#MemberBirthday').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=memberBirthday%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#MemberBirthday').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#MemberBirthday').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
              var somedate1 = new Date('1970-01-01');
              var somedate2 = new Date();
              $('#MemberBirthday').datetimepicker({
                  beforeShowDay: function(date) {
                	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        		             ||
        		            date.getYear() >  somedate2.getYear() || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                      ) {
                           return [false, ""]
                      }
                      return [true, ""];
              }});
        
</script>
<script>
                    // 題目： 請製作可以同時上傳多張圖片到前端預覽的功能
                    // 學習重點：
                    // 1. File API – Read as Data URL
            
                    function init() {
            
                        // 1. 抓取DOM元素
                        let memberPhoto = document.getElementById("MemberPhoto");
                        let preview = document.getElementById('Preview');
            
                        // 2. 對myFile物件註冊change事件 - 改變選擇的檔案時觸發
                        memberPhoto.addEventListener('change', function(e) {
                            // 取得檔案物件的兩種方式
                            // 1. 直接從myFile物件上取得檔案物件 (因為非同步，一樣，多個classname註冊時會有問題)
                            // 2. 從event物件中取得他的soure target，也就是myFile物件，再取得檔案物件 
                            // 檔案的基本資訊，包括：檔案的名稱、大小與文件型態
                            let files = e.target.files;
                            // 判斷files物件是否存在
                            if (files !== null) {
                                let file = files[0];
                                    // 取出files物件的第i個
                                    // 判斷file.type的型別是否包含'image'
                                    if (file.type.indexOf('image') > -1) {
                                        
                                        // new a FileReader
                                        let reader = new FileReader();
                                        // 在FileReader物件上註冊load事件 - 載入檔案的意思
                                        reader.addEventListener('load', function(e) {
                                            // 取得結果 提示：從e.target.result取得讀取到結果
                                            let result = e.target.result;
                                            // console.log(result) 確認讀取到結果
                                            // 賦予src屬性
                                            preview.src=result;
                                        });
                                        // 使用FileReader物件上的 readAsDataURL(file) 的方法，傳入要讀取的檔案，並開始進行讀取
                                        reader.readAsDataURL(file); // trigger!!!!
                                    } else {
                                        // 彈出警告視窗 alert('請上傳圖片！');
                                        alert('請上傳圖片！');
                                    }
                            }
                        });
                    }
            
                    window.onload = init;
                </script>
</body>
</html>