package ch08;

//인터페이스
interface Calc {
	//추상메소드 : abstract는 사용하지 않아도 자동으로 됨.
	void plus(int a, int b);
	//void prn() {} 일반메소드는 선언 불가
}

class Function extends Object implements Calc {
	@Override
	public void plus(int a, int b) {
	}
}

class Graphic implements Calc {
	@Override
	public void plus(int a, int b) {
	}
}

public class InterfaceEx1 {
	public static void main(String[] args) {
		Calc c; //타입으로는 가능
		//c = new Calc(); 실제 객체 생성 불가능
		c = new Function();
	}
}
