package ch08;

interface Interface3 {
	void prn1();
}
//인터페이스끼리 다중 상속 가능하고, 추상클래스처럼 추상메소드 구현하지 않아도 됨.
interface Interface3_1 extends Interface3 {
	void prn2();
}
interface Interface3_2 extends Interface3_1 {
	@Override
	default void prn1() {}
	
	@Override
	default void prn2() {}
}

public class InterfaceEx3 {
	public static void main(String[] args) {
		
	}
}
