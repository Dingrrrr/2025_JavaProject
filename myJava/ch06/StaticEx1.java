package ch06;

/*static(고정된) : 필드, 메소드, 클래스(조건)
 *1. 필드, 메소드 : 객체 생성없이 클래스명으로 JVM이 메모리에 로딩하여 사용. 
 *2. 클래스(조건) : 내부 클래스 앞에 사용 가능.
 **/

class Static1 {
	
	static class Innser {} // <- 조건적으로 외부 클래스에 static을 정의할 수 있음.
	
	static int a = 10;
	
	static void prn1() {
		System.out.println(a);
	}
}

public class StaticEx1 {
	public static void main(String[] args) {
		//static으로 선언된 필드 및 메소드는 객체 생성없이 클래스명으로 접근하여 사용 가능.(유틸성)
		Static1.a = 20;
		Static1.prn1();
		//객체 생성을 해서 사용해도 문법적으로는 문제는 없지만, 일반적이진 않음.
		Static1 st1 = new Static1();
		st1.a = 30;
		st1.prn1();
		//Math m = bew Math(); <- Math는 객체 생성이 안됨.
		System.out.println(Math.abs(-10));
		System.out.println(Math.PI);
		String str1 = "10";
		String str2 = "20";
		System.out.println(str1 + str2);
		System.out.println(Integer.parseInt(str1) + Integer.parseInt(str2));
		//PI, str1, 2, parseInt는 클래스명으로 접근, static 메소드
	}
}