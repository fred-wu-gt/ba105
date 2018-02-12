package com.fru.pri.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.commodity.model.CommodityVO;

public class Fru_priService {
	
	private Fru_priDAO_Interface dao;
	
	public Fru_priService(){
		dao = new Fru_priDAO();
	}
	
	
	public Fru_priVO addFru_pri(String fp_no, String fru_no, String fp_name, Timestamp fp_time, Double fp_pri){
		Fru_priVO fru_priVO = new Fru_priVO();
		
		fru_priVO.setFru_no(fru_no);
		fru_priVO.setFp_name(fp_name);
		fru_priVO.setFp_pri(fp_pri);

		dao.add(fru_priVO);
//		System.out.println("=================新增成功=================");
	
		return fru_priVO;
		
	}
	
	public Fru_priVO updateFru_pri(String fp_no, String fru_no, String fp_name, Double fp_pri){
		Fru_priVO fru_priVO = new Fru_priVO();
		
		fru_priVO.setFp_no(fp_no);
		fru_priVO.setFru_no(fru_no);
		fru_priVO.setFp_name(fp_name);
		fru_priVO.setFp_pri(fp_pri);
		
		
		dao.update(fru_priVO);
//		System.out.println("================修改成功==================");
		
		return fru_priVO;
		
	}
	
	public void deletFru_pri(String fp_no){
		 dao.delete(fp_no);
	}
	
	
	public Fru_priVO getoneFru_pri(String fp_no){
		return dao.findByFp_no(fp_no);
	}
	
	public List<Fru_priVO> getAll(){
		return dao.getAll();
	}
	
	
	public  Fru_priVO getoneFru_name(String fp_name){
		return dao.findByFp_name(fp_name);	
	}
	
	public List<Fru_priVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	
//	public Fru_priVO insert2Fru_pri(String fp_no, String fru_no, String fp_name, Timestamp fp_time, String fp_pri){
//		Fru_priVO mem3 = new Fru_priVO();
//		
//		mem3.setFru_no(fru_no);
//		mem3.setFp_name(fp_name);
//		mem3.setFp_pri(fp_pri);
//
//		dao.add(mem3);
//		System.out.println("=================新增成功=================");
//	
//		return mem3;
//		
//	}
	
	
	
	
}
