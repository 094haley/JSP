package kr.co.jboard1.dao;

import kr.co.jboard1.bean.UserBean;
import kr.co.jboard1.db.DBHelper;
import kr.co.jboard1.db.Sql;

public class UserDAO extends DBHelper {

	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
	public UserDAO() {}
	
	// 기본 CRUD
	public void insertUser(UserBean ub) {
		
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, ub.getUid());
			psmt.setString(2, ub.getPass());
			psmt.setString(3, ub.getName());
			psmt.setString(4, ub.getNick());
			psmt.setString(5, ub.getEmail());
			psmt.setString(6, ub.getHp());
			psmt.setString(7, ub.getZip());
			psmt.setString(8, ub.getAddr1());
			psmt.setString(9, ub.getAddr2());
			psmt.setString(10, ub.getRegip());
			psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void selectUser() {}
	public void selectUsers() {}
	public void updateUser() {}
	public void deleteUser() {}

}
