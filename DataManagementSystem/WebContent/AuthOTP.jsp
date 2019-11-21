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
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
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
			<p>안드로이드 : 구글 스토어에서 google authenticator를 다운받고 바코드를 스캔해주세요.</p><br>
			<p style="color : red">이미 바코드를 스캔한 경우 등록된 OTP 정보를 입력하세요.</p>
		</div><br>
		<div class="form-group">
			OTP 입력 : <input type = "text" class="form-control" size="30" name="checkCode">
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-default" value="인증">
		</div>
	</form>
</div>
</body>
</html>