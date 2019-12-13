<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    	String checkId = (String)request.getAttribute("checkOkId");
    	String name = (String)session.getAttribute("name");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
<script type="text/javascript" src="<c:url value="/resource/js/jquery-3.4.1.js"/>"></script>
<script language="JavaScript" src="MemberCheckValid.js" charset="UTF-8"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<form class="form-inline" style="text-align:center" action="searchPatientInfo.do" method="post">
  <div class="form-group">
    <input type="text" class="form-control" id="pName" name="patient_name" placeholder="환자 이름" required="required" maxlength="20">
  </div>
  <div class="form-group">
    <input type="text" class="form-control" id="pNumber" name="patient_number" placeholder="환자 주민등록번호" required="required" maxlength="14">
  </div>
  <button type="submit" class="btn btn-default">찾기</button>
</form>
</body>
</html>