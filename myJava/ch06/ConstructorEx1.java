package ch06;

//생성자 : 객체를 생성하는 아주 특별한 메소드
class Constructor1 {
	
	//생성자 : 반드시 클래스명과 동일한 이름으로 선언된 메소드, 리턴 타입 자체가 없다.
	//JVM이 클래스가 컴파일 시점에 생성자가 한개라도 없으면 디폴트 생성자를 제공.
	Constructor1() {
		System.out.println("디폴트 생성자");
	}
	Constructor1(int a) {
		System.out.println("매개변수 int형");
	}
	Constructor1(String s) {
		System.out.println("매개변수 String형");
	}
}

public class ConstructorEx1 {

	public static void main(String[] args) {
		Constructor1 c1 = new Constructor1(); // new 연산자 뒤에는 생성자가 와야함.
		//15개의 생성자가 존재 -> 다양한 상황에서 객체가 생성이됨. 
		String str = new String();
	}
}
