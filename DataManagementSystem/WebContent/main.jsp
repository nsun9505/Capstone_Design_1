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
<script language="JavaScript" src="main.js" charset="UTF-8"></script>
<!-- �������� �ּ�ȭ�� �ֽ� CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- �ΰ����� �׸� -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- �������� �ּ�ȭ�� �ֽ� �ڹٽ�ũ��Ʈ -->
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
			<h3><%=name%>�� �ݰ����ϴ�.
			</h3>
			<input type="reset" value="�α׾ƿ�" class="btn btn-default"
				onclick="javascript:window.location='logout.jsp'">&nbsp;&nbsp;&nbsp;
		</div>
		<hr>
	</div>
	<hr>

	<div class="container" style="text-align: center">
		<form action="getPacientInfo.do" method="post" class="form-horizontal">
			  <div class="form-group">
   				 <input type="text" class="form-control" id="pName" name="patient_name" placeholder="ȯ�� �̸�" required="required" maxlength="3">
  			</div>
  			<div class="form-group">
				<div class="row">
					<div class="col-xs-6"><input type="text" class="form-control" id="Pnumber1" name="patient_number_1" placeholder="�ֹι�ȣ ���ڸ�" required="required" maxlength="6"></div>
					<div class="col-xs-6"><input type="text" class="form-control" id="Pnumber2" name="patient_number_2" placeholder="�ֹι�ȣ ���ڸ�" required="required" maxlength="7"></div>
				</div>        	
        </div>
  			<button type="submit" class="btn btn-default">ã��</button>
		</form>
		<h2>ȯ�� ���� ���</h2>
		<br>
		<c:if test="${ list }">
			<c:forEach items="${list}" var="dto">
				<form action="updateTodo.do" method="post" name="${f}${dto.issue_number}" style="text-align:left">
					<input type="hidden" name="data_id" value="${dto.issue_number}">
					<div class="form-group">
						<label for="dName">��� �ǻ�</label>
						<input type="text" name="doctor_name" id="dName" class="form-control" value="${dto.doctor_name}" readonly>
					</div>
					<div class="form-group">
						<label for="pName">ȯ�� �̸�</label>
						<input type="text" name="patient_name" id="pName" class="form-control" value="${dto.patient_name}" readonly>
					</div>
					<div class="form-group">
						<label for="hName">���� �̸�</label>
						<input type="text" name="hospital_name" id="hName" class="form-control" value="${dto.hospital_name}" readonly>
					</div>
					<div class="form-group">
						<label for="content">���� ����</label>
						<input type="text" name="diagnosis_content" id="content" class="form-control" value="${dto.content}" readonly>
					</div>
					<div class="form-group">
						<label for="content">���� ����</label>
						<input type="date" name="diagnosis_date" id="dDate" class="form-control" value="${dto.diagnosis_date}">
					</div>
					<div class=form-group>
						<input type="button" value="����" class="btn btn-default" onclick="modifyData(${f}${dto.issue_number})">
					</div>
				</form>
				<hr>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>