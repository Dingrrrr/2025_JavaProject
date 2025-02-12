package ch06;

import java.awt.Frame;

class Super3 extends Object/*클래스 위에 extends가 생략 되었다면 Object가 부모*/ {
	//super 누구지?
	Super3() {
		super(); //생략되어 있고 생성자의 항상 첫 번째 라인
		System.out.println("Super3 생성자");
	}
}

class Sub3 extends Super3 {
	
	public Sub3() {
		super();
		System.out.println("Sub3 생성자");
	}
}

public class ConstructorEx3 {

	public static void main(String[] args) {
		//기본적으로 8개의 메소드 사용가능 <- Object 선언
		Sub3 s3 = new Sub3();
		Frame f = new Frame();
	}
}
