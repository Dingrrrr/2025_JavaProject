package member2;

//테이블명 + Bean
public class ZipcodeBean {
	
	// table 컬럼명 -> Bean -> form name까지 일치
	private String zipcode;
	private String area1;
	private String area2;
	private String area3;
	
	//자바 db연동은 무조건 Beans를 만들어야한다.
	public String getZipcode/*getXxx*/() {
		return zipcode;
	}
	public void setZipcode/*setXxx*/(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getArea1() {
		return area1;
	}
	public void setArea1(String area1) {
		this.area1 = area1;
	}
	public String getArea2() {
		return area2;
	}
	public void setArea2(String area2) {
		this.area2 = area2;
	}
	public String getArea3() {
		return area3;
	}
	public void setArea3(String area3) {
		this.area3 = area3;
	}
	
	
}
