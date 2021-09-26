package com.memTime.model;

import java.sql.Date;
import java.util.List;

public class AdService {

	private AdDAO_interface dao;

	public AdService() {
		dao = new AdDAO();
	}

	public AdVO addAd(String adTitle, String ad, String adPic1, String adPic2, String adPic3, Integer adState, Date postTime, Date deadline) {

		AdVO adVO = new AdVO();

		adVO.setAdTitle(adTitle);
		adVO.setAd(ad);
		adVO.setAdPic1(adPic1);
		adVO.setAdPic2(adPic2);
		adVO.setAdPic3(adPic3);
		adVO.setAdState(adState);
		adVO.setPostTime(postTime);
		adVO.setDeadline(deadline);
		dao.insert(adVO);

		return adVO;
	}

	public AdVO updateAd(Integer adNo, String adTitle, String ad, String adPic1, String adPic2, String adPic3, Integer adState, Date postTime, Date deadline) {

		AdVO adVO = new AdVO();
		
		adVO.setAdNo(adNo);
		adVO.setAdTitle(adTitle);
		adVO.setAd(ad);
		adVO.setAdPic1(adPic1);
		adVO.setAdPic2(adPic2);
		adVO.setAdPic3(adPic3);
		adVO.setAdState(adState);
		adVO.setPostTime(postTime);
		adVO.setDeadline(deadline);
		dao.update(adVO);

		return adVO;
	}

	public void deleteAd(Integer adNo) {
		dao.delete(adNo);
	}

	public AdVO getOneAd(Integer adNo) {
		return dao.findByPrimaryKey(adNo);
	}

	public List<AdVO> getAll() {
		return dao.getAll();
	}
	
	public List<AdVO> getAllByKeyword(String keyword) {
		return dao.findAllByKeyword(keyword);
	}
}
