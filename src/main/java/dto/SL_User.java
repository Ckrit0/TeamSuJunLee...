package dto;

public class SL_User {
	private int user_Number;
	private String user_ID;
	private String user_Password;
	private String user_Password_re;
	private String user_Date;
	private String user_Active;
	
	
	public int getUser_Number() {
		return user_Number;
	}
	public void setUser_Number(int user_Number) {
		this.user_Number = user_Number;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getUser_Password() {
		return user_Password;
	}
	public void setUser_Password(String user_Password) {
		this.user_Password = user_Password;
	}
	public String getUser_Password_re() {
		return user_Password_re;
	}
	public void setUser_Password_re(String user_Password_re) {
		this.user_Password_re = user_Password_re;
	}
	public String getUser_Date() {
		return user_Date;
	}
	public void setUser_Date(String user_Date) {
		this.user_Date = user_Date;
	}
	public String getUser_Active() {
		return user_Active;
	}
	public void setUser_Active(String user_Active) {
		this.user_Active = user_Active;
	}
}
