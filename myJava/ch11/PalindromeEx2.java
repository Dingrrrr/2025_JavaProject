package ch11;

import java.util.Arrays;

public class PalindromeEx2 {

	public static final int MAX = 100000;
	
	public static void main(String[] args) {
		int sum[] = new int[3];
		for (int i = 0; i <= MAX; i++) {
			if(isPalindrome(i, 10)) {
				//10진수로 변환된 대칭수가 true
				sum[0]+=i;
			}
			if(isPalindrome(i, 2)) {
				//2진수로 변환된 대칭수가 true
				sum[1]+=i;
			}
			if(isPalindrome(i, 10)&&isPalindrome(i, 2)) {
				//10진수, 2진수로 변환된 대칭수가 true
				sum[2]+=i;
			}
		}
		System.out.println(Arrays.toString(sum));
	}
	
	//매개변수 a는 비교되는 값, radix는 진수를 입력
	public static boolean isPalindrome(int a, int radix) {
		StringBuffer sb = new StringBuffer(Integer.toString(a, radix));
		return sb.toString().equals(sb.reverse().toString());
	}
	
}
