package com.member.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void updatePhoto(String memberAccount,byte[] memberPhoto);
	public void updatePassword(String memberAccount,String memberPassword);
	public void updatePoint(String memberAccount,Integer memberPoint);
	public void updateBlackList(String memberAccount,Integer memberBlackList);
	public byte[] getPhoto(String memberAccount);
	public MemberVO getOne(String memberAccount);
	public MemberVO getOne(Integer memberNoA);
	public List<MemberVO> getAll();
	public List<MemberVO> compositeQuery(Map<String, String[]> map);
	public Set<MemberVO> getAllExceptMeBySet(Integer memberNoA);
	public boolean checkAccount(String memberAccount);
	public boolean checkEmail(String memberEmail);
	public boolean checkAccountPassword(String memberAccount,String memberPassword);
	public boolean checkAccountEmail(String memberAccount,String memberEmail);
}
