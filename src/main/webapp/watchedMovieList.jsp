<%@page import="dto.CK_TicketedMovie"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.CK_User"%>
<%@page import="dao.CK_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<body>
	<%
	String wuser_number = (String) session.getAttribute("CK_user_num");
	CK_DAO wdao = new CK_DAO();
	CK_User wuser = wdao.setUserByUser_number(Integer.parseInt(wuser_number));
	List<CK_TicketedMovie> CK_WMovie = wdao.watchedMovieList(wuser_number);
	int wT_page;
	int wW_page;
	if (request.getParameter("tpage") == null) {
		wT_page = 1;
	} else {
		wT_page = Integer.parseInt(request.getParameter("tpage"));
	}
	if (request.getParameter("wpage") == null) {
		wW_page = 1;
	} else {
		wW_page = Integer.parseInt(request.getParameter("wpage"));
	}
	%>
	<h2>관람 내역(상영일 순)</h2>
	<ul>
		<%
		int wbeginNum = (wW_page - 1) * 8;
		int wendNum;
		if (wW_page * 8 > CK_WMovie.size()) {
			wendNum = CK_WMovie.size();
		} else {
			wendNum = wW_page * 8;
		}
		String wMcode = (String)session.getAttribute("MH_movieCd"); 
		for (int i = wbeginNum; i < wendNum; i++) {
		%>
		<%if(String.valueOf(wMcode).equals(String.valueOf(CK_WMovie.get(i).getM_code()))){%>
			<b>
			<%}%>
			<li><a style="text-decoration: none; color: black;" href="CK_selectMovie.jsp?tpage=<%=wT_page%>&wpage=<%=wW_page%>&mcode=<%=CK_WMovie.get(i).getM_code()%>"><%=wdao.setMovieByM_code(String.valueOf(CK_WMovie.get(i).getM_code())).getM_title()%><br>
				상영일: <%=CK_WMovie.get(i).getWatchDate()%></a><br>
			</li>
			<%if(String.valueOf(wMcode).equals(String.valueOf(CK_WMovie.get(i).getM_code()))){%>
			</b>
			<%}%>
		<%}%><br>
		<%
		for (int i = 0; i < Math.ceil(CK_WMovie.size() / 8.0); i++) {
		%>
		<a style="text-decoration: none;" href="changePage.jsp?tpage=<%=wT_page%>&wpage=<%=i+1%>"><%=i + 1%></a>
		<%}%>
	</ul>
</body>
</html>