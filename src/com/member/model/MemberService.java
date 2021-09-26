package com.member.model;

import java.sql.Date;
import java.util.List;

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
	
	
	public void updatePassword(String memberAccount,String memberPassword) {
		dao.updatePassword(memberAccount,memberPassword);
	}
	
	public void updatePoint(String memberAccount,Integer memberPoint) {
		dao.updatePoint(memberAccount,memberPoint);
	}
	
	public void updateBlackList(String memberAccount,Integer memberBlackList) {
		dao.updateBlackList(memberAccount,memberBlackList);
	}
	
	
	public MemberVO getOneMember(String memberAccount) {
		return dao.getOne(memberAccount);
	}
	public MemberVO getOneMemberByNo(Integer memberno) {
		return dao.getOneByNo(memberno);
	}
	public List<MemberVO> getAllMember(){
		return dao.getAll();
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
