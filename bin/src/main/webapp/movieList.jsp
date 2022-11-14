<%-- 영화의 목록 표시를 담당하는 jsp파일
	담당자: 이민혁 --%>
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
<!DOCTYPE html>
<html>
<body>
	<h2>영화순위</h2>
	<br>
	<%
	ApiMain api = new ApiMain();
	JSONParser jsonParser = new JSONParser();
	try {
		JSONObject boxObj = (JSONObject) jsonParser.parse(api.boxOffice );
		JSONObject boxResultObj = (JSONObject) boxObj.get("boxOfficeResult");

		JSONArray dailyBoxArr = (JSONArray) boxResultObj.get("dailyBoxOfficeList");
		for (int i = 0; i < dailyBoxArr.size(); i++) {
			JSONObject dailyBoxObj = (JSONObject) dailyBoxArr.get(i);	//영진위 일별 박스오피스
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
	} finally {
	System.out.println("이상하다");
	}
	%>
<!-- 	<script> -->
<!--  		document.querySelectorAll("#movieDetail").addEventListener("click",(e)=>{ -->
<!--  			e.preventDefault(); -->
<!--  			alert("예매하시겠습니까?") -->
<!--  		}); -->
<!-- 	</script> -->
</body>
</html>