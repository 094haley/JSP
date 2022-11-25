<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./_header.jsp"/>
<script>
	$(document).ready(function(){
		
		// 글 삭제
		
		// 댓글 삭제
		
		// 댓글 수정
		
		// 댓글 작성
		$('.commentForm > form').submit(function(){
			
			let no 		 = $(this).children('input[name=no]').val();
			let uid 	 = $(this).children('input[name=uid]').val();
			let textarea = $(this).children('textarea[name=content]');
			let content  = textarea.val();
			
			if(content == ''){
				alert('댓글을 작성하세요');
				return false;
			}
			
			let jsonData = {
					"no" : no,
					"uid" : uid,
					"content" : content,	
			}
			
			$.ajax({
				url: '/Jboard2/writecomment.do',
				method: 'POST',
				data: jsonData,
				dataType: 'json',
				success: function(data) {
					
					if(data.result > 0){
						
						let article = "<article>";
							article += "<span class='nick'>"+data.nick+"</span>";
							article += "<span class='date'>"+data.date+"</span>";
							article += "<p class='content'>"+data.content+"</p>";
							article += "<div>";
							article += "<a href='#' class='remove' data-no='"+data.no+"' data-parent=''"+data.parent+">삭제</a>";							
							article += "<a href='#' class='modify' data-no='"+data.no+"'>수정</a>";							
							article += "</div>";
							article += "</article>";
							
							$('.commentList > .empty').hide();
							$('.commentList').append(article);
							textarea.val('');
					}
				}
			});
			return false;
		});
		
	});
</script>
        <main id="board">
            <section class="view">                
                <table border="0">
                    <caption>글보기</caption>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" name="title" value="${article.title}" readonly/></td>
                    </tr>
                    <c:if test="${article.file > 0}">
                    <tr>
                        <th>첨부파일</th>
                        <td><a href="/Jboard2/downloadfile.do?parent=${article.no}">${article.oriName}</a>&nbsp;<span>${article.download}</span>회 다운로드</td>
                    </tr>
                    </c:if>
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="content" readonly>${article.content}</textarea>
                        </td>
                    </tr>                    
                </table>
                
                <div>
                    <a href="/Jboard2/delete.do?no=${article.no}&pg=${pg}" class="btn btnRemove">삭제</a>
                    <a href="/Jboard2/modify.do?no=${article.no}&pg=${pg}" class="btn btnModify">수정</a>
                    <a href="/Jboard2/list.do?pg=${pg}" class="btn btnList">목록</a>
                </div>

                <!-- 댓글목록 -->
                <section class="commentList">
                    <h3>댓글목록</h3>                   
					<c:forEach var="comment" items="${comments}">
                    <article>
                        <span class="nick">${comment.nick}</span>
                        <span class="date">${(comment.rdate).substring(2, 10)}</span>
                        <p class="content">${comment.content}</p>                        
                        <div>
                            <a href="#" class="remove" data-no="${comment.no}" data-parent="${comment.parent}">삭제</a>
                            <a href="#" class="modify" data-no="${comment.no}">수정</a>
                        </div>
                    </article>
					</c:forEach>
					<c:if test="${comments.size() == 0}">
                    <p class="empty">등록된 댓글이 없습니다.</p>
					</c:if>
                </section>

                <!-- 댓글쓰기 -->
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form action="#">
	                    <input type="hidden" name="no" value="${no}"/>
	                    <input type="hidden" name="uid" value="${sessUser.uid}"/>
                        <textarea name="content" placeholder="댓글 내용을 입력하세요."></textarea>
                        <div>
                            <a href="#" class="btn btnCancel">취소</a>
                            <input type="submit" value="작성완료" class="btn btnComplete"/>
                        </div>
                    </form>
                </section>

            </section>
        </main>
<jsp:include page="./_footer.jsp"/>