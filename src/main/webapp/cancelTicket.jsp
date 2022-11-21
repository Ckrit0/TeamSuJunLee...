<%@page import="dao.CK_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String userNum = (String) session.getAttribute("CK_user_num");
int tNum = Integer.parseInt(request.getParameter("cancel"));
CK_DAO dao = new CK_DAO();
dao.cancelTicket(userNum, tNum);
response.sendRedirect("member.jsp?tpage=1&wpage=1");
%>
