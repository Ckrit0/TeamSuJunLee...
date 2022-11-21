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
			String user_Password = request.getParameter("userPassword");
			String user_Active = request.getParameter("userActive");
			PrintWriter script = response.getWriter();
			CK_DAO CK_dao = new CK_DAO();
			int CK_user_num = Integer.parseInt((String)session.getAttribute("CK_user_num"));
			CK_User CK_user = CK_dao.setUserByUser_number(CK_user_num);
			// 비밀번호 확인
			SL_UserDAO userDAO = new SL_UserDAO();
			int result=0;
			if (user_Password.equals(userDAO.getUserPassword(userDAO.UserNumber(CK_user.getUser_id())))){
					result = userDAO.SL_Delete_User(userDAO.UserNumber(CK_user.getUser_id()));
			}else if(request.getParameter("userPassword") == ""){
				script.println("<script>");
				script.println("alert('비밀번호를 입력하세요.')");
				script.println("location.href = 'joinModDrop.jsp'");
				script.println("</script>");
			}
			else if(!user_Password.equals(userDAO.getUserPassword(userDAO.UserNumber(CK_user.getUser_id())))){
				script.println("<script>");
				script.println("alert('비밀번호가 일치 하지 않습니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
			if (result == 1) {
				script.println("<script>");
				script.println("alert('탈퇴 되었습니다')");
				session.removeAttribute("CK_user_num");
				script.println("location.href = 'index.jsp'");
				script.println("</script>");
			} else {
				script.println("<script>");
				script.println("alert('서버 오류')");
				script.println("history.back()");
				script.println("</script>");
				
			}
	%>