/**
 * 
 */
 
 let preventDoubleClick = false;
 let isEmailAuthOk = false;
 
$(function(){


	// 이메일 인증 검사
	let emailCode = 0;
		
	$('#btnEmail').click(function(){
		
		// 중복 클릭 방지
		$(this).hide();
		
		let email = $('input[name=email]').val();
		
		if(email == ''){
			alert('이메일을 입력 하세요.');
			return;
		}
		
		if(preventDoubleClick){
			return;
		}
		
		preventDoubleClick = true;
		
		$('.resultEmail').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		
		setTimeout(function(){
			
			$.ajax({
				url: '/Jboard2/user/emailAuth.do',
				method: 'get', 
				data: {"email":email},
				dataType: 'json',
				success: function(data){
					//console.log(data);
					if(data.status == 1) {
						// 메일 발송 성공
						emailCode = data.code;
						
						$('.resultEmail').text('인증코드를 전송 했습니다. 이메일을 확인하세요');
						$('.auth').show();
					}else{
						// 메일 발송 실패
						$('.resultEmail').text('이메일 전송을 실패했습니다. 이메일을 확인 후 다시 시도하세요.');
					}
				
				}
			});
			
		}, 1000);
	
	});
		
	// 이메일 인증코드 확인
	$('#btnEmailConfirm').click(function(){
		
		let code = $('input[name=auth]').val();
		
		if(code == ''){
			alert('이메일을 확인 후 인증코드를 입력하세요.');
			return;
		}
		
		if(code == emailCode){
			isEmailAuthOk = true;
			$('input[name=email]').attr('readonly', true);
			$('.resultEmail').text('이메일이 인증 되었습니다');
			$('.btnAuth').hide();
		}else {
			isEmailAuthOk = false;
			alert('인증 코드가 틀립니다.\n다시 확인 하십시오.')
		}
		
	});
	
});