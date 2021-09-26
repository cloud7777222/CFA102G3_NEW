package com.emp.model;

import java.util.List;

import com.prod.model.ProdVO;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}
	
	public EmpVO addEmp(String empaccount,String emppassword,String empname,Integer empstate){
	EmpVO empVO = new EmpVO();
	
	empVO.setEmpaccount(empaccount);
	empVO.setEmppassword(emppassword);
	empVO.setEmpname(empname);
	empVO.setEmpstate(empstate);
	
	
	dao.insert(empVO);
	
	return empVO;
	}
	public EmpVO updateEmp(String empaccount,String emppassword,String empname,Integer empstate,Integer empno){
		EmpVO empVO = new EmpVO();
		
		empVO.setEmpaccount(empaccount);
		empVO.setEmppassword(emppassword);
		empVO.setEmpname(empname);
		empVO.setEmpstate(empstate);
		empVO.setEmpno(empno);
		
		
		dao.update(empVO);
		
		return empVO;
	}
	public EmpVO updateState(String empaccount,String emppassword,String empname,Integer empstate){
		EmpVO empVO = new EmpVO();
		
		
		empVO.setEmpstate(empstate);
		
		
		dao.updatestate(empVO);
		
		return empVO;
	}
	public void delEmp(Integer empno) {
        dao.delete(empno);
		
	}
	public List<EmpVO> getAll(){
		return dao.getAll();
	}
	public EmpVO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}
	public EmpVO Elogin(String empaccount, String emppassword) {
		return dao.EmpLogin(empaccount, emppassword);
	}
	
}
