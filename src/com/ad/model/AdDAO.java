package com.ad.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdDAO implements AdDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
			"INSERT INTO advertise (adtitle, ad, adpic1, adpic2, adpic3, adstate, posttime, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adno, adtitle, ad, adpic1, adpic2, adpic3, adstate, posttime, deadline FROM advertise order by adno desc";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM advertise where adno = ?";
	private static final String DELETE = 
			"DELETE FROM advertise WHERE adno = ?";
	private static final String UPDATE = 
			"UPDATE advertise SET adtitle=?, ad=?, adpic1=?, adpic2=?, adpic3=?, adstate=?, posttime=?, deadline=? WHERE adno = ?";
	private static final String GET_ALL_STMT_BY_KEYWORD = 
			"SELECT * FROM advertise WHERE adno LIKE ? OR ad LIKE ? OR adtitle LIKE ? order by adstate DESC, adno DESC";

	@Override
	public void insert(AdVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVO.getAdTitle());
			pstmt.setString(2, adVO.getAd());
			pstmt.setString(3, adVO.getAdPic1());
			pstmt.setString(4, adVO.getAdPic2());
			pstmt.setString(5, adVO.getAdPic3());
			pstmt.setInt(6, adVO.getAdState());
			pstmt.setDate(7, adVO.getPostTime());
			pstmt.setDate(8, adVO.getDeadline());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			e.printStackTrace(System.err);
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
	public void update(AdVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adVO.getAdTitle());
			pstmt.setString(2, adVO.getAd());
			pstmt.setString(3, adVO.getAdPic1());
			pstmt.setString(4, adVO.getAdPic2());
			pstmt.setString(5, adVO.getAdPic3());
			pstmt.setInt(6, adVO.getAdState());
			pstmt.setDate(7, adVO.getPostTime());
			pstmt.setDate(8, adVO.getDeadline());
			pstmt.setInt(9, adVO.getAdNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			e.printStackTrace(System.err);
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
	public void delete(Integer adNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AdVO findByPrimaryKey(Integer adNo) {

		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adVo 也稱為 Domain objects
				adVO = new AdVO();
				adVO.setAdNo(rs.getInt("adno"));
				adVO.setAdTitle(rs.getString("adtitle"));
				adVO.setAd(rs.getString("ad"));
				adVO.setAdPic1(rs.getString("adpic1"));
				adVO.setAdPic2(rs.getString("adpic2"));
				adVO.setAdPic3(rs.getString("adpic3"));
				adVO.setAdState(rs.getInt("adstate"));
				adVO.setPostTime(rs.getDate("posttime"));
				adVO.setDeadline(rs.getDate("deadline"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return adVO;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adVO �]�٬� Domain objects
				adVO = new AdVO();
				adVO.setAdNo(rs.getInt("adno"));
				adVO.setAdTitle(rs.getString("adtitle"));
				adVO.setAd(rs.getString("ad"));
				adVO.setAdPic1(rs.getString("adpic1"));
				adVO.setAdPic2(rs.getString("adpic2"));
				adVO.setAdPic3(rs.getString("adpic3"));
				adVO.setAdState(rs.getInt("adstate"));
				adVO.setPostTime(rs.getDate("posttime"));
				adVO.setDeadline(rs.getDate("deadline"));
				list.add(adVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public List<AdVO> findAllByKeyword(String keyword) {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;
		String $keyword = "%" + keyword + "%";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT_BY_KEYWORD);
			pstmt.setString(1, $keyword);
			pstmt.setString(2, $keyword);
			pstmt.setString(3, $keyword);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adVO = new AdVO();
				adVO.setAdNo(rs.getInt("adno"));
				adVO.setAdTitle(rs.getString("adtitle"));
				adVO.setAd(rs.getString("ad"));
				adVO.setAdPic1(rs.getString("adpic1"));
				adVO.setAdPic2(rs.getString("adpic2"));
				adVO.setAdPic3(rs.getString("adpic3"));
				adVO.setAdState(rs.getInt("adstate"));
				adVO.setPostTime(rs.getDate("posttime"));
				adVO.setDeadline(rs.getDate("deadline"));
				list.add(adVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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