package ch04;

public class Ex10 {

	public static void main(String[] args) {
		
		int i = 0;
		while(true) {
			if(i==10)
				break;
			i++;
		}
		System.out.println(i);
		
		/*for문과 break문을 사용하여 1에서 n까지의 합이 100 이상 최대한 가까운 
		 * 합을 리턴*/
		int sum = 0;
		for(int j = 0; true; j++) {
			if(sum>100) break;
			sum += j;
			//System.out.println(j);
		}
		System.out.println(sum);
		/*for 문을 이용하여 순차적으로 1에서 n까지의 합이 100 이하에 가장 가까운 n과 sum 값 리턴*/
		sum = 0;
		int n = 0;
		for(int j = 1; true; j++) {
			sum += j;
			if(sum>100) {
				sum -= j;
				n = j-1;
				break;
			}
		}
		System.out.println("n : " + n + "sum : " + sum);
		
		/*1~10사이의 짝수를 출력하시오*/
		for(int j = 1; j<11; j++) {
			if(j%2==0)
				System.out.println(j + "\t");
		}
		/*1~10사이의 짝수를 출력하시오(continue 사용)*/
		for(int j = 1; j<11; j++) {
			if(j%2!=0)
				continue; //코드 실행이 아래로 가지않고 증감치로 바로 감.ㄴ
				System.out.println(j + "\t");
		}
		/*1~20사이에 3의 배수의 식과 합을 출력하시오.(continue)
		  * 3 + 6 + 9 + 12 + 15 + 18 = 63 */
		for(int j = 1; j<21; j++) {
			if(j%3!=0)
				continue;
			if(j>3)
				System.out.print(" + ");
			sum+=j;
			System.out.print(j);
		}
		System.out.println(" = " + sum);
	}
}
