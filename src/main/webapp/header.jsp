<%-- 페이지 네비게이션과 로그인/로그아웃을 담당하는 jsp파일
	세션변수 CK_email : 로그인 상태를 확인하기 위해 사용 (DB만들면 PK값으로 변수명 변경예정)
	담당자: 홍준표 --%>
<%@page import="dto.CK_User"%>
<%@page import="dao.CK_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
CK_DAO CK_Dao = new CK_DAO();
%>
<!DOCTYPE html>
<html>
<body>
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp" style="font-size: 30px;">영화예매프로젝트</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp" style="font-size: 18px;">예매하기</a></li>

				</ul>
				<% if(session.getAttribute("CK_user_num") == null){%>
					<form action="login.jsp" method="post" class="d-flex">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><div class="form-floating mb-3">
								<input type="email" class="form-control" name="CK_input_email"
									placeholder="name@example.com"> <label
									for="floatingInput">Email address</label>
							</div></li>
						<li class="nav-item"><div class="form-floating">
								<input type="password" class="form-control"
									name="CK_input_password" placeholder="Password"> <label
									for="floatingPassword">Password</label>
							</div></li>
						<li class="nav-item">
							<button class="btn btn-outline-success" style="padding: 16px"
								type="submit">로그인</button>
						</li>
					</ul>
				</form>
				<a href="joinModDrop.jsp">
					<button class="btn btn-outline-success nav-item"
						style="padding: 16px; margin-top: -16px" type="button">회원가입</button>
				</a>
				<%} else{
					int CK_user_num = Integer.parseInt((String)session.getAttribute("CK_user_num"));
					CK_User CK_user = CK_Dao.setUserByUser_number(CK_user_num);
					%>
					
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><%=CK_user.getUser_id()%>님 반갑습니다.</li>
					</ul>
				<a href="member.jsp">
					<button class="btn btn-outline-success nav-item"
						style="padding: 16px;" type="button">예매내역</button>
				</a>
				<a href="joinModDrop.jsp">
					<button class="btn btn-outline-success nav-item"
						style="padding: 16px;" type="button">회원정보수정</button>
				</a>
				<a href="logout.jsp">
					<button class="btn btn-outline-success nav-item"
						style="padding: 16px;" type="button">로그아웃</button>
				</a>
				<%} %>
				

			</div>
		</div>
	</nav>
</body>
</html>