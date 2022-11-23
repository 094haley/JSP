package kr.co.jboard2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.vo.ArticleVO;
import kr.co.jboard2.vo.TermsVO;

public enum UserService {

	INSTANCE;
	private ArticleDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UserService() {
		dao = new ArticleDAO();
	}
	
	public void insertUser() {}
	public void selectUser() {}
	public void selectUsers() {}
	
	public void selectArticle(){}
	public List<ArticleVO> selectArticles(int limitStart){
		return dao.selectArticles(limitStart);
	}
	
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	
	public void updatetUser() {}
	public void deleteUser() {}
	
}
