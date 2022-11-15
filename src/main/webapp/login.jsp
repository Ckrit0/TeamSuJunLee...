<%-- 세션의 로그인을 담당하는 jsp파일
	세션변수 CK_email : 로그인 상태를 확인하기 위해 사용 (DB만들면 PK값으로 변수명 변경예정)
	담당자: 홍준표 --%>
<%@page import="dto.CK_User"%>
<%@page import="dao.CK_UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String CK_id = request.getParameter("CK_input_email").strip();
String CK_pw = request.getParameter("CK_input_password");
CK_UserDAO userDAO = new CK_UserDAO();
int CK_tryLogin = userDAO.login(CK_id, CK_pw);
if(CK_tryLogin == 1){ // 로그인 성공
	CK_User user = userDAO.setUserByUser_id(CK_id);
	session.setAttribute("CK_user_num", String.valueOf(user.getUser_number()));
}else if(CK_tryLogin == 0){ // 비밀번호 틀림
	
}else if(CK_tryLogin == -1){ // 아이디 틀림
	
}else{ // 서버오류
	
}
response.sendRedirect("index.jsp");
%>
