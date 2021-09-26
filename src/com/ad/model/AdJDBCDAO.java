package com.ad.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class AdJDBCDAO implements AdDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO advertise (adtitle, ad, adpic1, adpic2, adpic3, adstate, posttime, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adno, adtitle, ad, adpic1, adpic2, adpic3, adstate, posttime, deadline FROM advertise";
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_ALL_STMT);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

		AdJDBCDAO dao = new AdJDBCDAO();

		// 新增
		AdVO adVO1 = new AdVO();
		adVO1.setAdTitle("我是廣告標題99");
		adVO1.setAd("我是廣告99");
		adVO1.setAdPic1("https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg");
		adVO1.setAdPic2("圖片2");
		adVO1.setAdPic3("圖片3");
		adVO1.setAdState(1);
		adVO1.setPostTime(java.sql.Date.valueOf("2005-01-01"));
		adVO1.setDeadline(java.sql.Date.valueOf("2020-01-01"));
		dao.insert(adVO1);
//		adVO1.setPostTime(java.sql.Date.valueOf("2005-01-01"));

		// 修改
		AdVO adVO2 = new AdVO();
		adVO2.setAdNo(1004);
		adVO2.setAdTitle("我是廣告標題9");
		adVO2.setAd("我是廣告9");
		adVO2.setAdPic1("https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg");
		adVO2.setAdPic2("圖片2");
		adVO2.setAdPic3("圖片3");
		adVO2.setAdState(1);
		adVO2.setPostTime(Date.valueOf("2021-01-01"));
		adVO2.setDeadline(Date.valueOf("2021-02-01"));
		dao.update(adVO2);

		// 刪除
		dao.delete(1003);

		// 查詢one
		AdVO adVO3 = dao.findByPrimaryKey(1006);
		System.out.print(adVO3.getAdNo() + ",");
		System.out.print(adVO3.getAdTitle() + ",");
		System.out.print(adVO3.getAd() + ",");
		System.out.print(adVO3.getAdPic1() + ",");
		System.out.print(adVO3.getAdPic2() + ",");
		System.out.print(adVO3.getAdPic3() + ",");
		System.out.print(adVO3.getAdState() + ",");
		System.out.print(adVO3.getPostTime() + ",");
		System.out.print(adVO3.getDeadline());
		System.out.println();
		System.out.println("---------------------");

		// 查詢all
		List<AdVO> list = dao.getAll();
		for (AdVO aAd : list) {
			System.out.print(aAd.getAdNo() + ",");
			System.out.print(aAd.getAdTitle() + ",");
			System.out.print(aAd.getAd() + ",");
			System.out.print(aAd.getAdPic1() + ",");
			System.out.print(aAd.getAdPic2() + ",");
			System.out.print(aAd.getAdPic3() + ",");
			System.out.print(aAd.getAdState() + ",");
			System.out.print(aAd.getPostTime() + ",");
			System.out.print(aAd.getDeadline());
			System.out.println();
		}
		System.out.println("---------------------");

		// keyword查詢
		List<AdVO> list2 = dao.findAllByKeyword("9");
		for (AdVO aAd : list2) {
			System.out.print(aAd.getAdNo() + ",");
			System.out.print(aAd.getAdTitle() + ",");
			System.out.print(aAd.getAd() + ",");
			System.out.print(aAd.getAdPic1() + ",");
			System.out.print(aAd.getAdPic2() + ",");
			System.out.print(aAd.getAdPic3() + ",");
			System.out.print(aAd.getAdState() + ",");
			System.out.print(aAd.getPostTime() + ",");
			System.out.print(aAd.getDeadline());
			System.out.println();
		}
		System.out.println("keyword查詢");
	}
}