package com.activityorder.model;


import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
public class ActivityorderDAO implements ActivityorderDAO_interface {
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
			"INSERT INTO ACTIVITYORDER (actNo,MemberNo,actorderpoint,actTotalParticipant,actRegisterOrderDate) VALUES (?,?,300,5,NOW())";
		private static final String GET_ALL_STMT = 
			"SELECT actNo,MemberNo,actOrderPoint,actTotalParticipant,actRegisterOrderDate FROM ACTIVITYORDER order by actNo";
		private static final String GET_ONE_STMT = 
			"SELECT actNo,MemberNo,actOrderPoint,actTotalParticipant,actRegisterOrderDate FROM ACTIVITYORDER where actNo = ?";
		private static final String DELETE = 
			"DELETE FROM ACTIVITYORDER where actNo = ?";
		private static final String UPDATE = 
			"UPDATE ACTIVITYORDER set MemberNo = ?, actOrderPoint = ?, actTotalParticipant = ?, actRegisterOrderDate = ? ,where actNo = ?";

		@Override
		public void insert(ActivityorderVO activityorderVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setInt(1, activityorderVO.getActNo());
				pstmt.setInt(2, activityorderVO.getMemberNo());
				pstmt.executeUpdate();
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
		public void update(ActivityorderVO activityorderVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setInt(1, activityorderVO.getMemberNo());
				pstmt.setInt(2, activityorderVO.getActOrderPoint());
				pstmt.setInt(3, activityorderVO.getActTotalParticipant());
				pstmt.setDate(4, activityorderVO.getActRegisterOrderDate());
				pstmt.setInt(5, activityorderVO.getActNo());
				pstmt.executeUpdate();
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
		public ActivityorderVO findByPrimaryKey(Integer actNo , Integer mrmberno) {
			ActivityorderVO activityorderVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, actNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					activityorderVO = new ActivityorderVO();
					activityorderVO.setActNo(rs.getInt("actNo"));
					activityorderVO.setMemberNo(rs.getInt("MemberNo"));
					activityorderVO.setActOrderPoint(rs.getInt("actOrderPoint"));
					activityorderVO.setActTotalParticipant(rs.getInt("actTotalParticipant"));
					activityorderVO.setActRegisterOrderDate(rs.getDate("actRegisterOrderDate"));
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
			return activityorderVO;
		}

		@Override
		public List<ActivityorderVO> getAll() {
			List<ActivityorderVO> list = new ArrayList<ActivityorderVO>();
			ActivityorderVO activityorderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {


				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					activityorderVO = new ActivityorderVO();
					activityorderVO.setActNo(rs.getInt("actNo"));
					activityorderVO.setMemberNo(rs.getInt("MemberNo"));
					activityorderVO.setActOrderPoint(rs.getInt("actOrderPoint"));
					activityorderVO.setActTotalParticipant(rs.getInt("actTotalParticipant"));
					activityorderVO.setActRegisterOrderDate(rs.getDate("actRegisterOrderDate"));
					list.add(activityorderVO); 
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




		@Override
		public void insertWithactivityorder(ActivityorderVO activityorderVO, List<ActivityorderVO> list) {
			// TODO Auto-generated method stub
			
		}







		
	
}
