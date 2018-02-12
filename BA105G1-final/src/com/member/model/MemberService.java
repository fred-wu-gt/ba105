package com.member.model;

import java.sql.Date;
import java.util.List;

public class MemberService {

	private MemberDAO_Interface dao;
	
	public MemberService(){
		dao = new JNDIMemberDAO();
	}
	
	
	// =============宸鈞開始=====================
		// 修改會員點數的方法
		public void updateMem_val(String mem_id, String mem_val) {

			MemberVO memVO = new MemberVO();
			memVO.setMem_id(mem_id);
			memVO.setMem_val(mem_val);

			dao.updateMem_val(memVO);

		}

	// =============宸鈞結束=====================
		
	
	
	public MemberVO addMember(String mem_id,String mem_psw,String mem_name,String mem_gen
			,java.sql.Date mem_bir,String mem_email,String mem_phone,String mem_loc,byte[] mem_photo,String mem_photo_base64
			,String mem_stat,String mem_poin,String mem_val,String check){
		MemberVO memVO = new MemberVO();
		
		memVO.setMem_id(mem_id);
		memVO.setMem_psw(mem_psw);
		memVO.setMem_name(mem_name);
		memVO.setMem_gen(mem_gen);
		memVO.setMem_bir(mem_bir);
		memVO.setMem_email(mem_email);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_loc(mem_loc);
		memVO.setMem_photo(mem_photo);
		memVO.setMem_photo_base64(mem_photo_base64);
		memVO.setMem_stat(mem_stat);
		memVO.setMem_poin(mem_poin);
		memVO.setMem_val(mem_val);
		memVO.setCheck(check);
		dao.add(memVO);
		
		return memVO;
		
	}
	
	public MemberVO updateMember(String mem_no,String mem_id ,String mem_psw, String mem_name, String mem_gen, Date mem_bir,
			String mem_email, String mem_phone, String mem_loc,byte[] mem_photo,String mem_photo_base64, String mem_stat, String mem_poin,
			String mem_val){
			
			MemberVO memVO = new MemberVO();
			
			memVO.setMem_no(mem_no);
			memVO.setMem_id(mem_id);
			memVO.setMem_psw(mem_psw);
			memVO.setMem_name(mem_name);
			memVO.setMem_gen(mem_gen);
			memVO.setMem_bir(mem_bir);
			memVO.setMem_email(mem_email);
			memVO.setMem_phone(mem_phone);
			memVO.setMem_loc(mem_loc);
			memVO.setMem_photo(mem_photo);
			memVO.setMem_photo_base64(mem_photo_base64);
			memVO.setMem_stat(mem_stat);
			memVO.setMem_poin(mem_poin);
			memVO.setMem_val(mem_val);
			
			
			dao.update(memVO);
			
			System.out.println("================修改成功==================");
		
		    return memVO;
		
		
	}
	           
	public void deleteMember(String mem_id){
			dao.delete(mem_id);
	}
	
	public MemberVO getOneMember(String mem_id){
		return dao.findByMem_id(mem_id);
	}
	
	public MemberVO findByMem_no(String mem_no){
		return dao.findByMem_no(mem_no);
	}
	
	
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}

	

	
	
	
	
	
	
}
