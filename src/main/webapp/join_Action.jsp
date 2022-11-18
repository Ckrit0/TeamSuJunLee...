<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.SL_UserDAO"%>
<%@ page import="dto.SL_User"%>
<%@ page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
</head>

<body>
	<%
        String user_ID		= request.getParameter("userID");
                String user_Password = request.getParameter("userPassword");
                String user_Password_re = request.getParameter("userPassword_re");
                String user_Date		= request.getParameter("userDate");
                PrintWriter script = response.getWriter();
                
             // 비밀번호 확인
            	 if(!user_Password.equals(user_Password_re)) {
                	script.println("<script>");
                	script.println("alert('비밀번호를 확인해 주세요')");
                	script.println("history.back()");
                	script.println("</script>");
                } else if (user_ID == "" || user_Password == "" || user_Date == ""){
        	    		// -1: 서버 오류 / 0: 이미존재하는 아이디 / 1: 성공
	        			script.println("<script>");
	        			script.println("alert('빈칸을 확인해 주세요')");
	        			script.println("history.back()");
	        			script.println("</script>");
	        		} else {
        			SL_User user = new SL_User();
        			SL_UserDAO userDao = new SL_UserDAO();
        			user.setUser_ID(user_ID);
        			user.setUser_Password(user_Password);
        			user.setUser_Date(user_Date);

        			
        			int result = userDao.join(user);

        			if (result == -1) {
        				script.println("<script>");
        				script.println("alert('서버오류')");
        				script.println("history.back()");
        				script.println("</script>");
        			} else if (result == 0) {
        				script.println("<script>");
        				script.println("alert('이미 아이디가 존재합니다.')");
        				script.println("history.back()");
        				script.println("</script>");
        			} else {
        				script.println("<script>");
        				script.println("alert('회원가입을 축하드립니다.')");
        				script.println("location.href = 'index.jsp'");
        				script.println("</script>");
        			}
        		}
        	
        %>
</body>
</html>