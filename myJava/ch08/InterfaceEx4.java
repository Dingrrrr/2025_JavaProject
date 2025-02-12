package ch08;

interface Interface4 {
	void prn1();
}

interface Interface4_1 {
	void prn2();
}

//여러개의 인터페이스를 클래스에서 구현(implement) 할 수 있음.
class MyClass4 implements Interface4, Interface4_1 {
	@Override
	public void prn1() {
	}
	@Override
	public void prn2() {
	}
}

public class InterfaceEx4 {
	public static void main(String[] args) {
		
	}
}
