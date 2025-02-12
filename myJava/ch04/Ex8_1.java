package ch04;

public class Ex8_1 {

	public static void main(String[] args) {
		/*
		 * 문제1.for 문을 이용해서 1~10까지의 합을 구하시오.
		 * (식과 함께) 1 + 2 +.... + 10 = 55
		 */
		int sum = 0;
		for(int i = 0; i<11; i++) {
			if(i!=0)
				sum += i;
				System.out.println(i + " + " + sum);
		}
		
		
	}

}
