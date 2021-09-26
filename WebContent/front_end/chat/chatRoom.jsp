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
img{
	width:30%;
}
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
  
  
  .message-area {
	height: 70%;
	resize: none;
	box-sizing: border-box;
	overflow: auto;
	background-color: #ffffff;
}

.input-area {
	background: #0078ae;
	box-shadow: inset 0 0 10px #00568c;
}

.input-area input {
	margin: 0.5em 0em 0.5em 0.5em;
}

.text-field {
	border: 1px solid grey;
	padding: 0.2em;
	box-shadow: 0 0 5px #000000;
}

h1 {
	font-size: 1.5em;
	padding: 5px;
	margin: 5px;
}

#message {
	min-width: 50%;
	max-width: 60%;
}

.statusOutput {
	background: #0078ae;
	text-align: center;
	color: #ffffff;
	border: 1px solid grey;
	padding: 0.2em;
	box-shadow: 0 0 5px #000000;
	width: 30%;
	margin-top: 10%;
	margin-left: 60%;
}
ul{
  list-style: none;
  margin: 0;
  padding: 0;
}

ul li{
  display:inline-block;
  clear: both;
  padding: 20px;
  border-radius: 30px;
  margin-bottom: 2px;
  font-family: Helvetica, Arial, sans-serif;
}

.friend{
  background: #eee;
  float: left;
}

.me{
  float: right;
  background: #0084ff;
  color: #fff;
}

.friend + .me{
  border-bottom-right-radius: 5px;
}

.me + .me{
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}

.me:last-of-type {
  border-bottom-right-radius: 30px;
}

</style>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.0/moment.min.js"></script>
<script>
function CheckForm(){
  if(confirm("確認要刪除好友嗎")==true)   
    return true;
  else  
    return false;
}
</script>
</head>
<body onload="connect();"   onunload="disconnect();">

<table id="FriendList">
<c:forEach var="Friend" items="${friendList}">
	<tr id="${Friend.memberAccount}" style="background-color: gray;">
	<div class=unread></div>
	<div id="chooseOne" style="display:none"></div>
		<td><img src="<%=request.getContextPath()%>/GetPhoto?memberAccount=${Friend.memberAccount}"></td>
		<td>${Friend.memberName}</td>
		<td>
		<form action="<%=request.getContextPath()%>/friend/friend" method="POST" onSubmit="return CheckForm();">
		<input type="submit" value="刪除好友">
		<input type="hidden" name="action" value="delete_Friend_By_FriendList">
		<input type="hidden" name="memberAccountA" value="${memberVO.memberAccount}">
		<input type="hidden" name="memberAccountB" value="${Friend.memberAccount}">
		</form>
		</td>
	</tr>
</c:forEach>
</table>


	<h3 id="statusOutput" class="statusOutput"> </h3>
	<div id="messagesArea" class="panel message-area" ></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" /> 
	</div>

</body>
<script>
var MyPoint = "/ChatWS/<%=memberAccount%>";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

var statusOutput = document.getElementById("statusOutput");
var chooseOne = document.getElementById("chooseOne");
var messagesArea = document.getElementById("messagesArea");
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
					alert(unreadMessage);
				}
			}
			else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.memberAccountA === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = "<span>" + historyData.chatSeen + "</sapn>" + showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.memberAccountA === self ? li.className += 'me' : li.className += 'friend';//處理放自己還是別人(自己右邊別人左邊)
				li.innerHTML ="<span>" +jsonObj.chatSeen+"</sapn>" + jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
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
				"unreadMessage" : "",
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
				a.style.backgroundColor="red";
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
		var container = document.getElementById(memberAccountB);
		container.addEventListener("click", function(e) {
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
					"unreadMessage" : "",
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
				"unreadMessage" : "",
				"message" : ""
			};
		webSocket.send(JSON.stringify(jsonObj));
	}
</script>
</html>