package com.order.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.orderlist.model.OrderlistDAO;
import com.orderlist.model.OrderlistVO;


public class OrderDAO implements OrderDAO_interface{
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
			"insert into `order` (orderstate,total,orderer,address,tel,orderdate,paymentmeth,deliverymeth,memberno) values(?, ?, ?, ?, ?,NOW(), ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"select * from `order` order by orderno desc";
	private static final String GET_ONE_STMT =
			"select * from `order` where orderno=?";
	private static final String DELETE =
			"delete from `order` where orderno = ?";
	private static final String CANCEL =
			"update `order` set orderstate = ? where orderno = ? ";
	private static final String UPDATE =
			"update `order` set orderer=?,address=?,tel=?,deliverymeth=? where (orderno = ?)";
	private static final String GET_ONE_MNO =
			"select * from `order`  where memberno=? order by orderno desc";
	private static final String GET_LIST_BY_ONO =
			"select * from orderlist where orderno=? order by orderno";
	private static final String UPDATE_ORDER_PRICE =
			"update `order` set total = ? where (orderno = ?)";
	private static final String GET_ORDER_STATE =
			"select * from `order` where orderstate = ? order by orderno desc";
	private static final String GET_ORDER_STATE_V =
			"select * from `order` where orderstate = ? and memberno = ? order by orderno desc";
	private static final String UPDATE_CANCEL_REASON =
			"update `order` set cancelreason = ? where  orderno = ? ";
	private static final String GET_ORDER_BY_MEMBERNO =
			"select * from `order` where orderno = ? and memberno = ? ";
	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT,PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			pstmt.setInt(1, orderVO.getOrderstate());
			pstmt.setInt(2, orderVO.getTotal());
			pstmt.setString(3, orderVO.getOrderer());
			pstmt.setString(4, orderVO.getAddress());
			pstmt.setString(5, orderVO.getTel());
			pstmt.setInt(6, orderVO.getPaymentmeth());
			pstmt.setInt(7, orderVO.getDeliverymeth());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			Integer orderno = rs.getInt(1);
			orderVO.setOrderno(orderno);
			
