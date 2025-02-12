package TeamProject;

import java.sql.Timestamp;

public class ComuBean {
	private int post_id;
	private String user_id;
	private String comu_title;
	private String comu_content;
	private Timestamp comu_date;
	private String comu_image;
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getComu_title() {
		return comu_title;
	}
	public void setComu_title(String comu_title) {
		this.comu_title = comu_title;
	}
	public String getComu_content() {
		return comu_content;
	}
	public void setComu_content(String comu_content) {
		this.comu_content = comu_content;
	}
	public Timestamp getComu_date() {
		return comu_date;
	}
	public void setComu_date(Timestamp comu_date) {
		this.comu_date = comu_date;
	}
	public String getComu_image() {
		return comu_image;
	}
	public void setComu_image(String comu_image) {
		this.comu_image = comu_image;
	}
}
