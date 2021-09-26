package com.activity.model;

	import java.util.*;

	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;

	import java.sql.*;

	public class ActivityDAO implements ActivityDAO_interface {

		
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
			"INSERT INTO ACTIVITY (actNo,actType,actName,actDate,actLocation,actDirection,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT actNo,actType,actName,actDate,actLocation,actDirection,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,actPicture,totalStar,totalEvaluator FROM ACTIVITY order by actNo";
		private static final String GET_ONE_STMT = 
			"SELECT actNo,actType,actName,actDate,actLocation,actDirection,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,actPicture,totalStar,totalEvaluator FROM ACTIVITY where actNo = ?";
		private static final String DELETE = 
			"DELETE FROM ACTIVITY where actNo = ?";
		private static final String UPDATE_STMT = 
			"UPDATE ACTIVITY set  actType = ?, actName = ?, actDate = ?, actLocation = ?, actDirection = ? ,actState= ? ,actHoldState= ? ,actRegisterStartDate= ? ,actRegisterDeadLine= ? ,actPicture= ? ,totalStar= ? ,totalEvaluator= ? where actNo = ?";

		@Override
		public void insert(ActivityVO activityVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, activityVO.getActNo());
				pstmt.setInt(2, activityVO.getActType());
				pstmt.setString(3, activityVO.getActName());
				pstmt.setDate(4, activityVO.getActDate());
				pstmt.setString(5, activityVO.getActLocation());
				pstmt.setString(6, activityVO.getActDirection());
				pstmt.setInt(7, activityVO.getActState());
				pstmt.setInt(8, activityVO.getActHoldState());
				pstmt.setDate(9, activityVO.getActRegisterStartDate());
				pstmt.setDate(10, activityVO.getActRegisterDeadLine());
				
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
		public void update(ActivityVO activityVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);

				
				pstmt.setInt(1, activityVO.getActNo());
				pstmt.setInt(2, activityVO.getActType());
				pstmt.setString(3, activityVO.getActName());
				pstmt.setDate(4, activityVO.getActDate());
				pstmt.setString(5, activityVO.getActLocation());
				pstmt.setString(6, activityVO.getActDirection());
				pstmt.setInt(7, activityVO.getActState());
				pstmt.setInt(8, activityVO.getActHoldState());
				pstmt.setDate(9, activityVO.getActRegisterStartDate());
				pstmt.setDate(10, activityVO.getActRegisterDeadLine());
				pstmt.setBytes(11, activityVO.getActPicture());
				pstmt.setInt(12, activityVO.getTotalStar());
				pstmt.setInt(13, activityVO.getTotalEvaluator());
				
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
		public ActivityVO findByPrimaryKey(Integer actNo) {

			ActivityVO activityVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, actNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					activityVO = new ActivityVO();
					activityVO.setActNo(rs.getInt("actNo"));
					activityVO.setActType(rs.getInt("actType"));
					activityVO.setActName(rs.getString("actName"));
					activityVO.setActDate(rs.getDate("actDate"));
					activityVO.setActLocation(rs.getString("actLocation"));
					activityVO.setActDirection(rs.getString("actDirection"));
					activityVO.setActState(rs.getInt("actState"));
					activityVO.setActHoldState(rs.getInt("actHoldState"));
					activityVO.setActRegisterStartDate(rs.getDate("actRegisterStartDate"));
					activityVO.setActRegisterDeadLine(rs.getDate("actRegisterDeadLine"));
					activityVO.setActPicture(rs.getBytes("actPicture"));
					activityVO.setTotalStar(rs.getInt("totalStar"));
					activityVO.setTotalEvaluator(rs.getInt("totalEvaluator"));
					
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
			return activityVO;
		}

		@Override
		public List<ActivityVO> getAll() {
			List<ActivityVO> list = new ArrayList<ActivityVO>();
			ActivityVO activityVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					
					activityVO = new ActivityVO();
					activityVO.setActNo(rs.getInt("actNo"));
					activityVO.setActType(rs.getInt("actType"));
					activityVO.setActName(rs.getString("actName"));
					activityVO.setActDate(rs.getDate("actDate"));
					activityVO.setActLocation(rs.getString("actLocation"));
					activityVO.setActDirection(rs.getString("actDirection"));
					activityVO.setActState(rs.getInt("actState"));
					activityVO.setActHoldState(rs.getInt("actHoldState"));
					activityVO.setActRegisterStartDate(rs.getDate("actRegisterStartDate"));
					activityVO.setActRegisterDeadLine(rs.getDate("actRegisterDeadLine"));
					activityVO.setActPicture(rs.getBytes("actPicture"));
					activityVO.setTotalStar(rs.getInt("totalStar"));
					activityVO.setTotalEvaluator(rs.getInt("totalEvaluator"));	
					list.add(activityVO); // Store the row in the list
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


