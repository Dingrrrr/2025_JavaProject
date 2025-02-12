package ch04;

import java.util.Arrays;

public class LuckyLotto3 {

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
			int num = (int)(Math.random()*45)+1;
			if(isDuplication(lotto, num)) {
				//중복이면
				i--;
			}else {
				//중복이 아니면 배열에 리턴
				lotto[i] = num;
			}
			
		}
		//오름차순 정렬
		Arrays.sort(lotto);
		return lotto;
	}
	
	public static boolean isDuplication(int arr[], int num) {
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			str+=arr[i]+" ";
		}
		return str.contains(String.valueOf(num));
	}
}
