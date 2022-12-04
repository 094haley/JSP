package kr.co.farmstory2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.Sql;
import kr.co.farmstory2.vo.ArticleVO;
import kr.co.farmstory2.vo.FileVO;

public class ArticleDAO extends DBHelper {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleVO article) {
		int parent = 0;
		try {
			logger.info("insertArticle");
			conn = getConnection();
			
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, article.getCate());
			psmt.setString(2, article.getTitle());
			psmt.setString(3, article.getContent());
			psmt.setInt(4, article.getFname() == null ? 0 : 1);
			psmt.setString(5, article.getUid());
			psmt.setString(6, article.getRegip());
			
			psmt.executeUpdate();
			rs = stmt.executeQuery(Sql.SELECT_MAX_NO);
			
			// 작업확정
			conn.commit();
			
			if(rs.next()) {
				parent = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return parent;
	}
	
	public void insertFile(int parent, String newName, String fname) {
		try {
			logger.info("insertFile");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, parent);
			psmt.setString(2, newName);
			psmt.setString(3, fname);
			
			psmt.executeUpdate();		
			
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public ArticleVO insertComment(ArticleVO comment) {
		ArticleVO article = null;
		int result = 0;
		try {
			logger.info("insertComment");
			conn = getConnection();
			
			conn.setAutoCommit(false);
			PreparedStatement psmt1 = conn.prepareStatement(Sql.INSERT_COMMENT);
			PreparedStatement psmt2 = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_PLUS);
			stmt = conn.createStatement();
			
			psmt1.setInt(1, comment.getParent());
			psmt1.setString(2, comment.getContent());
			psmt1.setString(3, comment.getUid());
			psmt1.setString(4, comment.getRegip());
			
			psmt2.setInt(1, comment.getParent());
			
			result = psmt1.executeUpdate();
			psmt2.executeUpdate();
			rs = stmt.executeQuery(Sql.SELECT_COMMENT_LATEST);
			
			conn.commit();
			
			if(rs.next()) {
				article = new ArticleVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getString(2));
				article.setContent(rs.getString(6));
				article.setRdate(rs.getString(11).substring(2, 10));
				article.setNick(rs.getString(12));
			}
			
			psmt1.close();
			psmt2.close();
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return article;
	}
	
