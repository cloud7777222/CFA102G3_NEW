package com.mempersonalpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

//實作DAO介面 : override抽象方法, 實作"新增修查"方法 

public class MemPersonalPageDAO implements MemPersonalPageDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增修查sql指令
	public static final String INSERT_SQL = "insert into mempersonalpage (memberNo, postPhoto, postContent, postTime, numOfLike, postState) values (?, ?, ?, ?, ?, ?)";
	public static final String UpdateBy_postNo_SQL = "update mempersonalpage set memberNo = ?, postPhoto = ?, postContent = ?, postTime = ?, numOfLike = ?, postState = ? where postNo = ?";
	public static final String DeletBy_postNo_SQL = "delete from mempersonalpage where postNo = ?";
	public static final String FindBy_postNo_SQL = "select * from mempersonalpage where postNo = ?";
	public static final String getAll_SQL = "select * from mempersonalpage order by postNo desc";

	// 實作新增: 會員新增一筆個人頁面貼文
	@Override
	public void insert(MemPersonalPageVO mppVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setInt(1, mppVO.getMemberNo());
			pstmt.setBytes(2, mppVO.getPostPhoto());
			pstmt.setString(3, mppVO.getPostContent());
			pstmt.setDate(4, mppVO.getPostTime());
			pstmt.setInt(5, mppVO.getNumOfLike());
			pstmt.setInt(6, mppVO.getPostState());

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

	// 更新貼文內容及圖片
	@Override
	public void update(MemPersonalPageVO mppVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UpdateBy_postNo_SQL);

			pstmt.setInt(1, mppVO.getMemberNo());
			pstmt.setBytes(2, mppVO.getPostPhoto());
			pstmt.setString(3, mppVO.getPostContent());
			pstmt.setDate(4, mppVO.getPostTime());
			pstmt.setInt(5, mppVO.getNumOfLike());
			pstmt.setInt(6, mppVO.getPostState());
			pstmt.setInt(7, mppVO.getPostNo());

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
	public MemPersonalPageVO findByPrimaryKey(Integer postNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MemPersonalPageVO mppVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBy_postNo_SQL);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mppVO = new MemPersonalPageVO();
				mppVO.setPostNo(rs.getInt("postNo"));
				mppVO.setMemberNo(rs.getInt("memberNo"));
				mppVO.setPostPhoto(rs.getBytes("postPhoto"));
				mppVO.setPostContent(rs.getString("postContent"));
				mppVO.setPostTime(rs.getDate("postTime"));
				mppVO.setNumOfLike(rs.getInt("numOfLike"));
				mppVO.setPostState(rs.getInt("postState"));
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
		return mppVO;
	}

	// 顯示所有貼文
	@Override
	public List<MemPersonalPageVO> getAll() {
		List<MemPersonalPageVO> list = new ArrayList<MemPersonalPageVO>();
		MemPersonalPageVO mppVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mppVO = new MemPersonalPageVO();
				mppVO.setPostNo(rs.getInt("postNo"));
				mppVO.setMemberNo(rs.getInt("memberNo"));
				mppVO.setPostPhoto(rs.getBytes("postPhoto"));
				mppVO.setPostContent(rs.getString("postContent"));
				mppVO.setPostTime(rs.getDate("postTime"));
				mppVO.setNumOfLike(rs.getInt("numOfLike"));
				mppVO.setPostState(rs.getInt("postState"));
				list.add(mppVO); // Store the row in the list
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
