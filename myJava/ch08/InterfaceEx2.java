package ch08;

interface Interface2 {
	//필드는 자동적으로 상수가 되기 때문에 반드시 초기화를 줘야 함.
	int a = 0;
	void prn1();
	
	static void prn2() {System.out.println("static 메소드");}
	default void prn3() {System.out.println("default 메소드");}
}

class MyClass2 implements Interface2 {
	@Override
	public void prn1() {
	}
}

public class InterfaceEx2 {
	public static void main(String[] args) {
		MyClass2 mc = new MyClass2();
		Interface2.prn2(); //인터페이스 이름으로 접근 사용
		mc.prn3(); // 구현된 클래스의 레퍼런스 변수명으로 사용
	}
}
