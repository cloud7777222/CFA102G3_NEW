package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void updatePassword(String memberAccount,String memberPassword);
	public void updatePoint(String memberAccount,Integer memberPoint);
	public void updateBlackList(String memberAccount,Integer memberBlackList);
	public MemberVO getOne(String memberAccount);
	public MemberVO getOneByNo(Integer memberno);
	public List<MemberVO> getAll();
	public boolean checkAccount(String memberAccount);
	public boolean checkEmail(String memberEmail);
	public boolean checkAccountPassword(String memberAccount,String memberPassword);
	public boolean checkAccountEmail(String memberAccount,String memberEmail);
}
