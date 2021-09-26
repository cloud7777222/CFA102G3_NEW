package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface{
	private static DataSource ds=null;
	static {
		try {
			Context ctx=new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT="INSERT INTO MEMBER(memberAccount,memberPassword,memberEmail) VALUES( ?, ?, ?)";
	private static final String UPDATE="UPDATE MEMBER SET memberPhoto=?,memberName=?,memberGender=?,memberBirthday=?,memberJob=?,memberCountry=?,memberPhone=?, memberIntroduce=? WHERE memberAccount=?";
	private static final String UPDATE_PASSWORD="UPDATE MEMBER SET memberPassword=? WHERE memberAccount=?";
	private static final String UPDATE_POINT="UPDATE MEMBER SET memberPoint=? WHERE memberAccount=?";
	private static final String UPDATE_BLACKLIST="UPDATE MEMBER SET memberBlackList=? WHERE memberAccount=?";
	private static final String GET_ONE="SELECT * FROM MEMBER WHERE memberAccount=?";
	private static final String GET_ONE_BYNO="SELECT * FROM MEMBER WHERE memberno=?";
	private static final String GET_ALL="SELECT * FROM MEMBER";
	private static final String CHECK_ACCOUNT="SELECT * FROM MEMBER WHERE memberAccount=?";
	private static final String CHECK_EMAIL="SELECT * FROM MEMBER WHERE memberEmail=?";
	private static final String CHECK_ACCOUNT_PASSWORD="SELECT * FROM MEMBER WHERE memberAccount=? AND memberPassword=?";
	private static final String CHECK_ACCOUNT_EMAIL="SELECT * FROM MEMBER WHERE memberAccount=? AND memberEmail";
	
	@Override
	public void insert(MemberVO memberVO) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT);

			pstmt.setString(1, memberVO.getMemberAccount());
			pstmt.setString(2, memberVO.getMemberPassword());
			pstmt.setString(3, memberVO.getMemberEmail());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
//			throw new RuntimeException("A database error occured. "
//					+ e.getMessage());
			// Clean up JDBC resources
		}
		finally {
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void update(MemberVO memberVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);
			
			pstmt.setBytes(1, memberVO.getMemberPhoto());
			pstmt.setString(2, memberVO.getMemberName());
			pstmt.setInt(3, memberVO.getMemberGender());
			pstmt.setDate(4, memberVO.getMemberBirthday());
			pstmt.setString(5, memberVO.getMemberJob());
			pstmt.setInt(6, memberVO.getMemberCountry());
			pstmt.setString(7, memberVO.getMemberPhone());
			pstmt.setString(8, memberVO.getMemberIntroduce());
			pstmt.setString(9, memberVO.getMemberAccount());
			pstmt.executeUpdate();
		} catch (SQLException e) {
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
	public void updatePassword(String memberAccount,String memberPassword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_PASSWORD);
			pstmt.setString(1, memberPassword);
			pstmt.setString(2, memberAccount);
			pstmt.executeUpdate();
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
	public void updatePoint(String memberAccount,Integer memberPoint) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_POINT);
			pstmt.setInt(1, memberPoint);
			pstmt.setString(2, memberAccount);
			pstmt.executeUpdate();
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
	public void updateBlackList(String memberAccount,Integer memberBlackList) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE_BLACKLIST);
			pstmt.setInt(1, memberBlackList);
			pstmt.setString(2, memberAccount);
			pstmt.executeUpdate();
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
	public MemberVO getOne(String memberAccount) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE);
			pstmt.setString(1, memberAccount);
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
		
		return memberVO;
	}

	
	


	@Override
	public List<MemberVO> getAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		List<MemberVO> list=new ArrayList<MemberVO>();
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL);
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
	public boolean checkAccount(String memberAccount) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean check=false;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(CHECK_ACCOUNT);
			pstmt.setString(1, memberAccount);
			rs=pstmt.executeQuery();
			
				if(rs.next()) {
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
	public boolean checkEmail(String memberEmail) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean check=false;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(CHECK_EMAIL);
			pstmt.setString(1, memberEmail);
			rs=pstmt.executeQuery();
			
				if(rs.next()) {
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
	public boolean checkAccountPassword(String memberAccount, String memberPassword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean check=false;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(CHECK_ACCOUNT_PASSWORD);
			pstmt.setString(1, memberAccount);
			pstmt.setString(2, memberPassword);
			rs=pstmt.executeQuery();
			
				if(rs.next()) {
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
	public boolean checkAccountEmail(String memberAccount, String memberEmail) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean check=false;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(CHECK_ACCOUNT_EMAIL);
			pstmt.setString(1, memberAccount);
			pstmt.setString(2, memberEmail);
			rs=pstmt.executeQuery();
			
				if(rs.next()) {
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
	public MemberVO getOneByNo(Integer memberno) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		MemberVO memberVO=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_BYNO);
			pstmt.setInt(1, memberno);
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
		
		return memberVO;
	}




}
			

