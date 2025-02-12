package ch06;

//클래스명(풀네임) : ch06.Car1
//같은 파일에 여러개 class 정의 가능함. 그러나 각자의 클래스가 만드러짐. Car1.class, CarEx1.class
// 클래스 선언 : 객체를 만드는 틀
class Car1/*클래스 명*/ {
	//필드(filed) : 객체의 속성 <- 무조건 ()가 없다. 소문자로 시작, 카멜표기법
		String carName;
		int velocity;
		String carColor;
		
	//메소드(method) : 객체의 기능(동작) <- 무조건 ()가 있다. 소문자 시작, 카멜표기법
	void speedUp() {
		velocity++;
	}
	
	void speedDown() {
		velocity--;
		if(velocity<0)
			velocity = 0;
	}
	
	void stop() {
		velocity = 0;
	}
}

//파일명은 반드시 public 선언된 클래스 이름을 저장 : CarEx1.java
public class CarEx1 {

	public static void main(String[] args) {
		//클래스를 가지고 객체를 생성
		Car1 c1/*레퍼런스 변수*/ = new/*객체생성키워드*/ Car1(); //Car1 c1 = new Car1(); -> new : 객체를 생성하는 예약어.(모든 객체는 new를 통해서만 생성이 된다.)
		c1.carName = "카마로 머스탱";
		c1.carColor = "노란색";
		c1.speedUp();//메소드 호출
		System.out.println(c1.carName);
		System.out.println(c1.carColor);
		System.out.println(c1.velocity);
		Car1 c2 = new Car1();
		System.out.println("c1 : " + c1);
		System.out.println("c2 : " + c1);
	}

}
