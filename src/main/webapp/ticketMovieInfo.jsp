<%-- 예매 또는 관람한 영화의 상세페이지 표시를 담당하는 jsp파일
	담당자: 이민혁 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ page import="dao.MovieDao"%>
<%@ page import="dto.MovieInfo"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<body>

	

	<%
	String MH_code = (String)session.getAttribute("MH_movieCd"); 
	if(MH_code == null ){%> 
	<div>예매목록</div> 
	<% }else{%>
		<h2>예매 및 관람영화 상세페이지</h2>
	<%MovieDao MH_dao = new MovieDao(); 
 		
	List<MovieInfo> MH_movieInfo = MH_dao.selectMovieInfo(Integer.parseInt(MH_code)); 
	
	for(MovieInfo MH_mi : MH_movieInfo) {
	String openDate = String.valueOf( MH_mi.getOpen_dt());
	String closeDate = String.valueOf(MH_mi.getClose_dt());
	%>
	<img
		src="https://image.tmdb.org/t/p/original<%=MH_mi.getPoster_path()%>"
		width=200 height=300>
	<h3><%=MH_mi.getM_title()%></h3>
	<div>순위 : <%=MH_mi.getRank()%></div>
	<div>장르 : <%=MH_mi.getGenreName()%></div>
	<div><%=MH_mi.getAudits()%></div>
	<div>누적 관객 수 : <%=MH_mi.getAudi_acc()%></div>
	<div>개봉일 : <%=openDate.substring(0, 10)%></div>
	<div>마감일 : <%=closeDate.substring(0, 10)%></div>
	<div>가격 : <%=MH_mi.getPrice()%></div>
	<br>  
	<%} }%> 
</body>
</html>