package ch04;

public class Ex5 {

	public static void main(String[] args) {
		//제어문은 중첩이 가능
		int a = 20;
		if(a>0) {
			if(a%2==0) {
				System.out.println(a + "는 양수이고 짝수입니다.");
			}else {
				System.out.println(a + "는 양수이고 홀수입니다.");
			}
		}else if(a<0) {
			System.out.println(a + "는 음수입니다.");
		}
	}
}
