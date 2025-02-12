package ch02;

public class Ex3 {

	public static void main(String[] args) {
		//데이터 타입(Data Type) : 기본형(8) + 참조형(Class Type : 무한대)
		//정수형(4)
		byte b = 10;
		short s = 20;
		int i = 30; //기본형
		long l = 40;
		
		//실수형(2)
		//리터럴 기본값이 double이기 때문에 명시적으로 float 변환을 위한 표시
		float f = 3.14f; //리터럴 값에 F/f 표기
		double d = 6.28; //기본값
		
		//문자형(1)
		//기본형은 타입에 맞는 값을 변수로 사용하기 위한 기본 기능만 있음.
		//참조형은 클래스 타입이기 때문에 필드와 메소드를 사용할 수 있음.
		char c = 'A';
		char c1 = '가';
		String/*문자열*/ str = "Java"; //참조형
		System.out.println(str.toUpperCase()); 
		
		//불린형
		boolean bl = false; //true
		//short형과 double형의 최소값과 최대값을 출력하시오
		System.out.println(Short.MIN_VALUE);
		System.out.println(Short.MAX_VALUE); //32767
		//4.9E-324 : 0.00000....49(324개의 소수점 뒤에 0이 있고 마지막에 49가 위치한 값)
		System.out.println(Double.MIN_VALUE);
		//1.7976931348623157E308 : 1.7976931348623157....(뒤에 308자리 숫자)
		System.out.println(Double.MAX_VALUE);
		
		short s1 = (short)32768;
		System.out.println(s1);
		
		int i2 = 200;
		long l2 = i2; //묵시적 형 변환(작은 것에서 큰 것으로)
		
		long l3 = 300;
		int i3 = (int)l3; //명시적 형 변환
	}

}
