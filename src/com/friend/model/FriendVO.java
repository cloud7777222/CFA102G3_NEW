package com.friend.model;

public class FriendVO {
	private Integer memberNoA;
	private Integer memberNoB;
	private Integer friendRequest;
	private Integer friendStatus;
	
	
	public Integer getMemberNoA() {
		return memberNoA;
	}
	public void setMemberNoA(Integer memberNoA) {
		this.memberNoA = memberNoA;
	}
	public Integer getMemberNoB() {
		return memberNoB;
	}
	public void setMemberNoB(Integer memberNoB) {
		this.memberNoB = memberNoB;
	}
	public Integer getFriendRequest() {
		return friendRequest;
	}
	public void setFriendRequest(Integer friendRequest) {
		this.friendRequest = friendRequest;
	}
	public Integer getFriendStatus() {
		return friendStatus;
	}
	public void setFriendStatus(Integer friendStatus) {
		this.friendStatus = friendStatus;
	}
	
	@Override
	public String toString() {
		return "FriendVO [memberNoA=" + memberNoA + ", memberNoB=" + memberNoB + ", friendRequest=" + friendRequest
				+ ", friendStatus=" + friendStatus + "]";
	}

}
