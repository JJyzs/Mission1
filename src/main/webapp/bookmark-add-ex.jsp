<%@page import="db.MemberService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
MemberService memberSer = new MemberService();
String bookString = request.getParameter("bookmarkname");
String wiString = request.getParameter("winame");

memberSer.bookmarkadd(bookString, wiString);
response.sendRedirect("bookmark-list.jsp");
%>