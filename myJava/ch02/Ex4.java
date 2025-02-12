package ch02;

public class Ex4 {

	public static void main(String[] args) {
		//자바의 인코딩은 UTF-8(유니코드) : 1 ~ 4 바이트 크기
		//A -> 65 -> 0100 0001 : CPU 전달
		int 키, 나이;
		출력하기("오늘은 행복한 목요일");
		
		char a1 = 'A';
		char a2 = 65;
		char a3 = '\u0041';
		System.out.println(a1 + " : " + a2 + " : " + a3);
		
		//영어, 특수문자 : 1바이트, 한글 : 2바이트
		char b1 = '가';
		char b2 = 44032;
		char b3 = '\uac00';
		System.out.println(b1 + " : " + b2 + " : " + b3);
	}
	
	public static void 출력하기(String 문자) {
		System.out.println(문자);
	}
}
