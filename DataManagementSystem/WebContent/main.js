function checkDelete(frm){
	frm.action = "deleteTodo.do";
	frm.submit();
}

function popup(id){
	var url = "modify.jsp?id="+id;
	window.open(url, "modify", "width=360px,height='500px,resizable=0,toolbars=no,scrollbars=no");
}

function addData(){
	var url = "addDataForm.jsp";
	window.open(url, "addForm", "width=600px,height='600px,resizable=0,toolbars=no,scrollbars=no");
}

function modifyData(frmName){
	var url = "modifyData.do";
	window.open(url, "modify", "width=360px,height='500px,resizable=0,toolbars=no,scrollbars=no");
}

function deleteData(frmName){
	if(!confirm("삭제 요청을 하시겠습니까?")){
		return;
	}
	frmName.action = "deleteData.do";
	frmName.submit();
}

function check(){
	var site_pw =insertForm.site_pw.value;
	var site_id = insertForm.site_id.value;
	var site = insertForm.site.value;
	var data_level = insertForm.data_level.value;
	var data_description = insertForm.data_description.value;
	
	if(site_pw == "" || site_id == "" || site == "" || data_level == "" || data_description == ""){
		alert("데이터 추가 시 빈칸이 없어야 합니다.");
		return;
	}
	insertForm.submit();
	return;
}

function showPopup() { 
	window.open("addDataForm.jsp", "a", "width=500, height=600, left=100, top=50");
	}