package kr.co.jboard2.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.vo.FileVO;

@WebServlet("/downloadfile.do")
public class DownloadFileController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	public void init() throws ServletException {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String parent = req.getParameter("parent");
		
		// 파일 정보 가져오기 & 파일 다운로드 카운트 + 1
		FileVO vo = service.selectFile(parent);
		service.updateFileDownload(vo.getFno());
		
		// 파일 다운로드
		service.downloadFile(req, resp, vo);
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
