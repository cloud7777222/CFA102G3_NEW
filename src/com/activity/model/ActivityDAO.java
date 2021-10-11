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
			"INSERT INTO ACTIVITY (actNo,actType,actName,actDate,actLocation,actDirection,maxParticipant,minParticipant,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,totalStar,totalEvaluator) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT actNo,actType,actName,actDate,actLocation,actDirection,maxParticipant,minParticipant,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,actPicture,totalStar,totalEvaluator FROM ACTIVITY order by actNo";
		private static final String GET_ONE_STMT = 
			"SELECT actNo,actType,actName,actDate,actLocation,actDirection,maxParticipant,minParticipant,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,actPicture,totalStar,totalEvaluator FROM ACTIVITY where actNo = ?";
		private static final String GET_PICTURE="SELECT actPicture FROM ACTIVITY WHERE actName=?";
		private static final String GET_ACTIVITY_TYPEA =
				"SELECT actNo,actType,actTypeName,actDate,actLocation FROM ACTIVITY where actType = ?";
		private static final String GET_ACTIVITY_TYPE =
				"SELECT actNo,actType,actTypeName,actDate,actLocation,actDirection FROM ACTIVITY where actType = ?";
		private static final String DELETE = 
			"DELETE FROM ACTIVITY where actNo = ?";
		private static final String UPDATE_STMT = 
			"UPDATE ACTIVITY set  actType = ?, actName = ?, actDate = ?, actLocation = ?, actDirection = ? ,maxParticipant= ?,minParticipant= ?,actState= ? ,actHoldState= ? ,actRegisterStartDate= ? ,actRegisterDeadLine= ? ,actPicture= ? ,totalStar= ? ,totalEvaluator= ? where actNo = ?";
		private static final String UPDATE_PHOTO="UPDATE ACTIVITY set actPicture=? where actName=?";
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
				pstmt.setInt(7, activityVO.getMaxParticipant());
				pstmt.setInt(8, activityVO.getMinParticipant());
				pstmt.setInt(9, activityVO.getActState());
				pstmt.setInt(10, activityVO.getActHoldState());
				pstmt.setDate(11, activityVO.getActRegisterStartDate());
				pstmt.setDate(12, activityVO.getActRegisterDeadLine());
					
				pstmt.setInt(13, activityVO.getTotalStar());
				pstmt.setInt(14, activityVO.getTotalEvaluator());
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
				pstmt.setInt(7, activityVO.getMaxParticipant());
				pstmt.setInt(8, activityVO.getMinParticipant());
				pstmt.setInt(9, activityVO.getActState());
				pstmt.setInt(10, activityVO.getActHoldState());
				pstmt.setDate(11, activityVO.getActRegisterStartDate());
				pstmt.setDate(12, activityVO.getActRegisterDeadLine());
				pstmt.setBytes(13, activityVO.getActPicture());
				pstmt.setInt(14, activityVO.getTotalStar());
				pstmt.setInt(15, activityVO.getTotalEvaluator());
				
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
					activityVO.setMaxParticipant(rs.getInt("maxParticipant"));
					activityVO.setMinParticipant(rs.getInt("minParticipant"));
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
					
					activityVO = new ActivityVO();
					activityVO.setActNo(rs.getInt("actNo"));
					activityVO.setActType(rs.getInt("actType"));
					activityVO.setActName(rs.getString("actName"));
					activityVO.setActDate(rs.getDate("actDate"));
					activityVO.setActLocation(rs.getString("actLocation"));
					activityVO.setActDirection(rs.getString("actDirection"));
					activityVO.setMaxParticipant(rs.getInt("maxParticipant"));
					activityVO.setMinParticipant(rs.getInt("minParticipant"));
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

		@Override
		public byte[] getPhoto(String actName) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			byte[] actPicture=null;
			try {
				con=ds.getConnection();
				pstmt=con.prepareStatement(GET_PICTURE);
				pstmt.setString(1, actName);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					actPicture=rs.getBytes("actPicture");
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(con!=null) {
					try {
						con.close();
					}
					catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			return actPicture;
		}

		@Override
		public void updatePhoto(String actName, byte[] actPicture) {
			Connection con=null;
			PreparedStatement pstmt=null;
			
			try {
				con=ds.getConnection();
				pstmt=con.prepareStatement(UPDATE_PHOTO);
				
				pstmt.setBytes(1, actPicture);
				pstmt.setString(2, actName);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(con!=null) {
					try {
						con.close();
					}
					catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}

		@Override
		public List<ActivityVO> searchByTypeAll(Integer actType) {
			List<ActivityVO> list = new ArrayList<ActivityVO>();
			ActivityVO activityVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ACTIVITY_TYPE);
				
				pstmt.setInt(1, actType);
				
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					activityVO.setActNo(rs.getInt("actNo"));
					activityVO.setActType(rs.getInt("actType"));
					activityVO.setActName(rs.getString("actName"));
					activityVO.setActDate(rs.getDate("actDate"));
					activityVO.setActLocation(rs.getString("actLocation"));
					activityVO.setActDirection(rs.getString("(actDirection"));
					
					list.add(activityVO); 
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

		@Override
		public List<ActivityVO> searchByTypeA(Integer actType) {
			List<ActivityVO> list = new ArrayList<ActivityVO>();
			ActivityVO activityVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ACTIVITY_TYPEA);
				
				pstmt.setInt(1, actType);
				
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					activityVO = new ActivityVO();
					activityVO.setActNo(rs.getInt("actNo"));
					activityVO.setActType(rs.getInt("actType"));
					activityVO.setActName(rs.getString("actName"));
					activityVO.setActDate(rs.getDate("actDate"));
					activityVO.setActLocation(rs.getString("actLocation"));
				
			
					list.add(activityVO); 
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

		@Override
		public List<ActivityVO> findAllByKeyword(String keyword) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		}


