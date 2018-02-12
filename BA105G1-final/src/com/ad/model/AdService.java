package com.ad.model;

import java.sql.Timestamp;
import java.util.List;

public class AdService {

	private AdDAO_interface dao;

	public AdService() {
		dao = new AdJNDIImpl();
	}

	/**
	 * @param adVo
	 * @see com.ad.model.AdDAO_interface#insert(com.ad.model.AdVO)
	 */
	public AdVO addAd(String ad_no,String shop_no,Timestamp ad_idesta, Timestamp ad_end, byte[] ad_photo) {

		AdVO adVO = new AdVO();
		adVO.setShop_no(shop_no);
		adVO.setAd_idesta(ad_idesta);
		adVO.setAd_end(ad_end);
		adVO.setAd_photo(ad_photo);
		adVO.setAd_no(ad_no);

		dao.insert(adVO);
		return adVO;
	}

	/**
	 * @param adVo
	 * @see com.ad.model.AdDAO_interface#update(com.ad.model.AdVO)
	 */
	public AdVO update(String ad_no,String shop_no, Timestamp ad_idesta, Timestamp ad_start, Timestamp ad_end, Integer ad_exp,
			String ad_stat, byte[] ad_photo) {

		AdVO adVO = new AdVO();
		adVO.setShop_no(shop_no);
		adVO.setAd_idesta(ad_idesta);
		adVO.setAd_start(ad_start);
		adVO.setAd_end(ad_end);
		adVO.setAd_exp(ad_exp);
		adVO.setAd_stat(ad_stat);
		adVO.setAd_photo(ad_photo);
		adVO.setAd_no(ad_no);
		dao.update(adVO);
		
		return adVO;
	}

	/**
	 * @param ad_no
	 * @see com.ad.model.AdDAO_interface#delete(java.lang.String)
	 */
	public void deleteAd(String ad_no) {
		dao.delete(ad_no);
	}

	/**
	 * @param ad_no
	 * @return
	 * @see com.ad.model.AdDAO_interface#findByPrimaryKey(java.lang.String)
	 */
	public AdVO findByPrimaryKey(String ad_no) {
		return dao.findByPrimaryKey(ad_no);
	}

	/**
	 * @return
	 * @see com.ad.model.AdDAO_interface#getAll()
	 */
	public List<AdVO> getAll() {
		return dao.getAll();
	}

	

	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public List<AdVO> findByAd_stat(String ad_stat){
		return dao.findByAd_stat(ad_stat);
	}
	public void updateAd_stat(AdVO adVo){
		dao.updateAd_stat(adVo);
	}
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
