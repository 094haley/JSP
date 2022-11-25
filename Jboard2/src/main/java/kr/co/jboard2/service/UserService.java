package kr.co.jboard2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.vo.ArticleVO;
import kr.co.jboard2.vo.FileVO;
import kr.co.jboard2.vo.TermsVO;

public enum UserService {

	INSTANCE;
	private ArticleDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UserService() {
		dao = new ArticleDAO();
	}
	
	public int insertArticle(ArticleVO article) {
		return dao.insertArticle(article);
	}
	
	public void insertFile(int parent, String newName, String fname) {
		dao.insertFile(parent, newName, fname);
	}
	
	
	public ArticleVO selectArticle(String no){
		return dao.selectArticle(no);
	}
	
	public List<ArticleVO> selectArticles(int limitStart){
		return dao.selectArticles(limitStart);
	}
	
	public FileVO selectFile(String parent) {
		return dao.selectFile(parent);
	}
	
	
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	
	public void updateArticleHit(String no) {
		dao.updateArticleHit(no);
	}
	
	public void updateFileDownload(int fno) {
		dao.updateFileDownload(fno);
	}
	
	public void deleteUser() {}
	
}
