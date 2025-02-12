package ch03;

public class Ex1 {

	public static void main(String[] args) {
		int a = 1;
		int b = 1;
		int a1 = a++; // 대입 후에 증감
		int b1 = ++b; // 증강 후에 대입
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		System.out.println("a1 : " + a1);
		System.out.println("b1 : " + b1);
	}

}
