/**
 * �꼫臾� �옦�� connect�� close�뒗 �젒�냽�삤瑜섎�� 諛쒖깮 �븷 �닔 �엳�뒿�땲�떎.
 * 洹멸쾬�룄 紐⑤Ⅴ怨� 肄붾뱶留� 怨꾩냽 �닔�젙�븿..
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dto.CK_Movie;
import dto.CK_User;

public class CK_DAO {
	// �쟾�뿭蹂��닔 紐⑥쓬
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String dbID = "scott";
	private String dbPW = "tiger";
	private String SQL = "";

	public CK_DAO() {
		connect();
	}

	/*
	 * id�뿉 �뵲瑜� 鍮꾨�踰덊샇 鍮꾧탳 parameter = id, password. return 1:鍮꾨�踰덊샇 �씪移�, 0:鍮꾨�踰덊샇
	 * 遺덉씪移�, -1:�븘�씠�뵒�뾾�쓬, -2:�꽌踰꾩삤瑜�
	 */
	public int login(String user_id, String user_password) {
		connect();
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
	 * user_number瑜� 諛쏆븘 �빐�떦 CK_User 媛앹껜 諛섑솚 parameter = user_number return CK_User
	 * 媛앹껜
	 */
	public CK_User setUserByUser_number(int user_number) {
		connect();
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
	 * user_id瑜� 諛쏆븘 �빐�떦 CK_User 媛앹껜 諛섑솚 parameter = user_id return CK_User 媛앹껜
	 */
	public CK_User setUserByUser_id(String user_id) {
		connect();
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
	 * �삁留ㅻぉ濡� DB�뿉 異붽� parameter = user_number, m_code, watch_date. return 1:�셿猷�,
	 * -2:�꽌踰꾩삤瑜�
	 */
	public int ticketing(int user_number, int m_code, Date watch_date) {
		connect();
		SQL = "INSERT INTO TICKET_LIST VALUES (?, ?, SYSDATE, ?, (SELECT NVL(MAX(ticket_num),0)+1 FROM TICKET_LIST WHERE user_number = ?))";
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
	 * 議고쉶湲곗��궇吏쒖� �쁺�솕肄붾뱶濡� CK_Movie媛앹껜 諛섑솚 parameter = m_code, blarRange(媛�移�)
	 * return CK_Movie 媛앹껜
	 */
	public CK_Movie setMovieByM_code(String m_code) {
		connect();
		CK_Movie movie = null;
		SQL = "SELECT * FROM MOVIE_INFO WHERE m_code = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, m_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				movie = new CK_Movie(); // 議고쉶湲곗��씪 異붽�
				movie.setM_code(rs.getInt("m_code"));
				movie.setM_title(rs.getString("m_title"));
				movie.setOpen_dt(rs.getDate("open_dt"));
				movie.setClose_dt(rs.getDate("close_dt"));
				movie.setGenre_code(rs.getInt("genre_code"));
				movie.setPoster_path(rs.getString("poster_path"));
				movie.setRank(rs.getInt("rank"));
				movie.setAudi_acc(rs.getInt("audi_acc"));
				movie.setAudits(rs.getString("audits"));
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
	

	public void chkWatched(CK_User user) {
		user.getUser_number();
		java.util.Date CK_today = new java.util.Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date sqlToday = Date.valueOf(date.format(CK_today));
		connect();
		SQL = "SELECT * FROM TICKET_LIST WHERE user_number = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, user.getUser_number());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(rs.getDate("watch_date").getYear() < sqlToday.getYear()) {
					passList(rs.getInt("user_number"), rs.getInt("m_code"), rs.getDate("watch_date"));
				}else if(rs.getDate("watch_date").getYear() == sqlToday.getYear()) {
					if(rs.getDate("watch_date").getMonth() < sqlToday.getMonth()) {
						passList(rs.getInt("user_number"), rs.getInt("m_code"), rs.getDate("watch_date"));
					}else if(rs.getDate("watch_date").getMonth() == sqlToday.getMonth()) {
						if(rs.getDate("watch_date").getDate() < sqlToday.getDate()) {
							passList(rs.getInt("user_number"), rs.getInt("m_code"), rs.getDate("watch_date"));
						}else {}
					}else {}
				}else {}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close();
		}
		
	}
	
	private void passList(int user_number, int m_code, Date watch_date) throws Exception {
		// TODO Auto-generated method stub
		SQL = "INSERT INTO WATCHED_LIST VALUES (?, ?, ?, (SELECT NVL(MAX(watch_num),0)+1 FROM WATCHED_LIST WHERE user_number = ?))";
		pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, user_number);
		pstmt.setInt(2, m_code);
		pstmt.setDate(3, watch_date);
		pstmt.setInt(4, user_number);
		rs = pstmt.executeQuery();
		SQL = "DELETE INTO TICKET_LIST WHERE user_number = ?";
		pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, user_number);
		rs = pstmt.executeQuery();
	}
	
	
	public void connect() {
		try {
			close();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * DB �뿰寃� �떕湲�
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
