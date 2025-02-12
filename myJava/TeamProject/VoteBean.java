package TeamProject;

import java.sql.Timestamp;

public class VoteBean {
	private int vote_id;
	private String user_id;
	private int pet_id;
	private int vote_like;
	private String vote_image;
	private Timestamp vote_date;
	
	public int getVote_id() {
		return vote_id;
	}
	public void setVote_id(int vote_id) {
		this.vote_id = vote_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public int getVote_like() {
		return vote_like;
	}
	public void setVote_like(int vote_like) {
		this.vote_like = vote_like;
	}
	public String getVote_image() {
		return vote_image;
	}
	public void setVote_image(String vote_image) {
		this.vote_image = vote_image;
	}
	public Timestamp getVote_date() {
		return vote_date;
	}
	public void setVote_date(Timestamp vote_date) {
		this.vote_date = vote_date;
	}
}
