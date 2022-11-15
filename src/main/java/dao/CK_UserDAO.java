/**
 * 너무 잦은 connect와 close는 접속오류를 발생 할 수 있습니다.
 * 그것도 모르고 코드만 계속 수정함..
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.CK_User;

public class CK_UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String dbID = "scott";
	private String dbPW = "tiger";
	private String SQL = "";

	public CK_UserDAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * parameter = id, password. id에 따른 비밀번호 비교 return 1:비밀번호 일치, 0:비밀번호 불일치,
	 * -1:아이디없음, -2:서버오류
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
	 * parameter = user_number user_number를 받아 해당 CK_User 객체 반환 return CK_User 객체
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
	 * parameter = user_id user_id를 받아 해당 CK_User 객체 반환 return CK_User 객체
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
			System.out.println("isHere?");
		} finally {
			close();
		}
		return user;
	}

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
