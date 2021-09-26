package com.expertpersonalpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mempersonalpage.model.MemPersonalPageVO;

//實作DAO介面 : override抽象方法, 實作"新增修查"方法 

public class ExpertPersonalPageDAO implements ExpertPersonalPageDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/belovedb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增修查sql指令
	public static final String INSERT_SQL = "insert into expertpersonalpage (expertNo, postPhoto, postContent, postTime, numOfLike, postState) values (?, ?, ?, ?, ?, ?)";
	public static final String UpdateBy_postNo_SQL = "update expertpersonalpage set expertNo = ?, postPhoto = ?, postContent = ?, postTime = ?, numOfLike = ?, postState = ? where postNo = ?";
	public static final String DeletBy_postNo_SQL = "delete from expertpersonalpage where postNo = ?";
	public static final String FindBy_postNo_SQL = "select * from expertpersonalpage where postNo = ?";
	public static final String getAll_SQL = "select * from expertpersonalpage";

	// 實作新增: 會員新增一筆個人頁面貼文
	@Override
	public void insert(ExpertPersonalPageVO eppVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setInt(1, eppVO.getExpertNo());
			pstmt.setBytes(2, eppVO.getPostPhoto());
			pstmt.setString(3, eppVO.getPostContent());
			pstmt.setDate(4, eppVO.getPostTime());
			pstmt.setInt(5, eppVO.getNumOfLike());
			pstmt.setInt(6, eppVO.getPostState());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
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

	// 更新貼文內容,圖片及貼文時間
	@Override
	public void update(ExpertPersonalPageVO eppVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UpdateBy_postNo_SQL);

			pstmt.setInt(1, eppVO.getExpertNo());
			pstmt.setBytes(2, eppVO.getPostPhoto());
			pstmt.setString(3, eppVO.getPostContent());
			pstmt.setDate(4, eppVO.getPostTime());
			pstmt.setInt(5, eppVO.getNumOfLike());
			pstmt.setInt(6, eppVO.getPostState());
			pstmt.setInt(7, eppVO.getPostNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
			// Clean up resources
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

	// 刪除一則貼文, 根據PK : postNo
	@Override
	public void delete(Integer postNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DeletBy_postNo_SQL);

			pstmt.setInt(1, postNo);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// se.printStackTrace(System.err);
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

	// 搜尋一筆貼文, 根據PK : postNo
	@Override
	public ExpertPersonalPageVO findByPrimaryKey(Integer postNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ExpertPersonalPageVO eppVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBy_postNo_SQL);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				eppVO = new ExpertPersonalPageVO();
				eppVO.setPostNo(rs.getInt("postNo"));
				eppVO.setExpertNo(rs.getInt("expertNo"));
				eppVO.setPostPhoto(rs.getBytes("postPhoto"));
				eppVO.setPostContent(rs.getString("postContent"));
				eppVO.setPostTime(rs.getDate("postTime"));
				eppVO.setNumOfLike(rs.getInt("numOfLike"));
				eppVO.setPostState(rs.getInt("postState"));
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
		return eppVO;
	}

	// 顯示所有貼文
	@Override
	public List<ExpertPersonalPageVO> getAll() {
		List<ExpertPersonalPageVO> list = new ArrayList<ExpertPersonalPageVO>();
		ExpertPersonalPageVO eppVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				eppVO = new ExpertPersonalPageVO();
				eppVO.setPostNo(rs.getInt("postNo"));
				eppVO.setExpertNo(rs.getInt("expertNo"));
				eppVO.setPostPhoto(rs.getBytes("postPhoto"));
				eppVO.setPostContent(rs.getString("postContent"));
				eppVO.setPostTime(rs.getDate("postTime"));
				eppVO.setNumOfLike(rs.getInt("numOfLike"));
				eppVO.setPostState(rs.getInt("postState"));
				list.add(eppVO); // Store the row in the list
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
