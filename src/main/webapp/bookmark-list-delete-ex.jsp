<%@page import="db.MemberService"%>
<%@page import="db.booklistgetset"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberService memberSer = new MemberService();

String IDString = request.getParameter("ID");
int ID = Integer.parseInt(IDString);
memberSer.booklistDelete(ID);
response.sendRedirect("bookmark-list.jsp");
%>