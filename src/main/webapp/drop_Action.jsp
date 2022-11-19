<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.SL_UserDAO"%>
<%@ page import="dto.SL_User"%>
<%@ page import="java.io.PrintWriter"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ page import="dao.CK_DAO" %>
<%@ page import="dto.CK_User" %>

<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
</head>

<body>
	<%
			String user_ID = request.getParameter("userID");
			String user_Active = request.getParameter("userActive");
			PrintWriter script = response.getWriter();
			SL_User user = new SL_User();
			CK_DAO CK_dao = new CK_DAO();
			int CK_user_num = Integer.parseInt((String)session.getAttribute("CK_user_num"));
			CK_User CK_user = CK_dao.setUserByUser_number(CK_user_num);
			// 비밀번호 확인
			SL_UserDAO userDAO = new SL_UserDAO();
			int result = userDAO.SL_Delete_User(user_Active, userDAO.UserNumber(CK_user.getUser_id()));
			if (result == -1) {
				script.println("<script>");
				script.println("alert('탈퇴에 실패했습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				script.println("<script>");
				script.println("location.href = 'index.jsp'");
				script.println("</script>");
			}
	%>