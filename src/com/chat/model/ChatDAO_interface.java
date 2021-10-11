package com.chat.model;

import java.util.List;

import com.friend.model.FriendVO;

public interface ChatDAO_interface {
	public void insert(ChatVO chatVO);
	public void delete_one(ChatVO chatVO);
	public void delete_all(String memberAccountA);
	public List<String> get_all(String memberAccountA,String memberAccountB);
	public Integer unread_number(String memberAccountA,String memberAccountB);
	public void update_unread_status(String memberAccountA,String memberAccountB);
	public String get_last_message(String memberAccountA,String memberAccountB);
}
