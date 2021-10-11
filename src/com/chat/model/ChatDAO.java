package com.chat.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.friend.model.FriendService;
import com.friend.model.FriendVO;
import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ChatDAO implements ChatDAO_interface{
	
	private static JedisPool pool =JedisConnectionPool.getJedisPool();
	@Override
	public void insert(ChatVO chatVO) {
		String keyA=new StringBuilder(chatVO.getMemberAccountA()).append(":").append(chatVO.getMemberAccountB()).toString();
		String keyB=new StringBuilder(chatVO.getMemberAccountB()).append(":").append(chatVO.getMemberAccountA()).toString();
		String jsonStrA="";
		String jsonStrB="";
		Jedis jedis = pool.getResource();//拿連線
		jsonStrB = new JSONObject(chatVO).toString();
		jedis.rpush(keyB, jsonStrB);
		
		chatVO.setChatSeen("已讀");//自己已讀
		jsonStrA = new JSONObject(chatVO).toString();
		jedis.rpush(keyA, jsonStrA);
		jedis.close();
	}

	@Override
	public void delete_one(ChatVO chatVO) {
		Jedis jedis = pool.getResource();
		System.out.println("hi");
		jedis.close();		
	}

	@Override
	public void delete_all(String memberNoA) {
		Jedis jedis = pool.getResource();
		System.out.println("hi");
		jedis.close();		
	}


	
	@Override
	public List<String> get_all(String memberAccountA,String memberAccountB) {
		String key=new StringBuilder(memberAccountA).append(":").append(memberAccountB).toString();
		Jedis jedis = pool.getResource();

		List<String> list=jedis.lrange(key, 0, -1);
		jedis.close();
		return list;
	}

	@Override
	public Integer unread_number(String memberAccountA, String memberAccountB) {
		int count=0;
		String key=new StringBuilder(memberAccountA).append(":").append(memberAccountB).toString();
		Jedis jedis = pool.getResource();
		List<String> list=jedis.lrange(key, 0, -1);
		Gson gson=new Gson();
		
		
		for(String obj:list) {
			ChatVO chatVO=gson.fromJson(obj, ChatVO.class);
			if(!chatVO.getChatSeen().equals("已讀")) {
				count++;
			}
		}
		jedis.close();
		return count;
	}

	@Override
	public void update_unread_status(String memberAccountA, String memberAccountB) {
		String keyA=new StringBuilder(memberAccountA).append(":").append(memberAccountB).toString();
		String keyB=new StringBuilder(memberAccountB).append(":").append(memberAccountA).toString();
		Jedis jedis = pool.getResource();
		int unreadmessage =unread_number(memberAccountA, memberAccountB);
		Gson gson=new Gson();
		for(int i=1;i<=unreadmessage;i++) {
			String objA=jedis.lindex(keyA, -i);
			ChatVO chatVOA=gson.fromJson(objA, ChatVO.class);
			chatVOA.setChatSeen("已讀");
			String updateObjA=gson.toJson(chatVOA);
			jedis.lset(keyA, -i, updateObjA);
			
			String objB=jedis.lindex(keyB, -i);
			ChatVO chatVOB=gson.fromJson(objB, ChatVO.class);
			chatVOB.setChatSeen("已讀");
			String updateObjB=gson.toJson(chatVOB);
			jedis.lset(keyB, -i, updateObjB);
		}
		jedis.close();	
	}

	@Override
	public String get_last_message(String memberAccountA, String memberAccountB) {
		String key=new StringBuilder(memberAccountA).append(":").append(memberAccountB).toString();
		Jedis jedis = pool.getResource();
		Gson gson=new Gson();
		ChatVO chatVO=new ChatVO();
		chatVO.setMemberAccountA(memberAccountA);
		chatVO.setMemberAccountB(memberAccountB);
		chatVO.setType("");
		chatVO.setChatTime("");
		chatVO.setChatSeen("");
		chatVO.setWhichOne("");
		chatVO.setUnreadMessage("");
		chatVO.setLastMessage("暫無聊天紀錄");
		chatVO.setMessage("");
		String lastChat=null;
		if(jedis.lindex(key, -1)!=null) {
			String lastMessage=jedis.lindex(key, -1);
			ChatVO lastChatVO=gson.fromJson(lastMessage, ChatVO.class);
			chatVO.setLastMessage(lastChatVO.getMessage());
			lastChat=gson.toJson(chatVO);
		}
		else {
			lastChat=gson.toJson(chatVO);
		}
		
		
		jedis.close();
		return lastChat;
	}

}
