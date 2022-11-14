<%-- 세션의 로그아웃을 담당하는 jsp파일
	세션변수 CK_email : 로그인 상태를 확인하기 위해 사용 (DB만들면 PK값으로 변수명 변경예정)
	담당자: 홍준표 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
session.removeAttribute("CK_email");
response.sendRedirect("index.jsp");
%>
