package ch06;

import java.awt.Frame;

/* protected로 선언된 필드 및 메소드는 사용하는 방법 : 상속, 같은 패키지
 * 그러나 SUN에서 재공되는 클래스와 같은 패키지를 만다는 것은 거의 불가능.
 * 결론적으로 사용 할 수 있는 방법은 상속 밖에 없음. */
class Access2 extends Frame {
	
	public Access2() {
		setSize(200, 200);
		setLocation(100, 200);
		setVisible(true);
	}
	
	//paramString 호출
	public void prn() {
		System.out.println(paramString());
	}
	
}

public class AccessEx2 {
	public static void main(String[] args) {
		Access2 f = new Access2();
		f.prn();
	}
}