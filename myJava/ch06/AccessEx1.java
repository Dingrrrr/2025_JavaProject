package ch06;

/*접근 제어자 : 클래스, 필드, 메소드
 * 1. private(0.1%) : 어디에서나 접근 가능.
 * 2. protected(0.01%) : 상속 또는 같은 패캐지에서는 사용 가능.
 * 3. public(99.9%) : 자신의 클래스({}) 안에서만 사용 가능.
 * 4. 선언을 안한 경우
 * */

class Access1 {
	public int a = 0; //어디에서나 접근 가능.
	protected int b = 0; //상속 또는 같은 패캐지에서는 사용 가능.
	private int c = 0; //자신의 클래스 안에서만 사용 가능.
	
	private int speed = 0;
	public void setSpeed(int v) {
		this.speed = v;
		if(speed < 0) 
			speed = 0;
		}
	public int getSpeed() {
			return speed;
	}
}

public class AccessEx1 {
	public static void main(String[] args) {
		Access1 ac1 = new Access1();
		System.out.println(ac1.a);
		System.out.println(ac1.b);
		//System.out.println(ac1.c);
		//ac1.speed = -10;
		ac1.setSpeed(-100);
		System.out.println("speed : " + ac1.getSpeed());
		//Math 클래스의 생성자는 아주 특이하게 private
		//Math ma = new Math();
	}
}
