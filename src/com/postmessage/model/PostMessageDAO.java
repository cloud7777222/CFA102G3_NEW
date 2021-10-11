package com.postmessage.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class PostMessageDAO implements PostMessageDAO_interface{
	
	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
		private static DataSource ds = null;
		static {
			Context ctx;
			try {
				ctx = new javax.naming.InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		// �s�W�׬dsql���O
		public static final String INSERT_SQL = "insert into postmessage (memberNo, postNo, mesContent, mesTime, mesState) values (?, ?, ?, ?, ?)";
		public static final String UpdateBy_mesNo_SQL = "update postmessage set memberNo = ?, postNo = ?, mesContent = ?, mesTime = ?, mesState = ? where mesNo = ?";
		public static final String DeletBy_mesNo_SQL = "delete from postmessage where mesNo = ?";
		public static final String FindBy_mesNo_SQL = "select * from postmessage where mesNo = ?";
		public static final String getAll_SQL = "select * from postmessage";
		
		
	
		//�s�W�@�h�׾¤峹�d��
	@Override
	public void insert(PostMessageVO postMessageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setInt(1, postMessageVO.getMemberNo());
			pstmt.setInt(2, postMessageVO.getPostNo());
			pstmt.setString(3, postMessageVO.getMesContent());
			pstmt.setDate(4, postMessageVO.getMesTime());
			pstmt.setInt(5, postMessageVO.getMesState());
		
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurd. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}

	// ��s�峹�d�����e�Ϊ��A
	@Override
	public void update(PostMessageVO postMessageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UpdateBy_mesNo_SQL);

			pstmt.setInt(1, postMessageVO.getPostNo());
			pstmt.setInt(2, postMessageVO.getMemberNo());
			pstmt.setString(3, postMessageVO.getMesContent());
			pstmt.setDate(4, postMessageVO.getMesTime());
			pstmt.setInt(5, postMessageVO.getMesState());
			pstmt.setInt(6, postMessageVO.getMesNo());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurd. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	// �R���@�h�峹�d��, �ھ�PK : mesNo
	@Override
	public void delete(Integer mesNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DeletBy_mesNo_SQL);

			pstmt.setInt(1, mesNo);
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurd. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	// �j�M�@�g�׾¤峹, �ھ�PK : mesNo
	@Override
	public PostMessageVO findByPrimaryKey(Integer mesNo) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostMessageVO postMessageVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBy_mesNo_SQL);

			pstmt.setInt(1, mesNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postMessageVO = new PostMessageVO();
				postMessageVO.setMesNo(rs.getInt("mesNo"));				
				postMessageVO.setMemberNo(rs.getInt("memberNo"));
				postMessageVO.setPostNo(rs.getInt("postNo"));
				postMessageVO.setMesContent(rs.getString("mesContent"));
				postMessageVO.setMesTime(rs.getDate("mesTime"));
				postMessageVO.setMesState(rs.getInt("mesState"));				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return postMessageVO;
	}

	// ��ܩҦ��׾¤峹�d��
	@Override
	public List<PostMessageVO> getAll() {
		
		List<PostMessageVO> list = new ArrayList<PostMessageVO>();
		PostMessageVO postMessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postMessageVO = new PostMessageVO();
				postMessageVO.setMesNo(rs.getInt("mesNo"));				
				postMessageVO.setMemberNo(rs.getInt("memberNo"));
				postMessageVO.setPostNo(rs.getInt("postNo"));
				postMessageVO.setMesContent(rs.getString("mesContent"));
				postMessageVO.setMesTime(rs.getDate("mesTime"));
				postMessageVO.setMesState(rs.getInt("mesState"));	
				list.add(postMessageVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	
}
