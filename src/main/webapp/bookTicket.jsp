<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dto.CK_Movie"%>
<%@page import="dao.CK_DAO"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<body>
	<%
	if (session.getAttribute("MH_movieCd") == null) {
	%>
	<h2>영화 선택 후, 예매가 가능합니다.</h2>
	<%
	} else {
	String movieCode = (String) session.getAttribute("MH_movieCd");
	CK_DAO ck_dao = new CK_DAO();
	CK_Movie selectedMovie = ck_dao.setMovieByM_code(movieCode);
	Date CK_today = new Date();
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	%>
	
	<form action="ticketing.jsp" method="post">
		<input type="hidden" name="watch_code" value="<%=selectedMovie.getM_code()%>">
		<h2>영화 예매하기</h2>
		제목: <%=selectedMovie.getM_title()%>
		<input type="hidden" name="watch_name" value="<%=selectedMovie.getM_title()%>"><br>
		관람 등급: <%=selectedMovie.getAudits() %><br>
		<input type="hidden" name="watch_audi" value="<%=selectedMovie.getAudits()%>"><br>
		관람 날짜 <input type="date" name="watch_date" value="<%=date.format(CK_today) %>" min="<%=date.format(selectedMovie.getOpen_dt()) %>" max="<%=date.format(selectedMovie.getClose_dt()) %>"><br>	
		가격 : <%=selectedMovie.getPrice() %> 원<br>
		<%if(session.getAttribute("CK_user_num") == null){ %>
			<button disabled>예매하기</button><br>(로그인이 필요합니다.)		
		<%}else{ %>
			<button type=submit>예매하기</button>		
		<%} %>
	</form>
	<%
	}
	session.removeAttribute("MH_movieCd");
	%>
</body>
</html>