package ch06;

import com.mysql.cj.jdbc.Driver;
import java.awt.Frame;
//기본적으로 같은 패키지이면 import의 필요가 없음. 그러나 다른 패키지라면 반드시 import가 필요.
import java.lang.*;//생략
import java.util.Date;

//패키지명 : 도메인 거꾸로. http://mysql.com
public class PackageEx1 {
	public static void main(String[] args) throws Exception {
		Driver d = new Driver();
		String str = new String();
		Frame f = new Frame();
		//자주 사용하는 클래스는 import
		Date date = new Date();
		//적게 사용하는 클래스 full name
		java.sql.Date date2 = new java.sql.Date(0);
	}
}