			con.commit();
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
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
	}

	@Override
	public void delete(Integer orderno){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, orderno);
			
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
	public void updateOrder(String orderer,String address,String tel,Integer deliverymeth,Integer orderno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
		
			pstmt.setString(1, orderer);
			pstmt.setString(2, address);
			pstmt.setString(3, tel);
			pstmt.setInt(4, deliverymeth);
			pstmt.setInt(5,orderno);

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
	public void cancel(Integer orderno,Integer orderstate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CANCEL);
			
			pstmt.setInt(1, orderstate);
			pstmt.setInt(2, orderno);
			
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
	public List<OrderVO> getByMno(Integer memberno) {
		
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO  orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MNO);
			pstmt.setInt(1, memberno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderVO = new OrderVO();
				orderVO.setOrderno(rs.getInt("orderno"));
				orderVO.setMemberno(rs.getInt("memberno"));
				orderVO.setOrderstate(rs.getInt("orderstate"));
				orderVO.setTotal(rs.getInt("total"));
				orderVO.setOrderer(rs.getString("orderer"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setTel(rs.getString("tel"));
				orderVO.setOrderdate(rs.getDate("orderdate"));
				orderVO.setCreditcardnum(rs.getString("creditcardnum"));
				orderVO.setPaymentmeth(rs.getInt("paymentmeth"));
				orderVO.setDeliverymeth(rs.getInt("deliverymeth"));
				list.add(orderVO);
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
	public OrderVO findByPrimaryKey(Integer orderno) {
		
		OrderVO  orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, orderno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderVO = new OrderVO();
				orderVO.setOrderno(rs.getInt("orderno"));
				orderVO.setMemberno(rs.getInt("memberno"));
				orderVO.setOrderstate(rs.getInt("orderstate"));
				orderVO.setTotal(rs.getInt("total"));
				orderVO.setOrderer(rs.getString("orderer"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setTel(rs.getString("tel"));
				orderVO.setOrderdate(rs.getDate("orderdate"));
				orderVO.setCreditcardnum(rs.getString("creditcardnum"));
				orderVO.setPaymentmeth(rs.getInt("paymentmeth"));
				orderVO.setDeliverymeth(rs.getInt("deliverymeth"));
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
		return orderVO;
	}
	
	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO  orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderVO = new OrderVO();
				orderVO.setOrderno(rs.getInt("orderno"));
				orderVO.setMemberno(rs.getInt("memberno"));
				orderVO.setOrderstate(rs.getInt("orderstate"));
				orderVO.setTotal(rs.getInt("total"));
				orderVO.setOrderer(rs.getString("orderer"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setTel(rs.getString("tel"));
				orderVO.setOrderdate(rs.getDate("orderdate"));
				orderVO.setCreditcardnum(rs.getString("creditcardnum"));
				orderVO.setPaymentmeth(rs.getInt("paymentmeth"));
				orderVO.setDeliverymeth(rs.getInt("deliverymeth"));
				list.add(orderVO);
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
	public List<OrderlistVO> getListbyono(Integer orderno) {
		List<OrderlistVO> set = new ArrayList<OrderlistVO>();
		OrderlistVO OrderlistVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_ONO);
			pstmt.setInt(1, orderno);
			rs = pstmt.executeQuery();
		
		
		
		while (rs.next()) {
			
			OrderlistVO = new OrderlistVO();
			OrderlistVO.setOrderno(rs.getInt("orderno"));
			OrderlistVO.setProdno(rs.getInt("prodno"));
			OrderlistVO.setQuantity(rs.getInt("quantuty"));
			OrderlistVO.setPrice(rs.getInt("price"));
		
			set.add(OrderlistVO);
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
	return set;
}

	@Override
	public void insertWithOrderlist(OrderVO orderVO, List<OrderlistVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[] = {"orderno"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			
			pstmt.setInt(1, orderVO.getOrderstate());
			pstmt.setInt(2, orderVO.getTotal());
			pstmt.setString(3, orderVO.getOrderer());
			pstmt.setString(4, orderVO.getAddress());
			pstmt.setString(5, orderVO.getTel());
			pstmt.setInt(6, orderVO.getPaymentmeth());
			pstmt.setInt(7, orderVO.getDeliverymeth());
			pstmt.setInt(8, orderVO.getMemberno());
			
			pstmt.executeUpdate();
			String next_orderno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				next_orderno = rs.getString(1);
				System.out.println("自增主鍵值= " + next_orderno +"(剛新增成功的訂單編號)");
			}else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			OrderlistDAO dao = new OrderlistDAO();
			System.out.println("list.size()-A="+list.size());
			for (OrderlistVO olist : list) {
				olist.setOrderno(new Integer(next_orderno)) ;
				dao.insert2(olist,con);
			}
			con.commit();
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_orderno + "時,共有明細" + list.size()
					+ "筆同時被新增");
			
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
	public void updatePrice(Integer total, Integer orderno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDER_PRICE);
			
			pstmt.setInt(1, total);
			pstmt.setInt(2, orderno);
			
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
	public List<OrderVO> getOrderState(Integer orderstate) {
		
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO  orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDER_STATE);
			pstmt.setInt(1, orderstate);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderVO = new OrderVO();
				orderVO.setOrderno(rs.getInt("orderno"));
				orderVO.setMemberno(rs.getInt("memberno"));
				orderVO.setOrderstate(rs.getInt("orderstate"));
				orderVO.setTotal(rs.getInt("total"));
				orderVO.setOrderer(rs.getString("orderer"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setTel(rs.getString("tel"));
				orderVO.setOrderdate(rs.getDate("orderdate"));
				orderVO.setCreditcardnum(rs.getString("creditcardnum"));
				orderVO.setPaymentmeth(rs.getInt("paymentmeth"));
				orderVO.setDeliverymeth(rs.getInt("deliverymeth"));
				orderVO.setCancelreason(rs.getString("cancelreason"));
				list.add(orderVO);
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
	public List<OrderVO> getOrderStateV(Integer orderstate, Integer memberno) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO  orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDER_STATE_V);
			pstmt.setInt(1, orderstate);
			pstmt.setInt(2, memberno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderVO = new OrderVO();
				orderVO.setOrderno(rs.getInt("orderno"));
				orderVO.setMemberno(rs.getInt("memberno"));
				orderVO.setOrderstate(rs.getInt("orderstate"));
				orderVO.setTotal(rs.getInt("total"));
				orderVO.setOrderer(rs.getString("orderer"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setTel(rs.getString("tel"));
				orderVO.setOrderdate(rs.getDate("orderdate"));
				orderVO.setCreditcardnum(rs.getString("creditcardnum"));
				orderVO.setPaymentmeth(rs.getInt("paymentmeth"));
				orderVO.setDeliverymeth(rs.getInt("deliverymeth"));
				list.add(orderVO);
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
	public void cancelreason(Integer orderno,String cancelreason) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CANCEL_REASON);
			
		
			pstmt.setString(1,cancelreason);
			pstmt.setInt(2, orderno);
		
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
	public OrderVO findOrderByMemberno(Integer orderno, Integer memberno) {
		
		OrderVO  orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDER_BY_MEMBERNO);
			pstmt.setInt(1, orderno);
			pstmt.setInt(2, memberno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderno(rs.getInt("orderno"));
				orderVO.setMemberno(rs.getInt("memberno"));
				orderVO.setOrderstate(rs.getInt("orderstate"));
				orderVO.setTotal(rs.getInt("total"));
				orderVO.setOrderer(rs.getString("orderer"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setTel(rs.getString("tel"));
				orderVO.setOrderdate(rs.getDate("orderdate"));
				orderVO.setCreditcardnum(rs.getString("creditcardnum"));
				orderVO.setPaymentmeth(rs.getInt("paymentmeth"));
				orderVO.setDeliverymeth(rs.getInt("deliverymeth"));
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
		return orderVO;
	 }
  

}
	
	

	

	
	


