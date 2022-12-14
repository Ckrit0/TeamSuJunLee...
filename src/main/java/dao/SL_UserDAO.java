package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.SL_User;

public class SL_UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String dbID = "scott";
	private String dbPW = "tiger";
	private String SQL = "";

	public SL_UserDAO() {
		// TODO Auto-generated constructor stub
		connect();
		
	}

	private void connect() {
		// TODO Auto-generated method stub
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
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 중복여부 확인
	public boolean ID_Check(String userID) {
		connect();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM User_Member WHERE user_ID = ?");
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 회원가입
	/*
	 * -1: 서버오류 0: 이미 존재하는 아이디 1: 성공
	 */
	public int join(SL_User user) {
		connect();
		if (!ID_Check(user.getUser_ID()))
			return 0;
		int result = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO User_Member(USER_NUMBER, user_ID, user_Password, user_Date, user_Active) VALUES (seq_user_Number.nextval, ?, ?, ?, '1')");

			pst.setString(1, user.getUser_ID());
			pst.setString(2, user.getUser_Password());
			pst.setString(3, user.getUser_Date());
			result = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		return result;
	}

	// 유저 데이터 가져오기
	public SL_User getUser(String userID) {
		connect();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM User_Member WHERE user_ID = ?");
			pst.setString(1, userID);
			rs = pst.executeQuery();
			if (rs.next()) {
				SL_User user = new SL_User();
				user.setUser_Number(rs.getInt(1));
				user.setUser_ID(rs.getString(2));
				user.setUser_Password(rs.getString(3));
				user.setUser_Date(rs.getString(4));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int SL_update_User(String userPassword, String userDate, int user_Number) {
		connect();
		String sql = "UPDATE User_Member " + " SET user_Password = ?, user_Date = ? WHERE user_Number = ?";
		int result =0;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPassword);
			pstmt.setString(2, userDate);
			pstmt.setInt(3, user_Number);
			
			result = pstmt.executeUpdate();
				System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

	public int SL_Delete_User(int user_Number) {
		connect();
		String sql = "UPDATE User_Member SET user_Active = 0, user_out_date = sysdate WHERE user_Number = ?";
		int result = 0;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_Number);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public int UserNumber(String userID) {
		connect();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT user_Number FROM User_Member WHERE user_ID = ?");
			pst.setString(1, userID);
			rs = pst.executeQuery();
			if (rs.next()) {
				SL_User user = new SL_User();
				user.setUser_Number(rs.getInt("user_Number"));
				return user.getUser_Number();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getUserPassword(int userNum) {
		connect();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT user_Password FROM User_Member WHERE user_Number = ?");
			pst.setInt(1, userNum);
			rs = pst.executeQuery();
			if (rs.next()) {
				SL_User user = new SL_User();
				user.setUser_Password(rs.getString("user_Password"));
				return user.getUser_Password();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
