package kr.co.farmstory2.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.dao.UserDAO;
import kr.co.farmstory2.vo.TermsVO;
import kr.co.farmstory2.vo.UserVO;

public enum UserService {

	INSTANCE;
	private UserDAO dao;
	private UserService() {
		dao = new UserDAO();
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser(UserVO user) {
		dao.insertUser(user);
	}
	
	public UserVO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	
	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	
	public UserVO selectUserForFindId(String name, String email) {
		return dao.selectUserForFindId(name, email);
	}
	
	public UserVO selectUserForFindPw(String uid, String email) {
		return dao.selectUserForFindPw(uid, email);
	}
	
	public UserVO selectUserBySessId(String sessId) {
		return dao.selectUserBySessId(sessId);
	}
	
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	
	public void updateUserForSession(String uid, String sessId) {
		dao.updateUserForSession(uid, sessId);
	}
	
	public void updateUserForSessionLimitDate(String sessId) {
		dao.updateUserForSessionLimitDate(sessId);
	}
	
	public void updateUserForSessionOut(String uid) {
		dao.updateUserForSessionOut(uid);
	}
	
	public int updateUserPassword(String uid, String pass) {
		return dao.updateUserPassword(uid, pass);
	}
	
	public int[] sendEmailCode(String receiver) {
		
		// 인증 코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		
		// 기본 정보
		String sender = "3273bee@gmail.com";
		String password = "wbvaxxtpgjnmxtxt";
		
		String title = "Farmstory2 인증코드 입니다.";
		String content = "<h1>인증 코드는 "+code+" 입니다.</h1>";
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		
		});
		
		// 메일 발송
		Message message = new MimeMessage(session);
		int status = 0;
		
		try {
			logger.debug("메일 전송 시작...");
			message.setFrom(new InternetAddress(sender, "관리자", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=utf-8");
			Transport.send(message);
			
			status = 1;
		}catch(Exception e) {
			e.printStackTrace();
			status = 0;
			logger.error("메일 전송 실패...");
		}
		logger.debug("메일 전송 성공...");
		
		int result[] = {status, code};
		return result;
	}
	
	// 추가적인 서비스 로직
	
	public void sendResult(int result, HttpServletResponse resp) throws IOException {
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);

		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}
