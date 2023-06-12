<%@page import="db.MemberService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<h1>북마크 그룹 추가</h1>
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
}

#customers th {
	width: 20%;
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
		<a href="북마크 보기">북마크 보기 </a>| 
		<a href="${pageContext.request.contextPath}/bookmark-group.jsp">북마크그룹 관리</a>
	</p>

		<%
		    request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			String turn = request.getParameter("turn");

			if (name != null && turn != null && name!="" && turn!="") {
			
				int turnValue = Integer.parseInt(turn);				
				MemberService memberSer = new MemberService();
				memberSer.bookmarkInsert(name, turnValue);
			    response.sendRedirect("bookmark-group.jsp");
			}
		%>
		
	<script>
    function show() {
      alert('북마크 그룹 정보를 추가하였습니다.');
    }
   	</script>
	
	
<table id="customers">
		<form action ="./bookmark-group-add.jsp" method="post">
		<tr><th>북마크 이름</th> 
		<td><input Type="text" id="name" name="name"></td></tr> 
		
		<tr><th>순서</th> 
		<td><input Type="text" id="turn" name="turn"></td></tr>
		
		<td colspan="2" align="center" ><button type="submit" onclick="show()">추가</button></td>
		</form>
</table>
</body>
</html>