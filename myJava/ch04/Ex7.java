package ch04;

import java.util.Iterator;

public class Ex7 {
	public static void main(String[] args) {
		//for문을 이용해서 1~10까지 합을 구하시오
		int sum = 0;
		for (int i = 0; i < 11; i++) {
			sum += i;
		}
		System.out.println("sum : " + sum);
		int j = 0;
		for( ; j < 5; j++){
			System.out.println("j : " + j);
		}
		for(int i=0; i<Integer.MAX_VALUE; i++) {
			System.out.println("i : " + i);
		}
		for ( ; ; ) {
			System.out.println("무한반복");
		}
	}
}
