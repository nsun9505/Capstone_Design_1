<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
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
<!-- �������� �ּ�ȭ�� �ֽ� CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- �ΰ����� �׸� -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- �������� �ּ�ȭ�� �ֽ� �ڹٽ�ũ��Ʈ -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" style="width:600px; margin: 0 auto;">
		<h2 style="text-align:center">ȯ�� ���ܼ� ����</h2>
		<hr size="20px">
		<form action="modifyPatientSheet.do" method="post" name="modifyDataForm">
			<input type="hidden" value="modify" name="operation_type">
			<div class="form-group">
				ȯ�� �̸� : <input type="text" class="form-control" name="patient_name" value = "<%=patient_name %>"
					maxlength="10" readonly>
			</div>
			<div class="form-group">
				ȯ�� �ֹε�Ϲ�ȣ : <input type="text" class="form-control" value="<%=patient_number %>"
					name="patient_number" maxlength="14" readonly>
			</div>
			<div class="form-group">
				��� �ǻ� : <input type="text" class="form-control" name="doctor_name" value="<%=doctor_name %>"
					maxlength="10" readonly>
			</div>
			<div class="form-group">
				���� �̸� : <input type="text" class="form-control" name="hospital_name" value="<%=hospital_name %>"
					maxlength="20" readonly>
			</div>
			<div class="form-group">
				���� ���� ��¥ : <input type="date" class="form-control" name="diagnosis_date" id="modify_dDate" readonly>
			</div>
			<div class="form-group">
				���� ���� : <textarea class="form-control" name="diagnosis_content" id="new_content" style="height:300px">[���� ���� �Է�]</textarea>
			</div>
			<div class="form-group" style="text-align:right">
				<input type="submit" class="btn btn-default" value="�߰�" onclick="modifyPatientSheetData();"/>
				<input type="button" class="btn btn-default" value="���" onclick="self.close();"/>
			</div>
		</form>
	</div>
</body>
<script>
		document.getElementById('modify_dDate').value = new Date().toISOString().substring(0, 10);
		function cancelWindow(){
			self.close();
		}
		function modifyPatientSheetData(){
			var content = document.getElementById("new_content").value.replace(/\s/gi, "");
			if(content == "")
			{
				alert("������ ���� ������ �Է����ּ���.");
			}else{
				modifyDataForm.submit();
			}
		}
</script>
</html>