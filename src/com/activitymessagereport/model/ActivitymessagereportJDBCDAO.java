package com.activitymessagereport.model;

import java.util.*;
import java.sql.*;

public class ActivitymessagereportJDBCDAO implements ActivitymessagereportDAO_interface{
	
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei";
			String userid = "David";
			String passwd = "123456";
			
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
						Class.forName(driver);
						con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(INSERT_STMT);

						pstmt.setInt(1, activitymessagereportVO.getActMessageNo());
						pstmt.setInt(2, activitymessagereportVO.getMemberNo());
						pstmt.setString(3, activitymessagereportVO.getActMessageReportReason());
						pstmt.setInt(4, activitymessagereportVO.getActMessageReportState());
						
						
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
				public void update(ActivitymessagereportVO activitymessagereportVO) {
					Connection con = null;
					PreparedStatement pstmt = null;

					try {

						Class.forName(driver);
						con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(UPDATE);

						
					
						pstmt.setInt(1, activitymessagereportVO.getActMessageNo());
						pstmt.setInt(2, activitymessagereportVO.getMemberNo());
						pstmt.setString(3, activitymessagereportVO.getActMessageReportReason());
						pstmt.setInt(4, activitymessagereportVO.getActMessageReportState());
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
				public ActivitymessagereportVO findByPrimaryKey(Integer actMessageNo) {
					ActivitymessagereportVO activitymessagereportVO = null;
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
							activitymessagereportVO = new ActivitymessagereportVO();
							activitymessagereportVO.setActMessageNo(rs.getInt("actMessageNo"));
							activitymessagereportVO.setMemberNo(rs.getInt("memberNo"));
							activitymessagereportVO.setActMessageReportReason(rs.getString("actMessageReportReason"));
							activitymessagereportVO.setActMessageReportState(rs.getInt("actMessageReportState"));
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


						Class.forName(driver);
						con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(GET_ALL_STMT);
						rs = pstmt.executeQuery();

						while (rs.next()) {
							// empVO �]�٬� Domain objects
							
							activitymessagereportVO = new ActivitymessagereportVO();
							activitymessagereportVO.setActMessageNo(rs.getInt("actMessageNo"));
							activitymessagereportVO.setMemberNo(rs.getInt("memberNo"));
							activitymessagereportVO.setActMessageReportReason(rs.getString("actMessageReportReason"));
							activitymessagereportVO.setActMessageReportState(rs.getInt("actMessageReportState"));
							list.add(activitymessagereportVO); // Store the row in the list
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

					ActivitymessagereportJDBCDAO dao = new ActivitymessagereportJDBCDAO();

					// �s�W
					ActivitymessagereportVO activitymessagereportVO1 = new ActivitymessagereportVO();
					activitymessagereportVO1.setActMessageNo(3001);
					activitymessagereportVO1.setMemberNo(1);
					activitymessagereportVO1.setActMessageReportReason("�H������");
					activitymessagereportVO1.setActMessageReportState(1);
					dao.insert(activitymessagereportVO1);

					// �ק�
					ActivitymessagereportVO activitymessagereportVO2 = new ActivitymessagereportVO();
					activitymessagereportVO2.setActMessageNo(3002);
					activitymessagereportVO2.setMemberNo(1);
					activitymessagereportVO2.setActMessageReportReason("���y�t�����r��");
					activitymessagereportVO2.setActMessageReportState(1);
					dao.update(activitymessagereportVO2);

					// �R��
					dao.delete(3002);

					// �d��
					ActivitymessagereportVO ActivitymessagereportVO3 = dao.findByPrimaryKey(3001);
					System.out.print(ActivitymessagereportVO3.getActMessageNo() + ",");
					System.out.print(ActivitymessagereportVO3.getMemberNo() + ",");
					System.out.print(ActivitymessagereportVO3.getActMessageReportReason() + ",");
					System.out.print(ActivitymessagereportVO3.getActMessageReportState() + ",");
					System.out.println("---------------------");

					// �d��
					List<ActivitymessagereportVO> list = dao.getAll();
					for (ActivitymessagereportVO aActivitymessagereport : list) {
						System.out.print(aActivitymessagereport.getActMessageNo() + ",");
						System.out.print(aActivitymessagereport.getMemberNo() + ",");
						System.out.print(aActivitymessagereport.getActMessageReportReason() + ",");
						System.out.print(aActivitymessagereport.getActMessageReportState() + ",");
						System.out.println();
					}
				}	
}
