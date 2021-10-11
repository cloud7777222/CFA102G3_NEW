<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friend.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	Integer memberNoA=memberVO.getMemberNo();
	String memberAccount=memberVO.getMemberAccount();
    FriendService friendService=new FriendService();
    List<MemberVO> friendList=new ArrayList<MemberVO>();
	 friendList=friendService.getFriend(memberNoA);
	 pageContext.setAttribute("friendList",friendList);//好友列表用
%>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.0/moment.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
<style>
	.container{max-width:1170px; margin:auto;}
img{ max-width:100%;}
.inbox_people {
  background: #f8f8f8 none repeat scroll 0 0;
  float: left;
  overflow: hidden;
  width: 40%; border-right:1px solid #c4c4c4;
}
.inbox_msg {
  border: 1px solid #c4c4c4;
  clear: both;
  overflow: hidden;
}
.top_spac{ margin: 20px 0 0;}


.recent_heading {float: left; width:40%;}
.srch_bar {
  display: inline-block;
  text-align: right;
  width: 60%;
}
.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

.recent_heading h4 {
  color: #05728f;
  font-size: 21px;
  margin: auto;
}
.srch_bar input{ border:1px solid #cdcdcd; border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
.srch_bar .input-group-addon button {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  padding: 0;
  color: #707070;
  font-size: 18px;
}
.srch_bar .input-group-addon { margin: 0 0 0 -27px;}

.chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
.chat_ib h5 span{ font-size:13px; float:right;}
.chat_ib p{ font-size:14px; color:#989898; margin:auto}
.chat_img {
  float: left;
  width: 11%;
}
.chat_ib {
  float: left;
  padding: 0 0 0 15px;
  width: 88%;
}

.chat_people{ overflow:hidden; clear:both;}
.chat_list {
  border-bottom: 1px solid #c4c4c4;
  margin: 0;
  padding: 18px 16px 10px;
}
.inbox_chat { height: 550px; overflow-y: scroll;}

.active_chat{ background:#ebebeb;}

.incoming_msg_img {
  display: inline-block;
  width: 6%;
}
.received_msg {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
 }
 .received_withd_msg p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 12px;
  margin: 8px 0 0;
}
.received_withd_msg { width: 57%;}
.mesgs {
  float: left;
  padding: 30px 15px 0 25px;
  width: 60%;
}

 .sent_msg p {
  background: #05728f none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
.sent_msg {
  float: right;
  width: 46%;
}
.input_msg_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
}

.type_msg {border-top: 1px solid #c4c4c4;position: relative;}
.msg_send_btn {
  background: #05728f none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: absolute;
  right: 0;
  top: 11px;
  width: 33px;
}
.messaging { padding: 0 0 50px 0;}
.msg_history {
  height: 516px;
  overflow-y: auto;
}
#online_status{
    width: 5px;
    height: 5px;
    background-color: red;
    border-radius: 80px;
  }
   img{
 	  height:45px;
 	  width:50px;
      border-radius: 150px;
    }
</style>







<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free Website Template" name="keywords">
<meta content="Free Website Template" name="description">

<%@ include file="/front_end/pages/link.file"%>
<style>

.text p {
	width: 100%;
}

.carousel {
	background: #940a3f;
	min-height: 130px;
}

@media ( max-width : 991.98px) {
	.carousel {
		min-height: 0;
	}
}
</style>
</head>
<body onload="connect();"   onunload="disconnect();">
	<!-- Top Bar Start -->
	<jsp:include page="/front_end/topbar.jsp" flush="true" />
	<!-- Nav Bar End -->

	<div class="carousel">
		<div class="container-fluid">
			<div class="owl-carousel"></div>
		</div>
	</div>
	<!-- Carousel End -->

	<div class="container-fluid">














<div class="container">
<h3 class=" text-center">Messaging</h3>
<div class="messaging">
      <div class="inbox_msg">
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>Recent</h4>
            </div>
            <div class="srch_bar">
              <div class="stylish-input-group">
                <input type="text" class="search-bar"  placeholder="Search" >
                <span class="input-group-addon">
                <button type="button"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                </span> </div>
            </div>
          </div>
          <div class="inbox_chat">
                <div id="chooseOne" style="display:none"></div>
            <c:forEach var="Friend" items="${friendList}">
            <div class="chat_list" id="${Friend.memberAccount}row">
            <div id="${Friend.memberAccount}" style="background-color: gray; height:10px; width: 10px; border-radius: 80px; float: right;"></div>
              <div class="chat_people">
                <div class="chat_img"> <img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${Friend.memberAccount}"> </div>
                <div class="chat_ib">
                  <h5>${Friend.memberName} </h5>
                  <div id="${Friend.memberAccount}last"></div>
                 <div id="${Friend.memberAccount}unread" style="float: right; font-size: 5px; color: blue;"></div>
                </div>
              </div>
            </div>
          </c:forEach>
         </div>
        </div>
       
       
       <div class="mesgs">
          <div id="msg_history" class="msg_history">
            
		<h3 id="statusOutput" class="statusOutput"> </h3>
          </div>
          <div class="type_msg">
            <div class="input_msg_write">
              <input id="message" type="text" class="write_msg" placeholder="Type a message" onkeydown="if (event.keyCode == 13) sendMessage();"/>
              <button id="sendMessage" class="msg_send_btn" type="submit" onclick="sendMessage();"><i class="fas fa-paper-plane" aria-hidden="true"></i></button>
            </div>
          </div>
          </div>
	  </div>
	 </div>




	<h3 id="statusOutput" class="statusOutput"> </h3>








	</div>

	<!-- Footer Start -->
	<jsp:include page="/front_end/footer.jsp" flush="true" />
	<!-- Footer End -->

	<!-- Back to top button -->
	<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

	<!-- Pre Loader -->
	<div id="loader" class="show">
		<div class="loader"></div>
	</div>

	<%@ include file="/front_end/pages/script.file"%>

	<script>
        // $(".nav-item.dropdown").click(()=>{
        //     console.log("ijoioi")
        // //    $(this).next().toggle();
        // // $(this).children().html("99999999999")
        // // $(".dropdown-menu.dropdown-menu-end").toggle();
        // //    console.log($(this).children(".dropdown-menu.dropdown-menu-end").toggle());
        // //    $(".dropdown-menu.dropdown-menu-end").hover();
        // //    $(".dropdown-menu.dropdown-menu-end").toggle();
        // })
        let newsText = [
            "大受歡迎",
            "名額有限",
            "成為受歡迎對象吧"
        ];
        let i = 0;

        let carouselNews = window.setInterval(() => {
            $("#news").html(newsText[i++ % 3]);

            if(i%2==0){
                $("#news").addClass("run");
            }else{
                $("#news").removeClass("run");
            }
        }, 4000);


    </script>
</body>
<script>
var MyPoint = "/ChatWS/<%=memberAccount%>";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

var statusOutput = document.getElementById("statusOutput");
var chooseOne = document.getElementById("chooseOne");
var msg_history = document.getElementById("msg_history");
var self ='${memberVO.memberAccount}';
var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			else if ("unreadMessage" === jsonObj.type) {
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var unreadFriend = JSON.parse(messages[i]);
					var unreadMessage = unreadFriend.unreadMessage;
					var lastMessage = unreadFriend.lastMessage;
					var memberAccountB=unreadFriend.memberAccountB;
					var unread = document.getElementById(memberAccountB+"unread");
					var last = document.getElementById(memberAccountB+"last");
					last.innerHTML=lastMessage;
					if(unreadMessage>0){
					unread.innerHTML="你有"+unreadMessage+"筆未讀訊息";
					}
				}
			}
			else if ("unchoose" === jsonObj.type) {
					var unreadMessage=jsonObj.unreadMessage;
					var memberAccountA=jsonObj.memberAccountA;
					var lastMessage = jsonObj.lastMessage;
					var unread = document.getElementById(memberAccountA+"unread");
					var last = document.getElementById(memberAccountA+"last");
					last.innerHTML=lastMessage;
					if(unreadMessage>0){
					unread.innerHTML="你有"+unreadMessage+"筆未讀訊息";
					}
				}
			
			else if ("history" === jsonObj.type) {
				msg_history.innerHTML = '';
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					if(historyData.memberAccountA === self){
						
						var chat_div = document.createElement('div');  
						chat_div.className='outgoing_msg';
						
						var sent_msg_div = document.createElement('div')
						sent_msg_div.className='sent_msg';
						
						var message_sent = document.createElement('p')
						message_sent.innerHTML = historyData.message;
						
						var message_time = document.createElement('span')
						message_time.innerHTML =historyData.chatTime + historyData.chatSeen;
						message_time.className= 'time_date';
						
						msg_history.appendChild(chat_div);
						chat_div.appendChild(sent_msg_div);
						sent_msg_div.appendChild(message_sent);
						sent_msg_div.appendChild(message_time);
					}else {
						var chat_div = document.createElement('div'); 
						chat_div.className='incoming_msg';
						
						
						var recrive_msg_div_img = document.createElement('div')
						recrive_msg_div_img.className='incoming_msg_img';
						
						var recrive_img = document.createElement('img')
						recrive_img.src="<%=request.getContextPath()%>/GetPhoto?memberAccount="+jsonObj.memberAccountB;
						var recrive_msg = document.createElement('div')
						recrive_msg.className='received_msg';
						
						var received_withd_msg = document.createElement('div')
						received_withd_msg.className='received_withd_msg';
						
						var message_sent = document.createElement('p')
						message_sent.innerHTML = historyData.message;
						
						var message_time = document.createElement('span')
						message_time.innerHTML = historyData.chatTime;
						
						msg_history.appendChild(chat_div);
						chat_div.appendChild(recrive_msg_div_img);
						chat_div.appendChild(recrive_msg);
						recrive_msg_div_img.appendChild(recrive_img);
						recrive_msg.appendChild(received_withd_msg);
						received_withd_msg.appendChild(message_sent);
						received_withd_msg.appendChild(message_time);
					}
				}
					msg_history.scrollTop = msg_history.scrollHeight;
				}
				else if ("chat" === jsonObj.type) {
				if(jsonObj.memberAccountA === self){
					
					
					var memberAccountB=jsonObj.memberAccountB;
					var lastMessage = jsonObj.lastMessage;
					var last = document.getElementById(memberAccountB+"last");
					last.innerHTML=lastMessage;
					
					var chat_div = document.createElement('div');  
					chat_div.className='outgoing_msg';
					
					
					var sent_msg_div = document.createElement('div')
					sent_msg_div.className='sent_msg';
					
					var message_sent = document.createElement('p')
					message_sent.innerHTML = jsonObj.message;
					
					var message_time = document.createElement('span')
					message_time.innerHTML =  jsonObj.chatTime + jsonObj.chatSeen;
					message_time.className='time_date';
					
					msg_history.appendChild(chat_div);
					chat_div.appendChild(sent_msg_div);
					sent_msg_div.appendChild(message_sent);
					sent_msg_div.appendChild(message_time);
				
				}else {
					
					var memberAccountA=jsonObj.memberAccountA;
					var lastMessage = jsonObj.lastMessage;
					var last = document.getElementById(memberAccountA+"last");
					last.innerHTML=lastMessage;
					
					
					var chat_div = document.createElement('div');  
					chat_div.className='incoming_msg';
					
					
					var recrive_msg_div_img = document.createElement('div')
					recrive_msg_div_img.className='incoming_msg_img';
					
					var recrive_img = document.createElement('img')
					if(jsonObj.memberAccountA === self){
					recrive_img.src="<%=request.getContextPath()%>/GetPhoto?memberAccount="+jsonObj.memberAccountB;
					var recrive_msg = document.createElement('div')
					recrive_msg.className='received_msg';
					}else{
						recrive_img.src="<%=request.getContextPath()%>/GetPhoto?memberAccount="+jsonObj.memberAccountA;
						var recrive_msg = document.createElement('div')
						recrive_msg.className='received_msg';
					}
					var received_withd_msg = document.createElement('div')
					received_withd_msg.className='received_withd_msg';
					
					var message_sent = document.createElement('p')
					message_sent.innerHTML = jsonObj.message;
					
					var message_time = document.createElement('span')
					message_time.innerHTML = jsonObj.chatTime;
					
					msg_history.appendChild(chat_div);
					chat_div.appendChild(recrive_msg_div_img);
					chat_div.appendChild(recrive_msg);
					recrive_msg_div_img.appendChild(recrive_img);
					recrive_msg.appendChild(received_withd_msg);
					received_withd_msg.appendChild(message_sent);
					received_withd_msg.appendChild(message_time);
				}
				msg_history.scrollTop = msg_history.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var memberAccountA = "${memberVO.memberAccount}"
		var memberAccountB = chooseOne.textContent;//從這邊拿好友的值
		var message = inputMessage.value.trim();
		var now = new Date();
        now=moment().format('h:mma');//現在時間
        
		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (memberAccountB === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"memberAccountA" : memberAccountA,
				"memberAccountB" : memberAccountB,
				"type" : "chat",
				"chatTime" : now,
				"chatSeen" : "",
				"whichOne" : "",
				"unreadMessage" : "0",
				"lastMessage" : "",
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var all = jsonObj.users;
		var me = jsonObj.user;
		var memberAccountA="${memberVO.memberAccount}"
			<c:forEach var="Friend" items="${friendList}">
			var friend="${Friend.memberAccount}"
			var a=document.getElementById(friend);
			a.style.backgroundColor="gray";
		for(var i = 0; i < all.length; i++){
			if(friend===all[i]){
				a.style.backgroundColor="green";
			}
		}
			</c:forEach>
		
		<c:forEach var="Friend" items="${friendList}">
		var memberAccountB="${Friend.memberAccount}"
		var memberNameB="${Friend.memberName}"
			addListener(memberAccountA,memberAccountB,memberNameB);
		</c:forEach>
		if(me==memberAccountA){
		unreadMessage()
		}
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener(memberAccountA,memberAccountB,memberNameB) {
		var container = document.getElementById(memberAccountB+"row");
		container.addEventListener("click", function(e) {
			var unread = document.getElementById(memberAccountB+"unread");
			unread.innerHTML="";
			updateMemberNameB(memberNameB);
			updateMemberAccountB(memberAccountB);
			var whichOne=statusOutput.innerHTML
			var jsonObj = {
					"memberAccountA" : memberAccountA,
					"memberAccountB" : memberAccountB,
					"type" : "history",
					"chatTime" : "",
					"chatSeen" : "",
					"whichOne" : whichOne,
					"unreadMessage" : "0",
					"lastMessage" : "",
					"message" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	function disconnect() {
		webSocket.close();
	}
	
	function updateMemberNameB(memberNameB) {
		statusOutput.innerHTML = memberNameB;
	}//建立一個hidden的元素從這邊拿好友的值
	function updateMemberAccountB(memberAccountB) {
		chooseOne.innerHTML = memberAccountB;
	}//建立一個hidden的元素從這邊拿好友的值
	function unreadMessage(){
		var memberAccountA = "${memberVO.memberAccount}";
		var jsonObj = {
				"memberAccountA" : memberAccountA,
				"memberAccountB" : "",
				"type" : "unreadMessage",
				"chatTime" : "",
				"chatSeen" : "",
				"whichOne" : "",
				"unreadMessage" : "0",
				"lastMessage" : "",
				"message" : ""
			};
		webSocket.send(JSON.stringify(jsonObj));
	}
</script>
</html>