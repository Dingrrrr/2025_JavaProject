package ch11;

import java.util.StringTokenizer;

public class StringTokenizerEx1 {
	public static void main(String[] args) {
		String str1 = "자바 JSP Android Spring";
		StringTokenizer st1 = new StringTokenizer(str1); //기본 구분자는 공백
		int len1 = st1.countTokens();
		System.out.println(len1);
		while(st1.hasMoreTokens()) {
			System.out.println(st1.nextToken());
		}
		System.out.println("----------------------");
		String str2 = "하니;다니엘:민지;해린:혜인";
		StringTokenizer st2 = new StringTokenizer(str2,";:");
		int len2 = st2.countTokens();
		String arr2[] = new String[len2];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = st2.nextToken();
			System.out.println(arr2[i]);
		}
	}
}
