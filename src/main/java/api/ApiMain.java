package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ApiMain {
	
	public JSONObject getTmdbJson(Object object) {
		String jsonStr = "";

		StringBuilder urlBuilder = new StringBuilder("https://api.themoviedb.org/3/search/movie"); /*URL*/
		try {
			urlBuilder.append("?" + URLEncoder.encode("api_key","UTF-8") + "=522104ab0e22f171a7b47fa13597f9fc");

			urlBuilder.append("&" + URLEncoder.encode("language","UTF-8") + "=ko-KR"); /*XML 또는 JSON*/
			urlBuilder.append("&" + URLEncoder.encode("query","UTF-8") + "="+ URLEncoder.encode( (String) object, "UTF-8"));
			//			urlBuilder.append("&" + URLEncoder.encode("sort_by","UTF-8") + "=popularity.desc");
			urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "="+ URLEncoder.encode("1", "UTF-8"));
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;

			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			jsonStr = sb.toString();
		}catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("getTmdbJson");
		}/*Service Key*/

		JSONParser jsonParser = new JSONParser();
		JSONObject tmdbObj=null;
		try {
			tmdbObj = (JSONObject)jsonParser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			System.out.println("getTmdbJson");
		}
		return tmdbObj;

	}

	public String getDailyBoxOffice() {
		String boxOffice ="";

		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH)+1;
		int day = date.get(Calendar.DAY_OF_MONTH)-2;

		StringBuilder urlBuilder = new StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"); /*URL*/
		try {
			urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=2d2918856517b45a2d1b486cbe274b43");
			urlBuilder.append("&" + URLEncoder.encode("targetDt","UTF-8") + "=" + URLEncoder.encode(year+""+month+""+day, "UTF-8")); //조회하는 날짜 
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			//http응답코드 200번대는 성공
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			boxOffice = sb.toString();
		}catch (IOException  e) {
			e.printStackTrace();
		}
		return boxOffice;
	}

	public  JSONObject getWatchGrade(Object object) throws IOException {
		String jsonObj = "";
		StringBuilder urlBuilder = new StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json"); /*URL*/
	    urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=2d2918856517b45a2d1b486cbe274b43"); /*Service Key*/
	    urlBuilder.append("&" + URLEncoder.encode("movieCd","UTF-8") + "=" + URLEncoder.encode((String) object, "UTF-8")); //영화코드
//	    urlBuilder.append("&" + URLEncoder.encode("movieCd","UTF-8") + "=" + code); //영화코드
	    URL url = new URL(urlBuilder.toString());
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	    System.out.println("Response code: " + conn.getResponseCode());
	    BufferedReader rd;
	    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	    } else {
	        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
	    }
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	        sb.append(line);
	    }
	    rd.close();
	    conn.disconnect();
	    jsonObj = sb.toString();
	    
	    JSONParser jsonParser = new JSONParser();
		JSONObject tmdbObj=null;
		try {
			tmdbObj = (JSONObject)jsonParser.parse(jsonObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			System.out.println("getTmdbJson");
		}
		return tmdbObj;
	}

}


