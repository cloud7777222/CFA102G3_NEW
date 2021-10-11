package com.activity.model;

import java.util.*;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;

public class ActivityJDBCDAO implements ActivityDAO_interface {

	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/beloveDB?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ACTIVITY (actNo,actType,actName,actDate,actLocation,actDirection,MaxParticipant,MinParticipant,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,actPicture,TotalStar,TotalEvaluator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT actNo,actType,actName,actDate,actLocation,actDirection,MaxParticipant,MinParticipant,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,actPicture,TotalStar,TotalEvaluator FROM ACTIVITY order by actNo";
	private static final String GET_ONE_STMT = 
		"SELECT actNo,actType,actName,actDate,actLocation,actDirection,MaxParticipant,MinParticipant,actState,actHoldState,actRegisterStartDate,actRegisterDeadLine,actPicture,TotalStar,TotalEvaluator FROM ACTIVITY where actNo = ?";
	private static final String GET_PICTURE="SELECT actPicture FROM ACTIVITY WHERE actName=?";
	private static final String DELETE = 
		"DELETE FROM ACTIVITY where actNo = ?";
	private static final String UPDATE_STMT = 
		"UPDATE ACTIVITY set  actType = ?, actName = ?, actDate = ?, actLocation = ?, actDirection = ? ,MaxParticipant= ? ,MinParticipant= ? ,actState= ? ,actHoldState= ? ,actRegisterStartDate= ? ,actRegisterDeadLine= ? ,actPicture= ? ,TotalStar= ? ,TotalEvaluator= ? where actNo = ?";
	private static final String picture = "INSERT INTO ACTIVITY(actNo, actPicture) VALUES (?, ?)";

	@Override
	public void insert(ActivityVO activityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			pstmt.setBytes(13, activityVO.getActPicture());
			pstmt.setInt(14, activityVO.getTotalStar());
			pstmt.setInt(15, activityVO.getTotalEvaluator());
			
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
	public void update(ActivityVO activityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public ActivityVO findByPrimaryKey(Integer actNo) {

		ActivityVO activityVO = null;
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


			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		ActivityJDBCDAO dao = new ActivityJDBCDAO();

	
		ActivityVO activityVO1 = new ActivityVO();
		activityVO1.setActNo(1028);
		activityVO1.setActType(10);
		activityVO1.setActName("KTV");
		activityVO1.setActDate(java.sql.Date.valueOf("2021-08-30"));;
		activityVO1.setActLocation("中壢");
		activityVO1.setActDirection("唱歌");
		activityVO1.setMaxParticipant(new Integer(16));
		activityVO1.setMinParticipant(new Integer(6));
		activityVO1.setActState(1);
		activityVO1.setActHoldState(1);
		activityVO1.setActRegisterStartDate(Date.valueOf("2021-08-25 00:00:00"));;
		activityVO1.setActRegisterDeadLine(Date.valueOf("2021-08-27 00:00:00"));;
		activityVO1.setActPicture(null);
		byte[] pic = getPictureByteArray("items/FC_Barcelona.png");
		activityVO1.setTotalStar(new Integer(10));
		activityVO1.setTotalEvaluator(new Integer(16));
		
		dao.insert(activityVO1);

	
		ActivityVO activityVO2 = new ActivityVO();
		activityVO2.setActNo(1001);
		activityVO2.setActType(20);
		activityVO2.setActName("ktv");
		activityVO2.setActDate(java.sql.Date.valueOf("2021-08-31"));
		activityVO2.setActLocation("家");
		activityVO2.setActDirection("唱歌");
		activityVO2.setMaxParticipant(new Integer(18));
		activityVO2.setMinParticipant(new Integer(8));
		activityVO2.setActState(1);
		activityVO2.setActHoldState(1);
		activityVO2.setActRegisterStartDate(Date.valueOf("2021-08-26 00:00:00"));;
		activityVO2.setActRegisterDeadLine(Date.valueOf("2021-08-28 00:00:00"));;
		activityVO2.setActPicture(null);
		activityVO2.setTotalStar(new Integer(12));
		activityVO2.setTotalEvaluator(new Integer(14));
		
		dao.update(activityVO2);
	
		dao.delete(1012);

		
		ActivityVO ActivityVO3 = dao.findByPrimaryKey(1001);
		System.out.print(ActivityVO3.getActNo() + ",");
		System.out.println(ActivityVO3.getActType()+ ",");
		System.out.print(ActivityVO3.getActName() + ",");
		System.out.print(ActivityVO3.getActDate() + ",");
		System.out.print(ActivityVO3.getActLocation() + ",");
		System.out.print(ActivityVO3.getActDirection() + ",");
		System.out.print(ActivityVO3.getMaxParticipant() + ",");
		System.out.println(ActivityVO3.getMinParticipant()+ ",");
		System.out.println(ActivityVO3.getActState()+ ",");
		System.out.println(ActivityVO3.getActHoldState()+ ",");
		System.out.println(ActivityVO3.getActRegisterStartDate()+ ",");
		System.out.println(ActivityVO3.getActRegisterDeadLine()+ ",");
		System.out.println(ActivityVO3.getActPicture()+ ",");
		System.out.println(ActivityVO3.getTotalStar()+ ",");
		System.out.println(ActivityVO3.getTotalEvaluator()+ ",");
		
		System.out.println("---------------------");

		// �d��
		List<ActivityVO> list = dao.getAll();
		for (ActivityVO aActivity : list) {
			System.out.print(aActivity.getActNo() + ",");
			System.out.println(aActivity.getActType()+ ",");
			System.out.print(aActivity.getActName() + ",");
			System.out.print(aActivity.getActDate() + ",");
			System.out.print(aActivity.getActLocation() + ",");
			System.out.print(aActivity.getActDirection() + ",");
			System.out.print(aActivity.getMaxParticipant() + ",");
			System.out.println(aActivity.getMinParticipant()+ ",");
			System.out.println(aActivity.getActState()+ ",");
			System.out.println(aActivity.getActHoldState()+ ",");
			System.out.println(aActivity.getActRegisterStartDate()+ ",");
			System.out.println(aActivity.getActRegisterDeadLine()+ ",");
			System.out.println(aActivity.getActPicture()+ ",");
			System.out.println(aActivity.getTotalStar()+ ",");
			System.out.println(aActivity.getTotalEvaluator()+ ",");
		
			System.out.println();
		}
	}

	private static byte[] getPictureByteArray(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private static InputStream getPictureStream(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getPhoto(String actName) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		byte[] actPicture=null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivityVO> searchByTypeAll(Integer actType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityVO> searchByTypeA(Integer actType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityVO> findAllByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}