package com.messagereport.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MessageReportDAO implements MessageReportDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		Context ctx;
		try {
			ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/belovedb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增修查sql指令
	public static final String INSERT_SQL = "insert into messagereport (memberNo, mesNo, mesRepTime, mesRepFor, mesRevState) values (?, ?, ?, ?, ?)";
	public static final String UpdateBy_PKs_SQL = "update messagereport set mesRepTime = ?, mesRepFor = ?, mesRevState = ? where memberNo = ? and mesNo = ?";
	public static final String DeletBy_PKs_SQL = "delete from messagereport where memberNo = ? and mesNo = ?";
	public static final String FindBy_PKs_SQL = "select * from messagereport where memberNo = ? and mesNo = ?";
	public static final String getAll_SQL = "select * from messagereport";

	// 新增一筆論壇文章留言檢舉
	@Override
	public void insert(MessageReportVO messageReportVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setInt(1, messageReportVO.getMemberNo());
			pstmt.setInt(2, messageReportVO.getMesNo());
			pstmt.setDate(3, messageReportVO.getMesRepTime());
			pstmt.setString(4, messageReportVO.getMesRepFor());
			pstmt.setInt(5, messageReportVO.getMesRevState());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurd. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	// 更新論壇文章留言檢舉內容及狀態
	@Override
	public void update(MessageReportVO messageReportVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UpdateBy_PKs_SQL);
			
			pstmt.setDate(1, messageReportVO.getMesRepTime());
			pstmt.setString(2, messageReportVO.getMesRepFor());
			pstmt.setInt(3, messageReportVO.getMesRevState());
			pstmt.setInt(4, messageReportVO.getMemberNo());
			pstmt.setInt(5, messageReportVO.getMesNo());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurd. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	// 刪除一則論壇文章留言檢舉, 根據複合PK : memberNo+mesNo
	@Override
	public void delete(Integer memberNo, Integer mesNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DeletBy_PKs_SQL);

			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, mesNo);
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurd. " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	// 搜尋一則論壇文章留言檢舉, 根據複合PK : memberNo + mesNo
	@Override
	public MessageReportVO findByPrimaryKey(Integer memberNo, Integer mesNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MessageReportVO messageReportVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBy_PKs_SQL);

			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, mesNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				messageReportVO = new MessageReportVO();						
				messageReportVO.setMemberNo(rs.getInt("memberNo"));
				messageReportVO.setMesNo(rs.getInt("mesNo"));	
				messageReportVO.setMesRepTime(rs.getDate("mesRepTime"));
				messageReportVO.setMesRepFor(rs.getString("mesRepFor"));
				messageReportVO.setMesRevState(rs.getInt("mesRevState"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return messageReportVO;
	}

	// 顯示所有論壇文章留言檢舉
	@Override
	public List<MessageReportVO> getAll() {

		List<MessageReportVO> list = new ArrayList<MessageReportVO>();
		MessageReportVO messageReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				messageReportVO = new MessageReportVO();
				messageReportVO.setMemberNo(rs.getInt("memberNo"));
				messageReportVO.setMesNo(rs.getInt("mesNo"));	
				messageReportVO.setMesRepTime(rs.getDate("mesRepTime"));
				messageReportVO.setMesRepFor(rs.getString("mesRepFor"));
				messageReportVO.setMesRevState(rs.getInt("mesRevState"));
				list.add(messageReportVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
