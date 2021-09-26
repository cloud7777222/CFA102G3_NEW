package com.friend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;

public class FriendDAO implements FriendDAO_interface{
	private static DataSource ds=null;
	static {
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT="INSERT INTO FRIEND (memberNoA,memberNoB,friendRequest,friendStatus) VALUES(?,?,?,?)";
	private static final String UPDATE="UPDATE FRIEND SET friendRequest=?,friendStatus=? WHERE memberNoA=? AND memberNoB=?";
	private static final String DELETE="DELETE FROM FRIEND WHERE memberNoA=? AND memberNoB=?";
	private static final String GET_ONE="SELECT * FROM FRIEND WHERE memberNoA=? AND memberNoB=?";
	private static final String GET_ALL="SELECT * FROM FRIEND WHERE memberNoA=?";
	private static final String CHECK_IS_FRIEND="SELECT * FROM FRIEND WHERE memberNoA=? AND memberNoB=?";
	private static final String GET_FRIEND_REQUEST="SELECT * FROM FRIEND F  LEFT JOIN MEMBER M ON F.memberNoB=M.MemberNo WHERE F.memberNoA=? AND F.friendStatus=1";
	private static final String GET_FRIEND="SELECT * FROM FRIEND F  LEFT JOIN MEMBER M ON F.memberNoB=M.MemberNo WHERE F.memberNoA=? AND F.friendStatus=3";
	private static final String GET_FRIEND_WS="SELECT DISTINCT memberNo,memberAccount,memberPassword,memberPhoto,memberName,memberGender,memberBirthday,memberJob,memberCountry,memberPhone,memberEmail,memberIntroduce,memberPoint,memberBlackList FROM FRIEND F  LEFT JOIN MEMBER M ON F.memberNoB=M.MemberNo WHERE M.memberNo=? or(F.memberNoA=? AND F.friendStatus=3)";
	
	@Override
	public void insert(FriendVO friendVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
		con=ds.getConnection();
		pstmt=con.prepareStatement(INSERT);
		pstmt.setInt(1, friendVO.getMemberNoA());
		pstmt.setInt(2, friendVO.getMemberNoB());
		pstmt.setInt(3, friendVO.getFriendRequest());
		pstmt.setInt(4, friendVO.getFriendStatus());
		pstmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(FriendVO friendVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setInt(1, friendVO.getFriendRequest());
			pstmt.setInt(2, friendVO.getFriendStatus());
			pstmt.setInt(3, friendVO.getMemberNoA());
			pstmt.setInt(4, friendVO.getMemberNoB());
			pstmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
				con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer memberNoA,Integer memberNoB) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, memberNoA);
			pstmt.setInt(2, memberNoB);
			pstmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
				con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public FriendVO get_one(Integer memberNoA, Integer memberNoB) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		FriendVO friendVO=null;
		
		try {
		con=ds.getConnection();
		pstmt=con.prepareStatement(GET_ONE);
		pstmt.setInt(1, memberNoA);
		pstmt.setInt(2, memberNoB);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			friendVO=new FriendVO();
			friendVO.setMemberNoA(rs.getInt("memberNoA"));
			friendVO.setMemberNoB(rs.getInt("memberNoB"));
			friendVO.setFriendRequest(rs.getInt("friendRequest"));
			friendVO.setFriendStatus(rs.getInt("friendStatus"));
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return friendVO;
	}


	@Override
	public List<FriendVO> get_all(Integer memberNoA) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		FriendVO friendVO=null;
		List<FriendVO> list=new ArrayList<FriendVO>();
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL);
			pstmt.setInt(1, memberNoA);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				friendVO=new FriendVO();
				friendVO.setMemberNoA(rs.getInt("memberNoA"));
				friendVO.setMemberNoB(rs.getInt("memberNoB"));
				friendVO.setFriendRequest(rs.getInt("friendRequest"));
				friendVO.setFriendStatus(rs.getInt("friendStatus"));
				list.add(friendVO);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

