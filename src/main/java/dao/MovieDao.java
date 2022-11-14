package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public void connect() throws Exception {
		String db_url = "jdbc:oracle:thin:@localhost:1521:orcl"; // ���� DB����
		String db_id = "scott"; // ���� ���̵�
		String db_pw = "tiger"; // ���� ���̵��� ��й�ȣ

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
//	public void insertMovieInfo(TmdbData tmdb) {
//		
//		String sqlQuery = "INSERT INTO movie_info VALUES( ?, ?)";
//		try {
//			connect();
//			psmt = conn.prepareStatement(sqlQuery);
//		
//			psmt.setString(1, tmdb.title);  
//			psmt.setString(2, tmdb.poster_path);
//
//			int resultCnt = psmt.executeUpdate();
//			if(resultCnt > 0) {
//				System.out.println("Insert ����");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			disConnect();
//		}
//	}
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
