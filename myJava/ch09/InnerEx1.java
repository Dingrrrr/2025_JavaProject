package ch09;

/*중첩(내부) 클래스
 * 1. 클래스 안에 선언을 한다.(70%)
 * 2. 클래스 안에 선언이지만 static 클래스 선언(0.1%)
 * 3. 메소드 안에 클래스 선언.(01%)
 * 4. 메소드 안에 선언을 하지만 익명 클래스(30%)
 * 메소드 안에 독립적인 메소드 선언은 불가
 * */

interface MyInner {
	void prn();
}

class Outer {
	/*1*/class Inner1{}
	/*2*/static class Inner2{}
	void method() {
		/*3*/class Inner3{}
		/*4*/new MyInner() {
			
			@Override
			public void prn() {
			}
		};
	}
}

public class InnerEx1 {

}
