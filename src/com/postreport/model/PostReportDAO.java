package com.postreport.model;

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


public class PostReportDAO implements PostReportDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
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

	// �s�W�׬dsql���O
	public static final String INSERT_SQL = "insert into postreport (postNo, memberNo, postRepTime, postRepFor, postRevState) values (?, ?, ?, ?, ?)";
	public static final String UpdateBy_postNo_SQL = "update postreport set postRepTime = ?, postRepFor = ?, postRevState = ? where postNo = ? and memberNo = ?";
	public static final String DeletBy_postNo_SQL = "delete from postreport where postNo = ? and memberNo = ?";
	public static final String FindBy_postNo_SQL = "select * from postreport where postNo = ? and memberNo = ?";
	public static final String getAll_SQL = "select * from postreport";

	
	//�s�W�@���׾¤峹���|
	@Override
	public void insert(PostReportVO postReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setInt(1, postReportVO.getPostNo());
			pstmt.setInt(2, postReportVO.getMemberNo());
			pstmt.setDate(3, postReportVO.getPostRepTime());
			pstmt.setString(4, postReportVO.getPostRepFor());
			pstmt.setInt(5, postReportVO.getPostRevState());

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

	// ��s�׾¤峹���|���e�Ϊ��A
	@Override
	public void update(PostReportVO postReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UpdateBy_postNo_SQL);
			
			pstmt.setDate(1, postReportVO.getPostRepTime());
			pstmt.setString(2, postReportVO.getPostRepFor());
			pstmt.setInt(3, postReportVO.getPostRevState());
			pstmt.setInt(4, postReportVO.getPostNo());
			pstmt.setInt(5, postReportVO.getMemberNo());
			
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

	// �R���@�h�׾¤峹���|, �ھڽƦXPK : postNo+memberNo
	@Override
	public void delete(Integer postNo, Integer memberNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DeletBy_postNo_SQL);

			pstmt.setInt(1, postNo);
			pstmt.setInt(2, memberNo);
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

	// �j�M�@�h�׾¤峹���|, �ھڽƦXPK : postNo+memberNo
	@Override
	public PostReportVO findByPrimaryKey(Integer postNo, Integer memberNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostReportVO postReportVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FindBy_postNo_SQL);

			pstmt.setInt(1, postNo);
			pstmt.setInt(2, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postReportVO = new PostReportVO();
				postReportVO.setPostNo(rs.getInt("postNo"));				
				postReportVO.setMemberNo(rs.getInt("memberNo"));
				postReportVO.setPostRepTime(rs.getDate("postRepTime"));
				postReportVO.setPostRepFor(rs.getString("postRepFor"));
				postReportVO.setPostRevState(rs.getInt("postRevState"));

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
		return postReportVO;
	}
	
	
	// ��ܩҦ��׾¤峹���|
	@Override
	public List<PostReportVO> getAll() {

		List<PostReportVO> list = new ArrayList<PostReportVO>();
		PostReportVO postReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postReportVO = new PostReportVO();
				postReportVO.setPostNo(rs.getInt("postNo"));				
				postReportVO.setMemberNo(rs.getInt("memberNo"));
				postReportVO.setPostRepTime(rs.getDate("postRepTime"));
				postReportVO.setPostRepFor(rs.getString("postRepFor"));
				postReportVO.setPostRevState(rs.getInt("postRevState"));
				list.add(postReportVO); // Store the row in the list
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
	public List<PostReportVO> getAll(Map<String, String[]> map) {
	
		List<PostReportVO> list = new ArrayList<PostReportVO>();
		PostReportVO postReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String finalSQL = "select * from postreport" + jdbcUtil_CompositeQuery_Post.get_WhereCondition(map)
					+ "order by postNo";
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postReportVO = new PostReportVO();
				postReportVO.setPostNo(rs.getInt("postNo"));				
				postReportVO.setMemberNo(rs.getInt("memberNo"));
				postReportVO.setPostRepTime(rs.getDate("postRepTime"));
				postReportVO.setPostRepFor(rs.getString("postRepFor"));
				postReportVO.setPostRevState(rs.getInt("postRevState"));
				list.add(postReportVO); // Store the row in the list
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
