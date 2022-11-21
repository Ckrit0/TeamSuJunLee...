package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.MovieInfo;

public class MovieDao {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public void connect() throws Exception {
		String db_url = "jdbc:oracle:thin:@localhost:1521:orcl"; // 접속 DB정보
		String db_id = "scott"; // 접속 아이디
		String db_pw = "tiger"; // 접속 아이디의 비밀번호

		Class.forName("oracle.jdbc.driver.OracleDriver");

		if(conn != null) {
			conn.close();
		}
		conn = DriverManager.getConnection(db_url, db_id, db_pw);
	}

	public void disConnect() {
		try {
			if(rs != null) {
				rs.close();	
			}
			if(psmt != null) {
				psmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertMovieInfo(MovieInfo mi) {
		
		String sqlQuery = "INSERT INTO movie_info VALUES( ?, ?,?,?,?,?,?,?,?,12000)";
		try {
			connect();
			psmt = conn.prepareStatement(sqlQuery);
		
			psmt.setInt(1, mi.getM_code());  
			psmt.setString(2, mi.getM_title());
			psmt.setTimestamp(3, Timestamp.valueOf(mi.getOpen_dt()));
			psmt.setTimestamp(4, Timestamp.valueOf(mi.getClose_dt()));
			psmt.setInt(5, mi.getGenre_code());
			psmt.setString(6, mi.getPoster_path());
			psmt.setInt(7, mi.getRank());
			psmt.setInt(8, mi.getAudi_acc());
			psmt.setInt(9, mi.getAudits());
//			psmt.setInt(10, mi.getPrice());  

			int resultCnt = psmt.executeUpdate();
			if(resultCnt > 0) {
				System.out.println("Insert 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
//	public List<TmdbData> selectTmdbStatus() {
//		String sqlQuery = "select * from movie_info";
//		List<TmdbData> tmdbList = null;
//		try {
//			connect();
//			psmt = conn.prepareStatement(sqlQuery);
//			rs = psmt.executeQuery();
//			
//			tmdbList = new ArrayList<TmdbData>();
//			while (rs.next()) {
//				TmdbData tmdb = new TmdbData();
//				tmdb.title = rs.getString("title");
//				tmdb.poster_path = rs.getString("poster_path");
//				
//				tmdbList.add(tmdb);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			disConnect();
//		}
//		return tmdbList;
//	}
}
