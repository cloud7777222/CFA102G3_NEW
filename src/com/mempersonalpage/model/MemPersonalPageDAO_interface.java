package com.mempersonalpage.model;

import java.util.List;

//新增修查的抽象方法

public interface MemPersonalPageDAO_interface {
	
	public void insert(MemPersonalPageVO memPersonalPageVO);
	public void update(MemPersonalPageVO memPersonalPageVO);
	public void delete(Integer postNo); //用PK
	public MemPersonalPageVO findByPrimaryKey(Integer postNo); //用PK, 會回傳一筆貼文物件
	public List<MemPersonalPageVO> getAll();
	
	
}
