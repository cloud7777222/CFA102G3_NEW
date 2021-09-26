package com.emp.model;

import java.util.List;



public interface EmpDAO_interface {
	
	public void insert (EmpVO empVO);
	public void delete(Integer empno);
    public void update(EmpVO empVO);
    public void updatestate(EmpVO empVO);
    
    public EmpVO findByPrimaryKey(Integer empno);
    public List<EmpVO> getAll();
    
    public EmpVO EmpLogin(String empaccount,String emppassword);
    
}
