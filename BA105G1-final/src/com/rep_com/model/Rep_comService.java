package com.rep_com.model;

import java.sql.Timestamp;
import java.util.List;

import com.ord_det.model.Ord_detVO;

public class Rep_comService {
	private Rep_comDAO_interface dao ;
	public Rep_comService(){
		 dao = new Rep_comJNDIDAO();
	}

	public Rep_comVO addRep_com(String com_no, String mem_no,String rc_cuz,
	 String rc_stat,String rc_txt){
		Rep_comVO rep_comVO = new Rep_comVO();
		rep_comVO.setCom_no(com_no);
		rep_comVO.setMem_no(mem_no);
		rep_comVO.setRc_cuz(rc_cuz);
		rep_comVO.setRc_stat(rc_stat);
		rep_comVO.setRc_txt(rc_txt);

		String rc_no = dao.insert(rep_comVO);
		rep_comVO.setRc_no(rc_no);

		return rep_comVO;

	}
	
	public Rep_comVO updateRep_comStat(String rc_stat, String rc_no) {
		Rep_comVO rep_comVO = new Rep_comVO();
		rep_comVO.setRc_stat(rc_stat);
		rep_comVO.setRc_no(rc_no);

		dao.updateRep_comStat(rep_comVO);
		return rep_comVO;
	}
	
	
	public List<Rep_comVO> getAll(){
		return dao.getAll();
		
	}
	
	public Rep_comVO getOneRep_com(String rc_no) {
		return dao.findByPrimaryKey(rc_no);
	}
	
	
	

}
