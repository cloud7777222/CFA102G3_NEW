package com.activitymessage.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class ActivitymessageJDBCDAO implements ActivitymessageDAO_interface {
	
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei";
		String userid = "David";
		String passwd = "123456";
		
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
					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(INSERT_STMT);

					pstmt.setInt(1, activitymessageVO.getActMessageNo());
					pstmt.setInt(2, activitymessageVO.getActNo());
					pstmt.setInt(3, activitymessageVO.getMemberNo());
					pstmt.setString(4, activitymessageVO.getActMessageContent());
					pstmt.setDate(5, activitymessageVO.getActMessageTime());
					pstmt.setInt(6, activitymessageVO.getActMessageState());
					
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
			public void update(ActivitymessageVO activitymessageVO) {
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(UPDATE);

					
				
					pstmt.setInt(1, activitymessageVO.getActMessageNo());
					pstmt.setInt(2, activitymessageVO.getActNo());
					pstmt.setInt(3, activitymessageVO.getMemberNo());
					pstmt.setString(4, activitymessageVO.getActMessageContent());
					pstmt.setDate(5, activitymessageVO.getActMessageTime());
					pstmt.setInt(6, activitymessageVO.getActMessageState());
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
			public void delete(Integer actMessageNo) {
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(DELETE);

					pstmt.setInt(1, actMessageNo);

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
			public ActivitymessageVO findByPrimaryKey(Integer actMessageNo) {

				ActivitymessageVO activitymessageVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(GET_ONE_STMT);

					pstmt.setInt(1, actMessageNo);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVo �]�٬� Domain objects
						activitymessageVO = new ActivitymessageVO();
						activitymessageVO.setActMessageNo(rs.getInt("actMessageNo"));
						activitymessageVO.setActNo(rs.getInt("actNo"));
						activitymessageVO.setMemberNo(rs.getInt("memberNo"));
						activitymessageVO.setActMessageContent(rs.getString("actMessageContent"));
						activitymessageVO.setActMessageTime(rs.getDate("actMessageTime"));
						activitymessageVO.setActMessageState(rs.getInt("actMessageState"));
					
						
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


					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVO �]�٬� Domain objects
						
						activitymessageVO = new ActivitymessageVO();
						activitymessageVO.setActMessageNo(rs.getInt("actMessageNo"));
						activitymessageVO.setActNo(rs.getInt("actNo"));
						activitymessageVO.setMemberNo(rs.getInt("memberNo"));
						activitymessageVO.setActMessageContent(rs.getString("actMessageContent"));
						activitymessageVO.setActMessageTime(rs.getDate("actMessageTime"));
						activitymessageVO.setActMessageState(rs.getInt("actMessageState"));
						list.add(activitymessageVO); // Store the row in the list
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

				ActivitymessageJDBCDAO dao = new ActivitymessageJDBCDAO();

				// �s�W
				ActivitymessageVO activitymessageVO1 = new ActivitymessageVO();
				activitymessageVO1.setActMessageNo(3001);
				activitymessageVO1.setActNo(1001);
				activitymessageVO1.setMemberNo(1);;
				activitymessageVO1.setActMessageContent("�ܦn");
				activitymessageVO1.setActMessageTime(Date.valueOf("2021-08-26 00:00:00"));
				activitymessageVO1.setActMessageState(1);
				dao.insert(activitymessageVO1);

				// �ק�
				ActivitymessageVO activitymessageVO2 = new ActivitymessageVO();
				activitymessageVO2.setActMessageNo(3001);
				activitymessageVO2.setActNo(1001);
				activitymessageVO2.setMemberNo(2);;
				activitymessageVO2.setActMessageContent("�g");
				activitymessageVO2.setActMessageTime(Date.valueOf("2021-08-26 00:00:00"));
				activitymessageVO2.setActMessageState(0);
				dao.update(activitymessageVO2);

				// �R��
				dao.delete(3002);

				// �d��
				ActivitymessageVO ActivitymessageVO3 = dao.findByPrimaryKey(3001);
				System.out.print(ActivitymessageVO3.getActMessageNo() + ",");
				System.out.print(ActivitymessageVO3.getActNo() + ",");
				System.out.print(ActivitymessageVO3.getMemberNo() + ",");
				System.out.print(ActivitymessageVO3.getActMessageContent() + ",");
				System.out.print(ActivitymessageVO3.getActMessageTime() + ",");
				System.out.print(ActivitymessageVO3.getActMessageState() + ",");
				System.out.println("---------------------");

				// �d��
				List<ActivitymessageVO> list = dao.getAll();
				for (ActivitymessageVO aActivitymessage : list) {
					System.out.print(aActivitymessage.getActMessageNo() + ",");
					System.out.print(aActivitymessage.getActNo() + ",");
					System.out.print(aActivitymessage.getMemberNo() + ",");
					System.out.print(aActivitymessage.getActMessageContent() + ",");
					System.out.print(aActivitymessage.getActMessageTime() + ",");
					System.out.print(aActivitymessage.getActMessageState() + ",");
					System.out.println();
				}
			}
		}
	

