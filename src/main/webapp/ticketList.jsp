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
	String user_number = (String) session.getAttribute("CK_user_num");
	CK_DAO dao = new CK_DAO();
	CK_User user = dao.setUserByUser_number(Integer.parseInt(user_number));
	dao.chkWatched(user);
	List<CK_TicketedMovie> CK_TMovie = dao.ticketedMovieList(user_number);
	int T_page;
	int W_page;
	if (request.getParameter("tpage") == null) {
		T_page = 1;
	} else {
		T_page = Integer.parseInt(request.getParameter("tpage"));
	}
	if (request.getParameter("wpage") == null) {
		W_page = 1;
	} else {
		W_page = Integer.parseInt(request.getParameter("wpage"));
	}
	%>
	<h2>예매 내역(상영일 순)</h2>
	<ul>
		<%
		int beginNum = (T_page - 1) * 5;
		int endNum;
		if (T_page * 5 > CK_TMovie.size()) {
			endNum = CK_TMovie.size();
		} else {
			endNum = T_page * 5;
		}
		String Mcode = (String)session.getAttribute("MH_movieCd"); 
		for (int i = beginNum; i < endNum; i++) {
		%>
			<li>
			<%if(String.valueOf(Mcode).equals(String.valueOf(CK_TMovie.get(i).getM_code()))){%>
			<b>
			<%}%>
				<a style="text-decoration: none; color: black;" href="CK_selectMovie.jsp?tpage=<%=T_page%>&wpage=<%=W_page%>&mcode=<%=CK_TMovie.get(i).getM_code()%>"><%=dao.setMovieByM_code(String.valueOf(CK_TMovie.get(i).getM_code())).getM_title()%><br>
				상영일: <%=CK_TMovie.get(i).getWatchDate()%></a><br>
			<%if(String.valueOf(Mcode).equals(String.valueOf(CK_TMovie.get(i).getM_code()))){%>
			</b>
			<%}%>
				<form action="cancelTicket.jsp" method="post">
					<button type="submit" name="cancel"	value="<%=CK_TMovie.get(i).getT_num()%>">예매 취소</button>
				</form>
			</li>
		<%}%><br>
		<%
		for (int i = 0; i < Math.ceil(CK_TMovie.size() / 5.0); i++) {
		%>
		<a style="text-decoration: none;" href="changePage.jsp?tpage=<%=i + 1%>&wpage=<%=W_page%>"><%=i + 1%></a>
		<%}%>
	</ul>
</body>
</html>