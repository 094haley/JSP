package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.vo.ArticleVO;

@WebServlet("/board/write.do")
public class WriteController extends HttpServlet {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	public void init() throws ServletException {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.debug("here1");
		
		// 파일 업로드
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/file");
		
		MultipartRequest mr = service.uploadFile(req, path);
		
		logger.debug("here2");	
		
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String uid = req.getParameter("uid");
		String title 	= mr.getParameter("title");
		String content 	= mr.getParameter("content");
		String fname 	= mr.getFilesystemName("fname");
		String regip 	= req.getRemoteAddr();
		
		ArticleVO article = new ArticleVO();
		article.setUid(uid);
		article.setCate(cate);
		article.setTitle(title);
		article.setContent(content);
		article.setFname(fname);
		article.setRegip(regip);
		
		logger.debug("here3");
		logger.debug(""+uid);
		logger.debug(""+title);
		logger.debug(""+fname);

		
		// 글 등록
		int parent = service.insertArticle(article);
		
		logger.debug("here4");
		
		// 파일을 첨부했으면
		if(fname != null) {
			// 파일명 수정
			String newName = service.renameFile(fname, uid, path);
			logger.debug("here5");
			
			// 파일 테이블 Insert
			service.insertFile(parent, newName, fname);
			
			logger.debug("here6");
		}
		
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);
	}
	
}
