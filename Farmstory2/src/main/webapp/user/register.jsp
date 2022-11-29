<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/_header.jsp"/>
<script>
	
	//데이터 검증에 사용하는 정규표현식
	let regUid = /^[a-z]+[a-z0-9]{5,19}$/g;
	let regPass = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
	let regName = /^[ㄱ-힣]+$/;
	let regNick = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	let regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	let regHp = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
	
	// 폼 데이터 검증 결과 상태변수
	let isUidOk = false;
	let isPassOk = false;
	let isNameOk = false;
	let isNickOk = false;
	let isEmailOk = false;
	let isEmailAuthOk = false;
	let isHpOk = false;
	
	let preventDoubleClick = false;
	
	$(function(){
		
		// 아이디 검사하기
		$('input[name=uid]').keydown(function() {
			isUidOk = false;
		});
		
		$('#btnIdCheck').click(function(){
			
			let uid = $('input[name=uid]').val();
			
			if(isUidOk) {
				return;
			}
			
			if(!uid.match(regUid)) {
				isUidOk = false;
				$('.uidResult').css('color', 'red').text('유효한 아이디가 아닙니다.');
				return;
			}
			
			let jsonData = {"uid":uid};
			
			$('.uidResult').css('color', 'black').text('...');
			
			setTimeout(function(){
				
				$.ajax({
					url: '/Farmstory2/user/checkUid.do',
					method:'get',
					data: jsonData,
					dataType: 'json',
					success: function(data) {
						
						if(data.result == 0) {
							isUidOk = true;
							$('.uidResult').css('color', 'green').text('사용 가능한 아이디 입니다.');
						}else {
							isUidOk = false;
							$('.uidResult').css('color', 'red').text('이미 사용중인 아이디 입니다.');
						}
					}
				});
				
			}, 500);
		});
		
		// 이름 검사하기
		$('input[name=name]').focusout(function(){
			
			let name = $(this).val();
			
			if(name.match(regName)) {
				isNameOk = true;
				$('.nameResult').text('');
			}else{
				isNameOk = false;
				$('.nameResult').css('color', 'red').text('유효한 이름이 아닙니다.');
			}
		});

		// 별명 검사하기
		$('input[name=nick]').keydown(function(){
			isNickOk = false;
		});
		
		$('#btnNickCheck').click(function(){
			
			let nick = $('input[name=nick]').val();
			
			if(isNickOk){
				return;
			}
			
			if(!nick.match(regNick)){
				isNickOk = false;
				$('.nickResult').css('color', 'red').text('유효한 별명이 아닙니다.');
				return;
			}
			
			let jsonData = {"nick":nick};
			
			$('.nickResult').css('color', 'black').text('...');
			setTimeout(function(){
				$.ajax({
					url: '/Farmstory2/user/checkNick.do',
					method:'get',
					data: jsonData,
					dataType:'json',
					success: function(data){
						
						if(data.result == 0){
							isNickOk = true;
							$('.nickResult').css('color', 'green').text('사용 가능한 별명 입니다.');
						}else{
							isNickOk = false;
							$('.nickResult').css('color', 'red').text('이미 사용중인 별명 입니다.');
						}
					}
				});
			}, 500);
		});
		
		
		// 이메일 유효성 검사 & 인증 검사	
		let emailCode = 0;

		$('#btnEmailAuth').click(function(){
			
			let email = $('input[name=email]').val();
			
			if(isEmailOk) {
				return;
			}
			
			if(email == ''){
				alert('이메일을 입력 하세요.');
				return;
			}
			
			if(preventDoubleClick){
				return;
			}
			
			if(!email.match(regEmail)){
				isEmailOk = false;
				$('.emailResult').css('color', 'red').text('유효한 이메일 형식이 아닙니다.');
				return;
			}
			
			// 중복 클릭 방지
			$(this).hide();
			
			preventDoubleClick = true;
			$('.emailResult').css('color', 'black').text('인증코드를 전송 중입니다. 잠시만 기다리세요...');
			
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
							
							$('.emailResult').css('color', 'black').text('인증코드를 전송했습니다. 이메일을 확인하세요');
							$('.auth').show();
						}else{
							// 메일 발송 실패
							$('.emailResult').css('color', 'red').text('이메일 전송을 실패했습니다. 이메일을 확인 후 다시 시도하세요.');
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
			
			if(code != emailcode){
				return;
			}else{
				isEmailAuthOk = true;
			}
			
			/*
			if(code == emailCode){
				isEmailAuthOk = true;
			}
			*/
			
			let email = $('input[name=email]').val();
			let jsonData = {"email":email};
			
			setTimeout(function(){
				$.ajax({
					url: '/Farmstory2/user/checkEmail.do',
					method: 'get',
					data: jsonData,
					dataType: 'json',
					success: function(data) {

						if (data.result == 0) {
							isEmailOk = true;
							$('input[name=email]').attr('readonly', true);
							$('.emailResult').css('color', 'green').text('이메일이 인증되었습니다.');
							$('.btnAuth').hide();
						} else {
							isEmailOk = false;
							$('.emailResult').css('color', 'red').text('이미 사용중인 이메일 입니다.');
							$('#btnEmailAuth').show();
						}
					}
				});
			}, 500);
			
		});
		
		// 휴대폰 검사하기
		
		// 최종 폼 전송
		
		
	});

</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/Farmstory2/js/zipcode.js"></script>
<main id="user">
    <section class="register">
        <form action="/Farmstory2/user/register.do" method="post">
            <table border="1">
                <caption>사이트 이용정보 입력</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력"/>
                        <button type="button" id="btnIdCheck"><img src="./img/chk_id.gif" alt="중복확인"/></button>
                        <span class="uidResult"></span>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="pass1" placeholder="비밀번호 입력"/></td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td><input type="password" name="pass2" placeholder="비밀번호 입력 확인"/></td>
                </tr>
            </table>

            <table border="1">
                <caption>개인정보 입력</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" placeholder="이름 입력"/>
                        <span class="nameResult"></span>                           
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                        <input type="text" name="nick" placeholder="별명 입력"/>
                        <button type="button" id="btnNickCheck"><img src="./img/chk_id.gif" alt="중복확인"/></button>
                        <span class="nickResult"></span>
                    </td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>
                        
                        <input type="email" name="email" placeholder="이메일 입력"/>
                        <button type="button" id="btnEmailAuth"><img src="./img/chk_auth.gif" alt="인증번호 받기"/></button>
                        <span class="emailResult"></span>
                        <div class="auth">
                            <input type="text" name="auth" placeholder="인증번호 입력"/>
                            <button type="button" id="btnEmailConfirm"><img src="./img/chk_confirm.gif" alt="확인"/></button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td><input type="text" name="hp" placeholder="휴대폰 입력"/></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <input type="text" name="zip" id="zip" placeholder="우편번호" readonly/>
                        <button type="button" onclick="zipcode()"><img src="./img/chk_post.gif" alt="우편번호찾기"/></button>
                        <input type="text" name="addr1" id="addr1" placeholder="주소 검색"/>
                        <input type="text" name="addr2" id="addr2" placeholder="상세주소 입력"/>
                    </td>
                </tr>
            </table>

            <div>
                <a href="./login.do" class="btn btnCancel">취소</a>
                <input type="submit" value="회원가입" class="btn btnRegister"/>
            </div>

        </form>

    </section>
</main>
<jsp:include page="/_footer.jsp"/>