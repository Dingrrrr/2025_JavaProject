package ch10;

public class ExceptionEx6 {
	public static void main(String[] args) {
		try {
			exec3();
			System.out.println("예외없이 실행");
		} catch (Exception e) {
			e.printStackTrace(); //throws가 실행되는 경로를 보여주기 위한 코드
		}
	}
	
	public static void exec1() throws Exception {
		//예외가 일어날 수 있는 코드가 있다고 가정
		int c = 10/0;
	}
	public static void exec2() throws Exception {
		exec1();
	}
	public static void exec3() throws Exception {
		exec2();
	}
}
