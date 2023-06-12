<%@page import="java.util.List"%>
<%@page import="db.OpenApi"%>
<%@page import="db.Info"%>
<%@page import="db.Totalcount"%>
<%@page import="db.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
MemberService memberSer = new MemberService();
List<Info> list = OpenApi.depoint();	
memberSer.resister(list);
Totalcount tota = new  Totalcount();
int totalco = tota.total();
%>

<h1 style = "text-align: center;"><%= totalco %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
<div style="text-align: center;">
<a href="${pageContext.request.contextPath}/home.jsp">홈으로 가기</a>
</div>
</body>
</html>