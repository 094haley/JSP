package kr.co.farmstory2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.vo.ArticleVO;
import kr.co.farmstory2.vo.FileVO;

public enum ArticleService {
	
	INSTANCE;
	private ArticleDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleService() {
		dao = new ArticleDAO();
	}
	
	public int insertArticle(ArticleVO article) {
		return dao.insertArticle(article);
	}
	
	public void insertFile(int parent, String newName, String fname) {
		dao.insertFile(parent, newName, fname);
	}
	
	public ArticleVO insertComment(ArticleVO comment) {
		return dao.insertComment(comment);
	}
	
	public ArticleVO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	
	public List<ArticleVO> selectArticles(String cate, int start) {
		return dao.selectArticles(cate, start);
	}
	
	public List<ArticleVO> selectArticlesByKeyWord(String keyword, String cate, int start) {
		return dao.selectArticlesByKeyWord(keyword, cate, start);
	}
	
	public FileVO selectFile(String parent) {
		return dao.selectFile(parent);
	}
	
	public List<ArticleVO> selectLatests(String cate1, String cate2, String cate3) {
		return dao.selectLatests(cate1, cate2, cate3);
	}
	
	public List<ArticleVO> selectLatests(String cate) {
		return dao.selectLatests(cate);
	}
	
	public Map<String, Object> selectLatestArticles(){
		return dao.selectLatestArticles();
	}
	
	public List<ArticleVO> selectComments(String no) {
		return dao.selectComments(no);
	}
	
	public int selectCountTotal(String search, String cate) {
		return dao.selectCountTotal(search, cate);
	}
	
	public void updateArticle(String no, String title, String content) {
		dao.updateArticle(no, title, content);
	}
	
	public void updateArticleHit(String no) {
		dao.updateArticleHit(no);
	}
	
	public void updateFileDownload(int fno) {
		dao.updateFileDownload(fno);
	}
	
	public int updateComment(String no, String content) {
		return dao.updateComment(no, content);
	}
	
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	
	public String deleteFile(String no) {
		return dao.deleteFile(no);
	}
	
	public int deleteComment(String no, String parent) {
		return dao.deleteComment(no, parent);
	}
	
	// 추가적인 서비스 로직
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		
		int pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (pageGroupCurrent -1) * 10 + 1;
		int pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int[] pageGroup = {pageGroupStart, pageGroupEnd};
		
		return pageGroup;
		
	}
	
	public int getLastPageNum(int total) {
		
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = (total / 10);
		}else {
			lastPageNum = (total / 10) + 1;
		}
		
		return lastPageNum;
	}
	
	public int getStartNum(int currentPage) {
		
		return (currentPage -1) * 10;
	}	
	
	public MultipartRequest uploadFile(HttpServletRequest req, String path) throws IOException {

		File targetDir = new File(path);
		if(!targetDir.exists()) {
			targetDir.mkdirs();
		}
		
		int maxSize = 1024 * 1024 * 10;
		return new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	}
	
	public String renameFile(String fname, String uid, String path) {
		
		int i = fname.lastIndexOf(".");
		String ext = fname.substring(i);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_");
		String now = sdf.format(new Date());
		String newName = now+uid+ext;
		
		File f1 = new File(path+"/"+fname);
		File f2 = new File(path+"/"+newName);
		
		f1.renameTo(f2);
		
		return newName;
	}
	
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileVO vo) throws IOException {
		
		// 파일 다운로드를 위한 response 헤더 수정
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(vo.getOriName(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		// response 객체로 파일 스트림 작업
		ServletContext application = req.getServletContext();
		
		String savePath = application.getRealPath("/file");
		File file = new File(savePath+"/"+vo.getNewName());
		
		// 출력 스트림 초기화
		ServletOutputStream out = resp.getOutputStream();
		out.flush();
	
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		while(true) {
			
			int data = bis.read();
			
			if(data == -1) {
				break;
			}
			bos.write(data);
		}
		
		bos.close();
		bis.close();
	}
	
	public void deleteDirectoryFile(String fileName, HttpServletRequest req) {
		
		if(fileName != null) {
			ServletContext ctx = req.getServletContext();
			String path = ctx.getRealPath("/file");
			
			File file = new File(path, fileName);
			if(file.exists()) {
				file.delete();
			}
		}
	}
	
	public void sendResult(int result, HttpServletResponse resp) throws IOException {
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
	}
	
	public void sendComment(ArticleVO article, HttpServletResponse resp) throws IOException {
		
		JsonObject json = new JsonObject();
		json.addProperty("result", 1);
		json.addProperty("no", article.getNo());
		json.addProperty("parent", article.getParent());
		json.addProperty("nick", article.getNick());
		json.addProperty("date", article.getRdate());
		json.addProperty("content", article.getContent());
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());

	}
}
