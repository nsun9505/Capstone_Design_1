<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	if (session.getAttribute("ValidMem") == null) {
%>
<jsp:forward page="index.html" />
<%
	}
	String doctor_name = (String) session.getAttribute("name");
	String hospital_name = (String) session.getAttribute("hospital_name");
	String patient_name = (String) session.getAttribute("search_patient_name");
	String patient_number = (String) session.getAttribute("search_patient_number");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" style="width:600px; margin: 0 auto;">
		<h2 style="text-align:center">환자 진단서 입력</h2>
		<hr size="20px">
		<form action="modifyPatientSheet.do" method="post" name="modifyDataForm">
			<div class="form-group">
				환자 이름 : <input type="text" class="form-control" name="patient_name" value = "<%=patient_name %>"
					maxlength="10" readonly>
			</div>
			<div class="form-group">
				환자 주민등록번호 : <input type="text" class="form-control" value="<%=patient_number %>"
					name="patient_number" maxlength="14" readonly>
			</div>
			<div class="form-group">
				담당 의사 : <input type="text" class="form-control" name="doctor_name" value="<%=doctor_name %>"
					maxlength="10" readonly>
			</div>
			<div class="form-group">
				병원 이름 : <input type="text" class="form-control" name="hospital_name" value="<%=hospital_name %>"
					maxlength="20" readonly>
			</div>
			<div class="form-group">
				진단 날짜 : <input type="date" class="form-control" name="diagnosis_date" id="modify_dDate" readonly>
			</div>
			<div class="form-group">
				진단 내용 : <textarea class="form-control" name="diagnosis_content" id="new_content" style="height:300px"></textarea>
			</div>
			<div class="form-group" style="text-align:right">
				<input type="submit" class="btn btn-default" value="추가" onclick="addPatientSheetData();"/>
				<input type="button" class="btn btn-default" value="취소" onclick="self.close();"/>
			</div>
		</form>
	</div>
</body>
<script>
		function cancelWindow(){
			self.close();
		}
		function addPatientSheetData(){
			var content = document.getElementById("new_content").value.replace(/\s/gi, "");
			if(content == "")
			{
				alert("진단 내용을 입력해주세요.");
			}else {
				addDataForm.submit();
			}
		}
</script>
</html>