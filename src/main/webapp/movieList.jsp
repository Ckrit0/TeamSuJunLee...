<%-- 영화의 목록 표시를 담당하는 jsp파일
	담당자: 이민혁 --%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="org.json.simple.parser.ParseException"%>
<%@ page import="api.ApiMain"%>
<%@ page import="dao.MovieDao" %>
<%@ page import="dto.MovieInfo" %>
<!DOCTYPE html>
<html>
<body>
	<h2>영화순위</h2>
	<br>
	<%
	ApiMain api = new ApiMain();
	MovieDao md = new MovieDao();
	MovieInfo mi = new MovieInfo();
	JSONParser jsonParser = new JSONParser();
	try {
		JSONObject boxObj = (JSONObject) jsonParser.parse(api.getDailyBoxOffice() );
		JSONObject boxResultObj = (JSONObject) boxObj.get("boxOfficeResult");

		JSONArray dailyBoxArr = (JSONArray) boxResultObj.get("dailyBoxOfficeList");
		for (int i = 0; i < dailyBoxArr.size(); i++) {
			JSONObject dailyBoxObj = (JSONObject) dailyBoxArr.get(i);	//영진위 일별 박스오피스
			//데이터 DB에 insert
			mi.setM_code(Integer.parseInt((String) dailyBoxObj.get("movieCd")));
			mi.setM_title((String) dailyBoxObj.get("movieNm"));
			//데이트 넣기
			String yyyymmdd = (String)dailyBoxObj.get("openDt");
			System.out.println(yyyymmdd);
			System.out.println(yyyymmdd.substring(0, 4));
			System.out.println(yyyymmdd.substring(5, 7));
			System.out.println(yyyymmdd.substring(8));
			int year = Integer.parseInt(yyyymmdd.substring(0, 4));
			int month = Integer.parseInt(yyyymmdd.substring(5, 7));
			int day = Integer.parseInt(yyyymmdd.substring(8));
// 			mi.setOpen_dt(LocalDateTime.of(year, month, day, 0, 0));
			JSONObject tmdbSearchMovie = api.getTmdbJson(dailyBoxObj.get("movieNm")); //tmdb영화검색
			JSONArray resultsArr = (JSONArray) tmdbSearchMovie.get("results");
	%>

	<%
	for (int j = 0; j < resultsArr.size(); j++) {
		JSONObject resultObj = (JSONObject) resultsArr.get(j);
		if (dailyBoxObj.get("movieNm").equals(resultObj.get("title"))) {
	%>
	<img
		src="https://image.tmdb.org/t/p/original<%=resultObj.get("poster_path")%>"
		width=200 height=200  id = "movieDetail" style = cursor:pointer onclick ="alert('예매하시겠음?')">

	<%}
	}%>
	<h4><%=dailyBoxObj.get("movieNm")%>
	</h4>
	<%
	
	}
	} catch (ParseException e) {
	e.printStackTrace();
	} 
	%>

</body>
</html>