package com.prom.model;

import java.util.List;

public interface PromDAO_interface {

	  public void insert(PromVO promVO);
      public void update(PromVO promVO);
      public void delete(String prom_no);
      public PromVO findByPrimaryKey(String shop_no);
      public List<PromVO> getAll();
	
}
