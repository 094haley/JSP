package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.service.user.UserService;
import kr.co.jboard2.vo.UserVO;

@WebServlet("/user/findId.do")
public class FindIdController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;
	
	@Override
	public void init() throws ServletException {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		UserVO vo = new UserVO();
		vo.setName(name);
		vo.setEmail(email);
		
		int result = service.selectCheckUid(vo);
		*/
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findId.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
