package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.MovieInfo;

public class MovieDao {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public void connect() throws Exception {
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe"; // 접속 DB정보
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
	
public void mergeMovieInfo(MovieInfo mi) {
		
	String sqlQuery = "MERGE INTO movie_info m\r\n"
			+ "USING dual\r\n"
			+ "ON (m.showRange = to_char(sysdate,'YYYYMMDD~YYYYMMDD'))\r\n"
			+ "WHEN MATCHED THEN\r\n"
			+ "    UPDATE SET \r\n"
			+ "            m.m_code=m.m_code,\r\n"
			+ "            m.m_title=m.m_title,\r\n"
			+ "            m.open_dt =m.open_dt,\r\n"
			+ "            m.close_dt=m.close_dt,\r\n"
			+ "            m.genre_code=m.genre_code,\r\n"
			+ "            m.poster_path=m.poster_path,\r\n"
			+ "            m.rank=m.rank,\r\n"
			+ "            m.audi_acc=m.audi_acc,\r\n"
			+ "            m.audits=m.audits,\r\n"
			+ "            m.price=m.price\r\n"
			+ "WHEN NOT MATCHED THEN\r\n"
			+ "    INSERT (\r\n"
			+ "            m.showRange,\r\n"
			+ "            m.m_code,\r\n"
			+ "            m.m_title,\r\n"
			+ "            m.open_dt,\r\n"
			+ "            m.close_dt,\r\n"
			+ "            m.genre_code,\r\n"
			+ "            m.poster_path,\r\n"
			+ "            m.rank,\r\n"
			+ "            m.audi_acc,\r\n"
			+ "            m.audits,\r\n"
			+ "            m.price\r\n"
			+ "           )\r\n"
			+ "    VALUES(?,?,?,?,?,?,?,?,?,?,12000)";
		try {
			connect();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, mi.getShowRange());
			psmt.setInt(2, mi.getM_code());  
			psmt.setString(3, mi.getM_title());
			psmt.setTimestamp(4, Timestamp.valueOf(mi.getOpen_dt()));
			psmt.setTimestamp(5, Timestamp.valueOf(mi.getClose_dt()));
			psmt.setInt(6, mi.getGenre_code());
			psmt.setString(7, mi.getPoster_path());
			psmt.setInt(8, mi.getRank());
			psmt.setInt(9, mi.getAudi_acc());
			psmt.setString(10, mi.getAudits());

			int resultCnt = psmt.executeUpdate(); // executeQuery -> Select -> ResultSet
			// executeUpdate -> insert, delete, update -> int
			if(resultCnt>0) {
			System.out.println("merge 성공");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConnect();
	}
	}


	public void insertMovieInfo(MovieInfo mi) {
		
		String sqlQuery = "INSERT INTO movie_info VALUES( ?,?, ?,?,?,?,?,?,?,?,12000)";
		try {
			connect();
			psmt = conn.prepareStatement(sqlQuery);
			
			psmt.setString(1, mi.getShowRange());
			psmt.setInt(2, mi.getM_code());  
			psmt.setString(3, mi.getM_title());
			psmt.setTimestamp(4, Timestamp.valueOf(mi.getOpen_dt()));
			psmt.setTimestamp(5, Timestamp.valueOf(mi.getClose_dt()));
			psmt.setInt(6, mi.getGenre_code());
			psmt.setString(7, mi.getPoster_path());
			psmt.setInt(8, mi.getRank());
			psmt.setInt(9, mi.getAudi_acc());
			psmt.setString(10, mi.getAudits());
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
	
	public String selectMovieImageBYCode(int code){
		String sql = "SELECT poster_path FROM movie_info WHERE m_code = ?";
		MovieInfo movieInfo = null;
		
		try {
			connect();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, code);
			rs = psmt.executeQuery();
			
			movieInfo = new MovieInfo();
			if(rs.next()) {
//				movieInfo.set(rs.getInt("m_code"));
				movieInfo.setPoster_path(rs.getString("poster_path"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return movieInfo.getPoster_path();
	}
	
	public List<MovieInfo> selectMovieInfo() {
		String sqlQuery = "select m_title"
				+ " ,to_char(open_dt,'YYYYMMDD') open_dt, to_char(close_dt+30,'YYYYMMDD') close_dt, rank , audi_acc , audits, price "
				+ " from movie_info where showRange =?";
		List<MovieInfo> movieInfoList = null;
		MovieInfo movieInfo = new MovieInfo();
		try {
			connect();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, "20221114~20221114");
			rs = psmt.executeQuery();
			
			movieInfoList = new ArrayList<MovieInfo>();
			while (rs.next()) {
				movieInfo.setM_title( rs.getString("m_title")) ;
				String yyyymmdd = rs.getString("open_dt");
				int year = Integer.parseInt(yyyymmdd.substring(0, 3));
				int month = Integer.parseInt(yyyymmdd.substring(4, 5));
				int day = Integer.parseInt(yyyymmdd.substring(6));
				movieInfo.setOpen_dt(LocalDateTime.of(year, month,day, 0, 0));
				
				String yyyymmdd2 = rs.getString("close_dt");
				int year2 = Integer.parseInt(yyyymmdd2.substring(0, 3));
				int month2 = Integer.parseInt(yyyymmdd2.substring(4, 5));
				int day2 = Integer.parseInt(yyyymmdd2.substring(6));
				movieInfo.setOpen_dt(LocalDateTime.of(year2, month2,day2, 0, 0));
				movieInfo.setRank(rs.getInt("rank"));
				movieInfo.setAudi_acc(rs.getInt("audi_acc"));
				movieInfo.setAudits(rs.getString("audits"));
				movieInfo.setPrice(rs.getInt("price"));
				
				movieInfoList.add(movieInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return movieInfoList;
	}
}
