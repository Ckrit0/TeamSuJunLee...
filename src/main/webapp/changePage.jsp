<%@page import="dto.CK_User"%>
<%@page import="dao.CK_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
int T_page = Integer.parseInt(request.getParameter("tpage"));
int W_page = Integer.parseInt(request.getParameter("wpage"));
response.sendRedirect("member.jsp?tpage="+T_page+"&wpage="+W_page);
%>
