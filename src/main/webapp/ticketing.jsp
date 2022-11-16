<%-- 티켓을 구매하는 jsp파일
	user_id와 m_code와 지정한 날짜와 예약주문날짜를 받아와서 DB에 저장
	담당자: 홍준표 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
//request.getParameter(); or session

response.sendRedirect("member.jsp");
%>