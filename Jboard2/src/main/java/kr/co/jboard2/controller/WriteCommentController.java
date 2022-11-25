package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.vo.ArticleVO;

@WebServlet("/writecomment.do")
public class WriteCommentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.debug("here1...");
		
		String no = req.getParameter("no");
		String content = req.getParameter("content");
		String uid = req.getParameter("uid");
		String regip = req.getRemoteAddr();
		
		ArticleVO comment = new ArticleVO();
		comment.setParent(no);
		comment.setContent(content);
		comment.setUid(uid);
		comment.setRegip(regip);
		
		logger.debug("here2...");
		// 댓글 작성
		ArticleVO article = service.insertComment(comment);
		
		logger.debug("here3...");
		
		// 작성한 댓글을 json데이터로 보내기
		service.sendComment(article, resp);
		
		logger.debug("here4...");
		
	}

}
