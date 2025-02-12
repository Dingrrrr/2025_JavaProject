package ch10;

public class ExceptionEx2 {
	public static void main(String[] args) {
		try { //예외 가능성 있는 코드가 들어가는 블럭
			int arr[] = new int[3];
			arr[0] = 1;
			arr[1] = 2;
			arr[2] = 3;
			arr[3] = 4;
		} catch (Exception e) { //예외가 일어났을때 실행되는 블럭
			System.err.println(e.getMessage());
		} finally { //예외발생에 관계없이 무조건 실행되는 블럭
			System.out.println("finally");
		}
	}
}
