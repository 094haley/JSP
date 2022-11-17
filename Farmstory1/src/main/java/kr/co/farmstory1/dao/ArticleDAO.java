package kr.co.farmstory1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory1.bean.ArticleBean;
import kr.co.farmstory1.bean.FileBean;
import kr.co.farmstory1.db.DBHelper;
import kr.co.farmstory1.db.Sql;

public class ArticleDAO extends DBHelper {

	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleBean ab) {
		
		int parent = 0;
		try {
			logger.info("insertArticle");
			
			conn = getConnection();
			// 트랜잭션 시작
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, ab.getCate());
			psmt.setString(2, ab.getTitle());
			psmt.setString(3, ab.getContent());
			psmt.setInt(4, ab.getFname() == null ? 0 : 1);
			psmt.setString(5, ab.getUid());
			psmt.setString(6, ab.getRegip());
			
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
	
	public ArticleBean insertComment(ArticleBean comment) {
		
		ArticleBean article = null;
		int result = 0;

		try {
			logger.info("insertComment");
			conn = getConnection();
			
			// 트랜잭션 댓글쓰기 + 부모게시글 댓글 수 업데이트 + 최신댓글 가져오기
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
			
			// 작업 확정
			conn.commit();
			
			if(rs.next()) {
				article = new ArticleBean();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
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
	
	public ArticleBean selectArticle(String no) {
		
		ArticleBean article = null;
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleBean();
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
	
	public List<ArticleBean> selectArticles(String cate, int start) {
		
		List<ArticleBean> articles = new ArrayList<>();
		
		try {
			logger.info("selectArticle");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleBean ab = new ArticleBean();
				ab.setNo(rs.getInt(1));
				ab.setParent(rs.getString(2));
				ab.setComment(rs.getInt(3));
				ab.setCate(rs.getString(4));
				ab.setTitle(rs.getString(5));
				ab.setContent(rs.getString(6));
				ab.setFile(rs.getInt(7));
				ab.setHit(rs.getInt(8));
				ab.setUid(rs.getString(9));
				ab.setRegip(rs.getString(10));
				ab.setRdate(rs.getString(11));
				ab.setNick(rs.getString(12));
				
				articles.add(ab);
			}
		
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return articles;
	}
	
	public int selectCountTotal(String cate) {
		
		int total = 0;
		
		try {
			logger.info("selectCountToTal");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}

		return total;
	}

	public List<ArticleBean> selectComments(String parent) {
		
		List<ArticleBean> comments = new ArrayList<>(); 
		
		try {
			logger.info("selectComments");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleBean comment = new ArticleBean();
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
	
	public FileBean selectFile(String parent) {
		FileBean fb = null;
		try {
		
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				fb = new FileBean();
				fb.setFno(rs.getInt(1));
				fb.setParent(rs.getInt(2));
				fb.setNewName(rs.getString(3));
				fb.setOriName(rs.getString(4));
				fb.setDownload(rs.getInt(5));
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}	
		return fb;
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
		
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			
			psmt.setString(1, no);
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
	
	public int deleteComment(String no, String parent) {
		int result = 0;
		try {
			logger.info("deleteComment");
			conn = getConnection();
			
			// 트랜잭션 댓글삭제 + 댓글개수 - 1
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
			conn.close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public String deleteFile(String no) {
		
		String newName = null;
		try {
			logger.info("deleteFile");
			conn = getConnection();
			
			// 트랜잭션
			conn.setAutoCommit(false);
			
			PreparedStatement psmt1 = conn.prepareStatement(Sql.SELECT_FILE);
			PreparedStatement psmt2 = conn.prepareStatement(Sql.DELETE_FILE);
			
			psmt1.setString(1, no);
			psmt2.setString(1, no);
			
			rs = psmt1.executeQuery();
			psmt2.executeUpdate();
			
			// 작업 확정
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
}
