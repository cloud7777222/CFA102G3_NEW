package com.posttype.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.post.model.PostVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Post;

public class PostTypeDAO implements PostTypeDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		Context ctx;
		try {
			ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增修查sql指令
	public static final String INSERT_SQL = "insert into posttype (postType) values (?)";
	public static final String UpdateBy_postTypeNo_SQL = "update posttype set postType = ? where postTypeNo = ?";
	public static final String DeletBy_postTypeNo_SQL = "delete from posttype where postTypeNo = ?";
	public static final String FindBy_postTypeNo_SQL = "select * from posttype where postTypeNo = ?";
	public static final String getAll_SQL = "select * from posttype";
	public static final String GET_Posts_By_PostTypeNo = "SELECT postNo, postTypeNo, memberNo, postContent, postTime, postState, mesCount, numOfLike FROM post where postTypeNo = ? order by postNo desc";

	@Override
	public void insert(PostTypeVO postTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setString(1, postTypeVO.getPostType());
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

	@Override
	public void update(PostTypeVO postTypeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UpdateBy_postTypeNo_SQL);
			
			pstmt.setString(1, postTypeVO.getPostType());
			pstmt.setInt(2, postTypeVO.getPostTypeNo());
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

	@Override
	public void delete(Integer postTypeNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DeletBy_postTypeNo_SQL);

			pstmt.setInt(1, postTypeNo);
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

	@Override
	public PostTypeVO findByPrimaryKey(Integer postTypeNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostTypeVO postTypeVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBy_postTypeNo_SQL);

			pstmt.setInt(1, postTypeNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postTypeVO = new PostTypeVO();
				postTypeVO.setPostTypeNo(rs.getInt("postTypeNo"));
				postTypeVO.setPostType(rs.getString("postType"));
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
		return postTypeVO;
	}

	@Override
	public List<PostTypeVO> getAll() {

		List<PostTypeVO> list = new ArrayList<PostTypeVO>();
		PostTypeVO postTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postTypeVO = new PostTypeVO();
				postTypeVO.setPostTypeNo(rs.getInt("postTypeNo"));
				postTypeVO.setPostType(rs.getString("postType"));			
				list.add(postTypeVO); // Store the row in the list
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

	@Override
	public List<PostTypeVO> getAll(Map<String, String[]> map) {

		List<PostTypeVO> list = new ArrayList<PostTypeVO>();
		PostTypeVO postTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String finalSQL = "select * from posttype" + jdbcUtil_CompositeQuery_Post.get_WhereCondition(map)
					+ "order by postTypeNo";
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postTypeVO = new PostTypeVO();
				postTypeVO.setPostTypeNo(rs.getInt("postTypeNo"));
				postTypeVO.setPostType(rs.getString("postType"));	
				list.add(postTypeVO); // Store the row in the list
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

	//擇一查多
	@Override
	public Set<PostVO> getPostsByPostTypeNo(Integer postTypeNo) {
		
		Set<PostVO> set = new LinkedHashSet<PostVO>();
		PostVO postVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Posts_By_PostTypeNo);
			pstmt.setInt(1, postTypeNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				postVO = new PostVO();
				postVO.setPostNo(rs.getInt("postNo"));
				postVO.setPostTypeNo(rs.getInt("postTypeNo"));
				postVO.setMemberNo(rs.getInt("memberNo"));
				postVO.setPostContent(rs.getString("postContent"));
				postVO.setPostTime(rs.getDate("postTime"));
				postVO.setPostState(rs.getInt("postState"));
				postVO.setMesCount(rs.getInt("mesCount"));
				postVO.setNumOfLike(rs.getInt("numOfLike"));
				set.add(postVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

}
