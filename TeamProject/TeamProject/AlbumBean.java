package TeamProject;

import java.sql.Timestamp;

public class AlbumBean {
	private int album_id;
	private int pet_id;
	private String album_desc;
	private String album_tags;
	private byte[] album_image;
	private Timestamp album_date;
	
	
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public String getAlbum_desc() {
		return album_desc;
	}
	public void setAlbum_desc(String album_desc) {
		this.album_desc = album_desc;
	}
	public String getAlbum_tags() {
		return album_tags;
	}
	public void setAlbum_tags(String album_tags) {
		this.album_tags = album_tags;
	}
	public byte[] getAlbum_image() {
		return album_image;
	}
	public void setAlbum_image(byte[] album_image) {
		this.album_image = album_image;
	}
	public Timestamp getAlbum_date() {
		return album_date;
	}
	public void setAlbum_date(Timestamp album_date) {
		this.album_date = album_date;
	}
	
	
}
