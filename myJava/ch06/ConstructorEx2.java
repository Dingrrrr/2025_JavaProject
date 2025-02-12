package ch06;

import java.awt.Dialog;
import java.awt.Frame;

class Constructor2 {
	//생성자가 선언되어 있지 않으면 디폴트 생성자 제공 <- JVM(컴파일 시점에...)
	//생성자가 하나라도 선언이 되어 있으면 디폴트 생성자를 제공하지 않음.
	public Constructor2() {}
	public Constructor2(int a) {
		
	}
}

public class ConstructorEx2 {

	public static void main(String[] args) {
		Constructor2 c1 = new Constructor2();
		Constructor2 c2 = new Constructor2(22);
		//Dialog 클래스는 디폴트 생성자는 선언되어 있지 않음.
		Dialog d = new Dialog(new Frame());
	}
}
