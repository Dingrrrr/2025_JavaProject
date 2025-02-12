package ch07;

class Animal {
	
	String name;
	
	void move() {
		System.out.println("동물이 움직여라");
	}
}

class Bird extends Animal {
	@Override
	void move() {
		name = "새";
		System.out.println(name + "날아라");
	}
}

class Fish extends Animal {
	@Override
	void move() {
		name = "물고기";
		System.out.println(name + "헤엄쳐라");
	}
}

class Cheetah extends Animal {
	@Override
	void move() {
		name = "치타";
		System.out.println(name + "달려라");
	}
}

public class CastingEx3 {
	public static void main(String[] args) {
		Animal ani[] = new Animal[3];
		ani[0] = new Bird(); //상위클래스의 레퍼런스 변수는 하위클래스 객체를 가르킬 수 있음.
		ani[1] = new Fish();
		ani[2] = new Cheetah();
		for (int i = 0; i < ani.length; i++) {
			//동적바인딩 : Override된 메소드는 하위클래스 메소드가 호출. 실행사점 JVM 결정
			ani[i].move();
		}
		Fish f = new Fish();
		Object obj = f;
		System.out.println(f);
		System.out.println(obj);
	
		Animal ani2 = new Animal();
		//강제로 Casting 경우 컴파일 시점은 에러가 발생되지 않고, 실행 시점에 에러 발생
		Bird d = (Bird)ani2;
	}
}
