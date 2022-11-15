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
	<%MovieDao dao = new MovieDao();
// 	MovieInfo mi2 = new MovieInfo();
// 	out.println(mi2.getRank());
	List<MovieInfo> movieInfo = dao.selectMovieInfo();
	for(MovieInfo mi : movieInfo) {%>
		<h1><%=mi.getM_title() %></h1>
	<%}
	%>
	
</body>
</html>