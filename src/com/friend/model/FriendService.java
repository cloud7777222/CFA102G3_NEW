package com.friend.model;

import java.util.List;

import com.member.model.MemberVO;

public class FriendService {
	private FriendDAO_interface dao;
	
	public FriendService() {
		dao=new FriendDAO();
	}
	
	public FriendVO addFriend(Integer memberNoA,Integer memberNoB,Integer friendRequest,Integer friendStatus) {
		FriendVO friendVO=new FriendVO();
		friendVO.setMemberNoA(memberNoA);
		friendVO.setMemberNoB(memberNoB);
		friendVO.setFriendRequest(friendRequest);
		friendVO.setFriendStatus(friendStatus);
		dao.insert(friendVO);
		
		return friendVO;
	}
	
	public FriendVO updateFriend(Integer memberNoA,Integer memberNoB,Integer friendRequest,Integer friendStatus) {
		FriendVO friendVO=new FriendVO();
		friendVO.setMemberNoA(memberNoA);
		friendVO.setMemberNoB(memberNoB);
		friendVO.setFriendRequest(friendRequest);
		friendVO.setFriendStatus(friendStatus);
		dao.update(friendVO);
		
		return friendVO;
	}
	
	public void deleteFriend(Integer memberNoA,Integer memberNoB) {
		dao.delete(memberNoA, memberNoB);
	}
	
	public FriendVO getOneFriend(Integer memberNoA,Integer memberNoB) {
		return dao.get_one(memberNoA, memberNoB);
	}
	
	public List<FriendVO> getAllFriend(Integer memberNoA){
		return dao.get_all(memberNoA);
	}
	
	public boolean checkIsFriend(Integer memberNoA,Integer memberNoB) {
		return dao.checkIsFriend(memberNoA, memberNoB);
	}
	
	public List<MemberVO> getFriendRequest(Integer memberNoA){
		return dao.getFriendRequest(memberNoA);
	}
	
	public List<MemberVO> getFriend(Integer memberNoA){
		return dao.getFriend(memberNoA);
	}
	
	public List<MemberVO> getFriendWS(Integer memberNoA){
		return dao.getFriendWS(memberNoA);
	}
}
