package com.member.model;

import java.sql.Date;
import java.util.Arrays;

public class MemberVO {
	private Integer memberNo;
	private String memberAccount;
	private String memberPassword;
	private byte[] memberPhoto;
	private String memberName;
	private Integer memberGender;
	private Date memberBirthday;
	private String memberJob;
	private Integer memberCountry;
	private String memberPhone;
	private String memberEmail;
	private String memberIntroduce;
	private Integer memberPoint;
	private Integer memberBlackList;
	
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public byte[] getMemberPhoto() {
		return memberPhoto;
	}
	public void setMemberPhoto(byte[] memberPhoto) {
		this.memberPhoto = memberPhoto;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Integer getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(Integer memberGender) {
		this.memberGender = memberGender;
	}
	public Date getMemberBirthday() {
		return memberBirthday;
	}
	public void setMemberBirthday(Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}
	public String getMemberJob() {
		return memberJob;
	}
	public void setMemberJob(String memberJob) {
		this.memberJob = memberJob;
	}
	public Integer getMemberCountry() {
		return memberCountry;
	}
	public void setMemberCountry(Integer memberCountry) {
		this.memberCountry = memberCountry;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberIntroduce() {
		return memberIntroduce;
	}
	public void setMemberIntroduce(String memberIntroduce) {
		this.memberIntroduce = memberIntroduce;
	}
	public Integer getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(Integer memberPoint) {
		this.memberPoint = memberPoint;
	}
	public Integer getMemberBlackList() {
		return memberBlackList;
	}
	public void setMemberBlackList(Integer memberBlackList) {
		this.memberBlackList = memberBlackList;
	}
	@Override
	public String toString() {
		return "MemberVO [memberNo=" + memberNo + ", memberAccount=" + memberAccount + ", memberPassword="
				+ memberPassword + ", memberPhoto=" + Arrays.toString(memberPhoto) + ", memberName=" + memberName
				+ ", memberGender=" + memberGender + ", memberBirthday=" + memberBirthday + ", memberJob=" + memberJob
				+ ", memberCountry=" + memberCountry + ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail
				+ ", memberIntroduce=" + memberIntroduce + ", memberPoint=" + memberPoint + ", memberBlackList="
				+ memberBlackList + "]";
	}
}
 