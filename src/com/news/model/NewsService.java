package com.news.model;

import java.util.List;

public class NewsService {

	private NewsDAO_interface dao;

	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO addNews(String newsTitle, String news, byte[] newsPic, Integer newsState) {

		NewsVO newsVO = new NewsVO();

		newsVO.setNewsTitle(newsTitle);
		newsVO.setNews(news);
		newsVO.setNewsPic(newsPic);
		newsVO.setNewsState(newsState);
		dao.insert(newsVO);

		return newsVO;
	}

	public NewsVO updateNews(Integer newsNo, String newsTitle, String news, byte[] newsPic, Integer newsState) {

		NewsVO newsVO = new NewsVO();
		
		newsVO.setNewsNo(newsNo);
		newsVO.setNewsTitle(newsTitle);
		newsVO.setNews(news);
		newsVO.setNewsPic(newsPic);
		newsVO.setNewsState(newsState);
		dao.update(newsVO);

		return newsVO;
	}

	public void deleteNews(Integer newsNo) {
		dao.delete(newsNo);
	}

	public NewsVO getOneNews(Integer newsNo) {
		return dao.findByPrimaryKey(newsNo);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}
}
