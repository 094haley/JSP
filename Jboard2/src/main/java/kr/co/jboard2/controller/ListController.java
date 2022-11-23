package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.service.UserService;
import kr.co.jboard2.vo.ArticleVO;
import kr.co.jboard2.vo.PageVO;


@WebServlet("/list.do")
public class ListController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;
	
	@Override
	public void init() throws ServletException {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("pg");
		
		// 게시물 목록 처리 관련 변수 선언
		int limitStart = 0;
		int currentPage = 1;
		int total = 0;
		int lastPageNum = 0;
		int pageGroupCurrent = 1;
		int pageGroupStart = 1;
		int pageGroupEnd = 0;
		int pageStartNum = 0;
		
		// 전체 게시물 갯수 구하기
		total = service.selectCountTotal();
		
		// 페이지 마지막 번호 계산
		if(total % 10 == 0) {
			lastPageNum = (total / 10);
		}else {
			lastPageNum = (total / 10) + 1;
		}
		
		// 현재 페이지 게시물 limit 시작값 계산
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		
		limitStart = (currentPage -1) * 10;
		
		// 페이지 그룹 계산
		pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
		pageGroupStart = (pageGroupCurrent -1) * 10 + 1;
		pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		// 페이지 시작 번호 계산
		pageStartNum = total - limitStart;
		
		PageVO vo = new PageVO();
		vo.setLimitStart(limitStart);
		vo.setCurrentPage(currentPage);
		vo.setTotal(total);
		vo.setLastPageNum(lastPageNum);
		vo.setPageGroupCurrent(pageGroupCurrent);
		vo.setPageGroupStart(pageGroupStart);
		vo.setPageGroupEnd(pageGroupEnd);
		vo.setPageStartNum(pageStartNum);
		
		// 페이지 관련 변수값 넘기기
		req.setAttribute("vo", vo);
		
		// 현재 페이지 게시물 가져오기
		List<ArticleVO> articles = service.selectArticles(limitStart);
		req.setAttribute("articles", articles);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("./list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
