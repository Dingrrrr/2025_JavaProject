package ch03;

public class Ex4 {

	public static void main(String[] args) {
		//논리연산 : &, &&, |, ||
		//홍길동은 키도 크고 잘 생겼다.
		//홍길동은 키가 크거나 또는 잘 생겼다.
		int a = 10;
		int b = 4;
		int c = 0;
		System.out.println((a==b)&(a>b));
		System.out.println((a==b)&&(a>b));
		System.out.println((a==b)&&(a/c==b));
		//System.out.println((a==b)&(a/c==b));
		System.out.println("**************");
		System.out.println((a!=b)|(a>b));
		System.out.println((a!=b)||(a>b));
		System.out.println("**************");
		//XOR : 배타적 논리 연산 <- 서로 값이 다르면 true
		System.out.println((a==b/*f*/)^(a>b/*t*/)); //true
		System.out.println((a!=b)^(a>b));
		System.out.println(true^false);
		System.out.println(true^true);
		System.out.println(false^false);
	}
}
