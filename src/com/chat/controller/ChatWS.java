package com.chat.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.chat.model.ChatDAO;
import com.chat.model.ChatVO;
import com.chat.model.State;
import com.friend.model.FriendService;
import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@ServerEndpoint("/ChatWS/{memberAccount}")
public class ChatWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	private static Map<String,String> whichOneMap=new HashMap<String,String>();
	Gson gson=new Gson();
	@OnOpen
	public void onOpen(@PathParam("memberAccount") String memberAccount,Session userSession) {
		sessionsMap.put(memberAccount, userSession);//上線就把自己的Session放到Map裡面
		Set<String> userNames = sessionsMap.keySet();//拿出所有Map的Key
		
		State stateMessage = new State("open", memberAccount, userNames);//送所有人的
		String stateMessageJson = gson.toJson(stateMessage);
		
		Map<String, Session> friendSessionsMap=getFriendMap(memberAccount);
		Collection<Session> sessions =friendSessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {//判斷對方的session有沒有被打開 有開的我再告訴他我上線了
				session.getAsyncRemote().sendText(stateMessageJson);//把open的訊息推出去
			}
		}
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatVO chatVO = gson.fromJson(message, ChatVO.class);//JSON轉回字串 用ChatMessage還原成(參考這個物件)chatMessage的物件
		MemberService memberService=new MemberService();
		String memberAccountA = chatVO.getMemberAccountA();
		String memberAccountB = chatVO.getMemberAccountB();
		ChatDAO chatDAO=new ChatDAO();
		Session receiverSession = sessionsMap.get(memberAccountB);//因為他沒有在線上所以沒有key
		if("unreadMessage".equals(chatVO.getType())) {//未讀訊息數量
			Gson gson=new Gson();
			FriendService friendService=new FriendService();
			MemberVO memberVO=new MemberVO();
			List<MemberVO> list=new ArrayList<MemberVO>();
			List<String> unreadFriendList=new ArrayList<String>();//裝好友帳號跟未讀數量
			memberVO=memberService.getOneMember(memberAccountA);
			Integer memberNoA=memberVO.getMemberNo();
			list=friendService.getFriend(memberNoA);//拿出所有好友
			for(MemberVO friend:list) {
				ChatVO friendUnread=new ChatVO();
				ChatVO lastMessageVO=gson.fromJson(chatDAO.get_last_message(memberAccountA, friend.getMemberAccount()), ChatVO.class);
				friendUnread.setMemberAccountA(memberAccountA);
				friendUnread.setMemberAccountB(friend.getMemberAccount());
				friendUnread.setUnreadMessage(chatDAO.unread_number(memberAccountA, friend.getMemberAccount()).toString());
				friendUnread.setLastMessage(lastMessageVO.getLastMessage());
				unreadFriendList.add(gson.toJson(friendUnread));
				System.out.println(friendUnread.getUnreadMessage());
			}
			ChatVO unreadMsg=new ChatVO();
			unreadMsg.setType("unreadMessage");
			unreadMsg.setMessage(gson.toJson(unreadFriendList));
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(unreadMsg));
				System.out.println("history = " + gson.toJson(unreadMsg));//可以刪掉
			return;
			}
		}
		if ("history".equals(chatVO.getType())) {
			whichOneMap.put(memberAccountA, chatVO.getWhichOne());//把自己現在點的好友放到Map裡
			int unread_number=chatDAO.unread_number(memberAccountA, memberAccountB);//看有沒有未讀訊息
			if(unread_number!=0) {//先看有沒有未讀訊息
				chatDAO.update_unread_status(memberAccountA, memberAccountB);//更改未讀狀態
				List<String> historyDataB =chatDAO.get_all(memberAccountB, memberAccountA);
				String historyMsgB = gson.toJson(historyDataB);
				ChatVO chatVOHistoryB=new ChatVO();
				chatVOHistoryB.setMemberAccountA(memberAccountB);
				chatVOHistoryB.setMemberAccountB(memberAccountA);
				chatVOHistoryB.setType("history");
				chatVOHistoryB.setMessage(historyMsgB);//訊息list
				if (receiverSession != null && receiverSession.isOpen()) {//////////
					String myname=memberService.getOneMember(memberAccountA).getMemberName();//自己的名字
					String friendChoose=whichOneMap.get(memberAccountB);//好友是不是選擇我
					if(myname.equals(friendChoose)) {
					receiverSession.getAsyncRemote().sendText(gson.toJson(chatVOHistoryB));//如果有未讀訊息 更新後對方有上線推給對方
					System.out.println("history = " + gson.toJson(chatVOHistoryB));//可以刪掉
					}
				}
			}
			List<String> historyDataA =chatDAO.get_all(memberAccountA, memberAccountB);
			String historyMsgA = gson.toJson(historyDataA);
			ChatVO chatVOHistoryA=new ChatVO();
			chatVOHistoryA.setMemberAccountA(memberAccountA);
			chatVOHistoryA.setMemberAccountB(memberAccountB);
			chatVOHistoryA.setType("history");
			chatVOHistoryA.setMessage(historyMsgA);//訊息list
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(chatVOHistoryA));
				System.out.println("history = " + gson.toJson(chatVOHistoryA));//可以刪掉
				return;
			}
		}
		if ("chat".equals(chatVO.getType())) {
		String myname=memberService.getOneMember(memberAccountA).getMemberName();//自己的名字
		String friendChoose=whichOneMap.get(memberAccountB);//好友是不是選擇我
		if (receiverSession != null && receiverSession.isOpen()) {
			if(myname.equals(friendChoose)) {
				chatVO.setChatSeen("已讀");//如果是就把狀態改為已讀
				chatDAO.insert(chatVO);
				ChatVO lastMessageVO=gson.fromJson(chatDAO.get_last_message(memberAccountA,memberAccountB), ChatVO.class);
				chatVO.setLastMessage(lastMessageVO.getLastMessage());
				receiverSession.getAsyncRemote().sendText(gson.toJson(chatVO));//如果有上線的話 message的seen改成""
				userSession.getAsyncRemote().sendText(gson.toJson(chatVO));
				return;
			}
			else{
				chatDAO.insert(chatVO);
				ChatVO lastMessageVO=gson.fromJson(chatDAO.get_last_message(memberAccountA,memberAccountB), ChatVO.class);
				chatVO.setChatSeen("");
				chatVO.setLastMessage(lastMessageVO.getLastMessage());
				userSession.getAsyncRemote().sendText(gson.toJson(chatVO));
				ChatVO chatVOB=chatVO;
				chatVOB.setType("unchoose");
				chatVOB.setUnreadMessage(chatDAO.unread_number(memberAccountB, memberAccountA).toString());
				receiverSession.getAsyncRemote().sendText(gson.toJson(chatVOB));//如果有上線的話 message的seen改成"
				System.out.println("Message received: " +chatVOB);
				return;
			}
		}else {
			chatDAO.insert(chatVO);
			ChatVO lastMessageVO=gson.fromJson(chatDAO.get_last_message(memberAccountA,memberAccountB), ChatVO.class);
			chatVO.setChatSeen("");
			chatVO.setLastMessage(lastMessageVO.getLastMessage());
			userSession.getAsyncRemote().sendText(gson.toJson(chatVO));
			return;
		}
	}
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				whichOneMap.remove(userName);
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
		for(String a:userNames) {
			System.out.println(a);
		}
	}
	
	
	
	
	public Map<String, Session> getFriendMap(String memberAccount){
		List<MemberVO> friendList=new ArrayList<MemberVO>();
		Map<String, Session> friendSessionsMap = new ConcurrentHashMap<>();//有上線的好友(有Session)
		MemberService memberService=new MemberService();
		FriendService friendService=new FriendService();
		
		MemberVO memberVO=memberService.getOneMember(memberAccount);
		Integer memberNoA=memberVO.getMemberNo();
		friendList=friendService.getFriendWS(memberNoA);//拿到好友的list
		
		for(MemberVO friend:friendList) {
			if(sessionsMap.get(friend.getMemberAccount())!=null) {
				friendSessionsMap.put(friend.getMemberAccount(), sessionsMap.get(friend.getMemberAccount()));
			}
		}
		return friendSessionsMap;
	}
}
