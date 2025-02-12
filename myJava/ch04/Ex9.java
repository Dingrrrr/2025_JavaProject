package ch04;

public class Ex9 {

	public static void main(String[] args) {
		//while문을 이용해서 1~10까지의 합을 구하시오.
		int sum = 0;
		int i = 0;
		while(i<11) {
			sum += i;
			i++;
		}
		System.out.println(sum);
		
		while(true) {
			System.out.println("무한반복");
			if(true) {
				break; //자기를 감싸고있는 반복을 빠져 나옴.
			}//--if
		}//--while
		
		do {
			System.out.println("무조건 한번은 실행");
		} while (false);
	}
}
