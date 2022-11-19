<%-- 예매한 목록(관람 전)의 표시를 담당하는 jsp파일
	담당자: 홍준표 --%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.CK_User"%>
<%@page import="dao.CK_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<body>
<%
String user_number = (String) session.getAttribute("CK_user_num");
CK_DAO dao = new CK_DAO();
CK_User user = dao.setUserByUser_number(Integer.parseInt(user_number));
dao.chkWatched(user);

%>
</body>
</html>