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
	public static String boxOffice = "{\r\n"
			+ "    \"boxOfficeResult\": {\r\n"
			+ "        \"boxofficeType\": \"일별 박스오피스\",\r\n"
			+ "        \"showRange\": \"20221110~20221110\",\r\n"
			+ "        \"dailyBoxOfficeList\": [\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"1\",\r\n"
			+ "                \"rank\": \"1\",\r\n"
			+ "                \"rankInten\": \"0\",\r\n"
			+ "                \"rankOldAndNew\": \"OLD\",\r\n"
			+ "                \"movieCd\": \"20226254\",\r\n"
			+ "                \"movieNm\": \"블랙 팬서: 와칸다 포에버\",\r\n"
			+ "                \"openDt\": \"2022-11-09\",\r\n"
			+ "                \"salesAmt\": \"1134897069\",\r\n"
			+ "                \"salesShare\": \"75.1\",\r\n"
			+ "                \"salesInten\": \"-785462208\",\r\n"
			+ "                \"salesChange\": \"-40.9\",\r\n"
			+ "                \"salesAcc\": \"3068108346\",\r\n"
			+ "                \"audiCnt\": \"108625\",\r\n"
			+ "                \"audiInten\": \"-75418\",\r\n"
			+ "                \"audiChange\": \"-41\",\r\n"
			+ "                \"audiAcc\": \"293424\",\r\n"
			+ "                \"scrnCnt\": \"2523\",\r\n"
			+ "                \"showCnt\": \"9483\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"2\",\r\n"
			+ "                \"rank\": \"2\",\r\n"
			+ "                \"rankInten\": \"0\",\r\n"
			+ "                \"rankOldAndNew\": \"OLD\",\r\n"
			+ "                \"movieCd\": \"20198429\",\r\n"
			+ "                \"movieNm\": \"자백\",\r\n"
			+ "                \"openDt\": \"2022-10-26\",\r\n"
			+ "                \"salesAmt\": \"114885085\",\r\n"
			+ "                \"salesShare\": \"7.6\",\r\n"
			+ "                \"salesInten\": \"-4931743\",\r\n"
			+ "                \"salesChange\": \"-4.1\",\r\n"
			+ "                \"salesAcc\": \"5960068823\",\r\n"
			+ "                \"audiCnt\": \"11693\",\r\n"
			+ "                \"audiInten\": \"-722\",\r\n"
			+ "                \"audiChange\": \"-5.8\",\r\n"
			+ "                \"audiAcc\": \"599438\",\r\n"
			+ "                \"scrnCnt\": \"753\",\r\n"
			+ "                \"showCnt\": \"1702\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"3\",\r\n"
			+ "                \"rank\": \"3\",\r\n"
			+ "                \"rankInten\": \"1\",\r\n"
			+ "                \"rankOldAndNew\": \"OLD\",\r\n"
			+ "                \"movieCd\": \"20226798\",\r\n"
			+ "                \"movieNm\": \"에브리씽 에브리웨어 올 앳 원스\",\r\n"
			+ "                \"openDt\": \"2022-10-12\",\r\n"
			+ "                \"salesAmt\": \"38459578\",\r\n"
			+ "                \"salesShare\": \"2.5\",\r\n"
			+ "                \"salesInten\": \"3790798\",\r\n"
			+ "                \"salesChange\": \"10.9\",\r\n"
			+ "                \"salesAcc\": \"2775259552\",\r\n"
			+ "                \"audiCnt\": \"3742\",\r\n"
			+ "                \"audiInten\": \"371\",\r\n"
			+ "                \"audiChange\": \"11\",\r\n"
			+ "                \"audiAcc\": \"259608\",\r\n"
			+ "                \"scrnCnt\": \"245\",\r\n"
			+ "                \"showCnt\": \"373\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"4\",\r\n"
			+ "                \"rank\": \"4\",\r\n"
			+ "                \"rankInten\": \"1\",\r\n"
			+ "                \"rankOldAndNew\": \"OLD\",\r\n"
			+ "                \"movieCd\": \"20198317\",\r\n"
			+ "                \"movieNm\": \"인생은 아름다워\",\r\n"
			+ "                \"openDt\": \"2022-09-28\",\r\n"
			+ "                \"salesAmt\": \"25087891\",\r\n"
			+ "                \"salesShare\": \"1.7\",\r\n"
			+ "                \"salesInten\": \"2662798\",\r\n"
			+ "                \"salesChange\": \"11.9\",\r\n"
			+ "                \"salesAcc\": \"10589760554\",\r\n"
			+ "                \"audiCnt\": \"3480\",\r\n"
			+ "                \"audiInten\": \"507\",\r\n"
			+ "                \"audiChange\": \"17.1\",\r\n"
			+ "                \"audiAcc\": \"1135114\",\r\n"
			+ "                \"scrnCnt\": \"202\",\r\n"
			+ "                \"showCnt\": \"239\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"5\",\r\n"
			+ "                \"rank\": \"5\",\r\n"
			+ "                \"rankInten\": \"0\",\r\n"
			+ "                \"rankOldAndNew\": \"NEW\",\r\n"
			+ "                \"movieCd\": \"20217905\",\r\n"
			+ "                \"movieNm\": \"데시벨\",\r\n"
			+ "                \"openDt\": \"2022-11-16\",\r\n"
			+ "                \"salesAmt\": \"27279000\",\r\n"
			+ "                \"salesShare\": \"1.8\",\r\n"
			+ "                \"salesInten\": \"27279000\",\r\n"
			+ "                \"salesChange\": \"100\",\r\n"
			+ "                \"salesAcc\": \"43724000\",\r\n"
			+ "                \"audiCnt\": \"3031\",\r\n"
			+ "                \"audiInten\": \"3031\",\r\n"
			+ "                \"audiChange\": \"100\",\r\n"
			+ "                \"audiAcc\": \"4821\",\r\n"
			+ "                \"scrnCnt\": \"12\",\r\n"
			+ "                \"showCnt\": \"12\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"6\",\r\n"
			+ "                \"rank\": \"6\",\r\n"
			+ "                \"rankInten\": \"-3\",\r\n"
			+ "                \"rankOldAndNew\": \"OLD\",\r\n"
			+ "                \"movieCd\": \"20198461\",\r\n"
			+ "                \"movieNm\": \"리멤버\",\r\n"
			+ "                \"openDt\": \"2022-10-26\",\r\n"
			+ "                \"salesAmt\": \"23364086\",\r\n"
			+ "                \"salesShare\": \"1.5\",\r\n"
			+ "                \"salesInten\": \"-7479867\",\r\n"
			+ "                \"salesChange\": \"-24.3\",\r\n"
			+ "                \"salesAcc\": \"3770191036\",\r\n"
			+ "                \"audiCnt\": \"2609\",\r\n"
			+ "                \"audiInten\": \"-830\",\r\n"
			+ "                \"audiChange\": \"-24.1\",\r\n"
			+ "                \"audiAcc\": \"390347\",\r\n"
			+ "                \"scrnCnt\": \"396\",\r\n"
			+ "                \"showCnt\": \"526\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"7\",\r\n"
			+ "                \"rank\": \"7\",\r\n"
			+ "                \"rankInten\": \"0\",\r\n"
			+ "                \"rankOldAndNew\": \"NEW\",\r\n"
			+ "                \"movieCd\": \"20228357\",\r\n"
			+ "                \"movieNm\": \"극장판 소드 아트 온라인 -프로그레시브- 짙은 어둠의 스케르초\",\r\n"
			+ "                \"openDt\": \"2022-11-10\",\r\n"
			+ "                \"salesAmt\": \"28545900\",\r\n"
			+ "                \"salesShare\": \"1.9\",\r\n"
			+ "                \"salesInten\": \"28545900\",\r\n"
			+ "                \"salesChange\": \"100\",\r\n"
			+ "                \"salesAcc\": \"28545900\",\r\n"
			+ "                \"audiCnt\": \"2594\",\r\n"
			+ "                \"audiInten\": \"2594\",\r\n"
			+ "                \"audiChange\": \"100\",\r\n"
			+ "                \"audiAcc\": \"2594\",\r\n"
			+ "                \"scrnCnt\": \"89\",\r\n"
			+ "                \"showCnt\": \"204\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"8\",\r\n"
			+ "                \"rank\": \"8\",\r\n"
			+ "                \"rankInten\": \"0\",\r\n"
			+ "                \"rankOldAndNew\": \"NEW\",\r\n"
			+ "                \"movieCd\": \"20227762\",\r\n"
			+ "                \"movieNm\": \"올빼미\",\r\n"
			+ "                \"openDt\": \"2022-11-23\",\r\n"
			+ "                \"salesAmt\": \"13850000\",\r\n"
			+ "                \"salesShare\": \"0.9\",\r\n"
			+ "                \"salesInten\": \"13850000\",\r\n"
			+ "                \"salesChange\": \"100\",\r\n"
			+ "                \"salesAcc\": \"16073000\",\r\n"
			+ "                \"audiCnt\": \"1530\",\r\n"
			+ "                \"audiInten\": \"1530\",\r\n"
			+ "                \"audiChange\": \"100\",\r\n"
			+ "                \"audiAcc\": \"1777\",\r\n"
			+ "                \"scrnCnt\": \"3\",\r\n"
			+ "                \"showCnt\": \"5\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"9\",\r\n"
			+ "                \"rank\": \"9\",\r\n"
			+ "                \"rankInten\": \"12\",\r\n"
			+ "                \"rankOldAndNew\": \"OLD\",\r\n"
			+ "                \"movieCd\": \"20227784\",\r\n"
			+ "                \"movieNm\": \"내 친한 친구의 아침식사\",\r\n"
			+ "                \"openDt\": \"2022-11-10\",\r\n"
			+ "                \"salesAmt\": \"13878300\",\r\n"
			+ "                \"salesShare\": \"0.9\",\r\n"
			+ "                \"salesInten\": \"12006300\",\r\n"
			+ "                \"salesChange\": \"641.4\",\r\n"
			+ "                \"salesAcc\": \"23184300\",\r\n"
			+ "                \"audiCnt\": \"1517\",\r\n"
			+ "                \"audiInten\": \"1309\",\r\n"
			+ "                \"audiChange\": \"629.3\",\r\n"
			+ "                \"audiAcc\": \"2551\",\r\n"
			+ "                \"scrnCnt\": \"140\",\r\n"
			+ "                \"showCnt\": \"275\"\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"rnum\": \"10\",\r\n"
			+ "                \"rank\": \"10\",\r\n"
			+ "                \"rankInten\": \"-3\",\r\n"
			+ "                \"rankOldAndNew\": \"OLD\",\r\n"
			+ "                \"movieCd\": \"20226777\",\r\n"
			+ "                \"movieNm\": \"극장판 짱구는 못말려: 수수께끼! 꽃피는 천하떡잎학교\",\r\n"
			+ "                \"openDt\": \"2022-09-28\",\r\n"
			+ "                \"salesAmt\": \"13774194\",\r\n"
			+ "                \"salesShare\": \"0.9\",\r\n"
			+ "                \"salesInten\": \"-160697\",\r\n"
			+ "                \"salesChange\": \"-1.2\",\r\n"
			+ "                \"salesAcc\": \"7623693069\",\r\n"
			+ "                \"audiCnt\": \"1481\",\r\n"
			+ "                \"audiInten\": \"31\",\r\n"
			+ "                \"audiChange\": \"2.1\",\r\n"
			+ "                \"audiAcc\": \"760385\",\r\n"
			+ "                \"scrnCnt\": \"209\",\r\n"
			+ "                \"showCnt\": \"234\"\r\n"
			+ "            }\r\n"
			+ "        ]\r\n"
			+ "    }\r\n"
			+ "}";


	public static JSONObject getTmdbJson(Object object) {
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
		int day = date.get(Calendar.DAY_OF_MONTH)-1;

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


}


