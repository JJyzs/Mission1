<%@page import="java.util.List"%>
<%@page import="db.MemberService"%>
<%@page import="db.Info"%>
<%@page import="db.bookgetset"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
    width: 20%;
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: center;
	background-color: #04AA6D;
	color: white;
	}
	
#customers td {
    width: 80%;
    text-align: left;
}
}
</style>
</head>
<h1>와이파이 정보 구하기</h1>
<body>
	<p>
		<a href="${pageContext.request.contextPath}/home.jsp">홈</a> | 
		<a href="${pageContext.request.contextPath}/locationhistory.jsp">위치히스토리 목록</a> | 
		<a href="${pageContext.request.contextPath}/getwifiinfo.jsp">OpenAPI 와이파이 정보 가져오기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-list.jsp">북마크 보기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-group.jsp">북마크그룹 관리</a>
	</p>
	
	<script>
    function show() {
      alert('북마크 정보를 추가하였습니다.');
    }
   	</script>
	
	
	
	<form action ="./bookmark-add-ex.jsp" method="post">
	<select name ="bookmarkname" id ="bookmarkname">
	<option value="" disabled selected hidden>북마크 그룹 이름 선택</option>
	
	<%
  			MemberService memberSer = new MemberService();
	
			String num = request.getParameter("mgrNo");
			String dis = request.getParameter("distance");
		
			Info info = new Info();
				
			info = memberSer.wifiDetail(num, Double.parseDouble(dis));
	
  	
	List<bookgetset> booknamelist = memberSer.bookmarkNameSelect();
	
	for (bookgetset bookmark : booknamelist) {
		
	%>
  	<option value="<%= bookmark.getName() %>"><%= bookmark.getName() %></option>
  	<% } %>
	</select>
	
	<input type="hidden" name="winame" value="<%=info.getX_SWIFI_MAIN_NM()%>">	
	<button type="submit" onclick="show()">북마크 추가하기</button>
	</form>
	
	
	<br></br>
	
	<table id="customers">

			<tr><th>거리(KM)</th><td><%=info.getDistan()%></td></tr>
			<tr><th>관리번호</th><td><%=info.getX_SWIFI_MGR_NO()%></td></tr>
			<tr><th>자치구</th><td><%=info.getX_SWIFI_WRDOFC()%></td></tr>
			<tr><th>와이파이명</th><td><a href="detailpage.jsp?mgrNo=<%=info.getX_SWIFI_MGR_NO()%>&distance=<%=info.getDistan()%>"><%=info.getX_SWIFI_MAIN_NM()%></a></td></tr>
			<tr><th>도로명주소</th><td><%=info.getX_SWIFI_ADRES1()%></td></tr>
			<tr><th>상세주소</th><td><%=info.getX_SWIFI_ADRES2()%></td></tr>
			<tr><th>설치위치(층)</th><td><%=info.getX_SWIFI_INSTL_FLOOR()%></td></tr>
			<tr><th>설치유형</th><td><%=info.getX_SWIFI_INSTL_TY()%></td></tr>
			<tr><th>설치기관</th><td><%=info.getX_SWIFI_INSTL_MBY()%></td></tr>
			<tr><th>서비스구분</th><td><%=info.getX_SWIFI_SVC_SE()%></td></tr>
			<tr><th>망종류</th><td><%=info.getX_SWIFI_CMCWR()%></td></tr>
			<tr><th>설치년도</th><td><%=info.getX_SWIFI_CNSTC_YEAR()%></td></tr>
			<tr><th>실내외구분</th><td><%=info.getX_SWIFI_INOUT_DOOR()%></td></tr>
			<tr><th>WIFI접속환경</th><td><%=info.getX_SWIFI_REMARS3()%></td></tr>
			<tr><th>X좌표</th><td><%=info.getLAT()%></td></tr>
			<tr><th>Y좌표</th><td><%=info.getLNT()%></td></tr>
			<tr><th>작업일자</th><td><%=info.getWORK_DTTM()%></td></tr>
</table>
</body>          
</html>