package com.expertgenre.model;
import java.util.List;


public class ExpertgenreService {
	private ExpertgenreDAO_interface dao;

	public ExpertgenreService() {
		dao = new ExpertgenreDAO();
	}
	public ExpertgenreVO addExpertgenre(Integer expertGenreNo,String exGenreName) {
		
		ExpertgenreVO expertgenreVO = new ExpertgenreVO();
		expertgenreVO.setExpertGenreNo(expertGenreNo);
		expertgenreVO.setExGenreName(exGenreName);
		dao.insert(expertgenreVO);
		return expertgenreVO;
	}


	public ExpertgenreVO updateExpertgenre(Integer expertGenreNo,String exGenreName) {

		

		ExpertgenreVO expertgenreVO = new ExpertgenreVO();
		expertgenreVO.setExpertGenreNo(expertGenreNo);
		expertgenreVO.setExGenreName(exGenreName);
		dao.update(expertgenreVO);
		return expertgenreVO;
}
    public void deleteExpertgenre(Integer expertGenreNo) {
		
		dao.delete(expertGenreNo);
	}

	public ExpertgenreVO getOneExpert(Integer expertGenreNo) {
		return dao.findByPrimaryKey(expertGenreNo);
	}

	public List<ExpertgenreVO> getAll() {
		return dao.getAll();
	}
}
