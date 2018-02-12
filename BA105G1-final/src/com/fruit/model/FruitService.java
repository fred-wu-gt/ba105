package com.fruit.model;

import java.util.List;

public class FruitService {
	private FruitDAO_Interface dao;
	public FruitService(){
		dao=new FruitDAO();
	}
	public FruitVO addFruit(String fru_name, String fru_kno, String fru_pho_base64){
		FruitVO fruitVO=new FruitVO();
		fruitVO.setFru_name(fru_name);
		fruitVO.setFru_kno(fru_kno);
		fruitVO.setFru_pho_base64(fru_pho_base64);
		dao.add(fruitVO);
		return fruitVO;
	}
	public FruitVO updateFruit(String fru_no, String fru_name, String fru_kno, String fru_pho_base64){
		FruitVO fruitVO=new FruitVO();
		fruitVO.setFru_no(fru_no);
		fruitVO.setFru_name(fru_name);
		fruitVO.setFru_kno(fru_kno);
		fruitVO.setFru_pho_base64(fru_pho_base64);
		dao.update(fruitVO);
		return fruitVO;
	}
	public void deleteFruit(String fru_no){
		dao.delete(fru_no);
	}
	public FruitVO getOneFruit(String fru_no){
		return dao.findByFru_no(fru_no);
	}
	public FruitVO findByFru_name(String fru_name){
		return dao.findByFru_name(fru_name);
	}
	public List<FruitVO> getAll(){
		return dao.getAll();
	}

}
