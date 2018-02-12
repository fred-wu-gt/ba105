package com.hotsales.model;

import java.util.List;

public class HotsalesService {
	private HotsalesDAO_interface dao  ;
	public HotsalesService(){
		dao = new HotsalesJNDIDAO();
	}
	
	public List<String> findByOd_quan(){
		
		return dao.findByOd_quan();
		
	}
	

}
