package com.chat.model;

import java.sql.Date;
import java.util.Arrays;

public class ChatVO {
	private String memberAccountA;
	private String memberAccountB;
	private String type;
	private String chatTime;
	private String chatSeen;
	private String whichOne;
	private String unreadMessage;
	private String lastMessage;
	private String message;
	
	public String getMemberAccountA() {
		return memberAccountA;
	}
	public void setMemberAccountA(String memberAccountA) {
		this.memberAccountA = memberAccountA;
	}
	public String getMemberAccountB() {
		return memberAccountB;
	}
	public void setMemberAccountB(String memberAccountB) {
		this.memberAccountB = memberAccountB;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public String getChatSeen() {
		return chatSeen;
	}
	public void setChatSeen(String chatSeen) {
		this.chatSeen = chatSeen;
	}
	public String getWhichOne() {
		return whichOne;
	}
	public void setWhichOne(String whichOne) {
		this.whichOne = whichOne;
	}
	public String getUnreadMessage() {
		return unreadMessage;
	}
	public void setUnreadMessage(String unreadMessage) {
		this.unreadMessage = unreadMessage;
	}
	public String getLastMessage() {
		return lastMessage;
	}
	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "ChatVO [memberAccountA=" + memberAccountA + ", memberAccountB=" + memberAccountB + ", type=" + type
				+ ", chatTime=" + chatTime + ", chatSeen=" + chatSeen + ", whichOne=" + whichOne + ", unreadMessage="
				+ unreadMessage + ", lastMessage=" + lastMessage + ", message=" + message + "]";
	}
	
}