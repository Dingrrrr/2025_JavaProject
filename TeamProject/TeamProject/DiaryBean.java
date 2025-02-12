package TeamProject;

import java.sql.Timestamp;

public class DiaryBean {
	private int diary_id;
	private String diary_name;
	private int pet_id;
	private Timestamp diary_date;
	private String diary_content;
	
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	public String getDiary_name() {
		return diary_name;
	}
	public void setDiary_name(String diary_name) {
		this.diary_name = diary_name;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public Timestamp getDiary_date() {
		return diary_date;
	}
	public void setDiary_date(Timestamp diary_date) {
		this.diary_date = diary_date;
	}
	public String getDiary_content() {
		return diary_content;
	}
	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}
}
