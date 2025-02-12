package ch07;

import java.awt.Component;

//추상클래스 : 추상적으로 존재하는 클래스이고, 타입으로만 사용 가능 <- 실제 객체 생성 불가능
abstract class Abstract1 {
	
	//추상 메소드 : 일반적으로 추상클래스는 추상메소드를 가지고 있음.
	abstract void prn(); //메소드 선언부
	
}

class Normal1 extends Abstract1 {
	
	@Override //추상클래스의 추상메소드는 반드시 강제성을 가지고 오버라이딩을 해야함.
	void prn() {}
	
}

//그러나 추상클래스는 반드시 추상메소드를 가질 필요는 없다.
class Mcomponent extends Component {}

public class AbstractEx1 {
	public static void main(String[] args) {
		Abstract1 at; //참조형 타입으로 가능
		//at = new Abstract1();
		at = new Normal1();
	}
}
