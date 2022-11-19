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
			String user_Password_re = request.getParameter("userPassword_re");
			String user_Date = request.getParameter("userDate");
			PrintWriter script = response.getWriter();
			SL_User user = new SL_User();
			SL_UserDAO userDao = new SL_UserDAO();
			CK_DAO CK_dao = new CK_DAO();
			int CK_user_num = Integer.parseInt((String)session.getAttribute("CK_user_num"));
			CK_User CK_user = CK_dao.setUserByUser_number(CK_user_num);
			// 비밀번호 확인
			if (request.getParameter("userPassword") == null || request.getParameter("userPassword_re") == null
		|| request.getParameter("userDate") == null || request.getParameter("userPassword").equals("")
		|| request.getParameter("userPassword_re").equals("") || request.getParameter("userDate").equals("")) {
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
			} else if (!user_Password.equals(user_Password_re)) {
		script.println("<script>");
		script.println("alert('비밀번호를 확인해 주세요')");
		script.println("history.back()");
		script.println("</script>");
			} else {
			SL_UserDAO userDAO = new SL_UserDAO();
			int result = userDAO.SL_update_User(user_Password, user_Date, userDAO.UserNumber(CK_user.getUser_id()));
			if (result == -1) {
		script.println("<script>");
		script.println("alert('수정에 실패했습니다.')");
		script.println("history.back()");
		script.println("</script>");
			} else {
		script.println("<script>");
		script.println("location.href = 'index.jsp'");
		script.println("</script>");
			}
		}
	%>
</body>
</html>