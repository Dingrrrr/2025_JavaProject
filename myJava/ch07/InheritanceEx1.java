package ch07;

class Car1 /*2대 클래스 : extends가 없다면 2대 클래스*/ {
	
	int velocity;
	
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

class Bus1 extends Car1 {
	@Override //어노테이션(알림, 공지)
	void speedUp() {
		super.speedUp();
		if(velocity>100)
			velocity = 100;
	}
}

class Taxi1 extends Car1 {
	
	@Override //이 메소드 오버라이딩 한다고 말함.
	void speedUp() {
		velocity+=5;
	}
	
	@Override 
	void speedDown() {
		velocity-=5;
		if(velocity<0)
			velocity = 5;
	}
	
	@Deprecated
	void prn() {
		System.out.println("보인다");
	}
}


public class InheritanceEx1 {
	public static void main(String[] args) {
		Integer it = new Integer(0); // 사선이 있는 이유는 권장하지 않는다는 뜻
		Taxi1 t = new Taxi1();
		t.prn();
	}
}
