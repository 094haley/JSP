/**
 * 
 */
 
 let regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
 let preventDoubleClick = false;
 let isEmailAuthOk = false;
 
$(function(){ 
	// 이메일 인증 검사	
	let emailCode = 0;
	
	$('#btnEmail').click(function(){
		
		let email = $('input[name=email]').val();
		
		if(email == ''){
			alert('이메일을 입력 하세요.');
			return;
		}
		
		if(preventDoubleClick){
			return;
		}
		
		if(!email.match(regEmail)){
			isEmailAuthOk = false;
			$('.resultEmail').css('color', 'red').text('유효한 이메일 형식이 아닙니다.');
			return;
		}
		
		// 중복 클릭 방지
		$(this).hide();
		
		//preventDoubleClick = true;
		$('.resultEmail').css('color', 'black').text('인증코드를 전송 중입니다. 잠시만 기다리세요...');
		
		let jsonData = {"email":email};
		
		// 이메일 인증 검사
		
		setTimeout(function(){
			
			$.ajax({
				url: '/Farmstory2/user/emailAuth.do',
				method: 'get',
				data : jsonData,
				dataType: 'json',
				success: function(data){
					if(data.status == 1){
						//메일 발송 성공
						emailCode = data.code;
	
						$('.resultEmail').css('color', 'black').text('인증코드를 전송했습니다. 이메일을 확인하세요');
						$('.auth').show();
					}else{
						// 메일 발송 실패
						$('.resultEmail').css('color', 'red').text('이메일 전송을 실패했습니다. 이메일을 확인 후 다시 시도하세요.');
					}
				}
			});
			
		}, 1000);
	
	});
	
	// 이메일 인증코드 확인 & 중복검사
	$('#btnEmailConfirm').click(function(){
		let code = $('input[name=auth]').val();
		
		if(code == ''){
			alert('이메일을 확인 후 인증코드를 입력하세요.');
			return;
		}
		
		if(code != emailCode){
			isEmailAuthOk = false;
			alert('인증 코드가 일치하지 않습니다.\다시 확인 하십시오.');
			return;
		}else{
			isEmailAuthOk = true;
			$('input[name=email]').attr('readonly', true);
			$('.resultEmail').text('이메일이 인증 되었습니다.');
			$('.btnAuth').hide();
		}
	
	});
});
 
