package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.Sql;
import kr.co.farmstory2.vo.ArticleVO;

public class ArticleDAO extends DBHelper {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertArticle() {}
	
	public void insertFile() {}
	
	public void insertComment() {}
	
	public void selectArticle() {}
	
	public List<ArticleVO> selectArticles(String cate, int start) {
		
		List<ArticleVO> articles = new ArrayList<>();
		
		try {
			logger.info("selectArticle");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO article = new ArticleVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getString(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				article.setNick(rs.getString(12));
				
				articles.add(article);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	
	public List<ArticleVO> selectArticlesByKeyWord(String keyword, String cate, int start) {
		
		List<ArticleVO> articles = new ArrayList<>();
		try {
			logger.info("selectArticlesByKeyWord");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES_BY_KEYWORD);
			psmt.setString(1, cate);
			psmt.setString(2, "%"+keyword+"%");
			psmt.setString(3, "%"+keyword+"%");
			psmt.setInt(4, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO article = new ArticleVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				article.setNick(rs.getString(12));
				
				articles.add(article);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	
	public void selectLatests() {}
	
	public void selectComments() {}
	
	public int selectCountTotal(String search, String cate) {
		int total = 0;
		
		try {
			logger.info("selectCountTotal");
			conn = getConnection();
			
			if(search == null) {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate);
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					total = rs.getInt(1);
				}
				
			}else {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
				psmt.setString(2, "%"+search+"%");
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					total = rs.getInt(1);
				}
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	public void updateArticle() {}
	
	public void updateArticleHit() {}
	
	public void updateFileDownload() {}
	
	public void updateComment() {}
	
	public void deleteArticle() {}
	
	public void deleteFile() {}
	
	public void deleteComment() {}
	
}
