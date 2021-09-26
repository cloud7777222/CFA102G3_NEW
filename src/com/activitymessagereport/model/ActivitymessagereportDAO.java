package com.activitymessagereport.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
public class ActivitymessagereportDAO implements ActivitymessagereportDAO_interface {
	
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
			"INSERT INTO ACTIVITYMESSAGEREPORT (actMessageNo,memberNo,actMessageReportReason,actMessageReportState) VALUES (?,?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT actMessageNo,memberNo,actMessageReportReason,actMessageReportState FROM ACTIVITYMESSAGEREPORT order by actMessageNo";
		private static final String GET_ONE_STMT = 
			"SELECT actMessageNo,memberNo,actMessageReportReason,actMessageReportState FROM ACTIVITYMESSAGEREPORT where actMessageNo = ?";
		private static final String DELETE = 
			"DELETE FROM ACTIVITYMESSAGEREPORT where actMessageNo = ?";
		private static final String UPDATE = 
			"UPDATE ACTIVITYMESSAGEREPORT set actMessageNo = ?,memberNo = ?, actMessageReportReason = ?, actMessageReportState = ?,where actMessageNo = ?";
		@Override
		public void insert(ActivitymessagereportVO activitymessagereportVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, activitymessagereportVO.getActMessageNo());
				pstmt.setInt(2, activitymessagereportVO.getMemberNo());
				pstmt.setString(3, activitymessagereportVO.getActMessageReportReason());
				pstmt.setInt(4, activitymessagereportVO.getActMessageReportState());
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
		public void update(ActivitymessagereportVO activitymessagereportVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
      			pstmt.setInt(1, activitymessagereportVO.getActMessageNo());
				pstmt.setInt(2, activitymessagereportVO.getMemberNo());
				pstmt.setString(3, activitymessagereportVO.getActMessageReportReason());
				pstmt.setInt(4, activitymessagereportVO.getActMessageReportState());
				pstmt.executeUpdate();
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
		public void delete(Integer actMessageNo) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				pstmt.setInt(1, actMessageNo);
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
		public ActivitymessagereportVO findByPrimaryKey(Integer actMessageNo) {
			ActivitymessagereportVO activitymessagereportVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setInt(1, actMessageNo);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					activitymessagereportVO = new ActivitymessagereportVO();
					activitymessagereportVO.setActMessageNo(rs.getInt("actMessageNo"));
					activitymessagereportVO.setMemberNo(rs.getInt("memberNo"));
					activitymessagereportVO.setActMessageReportReason(rs.getString("actMessageReportReason"));
					activitymessagereportVO.setActMessageReportState(rs.getInt("actMessageReportState"));
				}
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
			return activitymessagereportVO;
			
		}
		@Override
		public List<ActivitymessagereportVO> getAll() {
			List<ActivitymessagereportVO> list = new ArrayList<ActivitymessagereportVO>();
			ActivitymessagereportVO activitymessagereportVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					activitymessagereportVO = new ActivitymessagereportVO();
					activitymessagereportVO.setActMessageNo(rs.getInt("actMessageNo"));
					activitymessagereportVO.setMemberNo(rs.getInt("memberNo"));
					activitymessagereportVO.setActMessageReportReason(rs.getString("actMessageReportReason"));
					activitymessagereportVO.setActMessageReportState(rs.getInt("actMessageReportState"));
					list.add(activitymessagereportVO); // Store the row in the list
				}
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
