package kr.co.farmstory2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.Sql;
import kr.co.farmstory2.vo.TermsVO;
import kr.co.farmstory2.vo.UserVO;

public class UserDAO extends DBHelper {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser(UserVO user) {
		
		try {
			logger.info("insertUser");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, user.getUid());
			psmt.setString(2, user.getPass());
			psmt.setString(3, user.getName());
			psmt.setString(4, user.getNick());
			psmt.setString(5, user.getEmail());
			psmt.setString(6, user.getHp());
			psmt.setString(7, user.getZip());
			psmt.setString(8, user.getAddr1());
			psmt.setString(9, user.getAddr2());
			psmt.setString(10, user.getRegip());
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void selectUser() {}
	
	public TermsVO selectTerms() {
		TermsVO vo = null;
		try {
			logger.info("selectTerms");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_TERMS);
			
			if(rs.next()) {
				vo = new TermsVO();
				vo.setTerms(rs.getString(1));
				vo.setPrivacy(rs.getString(2));
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public void selectUserForFindId() {}
	
	public void selectUserForFindPw() {}
	
	public void selectUserBySessId() {}
	
	public int selectCountUid(String uid) {
		int result = 0;
		try {
			logger.info("selectCountUid");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCountNick(String nick) {
		int result = 0;
		try {
			logger.info("selectCountNick");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCountEmail(String email) {
		int result = 0;
		try {
			logger.info("selectCountEmail");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCountHp(String hp) {
		int result = 0;
		try {
			logger.info("selectCountHp");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public void updateUserForSession() {}
	
	public void updateUserForSessionLimitDate() {}
	
	public void updateUserForSessionOut() {}
	
	public void updateUserPassword() {}
	
	public void sendEmailCode() {}

}
