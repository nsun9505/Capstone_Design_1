function checkInfo() {
	var re = /^[a-zA-Z0-9]{4,14}$/; // 아이디와 패스워드가 적합한지 검사할 정규식
	
	var id = document.getElementById("user_id");
	var pw = document.getElementById("user_pw");
	
	if(!check(re, id, "아이디는 4~14자의 영문 대소문자와 숫자로만 입력")){
		return false;
	}
	if(!check(re, pw, "비밀번호는 4~14자의 영문 대소문자와 숫자로만 입력")){
		return false;
	}
	if(document.joinForm.name.value.length == 0) {
		alert("이름을 입력하세요.");
		joinForm.name.focus();
		return false;
	}
	if(joinForm.pw.value != joinForm.pwck.value) {
		alert("비밀번호가 다릅니다. 다시 확인해 주세요");
		joinForm.pwck.value = "";
		joinForm.pwck.focus();
		return false;
	}
	joinForm.submit();
}

function checkDupId(){
	var re = /^[a-zA-Z0-9]{4,14}$/; // 아이디와 패스워드가 적합한지 검사할 정규식
		
	var id = document.getElementById("user_id");
		
	if(!check(re, id, "아이디는 4~14자의 영문 대소문자와 숫자로만 입력")){
			return;
	}
	joinForm.action = "idCheck.do";
	joinForm.submit();
}

function checkModifyInfo(){
	var re = /^[a-zA-Z0-9]{4,14}$/;
	var pw = document.getElementById("modifyPw");
	var pwck = document.getElementById("modifyPwCheck");
	
	if(!check(re, pw, "비밀번호는 4~14자리의 영문 대소문자와 숫자의 조합입니다.")){
		return;
	}
	if(pw.value != pwck.value){
		alert("비밀번호가 다릅니다.");
		return;
	}
	document.modifyForm.action = "memberModify.do";
	document.modifyForm.submit();
}

function checkDelInfo(){
	var re = /^[a-zA-Z0-9]{4,14}$/;
	var pw = document.getElementById("modifyPw");
	var pwck = document.getElementById("modifyPwCheck");
	
	if(!confirm("회원탈퇴 시 회원님의 비밀번호를 입력해야 합니다. 진행하시겠습니까?")){
		return;
	}
	
	if(!check(re, pw, "비밀번호는 4~14자리의 영문 대소문자와 숫자의 조합입니다.")){
		return;
	}
	if(pw.value != pwck.value){
		alert("입력한 비밀번호가 다릅니다.");
		return;
	}
	
	document.modifyForm.action = "memberDelete.do";
	document.modifyForm.submit();
}

function check(re, what, message) {
    if(re.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
    return false;
}