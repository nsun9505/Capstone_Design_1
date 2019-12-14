<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	if (session.getAttribute("ValidMem") == null) {
%>
<jsp:forward page="index.html" />
<%
	}
	String id = (String) session.getAttribute("id");
	request.setAttribute("id", id);
	String name = (String) session.getAttribute("name");
	String hospital_name = (String) session.getAttribute("hospital_name");
	String patient_name = (String)session.getAttribute("search_patient_name");
	String patient_number = (String)session.getAttribute("search_patient_number");
%>

<c:set var='f' value="value" />
<%--
<c:set var='d' value="date"/>
<c:set var="now" value="<%=new java.util.Date()%>"/>
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Patient List Main</title>
<script src="/main.js" charset="UTF-8"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
hr {
	width: 800px;
	color: green;
	size: 10px;
}
</style>
</head>
<body>
	<div>
		<div style="text-align: center;">
			<h1 style="font: 60px arial" size="30">Patient Information Management System</h1>
		</div>
		<div style="text-align: right;">
			<h3>소속 : <%=hospital_name%> / 이름 : <%=name%></h3>
			<input type="reset" value="로그아웃" class="btn btn-default"
				onclick="javascript:window.location='logout.jsp'">&nbsp;&nbsp;&nbsp;
		</div>
		<hr>
	</div>
	<hr>

	<div class="container" style="text-align: center">
		<form action="getPacientInfo.do" method="post" class="form-horizontal">
			  <div class="form-group">
   				 <input type="text" class="form-control" id="pName" name="patient_name" placeholder="환자 이름" required="required" maxlength="3">
  			</div>
  			<div class="form-group">
				<div class="row">
					<div class="col-xs-6"><input type="text" class="form-control" id="Pnumber1" name="patient_number_1" placeholder="주민번호 앞자리" required="required" maxlength="6"></div>
					<div class="col-xs-6"><input type="text" class="form-control" id="Pnumber2" name="patient_number_2" placeholder="주민번호 뒷자리" required="required" maxlength="7"></div>
				</div>        	
        </div>
  			<button type="submit" class="btn btn-default">찾기</button>
		</form>
		<h2>환자 진단 기록</h2>
		<br>
		<c:if test="${list != null && account_type eq 'doctor'}">
			<form>
				<div class="form-group">
					<input type="button" class="btn btn-default" onclick="addPatientSheet();" value="<%=patient_name %> 진단서 추가">
				</div>
			</form>
		</c:if>
			<c:forEach items="${list}" var="dto">
				<form action="updateTodo.do" method="post" name="${f}${dto.issue_number}" style="text-align:left">
					<input type="hidden" name="data_id" value="${dto.issue_number}">
					<div class="form-group">
						<label for="dName">담당 의사</label>
						<input type="text" name="doctor_name" id="dName" class="form-control" value="${dto.doctor_name}" readonly>
					</div>
					<div class="form-group">
						<label for="pName">환자 이름</label>
						<input type="text" name="patient_name" id="pName" class="form-control" value="${dto.patient_name}" readonly>
					</div>
					<div class="form-group">
						<label for="hName">병원 이름</label>
						<input type="text" name="hospital_name" id="hName" class="form-control" value="${dto.hospital_name}" readonly>
					</div>
					<div class="form-group">
						<label for="content">진단 내용</label>
						<textarea name="diagnosis_content" id="content" class="form-control" style="height:200px" readonly>${dto.content}</textarea>
					</div>
					<div class="form-group">
						<label for="content">진단 날짜</label>
						<input type="date" name="diagnosis_date" id="dDate" class="form-control" value="${dto.diagnosis_date}" readonly>
					</div>
					<c:if test="${account_type eq 'doctor'}">
					<div class=form-group>
						<input type="button" value="수정" class="btn btn-default" onclick="modifyPatientSheet(${f}${dto.issue_number});">
					</div>
					</c:if>
				</form>
				<hr>
			</c:forEach>
	</div>
</body>
<script>
function addPatientSheet(){
	var url = "addDataForm.jsp";
	window.open(url, "addForm", "width=600px,height=1000px,resizable=0,toolbars=no,scrollbars=no");
}

function modifyPatientSheet(content){
	var url = "modifyDataForm.jsp";
	window.open(url, "modifyFrom", "width=600px,height=1000px,resizable=0,toolbars=no,scrollbars=no");
}

</script>
</html>