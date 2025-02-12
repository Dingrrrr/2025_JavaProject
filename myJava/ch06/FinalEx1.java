package ch06;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

/*final(마지막) : 클래스, 변수, 메소드
 * 1. 클래스 : Super클래스의 역할을 할 수 없는 클래스, 즉 Sub클래스를 가질 수 없는 클래스(java.lang.클래스들)
 * 2. 변수 : 상수선언(필드, 매개변수, 지역변수 <- 메소드 안에 선언한 변수)
 * 3. 메소드 : */

final/*제어자*/ class Final1 {
	
}
//class Final2 extends Final1 {}

class Final3 {
	
	final int KIA = 1;
	final int LOTTE = 2;
	int a;
	
	void prn(final int b/*매개변수로 입력된 갑 그대로 사용*/) {
		//b = 10;
		final int c = 10;
		//c = 20;
		
	}
	
	//상수로 선언하는 또 다른 이유
	void prn2() {
		Frame f = new Frame();
		Button btn = new Button("버튼");
		//오타를 없애기 위해서 사용.
		f.add(btn, BorderLayout.CENTER);
		f.add(btn, "Center");
		f.add(btn, "center");
		//코드의 명시적인 분석을 위해서.
		System.out.println(Frame.NORMAL); //<- 코드 분석이 즉시 가능
		System.out.println(0);
		
	}
}

class Final4 {
	void prn1() {}
	final void prn2() {}
}

class Final5 extends Final4 {
	@Override //오버라이딩 : 부모클래스에서 선언된 메소드를 자식클래스에서 재정의 하는 것.
	void prn1() {
		super.prn1(); //오버라이딩된 메소드에 부모메소드 호출은 선택사양.
	}
	
	//void prn2() {}
}

public class FinalEx1 {
	public static/*일반제어자*/ void main(String[] args) {
		Final5 f = new Final5();
		f.prn2(); //final 메소드는 상속은 가능함. 대신 자신의 클래스에 재정의 불가능.
	}
}
