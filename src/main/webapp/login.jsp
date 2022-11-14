<%-- 세션의 로그인을 담당하는 jsp파일
	세션변수 CK_email : 로그인 상태를 확인하기 위해 사용 (DB만들면 PK값으로 변수명 변경예정)
	담당자: 홍준표 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String CK_email = request.getParameter("CK_input_email");
String CK_password = request.getParameter("CK_input_password");
if(CK_email != null && CK_email.equals(CK_password)){ // 임의값. 현재는 아이디와 비번이 같으면 로그인 성공으로 간주
	session.setAttribute("CK_email",CK_email);
}


response.sendRedirect("index.jsp");
%>
