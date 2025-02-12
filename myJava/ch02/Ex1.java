package ch02;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

//클래스명은 반드시 첫번째 철자가 대문자로 시작, 클래스명.java 만들어짐
public class Ex1 {
	public static void main(String[] args) {

		int a; // 변수 선언
		a = 10; // 변수에 값(value) 대입
		int b = 20;
		// int c = 3.14;
		String str = "자바";
		System.out.println(a);
		System.out.println(str);

		int x, y, z;
		x = 10;
		System.out.println(x);

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		
		Ex2 ex = new Ex2();
	}

}
