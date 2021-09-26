package com.ad.model;

import java.util.*;

public interface AdDAO_interface {
          public void insert(AdVO adVO);
          public void update(AdVO adVO);
          public void delete(Integer adNo);
          public AdVO findByPrimaryKey(Integer adNo);
          public List<AdVO> getAll();
          public List<AdVO> findAllByKeyword(String keyword);
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<AdVO> getAll(Map<String, String[]> map);
}
