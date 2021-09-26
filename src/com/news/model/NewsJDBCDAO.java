package com.news.model;

import java.util.*;
import java.io.FileInputStream;
import java.sql.*;

public class NewsJDBCDAO implements NewsDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/belovedb?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO news (newstitle, news, newspic, newsstate) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT newsno, newstitle, news, newspic, newsstate, posttime FROM news order by newsstate DESC, newsno DESC";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM news where newsno = ?";
	private static final String DELETE = 
			"DELETE FROM news WHERE newsno = ?";
	private static final String UPDATE = 
			"UPDATE news SET newstitle=?, news=?, newspic=?, newsstate=?, posttime=now() WHERE newsno = ?";

	@Override
	public void insert(NewsVO newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, newsVO.getNewsTitle());
			pstmt.setString(2, newsVO.getNews());
			pstmt.setBytes(3, newsVO.getNewsPic());
			pstmt.setInt(4, newsVO.getNewsState());

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
	public void update(NewsVO newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsVO.getNewsTitle());
			pstmt.setString(2, newsVO.getNews());
			pstmt.setBytes(3,newsVO.getNewsPic());
			pstmt.setInt(4, newsVO.getNewsState());
			pstmt.setInt(5, newsVO.getNewsNo());

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
	public void delete(Integer newsno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, newsno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public NewsVO findByPrimaryKey(Integer newsno) {

		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, newsno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// newsVo 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNewsNo(rs.getInt("newsno"));
				newsVO.setNewsTitle(rs.getString("newstitle"));
				newsVO.setNews(rs.getString("news"));
				newsVO.setNewsPic(rs.getBytes("newspic"));
				newsVO.setNewsState(rs.getInt("newsstate"));
				newsVO.setPostTime(rs.getDate("posttime"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNewsNo(rs.getInt("newsno"));
				newsVO.setNewsTitle(rs.getString("newstitle"));
				newsVO.setNews(rs.getString("news"));
				newsVO.setNewsPic(rs.getBytes("newspic"));
				newsVO.setNewsState(rs.getInt("newsstate"));
				newsVO.setPostTime(rs.getDate("posttime"));
				list.add(newsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	//test blob
	public static byte[] getPicByteArray(String path) throws Exception {
		FileInputStream fis = new FileInputStream(path);
		byte[] b = new byte[fis.available()];
		fis.read(b);
		fis.close();
		return b;
	}
	

	public static void main(String[] args) {
//		newsno,newstitle,news,newspic,newsstate,posttime

		NewsJDBCDAO dao = new NewsJDBCDAO();

		// 新增
		NewsVO newsVO1 = new NewsVO();
		newsVO1.setNewsTitle("我是標題99");
		newsVO1.setNews("我是新聞99");
		byte[] pic;
		try {
			pic = getPicByteArray("WebContent/back_end/news/none.jpg");
			newsVO1.setNewsPic(pic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		newsVO1.setNewsState(1);
		dao.insert(newsVO1);
//		newsVO1.setPostTime(java.sql.Date.valueOf("2005-01-01"));


		// 修改
		NewsVO newsVO2 = new NewsVO();
		newsVO2.setNewsNo(1001);
		newsVO2.setNewsTitle("我是標題8");
		newsVO2.setNews("我是新聞8");
		try {
			pic = getPicByteArray("WebContent/back_end/news/cat01.jpg");
			newsVO2.setNewsPic(pic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		newsVO2.setNewsState(1);
		dao.update(newsVO2);

		// 刪除
		dao.delete(1003);

		// 查詢one
		NewsVO newsVO3 = dao.findByPrimaryKey(1001);
		System.out.print(newsVO3.getNewsNo() + ",");
		System.out.print(newsVO3.getNewsTitle() + ",");
		System.out.print(newsVO3.getNews() + ",");
		System.out.print(newsVO3.getNewsPic() + ",");
		System.out.print(newsVO3.getNewsState() + ",");
		System.out.print(newsVO3.getPostTime());
		System.out.println();
		System.out.println("---------------------");

		// 查詢all
		List<NewsVO> list = dao.getAll();
		for (NewsVO aNews : list) {
			System.out.print(aNews.getNewsNo() + ",");
			System.out.print(aNews.getNewsTitle() + ",");
			System.out.print(aNews.getNews() + ",");
			System.out.print(aNews.getNewsPic() + ",");
			System.out.print(aNews.getNewsState() + ",");
			System.out.print(aNews.getPostTime() + ",");
			System.out.println();
		}
	}
}