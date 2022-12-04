/**
 * 인덱스 탭 부분 최신글
 */
 
 	$(function(){
		
		// 공지사항 최신글 가져오기
		$.ajax({
			url: '/Farmstory2/getLatest.do',
			data: {"cate":"1"},
			method: 'GET',
			dataType: 'json',
			success: function(data){
				
				for(let latest of data) {
					let url = "/Farmstory2/board/view.do?group=community&cate=1&no="+latest.no+"&pg=1";
					$('#tabs-1 .txt').append("<li><a href='"+url+"'>"+latest.title+"</a></li>");
				}
			}
		});
		
		// 1:1 문의사항 최신글 가져오기
		$.ajax({
			url: '/Farmstory2/getLatest.do',
			data: {"cate":"4"},
			method: 'GET',
			dataType: 'json',
			success: function(data){
				
				for(let latest of data) {
					let url = "/Farmstory2/board/view.do?group=community&cate=4&no="+latest.no+"&pg=1";
					$('#tabs-2 .txt').append("<li><a href='"+url+"'>"+latest.title+"</a></li>");
				}
			}
		});
		
		// 자주묻는 질문 최신글 가져오기
		$.ajax({
			url: '/Farmstory2/getLatest.do',
			data: {"cate":"5"},
			method: 'GET',
			dataType: 'json',
			success: function(data){
				
				for(let latest of data) {
					let url = "/Farmstory2/board/view.do?group=community&cate=5&no="+latest.no+"&pg=1";
					$('#tabs-3 .txt').append("<li><a href='"+url+"'>"+latest.title+"</a></li>");
				}
			}
		});
	});