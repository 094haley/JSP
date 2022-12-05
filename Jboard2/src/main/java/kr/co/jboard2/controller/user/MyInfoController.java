package kr.co.jboard2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.jboard2.service.UserService;
import kr.co.jboard2.vo.UserVO;

@WebServlet("/user/myInfo.do")
public class MyInfoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/myInfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid 	 = req.getParameter("uid");
		String nick  = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp 	 = req.getParameter("hp");
		String zip 	 = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		
		UserVO user = new UserVO();
		
		user.setUid(uid);
		user.setNick(nick);
		user.setEmail(email);
		user.setHp(hp);
		user.setZip(zip);
		user.setAddr1(addr1);
		user.setAddr2(addr2);
		
		
		service.updateUser(user);
		
		resp.sendRedirect("/Jboard2/list.do?result=203");
		
	}
}
