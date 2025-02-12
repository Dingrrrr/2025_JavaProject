package ch03;

import java.util.Scanner;

public class Ex5 {

	public static void main(String[] args) throws Exception {
		/*Scanner와 %, &&, while를 사용하여 입력한 값이 3의 배수인지 아닌지 메시지를 보여주세요.*/
		/*일회성이 아닌 연속성 구현*/
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하세요.");
		System.out.println("3의 배수를 입력하지 않으면 종료됩니다.");
		while(true) {
		int a = sc.nextInt();
		if(a!=0 && a%3==0) {
			System.out.println(a + "는 3의 배수입니다.");
		}
		else {
			System.out.println(a + "는 3의 배수가 아닙니다.");
			break;
		}
		}
	System.out.println("프로그램이 종료되었습니다.");
	}
}
