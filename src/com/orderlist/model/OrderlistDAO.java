package com.orderlist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.prod.model.ProdVO;

public class OrderlistDAO implements Orderlist_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//新增訂單明細
	private static final String INSERT_LIST="insert into orderlist (orderno,prodno,quantity,price) values (?,?, ?, ?)"; 
	//搜尋所有訂單明細
	private static final String GET_ALL_STMT="select * from orderlist order by orderno and prodno"; 
	//單一訂單明細
	private static final String GET_ONE_STMT="select * from orderlist where orderno = ? and prodno =?";
	//刪除
	private static final String DELETE="delete from orderlist where orderno = ? and prodno =?"; 
	//更新
	private static final String UPDATE="update orderlist set price=?,quantity=? where orderno = ? and prodno =?"; 
	
	private static final String GET_ALL_DETAIL="select * from orderlist where orderno = ? ";
	
	private static final String UPDATE_QUAN="update orderlist set quantity=? where orderno = ? and prodno =?"; 
	
	
	@Override
	public void insert(OrderlistVO orderlistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_LIST);
			
			
			pstmt.setInt(1, orderlistVO.getProdno());
			pstmt.setInt(2, orderlistVO.getQuantity());
			pstmt.setInt(3, orderlistVO.getPrice());
		
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
	public void update(OrderlistVO orderlistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, orderlistVO.getPrice());
			pstmt.setInt(2, orderlistVO.getQuantity());
			pstmt.setInt(3, orderlistVO.getOrderno());
			pstmt.setInt(4, orderlistVO.getProdno());
			
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
	public void delete(Integer orderno, Integer prodno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, orderno);
			pstmt.setInt(2,prodno);
			
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
	public List<OrderlistVO> getAll() {
		List<OrderlistVO> list = new ArrayList<OrderlistVO>();
		OrderlistVO orderlistVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				orderlistVO = new OrderlistVO();
				
				orderlistVO.setOrderno(rs.getInt("orderno"));
				orderlistVO.setProdno(rs.getInt("prodno"));
				orderlistVO.setQuantity(rs.getInt("quantity"));
				orderlistVO.setPrice(rs.getInt("price"));
				
				list.add(orderlistVO);
			
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
		
		return list;
	}
	

	@Override
	public OrderlistVO findByPrimaryKey(Integer orderno, Integer prodno) {
		OrderlistVO orderlistVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, orderno);
			pstmt.setInt(2, prodno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				orderlistVO = new OrderlistVO();
				
				orderlistVO.setOrderno(rs.getInt("orderno"));
				orderlistVO.setProdno(rs.getInt("prodno"));
				orderlistVO.setQuantity(rs.getInt("quantity"));
				orderlistVO.setPrice(rs.getInt("price"));
			
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
		
		return orderlistVO;
	}

	@Override
	public void insert2(OrderlistVO orderlistVO, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_LIST);
			
			pstmt.setInt(1, orderlistVO.getOrderno());
			pstmt.setInt(2, orderlistVO.getProdno());
			pstmt.setInt(3, orderlistVO.getQuantity());
			pstmt.setInt(4, orderlistVO.getPrice());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
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
		}
		
	}

	@Override
	public List<OrderlistVO> getOrderList(Integer orderno) {
		List<OrderlistVO> list = new ArrayList<OrderlistVO>();
		OrderlistVO orderlistVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DETAIL);
			pstmt.setInt(1, orderno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				orderlistVO = new OrderlistVO();
				
				orderlistVO.setOrderno(rs.getInt("orderno"));
				orderlistVO.setProdno(rs.getInt("prodno"));
				orderlistVO.setQuantity(rs.getInt("quantity"));
				orderlistVO.setPrice(rs.getInt("price"));
				
				list.add(orderlistVO);
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
		
		return list;
	
	}

	@Override
	public void updateQuantity(Integer quantity,Integer orderno,Integer prodno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_QUAN);
			
			pstmt.setInt(1,quantity);
			pstmt.setInt(2, orderno);
			pstmt.setInt(3, prodno);
			
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

	
	

	

}
