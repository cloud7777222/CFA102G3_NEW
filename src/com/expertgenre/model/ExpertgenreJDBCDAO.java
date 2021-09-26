package com.expertgenre.model;

import java.util.*;
import java.sql.*;

public class ExpertgenreJDBCDAO implements ExpertgenreDAO_interface{
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei";
		String userid = "David";
		String passwd = "123456";
		

		private static final String INSERT_STMT = 
			"INSERT INTO EXPERTGENRE (expertGenreNo,exGenreName) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT exGenreName FROM EXPERTGENRE order by expertGenreNo";
		private static final String GET_ONE_STMT = 
			"SELECT expertGenreNo,exGenreName FROM EXPERTGENRE where expertGenreNo = ?";
		private static final String DELETE = 
			"DELETE FROM EXPERTGENRE where expertGenreNo = ?";
		private static final String UPDATE = 
			"UPDATE EXPERTGENRE set  exGenreName = ? where expertGenreNo = ?";
		
		@Override
		public void insert(ExpertgenreVO expertgenreVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				
				pstmt.setInt(1,  expertgenreVO.getExpertGenreNo());
				pstmt.setString(2,  expertgenreVO.getExGenreName());
				pstmt.executeUpdate();

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
		public void update(ExpertgenreVO expertgenreVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, expertgenreVO.getExpertGenreNo());
				pstmt.setString(2, expertgenreVO.getExGenreName());

				pstmt.executeUpdate();

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
		public void delete(Integer expertGenreNo) {
		   	Connection con = null;
    		PreparedStatement pstmt = null;

    		try {

    			Class.forName(driver);
    			con = DriverManager.getConnection(url, userid, passwd);
    			pstmt = con.prepareStatement(DELETE);

    			pstmt.setInt(1, expertGenreNo);

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
		public ExpertgenreVO findByPrimaryKey(Integer expertGenreNo) {
			ExpertgenreVO expertgenreVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, expertGenreNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					expertgenreVO = new ExpertgenreVO();
					expertgenreVO.setExpertGenreNo(rs.getInt("expertGenreNo"));
					expertgenreVO.setExGenreName(rs.getString("exGenreName"));
					
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
			return expertgenreVO;
		}
		@Override
		public List<ExpertgenreVO> getAll() {
			List<ExpertgenreVO> list = new ArrayList<ExpertgenreVO>();
			ExpertgenreVO expertgenreVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {


				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// expertgenreVO 也稱為 Domain objects
					
					expertgenreVO = new ExpertgenreVO();
					expertgenreVO.setExpertGenreNo(rs.getInt("expertGenreNo"));
					expertgenreVO.setExGenreName(rs.getString("exGenreName"));
					list.add(expertgenreVO); // Store the row in the list
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

			ExpertgenreJDBCDAO dao = new  ExpertgenreJDBCDAO();

			// 新增
			ExpertgenreVO expertgenreVO1 = new ExpertgenreVO();
			expertgenreVO1.setExpertGenreNo(6);
			expertgenreVO1.setExGenreName("心靈專家");
			dao.insert(expertgenreVO1);

			// 修改
			ExpertgenreVO expertgenreVO2 = new ExpertgenreVO();
			expertgenreVO2.setExpertGenreNo(6);
			expertgenreVO2.setExGenreName("心靈專家");
			dao.update(expertgenreVO2);

			// 刪除
			dao.delete(5);

			// 查詢
			ExpertgenreVO expertgenreVO3 = dao.findByPrimaryKey(1);
			System.out.print(expertgenreVO3.getExpertGenreNo() + ",");
			System.out.print(expertgenreVO3.getExGenreName() + ",");
			System.out.println("---------------------");

			// 查詢
			List<ExpertgenreVO> list = dao.getAll();
			for (ExpertgenreVO expertgenre : list) {
				System.out.print(expertgenre.getExpertGenreNo() + ",");
				System.out.print(expertgenre.getExGenreName() + ",");
				System.out.println();
			}
		}
}
