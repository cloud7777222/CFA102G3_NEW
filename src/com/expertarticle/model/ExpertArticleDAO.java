package com.expertarticle.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ExpertArticleDAO implements ExpertArticleDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// �s�W�׬dsql���O
	public static final String INSERT_SQL = "insert into expertarticle (expertNo, articleContent, articlePhoto, articleTime, articleState, numOfLike) values (?, ?, ?, ?, ?, ?)";
	public static final String UpdateBy_expertNo_SQL = "update expertarticle set articleContent = ?, articlePhoto = ?, articleState = ? where articleNo = ?";
	public static final String DeletBy_expertNo_SQL = "delete from expertarticle where articleNo = ?";
	public static final String FindBy_expertNo_SQL = "select * from expertarticle where articleNo = ?";
	public static final String getAll_SQL = "select * from expertarticle";

	// ��@�s�W: �M�a�s�W�@���M��峹
	@Override
	public void insert(ExpertArticleVO expertArticleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setInt(1, expertArticleVO.getExpertNo());			
			pstmt.setString(2, expertArticleVO.getArticleContent());
			pstmt.setBytes(3, expertArticleVO.getArticlePhoto());
			pstmt.setDate(4, expertArticleVO.getArticleTime());
			pstmt.setInt(5, expertArticleVO.getArticleState());
			pstmt.setInt(6, expertArticleVO.getNumOfLike());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	// ��s�M�椺�e�ιϤ�
	@Override
	public void update(ExpertArticleVO expertArticleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UpdateBy_expertNo_SQL);

			pstmt.setString(1, expertArticleVO.getArticleContent());
			pstmt.setBytes(2, expertArticleVO.getArticlePhoto());
			pstmt.setInt(3, expertArticleVO.getArticleState());
			pstmt.setInt(4, expertArticleVO.getArticleNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
			// Clean up resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	// �R���@�h�K��, �ھ�PK : articleNo
	@Override
	public void delete(Integer articleNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DeletBy_expertNo_SQL);

			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	
	
	// �j�M�@���M��峹, �ھ�PK : articleNo
	@Override
	public ExpertArticleVO findByPrimaryKey(Integer articleNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ExpertArticleVO expertArticleVO = null;
		ResultSet rs = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBy_expertNo_SQL);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expertArticleVO = new ExpertArticleVO();
				expertArticleVO.setArticleNo(rs.getInt("articleNo"));
				expertArticleVO.setExpertNo(rs.getInt("expertNo"));
				expertArticleVO.setArticleContent(rs.getString("articleContent"));
				expertArticleVO.setArticlePhoto(rs.getBytes("articlePhoto")); 
				expertArticleVO.setArticleTime(rs.getDate("articleTime")); 
				expertArticleVO.setArticleState(rs.getInt("articleState"));
				expertArticleVO.setNumOfLike(rs.getInt("numOfLike"));
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
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return expertArticleVO;
	}

	
	
	// ��ܩҦ��M��峹
	@Override
	public List<ExpertArticleVO> getAll() {

		List<ExpertArticleVO> list = new ArrayList<ExpertArticleVO>();
		ExpertArticleVO expertArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expertArticleVO = new ExpertArticleVO();
				expertArticleVO.setArticleNo(rs.getInt("articleNo"));
				expertArticleVO.setExpertNo(rs.getInt("expertNo"));
				expertArticleVO.setArticleContent(rs.getString("articleContent"));
				expertArticleVO.setArticlePhoto(rs.getBytes("articlePhoto"));
				expertArticleVO.setArticleTime(rs.getDate("articleTime")); 
				expertArticleVO.setArticleState(rs.getInt("articleState"));
				expertArticleVO.setNumOfLike(rs.getInt("numOfLike"));
				list.add(expertArticleVO); // Store the row in the list
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
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return list;
	}

	

}
