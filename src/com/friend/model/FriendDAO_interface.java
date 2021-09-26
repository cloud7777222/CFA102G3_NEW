package com.friend.model;

import java.util.List;

import com.member.model.MemberVO;

public interface FriendDAO_interface {
	public void insert(FriendVO friendVO);
	public void update(FriendVO friendVO);
	public void delete(Integer memberNoA,Integer memberNoB);
	public FriendVO get_one(Integer memberNoA,Integer memberNoB);
	public List<FriendVO> get_all(Integer memberNoA);
	public List<MemberVO> getFriendRequest(Integer memberNoA);
	public List<MemberVO> getFriend(Integer memberNoA);
	public List<MemberVO> getFriendWS(Integer memberNoA);
	public boolean checkIsFriend(Integer memberNoA,Integer memberNoB);
}
