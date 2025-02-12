package ch06;

class Method2 {
	
	/*오버로딩(Over loading) : 같은 클래스 내에 동일한 메소드명으로 배개변수 개수와 타입을 
	달리해서 선언하고, 당연히 기능은 같음*/
	void prn(int a) {
		System.out.println(a);
	}
	void prn(int a, int b) {
		System.out.println(a + "\t" + b);
	}
	void prn(int a, int b, int c) {
		System.out.println(a + "\t" + b + "\t" + c);
	}
	
	//가변인수
	// ... : 갯수에 상관없이 다 받겠다는 뜻
	void prnf(int...arr/*자동으로 배열로 선언됨.*/) {
		for (int i = 0; i < arr.length; i++) {
			System.out.printf(arr[i] + "\t");
		}
		System.out.println();
	}
}

public class MethodEx2 {

	public static void main(String[] args) {
		//가변인수
		System.out.printf("%s, %d, %f", "문자열", 22, 3.14);
		Method2 m2 = new Method2();
		m2.prn(10);
		m2.prn(10, 20);
		m2.prn(10, 20, 30);
		//m2.prn(10, 20, 30, 40);
		m2.prnf(1);
		m2.prnf(1, 2, 3);
		m2.prnf(1, 2, 3, 4, 5, 6, 7, 8);
	}
}
