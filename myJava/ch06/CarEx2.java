package ch06;

//동일한 패키지 내에는 동일한 클래스 생성 불가능.
//Car2 타입은 참조형(reference)
class Car2 {
	
	void stop() {
		velocity = 0;
	}
	
	//객체가 생성될때 순서는 필드가 먼저 생김 -> 메소드 생김
	int velocity;
	
}

public class CarEx2 {

	public static void main(String[] args) {
		int a = 10;
		int b = a;//call by value
		a = 15;
		System.out.println(a + " : " + b);
		
		Car2 c1 = new Car2();
		Car2 c2 = new Car2();
		c1.velocity = 100;
		c2.velocity = 200;
		System.out.println(c1);
		System.out.println(c2);
		c1 = c2; //call by reference
		System.out.println("-------------------------");
		System.out.println(c1);
		System.out.println(c2);
		//문제. c1.velocity + c2.velocity 출력? c1 = c2;이므로 같이 200을 바라보고 있어서 400이 출력됨.
		System.out.println(c1.velocity + c2.velocity);
		System.out.println("-------------------------");
		for (int i = 0; i < 10; i++) {
			Car2 c3 = new Car2();
			System.out.println(c3);
		}
		
		//객체를 생성하는 이유는? 필드의 메소드 사용
		String str = new String("qwerqwerqwer");
		System.out.println(str.toUpperCase());
		System.out.println(str.length());
		System.out.println(str.replace('a', 'x'));
		
		//객체 생성없이도 클래스명으로 메소드 및 필드 사용가능
		System.out.println(Integer.toBinaryString(32));
		System.out.println(Math.PI);
	}

}
