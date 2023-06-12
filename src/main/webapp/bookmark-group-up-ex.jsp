<%@page import="db.MemberService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<%
request.setCharacterEncoding("utf-8");
MemberService memberSer = new MemberService();
String nameString = request.getParameter("name");
String TurnString = request.getParameter("turn");
String IDString = request.getParameter("ID");

int turn = Integer.parseInt(TurnString);
int ID = Integer.parseInt(IDString);

memberSer.bookmarkUpdate(nameString, turn, ID);
response.sendRedirect("bookmark-group.jsp");
%>
