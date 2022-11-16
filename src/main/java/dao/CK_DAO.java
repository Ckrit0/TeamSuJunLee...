/**
 * 너무 잦은 connect와 close는 접속오류를 발생 할 수 있습니다.
 * 그것도 모르고 코드만 계속 수정함..
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.CK_Movie;
import dto.CK_User;

public class CK_DAO {
	//전역변수 모음
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String dbID = "scott";
	private String dbPW = "tiger";
	private String SQL = "";

	public CK_DAO() { // 생성자, 연결정보 생성
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * id에 따른 비밀번호 비교
	 * parameter = id, password.
	 * return 1:비밀번호 일치, 0:비밀번호 불일치, -1:아이디없음, -2:서버오류
	 */
	public int login(String user_id, String user_password) {
		SQL = "SELECT user_password FROM USER_MEMBER WHERE user_id = ? AND user_active = 1";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("user_password").equals(user_password)) {
					return 1;
				} else {
					return 0;
				}
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}

	/*
	 * user_number를 받아 해당 CK_User 객체 반환
	 * parameter = user_number
	 * return CK_User 객체
	 */
	public CK_User setUserByUser_number(int user_number) {
		CK_User user = null;
		SQL = "SELECT * FROM USER_MEMBER WHERE user_number = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, user_number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new CK_User();
				user.setUser_number(rs.getInt("user_number"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_password(rs.getString("user_password"));
				user.setUser_date(rs.getDate("user_date"));
				if (rs.getString("user_active") == "1") {
					user.setUser_active(true);
				} else {
					user.setUser_active(false);
				}
				user.setUser_out_date(rs.getDate("user_out_date"));
				return user;
			} else {
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close();
		}
		return user;
	}

	/*
	 * user_id를 받아 해당 CK_User 객체 반환
	 * parameter = user_id
	 * return CK_User 객체
	 */
	public CK_User setUserByUser_id(String user_id) {
		CK_User user = null;
		SQL = "SELECT * FROM USER_MEMBER WHERE user_id = ? AND user_active = 1";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new CK_User();
				user.setUser_number(rs.getInt("user_number"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_password(rs.getString("user_password"));
				user.setUser_date(rs.getDate("user_date"));
				user.setUser_active(true);
				user.setUser_out_date(null);
				return user;
			} else {
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close();
		}
		return user;
	}

	/*
	 * 예매목록 DB에 추가
	 * parameter = user_number, m_code, watch_date. 
	 * return 1:완료, -2:서버오류
	 */
	public int ticketing(int user_number, int m_code, Date watch_date) {
		SQL = "INSERT INTO TICKET_LIST VALUES (?, ?, SYSDATE, ?, (SELECT NVL(MAX(ticket_num),0))+1 FROM TICKET_LIST WHERE user_number = ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, user_number);
			pstmt.setInt(2, m_code);
			pstmt.setDate(3, watch_date);
			pstmt.setInt(4, user_number);
			rs = pstmt.executeQuery();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	/*
	 * 조회기준날짜와 영화코드로 CK_Movie객체 반환
	 * parameter = m_code, blarRange(가칭)
	 * return CK_Movie 객체
	 */
	public CK_Movie setMovieByM_code(int m_code, Date blarRange) { // 조회기준일 변수변경
		CK_Movie movie = null;
		SQL = "SELECT * FROM MOVIE_INFO WHERE m_code = ? AND blarRange = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, m_code);
			pstmt.setDate(2, blarRange); // 조회기준일 변수변경
			rs = pstmt.executeQuery();
			if (rs.next()) {
				movie = new CK_Movie(); // 조회기준일 추가
				movie.setM_code(rs.getInt("m_code"));
				movie.setM_title(rs.getString("m_title"));
				movie.setOpen_dt(rs.getDate("open_dt"));
				movie.setClose_dt(rs.getDate("close_dt"));
				movie.setGenre_code(rs.getInt("genre_code"));
				movie.setPoster_path(rs.getString("poster_path"));
				movie.setRank(rs.getInt("rank"));
				movie.setAudi_acc(rs.getInt("audi_acc"));
				movie.setAudits(rs.getInt("audits"));
				movie.setPrice(rs.getInt("price"));
				return movie;
			} else {
				return movie;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close();
		}
		return movie;
	}
	
	//INSERT INTO WATCHED_LIST VALUES (user_number, m_code, watch_date, watch_num); // 관람목록 추가하기
	
	/*
	 * DB 연결 닫기
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
