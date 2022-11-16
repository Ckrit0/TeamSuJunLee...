<%-- 회원가입, 회원정보 수정, 회원 탈퇴를 담당하는 jsp파일
	담당자: 강수림 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수준이想</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
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
	<div class="join_container">
        <h2>
            회원 가입
        </h2>
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
                <input type="date" class="input" placeholder="생년월일" name="userDate"><br>
            </div><br><br>
            <% 
            //<h3>탈퇴여부</h3>
           // <div class="joinactive">
                //<input type="radio" name="useractive" value="0" title="탈퇴여부"> 탈퇴
                //<input type="radio" name="useractive" value="1" title="탈퇴여부"> 활동
            //</div>
                %>
            <input type="submit" class="bt_join" value="회원가입">
        </form>
    </div>
	
	<%@ include file="footer.jsp"%>
</body>
</html>