	public ArticleVO selectArticle(String no) {
		ArticleVO article = null;
		
		try {
			logger.info("selectArticle");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleVO();
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
				article.setFno(rs.getInt(12));
				article.setPno(rs.getInt(13));
				article.setNewName(rs.getString(14));
				article.setOriName(rs.getString(15));
				article.setDownload(rs.getInt(16));
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return article;
	}
	
	
	public List<ArticleVO> selectArticles(String cate, int start) {
		
		List<ArticleVO> articles = new ArrayList<>();
		
		try {
			logger.info("selectArticles");
			
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
	
	public FileVO selectFile(String parent) {
		FileVO file = null;
		try {
			logger.info("selectFile");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				file = new FileVO();
				file.setFno(rs.getInt(1));
				file.setParent(rs.getInt(2));
				file.setNewName(rs.getString(3));
				file.setOriName(rs.getString(4));
				file.setDownload(rs.getInt(5));
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return file;
	}
	 
	public List<ArticleVO> selectLatests(String cate1, String cate2, String cate3){
		
		List<ArticleVO> latests = new ArrayList<>();
		
		try {
			logger.info("selectLatests");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_LATESTS);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setString(3, cate3);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO article = new ArticleVO();
				article.setNo(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setRdate(rs.getString(3).substring(2, 10));
			
				latests.add(article);
			}
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return latests;
	}
	
	
	public synchronized List<ArticleVO> selectLatests(String cate) {
		
		List<ArticleVO> latests = new ArrayList<>();
		
		try {
			logger.info("selectLatests(String cate)");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_LATEST);
			psmt.setString(1, cate);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO vo = new ArticleVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setRdate(rs.getString(3).substring(2, 10));
				
				latests.add(vo);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return latests;
	}	
	
	public Map<String, Object> selectLatestArticles(){
		
		Map<String , Object> map = null;
		List<ArticleVO> croptalk1 = null;
		List<ArticleVO> croptalk2 = null;
		List<ArticleVO> croptalk3 = null;
		
		try {
			logger.info("selectLatestArticles");
			conn = getConnection();
			stmt = conn.createStatement();
			map = new HashMap<>();
			rs = stmt.executeQuery(Sql.SELECT_LATEST_ARTICLES);
			
			croptalk1 = new ArrayList<>();
			croptalk2 = new ArrayList<>();
			croptalk3 = new ArrayList<>();
			
			while(rs.next()) {
				ArticleVO vo = new ArticleVO();
				
				String cate = rs.getString("cate");
				vo.setNo(rs.getInt("no"));
				vo.setGroup(removeNumber(cate));
				vo.setCate(removeString(cate));
				vo.setTitle(rs.getString("title"));
				vo.setRdate(rs.getString("rdate").substring(2, 10));
				
				switch(cate) {
					
				case "croptalk1":
					croptalk1.add(vo); break;
					
				case "croptalk2":
					croptalk2.add(vo); break;
					
				case "croptalk3":
					croptalk3.add(vo); break;
				
				}
			}
			
			map.put("croptalk1", croptalk1);
			map.put("croptalk2", croptalk2);
			map.put("croptalk3", croptalk3);
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return map;
	}
	
	// 그룹과 카테고리를 가져오는 함수
	public String removeNumber(String str) {
		String match = "[0-9]";
		str = str.replaceAll(match, "");
		return str;
	}
	
	public String removeString(String str) {
		String match = "[^0-9]";
		str = str.replaceAll(match, "");
		return str;
	}
	
	
	public List<ArticleVO> selectComments(String parent) {
		List<ArticleVO> comments = new ArrayList<>();
		try {
			logger.info("selectComments");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				ArticleVO comment = new ArticleVO();
				comment.setNo(rs.getInt(1));
				comment.setParent(rs.getInt(2));
				comment.setComment(rs.getInt(3));
				comment.setCate(rs.getString(4));
				comment.setTitle(rs.getString(5));
				comment.setContent(rs.getString(6));
				comment.setFile(rs.getInt(7));
				comment.setHit(rs.getInt(8));
				comment.setUid(rs.getString(9));
				comment.setRegip(rs.getString(10));
				comment.setRdate(rs.getString(11));
				comment.setNick(rs.getString(12));
				
				comments.add(comment);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return comments;
	}
	
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
				psmt.setString(1, cate);
				psmt.setString(2, "%"+search+"%");
				psmt.setString(3, "%"+search+"%");
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
	
	public void updateArticle(String no, String title, String content) {
		try {
			logger.info("updateArticle");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, no);
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateArticleHit(String no) {
		
		try {
			logger.info("updateArticleHit");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateFileDownload(int fno) {
		try {
			logger.info("updateFileDownload");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_FILE_DOWNLOAD);
			psmt.setInt(1, fno);
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public int updateComment(String no, String content) {
		int result = 0;
		try {
			logger.info("updateComment");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
			psmt.setString(1, content);
			psmt.setString(2, no);
			
			result = psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public void deleteArticle(String no) {
		try {
			logger.info("deleteArticle");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public String deleteFile(String no) {
		String newName = null;
		
		try {
			logger.info("deleteFile");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement psmt1 = conn.prepareStatement(Sql.SELECT_FILE);
			PreparedStatement psmt2 = conn.prepareStatement(Sql.DELETE_FILE);
			
			psmt1.setString(1, no);
			psmt2.setString(1, no);
			
			rs = psmt1.executeQuery();
			psmt2.executeUpdate();
			
			conn.commit();
			
			if(rs.next()) {
				newName = rs.getString(3);
			}
			
			psmt1.close();
			psmt2.close();
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return newName;
	}
	
	public int deleteComment(String no, String parent) {
		int result = 0;
		try {
			logger.info("deleteComment");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement psmt1 = conn.prepareStatement(Sql.DELETE_COMMENT);
			PreparedStatement psmt2 = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_MINUS);
			psmt1.setString(1, no);
			psmt2.setString(1, parent);
			
			result = psmt1.executeUpdate();
			psmt2.executeUpdate();
			conn.commit();
			
			psmt1.close();
			psmt2.close();
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
}
