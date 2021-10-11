package com.prodsort.model;

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

import org.hibernate.Session;

import com.orderlist.model.OrderlistDAO;
import com.orderlist.model.OrderlistVO;
import com.prod.model.ProdDAO;
import com.prod.model.ProdVO;


public class ProdsortDAO implements ProdsortDAO_interface  {
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
				"insert into productsort (prodsortname) values(?)";
		private static final String DELETE = 
				"delete from productsort where prodsortno = ?";
		private static final String UPDATE =
				"update productsort set prodsortname = ? where prodsortno = ?";
		private static final String GET_ONE_STMT =
				"select prodsortname from productsort where prodsortno = ?";
		//取得所有商品類別
		private static final String GET_ALL_PRODSORT = 
				"select prodsortNo,prodsortName from productsort ";
		

		@Override
		public void insert(ProdsortVO prodsortVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, prodsortVO.getProdsortname());
				pstmt.executeUpdate();

				// Handle any driver errors
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
		public void delete(Integer prodsortno) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				pstmt.setInt(1, prodsortno);
				pstmt.executeUpdate();

				// Handle any driver errors
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
		public void update(ProdsortVO prodsortVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, prodsortVO.getProdsortname());
				pstmt.setInt(2, prodsortVO.getProdsortno());

				pstmt.executeUpdate();

				// Handle any driver errors
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
		public ProdsortVO findByPrimaryKey(Integer prodsortno) {
			
			ProdsortVO prodsortVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {


				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, prodsortno);

				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					prodsortVO = new ProdsortVO();
					prodsortVO.setProdsortno(rs.getInt("prodsortno"));
					prodsortVO.setProdsortname(rs.getString("prodsortname"));
				}

				// Handle any driver errors
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
			return prodsortVO;
		}




		@Override
		public List<ProdsortVO> getAll() {
			List<ProdsortVO> list = new ArrayList<ProdsortVO>();
			ProdsortVO prodsortVO = null;
				
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				
			try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_PRODSORT);
					rs = pstmt.executeQuery();
		
					while (rs.next()) {
						prodsortVO = new ProdsortVO();
						prodsortVO.setProdsortno(rs.getInt("prodsortno"));
						prodsortVO.setProdsortname(rs.getString("prodsortname"));
						list.add(prodsortVO); 
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
		public void insertWithProd(ProdsortVO prodsortVO, ProdVO prodVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				con.setAutoCommit(false);
				String cols2[] = {"prodsortno"};
				pstmt = con.prepareStatement(INSERT_STMT,cols2);
				
				pstmt.setString(1, prodsortVO.getProdsortname());
				
				
				pstmt.executeUpdate();
				String next_prodsortno = null;
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if(rs.next()) {
					next_prodsortno = rs.getString(1);
					System.out.println("自增主鍵值= " + next_prodsortno +"(剛新增成功的商品類別編號)");
				}else {
					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				
				ProdDAO dao = new ProdDAO();
				prodVO.setProdsortno(new Integer(next_prodsortno));
				dao.insert2(prodVO,con);
				con.commit();
				
				System.out.println("新增商品類別編號" + next_prodsortno);
				
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

		
			
			
		}


