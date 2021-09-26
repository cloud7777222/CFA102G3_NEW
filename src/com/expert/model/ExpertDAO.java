package com.expert.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ExpertDAO  implements ExpertDAO_interface {

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
		"INSERT INTO EXPERT (expertGenreNo,exAcount,exPassword,exPhoto,exName,exGender,exPhone,exEmail,exAdress,exIntroduce,checkData,checkState,exPoint,exBlackList,exPopSum,exPointSum) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT expertNo,expertGenreNo,exAcount,exPassword,exPhoto,exName,exGender,exPhone,exEmail,exAdress,exIntroduce,checkData,checkState,exPoint,exBlackList,exPopSum,exPointSum FROM EXPERT order by expertNo";
	private static final String GET_ONE_STMT = 
		"SELECT expertNo,expertGenreNo,exAcount,exPassword,exPhoto,exName,exGender,exPhone,exEmail,exAdress,exIntroduce,checkData,checkState,exPoint,exBlackList,exPopSum,exPointSum FROM EXPERT where expertNo = ?";
	private static final String DELETE = 
		"DELETE FROM EXPERT where expertNo = ?";
	private static final String UPDATE_STMT = 
		"UPDATE EXPERT set  expertGenreNo = ?, exAcount = ?, exPassword = ?, exPhoto= ?, exName = ? ,exGender= ? ,exPhone= ? ,actState= ? ,exEmail= ? ,exAdresse= ? ,exIntroduce= ? ,checkData= ? ,exPoint= ? ,exBlackList= ?,exPopSum= ? ,exPointSum= ? where expertNo = ?";

	@Override
	public void insert(ExpertVO expertVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, expertVO.getExpertGenreNo());
			pstmt.setString(2, expertVO.getExAcount());
			pstmt.setString(3, expertVO.getExPassword());
			pstmt.setBytes(4, expertVO.getExPhoto());
			pstmt.setString(5, expertVO.getExName());
			pstmt.setInt(6, expertVO.getExGender());
			pstmt.setString(7, expertVO.getExPhone());
			pstmt.setString(8, expertVO.getExEmail());
			pstmt.setString(9, expertVO.getExAdress());
			pstmt.setString(10, expertVO.getExIntroduce());
			pstmt.setBytes(11, expertVO.getCheckData());
			pstmt.setInt(12, expertVO.getCheckState());
			pstmt.setInt(13, expertVO.getExPoint());
			pstmt.setInt(14, expertVO.getExBlackList());
			pstmt.setInt(15, expertVO.getExPopSum());
			pstmt.setInt(16, expertVO.getExPointSum());
			
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
	public void update(ExpertVO expertVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setInt(1, expertVO.getExpertGenreNo());
			pstmt.setString(2, expertVO.getExAcount());
			pstmt.setString(3, expertVO.getExPassword());
			pstmt.setBytes(4, expertVO.getExPhoto());
			pstmt.setString(5, expertVO.getExName());
			pstmt.setInt(6, expertVO.getExGender());
			pstmt.setString(7, expertVO.getExPhone());
			pstmt.setString(8, expertVO.getExEmail());
			pstmt.setString(9, expertVO.getExAdress());
			pstmt.setString(10, expertVO.getExIntroduce());
			pstmt.setBytes(11, expertVO.getCheckData());
			pstmt.setInt(12, expertVO.getCheckState());
			pstmt.setInt(13, expertVO.getExPoint());
			pstmt.setInt(14, expertVO.getExBlackList());
			pstmt.setInt(15, expertVO.getExPopSum());
			pstmt.setInt(16, expertVO.getExPointSum());
			pstmt.setInt(17, expertVO.getExpertNo());
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
	public void delete(Integer expertNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, expertNo);

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
	public ExpertVO findByPrimaryKey(Integer expertNo) {

		ExpertVO expertVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, expertNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				expertVO = new ExpertVO();
				expertVO.setExpertNo(rs.getInt("expertNo"));
				expertVO.setExpertGenreNo(rs.getInt("expertGenreNo"));
				expertVO.setExAcount(rs.getString("exAcount"));
				expertVO.setExPassword(rs.getString("exPassword"));
				expertVO.setExPhoto(rs.getBytes("exPhoto"));
				expertVO.setExName(rs.getString("exName"));
				expertVO.setExGender(rs.getInt("exGender"));
				expertVO.setExPhone(rs.getString("exPhone"));
				expertVO.setExEmail(rs.getString("exEmail"));
				expertVO.setExAdress(rs.getString("exAdress"));
				expertVO.setExIntroduce(rs.getString("exIntroduce"));
				expertVO.setCheckData(rs.getBytes("checkData"));
				expertVO.setCheckState(rs.getInt("checkState"));
				expertVO.setExPoint(rs.getInt("exPoint"));
				expertVO.setExBlackList(rs.getInt("exBlackList"));
				expertVO.setExPopSum(rs.getInt("exPopSum"));
				expertVO.setExPointSum(rs.getInt("exPointSum"));
				
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
		return expertVO;
	}

	@Override
	public List<ExpertVO> getAll() {
		List<ExpertVO> list = new ArrayList<ExpertVO>();
		ExpertVO expertVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				

				expertVO = new ExpertVO();
				expertVO.setExpertNo(rs.getInt("expertNo"));
				expertVO.setExpertGenreNo(rs.getInt("expertGenreNo"));
				expertVO.setExAcount(rs.getString("exAcount"));
				expertVO.setExPassword(rs.getString("exPassword"));
				expertVO.setExPhoto(rs.getBytes("exPhoto"));
				expertVO.setExName(rs.getString("exName"));
				expertVO.setExGender(rs.getInt("exGender"));
				expertVO.setExPhone(rs.getString("exPhone"));
				expertVO.setExEmail(rs.getString("exEmail"));
				expertVO.setExAdress(rs.getString("exAdress"));
				expertVO.setExIntroduce(rs.getString("exIntroduce"));
				expertVO.setCheckData(rs.getBytes("checkData"));
				expertVO.setCheckState(rs.getInt("checkState"));
				expertVO.setExPoint(rs.getInt("exPoint"));
				expertVO.setExBlackList(rs.getInt("exBlackList"));
				expertVO.setExPopSum(rs.getInt("exPopSum"));
				expertVO.setExPointSum(rs.getInt("exPointSum"));
				list.add(expertVO); // Store the row in the list
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
