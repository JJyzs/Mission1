<%@page import="java.util.List"%>
<%@page import="db.MemberService"%>
<%@page import="db.booklistgetset"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<h1>북마크 목록</h1>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#customers {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

#customers th {
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
		<a href="${pageContext.request.contextPath}/locationhistory.jsp">위치 히스토리 목록</a> | 
		<a href="${pageContext.request.contextPath}/getwifiinfo.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-list.jsp">북마크 보기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-group.jsp">북마크 그룹 관리</a>
</p>

	<table id="customers">
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<%
		   	MemberService memberSer = new MemberService();
			
			List<booklistgetset> list_book = memberSer.booklistSelect();
			%>
	
		<%if (list_book != null) { %>
			<%for (booklistgetset bokli : list_book ) { %>
		<tr>
			<td><%=bokli.getID() %></td>
			<td><%=bokli.getBookname() %></td>
			<td><%=bokli.getWifiname() %></td>
			<td><%=bokli.getResi() %></td>
		<form action="./bookmark-list-deletepage.jsp" method="post">
			<input type="hidden" name="ID" value="<%=bokli.getID()%>">
			<input type="hidden" name="book" value="<%=bokli.getBookname()%>">
			<input type="hidden" name="wifi" value="<%=bokli.getWifiname()%>">
			<input type="hidden" name="resi" value="<%=bokli.getResi()%>">	
			<td><button onclick="location.href='bookmark-list-deletepage.jsp'">삭제</button></td>
		</form>
		</tr>
			<%
			}
			} else {
			%>
				<tr>
				<td colspan="5">정보가 존재하지 않습니다.</td>
				</tr>
			<% 
				}
			%>
		</tbody>
	</table>
</body>
</html>