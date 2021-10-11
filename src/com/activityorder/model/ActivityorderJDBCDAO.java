package com.activityorder.model;

import java.util.*;


import java.sql.*;



public class ActivityorderJDBCDAO implements ActivityorderDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ACTIVITYORDER (actNo,MemberNo,actOrderPoint,actTotalParticipant,actRegisterOrderDate) VALUES (?,?, ?, ?, ?)";
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
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setInt(1, activityorderVO.getActNo());
				pstmt.setInt(2, activityorderVO.getMemberNo());
				pstmt.setInt(3, activityorderVO.getActOrderPoint());
				pstmt.setInt(4, activityorderVO.getActTotalParticipant());
				pstmt.setDate(5, activityorderVO.getActRegisterOrderDate());
				// Handle any SQL errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setInt(1, activityorderVO.getMemberNo());
				pstmt.setInt(2, activityorderVO.getActOrderPoint());
				pstmt.setInt(3, activityorderVO.getActTotalParticipant());
				pstmt.setDate(4, activityorderVO.getActRegisterOrderDate());
				pstmt.setInt(5, activityorderVO.getActNo());
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, actNo);

				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public ActivityorderVO findByPrimaryKey(Integer actNo,Integer memberno) {
			ActivityorderVO activityorderVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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


				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public static void main(String[] args) {

			ActivityorderJDBCDAO dao = new ActivityorderJDBCDAO();

			// �s�W
			ActivityorderVO activityorderVO1 = new ActivityorderVO();
			activityorderVO1.setActNo(32);
			activityorderVO1.setMemberNo(1);
			activityorderVO1.setActOrderPoint(new Integer(300));
			activityorderVO1.setActTotalParticipant(new Integer(6));
			activityorderVO1.setActRegisterOrderDate(java.sql.Date.valueOf("2021-08-30"));
			dao.insert(activityorderVO1);
			
			// �ק�
			ActivityorderVO activityorderVO2 = new ActivityorderVO();
			activityorderVO2.setActNo(32);
			activityorderVO2.setMemberNo(1);
			activityorderVO2.setActOrderPoint(new Integer(300));
			activityorderVO2.setActTotalParticipant(new Integer(8));
			activityorderVO2.setActRegisterOrderDate(java.sql.Date.valueOf("2021-09-05"));
			dao.update(activityorderVO2);

			
			dao.delete(9);
			
			ActivityorderVO ActivityorderVO3 = dao.findByPrimaryKey(1,1);
			System.out.print(ActivityorderVO3.getActNo() + ",");
			System.out.print(ActivityorderVO3.getMemberNo() + ",");
			System.out.print(ActivityorderVO3.getActOrderPoint() + ",");
			System.out.print(ActivityorderVO3.getActTotalParticipant() + ",");
			System.out.print(ActivityorderVO3.getActRegisterOrderDate() + ",");
			System.out.println("---------------------");

			
			List<ActivityorderVO> list = dao.getAll();
			for (ActivityorderVO aActivityorder : list) {
				System.out.print(aActivityorder.getActNo() + ",");
				System.out.print(aActivityorder.getMemberNo() + ",");
				System.out.print(aActivityorder.getActOrderPoint() + ",");
				System.out.print(aActivityorder.getActTotalParticipant() + ",");
				System.out.print(aActivityorder.getActRegisterOrderDate() + ",");
				System.out.println();
			}
		}




		@Override
		public void insertWithactivityorder(ActivityorderVO activityorderVO, List<ActivityorderVO> list) {
			// TODO Auto-generated method stub
			
		}




		
	}