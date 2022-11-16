<%-- 영화 상세페이지 표시를 담당하는 jsp파일
	담당자: 이민혁 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ page import="dao.MovieDao"%>
<%@ page import="dto.MovieInfo"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<body>
	영화 상세페이지 작업공간
	
 	<%
 	String movieCd = request.getParameter("movieCd");
 	MovieDao MH_dao = new MovieDao();
 	List<MovieInfo> MH_movieInfo = MH_dao.selectMovieInfo(); 
 	for(MovieInfo MH_mi : MH_movieInfo) {%> 
 		<h3><%=MH_mi.getM_title()%></h3> 
 		<div><%=MH_mi.getOpen_dt() %></div> 
 		<div><%=MH_mi.getClose_dt() %></div> 
 		<div><%=MH_mi.getRank() %></div> 
		<div><%=MH_mi.getAudi_acc()%></div> 
		<div><%=MH_mi.getAudits() %></div> 
		<div><%=MH_mi.getPrice() %></div> <br>
 	<%} 
  	%>

	
</body>
</html>