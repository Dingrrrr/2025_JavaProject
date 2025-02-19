package TeamProject;

public class UserBean {
	private String user_id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private byte[] user_image; // String에서 byte[]로 변경
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public byte[] getUser_image() { // 반환 타입을 byte[]로 변경
        return user_image;
    }
    public void setUser_image(byte[] user_image) { // 파라미터 타입을 byte[]로 변경
        this.user_image = user_image;
    }
}
