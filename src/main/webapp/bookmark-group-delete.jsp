<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<h1>북마크 그룹 삭제</h1>
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
		<a href="${pageContext.request.contextPath}/bookmark-list.jsp">북마크 보기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-group.jsp">북마크그룹 관리</a>
	</p>
	<p><strong>북마크 그룹 이름을 삭제하시겠습니까?</strong></p>
	
	<%
	String id = request.getParameter("markid");
	String name = request.getParameter("markname");
	String turn = request.getParameter("turnnum");
	%>
	
	<script>
    function show() {
      alert('북마크 그룹 정보를 삭제하였습니다.');
    }
   	</script>
	
	<table id="customers">
	
		<tr><th>북마크 이름</th> 
		<td><input Type="text" id="name" name="name" value="<%= name %>"></td></tr> 
		
		<tr><th>순서</th> 
		<td><input Type="text" id="turn" name="turn" value="<%= turn %>"></td></tr>
		
	<form action="bookmark-group-delete-ex.jsp" method="post">
	<input type="hidden" name="ID" value="<%=id %>">
		<td colspan="2" align="center" ><a href="bookmark-group.jsp">돌아가기</a> | <button type="submit" onclick="show()">삭제</button></td>
	</form>
	</table> 

</body>
</html>