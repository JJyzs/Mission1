<%@page import="db.MemberService"%>
<%@page import="db.bookgetset"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<h1>북마크 그룹</h1>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #group {
  	font-family: Arial, Helvetica, sans-serif;
  	border-collapse: collapse;
  	width: 100%;
	}
	#group td, #group th {
  	border: 1px solid #ddd;
  	padding: 8px;
  	text-align: center;
	}
	#group th {
  	padding-top: 12px;
  	padding-bottom: 12px;
  	text-align: center;
  	background-color: #04AA6D;
  	color: white;
	}
</style>
</head>
	<p>
		<a href="${pageContext.request.contextPath}/home.jsp">홈</a> | 
		<a href="${pageContext.request.contextPath}/locationhistory.jsp">위치히스토리 목록</a> | 
		<a href="${pageContext.request.contextPath}/getwifiinfo.jsp">OpenAPI 와이파이 정보 가져오기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-list.jsp">북마크 보기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-group.jsp">북마크그룹 관리</a>
	</p>

<button type="button" onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>


<br></br>
<table id ="group">
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>					
				<th>순서</th>					
				<th>등록일자</th>					
				<th>수정일자</th>
				<th>비고</th>
					
			</tr>
		</thead>
		<tbody>
			<%
		   	MemberService memberSer = new MemberService();
			
			List<bookgetset> list_bookmark = memberSer.bookmarkSelect();
			%>
	
			<%for (bookgetset bokli : list_bookmark ) { %>
		<tr>
			<td><%=bokli.getID() %></td>
			<td><%=bokli.getName() %></td>
			<td><%=bokli.getTurn() %></td>
			<td><%=bokli.getResisterdate() %></td>
		    <% if(bokli.getModifydate() != null) {%>
			<td><%=bokli.getModifydate() %></td>
			<% }else{ %>
			<td></td>
		    <% } %>
			<td><a href="bookmark-group-up.jsp?markname=<%= bokli.getName() %>&turnnum=<%= bokli.getTurn() %>&markid=<%=bokli.getID() %>">수정</a>  <a href="bookmark-group-delete.jsp?markname=<%= bokli.getName() %>&turnnum=<%= bokli.getTurn() %>&markid=<%=bokli.getID() %>">삭제</a></td>
			<% } %>
		</tr>
		</tbody>
	</table>
</body>
</html>