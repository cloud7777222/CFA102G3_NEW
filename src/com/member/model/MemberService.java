package com.member.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberService {
	
	private MemberDAO_interface dao;
		
	public MemberService() {
			dao=new MemberDAO();
		}
	
	public void addMember(MemberVO memberVO) {
		dao.insert(memberVO);
	}
	
	public void updateMember(MemberVO memberVO) {
		dao.update(memberVO);
	}
	
	public void updatePhoto(String memberAccount,byte[] memberPhoto) {
		dao.updatePhoto(memberAccount, memberPhoto);
	}
	
	public void updatePassword(String memberAccount,String memberPassword) {
		dao.updatePassword(memberAccount,memberPassword);
	}
	
	
	public void updatePoint(String memberAccount,Integer memberPoint) {
		dao.updatePoint(memberAccount,memberPoint);
	}
	
	public void updateBlackList(String memberAccount,Integer memberBlackList) {
		dao.updateBlackList(memberAccount,memberBlackList);
	}
	
	public byte[] getPhoto(String memberAccount) {
		return dao.getPhoto(memberAccount);
	}
	
	public MemberVO getOneMember(String memberAccount) {
		return dao.getOne(memberAccount);
	}
	
	public MemberVO getOneMember(Integer memberNoA) {
		return dao.getOne(memberNoA);
	}
	
	public List<MemberVO> getAllMember(){
		return dao.getAll();
	}
	
	
	public Set<MemberVO> getAllMemberExceptMeBySet(Integer memberNoA){
		return dao.getAllExceptMeBySet(memberNoA);
	}
	
	public boolean checkAccount(String memberAccount) {
		return dao.checkAccount(memberAccount);
	}
	
	public boolean checkEmail(String memberEmail) {
		return dao.checkEmail(memberEmail);
	}
	
	public boolean checkAccountPassword(String memberAccount,String memberPassword) {
		return dao.checkAccountPassword(memberAccount,memberPassword);
	}
	
	public boolean checkAccountEmail(String memberAccount,String memberEmail) {
		return dao.checkAccountEmail(memberAccount,memberEmail);
	}
	
	
	
}
