package com.activitytype.model;

import java.util.*;


import java.sql.*;


	public class ActivitytypeJDBCDAO implements ActivitytypeDAO_interface {

	
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei";
		String userid = "David";
		String passwd = "123456";

		private static final String INSERT_STMT = 
			"INSERT INTO ACTIVITYTYPE (actType,actTypeName) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT actTypeName FROM ACTIVITYTYPE order by actType";
		private static final String GET_ONE_STMT = 
			"SELECT actType,actTypeName FROM ACTIVITYTYPE where actType = ?";
		private static final String DELETE = 
			"DELETE FROM ACTIVITYTYPE where actType = ?";
		private static final String UPDATE = 
			"UPDATE ACTIVITYTYPE set  actTypeName = ? where actType = ?";
		

			@Override
		public void insert(ActivitytypeVO activitytypeVO) {
				Connection con = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(INSERT_STMT);

					
					pstmt.setInt(1, activitytypeVO.getActType());
					pstmt.setString(2, activitytypeVO.getActTypeName());
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
		public void update(ActivitytypeVO activitytypeVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, activitytypeVO.getActType());
				pstmt.setString(2, activitytypeVO.getActTypeName());

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
		public void delete(Integer acttype) {
        	Connection con = null;
    		PreparedStatement pstmt = null;

    		try {

    			Class.forName(driver);
    			con = DriverManager.getConnection(url, userid, passwd);
    			pstmt = con.prepareStatement(DELETE);

    			pstmt.setInt(1, acttype);

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
		public ActivitytypeVO findByPrimaryKey(Integer acttype) {
			ActivitytypeVO activitytypeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, acttype);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					activitytypeVO = new ActivitytypeVO();
					activitytypeVO.setActType(rs.getInt("actType"));
					activitytypeVO.setActTypeName(rs.getString("actTypeName"));
					
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
			return activitytypeVO;
		}

		@Override
		public List<ActivitytypeVO> getAll() {
			List<ActivitytypeVO> list = new ArrayList<ActivitytypeVO>();
			ActivitytypeVO activitytypeVO = null;

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
					
					activitytypeVO = new ActivitytypeVO();
					activitytypeVO.setActType(rs.getInt("actType"));
					activitytypeVO.setActTypeName(rs.getString("actTypeName"));
					list.add(activitytypeVO); // Store the row in the list
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
		
		
	

	
	}


