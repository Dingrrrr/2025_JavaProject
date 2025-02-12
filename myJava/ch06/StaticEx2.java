package ch06;

class Static2 {
	
	static int a;
	int b; //non-static 필드 : 객체가 생성 될때 만 만들어짐.
	
	static void prn1() {
		/*b는 반드시 객체를 생성해야 사용 가능한 필드이기 때문에 객체 생성 없이 사용 가능한 메소드
		* static 메소드에서는 사용 불가능. 
		* 그러나 반대는 가능 <- prn2 메소드*/
		//System.out.println(a+b);
	}
	
	//non-static 메소드
	void prn2() {
		System.out.println(a+b);
	}
	
}

public class StaticEx2 {
	public static void main(String[] args) {
		Static2 st1 = new Static2();
		Static2 st2 = new Static2();
		st1.a = 10;
		st1.b = 10;
		st2.a = 20;
		st2.b = 20;
		System.out.println(st1.a); //정적 필드는 정적 방식으로 억세스 해야함. 
		System.out.println(st1.b);
		System.out.println(st2.a);
		System.out.println(st2.b);
	}
}