package ch10;

public class ExceptionEx1 {
	public static void main(String[] args) {
		try { //예외가 일어날 가능성이 있는 코드가 들어가는 영역.
			int a = 10, b = 0;
			System.out.println(a + b);
			System.out.println(a - b);
			System.out.println(a * b);
			System.out.println(a / b); //유효처리가 적절하지 않은 코드
			System.out.println(a % b);
			System.out.println("보이나요?");
		} catch (Exception e) {
			//예외가 일어나면 실행되는 영역
			System.err.println("예외가 발생");
			System.err.println(e.getMessage());
		}
		System.out.println("End");
	}
}
