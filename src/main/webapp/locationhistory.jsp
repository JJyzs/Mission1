<%@page import="db.MemberService"%>
<%@page import="db.locgetset"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<h1>위치 히스토리 목록</h1>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#location {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

tr:nth-child(even){background-color: #f2f2f2};

#customers tr:hover {background-color: #ddd;}


#location td, #location th {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

#location th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: center;
	background-color: #04AA6D;
	color: white;
}
</style>
</head>
<body>
	<p>
		<a href="${pageContext.request.contextPath}/home.jsp">홈</a> | 
		<a href="${pageContext.request.contextPath}/locationhistory.jsp">위치히스토리 목록</a> | 
		<a href="${pageContext.request.contextPath}/getwifiinfo.jsp">OpenAPI 와이파이 정보 가져오기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-list.jsp">북마크 보기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-group.jsp">북마크그룹 관리</a>
	</p>

	<table id="location">
		<thead>
			<tr>
				<th>ID</th>
				<th>x좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
		<%
		MemberService memberSer = new MemberService();
	
		List<locgetset> list_location = memberSer.locationSelect();
		%>
	
		<%for (locgetset locli : list_location) { %>
		<tr>
			<td><%=locli.getID() %></td>
			<td><%=locli.getXloc() %></td>
			<td><%=locli.getYloc() %></td>
			<td><%=locli.getSearchdate() %></td>
		<form action="./locationex.jsp" method="post">
			<input type="hidden" name="ID" value="<%=locli.getID()%>">	
			<td><button type="submit">삭제</button></td>
		</form>
		</tr>
		<% } %>
		</tbody>
	</table>
</body>
</html>