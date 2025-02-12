package ch02;

public class Ex2 {

	public static void main(String[] args) {
		// 예약어로 된 단어는 선언 불가능
		// int void;
		int a = 10;
		//c = a;
		int b = a;
		int c = 20;
		
		if(a >= 10) {
			System.out.println("실행되나요?");
			int d = 30;
			System.out.println(a+d);
		}
		//System.out.println(a+d);
		
	}

}
