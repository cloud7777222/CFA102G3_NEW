package com.mempersonalpage.model;

import java.util.List;

//�s�W�׬d����H��k

public interface MemPersonalPageDAO_interface {
	
	public void insert(MemPersonalPageVO memPersonalPageVO);
	public void update(MemPersonalPageVO memPersonalPageVO);
	public void delete(Integer postNo); //��PK
	public MemPersonalPageVO findByPrimaryKey(Integer postNo); //��PK, �|�^�Ǥ@���K�媫��
	public List<MemPersonalPageVO> getAll();
	
	
}
