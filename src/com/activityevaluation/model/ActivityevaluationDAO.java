package com.activityevaluation.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
public class ActivityevaluationDAO implements ActivityevaluationDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO ACTIVITYEVALUATION (actNo,memberNo,actStarRate) VALUES (?,?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT actNo,memberNo,actStarRate FROM ACTIVITYEVALUATION order by actNo";
		private static final String GET_ONE_STMT = 
			"SELECT actNo,memberNo,actStarRate where actNo = ?";
		private static final String DELETE = 
			"DELETE FROM ACTIVITYEVALUATION where actNo = ?";
		private static final String UPDATE = 
			"UPDATE ACTIVITYEVALUATION set memberNo = ?, actStarRate = ? ,where actNo = ?";

	@Override
	public void insert(ActivityevaluationVO activityevaluationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, activityevaluationVO.getActNo());
			pstmt.setInt(2, activityevaluationVO.getMemberNo());
			pstmt.setInt(3, activityevaluationVO.getActStarRate());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
			
	@Override
	public void update(ActivityevaluationVO activityevaluationVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, activityevaluationVO.getActNo());
			pstmt.setInt(2, activityevaluationVO.getMemberNo());
			pstmt.setInt(3, activityevaluationVO.getActStarRate());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	
	@Override
	public void delete(Integer actNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actNo);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

		
	
	@Override
	public ActivityevaluationVO findByPrimaryKey(Integer actNo) {

		ActivityevaluationVO activityevaluationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				activityevaluationVO = new ActivityevaluationVO();
				activityevaluationVO.setActNo(rs.getInt("actNo"));
				activityevaluationVO.setMemberNo(rs.getInt("MemberNo"));
				activityevaluationVO.setActStarRate(rs.getInt("actStarRate"));
			  }
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return activityevaluationVO;
		}

	@Override
	public List<ActivityevaluationVO> getAll() {
		List<ActivityevaluationVO> list = new ArrayList<ActivityevaluationVO>();
		ActivityevaluationVO activityevaluationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// activityevaluationVO �]�٬� Domain objects
				
				activityevaluationVO = new ActivityevaluationVO();
				activityevaluationVO.setActNo(rs.getInt("actNo"));
				activityevaluationVO.setMemberNo(rs.getInt("memberNo"));
				activityevaluationVO.setActStarRate(rs.getInt("actStarRate"));
				list.add(activityevaluationVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
