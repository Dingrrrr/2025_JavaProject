package TeamProject;

import java.sql.Timestamp;

public class MsgBean {
	private int msg_id;
	private String msg_title;
	private String sender_id;
	private String receiver_id;
	private String msg_content;
	private Timestamp msg_date;
	
	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public Timestamp getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(Timestamp msg_date) {
		this.msg_date = msg_date;
	}
}
