<%@page import="db.MemberService"%>
<%@page import="db.locgetset"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberService memberSer = new MemberService();
String ID = request.getParameter("ID");
memberSer.locationDelete(Integer.parseInt(ID));
response.sendRedirect("locationhistory.jsp");
%>

