package SL_JAVA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
	private int user_Number;
	private String user_ID;
	private String user_Password;
	private String user_Date;
	
	private Connection con;
	private ResultSet rs;
		
	public UserDAO() {
		try {
			String db_url = "jdbc:oracle:thin:@localhost:1521:orcl"; // 접속 DB정보
			String db_id = "scott"; // 접속 아이디
			String db_pw = "tiger"; // 접속 아이디의 비밀번호
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(db_url, db_id, db_pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인
	/*
	 * -2: 아이디없음
	 * -1: 서버오류
	 * 0: 비밀번호 틀림
	 * 1: 성공
	 */
	public int login(String userID, String userPassword) {
		try {
			PreparedStatement pst = con.prepareStatement("SELECT user_Password FROM User_Member WHERE user_ID = ?");
			pst.setString(1, userID);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString(1).equals(userPassword) ? 1 : 0;
			} else {
				return -2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// 중복여부 확인
	public boolean ID_Check(String userID) {
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM User_Member WHERE user_ID = ?");
			pst.setString(1, userID);
			rs = pst.executeQuery();
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
	 * -1: 서버오류
	 * 0: 이미 존재하는 아이디
	 * 1: 성공
	 */
	public int join(UserDAO userDAO) {
		if(!ID_Check(userDAO.getUserID())) return 0;
		
		try {
			PreparedStatement pst = con.prepareStatement("INSERT INTO User_Member(USER_NUMBER, user_ID, user_Password, user_Date) VALUES (seq_user_Number.nextval, ?, ?, ?)");
			pst.setString(1, userDAO.getUserID());
			pst.setString(2, userDAO.getUserPassword());
			pst.setString(3, userDAO.getUserDate());
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// 유저 데이터 가져오기
	public UserDAO getUser(String userID) {
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM User_Member WHERE user_ID = ?");
			pst.setString(1, userID);
			rs = pst.executeQuery();
			if (rs.next()) {
				UserDAO userDAO = new UserDAO();
				userDAO.setUserNumber(rs.getInt(1));
				userDAO.setUserID(rs.getString(2));
				userDAO.setUserPassword(rs.getString(3));
				userDAO.setUserDate(rs.getString(4));
				return userDAO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getUserNumber() {
		return user_Number;
	}
	public void setUserNumber(int userNumber) {
		this.user_Number = userNumber;
	}
	public String getUserID() {
		return user_ID;
	}
	public void setUserID(String userID) {
		this.user_ID = userID;
	}
	public String getUserPassword() {
		return user_Password;
	}
	public void setUserPassword(String userPassword) {
		this.user_Password = userPassword;
	}
	public String getUserDate() {
		return user_Date;
	}
	public void setUserDate(String userDate) {
		this.user_Date = userDate;
	}
}
