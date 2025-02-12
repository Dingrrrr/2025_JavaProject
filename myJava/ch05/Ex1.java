package ch05;

import java.util.Iterator;

public class Ex1 {

	public static void main(String[] args) {
		//1. 배열 변수를 선언
		int arr[];
		//2. 배열 크기 지정(불변 fix)
		arr = new int[5]; // new를 쓰는 이유는 내부적으로 배열 객체가 만들어짐.
		//3. 배열값 할당
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 4;
		arr[4] = 5;
		//arr[5] = 6; // 배열의 크기를 5로 지정했지만 예외가 발생하여 0부터 시작하여 arr[5]는 초과 값.
		//4. 배열의 출력
		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr["+i+"] : " + arr[i]);
		}
		System.out.println("--------------------------");
		int arr1[] = new int[3];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = i * 10;
			System.out.println("arr1["+i+"] : " + arr1[i]);
		}
		System.out.println("--------------------------");
		//배열 선언과 동시에 할당
		int arr2[] = {1, 2, 3, 4, 5};
		String str[] = {"Java", "JSP", "Flutter", "Spring"};
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
	}

}
