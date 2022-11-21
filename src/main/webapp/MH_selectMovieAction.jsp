<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.MovieInfo"%>
<%@ page import="java.util.*"%>
<%
	response.setCharacterEncoding("UTF-8");
	String movieCd = request.getParameter("movieCd") ;
	session.setAttribute("MH_movieCd", movieCd);
	out.println(session.getAttribute("MH_movieCd"));
	response.sendRedirect("index.jsp");
			 %>
			 
