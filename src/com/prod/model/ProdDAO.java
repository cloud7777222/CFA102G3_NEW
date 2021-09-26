package com.prod.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ProdDAO implements ProdDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		//後台新增商品
		private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT(prodsortNo,prodName,price,indroce,prodPic1,prodPic2,prodPic3,prodState,posttime) VALUES (?, ?, ?, ?, ?, ?, ?, ?,NOW())";
		//前台和後台查看商品
		private static final String GET_ALL_PROD = 
			"SELECT prodno,prodsortNo,prodName,price,indroce,prodPic1,prodPic2,prodPic3,prodState,postTime FROM PRODUCT order by prodno";
		private static final String GET_ALL_PRODV = 
			"SELECT prodno,prodsortNo,prodName,price,prodPic1,prodPic2,prodPic3,prodState,postTime FROM PRODUCT where prodstate = 1 order by prodno ";
		//前台查看商品詳情(使用prodno)
		private static final String GET_ONE_STMT =
			"SELECT prodno,prodsortno,prodName,price,indroce,prodPic1,prodPic2,prodPic3,prodState,postTime FROM PRODUCT WHERE prodno=?";
		//後台刪除商品
		private static final String DELETE =
			"DELETE from PRODUCT where prodno = ?";
		//後台更改商品
		private static final String UPDATE_PROD =
				"UPDATE PRODUCT set prodsortno=?, prodname=?, price=?, indroce=?,prodPic1=?,prodPic2=?, prodPic3=? where prodno = ?";
		//後台更改狀態
		private static final String UPDATE_STATE =
				"UPDATE PRODUCT set prodstate = ? where prodno = ?";
		//前台後台查找商品(使用prodsortno)
		private static final String GET_PROD_SORTV =
				"SELECT prodno,prodsortNo,prodName,price,prodPic1,prodPic2,prodPic3 FROM PRODUCT where prodstate = 1 and prodsortno = ?";
		private static final String GET_PROD_SORT =
				"SELECT prodno,prodsortNo,prodName,price,indroce,prodPic1,prodPic2,prodPic3,prodstate FROM PRODUCT where prodsortno = ?";
		private static final String GET_PROD_PRICE =
				"SELECT prodno,prodsortNo,prodName,price,prodPic1,prodPic2,prodPic3 FROM PRODUCT where prodstate = 1 and price between ? and ?";
		
		@Override
		public void insert(ProdVO prodVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, prodVO.getProdsortno());
				pstmt.setString(2, prodVO.getProdname());
				pstmt.setInt(3, prodVO.getPrice());
				pstmt.setString(4, prodVO.getIndroce());
				pstmt.setString(5, prodVO.getProdpic1());
				pstmt.setString(6, prodVO.getProdpic2());
				pstmt.setString(7, prodVO.getProdpic3());
				pstmt.setInt(8, prodVO.getProdstate());
				
		
				pstmt.executeUpdate();
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured."
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
	public ProdVO findByPrimaryKey(Integer prodno) {
		ProdVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, prodno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				prodVO = new ProdVO();
				prodVO.setProdno(rs.getInt("prodno"));
				prodVO.setProdsortno(rs.getInt("prodsortno"));
				prodVO.setProdname(rs.getString("prodname"));
				prodVO.setIndroce(rs.getString("indroce"));
				prodVO.setPrice(rs.getInt("price"));
				prodVO.setProdpic1(rs.getString("prodpic1"));
				prodVO.setProdpic2(rs.getString("prodpic2"));
				prodVO.setProdpic3(rs.getString("prodpic3"));
				prodVO.setProdstate(rs.getInt("prodstate"));
				prodVO.setPosttime(rs.getDate("posttime"));
			}
	
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
		return prodVO;
	}
		
	

	@Override
	public List<ProdVO> getAll() {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PROD);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdno(rs.getInt("prodno"));
				prodVO.setProdsortno(rs.getInt("prodsortno"));
				prodVO.setProdname(rs.getString("prodname"));
				prodVO.setPrice(rs.getInt("price"));
				prodVO.setIndroce(rs.getString("indroce"));
				prodVO.setProdpic1(rs.getString("prodpic1"));
				prodVO.setProdpic2(rs.getString("prodpic2"));
				prodVO.setProdpic3(rs.getString("prodpic3"));
				prodVO.setProdstate(rs.getInt("prodstate"));
				prodVO.setPosttime(rs.getDate("posttime"));
				list.add(prodVO); 
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
	



	@Override
	public void delete(Integer prodno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, prodno);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
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
	public void update(ProdVO prodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PROD);
			
			pstmt.setInt(1,prodVO.getProdsortno());
			pstmt.setString(2, prodVO.getProdname());
			pstmt.setInt(3, prodVO.getPrice());
			pstmt.setString(4, prodVO.getIndroce());
			pstmt.setString(5, prodVO.getProdpic1());
			pstmt.setString(6, prodVO.getProdpic2());
			pstmt.setString(7, prodVO.getProdpic3());
			pstmt.setInt(8,prodVO.getProdno());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
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
	public void updatestate(ProdVO prodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATE);
			
			pstmt.setInt(1, prodVO.getProdstate());
			pstmt.setInt(2, prodVO.getProdno());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
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
	public List<ProdVO> searchBySortV(Integer prodsortno) {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROD_SORTV);
			
			pstmt.setInt(1, prodsortno);
			
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdno(rs.getInt("prodno"));
				prodVO.setProdsortno(rs.getInt("prodsortno"));
				prodVO.setProdname(rs.getString("prodname"));
				prodVO.setPrice(rs.getInt("price"));
				prodVO.setProdpic1(rs.getString("prodpic1"));
				prodVO.setProdpic2(rs.getString("prodpic2"));
				prodVO.setProdpic3(rs.getString("prodpic3"));
				
				list.add(prodVO); 
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
	
	@Override
	public List<ProdVO> searchBySortAll(Integer prodsortno) {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROD_SORT);
			
			pstmt.setInt(1, prodsortno);
			
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdno(rs.getInt("prodno"));
				prodVO.setProdsortno(rs.getInt("prodsortno"));
				prodVO.setProdname(rs.getString("prodname"));
				prodVO.setPrice(rs.getInt("price"));
				prodVO.setIndroce(rs.getString("indroce"));
				prodVO.setProdpic1(rs.getString("prodpic1"));
				prodVO.setProdpic2(rs.getString("prodpic2"));
				prodVO.setProdpic3(rs.getString("prodpic3"));
				prodVO.setProdstate(rs.getInt("prodstate"));
				
				list.add(prodVO); 
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



	@Override
	public List<ProdVO> searchByPricerag(Integer minprice, Integer maxprice) {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROD_PRICE);
			
			pstmt.setInt(1, minprice);
			pstmt.setInt(2, maxprice);
			
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdno(rs.getInt("prodno"));
				prodVO.setProdsortno(rs.getInt("prodsortno"));
				prodVO.setProdname(rs.getString("prodname"));
				prodVO.setPrice(rs.getInt("price"));
				prodVO.setProdpic1(rs.getString("prodpic1"));
				prodVO.setProdpic2(rs.getString("prodpic2"));
				prodVO.setProdpic3(rs.getString("prodpic3"));
				
				list.add(prodVO); 
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



	@Override
	public List<ProdVO> getAllForFront() {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PRODV);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdno(rs.getInt("prodno"));
				prodVO.setProdsortno(rs.getInt("prodsortno"));
				prodVO.setProdname(rs.getString("prodname"));
				prodVO.setPrice(rs.getInt("price"));
				prodVO.setProdpic1(rs.getString("prodpic1"));
				prodVO.setProdpic2(rs.getString("prodpic2"));
				prodVO.setProdpic3(rs.getString("prodpic3"));
				prodVO.setProdstate(rs.getInt("prodstate"));
				prodVO.setPosttime(rs.getDate("posttime"));
				list.add(prodVO); 
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

		
	
		


	


