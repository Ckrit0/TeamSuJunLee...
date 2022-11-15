package dto;

import java.time.LocalDateTime;

public class MovieInfo {
	private String showRange;
	private int m_code;
	private String m_title;
	private LocalDateTime open_dt;
	private LocalDateTime close_dt;
	private int genre_code;
	private String poster_path;
	private int rank;
	private int audi_acc;
	private String audits;
	
	public String getShowRange() {
		return showRange;
	}
	public void setShowRange(String showRange) {
		this.showRange = showRange;
	}
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
	
	public LocalDateTime getOpen_dt() {
		return open_dt;
	}
	public void setOpen_dt(LocalDateTime open_dt) {
		this.open_dt = open_dt;
	}
	public LocalDateTime getClose_dt() {
		return close_dt;
	}
	public void setClose_dt(LocalDateTime close_dt) {
		this.close_dt = close_dt;
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
	private int price;

	

	
	
	
}
