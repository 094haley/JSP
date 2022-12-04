package kr.co.farmstory2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.vo.ArticleVO;

@WebServlet("/index.do")
public class IndexController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	public void init() throws ServletException {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		// 최신글 가져오기
		List<ArticleVO> latests = service.selectLatests("grow", "school", "story");
		
		// 오류방지를 위한 임시코드
		if(latests.size() < 15) {
			ArticleVO article = new ArticleVO();
			article.setNo(0);
			article.setTitle("무제");
			article.setRdate("00-00-00");
			
			for(int i=0; i<15; i++) {
				latests.add(article);
			}
		}
		
		req.setAttribute("latests", latests);
		*/
		
		Map<String, Object> map = service.selectLatestArticles();
		req.setAttribute("map", map);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
