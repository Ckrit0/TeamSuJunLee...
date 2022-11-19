<%-- 영화의 목록 표시를 담당하는 jsp파일
	담당자: 이민혁 --%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.*"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="org.json.simple.parser.ParseException"%>
<%@ page import="api.ApiMain"%>
<%@ page import="dao.MovieDao"%>
<%@ page import="dto.MovieInfo"%>
<!DOCTYPE html>
<html>
<body>
	<script>
		function funcSubmit(){
			document.getElementById("movieDetailForm").submit();
		} 
	</script>
	<h2>영화순위</h2>
	<br>
	<%
	ApiMain api = new ApiMain();
	MovieDao dao = new MovieDao();
	MovieInfo mi = new MovieInfo();
	Calendar cal = Calendar.getInstance();
	int yyyy = cal.get(Calendar.YEAR);
	int mm = cal.get(Calendar.MONTH) + 1;
	int dd = cal.get(Calendar.DATE) - 1;
	String today = yyyy + "" + mm + "" + dd + "~" + yyyy + "" + mm + "" + dd;
	String dateComparison = dao.selectShowRange();

	if (today.equals(dateComparison)) {
		List<MovieInfo> MH_rankingList = dao.selectMovieRankingList(dateComparison);
		for (int i = 0; i < MH_rankingList.size(); i++) {
	%>
	<form id="movieDetailForm" name="movieDetailForm" method="post"
		action="MH_selectMovieAction.jsp">
<%-- 		<input type="image" id="<%=MH_rankingList.get(i).getM_code()%>=" --%>
<!-- 			name="movieCd" -->
<%-- 			src="https://image.tmdb.org/t/p/original<%=dao.selectMovieImageBYCode(MH_rankingList.get(i).getM_code())%>" --%>
<!-- 			width=150 height=200 style="cursor: pointer" alt="이미지 준비중" -->
<%-- 			value=<%=MH_rankingList.get(i).getM_code()%>>  --%>

		<input type="hidden" name="movieCd"	onclick="funcSubmit();" value="<%=MH_rankingList.get(i).getM_code()%>">
		<button type="submit" style="-webkit-appearance: none;
  -moz-appearance: none; appearance: none;"><%=MH_rankingList.get(i).getM_title()%></button>
<%-- 		<h4><%=MH_rankingList.get(i).getM_title()%></h4> --%>
		<br>
	</form>
	<%
	}
	} else {
	try {
	JSONParser jsonParser = new JSONParser();
	JSONObject boxObj = (JSONObject) jsonParser.parse(api.getDailyBoxOffice());
	JSONObject boxResultObj = (JSONObject) boxObj.get("boxOfficeResult");
	mi.setShowRange(String.valueOf(boxResultObj.get("showRange")));
	JSONArray dailyBoxArr = (JSONArray) boxResultObj.get("dailyBoxOfficeList");
	for (int i = 0; i < dailyBoxArr.size(); i++) {
		JSONObject dailyBoxObj = (JSONObject) dailyBoxArr.get(i); //영진위 일별 박스오피스
		//영진위데이터 DB에 insert
		mi.setM_code(Integer.parseInt(String.valueOf(dailyBoxObj.get("movieCd"))));
		mi.setM_title(String.valueOf(dailyBoxObj.get("movieNm")));
		//데이트 넣기
		String yyyymmdd = String.valueOf(dailyBoxObj.get("openDt"));

		int year = Integer.parseInt(yyyymmdd.substring(0, 4));
		int month = Integer.parseInt(yyyymmdd.substring(5, 7));
		int day = Integer.parseInt(yyyymmdd.substring(8));
		mi.setOpen_dt(LocalDateTime.of(year, month, day, 0, 0));
		mi.setClose_dt(LocalDateTime.of(year, month, day, 0, 0));

		mi.setRank(Integer.parseInt(String.valueOf(dailyBoxObj.get("rank"))));
		mi.setAudi_acc(Integer.parseInt((String) dailyBoxObj.get("audiAcc")));
		JSONObject jsonObj = api.getWatchGrade(dailyBoxObj.get("movieCd"));
		JSONObject movieInfoResult = (JSONObject) jsonObj.get("movieInfoResult");
		JSONObject movieInfo = (JSONObject) movieInfoResult.get("movieInfo");
		JSONArray auditsArr = (JSONArray) movieInfo.get("audits");
		JSONObject auditsObj = (JSONObject) auditsArr.get(auditsArr.size() - 1);
		// 			System.out.println(auditsObj.get("watchGradeNm"));
		mi.setAudits(String.valueOf(auditsObj.get("watchGradeNm")));
		JSONObject tmdbSearchMovie = api.getTmdbJson(dailyBoxObj.get("movieNm")); //tmdb영화검색
		JSONArray resultsArr = (JSONArray) tmdbSearchMovie.get("results");

		for (int j = 0; j < resultsArr.size(); j++) {
			JSONObject resultObj = (JSONObject) resultsArr.get(j);
			JSONArray genreArr = (JSONArray) resultObj.get("genre_ids");
			if (dailyBoxObj.get("movieNm").equals(resultObj.get("title"))) {
		for (int k = 0; k < genreArr.size(); k++) {
			//tmdb 장르코드,포스터패스 가져오기
			mi.setGenre_code(Integer.parseInt(String.valueOf(genreArr.get(k))));
		}
			}
			mi.setPoster_path(String.valueOf(resultObj.get("poster_path")));
			dao.mergeMovieInfo(mi);
		}
	%>
	<form id="movieDetailForm" name="movieDetailForm" method="post"
		action="MH_selectMovieAction.jsp">
		<input type="image" id="<%=dailyBoxObj.get("movieCd")%>="
			name="movieCd"
			src="https://image.tmdb.org/t/p/original<%=dao.selectMovieImageBYCode(Integer.parseInt(String.valueOf(dailyBoxObj.get("movieCd"))))%>"
			width=150 height=200 style="cursor: pointer" alt="이미지 준비중"
			value=<%=dailyBoxObj.get("movieCd")%>> <input type="hidden"
			name="movieCd" value="<%=dailyBoxObj.get("movieCd")%>">
		<h4><%=dailyBoxObj.get("movieNm")%>
		</h4>
		<br>
	</form>
	<%
	out.println(mi.getShowRange());
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	%>



</body>
</html>