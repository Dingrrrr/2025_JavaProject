package ch10;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionEx3 {
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			int a, b;
			System.out.println("첫 번째 숫자 : ");
			a = sc.nextInt();
			System.out.println("두 번째 숫자 : ");
			b = sc.nextInt();
			System.out.println(a + " ÷ " + b + " = " + (a/b));
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력을 해야 합니다.");
		} catch (ArithmeticException e) {		
			System.out.println("0으로 입력하면 안되요");
		} catch (Exception e) {
			//System.err.println(e.getMessage()); //System.err, out 다 가능
			//예외가 일어난 모든 경로까지 출력
			e.printStackTrace();
		}finally {
			//사용 후에 반드시 불필요한 스트림(빨대) 제거(close)  
			sc.close(); //예외가 일어나든 안일어나든 불필요한 리소스를 제거
		}
	}
}
