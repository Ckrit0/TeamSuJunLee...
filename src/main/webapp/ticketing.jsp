<%-- 티켓을 구매하는 jsp파일
	user_id와 m_code와 지정한 날짜와 예약주문날짜를 받아와서 DB에 저장
	담당자: 홍준표 --%>
<%@page import="dto.CK_Movie"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dao.CK_DAO"%>
<%@page import="dto.CK_User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String watchCode = request.getParameter("watch_code");
String watchName = request.getParameter("watch_name");
String watchAudi = request.getParameter("watch_audi");
String watchDate = request.getParameter("watch_date");
String user_number = (String) session.getAttribute("CK_user_num");
CK_DAO dao = new CK_DAO();
CK_User user = dao.setUserByUser_number(Integer.parseInt(user_number));
CK_Movie movie = dao.setMovieByM_code(watchCode);
Date CK_today = new Date();
SimpleDateFormat date = new SimpleDateFormat("yyyy");
int age = Integer.parseInt(date.format(CK_today)) - Integer.parseInt(date.format(user.getUser_date())) + 1;
if (watchDate == "") {
%>
<script type="text/javascript">
	alert('날짜를 입력해주세요');
	history.back();
</script>
<%
}
if (watchAudi.equals("12세이상관람가") && age < 12) {
%>
<script type="text/javascript">
	alert('12세 이상만 관람이 가능합니다.');
	history.back();
</script>
<%
}
if (watchAudi.equals("15세이상관람가") && age < 15) {
%>
<script type="text/javascript">
	alert('15세 이상만 관람이 가능합니다.');
	history.back();
</script>
<%
}
if (watchAudi.equals("18세이상관람가") && age < 18) {
%>
<script type="text/javascript">
	alert('18세 이상만 관람이 가능합니다.');
	history.back();
</script>
<%
}
dao.ticketing(user.getUser_number(), movie.getM_code(), java.sql.Date.valueOf(watchDate));
response.sendRedirect("member.jsp");
%>