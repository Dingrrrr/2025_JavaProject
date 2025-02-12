package ch04;

import java.util.Arrays;

public class LuckyLotto {

	public static void main(String[] args) {
		int arr[] = getLotto();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
	}

	//1~45사이의 난수를 오름차순으로 리턴
	public static int[] getLotto() {
		int lotto[] = new int[6];
		for (int i = 0; i < lotto.length; i++) {
			lotto[i] = (int)(Math.random()*45)+1;
		
			//중복제거 로직
			for(int j = 0; j < i; j++) {
				if(lotto[i]==lotto[j]) {
					i--;
					break;
				}
			}
		}
		
		
		//오름차순 정렬
		Arrays.sort(lotto);
		return lotto;
	}
}