	@Override
	public boolean checkIsFriend(Integer memberNoA, Integer memberNoB) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean check=false;
		try {
		con=ds.getConnection();
		pstmt=con.prepareStatement(CHECK_IS_FRIEND);
		pstmt.setInt(1, memberNoA);
		pstmt.setInt(2, memberNoB);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			check=true;
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return check;
	}

	@Override
	public List<MemberVO> getFriendRequest(Integer memberNoA) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_FRIEND_REQUEST);
			pstmt.setInt(1, memberNoA);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO=new MemberVO();
				memberVO.setMemberNo(rs.getInt("memberNo"));
				memberVO.setMemberAccount(rs.getString("memberAccount"));
				memberVO.setMemberPassword(rs.getString("memberPassword"));
				memberVO.setMemberPhoto(rs.getBytes("memberPhoto"));
				memberVO.setMemberName(rs.getString("memberName"));
				memberVO.setMemberGender(rs.getInt("memberGender"));
				memberVO.setMemberBirthday(rs.getDate("memberBirthday"));
				memberVO.setMemberJob(rs.getString("memberJob"));
				memberVO.setMemberCountry(rs.getInt("memberCountry"));
				memberVO.setMemberPhone(rs.getString("memberPhone"));
				memberVO.setMemberEmail(rs.getString("memberEmail"));
				memberVO.setMemberIntroduce(rs.getString("memberIntroduce"));
				memberVO.setMemberPoint(rs.getInt("memberPoint"));
				memberVO.setMemberBlackList(rs.getInt("memberBlackList"));
				list.add(memberVO);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<MemberVO> getFriend(Integer memberNoA) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_FRIEND);
			pstmt.setInt(1, memberNoA);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO=new MemberVO();
				memberVO.setMemberNo(rs.getInt("memberNo"));
				memberVO.setMemberAccount(rs.getString("memberAccount"));
				memberVO.setMemberPassword(rs.getString("memberPassword"));
				memberVO.setMemberPhoto(rs.getBytes("memberPhoto"));
				memberVO.setMemberName(rs.getString("memberName"));
				memberVO.setMemberGender(rs.getInt("memberGender"));
				memberVO.setMemberBirthday(rs.getDate("memberBirthday"));
				memberVO.setMemberJob(rs.getString("memberJob"));
				memberVO.setMemberCountry(rs.getInt("memberCountry"));
				memberVO.setMemberPhone(rs.getString("memberPhone"));
				memberVO.setMemberEmail(rs.getString("memberEmail"));
				memberVO.setMemberIntroduce(rs.getString("memberIntroduce"));
				memberVO.setMemberPoint(rs.getInt("memberPoint"));
				memberVO.setMemberBlackList(rs.getInt("memberBlackList"));
				list.add(memberVO);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<MemberVO> getFriendWS(Integer memberNoA) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_FRIEND_WS);
			pstmt.setInt(1, memberNoA);
			pstmt.setInt(2, memberNoA);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO=new MemberVO();
				memberVO.setMemberNo(rs.getInt("memberNo"));
				memberVO.setMemberAccount(rs.getString("memberAccount"));
				memberVO.setMemberPassword(rs.getString("memberPassword"));
				memberVO.setMemberPhoto(rs.getBytes("memberPhoto"));
				memberVO.setMemberName(rs.getString("memberName"));
				memberVO.setMemberGender(rs.getInt("memberGender"));
				memberVO.setMemberBirthday(rs.getDate("memberBirthday"));
				memberVO.setMemberJob(rs.getString("memberJob"));
				memberVO.setMemberCountry(rs.getInt("memberCountry"));
				memberVO.setMemberPhone(rs.getString("memberPhone"));
				memberVO.setMemberEmail(rs.getString("memberEmail"));
				memberVO.setMemberIntroduce(rs.getString("memberIntroduce"));
				memberVO.setMemberPoint(rs.getInt("memberPoint"));
				memberVO.setMemberBlackList(rs.getInt("memberBlackList"));
				list.add(memberVO);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}


}
