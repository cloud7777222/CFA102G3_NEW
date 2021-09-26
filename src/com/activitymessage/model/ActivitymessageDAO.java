package com.activitymessage.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
public class ActivitymessageDAO implements ActivitymessageDAO_interface {
	
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
			"INSERT INTO ACTIVITYMESSAGE (actMessageNo,actNo,memberNo,actMessageContent,actMessageTime,actMessageState) VALUES (?,?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT actMessageNo,actNo,memberNo,actMessageContent,actMessageTime,actMessageState FROM ACTIVITYMESSAGE order by actMessageNo";
		private static final String GET_ONE_STMT = 
			"SELECT actMessageNo,actNo,memberNo,actMessageContent,actMessageTime,actMessageState FROM ACTIVITYMESSAGE where actMessageNo = ?";
		private static final String DELETE = 
			"DELETE FROM ACTIVITYMESSAGE where actMessageNo = ?";
		private static final String UPDATE = 
			"UPDATE ACTIVITYMESSAGE set actNo = ?,memberNo = ?, actMessageContent = ?, actMessageTime = ?, actMessageState = ? ,where actMessageNo = ?";
		@Override
		public void insert(ActivitymessageVO activitymessageVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, activitymessageVO.getActMessageNo());
				pstmt.setInt(2, activitymessageVO.getActNo());
				pstmt.setInt(3, activitymessageVO.getMemberNo());
				pstmt.setString(4, activitymessageVO.getActMessageContent());
				pstmt.setDate(5, activitymessageVO.getActMessageTime());
				pstmt.setInt(6, activitymessageVO.getActMessageState());
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
		public void update(ActivitymessageVO activitymessageVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
            	pstmt.setInt(1, activitymessageVO.getActMessageNo());
				pstmt.setInt(2, activitymessageVO.getActNo());
				pstmt.setInt(3, activitymessageVO.getMemberNo());
				pstmt.setString(4, activitymessageVO.getActMessageContent());
				pstmt.setDate(5, activitymessageVO.getActMessageTime());
				pstmt.setInt(6, activitymessageVO.getActMessageState());
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
		public ActivitymessageVO findByPrimaryKey(Integer actMessageNo) {

			ActivitymessageVO activitymessageVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, actMessageNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
			
					activitymessageVO = new ActivitymessageVO();
					activitymessageVO.setActMessageNo(rs.getInt("actMessageNo"));
					activitymessageVO.setActNo(rs.getInt("actNo"));
					activitymessageVO.setMemberNo(rs.getInt("memberNo"));
					activitymessageVO.setActMessageContent(rs.getString("actMessageContent"));
					activitymessageVO.setActMessageTime(rs.getDate("actMessageTime"));
					activitymessageVO.setActMessageState(rs.getInt("actMessageState"));				}
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
			return activitymessageVO;
		}
		@Override
		public List<ActivitymessageVO> getAll() {
			List<ActivitymessageVO> list = new ArrayList<ActivitymessageVO>();
			ActivitymessageVO activitymessageVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
		        con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					activitymessageVO = new ActivitymessageVO();
					activitymessageVO.setActMessageNo(rs.getInt("actMessageNo"));
					activitymessageVO.setActNo(rs.getInt("actNo"));
					activitymessageVO.setMemberNo(rs.getInt("memberNo"));
					activitymessageVO.setActMessageContent(rs.getString("actMessageContent"));
					activitymessageVO.setActMessageTime(rs.getDate("actMessageTime"));
					activitymessageVO.setActMessageState(rs.getInt("actMessageState"));
					list.add(activitymessageVO); // Store the row in the list
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
