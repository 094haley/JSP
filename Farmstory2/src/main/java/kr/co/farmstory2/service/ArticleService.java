package kr.co.farmstory2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.vo.ArticleVO;

public enum ArticleService {
	
	INSTANCE;
	private ArticleDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleService() {
		dao = new ArticleDAO();
	}
	
	public void insertArticle() {}
	
	public void insertFile() {}
	
	public void insertComment() {}
	
	public void selectArticle() {}
	
	public List<ArticleVO> selectArticles(String cate, int start) {
		return dao.selectArticles(cate, start);
	}
	
	public List<ArticleVO> selectArticlesByKeyWord(String keyword, String cate, int start) {
		return dao.selectArticlesByKeyWord(keyword, cate, start);
	}
	
	public void selectLatests() {}
	
	public void selectComments() {}
	
	public int selectCountTotal(String search, String cate) {
		return dao.selectCountTotal(search, cate);
	}
	
	public void updateArticle() {}
	
	public void updateArticleHit() {}
	
	public void updateFileDownload() {}
	
	public void updateComment() {}
	
	public void deleteArticle() {}
	
	public void deleteFile() {}
	
	public void deleteComment() {}
	
	
	// 추가적인 서비스 로직
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		
		int pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (pageGroupCurrent -1) * 10 + 1;
		int pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int[] pageGroup = {pageGroupStart, pageGroupEnd};
		
		return pageGroup;
		
	}
	
	public int getLastPageNum(int total) {
		
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = (total / 10);
		}else {
			lastPageNum = (total / 10) + 1;
		}
		
		return lastPageNum;
	}
	
	public int getStartNum(int currentPage) {
		
		return (currentPage -1) * 10;
	}	
	
}
