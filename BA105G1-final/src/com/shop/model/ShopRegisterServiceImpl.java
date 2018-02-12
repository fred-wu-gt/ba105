package com.shop.model;

public class ShopRegisterServiceImpl{
	
	ShopDAO_interface dao = null;

	public ShopRegisterServiceImpl() {
		dao = new ShopJNDIDAOImpl();
	}
	
	
}
