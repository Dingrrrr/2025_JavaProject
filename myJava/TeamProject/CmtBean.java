package TeamProject;

import java.sql.Timestamp;

public class CmtBean {
	private int cmt_id;
	private int post_id;
	private String user_id;
	private String cmt_content;
	private Timestamp cmt_date;
	
	public int getCmt_id() {
		return cmt_id;
	}
	public void setCmt_id(int cmt_id) {
		this.cmt_id = cmt_id;
	}
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
	public String getCmt_content() {
		return cmt_content;
	}
	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}
	public Timestamp getCmt_date() {
		return cmt_date;
	}
	public void setCmt_date(Timestamp cmt_date) {
		this.cmt_date = cmt_date;
	}
}
