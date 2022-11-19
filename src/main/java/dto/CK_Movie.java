package dto;

import java.sql.Date;

public class CK_Movie {
	private int m_code;
	private String m_title;
	private Date open_dt;
	private Date close_dt;
	private int genre_code;
	private String poster_path;
	private int rank;
	private int audi_acc;
	private String audits;
	private int price;
	
	public int getM_code() {
		return m_code;
	}
	public void setM_code(int m_code) {
		this.m_code = m_code;
	}
	public String getM_title() {
		return m_title;
	}
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}
	public Date getOpen_dt() {
		return open_dt;
	}
	public void setOpen_dt(Date open_dt) {
		this.open_dt = open_dt;
	}
	public Date getClose_dt() {
		return close_dt;
	}
	public void setClose_dt(Date close_dt) {
		Date temp = new Date(close_dt.getYear(), close_dt.getMonth(), close_dt.getDate()+30);
		this.close_dt = temp;
	}
	public int getGenre_code() {
		return genre_code;
	}
	public void setGenre_code(int genre_code) {
		this.genre_code = genre_code;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getAudi_acc() {
		return audi_acc;
	}
	public void setAudi_acc(int audi_acc) {
		this.audi_acc = audi_acc;
	}
	public String getAudits() {
		return audits;
	}
	public void setAudits(String audits) {
		this.audits = audits;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
