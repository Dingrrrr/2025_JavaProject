package ch09;

class Outer2 {
	
	int a = 10; //외부클래스 필드
	void prn1() {
		System.out.println("a : " + a);
		//내부클래스 메소드 및 필드는 직접 호출 불가능
		//prn2();
		//System.out.println("b : " + b);
		Inner2 in = new Inner2();
		in.b = 30;
		in.prn2();
	}
	
	//외부클래스 입장에서는 내부클래스를 메소드의 형태로 인식
	//메소드에 없는 클래스의 많은 기능이 있다. 상속, 객체
	class Inner2 {
		int b = 20; //내부클래스 필드
		void prn2() {
			//prn1(); //외부클래스 메소드 객체 생성없이 직접 호출
			System.out.println("a + b : " + (a + b)); //외부클래스 필드 사용 가능
		}
	}//--Inner2
	
}

public class InnerEx2 {
	public static void main(String[] args) {
		//제3의 클래스에서 내부클래스 사용 방법. 사용되는 경우는 거의 없음
		Outer2 out = new Outer2();
		Outer2.Inner2 in = out.new Inner2();
		in.b = 40;
		in.prn2();
	}
}
