<%@page import="java.util.List"%>
<%@page import="db.MemberService"%>
<%@page import="db.Info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<h1>와이파이 정보 구하기</h1>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
#customers {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

tr:nth-child(even){background-color: #f2f2f2};

#customers tr:hover {background-color: #ddd;}

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
		<a href="${pageContext.request.contextPath}/locationhistory.jsp">위치히스토리 목록</a> | 
		<a href="${pageContext.request.contextPath}/getwifiinfo.jsp">OpenAPI 와이파이 정보 가져오기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-list.jsp">북마크 보기</a> | 
		<a href="${pageContext.request.contextPath}/bookmark-group.jsp">북마크그룹 관리</a>
	</p>

	<form action="./home.jsp" method="get">
		<label for="LNT">LNT: </label> <input Type"text" id="LAT" value="0.0" name="lnt"> 
		<label for="LAT">LAT: </label> <input Type"text" id="LNT" value="0.0" name="lat">

		<button type="button" onclick="getLocation()">내 위치 가져오기</button>


		<button type="submit" onclick="this.form.submit()">근처 WIFI 정보보기</button>
	</form>

	<!-- 내 위치 가져오는 메소드 -->
	<script>
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition);
			} else {
				alert("Geolocation is not supported by this browser.");
			}
		}

		function showPosition(position) {
			var lat = position.coords.latitude;
			var lng = position.coords.longitude;

			document.getElementById("LAT").value = lat;
			document.getElementById("LNT").value = lng;
		}
	</script>

	<br></br>

	<table id="customers">
		<thead>
			<tr>
				<th>거리(KM)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>

		<tbody>
			<%
			String LAT = request.getParameter("lat");
			String LNT = request.getParameter("lnt");

			if (LAT != null && LNT != null) {
				double lat = Double.valueOf(LAT);
				double lnt = Double.valueOf(LNT);
				
				MemberService memberSer = new MemberService();
				
				memberSer.locationList(lnt, lat);
				
				List<Info> list_wifi = memberSer.dbSelect(lat, lnt);
			%>

			<%
			for (Info wifilist : list_wifi) {
			%>
			<tr>
				<td><%=wifilist.getDistan()%></td>
				<td><%=wifilist.getX_SWIFI_MGR_NO()%></td>
				<td><%=wifilist.getX_SWIFI_WRDOFC()%></td>
				<td><a
					href="detailpage.jsp?mgrNo=<%=wifilist.getX_SWIFI_MGR_NO()%>&distance=<%=wifilist.getDistan()%>"><%=wifilist.getX_SWIFI_MAIN_NM()%></a></td>
				<td><%=wifilist.getX_SWIFI_ADRES1()%></td>
				<td><%=wifilist.getX_SWIFI_ADRES2()%></td>
				<td><%=wifilist.getX_SWIFI_INSTL_FLOOR()%></td>
				<td><%=wifilist.getX_SWIFI_INSTL_TY()%></td>
				<td><%=wifilist.getX_SWIFI_INSTL_MBY()%></td>
				<td><%=wifilist.getX_SWIFI_SVC_SE()%></td>
				<td><%=wifilist.getX_SWIFI_CMCWR()%></td>
				<td><%=wifilist.getX_SWIFI_CNSTC_YEAR()%></td>
				<td><%=wifilist.getX_SWIFI_INOUT_DOOR()%></td>
				<td><%=wifilist.getX_SWIFI_REMARS3()%></td>
				<td><%=wifilist.getLAT()%></td>
				<td><%=wifilist.getLNT()%></td>
				<td><%=wifilist.getWORK_DTTM()%></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="17">위치 정보를 입력한 후에 조회해 주세요. <%
				}
				%>	
			</tbody>
	</table>
</body>
</html>