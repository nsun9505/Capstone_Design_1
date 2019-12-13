<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% 
	String id = request.getParameter("id");
	request.setAttribute("id", id);
	session.setAttribute("id", id);
	session.setAttribute("ValidMem", "yes");
	response.sendRedirect("main.jsp");
%>
<%--
	String id = request.getParameter("id");
	session.setAttribute("id", id);
	response.sendRedirect("authOTP.do?id="+id);
--%>
</body>
</html>