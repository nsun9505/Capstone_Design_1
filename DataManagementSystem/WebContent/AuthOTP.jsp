<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    	String id = (String)request.getAttribute("id");
    	String url = (String)request.getAttribute("url");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script language="JavaScript" src="task.js" charset="UTF-8"></script>
<!-- �������� �ּ�ȭ�� �ֽ� CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- �ΰ����� �׸� -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- �������� �ּ�ȭ�� �ֽ� �ڹٽ�ũ��Ʈ -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
hr{
	width:800px;
	color : green;
	size : 10px;
}
</style>
</head>
<body>
<div class="container" style="text-align:center">
	<h2>Check OTP</h2>
	<form class="form-inline" action="CheckOTP.do" method="post">
		<input type="hidden" size="30" value="<%=id%>" name="id"> <br>
		<div class="form-group">
			<img src="<%=url%>" width="300px" height="300px">
		</div><br>
		<div class="form-group">
			<p>�ȵ���̵� : ���� ������ google authenticator�� �ٿ�ް� ���ڵ带 ��ĵ���ּ���.</p><br>
			<p style="color : red">�̹� ���ڵ带 ��ĵ�� ��� ��ϵ� OTP ������ �Է��ϼ���.</p>
		</div><br>
		<div class="form-group">
			OTP �Է� : <input type = "text" class="form-control" size="30" name="checkCode">
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-default" value="����">
		</div>
	</form>
</div>
</body>
</html>