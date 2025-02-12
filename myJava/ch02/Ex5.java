package ch02;

public class Ex5 {

	public static void main(String[] args) {
		//Data Type : 기본형(class 타일) : sun 제공 + 외부 lib + 현재 선언된 클래스
		int a = 10; //기본형은 기능이 없는 타입
		// 클래스는 객체를 만드는 틀 -> 필드(객체의 속성) + 메소드(객체의 기능)
		// String 클래스 : 문제열을 객체화 ex) 문자열 길이 : lenght()
		String str1 = "qergeerqe"; //유일하게 new 없이 객체 생성 가능
		String str2 = new String("qwerqwer");
		System.out.println(str1.length());
		//Math 클래스 : 수학의 기능을 객체화 시킨 클래스 ex) PI(원주율 필드), 올림, 내림, 반올림, 절대값
		//반지름이 5인 원의 넓이를 출력하시오. 클래스.필드 or 클래스.메소드
		System.out.println(Math.PI*5*5);
		System.out.println(Math.abs(-10));
		
	}

}
