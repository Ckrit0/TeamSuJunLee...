<%-- 회원가입, 회원정보 수정, 회원 탈퇴를 담당하는 jsp파일
	담당자: 강수림 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.SL_UserDAO"%>
<%@page import="dto.SL_User"%>
<%@ page import="dao.CK_DAO" %>
<%@ page import="dto.CK_User" %>
<%request.setCharacterEncoding("UTF-8");
SL_UserDAO SL_Dao = new SL_UserDAO();
CK_DAO CK_Dao2 = new CK_DAO();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수준이想</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<!--          meta 선언          -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!--          link 선언          -->
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/style_join.css">

<!--          script 선언          -->
<script src="https://kit.fontawesome.com/e1bd1cb2a5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<script src="../js/script.js"></script>

</head>
<body>
	<%@ include file="header.jsp"%>
	<% if(session.getAttribute("CK_user_num") == null){%>
	<div class="join_container">
        <h2>
            회원 가입
        </h2>
        <br>
        <form method="post" action="./join_Action.jsp">
            <h3>아이디</h3>
            <div class="joinID">
                <input type="email" class="input" style="ime-mode:disabled;" placeholder="아이디" name="userID" title="아이디" maxlength="20">
            </div>
            <h3>비밀번호</h3>
            <div class="joinPassword">
                <input type="password" class="input" placeholder="비밀번호" name="userPassword" title="비밀번호" maxlength="20">
            </div>
             <h3>비밀번호 확인</h3>
            <div class="joinPassword">
                <input type="password" class="input" placeholder="비밀번호 확인" name="userPassword_re" title="비밀번호 확인" maxlength="20">
            </div>
            <h3>생년월일</h3>
            <div class="joinDate">
                <input type="date" class="input" placeholder="생년월일" name="userDate">
            </div>
            <br>
            <input type="submit" class="bt_join" value="회원가입">
         </form>
    </div>
	<%} else{
			int CK_user_num = Integer.parseInt((String)session.getAttribute("CK_user_num"));
			CK_User CK_user = CK_Dao2.setUserByUser_number(CK_user_num);
			%>
		<div class="join_container">
        <h2>
            정보 수정
            
        </h2>
        <form method="post" action="modify_Action.jsp">
            <h3>아이디</h3>
            <div class="joinID">
            <%=CK_user.getUser_id()%>
            </div>
            <h3>비밀번호</h3>
            <div class="joinPassword">
                <input type="password" class="input" placeholder="비밀번호" name="userPassword" title="비밀번호" maxlength="20">
            </div>
             <h3>비밀번호 확인</h3>
            <div class="joinPassword">
                <input type="password" class="input" placeholder="비밀번호 확인" name="userPassword_re" title="비밀번호 확인" maxlength="20">
            </div>
            <h3>생년월일</h3>
            <div class="joinDate">
                <input type="date" class="input" placeholder="생년월일" name="userDate">
            </div>
            
           <br>
           <input type="submit" class="bt_join" value="수정완료">
         </form>
           <br>
         <form method="post" action="drop_Action.jsp">
         <h3>탈퇴하기</h3>
            <div class="joinPassword">
                <input type="password" class="input" placeholder="비밀번호" name="userPassword" title="비밀번호" maxlength="20">
            </div>
			<input type="submit" class="bt_join" value="탈퇴하기">
			
         </form>
    </div>
	<%} %>
	<%@ include file="footer.jsp"%>
</body>
</html>