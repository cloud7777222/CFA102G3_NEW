package com.dateappoorder.model;

import java.util.*;

public interface DateappoorderDAO_interface {
          public void insert(DateappoorderVO dateappoorderVO);
          public void update(DateappoorderVO dateappoorderVO);
//          public void delete(Integer dateOrderNono);
          public DateappoorderVO findByPrimaryKey(Integer dateOrderNo);
          public List<DateappoorderVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
