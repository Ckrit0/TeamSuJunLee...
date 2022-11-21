package dto;

import java.sql.Date;

public class CK_User {
	private int user_number;
	private String user_id;
	private String user_password;
	private Date user_date;
	private boolean user_active;
	private Date user_out_date;

	//°Ù¼Â¸ğÀ½
	public int getUser_number() {
		return user_number;
	}
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public Date getUser_date() {
		return user_date;
	}
	public void setUser_date(Date user_date) {
		this.user_date = user_date;
	}
	public boolean isUser_active() {
		return user_active;
	}
	public void setUser_active(boolean user_active) {
		this.user_active = user_active;
	}
	public Date getUser_out_date() {
		return user_out_date;
	}
	public void setUser_out_date(Date user_out_date) {
		this.user_out_date = user_out_date;
	}
	
	
}
