package com.chat.model;

import java.util.Map;
import java.util.Set;

public class State {
	private String type;//action用來辨識這個訊息要幹嘛用的 例如有人上線 或是有人發送訊息 或是歷史訊息
	// the user changing the state
	private String user;
	// total users
	private Set<String> users;
	
	
	
	
	public State(String type, String user, Set<String> users) {
		super();
		this.type = type;
		this.user = user;
		this.users = users;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Set<String> getUsers() {
		return users;
	}
	public void setUsers(Set<String> users) {
		this.users = users;
	}
	


}