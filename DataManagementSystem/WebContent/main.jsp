<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%
	if(session.getAttribute("ValidMem") == null) {
%>
	<jsp:forward page="index.html" />
<%
	}
	String id = (String)session.getAttribute("id");
	String level = (String)session.getAttribute("level");
	request.setAttribute("id", id);
	request.setAttribute("level", level);
%>

<c:set var='f' value="value"/>
<%--
<c:set var='d' value="date"/>
<c:set var="now" value="<%=new java.util.Date()%>"/>
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>User List Main</title>
<script language="JavaScript" src="main.js" charset="UTF-8"></script>
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
<div>
	<div style="text-align:center;">
		<h1 style="font:60px arial" size="30">Data Management System</h1>
	</div>
	<div style="text-align:right;">
		<h3><%=id %>님 반갑습니다.</h3>
		<input type="reset" value="로그아웃" class="btn btn-default" onclick="javascript:window.location='logout.jsp'">&nbsp;&nbsp;&nbsp;
	</div>
	<hr>
</div>
	<hr>
	<div class="container" style="text-align:left">
		<form class="form-inline" action="addData.do" method="post" name="insertForm">
				<div class="form-group">
					site id : <input type="text" name="site_id" maxLength="15" class="form-control" style="width:100px" value="">
				</div><br>
				<div class="form-group">
					site pw : <input type="password" name="site_pw" maxLength="15" class="form-control" style="width:100px" value="">
				</div><br>
				<div class="form-group">
					site url : <input type="text" name="site" maxLength="100" style="width : 200px" class="form-control" value="http://">
				</div><br>
				<div class="form-group">
					data level : <input type="text" name="data_level" maxLength="1" class="form-control" style="width:100px">
				</div><br>
				<div class="form-group">
					data description : <input type="text" name="data_description" maxLength="100" class="form-control" style="width:500px">
				</div><br>
			<input type="button" value="데이터 추가" class="btn btn-default" onclick="check()"/>
		</form>
	</div>
<div class="container" style="text-align:center">
	<h2>Data List : ID / PW / Site / Description</h2><br>
	<c:forEach items="${list}" var="dto">
			<form class="form-inline" action="updateTodo.do" method="post" name="${f}${dto.data_id}">
				<input type="hidden" name="data_id" value="${dto.data_id}">
				<div class="form-group">
					<input type="text" name="site_id" class="form-control" style="width:90px" value="${dto.site_id}">
				</div>
				<div class="form-group">
					<input type="text" name="site_pw" class="form-control" style="width:90px" value="${dto.site_pw}">
				</div>
				<div class="form-group">
					<a href="${dto.site_url}" target="_blank">
					<input type="text" name="site" style="background-color : white; width : 250px" class="form-control" value="${dto.site_url}">
					</a>
				</div>
				<div class="form-group">
					<input type="hidden" name="data_level" class="form-control" value="${dto.data_level}" readonly>
				</div>
				<div class="form-group">
					<input type="text" name="data_description" class="form-control" style="width:450px" value="${dto.data_description}" >
				</div>
				<div class=form-group>
					<input type="button" value="삭제" class="btn btn-default" onclick="deleteData(${f}${dto.data_id})">
				</div>
				<div class=form-group>
					<input type="button" value="수정" class="btn btn-default" onclick="modifyData(${f}${dto.data_id})">
					</div>
				<div class=form-group>
					<input type="reset" value="초기화" class="btn btn-default">
				</div>
			</form>
	</c:forEach>
</div>
</body>
</html>