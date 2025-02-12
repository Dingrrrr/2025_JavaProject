package ch02;

import java.util.Scanner;

public class Ex8 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요 : ");
		String name = sc.nextLine(); // 입력한 문자열을 리턴
		System.out.print("나이를 입력하세요 : ");
		int age = sc.nextInt(); // 입력한 정수 리턴
		// \n(특수문자)은 한줄 내림
		System.out.printf("\n%s님의 나이는 %d살 입니다.", name, age);
		
	}

}
