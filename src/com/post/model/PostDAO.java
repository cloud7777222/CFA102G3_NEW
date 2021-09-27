package com.post.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Post;

public class PostDAO implements PostDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
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

	// �s�W�׬dsql���O
	public static final String INSERT_SQL = "insert into post (postTypeNo, memberNo, postContent, postTime, postState, mesCount, numOfLike) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String UpdateBy_postNo_SQL = "update post set postTypeNo = ?, memberNo = ?, postContent = ?, postTime = ?, postState = ?, mesCount = ?, numOfLike = ? where postNo = ?";
	public static final String DeletBy_postNo_SQL = "delete from post where postNo = ?";
	public static final String FindBy_postNo_SQL = "select * from post where postNo = ?";
	public static final String getAll_SQL = "select * from post";

	//�s�W�@�g�׾¤峹
	@Override
	public void insert(PostVO postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setInt(1, postVO.getPostTypeNo());
			pstmt.setInt(2, postVO.getMemberNo());
			pstmt.setString(3, postVO.getPostContent());
			pstmt.setDate(4, postVO.getPostTime());
			pstmt.setInt(5, postVO.getPostState());
			pstmt.setInt(6, postVO.getMesCount());
			pstmt.setInt(7, postVO.getNumOfLike());

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

	// ��s�峹���e�Ϊ��A
	@Override
	public void update(PostVO postVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UpdateBy_postNo_SQL);

			pstmt.setInt(1, postVO.getPostTypeNo());
			pstmt.setInt(2, postVO.getMemberNo());
			pstmt.setString(3, postVO.getPostContent());
			pstmt.setDate(4, postVO.getPostTime());
			pstmt.setInt(5, postVO.getPostState());
			pstmt.setInt(6, postVO.getMesCount());
			pstmt.setInt(7, postVO.getNumOfLike());
			pstmt.setInt(8, postVO.getPostNo());

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

	// �R���@�h�峹, �ھ�PK : postNo
	@Override
	public void delete(Integer postNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DeletBy_postNo_SQL);

			pstmt.setInt(1, postNo);
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

	// �j�M�@�g�׾¤峹, �ھ�PK : postNo
	@Override
	public PostVO findByPrimaryKey(Integer postNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostVO postVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBy_postNo_SQL);

			pstmt.setInt(1, postNo);
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
		return postVO;
	}

	// ��ܩҦ��׾¤峹
	@Override
	public List<PostVO> getAll() {

		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_SQL);
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
				list.add(postVO); // Store the row in the list
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

	// �ƦX�d��: �N���W�M�Ȧs��key,value
	@Override
	public List<PostVO> getAll(Map<String, String[]> map) {

		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String finalSQL = "select * from post" + jdbcUtil_CompositeQuery_Post.get_WhereCondition(map)
					+ "order by postNo";
			pstmt = con.prepareStatement(finalSQL);
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
				list.add(postVO); // Store the row in the list
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